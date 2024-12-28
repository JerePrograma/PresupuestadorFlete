import React from 'react';
import { IonHeader, IonToolbar, IonButtons, IonMenuButton, IonTitle } from '@ionic/react';

const TopBar: React.FC = () => {
  return (
    <IonHeader>
      <IonToolbar>
        <IonButtons slot="start">
          <IonMenuButton />
        </IonButtons>
        <IonTitle>Truck-Vector App</IonTitle>
      </IonToolbar>
    </IonHeader>
  );
};

export default TopBar;