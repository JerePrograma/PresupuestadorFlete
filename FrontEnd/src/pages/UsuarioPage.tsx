import {
  IonContent,
  IonHeader,
  IonPage,
  IonTitle,
  IonToolbar,
} from "@ionic/react";
import UsuarioForm from "../components/forms/UsuarioForm";

const UsuarioPage: React.FC = () => {
  return (
    <IonPage>
      <IonHeader>
        <IonToolbar>
          <IonTitle>Gestión de Usuarios</IonTitle>
        </IonToolbar>
      </IonHeader>
      <IonContent>
        <UsuarioForm />
      </IonContent>
    </IonPage>
  );
};

export default UsuarioPage;
