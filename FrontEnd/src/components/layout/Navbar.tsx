// src/components/layout/Navbar.tsx
import { IonToolbar, IonButtons, IonButton, IonTitle } from "@ionic/react";
import { Link } from "react-router-dom";

function Navbar() {
  return (
    <IonToolbar>
      <IonTitle>Facturación y Envíos</IonTitle>
      <IonButtons slot="start">
        <IonButton>
          <Link to="/">Inicio</Link>
        </IonButton>
        <IonButton>
          <Link to="/presupuestos">Presupuestos</Link>
        </IonButton>
        <IonButton>
          <Link to="/usuarios">Usuarios</Link>
        </IonButton>
        <IonButton>
          <Link to="/vehiculos">Vehículos</Link>
        </IonButton>
      </IonButtons>
       
       <div style={{ marginRight: '16px', display: 'flex', alignItems: 'center' }}>
          <AvatarChip
            avatarSrc="https://ionicframework.com/docs/img/demos/avatar.svg"
            label="Usuario"
          />
        </div>
    </IonToolbar>
  );
}

export default Navbar;
