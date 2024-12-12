import {
  IonContent,
  IonHeader,
  IonPage,
  IonTitle,
  IonToolbar,
} from "@ionic/react";
import PresupuestoForm from "../components/business/PresupuestoForm";
import PresupuestoList from "../components/business/PresupuestoList";

const PresupuestosPage: React.FC = () => {
  return (
    <IonPage>
      <IonHeader>
        <IonToolbar>
          <IonTitle>Presupuestos</IonTitle>
        </IonToolbar>
      </IonHeader>
      <IonContent>
        <PresupuestoForm />
        <PresupuestoList />
      </IonContent>
    </IonPage>
  );
};

export default PresupuestosPage;
