import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AlumnoListComponent } from './alumno-list';
import { AlumnoService } from '../../../core/services';
import { of, throwError } from 'rxjs';
import { signal } from '@angular/core';

describe('AlumnoListComponent', () => {
  let component: AlumnoListComponent;
  let fixture: ComponentFixture<AlumnoListComponent>;
  let alumnoServiceSpy: jasmine.SpyObj<AlumnoService>;

  const mockAlumnos = [
    { id: 1, nombre: 'Juan', apellidoPaterno: 'Pérez', apellidoMaterno: 'García', dni: '12345678', fechaIngreso: '2024-03-01', grado: '5to', seccion: 'A' },
    { id: 2, nombre: 'María', apellidoPaterno: 'López', apellidoMaterno: 'Martínez', dni: '87654321', fechaIngreso: '2024-03-01', grado: '4to', seccion: 'B' },
  ];

  beforeEach(async () => {
    alumnoServiceSpy = jasmine.createSpyObj('AlumnoService', ['listar', 'eliminar']);

    await TestBed.configureTestingModule({
      imports: [AlumnoListComponent],
      providers: [{ provide: AlumnoService, useValue: alumnoServiceSpy }],
    }).compileComponents();

    fixture = TestBed.createComponent(AlumnoListComponent);
    component = fixture.componentInstance;
    alumnoServiceSpy.listar.and.returnValue(of(mockAlumnos));
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should load alumnos on init', () => {
    expect(alumnoServiceSpy.listar).toHaveBeenCalled();
    expect(component.alumnos().length).toBe(2);
  });

  it('should compute nombreCompleto correctly', () => {
    const nombres = component.alumnosConNombreCompleto();
    expect(nombres[0].nombreCompleto).toBe('Pérez García, Juan');
    expect(nombres[1].nombreCompleto).toBe('López Martínez, María');
  });

  it('should open form for new alumno', () => {
    component.nuevoAlumno();
    expect(component.showForm()).toBeTrue();
    expect(component.editingAlumno()).toBeNull();
  });

  it('should open form for editing alumno', () => {
    const alumno = mockAlumnos[0];
    component.editarAlumno(alumno);
    expect(component.showForm()).toBeTrue();
    expect(component.editingAlumno()).toEqual(alumno);
  });

  it('should call service on delete and reload', () => {
    alumnoServiceSpy.eliminar.and.returnValue(of(void 0));
    alumnoServiceSpy.listar.and.returnValue(of([mockAlumnos[1]]));

    component.eliminarAlumno(1);
    expect(alumnoServiceSpy.eliminar).toHaveBeenCalledWith(1);
    expect(component.alumnos().length).toBe(1);
  });

  it('should handle error on load', () => {
    alumnoServiceSpy.listar.and.returnValue(throwError(() => new Error('Error')));
    component.cargarAlumnos();
    expect(component.error()).toBe('Error al cargar alumnos');
    expect(component.loading()).toBeFalse();
  });
});