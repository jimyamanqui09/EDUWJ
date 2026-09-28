import { Component, signal, computed, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { AlumnoService, AlumnoResponse } from '../../../core/services';
import { AlumnoFormComponent } from './alumno-form';

@Component({
  selector: 'app-alumno-list',
  standalone: true,
  imports: [CommonModule, RouterModule, AlumnoFormComponent],
  templateUrl: './alumno-list.html',
  styleUrl: './alumno-list.css',
})
export class AlumnoListComponent implements OnInit {
  private alumnoService = inject(AlumnoService);

  alumnos = signal<AlumnoResponse[]>([]);
  loading = signal(false);
  error = signal<string | null>(null);
  showForm = signal(false);
  editingAlumno = signal<AlumnoResponse | null>(null);

  alumnosConNombreCompleto = computed(() =>
    this.alumnos().map(a => ({
      ...a,
      nombreCompleto: `${a.apellidoPaterno} ${a.apellidoMaterno ?? ''}, ${a.nombre}`.trim(),
    }))
  );

  ngOnInit(): void {
    this.cargarAlumnos();
  }

  cargarAlumnos(): void {
    this.loading.set(true);
    this.error.set(null);
    this.alumnoService.listar().subscribe({
      next: (data) => {
        this.alumnos.set(data);
        this.loading.set(false);
      },
      error: (err) => {
        this.error.set('Error al cargar alumnos');
        this.loading.set(false);
        console.error(err);
      },
    });
  }

  nuevoAlumno(): void {
    this.editingAlumno.set(null);
    this.showForm.set(true);
  }

  editarAlumno(alumno: AlumnoResponse): void {
    this.editingAlumno.set(alumno);
    this.showForm.set(true);
  }

  onFormClose(): void {
    this.showForm.set(false);
    this.editingAlumno.set(null);
  }

  onFormSave(): void {
    this.cargarAlumnos();
    this.onFormClose();
  }

  eliminarAlumno(id: number): void {
    if (!confirm('¿Está seguro de eliminar este alumno?')) return;

    this.alumnoService.eliminar(id).subscribe({
      next: () => this.cargarAlumnos(),
      error: (err) => {
        this.error.set('Error al eliminar alumno');
        console.error(err);
      },
    });
  }
}