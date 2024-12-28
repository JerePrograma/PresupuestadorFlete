import React, { useEffect } from "react";
import { useHistory } from "react-router-dom";

const ProtectedRoute: React.FC<{ children: React.ReactNode }> = ({
  children,
}) => {
  const history = useHistory();

  useEffect(() => {
    const token = localStorage.getItem("token");
    if (!token) {
      history.push("/login");
    }
  }, [history]);

  return <>{children}</>;
};

export default ProtectedRoute;
