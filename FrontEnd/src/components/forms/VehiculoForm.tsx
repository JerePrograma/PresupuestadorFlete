import React, { useState } from "react";
import { IonButton, IonInput, IonItem, IonList, IonContent, IonPage, IonGrid, IonRow, IonCol } from "@ionic/react";
import { crearVehiculo } from "../../api/services/vehiculoService";

const VehiculoForm: React.FC = () => {
  const [formData, setFormData] = useState({
    nombre: "",
    capacidadMaxVolumen: 0,
    capacidadMaxPeso: 0,
    consumoPorKm: 0,
  });

  const handleChange = (e: CustomEvent) => {
    const { name, value } = e.detail;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      await crearVehiculo(formData);
      console.log("Vehículo creado:", formData);
    } catch (error) {
      console.error("Error al crear vehículo:", error);
    }
  };

  return (
    <IonPage>
      <IonContent>
        <IonGrid>
          <IonRow>
            <IonCol size="12">
              <form onSubmit={handleSubmit}>
                <IonList>
                  <IonItem>
                    <IonInput
                      name="nombre"
                      placeholder="Nombre del Vehículo"
                      onIonChange={handleChange}
                    />
                  </IonItem>
                  <IonItem>
                    <IonInput
                      name="capacidadMaxVolumen"
                      type="number"
                      placeholder="Capacidad Máxima de Volumen"
                      onIonChange={handleChange}
                    />
                  </IonItem>
                  <IonItem>
                    <IonInput
                      name="capacidadMaxPeso"
                      type="number"
                      placeholder="Capacidad Máxima de Peso"
                      onIonChange={handleChange}
                    />
                  </IonItem>
                  <IonItem>
                    <IonInput
                      name="consumoPorKm"
                      type="number"
                      placeholder="Consumo por Kilómetro"
                      onIonChange={handleChange}
                    />
                  </IonItem>
                  <IonButton expand="full" type="submit">
                    Crear Vehículo
                  </IonButton>
                </IonList>
              </form>
            </IonCol>
          </IonRow>
        </IonGrid>
      </IonContent>
    </IonPage>
  );
};

export default VehiculoForm;