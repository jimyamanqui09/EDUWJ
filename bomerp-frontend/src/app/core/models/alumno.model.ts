export interface AlumnoRequest {
  nombre: string;
  apellidoPaterno: string;
  apellidoMaterno?: string;
  dni: string;
  fechaIngreso: string;
  grado: string;
  seccion?: string;
}

export interface AlumnoResponse {
  id: number;
  nombre: string;
  apellidoPaterno: string;
  apellidoMaterno?: string;
  dni: string;
  fechaIngreso: string;
  grado: string;
  seccion?: string;
}

export interface AlumnoFormData extends AlumnoRequest {}