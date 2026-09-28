import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { NotaRequest, NotaResponse } from '../models';

@Injectable({ providedIn: 'root' })
export class NotaService {
  private readonly apiUrl = `${environment.apiBaseUrl}/api/v1/calificaciones/notas`;

  constructor(private http: HttpClient) {}

  listar(): Observable<NotaResponse[]> {
    return this.http.get<NotaResponse[]>(this.apiUrl);
  }

  obtener(id: number): Observable<NotaResponse> {
    return this.http.get<NotaResponse>(`${this.apiUrl}/${id}`);
  }

  registrar(nota: NotaRequest): Observable<NotaResponse> {
    return this.http.post<NotaResponse>(this.apiUrl, nota);
  }

  actualizar(id: number, nota: NotaRequest): Observable<NotaResponse> {
    return this.http.put<NotaResponse>(`${this.apiUrl}/${id}`, nota);
  }

  eliminar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  listarPorAlumno(alumnoId: number): Observable<NotaResponse[]> {
    return this.http.get<NotaResponse[]>(`${this.apiUrl}/alumno/${alumnoId}`);
  }

  listarPorEstado(estado: string): Observable<NotaResponse[]> {
    return this.http.get<NotaResponse[]>(`${this.apiUrl}/estado/${estado}`);
  }

  cerrarActa(id: number): Observable<NotaResponse> {
    return this.http.put<NotaResponse>(`${this.apiUrl}/cerrar/${id}`, {});
  }
}