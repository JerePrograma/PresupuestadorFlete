import React, { useState } from "react";
import { PresupuestoRequest } from "../../api/services/presupuestoService";
import {
  IonButton,
  IonContent,
  IonItem,
  IonList,
  IonSpinner,
  IonSelect,
  IonSelectOption,
} from "@ionic/react";
import { usePresupuesto } from "../../hooks/usePresupuesto";

function PresupuestoForm() {
  const { addPresupuesto, usuarios, vehiculos } = usePresupuesto();
  const [formData, setFormData] = useState<PresupuestoRequest>({
    origen: "",
    destino: "",
    volumenCarga: 0,
    pesoCarga: 0,
    nombreTipoVehiculo: "",
    usuariosInvolucrados: [],
  });

  const [loading, setLoading] = useState(false);

  const handleFieldChange = (name: string, value: any) => {
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setLoading(true);
    try {
      await addPresupuesto(formData);
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
          <IonItem>
            <IonSelect
              placeholder="Seleccionar Usuarios"
              multiple
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
          </IonItem>

          <IonItem>
            <IonSelect
              placeholder="Seleccionar Vehículo"
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
          </IonItem>

          <IonButton expand="full" type="submit" disabled={loading}>
            {loading ? <IonSpinner name="dots" /> : "Crear Presupuesto"}
          </IonButton>
        </IonList>
      </form>
    </IonContent>
  );
}

export default PresupuestoForm;
