/**
 * Configuración principal de Axios:
 * - Define la URL base y los encabezados comunes para las solicitudes HTTP.
 * - Habilita el envío de cookies con `withCredentials`.
 */

import axios from "axios";

const api = axios.create({
  baseURL: `${import.meta.env.VITE_API_URL || "http://149.56.68.32:8080/api"}`,
  headers: {
    "Content-Type": "application/json",
  },
});

// Interceptor de solicitudes
api.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem("token"); // Obtiene el token del almacenamiento local
    if (token) {
      config.headers.Authorization = `Bearer ${token}`; // Añade el token al encabezado
    }
    return config;
  },
  (error) => Promise.reject(error)
);

// Interceptor de respuestas
api.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response && error.response.status === 401) {
      localStorage.removeItem("token");
      if (!window.location.pathname.includes("/login")) {
        window.location.href = "/login";
      }
    }
    return Promise.reject(error);
  }
);

export default api;
