import {
  IonContent,
  IonHeader,
  IonPage,
  IonTitle,
  IonToolbar,
} from "@ionic/react";
import VehiculoForm from "../components/forms/VehiculoForm";

const VehiculoPage: React.FC = () => {
  return (
    <IonPage>
      <IonHeader>
        <IonToolbar>
          <IonTitle>Gestión de Vehículos</IonTitle>
        </IonToolbar>
      </IonHeader>
      <IonContent>
        <VehiculoForm />
      </IonContent>
    </IonPage>
  );
};

export default VehiculoPage;
