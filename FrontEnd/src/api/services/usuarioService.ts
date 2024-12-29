// src/api/services/usuarioService.ts
import api from "../axiosConfig"; // <-- usa la misma instancia con interceptores

export const crearUsuario = async (data: any) => {
  return await api.post("/usuarios/crear", data);
};
