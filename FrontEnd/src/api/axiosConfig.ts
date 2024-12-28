import axios from "axios";

const api = axios.create({
  baseURL: "http://192.168.1.16:8080",
  headers: {
    "Content-Type": "application/json",
  },
  withCredentials: true, // Enviar cookies con las solicitudes
});

// Interceptor para agregar el token JWT
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

export const login = async (email: string, password: string) => {
  try {
    const response = await api.post("/login", { email, password });
    const { mensaje: token } = response.data;

    // Guarda el token en localStorage
    localStorage.setItem("token", token);

    return response.data;
  } catch (error) {
    console.error("Error durante el login:", error);
    throw error;
  }
};

export default api;
