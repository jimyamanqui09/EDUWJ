import { Component, signal, computed, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { AlumnoService, AlumnoResponse } from '../../../core/services';
import { NotaService, NotaResponse, NotaEstado, NOTA_ESTADOS } from '../../../core/services';
import { NotaFormComponent } from './nota-form';

@Component({
  selector: 'app-nota-list',
  standalone: true,
  imports: [CommonModule, RouterModule, FormsModule, NotaFormComponent],
  templateUrl: './nota-list.html',
  styleUrl: './nota-list.css',
})
export class NotaListComponent implements OnInit {
  private notaService = inject(NotaService);
  private alumnoService = inject(AlumnoService);

  protected readonly NOTA_ESTADOS = NOTA_ESTADOS;

  notas = signal<NotaResponse[]>([]);
  alumnos = signal<AlumnoResponse[]>([]);
  loading = signal(false);
  error = signal<string | null>(null);
  showForm = signal(false);
  editingNota = signal<NotaResponse | null>(null);
  filterAlumnoId = signal<number | null>(null);
  filterEstado = signal<NotaEstado | ''>('');

  notasFiltradas = computed(() => {
    let result = this.notas();
    if (this.filterAlumnoId()) {
      result = result.filter(n => n.alumnoId === this.filterAlumnoId());
    }
    if (this.filterEstado()) {
      result = result.filter(n => n.estado === this.filterEstado());
    }
    return result;
  });

  getAlumnoNombre(alumnoId: number): string {
    const alumno = this.alumnos().find(a => a.id === alumnoId);
    return alumno ? `${alumno.apellidoPaterno} ${alumno.apellidoMaterno ?? ''}, ${alumno.nombre}` : `Alumno #${alumnoId}`;
  }

  getEstadoClass(estado: string): string {
    switch (estado) {
      case 'CERRADA': return 'estado-cerrada';
      case 'PROVISIONAL': return 'estado-provisional';
      default: return 'estado-registrada';
    }
  }

  ngOnInit(): void {
    this.cargarDatos();
  }

  cargarDatos(): void {
    this.loading.set(true);
    this.error.set(null);

    this.alumnoService.listar().subscribe({
      next: (alumnos) => {
        this.alumnos.set(alumnos);
        this.cargarNotas();
      },
      error: (err) => {
        this.error.set('Error al cargar alumnos');
        this.loading.set(false);
        console.error(err);
      },
    });
  }

  cargarNotas(): void {
    if (this.filterAlumnoId()) {
      this.notaService.listarPorAlumno(this.filterAlumnoId()!).subscribe({
        next: (data) => {
          this.notas.set(data);
          this.loading.set(false);
        },
        error: (err) => {
          this.error.set('Error al cargar notas');
          this.loading.set(false);
          console.error(err);
        },
      });
    } else if (this.filterEstado()) {
      this.notaService.listarPorEstado(this.filterEstado()!).subscribe({
        next: (data) => {
          this.notas.set(data);
          this.loading.set(false);
        },
        error: (err) => {
          this.error.set('Error al cargar notas');
          this.loading.set(false);
          console.error(err);
        },
      });
    } else {
      this.notaService.listar().subscribe({
        next: (data) => {
          this.notas.set(data);
          this.loading.set(false);
        },
        error: (err) => {
          this.error.set('Error al cargar notas');
          this.loading.set(false);
          console.error(err);
        },
      });
    }
  }

  onFilterChange(): void {
    this.cargarNotas();
  }

  limpiarFiltros(): void {
    this.filterAlumnoId.set(null);
    this.filterEstado.set('');
    this.cargarNotas();
  }

  nuevaNota(): void {
    this.editingNota.set(null);
    this.showForm.set(true);
  }

  editarNota(nota: NotaResponse): void {
    this.editingNota.set(nota);
    this.showForm.set(true);
  }

  onFormClose(): void {
    this.showForm.set(false);
    this.editingNota.set(null);
  }

  onFormSave(): void {
    this.cargarNotas();
    this.onFormClose();
  }

  eliminarNota(id: number): void {
    if (!confirm('¿Está seguro de eliminar esta nota?')) return;

    this.notaService.eliminar(id).subscribe({
      next: () => this.cargarNotas(),
      error: (err) => {
        this.error.set('Error al eliminar nota');
        console.error(err);
      },
    });
  }

  cerrarActa(id: number): void {
    if (!confirm('¿Cerrar el acta de esta nota? No se podrá modificar.')) return;

    this.notaService.cerrarActa(id).subscribe({
      next: () => this.cargarNotas(),
      error: (err) => {
        this.error.set('Error al cerrar acta');
        console.error(err);
      },
    });
  }
}