import React, { useState } from "react";
import { PresupuestoRequest } from "../../api/services/presupuestoService";
import {
  IonButton,
  IonContent,
  IonItem,
  IonList,
  IonSpinner,
} from "@ionic/react";
import AutocompleteInput from "../AutocompleteInputProps";
import ControlledInput from "../utils/ControlledInput";
import { usePresupuesto } from "../../hooks/usePresupuesto";

function PresupuestoForm() {
  const { addPresupuesto } = usePresupuesto(); // Hook para manejar presupuestos
  const [formData, setFormData] = useState<PresupuestoRequest>({
    origen: "",
    destino: "",
    volumenCarga: 0,
    pesoCarga: 0, // Nuevo campo
    nombreTipoVehiculo: "",
    usuariosInvolucrados: [], // Nuevo campo
  });

  const [loading, setLoading] = useState(false);

  // Manejador genérico para actualizar campos de entrada
  const handleFieldChange = (name: string, value: string | number | any[]) => {
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  // Maneja cambios en AutocompleteInput
  const handlePlaceChanged = (field: "origen" | "destino") => {
    return (place: google.maps.places.PlaceResult | null) => {
      if (place && place.formatted_address) {
        setFormData((prev) => ({
          ...prev,
          [field]: place.formatted_address,
        }));
      }
    };
  };

  // Maneja el envío del formulario
  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setLoading(true);

    try {
      await addPresupuesto(formData); // Agrega el presupuesto al estado global
      console.log("Presupuesto creado:", formData);

      // Restablece el formulario
      setFormData({
        origen: "",
        destino: "",
        volumenCarga: 0,
        pesoCarga: 0,
        nombreTipoVehiculo: "",
        usuariosInvolucrados: [],
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
            <AutocompleteInput
              label="Origen"
              onPlaceChanged={handlePlaceChanged("origen")}
            />
          </IonItem>

          {/* Destino */}
          <IonItem>
            <AutocompleteInput
              label="Destino"
              onPlaceChanged={handlePlaceChanged("destino")}
            />
          </IonItem>

          {/* Volumen de Carga */}
          <ControlledInput
            label="Volumen de Carga"
            name="volumenCarga"
            type="number"
            value={formData.volumenCarga}
            onChange={handleFieldChange}
            required
            min={0}
          />

          {/* Peso de Carga */}
          <ControlledInput
            label="Peso de Carga"
            name="pesoCarga"
            type="number"
            value={formData.pesoCarga}
            onChange={handleFieldChange}
            required
            min={0}
          />

          {/* Tipo de Vehículo */}
          <ControlledInput
            label="Tipo de Vehículo"
            name="nombreTipoVehiculo"
            value={formData.nombreTipoVehiculo}
            onChange={handleFieldChange}
            required
          />

          {/* Usuarios Involucrados */}
          <IonItem>
            <ControlledInput
              label="Usuarios Involucrados (IDs separados por comas)"
              name="usuariosInvolucrados"
              value={formData.usuariosInvolucrados.join(", ")}
              onChange={(name, value) =>
                handleFieldChange(
                  name,
                  value.split(",").map((id) => id.trim())
                )
              }
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
