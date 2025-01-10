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
    const hardcodedToken =
      "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJQcmVzdXB1ZXN0YWRvckZsZXRlIiwic3ViIjoic3VwZXJhZG1pbkBtYWlsLmNvbSIsImlkIjoxLCJleHAiOjE3MzY1NTE1NDJ9.kOESp-Rd-RBwm8obJJfwTkiTX4MVTD2br1EaiD6qLbA";
    config.headers.Authorization = `Bearer ${hardcodedToken}`;
    return config;
  },
  (error) => Promise.reject(error)
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
      // Aquí NO rediriges automáticamente a /login
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
