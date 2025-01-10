/***********************************************
 * src/components/forms/LoginForm.tsx
 ***********************************************/

/**
 * Este archivo define el formulario de login.
 * - Permite a los usuarios autenticarse.
 * - Usa el contexto de autenticación para manejar la lógica de login.
 */

import React, { useState } from "react";
import { IonButton, IonInput, IonItem, IonList } from "@ionic/react";
import { useAuth } from "../../hooks/context/AuthContext";

/**
 * Componente `LoginForm`:
 * - Contiene inputs para el email y contraseña.
 * - Usa el contexto `useAuth` para realizar la autenticación.
 * - Muestra errores en caso de fallos durante el inicio de sesión.
 */
const LoginForm: React.FC = () => {
  const [email, setEmail] = useState(""); // Estado local para el email
  const [password, setPassword] = useState(""); // Estado local para la contraseña
  const [error, setError] = useState<string | null>(null); // Estado local para errores
  const { login } = useAuth(); // Obtiene la función de login del contexto de autenticación

  /**
   * Maneja el envío del formulario.
   * - Llama a la función `login` del contexto.
   * - Maneja errores en caso de credenciales incorrectas.
   */
  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      await login(email, password); // Llama a la función de autenticación
      setError(null); // Limpia errores previos
    } catch (err) {
      setError("Credenciales incorrectas o error del servidor."); // Muestra el error
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <IonList>
        {/* Input para el email */}
        <IonItem>
          <IonInput
            placeholder="Email"
            type="email"
            value={email}
            onIonChange={(e) => setEmail(e.detail.value!)}
            required
          />
        </IonItem>

        {/* Input para la contraseña */}
        <IonItem>
          <IonInput
            placeholder="Contraseña"
            type="password"
            value={password}
            onIonChange={(e) => setPassword(e.detail.value!)}
            required
          />
        </IonItem>

        {/* Botón de envío */}
        <IonButton expand="full" type="submit">
          Iniciar Sesión
        </IonButton>
      </IonList>

      {/* Mensaje de error */}
      {error && <p style={{ color: "red" }}>{error}</p>}
    </form>
  );
};

export default LoginForm;
