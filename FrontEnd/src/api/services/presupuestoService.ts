// src/api/services/presupuestoService.ts
import api from "../axiosConfig";

export interface PresupuestoRequest {
  origen: string;
  destino: string;
  volumenCarga: number;
  pesoCarga: number; // Nuevo campo
  nombreTipoVehiculo: string;
  usuariosInvolucrados: string[]; // Nuevo campo como array
}

export interface PresupuestoResponse {
  origen: string;
  destino: string;
  volumenCarga: number;
  tipoVehiculo: string;
  costoBase?: string; // Campo opcional
  costoTotal: string;
  extras: string[];
  mensajeError?: string; // Para manejar posibles errores
}

export async function crearPresupuesto(
  data: PresupuestoRequest
): Promise<PresupuestoResponse> {
  const response = await api.post<PresupuestoResponse>(
    "api/presupuestos/crear",
    data
  );
  return response.data;
}

export async function listarPresupuestos(): Promise<PresupuestoResponse[]> {
  const response = await api.get<PresupuestoResponse[]>(
    "api/presupuestos/listar"
  );
  return response.data;
}
