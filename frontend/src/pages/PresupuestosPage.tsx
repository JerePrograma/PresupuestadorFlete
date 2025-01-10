import {
  IonContent,
  IonHeader,
  IonPage,
  IonTitle,
  IonToolbar,
  IonAccordionGroup,
  IonAccordion,
  IonItem,
  IonLabel,
} from "@ionic/react";
import PresupuestoForm from "../components/forms/PresupuestoForm";
//import PresupuestoList from "../components/business/PresupuestoList";
import UsuarioForm from "../components/forms/UsuarioForm";
import VehiculoForm from "../components/forms/VehiculoForm";
import { LoadScript } from "@react-google-maps/api";

const libraries = ["places"];

const PresupuestosPage: React.FC = () => {
  return (
    <IonPage>
      <IonHeader>
        <IonToolbar>
          <IonTitle>Presupuestos</IonTitle>
        </IonToolbar>
      </IonHeader>
      <IonContent>
        <LoadScript
          googleMapsApiKey="AIzaSyDpc10F7TycEIoHckCUKThh-NZRcXsfub0"
          libraries={libraries}
        >
          {/* Presupuesto Form */}
          <PresupuestoForm />

          {/* Presupuesto List */}

          {/* Accordion for Usuarios and Vehículos */}
          <IonAccordionGroup>
            {/* Usuarios Section */}
            <IonAccordion value="usuarios">
              <IonItem slot="header">
                <IonLabel>Gestión de Usuarios</IonLabel>
              </IonItem>
              <div slot="content">
                <UsuarioForm />
              </div>
            </IonAccordion>

            {/* Vehículos Section */}
            <IonAccordion value="vehiculos">
              <IonItem slot="header">
                <IonLabel>Gestión de Vehículos</IonLabel>
              </IonItem>
              <div slot="content">
                <VehiculoForm />
              </div>
            </IonAccordion>
          </IonAccordionGroup>
        </LoadScript>
      </IonContent>
    </IonPage>
  );
};

export default PresupuestosPage;
