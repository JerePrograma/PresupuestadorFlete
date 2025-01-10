import React from "react";
import { Route, Redirect } from "react-router-dom";

interface ProtectedRouteProps {
  component: React.ComponentType<any>;
  path: string;
  exact?: boolean;
}

const ProtectedRoute: React.FC<ProtectedRouteProps> = ({
  component: Component,
  ...rest
}) => {
  const token = localStorage.getItem("token"); // Verificar si existe el token

  return (
    <Route
      {...rest}
      render={(props) =>
        token ? (
          <Component {...props} /> // Renderiza el componente si hay token
        ) : (
          <Redirect to="/login" /> // Redirige al login si no hay token
        )
      }
    />
  );
};

export default ProtectedRoute;
