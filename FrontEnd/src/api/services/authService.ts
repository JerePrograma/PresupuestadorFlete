import axios from "../axiosConfig";

interface LoginRequest {
  email: string;
  password: string;
}

export const login = async (data: LoginRequest): Promise<string> => {
  const response = await axios.post("/login", data); // Endpoint del login
  return response.data.token; // Asegúrate de que el backend retorne un 'token'
};
