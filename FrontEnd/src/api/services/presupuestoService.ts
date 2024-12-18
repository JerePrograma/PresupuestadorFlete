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

export interface UsuarioResponse {
  id: number;
  nombre: string;
  email: string;
  tipoUsuario: string;
}

export interface VehiculoResponse {
  id: number;
  nombre: string;
  capacidad: string;
}

export async function listarUsuariosDisponibles(): Promise<UsuarioResponse[]> {
  const response = await api.get<UsuarioResponse[]>(
    "api/presupuestos/usuarios-disponibles"
  );
  return response.data;
}

export async function obtenerUsuariosPorRoles(
  roles: string[]
): Promise<UsuarioResponse[]> {
  const response = await api.get<UsuarioResponse[]>(
    "http://localhost:8080/api/usuarios-por-roles?roles=CHOFER&roles=AYUDANTE",
    { params: { roles } }
  );
  return response.data;
}

export async function listarVehiculosDisponibles(): Promise<
  VehiculoResponse[]
> {
  const response = await api.get<VehiculoResponse[]>(
    "api/presupuestos/vehiculos-disponibles"
  );
  return response.data;
}
