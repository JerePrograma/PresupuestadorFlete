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
    "/presupuestos/crear",
    data
  );
  return response.data;
}

/*export async function listarPresupuestos(): Promise<PresupuestoResponse[]> {
  const response = await api.get<PresupuestoResponse[]>("/presupuestos/listar");
  return response.data;
}*/

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

// Lista usuarios con roles CHOFER y AYUDANTE
export async function listarUsuariosDisponibles(): Promise<UsuarioResponse[]> {
  const response = await api.get<UsuarioResponse[]>(
    "/presupuestos/usuarios-disponibles"
  );
  return response.data;
}

// Lista vehículos disponibles
export async function listarVehiculosDisponibles(): Promise<
  VehiculoResponse[]
> {
  const response = await api.get<VehiculoResponse[]>(
    "/presupuestos/vehiculos-disponibles"
  );
  return response.data;
}
