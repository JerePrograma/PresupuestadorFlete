import { Redirect, Route } from "react-router-dom";

const PrivateRoute = ({ component: Component, ...rest }: any) => {
  const isAuthenticated = !!localStorage.getItem("token"); // Verifica si hay un token en localStorage

  return (
    <Route
      {...rest}
      render={(props) =>
        isAuthenticated ? <Component {...props} /> : <Redirect to="/login" />
      }
    />
  );
};

export default PrivateRoute;
