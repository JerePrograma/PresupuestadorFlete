/***********************************************
 * src/api/services/presupuestoService.ts
 ***********************************************/

/**
 * Servicios relacionados con presupuestos.
 * - Define interfaces para solicitudes y respuestas.
 * - Proporciona funciones para interactuar con el backend.
 */

import api from "../axiosConfig"; // Instancia configurada de Axios

/**
 * Interface `PresupuestoRequest`:
 * - Estructura de los datos para crear un presupuesto.
 */
export interface PresupuestoRequest {
  origen: string;
  destino: string;
  volumenCarga: number;
  pesoCarga: number; // Peso de la carga
  nombreTipoVehiculo: string; // Tipo de vehículo requerido
  usuariosInvolucrados: string[]; // Lista de usuarios involucrados
}

/**
 * Interface `PresupuestoResponse`:
 * - Estructura de la respuesta al crear o listar presupuestos.
 */
export interface PresupuestoResponse {
  origen: string;
  destino: string;
  volumenCarga: number;
  tipoVehiculo: string;
  costoBase?: string; // Costo base (opcional)
  costoTotal: string;
  extras: string[]; // Lista de extras añadidos
  mensajeError?: string; // Mensaje en caso de error
}

/**
 * Función `crearPresupuesto`:
 * - Envía una solicitud para crear un nuevo presupuesto.
 */
export async function crearPresupuesto(
  data: PresupuestoRequest
): Promise<PresupuestoResponse> {
  const response = await api.post<PresupuestoResponse>(
    "/presupuestos/crear",
    data
  );
  return response.data; // Devuelve los datos del presupuesto creado
}

/* Función para listar presupuestos (opcional)
export async function listarPresupuestos(): Promise<PresupuestoResponse[]> {
  const response = await api.get<PresupuestoResponse[]>("/presupuestos/listar");
  return response.data;
}
*/

/**
 * Interface `UsuarioResponse`:
 * - Define la estructura de los usuarios disponibles.
 */
export interface UsuarioResponse {
  id: number;
  nombre: string;
  email: string;
  tipoUsuario: string;
}

/**
 * Interface `VehiculoResponse`:
 * - Define la estructura de los vehículos disponibles.
 */
export interface VehiculoResponse {
  id: number;
  nombre: string;
  capacidad: string; // Capacidad del vehículo
}

/**
 * Función `listarUsuariosDisponibles`:
 * - Obtiene una lista de usuarios con roles específicos (CHOFER, AYUDANTE).
 */
export async function listarUsuariosDisponibles(): Promise<UsuarioResponse[]> {
  const response = await api.get<UsuarioResponse[]>(
    "/presupuestos/usuarios-disponibles"
  );
  return response.data;
}

/**
 * Función `listarVehiculosDisponibles`:
 * - Obtiene una lista de vehículos disponibles para presupuestos.
 */
export async function listarVehiculosDisponibles(): Promise<
  VehiculoResponse[]
> {
  const response = await api.get<VehiculoResponse[]>(
    "/presupuestos/vehiculos-disponibles"
  );
  return response.data;
}
