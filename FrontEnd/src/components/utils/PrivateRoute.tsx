import { Navigate, Route } from "react-router-dom";

const PrivateRoute = ({ component: Component, ...rest }: any) => {
  const isAuthenticated = !!localStorage.getItem("token"); // Verifica si hay un token en localStorage

  return (
    <Route
      {...rest}
      render={(props) =>
        isAuthenticated ? <Component {...props} /> : <Navigate to="/login" />
      }
    />
  );
};

export default PrivateRoute;
