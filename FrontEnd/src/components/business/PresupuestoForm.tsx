// src/components/business/PresupuestoForm.tsx
import { useState } from "react";
import {
  crearPresupuesto,
  PresupuestoRequest,
} from "../../api/services/presupuestoService";
import {
  IonButton,
  IonInput,
  IonItem,
  IonLabel,
  IonList,
  IonContent,
} from "@ionic/react";

function PresupuestoForm() {
  const [formData, setFormData] = useState<PresupuestoRequest>({
    origen: "",
    destino: "",
    volumenCarga: 0,
    nombreTipoVehiculo: "",
    distanciaKm: 0,
  });

  const handleChange = (e: any) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    const response = await crearPresupuesto(formData);
    console.log("Presupuesto creado:", response);
  };

  return (
    <IonContent>
      <IonList>
        <IonItem>
          <IonLabel position="floating">Origen</IonLabel>
          <IonInput
            name="origen"
            value={formData.origen}
            onIonChange={handleChange}
          />
        </IonItem>
        <IonItem>
          <IonLabel position="floating">Destino</IonLabel>
          <IonInput
            name="destino"
            value={formData.destino}
            onIonChange={handleChange}
          />
        </IonItem>
        <IonItem>
          <IonLabel position="floating">Volumen de Carga</IonLabel>
          <IonInput
            name="volumenCarga"
            type="number"
            value={formData.volumenCarga}
            onIonChange={handleChange}
          />
        </IonItem>
        <IonItem>
          <IonLabel position="floating">Tipo de Vehículo</IonLabel>
          <IonInput
            name="nombreTipoVehiculo"
            value={formData.nombreTipoVehiculo}
            onIonChange={handleChange}
          />
        </IonItem>
        <IonItem>
          <IonLabel position="floating">Distancia (km)</IonLabel>
          <IonInput
            name="distanciaKm"
            type="number"
            value={formData.distanciaKm}
            onIonChange={handleChange}
          />
        </IonItem>
      </IonList>
      <IonButton expand="full" onClick={handleSubmit}>
        Crear Presupuesto
      </IonButton>
    </IonContent>
  );
}

export default PresupuestoForm;
