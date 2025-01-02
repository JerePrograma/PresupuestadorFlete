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
// import PresupuestoList from "../components/business/PresupuestoList";
import UsuarioForm from "../components/forms/UsuarioForm";
import VehiculoForm from "../components/forms/VehiculoForm";
import { LoadScript, Libraries } from "@react-google-maps/api";

const libraries: Libraries = ["places"];

const PresupuestosPage: React.FC = () => {

  
  return (
    <IonPage placeholder={undefined} onPointerEnterCapture={undefined} onPointerLeaveCapture={undefined}>
      <IonHeader placeholder={undefined} onPointerEnterCapture={undefined} onPointerLeaveCapture={undefined}>
        <IonToolbar placeholder={undefined} onPointerEnterCapture={undefined} onPointerLeaveCapture={undefined}>
          <IonTitle placeholder={undefined} onPointerEnterCapture={undefined} onPointerLeaveCapture={undefined}>Presupuestos</IonTitle>
        </IonToolbar>
      </IonHeader>
      <IonContent placeholder={undefined} onPointerEnterCapture={undefined} onPointerLeaveCapture={undefined}>
        <LoadScript
          googleMapsApiKey="AIzaSyDpc10F7TycEIoHckCUKThh-NZRcXsfub0"
          libraries={libraries}
        >
          {/* Presupuesto Form */}
          <PresupuestoForm />

          {/* Presupuesto List */}
          {/* <PresupuestoList /> */}

          {/* Accordion for Usuarios and Vehículos */}
          <IonAccordionGroup placeholder={undefined} onPointerEnterCapture={undefined} onPointerLeaveCapture={undefined}>
            {/* Usuarios Section */}
            <IonAccordion value="usuarios" placeholder={undefined} onPointerEnterCapture={undefined} onPointerLeaveCapture={undefined}>
              <IonItem slot="header" placeholder={undefined} onPointerEnterCapture={undefined} onPointerLeaveCapture={undefined}>
                <IonLabel placeholder={undefined} onPointerEnterCapture={undefined} onPointerLeaveCapture={undefined}>Gestión de Usuarios</IonLabel>
              </IonItem>
              <div slot="content">
                <UsuarioForm />
              </div>
            </IonAccordion>

            {/* Vehículos Section */}
            <IonAccordion value="vehiculos" placeholder={undefined} onPointerEnterCapture={undefined} onPointerLeaveCapture={undefined}>
              <IonItem slot="header" placeholder={undefined} onPointerEnterCapture={undefined} onPointerLeaveCapture={undefined}>
                <IonLabel placeholder={undefined} onPointerEnterCapture={undefined} onPointerLeaveCapture={undefined}>Gestión de Vehículos</IonLabel>
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