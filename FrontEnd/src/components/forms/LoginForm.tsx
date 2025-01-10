/***********************************************
 * src/components/forms/LoginForm.tsx
 ***********************************************/

import React, { useState } from "react";
import { IonButton, IonInput, IonItem, IonList } from "@ionic/react";
import { useAuth } from "../../hooks/context/AuthContext";

const LoginForm: React.FC = () => {
  const [email, setEmail] = useState(""); // Estado local para el email
  const [password, setPassword] = useState(""); // Estado local para la contraseña
  const [error, setError] = useState<string | null>(null); // Estado local para errores
  const { login } = useAuth(); // Obtiene la función de login del contexto de autenticación

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault(); // Prevenir el comportamiento por defecto del formulario
    console.log("Intentando iniciar sesión..."); // Debugging

    try {
      await login(email, password); // Llama a la función de autenticación
      setError(null); // Limpia errores previos
      console.log("Inicio de sesión exitoso."); // Debugging
    } catch (err) {
      console.error("Error durante el inicio de sesión:", err); // Debugging
      setError("Credenciales incorrectas o error del servidor."); // Muestra el error
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <IonList>
        <IonItem>
          <IonInput
            placeholder="Email"
            type="email"
            value={email}
            onIonChange={(e) => setEmail(e.detail.value!)}
            required
          />
        </IonItem>
        <IonItem>
          <IonInput
            placeholder="Contraseña"
            type="password"
            value={password}
            onIonChange={(e) => setPassword(e.detail.value!)}
            required
          />
        </IonItem>
        <IonButton expand="full" type="submit">
          Iniciar Sesión
        </IonButton>
      </IonList>
      {error && <p style={{ color: "red" }}>{error}</p>}
    </form>
  );
};

export default LoginForm;
