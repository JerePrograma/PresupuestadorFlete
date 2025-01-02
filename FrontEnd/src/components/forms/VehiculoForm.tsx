import React, { useState } from "react";
import {
  IonButton,
  IonInput,
  IonItem,
  IonList,
  IonContent,
  IonPage,
  IonGrid,
  IonRow,
  IonCol,
  IonLabel,
  IonText,
  IonToast,
  useIonLoading
} from "@ionic/react";
import { crearVehiculo } from "../../api/services/vehiculoService";

const VehiculoForm: React.FC = () => {
  const [formData, setFormData] = useState({
    nombre: "",
    capacidad: "",
    tipoVehiculo: "",
    capacidadMaxVolumen: "",
    capacidadMaxPeso: "",
    consumoPorKm: "",
  });
  const [showToast, setShowToast] = useState(false);
  const [loading, setLoading] = useState(false);

  const handleInputChange = (e: CustomEvent) => {
    const { name, value } = (e.target as HTMLInputElement);
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setLoading(true);
    try {
      await crearVehiculo({
        ...formData,
        capacidadMaxVolumen: Number(formData.capacidadMaxVolumen),
        capacidadMaxPeso: Number(formData.capacidadMaxPeso),
        consumoPorKm: Number(formData.consumoPorKm),
      });
      setShowToast(true);
      setFormData({
        nombre: "",
        capacidad: "",
        tipoVehiculo: "",
        capacidadMaxVolumen: "",
        capacidadMaxPeso: "",
        consumoPorKm: "",
      });
    } catch (error) {
      console.error("Error al crear el vehículo:", error);
    } finally {
      setLoading(false);
    }
  };

  return (
    <IonPage placeholder={undefined} onPointerEnterCapture={undefined} onPointerLeaveCapture={undefined}>
      <IonContent placeholder={undefined} onPointerEnter={undefined} onPointerLeaveCapture={undefined} onPointerEnterCapture={undefined}>
        <IonGrid placeholder={undefined} onPointerEnterCapture={undefined} onPointerLeaveCapture={undefined}>
          <IonRow placeholder={undefined} onPointerEnterCapture={undefined} onPointerLeaveCapture={undefined}>
            <IonCol placeholder={undefined} onPointerEnterCapture={undefined} onPointerLeaveCapture={undefined}>
              <form onSubmit={handleSubmit}>
                <IonList placeholder={undefined} onPointerEnterCapture={undefined} onPointerLeaveCapture={undefined}>
                  <IonItem placeholder={undefined} onPointerEnterCapture={undefined} onPointerLeaveCapture={undefined}>
                    <IonLabel position="floating" placeholder={undefined} onPointerEnterCapture={undefined} onPointerLeaveCapture={undefined}>Nombre</IonLabel>
                    <IonInput
                      name="nombre"
                      value={formData.nombre}
                      onIonChange={handleInputChange}
                      required placeholder={undefined} onPointerEnterCapture={undefined} onPointerLeaveCapture={undefined}                    />
                  </IonItem>
                  <IonItem placeholder={undefined} onPointerEnterCapture={undefined} onPointerLeaveCapture={undefined}>
                    <IonLabel position="floating" placeholder={undefined} onPointerEnterCapture={undefined} onPointerLeaveCapture={undefined}>Capacidad</IonLabel>
                    <IonInput
                      name="capacidad"
                      value={formData.capacidad}
                      onIonChange={handleInputChange}
                      required placeholder={undefined} onPointerEnterCapture={undefined} onPointerLeaveCapture={undefined}                    />
                  </IonItem>
                  <IonItem placeholder={undefined} onPointerEnterCapture={undefined} onPointerLeaveCapture={undefined}>
                    <IonLabel position="floating" placeholder={undefined} onPointerEnterCapture={undefined} onPointerLeaveCapture={undefined}>Tipo de Vehículo</IonLabel>
                    <IonInput
                      name="tipoVehiculo"
                      value={formData.tipoVehiculo}
                      onIonChange={handleInputChange}
                      required placeholder={undefined} onPointerEnterCapture={undefined} onPointerLeaveCapture={undefined}                    />
                  </IonItem>
                </IonList>
                <IonButton type="submit" expand="full" disabled={loading} placeholder={undefined} onPointerEnterCapture={undefined} onPointerLeaveCapture={undefined}>
                  {loading ? "Creando..." : "Crear Vehículo"}
                </IonButton>
              </form>
              <IonToast
                isOpen={showToast}
                message="Vehículo creado exitosamente"
                duration={2000}
                onDidDismiss={() => setShowToast(false)}
              />
            </IonCol>
          </IonRow>
        </IonGrid>
      </IonContent>
    </IonPage>
  );
};

export default VehiculoForm;