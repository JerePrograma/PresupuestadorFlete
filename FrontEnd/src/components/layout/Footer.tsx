// src/components/layout/Footer.tsx
import { IonFooter, IonToolbar, IonTitle } from "@ionic/react";

function Footer() {
  return (
    <IonFooter placeholder={undefined} onPointerEnterCapture={undefined} onPointerLeaveCapture={undefined}>
      <IonToolbar placeholder={undefined} onPointerEnterCapture={undefined} onPointerLeaveCapture={undefined}>
        <IonTitle placeholder={undefined} onPointerEnterCapture={undefined} onPointerLeaveCapture={undefined}>© 2024 - JerePrograma</IonTitle>
      </IonToolbar>
    </IonFooter>
  );
}

export default Footer;
