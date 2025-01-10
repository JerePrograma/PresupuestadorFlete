/***********************************************
 * src/hooks/context/AuthContext.tsx
 ***********************************************/

/**
 * Contexto de autenticación `AuthContext`:
 * - Gestiona el estado global de autenticación de la aplicación.
 * - Proporciona funciones para iniciar y cerrar sesión.
 */

import React, {
  createContext, // Crea un contexto React
  useContext, // Permite consumir el contexto en otros componentes
  useEffect, // Maneja efectos secundarios
  useState, // Gestiona el estado local
  ReactNode, // Define el tipo de los hijos del proveedor
} from "react";
import { login as loginApi } from "../../api/axiosConfig"; // Función de API para el login

/**
 * Interfaz `AuthContextProps`:
 * - Define las propiedades y funciones expuestas por el contexto de autenticación.
 */
interface AuthContextProps {
  isAuth: boolean; // Estado de autenticación (true si el usuario está autenticado)
  loading: boolean; // Indica si los datos de autenticación están cargando
  login: (email: string, password: string) => Promise<void>; // Función de inicio de sesión
  logout: () => void; // Función para cerrar sesión
}

/**
 * Contexto `AuthContext`:
 * - Proporciona acceso a los estados y funciones relacionadas con la autenticación.
 */
const AuthContext = createContext<AuthContextProps | undefined>(undefined);

/**
 * Hook personalizado `useAuth`:
 * - Permite consumir el contexto en componentes hijos.
 * - Verifica que el contexto se use dentro de un `AuthProvider`.
 */
export const useAuth = () => {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error("useAuth must be used within an AuthProvider");
  }
  return context;
};

/**
 * Proveedor de contexto `AuthProvider`:
 * - Envuelve los componentes hijos con el contexto de autenticación.
 * - Maneja el estado y las funciones relacionadas con la autenticación.
 */
export const AuthProvider: React.FC<{ children: ReactNode }> = ({
  children,
}) => {
  const [isAuth, setIsAuth] = useState(false); // Estado de autenticación
  const [loading, setLoading] = useState(true); // Estado de carga

  /**
   * Efecto secundario:
   * - Comprueba si hay un token en el almacenamiento local al montar el componente.
   */
  useEffect(() => {
    const token = localStorage.getItem("token");
    if (token) {
      console.log("Token encontrado. Usuario autenticado.");
      setIsAuth(true);
    } else {
      console.log("No se encontró token. Usuario no autenticado.");
    }
    setLoading(false); // Finaliza el estado de carga
  }, []);

  /**
   * Función `login`:
   * - Realiza la autenticación del usuario mediante la API.
   * - Guarda el token en el almacenamiento local y actualiza el estado.
   */
  const login = async (email: string, password: string) => {
    console.log("Intentando autenticar al usuario...");
    try {
      const data = await loginApi(email, password);
      console.log("Autenticación exitosa. Token recibido:", data.token);
      localStorage.setItem("token", data.token);
      setIsAuth(true); // Marca al usuario como autenticado
    } catch (error) {
      console.error("Error durante el inicio de sesión:", error);
      throw error; // Lanza el error para manejarlo en el componente
    }
  };

  /**
   * Función `logout`:
   * - Elimina el token del almacenamiento local.
   * - Actualiza el estado para marcar al usuario como no autenticado.
   */
  const logout = () => {
    console.log("Cerrando sesión del usuario...");
    localStorage.removeItem("token");
    setIsAuth(false);
    window.location.href = "/login"; // Redirige al usuario a la página de login
  };

  /**
   * Proporciona las funciones y estados al contexto.
   */
  return (
    <AuthContext.Provider value={{ isAuth, loading, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};

export default AuthContext;
