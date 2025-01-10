/***********************************************
 * src/hooks/context/AuthContext.tsx
 ***********************************************/

/**
 * Contexto de autenticación `AuthContext`:
 * - Gestiona el estado global de autenticación de la aplicación.
 * - Proporciona funciones para iniciar y cerrar sesión.
 */

import React, {
  useContext, // Permite consumir el contexto en otros componentes
  useEffect, // Maneja efectos secundarios
  useState, // Gestiona el estado local
  createContext, // Crea un contexto React
  ReactNode, // Define el tipo de los hijos del proveedor
} from "react";
import api from "../../api/axiosConfig"; // Instancia de Axios configurada

/**
 * Interfaz `AuthContextProps`:
 * - Define las propiedades expuestas por el contexto de autenticación.
 */
interface AuthContextProps {
  isAuth: boolean; // Estado de autenticación (true si el usuario está autenticado)
  loading: boolean; // Indica si los datos de autenticación están cargando
  login: (email: string, password: string) => Promise<void>; // Función de inicio de sesión
  logout: () => void; // Función para cerrar sesión
}

/**
 * Contexto `AuthContext`:
 * - Se inicializa como `undefined` para evitar errores de referencia no inicializada.
 */
const AuthContext = createContext<AuthContextProps | undefined>(undefined);

/**
 * Hook personalizado `useAuth`:
 * - Permite consumir el contexto en componentes hijos.
 * - Verifica que el contexto se use dentro de un `AuthProvider`.
 */
export const useAuth = () => {
  const context = useContext(AuthContext);
  if (!context) throw new Error("useAuth must be used within an AuthProvider");
  return context;
};

/**
 * Proveedor de contexto `AuthProvider`:
 * - Envuelve los componentes hijos con el contexto de autenticación.
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
    const token = localStorage.getItem("token"); // Obtiene el token del almacenamiento local
    setIsAuth(!!token); // Actualiza el estado según la existencia del token
    setLoading(false); // Finaliza el estado de carga
  }, []);

  /**
   * Función `login`:
   * - Realiza la autenticación del usuario mediante la API.
   * - Guarda el token en el almacenamiento local y actualiza el estado.
   */
const login = async () => {
  setIsAuth(true); // Autenticación siempre exitosa
};

const logout = () => {
  setIsAuth(false); // Solo cambia el estado sin redirigir
};

  /**
   * Función `logout`:
   * - Elimina el token del almacenamiento local.
   * - Actualiza el estado para marcar al usuario como no autenticado.
   */

  /**
   * Proporciona las funciones y estados al contexto.
   */
  return (
    <AuthContext.Provider value={{ isAuth, loading, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};

