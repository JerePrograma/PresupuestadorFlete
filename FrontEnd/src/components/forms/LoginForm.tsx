import React, { useState } from "react";
import { IonButton, IonInput, IonItem, IonList } from "@ionic/react";
import { useAuth } from "../../hooks/context/AuthContext";

const LoginForm: React.FC = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState<string | null>(null);
  const { login } = useAuth(); // Usar el contexto de autenticación

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      await login(email, password); // Llama a la función de login desde el contexto
      setError(null); // Limpia cualquier error previo
    } catch (err) {
      setError("Credenciales incorrectas o error del servidor."); // Maneja el error
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <IonList lines="none" placeholder="" onPointerEnterCapture={() => {}} onPointerLeaveCapture={() => {}}>
        <IonItem lines="none" placeholder="" onPointerEnterCapture={() => {}} onPointerLeaveCapture={() => {}}>
          <IonInput
            placeholder="Email"
            type="email"
            value={email}
            onIonChange={(e) => setEmail(e.detail.value!)}
            required
            onPointerEnterCapture={() => {}}
            onPointerLeaveCapture={() => {}}
          />
        </IonItem>
        <IonItem placeholder="" onPointerEnterCapture={() => {}} onPointerLeaveCapture={() => {}}>
          <IonInput
            placeholder="Password"
            type="password"
            value={password}
            onIonChange={(e) => setPassword(e.detail.value!)}
            required
            onPointerEnterCapture={() => {}}
            onPointerLeaveCapture={() => {}}
          />
        </IonItem>
        <IonButton expand="full" type="submit" placeholder="" onPointerEnterCapture={() => {}} onPointerLeaveCapture={() => {}}>
          Iniciar Sesión
        </IonButton>
      </IonList>
      {error && <p style={{ color: "red" }}>{error}</p>}
    </form>
  );
};

export default LoginForm;