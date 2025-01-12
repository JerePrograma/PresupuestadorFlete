import React, { useState, useRef } from "react";
import {
  IonButton,
  IonContent,
  IonItem,
  IonList,
  IonSpinner,
  IonSelect,
  IonSelectOption,
} from "@ionic/react";
import { GoogleMap, LoadScript, Autocomplete } from "@react-google-maps/api";
import ControlledInput from "../utils/ControlledInput";
import { PresupuestoRequest } from "../../api/services/presupuestoService";
import { usePresupuesto } from "../../hooks/usePresupuesto";

const libraries: "places"[] = ["places"];

function PresupuestoForm() {
  const { addPresupuesto, usuarios, vehiculos, loading, error } =
    usePresupuesto();
  const [formData, setFormData] = useState<PresupuestoRequest>({
    origen: "",
    destino: "",
    volumenCarga: 0,
    pesoCarga: 0,
    nombreTipoVehiculo: "",
    usuariosInvolucrados: [],
  });
  const [submitting, setSubmitting] = useState(false);

  const autocompleteOriginRef = useRef<google.maps.places.Autocomplete | null>(
    null
  );
  const autocompleteDestinationRef =
    useRef<google.maps.places.Autocomplete | null>(null);

  // Maneja cambios en AutocompleteInput para Origen y Destino
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
  const handleFieldChange = (name: string, value: any) => {
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    if (!formData.origen.trim() || !formData.destino.trim()) {
      alert("Debes seleccionar un origen y un destino válidos.");
      return;
    }

    setSubmitting(true);
    try {
      await addPresupuesto(formData);
      console.log("Presupuesto creado:", formData);
      setFormData({
        origen: "",
        destino: "",
        volumenCarga: 0,
        pesoCarga: 0,
        nombreTipoVehiculo: "",
        usuariosInvolucrados: [],
      });
    } catch (err) {
      console.error("Error al crear el presupuesto:", err);
    } finally {
      setSubmitting(false);
    }
  };

  return (
    <IonContent>
      <LoadScript
        googleMapsApiKey="AIzaSyDpc10F7TycEIoHckCUKThh-NZRcXsfub0"
        libraries={libraries}
      >
        <form onSubmit={handleSubmit}>
          <IonList>
            {/* Origen */}
            <IonItem>
              <Autocomplete
                onLoad={(autocomplete) =>
                  (autocompleteOriginRef.current = autocomplete)
                }
                onPlaceChanged={handlePlaceChanged("origen")}
              >
                <input
                  type="text"
                  placeholder="Origen"
                  style={{ width: "100%", padding: "10px" }}
                />
              </Autocomplete>
            </IonItem>

            {/* Destino */}
            <IonItem>
              <Autocomplete
                onLoad={(autocomplete) =>
                  (autocompleteDestinationRef.current = autocomplete)
                }
                onPlaceChanged={handlePlaceChanged("destino")}
              >
                <input
                  type="text"
                  placeholder="Destino"
                  style={{ width: "100%", padding: "10px" }}
                />
              </Autocomplete>
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

            {/* Usuarios Involucrados */}
            <IonItem>
              {loading ? (
                <IonSpinner name="dots" />
              ) : usuarios.length > 0 ? (
                <IonSelect
                  placeholder="Seleccionar Usuarios"
                  multiple
                  value={formData.usuariosInvolucrados}
                  onIonChange={(e) =>
                    handleFieldChange("usuariosInvolucrados", e.detail.value)
                  }
                >
                  {usuarios.map((user) => (
                    <IonSelectOption key={user.id} value={user.id}>
                      {user.nombre} ({user.tipoUsuario})
                    </IonSelectOption>
                  ))}
                </IonSelect>
              ) : (
                <p>No hay usuarios disponibles</p>
              )}
            </IonItem>

            {/* Vehículo */}
            <IonItem>
              {loading ? (
                <IonSpinner name="dots" />
              ) : vehiculos.length > 0 ? (
                <IonSelect
                  placeholder="Seleccionar Vehículo"
                  value={formData.nombreTipoVehiculo}
                  onIonChange={(e) =>
                    handleFieldChange("nombreTipoVehiculo", e.detail.value)
                  }
                >
                  {vehiculos.map((vehiculo) => (
                    <IonSelectOption key={vehiculo.id} value={vehiculo.nombre}>
                      {vehiculo.nombre} - Capacidad: {vehiculo.capacidad}
                    </IonSelectOption>
                  ))}
                </IonSelect>
              ) : (
                <p>No hay vehículos disponibles</p>
              )}
            </IonItem>
          </IonList>

          {error && <p style={{ color: "red" }}>{error}</p>}

          <IonButton
            expand="full"
            type="submit"
            disabled={submitting || loading}
          >
            {submitting ? <IonSpinner name="dots" /> : "Crear Presupuesto"}
          </IonButton>
        </form>
      </LoadScript>
    </IonContent>
  );
}

export default PresupuestoForm;
