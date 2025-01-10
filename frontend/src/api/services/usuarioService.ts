import api from "../axiosConfig";

export const crearUsuario = async (data: any) => {
  const response = await api.post("/usuarios/crear", data);
  return response.data;
};
