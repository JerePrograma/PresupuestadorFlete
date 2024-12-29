import React, { useState } from "react";
import {
  IonButton,
  IonInput,
  IonItem,
  IonList,
  IonAlert,
  IonPage,
  IonContent,
  IonGrid,
  IonRow,
  IonCol,
} from "@ionic/react";
import { login } from "../../api/services/authService";
import { useHistory } from "react-router-dom";

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
    <IonPage>
      <IonContent>
        <IonGrid>
          <IonRow>
            <IonCol size="12">
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
                {error && (
                  <IonAlert isOpen={true} message={error} buttons={["OK"]} />
                )}
              </form>
            </IonCol>
          </IonRow>
        </IonGrid>
      </IonContent>
    </IonPage>
  );
};

export default LoginForm;
