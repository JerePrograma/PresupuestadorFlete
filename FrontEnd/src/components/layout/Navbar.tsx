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
      </IonButtons>
    </IonToolbar>
  );
}

export default Navbar;
