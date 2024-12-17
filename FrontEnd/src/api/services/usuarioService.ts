import axios from "axios";

const api = axios.create({
  baseURL: "http://localhost:8080",
  withCredentials: true, // Permitir el envío de cookies
});

export const crearUsuario = async (data: any) => {
  return await api.post("/api/usuarios/crear", data);
};
