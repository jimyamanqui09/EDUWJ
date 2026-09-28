import { ComponentFixture, TestBed } from '@angular/core/testing';
import { NotaListComponent } from './nota-list';
import { NotaService, AlumnoService } from '../../../core/services';
import { of, throwError } from 'rxjs';
import { signal } from '@angular/core';

describe('NotaListComponent', () => {
  let component: NotaListComponent;
  let fixture: ComponentFixture<NotaListComponent>;
  let notaServiceSpy: jasmine.SpyObj<NotaService>;
  let alumnoServiceSpy: jasmine.SpyObj<AlumnoService>;

  const mockAlumnos = [
    { id: 1, nombre: 'Juan', apellidoPaterno: 'Pérez', apellidoMaterno: 'García', dni: '12345678', fechaIngreso: '2024-03-01', grado: '5to', seccion: 'A' },
    { id: 2, nombre: 'María', apellidoPaterno: 'López', apellidoMaterno: 'Martínez', dni: '87654321', fechaIngreso: '2024-03-01', grado: '4to', seccion: 'B' },
  ];

  const mockNotas = [
    { id: 1, alumnoId: 1, evaluacion: 'Examen Parcial', valor: 15.5, ponderacion: 30, fechaRegistro: '2024-04-15', estado: 'REGISTRADA' },
    { id: 2, alumnoId: 2, evaluacion: 'Tarea 1', valor: 18, ponderacion: 20, fechaRegistro: '2024-04-10', estado: 'CERRADA' },
  ];

  beforeEach(async () => {
    notaServiceSpy = jasmine.createSpyObj('NotaService', ['listar', 'listarPorAlumno', 'listarPorEstado', 'eliminar', 'cerrarActa']);
    alumnoServiceSpy = jasmine.createSpyObj('AlumnoService', ['listar']);

    await TestBed.configureTestingModule({
      imports: [NotaListComponent],
      providers: [
        { provide: NotaService, useValue: notaServiceSpy },
        { provide: AlumnoService, useValue: alumnoServiceSpy },
      ],
    }).compileComponents();

    fixture = TestBed.createComponent(NotaListComponent);
    component = fixture.componentInstance;
    alumnoServiceSpy.listar.and.returnValue(of(mockAlumnos));
    notaServiceSpy.listar.and.returnValue(of(mockNotas));
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should load alumnos and notas on init', () => {
    expect(alumnoServiceSpy.listar).toHaveBeenCalled();
    expect(notaServiceSpy.listar).toHaveBeenCalled();
    expect(component.alumnos().length).toBe(2);
    expect(component.notas().length).toBe(2);
  });

  it('should get alumno nombre correctly', () => {
    const nombre = component.getAlumnoNombre(1);
    expect(nombre).toBe('Pérez García, Juan');
  });

  it('should filter notas by alumno', () => {
    component.filterAlumnoId.set(1);
    component.onFilterChange();
    expect(notaServiceSpy.listarPorAlumno).toHaveBeenCalledWith(1);
  });

  it('should filter notas by estado', () => {
    component.filterEstado.set('CERRADA');
    component.onFilterChange();
    expect(notaServiceSpy.listarPorEstado).toHaveBeenCalledWith('CERRADA');
  });

  it('should clear filters', () => {
    component.filterAlumnoId.set(1);
    component.filterEstado.set('CERRADA');
    component.limpiarFiltros();
    expect(component.filterAlumnoId()).toBeNull();
    expect(component.filterEstado()).toBe('');
  });

  it('should get correct estado class', () => {
    expect(component.getEstadoClass('REGISTRADA')).toBe('estado-registrada');
    expect(component.getEstadoClass('CERRADA')).toBe('estado-cerrada');
    expect(component.getEstadoClass('PROVISIONAL')).toBe('estado-provisional');
  });
});