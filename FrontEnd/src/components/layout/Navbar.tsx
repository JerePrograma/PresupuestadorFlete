// src/components/layout/Navbar.tsx
import { IonToolbar, IonButtons, IonButton, IonTitle } from "@ionic/react";
import { Link } from "react-router-dom";

function Navbar() {
  return (
    <IonToolbar placeholder={undefined} onPointerEnterCapture={undefined} onPointerLeaveCapture={undefined}>
      <IonTitle placeholder={undefined} onPointerEnterCapture={undefined} onPointerLeaveCapture={undefined}>Facturación y Envíos</IonTitle>
      <IonButtons slot="start" placeholder={undefined} onPointerEnterCapture={undefined} onPointerLeaveCapture={undefined}>
        <IonButton placeholder={undefined} onPointerEnterCapture={undefined} onPointerLeaveCapture={undefined}>
          <Link to="/">Inicio</Link>
        </IonButton>
        <IonButton placeholder={undefined} onPointerEnterCapture={undefined} onPointerLeaveCapture={undefined}>
          <Link to="/presupuestos">Presupuestos</Link>
        </IonButton>
        <IonButton placeholder={undefined} onPointerEnterCapture={undefined} onPointerLeaveCapture={undefined}>
          <Link to="/usuarios">Usuarios</Link>
        </IonButton>
        <IonButton placeholder={undefined} onPointerEnterCapture={undefined} onPointerLeaveCapture={undefined}>
          <Link to="/vehiculos">Vehículos</Link>
        </IonButton>
      </IonButtons>

      <div
        style={{ marginRight: "16px", display: "flex", alignItems: "center" }}
      >
      </div>
    </IonToolbar>
  );
}

export default Navbar;
