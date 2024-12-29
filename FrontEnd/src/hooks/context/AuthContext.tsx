/**********************************************
 * src/hooks/context/AuthContext.tsx
 **********************************************/
import React, {
  useContext,
  useEffect,
  useState,
  createContext,
  ReactNode,
} from "react";
import { login as loginApi } from "../../api/axiosConfig";
// ^ importas la función "login" del index.ts que hace `const { token } = ...`

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
    // Verificar si existe un token en el almacenamiento local
    const token = localStorage.getItem("token");
    setIsAuth(!!token);
    setLoading(false);
  }, []);

  const login = async (email: string, password: string) => {
    try {
      // Llama la función de tu capa de API
      const data = await loginApi(email, password);
      // A estas alturas, "loginApi" ya guardó en localStorage el token
      // o si prefieres, puedes setearlo aquí:
      // localStorage.setItem("token", data.token);

      // Actualizar el estado de autenticación
      setIsAuth(true);
    } catch (error) {
      console.error("Error durante el inicio de sesión:", error);
      throw error;
    }
  };

  const logout = () => {
    localStorage.removeItem("token");
    setIsAuth(false);
    window.location.href = "/login";
  };

  return (
    <AuthContext.Provider value={{ isAuth, loading, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};
