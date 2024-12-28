import React, {
  useContext,
  useEffect,
  useState,
  createContext,
  ReactNode,
} from "react";
import api from "../../api/axiosConfig";

interface AuthContextProps {
  isAuth: boolean;
  loading: boolean;
  login: (email: string, password: string) => Promise<void>;
  logout: () => void;
}

const AuthContext = createContext<AuthContextProps | undefined>(undefined);

export const useAuth = () => {
  const context = useContext(AuthContext);
  if (!context) throw new Error("useAuth must be used within an AuthProvider");
  return context;
};

export const AuthProvider: React.FC<{ children: ReactNode }> = ({
  children,
}) => {
  const [isAuth, setIsAuth] = useState(false);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const token = localStorage.getItem("token");
    setIsAuth(!!token);
    setLoading(false);
  }, []);

  const login = async (email: string, password: string) => {
    try {
      const response = await api.post("/login", { email, password });
      const { token } = response.data; // Asegúrate de que el backend devuelva el token como `token`
      localStorage.setItem("token", token);
      setIsAuth(true);
    } catch (error) {
      console.error("Error during login:", error);
      throw error;
    }
  };

  const logout = () => {
    localStorage.removeItem("token");
    setIsAuth(false);
  };

  return (
    <AuthContext.Provider value={{ isAuth, loading, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};
