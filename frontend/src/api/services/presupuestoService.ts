import api from "../axiosConfig";

export interface PresupuestoRequest {
  origen: string;
  destino: string;
  volumenCarga: number;
  pesoCarga: number;
  nombreTipoVehiculo: string;
  usuariosInvolucrados: string[];
}

export interface PresupuestoResponse {
  origen: string;
  destino: string;
  volumenCarga: number;
  tipoVehiculo: string;
  costoBase?: string;
  costoTotal: string;
  extras: string[];
  mensajeError?: string;
}

export const crearPresupuesto = async (
  data: PresupuestoRequest
): Promise<PresupuestoResponse> => {
  const response = await api.post<PresupuestoResponse>(
    "/presupuestos/crear",
    data
  );
  return response.data;
};

export const listarUsuariosDisponibles = async (): Promise<any[]> => {
  const response = await api.get("/presupuestos/usuarios-disponibles");
  return response.data;
};

export const listarVehiculosDisponibles = async (): Promise<any[]> => {
  const response = await api.get("/presupuestos/vehiculos-disponibles");
  return response.data;
};
