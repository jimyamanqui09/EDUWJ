export interface NotaRequest {
  alumnoId: number;
  evaluacion: string;
  valor: number;
  ponderacion: number;
  fechaRegistro: string;
  estado?: string;
}

export interface NotaResponse {
  id: number;
  alumnoId: number;
  evaluacion: string;
  valor: number;
  ponderacion: number;
  fechaRegistro: string;
  estado: string;
}

export interface NotaFormData extends NotaRequest {}

export type NotaEstado = 'REGISTRADA' | 'CERRADA' | 'PROVISIONAL';

export const NOTA_ESTADOS: NotaEstado[] = ['REGISTRADA', 'CERRADA', 'PROVISIONAL'];