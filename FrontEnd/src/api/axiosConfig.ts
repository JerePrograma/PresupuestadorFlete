/***********************************************
 * src/api/index.ts (o como lo llames)
 ***********************************************/
import axios from "axios";

const api = axios.create({
  baseURL: "http://localhost:8080/api",
  headers: {
    "Content-Type": "application/json",
  },
  withCredentials: true, // Enviar cookies con las solicitudes
});

// Interceptor para AGREGAR el token JWT en cada request
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

// Interceptor para CAPTURAR un 401 (token inválido/expirado)
api.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response && error.response.status === 401) {
      localStorage.removeItem("token");
      window.location.href = "/login"; // Fuerza el redirect a /login
    }
    return Promise.reject(error);
  }
);

/***********************************************
 * Ejemplo de funciones exportadas (login/logout)
 ***********************************************/

export const login = async (email: string, password: string) => {
  try {
    const response = await api.post("/login", { email, password });
    console.log("Login response data:", response.data);

    const { token } = response.data; // EXACTAMENTE la misma propiedad
    localStorage.setItem("token", token);

    return response.data;
  } catch (error: any) {
    console.error("Error durante el login:", error);
    if (error.response) {
      console.error("Error Response:", error.response.data);
    } else if (error.request) {
      console.error("Error Request:", error.request);
    } else {
      console.error("Error Message:", error.message);
    }
    throw error;
  }
};

export const logout = () => {
  localStorage.removeItem("token");
  window.location.href = "/login";
};

export default api;
