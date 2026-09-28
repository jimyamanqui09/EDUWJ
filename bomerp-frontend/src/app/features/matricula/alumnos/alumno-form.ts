import { Component, signal, computed, inject, input, output, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AlumnoService, AlumnoRequest, AlumnoResponse } from '../../../core/services';

@Component({
  selector: 'app-alumno-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './alumno-form.html',
  styleUrl: './alumno-form.css',
})
export class AlumnoFormComponent implements OnInit {
  private alumnoService = inject(AlumnoService);

  alumno = input<AlumnoResponse | null>(null);
  close = output<void>();
  save = output<void>();

  form = signal<AlumnoRequest>({
    nombre: '',
    apellidoPaterno: '',
    apellidoMaterno: '',
    dni: '',
    fechaIngreso: '',
    grado: '',
    seccion: '',
  });

  loading = signal(false);
  error = signal<string | null>(null);

  isEditing = computed(() => !!this.alumno());

  ngOnInit(): void {
    if (this.isEditing() && this.alumno()) {
      const a = this.alumno()!;
      this.form.set({
        nombre: a.nombre,
        apellidoPaterno: a.apellidoPaterno,
        apellidoMaterno: a.apellidoMaterno ?? '',
        dni: a.dni,
        fechaIngreso: a.fechaIngreso,
        grado: a.grado,
        seccion: a.seccion ?? '',
      });
    } else {
      this.form.update(f => ({ ...f, fechaIngreso: new Date().toISOString().split('T')[0] }));
    }
  }

  onSubmit(): void {
    const data = this.form();
    if (!data.nombre || !data.apellidoPaterno || !data.dni || !data.fechaIngreso || !data.grado) {
      this.error.set('Complete todos los campos obligatorios');
      return;
    }

    this.loading.set(true);
    this.error.set(null);

    const request$ = this.isEditing()
      ? this.alumnoService.actualizar(this.alumno()!.id, data)
      : this.alumnoService.crear(data);

    request$.subscribe({
      next: () => {
        this.save.emit();
      },
      error: (err) => {
        this.error.set(err.error?.message ?? 'Error al guardar alumno');
        this.loading.set(false);
      },
    });
  }

  onCancel(): void {
    this.close.emit();
  }
}