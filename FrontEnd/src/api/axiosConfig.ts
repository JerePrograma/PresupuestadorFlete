import axios from "axios";

const api = axios.create({
  baseURL: "http://localhost:8080/api",
  headers: {
    "Content-Type": "application/json",
  },
  withCredentials: true, // Enviar cookies con las solicitudes
});

// Interceptor para agregar el token JWT
api.interceptors.request.use(
  (config) => {
    if (config.method !== "options") {
      // Evita agregar el token en OPTIONS
      const token = localStorage.getItem("token");
      if (token) {
        config.headers.Authorization = `Bearer ${token}`;
      }
    }
    return config;
  },
  (error) => Promise.reject(error)
);

export const login = async (email: string, password: string) => {
  try {
    const response = await axios.post("/auth/login", { email, password });
    return response.data;
  } catch (error) {
    console.error("Error durante el login:", error);
    throw error;
  }
};

export default api;
