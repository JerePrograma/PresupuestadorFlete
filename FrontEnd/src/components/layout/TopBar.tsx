import React from 'react';
import { IonHeader, IonToolbar, IonButtons, IonMenuButton, IonTitle, IonIcon } from '@ionic/react';
import { busOutline } from 'ionicons/icons';

const TopBar: React.FC = () => {
  return (
    <IonHeader>
      <IonToolbar>
        <IonButtons slot="start">
          <IonMenuButton />
        </IonButtons>
        <IonTitle>
          Truck-Vector App   <IonIcon icon={busOutline} /> 
        </IonTitle>
      </IonToolbar>
    </IonHeader>
  );
};

export default TopBar;