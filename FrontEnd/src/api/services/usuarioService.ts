/***********************************************
 * src/api/services/usuarioService.ts
 ***********************************************/

/**
 * Servicios relacionados con usuarios.
 * - Proporciona funciones para interactuar con el backend.
 */

import api from "../axiosConfig"; // Instancia configurada de Axios

/**
 * Función `crearUsuario`:
 * - Envía una solicitud para crear un nuevo usuario.
 */
export const crearUsuario = async (data: any) => {
  return await api.post("/usuarios/crear", data);
};
