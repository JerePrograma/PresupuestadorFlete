// src/api/services/presupuestoService.ts
import api from '../axiosConfig';

export interface PresupuestoRequest {
  origen: string;
  destino: string;
  volumenCarga: number;
  nombreTipoVehiculo: string;
  distanciaKm: number;
}

export interface PresupuestoResponse {
  origen: string;
  destino: string;
  volumenCarga: number;
  tipoVehiculo: string;
  costoBase: string;
  costoTotal: string;
  extras: string[];
}

export async function crearPresupuesto(data: PresupuestoRequest): Promise<PresupuestoResponse> {
  const response = await api.post<PresupuestoResponse>('api/presupuestos/crear', data);
  return response.data;
}

export async function listarPresupuestos(): Promise<PresupuestoResponse[]> {
  const response = await api.get<PresupuestoResponse[]>('api/presupuestos/listar');
  return response.data;
}
