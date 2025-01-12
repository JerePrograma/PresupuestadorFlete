import React from "react";
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
import UsuarioForm from "../components/forms/UsuarioForm";
import VehiculoForm from "../components/forms/VehiculoForm";

const PresupuestosPage: React.FC = () => {
  return (
    <IonPage>
      <IonHeader>
        <IonToolbar>
          <IonTitle>Presupuestos</IonTitle>
        </IonToolbar>
      </IonHeader>
      <IonContent>
        {/* Formulario para crear presupuestos */}
        <PresupuestoForm />

        {/* Accordion para gestionar usuarios y vehículos */}
        <IonAccordionGroup>
          {/* Gestión de Usuarios */}
          <IonAccordion value="usuarios">
            <IonItem slot="header">
              <IonLabel>Gestión de Usuarios</IonLabel>
            </IonItem>
            <div slot="content">
              <UsuarioForm />
            </div>
          </IonAccordion>

          {/* Gestión de Vehículos */}
          <IonAccordion value="vehiculos">
            <IonItem slot="header">
              <IonLabel>Gestión de Vehículos</IonLabel>
            </IonItem>
            <div slot="content">
              <VehiculoForm />
            </div>
          </IonAccordion>
        </IonAccordionGroup>
      </IonContent>
    </IonPage>
  );
};

export default PresupuestosPage;

