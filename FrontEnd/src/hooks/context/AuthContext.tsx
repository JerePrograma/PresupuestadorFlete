/***********************************************
 * src/hooks/context/AuthContext.tsx
 ***********************************************/

/**
 * Contexto de autenticación deshabilitado temporalmente.
 * - No realiza validaciones con token.
 */

import React, { createContext, useContext, useState, ReactNode } from "react";

// Interfaz para el contexto de autenticación
interface AuthContextProps {
  isAuth: boolean;
  login: (email: string, password: string) => Promise<void>;
  logout: () => void;
}

const AuthContext = createContext<AuthContextProps | undefined>(undefined);

export const useAuth = () => {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error("useAuth must be used within an AuthProvider");
  }
  return context;
};

export const AuthProvider: React.FC<{ children: ReactNode }> = ({
  children,
}) => {
  const [isAuth, setIsAuth] = useState(true); // Estado por defecto a `true` para evitar validaciones

  const login = async (email: string, password: string) => {
    console.log("Simulando login...");
    setIsAuth(true); // Marca como autenticado directamente
  };

  const logout = () => {
    console.log("Simulando logout...");
    setIsAuth(false);
  };

  return (
    <AuthContext.Provider value={{ isAuth, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};
