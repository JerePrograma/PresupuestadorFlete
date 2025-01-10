/***********************************************
 * src/api/axiosConfig.ts
 ***********************************************/

/**
 * Configuración principal de Axios:
 * - Define la URL base y los encabezados comunes para las solicitudes HTTP.
 * - Habilita el envío de cookies con `withCredentials`.
 */

import axios from "axios";

const api = axios.create({
  baseURL: "http://149.56.68.32/api", // URL base del backend desplegado
  headers: {
    "Content-Type": "application/json",
  },
  withCredentials: true, // Esto permite enviar cookies o encabezados de autenticación
});

/**
 * Interceptor de solicitudes:
 * - Agrega el token JWT en el encabezado Authorization para cada solicitud.
 */
api.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem("token"); // Obtiene el token del almacenamiento local
    if (token) {
      config.headers.Authorization = `Bearer ${token}`; // Añade el token al encabezado
    }
    return config;
  },
  (error) => Promise.reject(error) // Maneja errores antes de enviar la solicitud
);

/**
 * Interceptor de respuestas:
 * - Captura errores 401 (autenticación fallida).
 * - Elimina el token y redirige al usuario a la página de login.
 */
api.interceptors.response.use(
  (response) => response, // Devuelve la respuesta sin modificaciones
  (error) => {
    if (error.response && error.response.status === 401) {
      localStorage.removeItem("token"); // Elimina el token del almacenamiento local
      window.location.href = "/login"; // Redirige a la página de login
    }
    return Promise.reject(error); // Rechaza el error para manejarlo externamente
  }
);

/***********************************************
 * Ejemplo de funciones exportadas
 ***********************************************/

/**
 * Función `login`:
 * - Realiza la solicitud de autenticación al backend.
 * - Guarda el token en el almacenamiento local.
 */
export const login = async (email: string, password: string) => {
  try {
    const response = await api.post("/login", { email, password });
    const { token } = response.data; // Extrae el token de la respuesta
    localStorage.setItem("token", token); // Almacena el token
    return response.data; // Devuelve los datos completos de la respuesta
  } catch (error: any) {
    console.error("Error durante el login:", error);
    throw error; // Lanza el error para manejarlo en los componentes
  }
};

/**
 * Función `logout`:
 * - Elimina el token del almacenamiento local.
 * - Redirige al usuario a la página de login.
 */
export const logout = () => {
  localStorage.removeItem("token");
  window.location.href = "/login";
};

export default api;

/**
 * Servicio `superLogin`:
 * - Envía credenciales al endpoint `/api/login/superlogin`.
 */
export const superLogin = async (email: string, password: string) => {
  try {
    const response = await api.post("/login/superlogin", { email, password });
    const { token } = response.data; // Extrae el token de la respuesta
    localStorage.setItem("token", token); // Almacena el token en localStorage
    return response.data; // Devuelve los datos de la respuesta
  } catch (error: any) {
    console.error("Error durante el superlogin:", error);
    throw error; // Lanza el error para manejarlo en los componentes
  }
};
