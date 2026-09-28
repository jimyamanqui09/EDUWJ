import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { AlumnoRequest, AlumnoResponse } from '../models';

@Injectable({ providedIn: 'root' })
export class AlumnoService {
  private readonly apiUrl = `${environment.apiBaseUrl}/api/v1/matricula/alumnos`;

  constructor(private http: HttpClient) {}

  listar(): Observable<AlumnoResponse[]> {
    return this.http.get<AlumnoResponse[]>(this.apiUrl);
  }

  obtener(id: number): Observable<AlumnoResponse> {
    return this.http.get<AlumnoResponse>(`${this.apiUrl}/${id}`);
  }

  crear(alumno: AlumnoRequest): Observable<AlumnoResponse> {
    return this.http.post<AlumnoResponse>(this.apiUrl, alumno);
  }

  actualizar(id: number, alumno: AlumnoRequest): Observable<AlumnoResponse> {
    return this.http.put<AlumnoResponse>(`${this.apiUrl}/${id}`, alumno);
  }

  eliminar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  buscarPorDni(dni: string): Observable<AlumnoResponse> {
    return this.http.get<AlumnoResponse>(`${this.apiUrl}/dni/${dni}`);
  }
}