export interface AuthRequest {
  username: string;
  password: string;
}

export interface AuthResponse {
  id: number;
  username: string;
  rol: string;
  token: string;
  mensaje: string;
}

export type UserRole = 'ADMIN' | 'PROFESOR' | 'ALUMNO';