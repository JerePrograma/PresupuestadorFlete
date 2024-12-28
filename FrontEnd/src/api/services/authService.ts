import api from "../axiosConfig";

export const login = async (email: string, password: string) => {
  try {
    const response = await api.post("/login", { email, password });
    const { mensaje: token } = response.data; // Ajustar al formato devuelto por tu API
    localStorage.setItem("token", token);
    return token;
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
