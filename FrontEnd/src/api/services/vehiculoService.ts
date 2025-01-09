/***********************************************
 * src/api/services/vehiculoService.ts
 ***********************************************/

/**
 * Servicios relacionados con vehículos.
 * - Define interfaces para solicitudes y respuestas.
 * - Proporciona funciones para interactuar con el backend.
 */

import api from "../axiosConfig"; // Instancia configurada de Axios

/**
 * Interface `VehiculoRequest`:
 * - Estructura de los datos para crear un vehículo.
 */
export interface VehiculoRequest {
  nombre: string;
  capacidadMaxVolumen: number; // Capacidad máxima en volumen
  capacidadMaxPeso: number; // Capacidad máxima en peso
  consumoPorKm: number; // Consumo por kilómetro
}

/**
 * Interface `VehiculoResponse`:
 * - Estructura de la respuesta al crear o listar vehículos.
 */
export interface VehiculoResponse {
  id: number;
  nombre: string;
  capacidadMaxVolumen: number;
  capacidadMaxPeso: number;
  consumoPorKm: number;
}

/**
 * Función `crearVehiculo`:
 * - Envía una solicitud para crear un nuevo vehículo.
 */
export async function crearVehiculo(
  data: VehiculoRequest
): Promise<VehiculoResponse> {
  const response = await api.post<VehiculoResponse>("/vehiculos/crear", data);
  return response.data; // Devuelve los datos del vehículo creado
}

/**
 * Función `listarVehiculos`:
 * - Obtiene una lista de vehículos disponibles.
 */
export async function listarVehiculos(): Promise<VehiculoResponse[]> {
  const response = await api.get<VehiculoResponse[]>("/vehiculos/listar");
  return response.data;
}
