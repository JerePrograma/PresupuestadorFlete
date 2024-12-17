import api from "../axiosConfig";

export interface VehiculoRequest {
  nombre: string;
  capacidadMaxVolumen: number;
  capacidadMaxPeso: number;
  consumoPorKm: number;
}

export interface VehiculoResponse {
  id: number;
  nombre: string;
  capacidadMaxVolumen: number;
  capacidadMaxPeso: number;
  consumoPorKm: number;
}

export async function crearVehiculo(
  data: VehiculoRequest
): Promise<VehiculoResponse> {
  const response = await api.post<VehiculoResponse>("api/vehiculos", data);
  return response.data;
}

export async function listarVehiculos(): Promise<VehiculoResponse[]> {
  const response = await api.get<VehiculoResponse[]>("api/vehiculos");
  return response.data;
}
