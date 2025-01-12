/**
 * Configuración principal de Axios:
 * - Define la URL base y los encabezados comunes para las solicitudes HTTP.
 * - Habilita el envío de cookies con `withCredentials`.
 */

import axios from "axios";

const api = axios.create({
  baseURL: "https://jereprograma.com/api", // O HTTPS si tienes certificado
  headers: {
    "Content-Type": "application/json",
  },
  withCredentials: true,
});

// Interceptor de solicitudes
api.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem("token");
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
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
      alert("Tu sesión ha expirado. Por favor, inicia sesión nuevamente.");
      if (!window.location.pathname.includes("/login")) {
        window.location.href = "/login";
      }
    }
    return Promise.reject(error);
  }
);

export default api;
