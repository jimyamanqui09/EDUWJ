import { Component, signal, computed, inject, input, output, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NotaService, NotaRequest, NotaResponse } from '../../../core/services';
import { AlumnoResponse } from '../../../core/services';
import { NOTA_ESTADOS, NotaEstado } from '../../../core/models';

@Component({
  selector: 'app-nota-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './nota-form.html',
  styleUrl: './nota-form.css',
})
export class NotaFormComponent implements OnInit {
  private notaService = inject(NotaService);

  nota = input<NotaResponse | null>(null);
  alumnos = input.required<AlumnoResponse[]>();
  close = output<void>();
  save = output<void>();

  form = signal<NotaRequest>({
    alumnoId: 0,
    evaluacion: '',
    valor: 0,
    ponderacion: 0,
    fechaRegistro: '',
    estado: 'REGISTRADA',
  });

  loading = signal(false);
  error = signal<string | null>(null);

  isEditing = computed(() => !!this.nota());
  estados = NOTA_ESTADOS;

  ngOnInit(): void {
    if (this.isEditing() && this.nota()) {
      const n = this.nota()!;
      this.form.set({
        alumnoId: n.alumnoId,
        evaluacion: n.evaluacion,
        valor: n.valor,
        ponderacion: n.ponderacion,
        fechaRegistro: n.fechaRegistro,
        estado: n.estado,
      });
    } else {
      this.form.update(f => ({ ...f, fechaRegistro: new Date().toISOString().split('T')[0], estado: 'REGISTRADA' }));
    }
  }

  getAlumnoNombre(alumnoId: number): string {
    const alumno = this.alumnos().find(a => a.id === alumnoId);
    return alumno ? `${alumno.apellidoPaterno} ${alumno.apellidoMaterno ?? ''}, ${alumno.nombre}` : `Alumno #${alumnoId}`;
  }

  onSubmit(): void {
    const data = this.form();
    if (!data.alumnoId || !data.evaluacion || data.valor == null || data.ponderacion == null || !data.fechaRegistro) {
      this.error.set('Complete todos los campos obligatorios');
      return;
    }

    if (data.valor < 0 || data.valor > 20) {
      this.error.set('La nota debe estar entre 0 y 20');
      return;
    }

    if (data.ponderacion < 0 || data.ponderacion > 100) {
      this.error.set('La ponderación debe estar entre 0 y 100');
      return;
    }

    this.loading.set(true);
    this.error.set(null);

    const request$ = this.isEditing()
      ? this.notaService.actualizar(this.nota()!.id, data)
      : this.notaService.registrar(data);

    request$.subscribe({
      next: () => {
        this.save.emit();
      },
      error: (err) => {
        this.error.set(err.error?.message ?? 'Error al guardar nota');
        this.loading.set(false);
      },
    });
  }

  onCancel(): void {
    this.close.emit();
  }
}