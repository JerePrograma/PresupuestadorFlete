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

export const crearVehiculo = async (
  data: VehiculoRequest
): Promise<VehiculoResponse> => {
  const response = await api.post<VehiculoResponse>("/vehiculos/crear", data);
  return response.data;
};

export const listarVehiculos = async (): Promise<VehiculoResponse[]> => {
  const response = await api.get<VehiculoResponse[]>("/vehiculos/listar");
  return response.data;
};
