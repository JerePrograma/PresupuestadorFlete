import React from 'react';
import { IonChip, IonAvatar, IonLabel } from '@ionic/react';

/**
 * AvatarChip Component
 * Muestra un chip con un avatar e información textual.
 * 
 * Props:
 * - avatarSrc: URL de la imagen del avatar.
 * - label: Texto del chip.
 */
const AvatarChip = ({ avatarSrc, label }) => {
  return (
    <IonChip>
      <IonAvatar>
        <img alt="Avatar" src={avatarSrc} />
      </IonAvatar>
      <IonLabel>{label}</IonLabel>
    </IonChip>
  );
};

export default AvatarChip;
