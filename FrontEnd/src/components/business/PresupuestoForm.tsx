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
  IonSpinner,
} from "@ionic/react";
import AutocompleteInput from "../AutocompleteInputProps"; // Componente creado para Google Places

function PresupuestoForm() {
  const [formData, setFormData] = useState<PresupuestoRequest>({
    origen: "",
    destino: "",
    volumenCarga: 0,
    nombreTipoVehiculo: "",
    distanciaKm: 0,
  });

  const [loading, setLoading] = useState(false);

  const handlePlaceChanged =
    (field: "origen" | "destino") =>
    (place: google.maps.places.PlaceResult | null) => {
      if (place && place.formatted_address) {
        setFormData((prev) => ({
          ...prev,
          [field]: place.formatted_address,
        }));
      }
    };

  const handleChange = (e: CustomEvent) => {
    const { name, value } = e.detail;
    setFormData((prev) => ({
      ...prev,
      [name]:
        name === "volumenCarga" || name === "distanciaKm"
          ? Number(value)
          : value,
    }));
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setLoading(true);

    try {
      const response = await crearPresupuesto(formData);
      console.log("Presupuesto creado:", response);
      // Reset del formulario si es necesario
      setFormData({
        origen: "",
        destino: "",
        volumenCarga: 0,
        nombreTipoVehiculo: "",
        distanciaKm: 0,
      });
    } catch (error) {
      console.error("Error al crear el presupuesto:", error);
    } finally {
      setLoading(false);
    }
  };

  return (
    <IonContent>
      <form onSubmit={handleSubmit}>
        <IonList>
          {/* Origen */}
          <IonItem>
            <IonLabel position="floating">Origen</IonLabel>
            <AutocompleteInput
              label="Origen"
              onPlaceChanged={handlePlaceChanged("origen")}
            />
          </IonItem>

          {/* Destino */}
          <IonItem>
            <IonLabel position="floating">Destino</IonLabel>
            <AutocompleteInput
              label="Destino"
              onPlaceChanged={handlePlaceChanged("destino")}
            />
          </IonItem>

          {/* Volumen de Carga */}
          <IonItem>
            <IonLabel position="floating">Volumen de Carga</IonLabel>
            <IonInput
              name="volumenCarga"
              type="number"
              value={formData.volumenCarga}
              onIonChange={handleChange}
              required
              min="0"
            />
          </IonItem>

          {/* Tipo de Vehículo */}
          <IonItem>
            <IonLabel position="floating">Tipo de Vehículo</IonLabel>
            <IonInput
              name="nombreTipoVehiculo"
              value={formData.nombreTipoVehiculo}
              onIonChange={handleChange}
              required
            />
          </IonItem>

          {/* Distancia (km) */}
          <IonItem>
            <IonLabel position="floating">Distancia (km)</IonLabel>
            <IonInput
              name="distanciaKm"
              type="number"
              value={formData.distanciaKm}
              onIonChange={handleChange}
              required
              min="0"
            />
          </IonItem>
        </IonList>

        {/* Botón de Enviar */}
        <IonButton expand="full" type="submit" disabled={loading}>
          {loading ? <IonSpinner name="dots" /> : "Crear Presupuesto"}
        </IonButton>
      </form>
    </IonContent>
  );
}

export default PresupuestoForm;
