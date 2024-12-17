import React, { useState } from "react";
import { IonButton, IonInput, IonItem, IonList, IonAlert } from "@ionic/react";
import { login } from "../../api/services/authService";
import { useHistory } from "react-router-dom";

const LoginForm: React.FC = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState<string | null>(null);
  const history = useHistory();

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      const token = await login({ email, password });
      localStorage.setItem("token", token); // Guarda el token en el almacenamiento local
      history.push("/presupuestos"); // Redirige a la página principal
    } catch (err) {
      setError("Credenciales incorrectas o error del servidor.");
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
      {error && <IonAlert isOpen={true} message={error} buttons={["OK"]} />}
    </form>
  );
};

export default LoginForm;
