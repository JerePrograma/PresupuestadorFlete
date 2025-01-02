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
  IonInput,
  IonLabel,
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
    <IonContent placeholder={undefined} onPointerEnterCapture={undefined} onPointerLeaveCapture={undefined}>
      <form onSubmit={handleSubmit}>
        <IonList placeholder={undefined} onPointerEnterCapture={undefined} onPointerLeaveCapture={undefined}>
          <IonItem placeholder={undefined} onPointerEnterCapture={undefined} onPointerLeaveCapture={undefined}>
            <IonLabel position="floating" placeholder={undefined} onPointerEnterCapture={undefined} onPointerLeaveCapture={undefined}>Origen</IonLabel>
            <IonInput
              value={formData.origen}
              onIonChange={(e) => handleFieldChange("origen", e.detail.value)} placeholder={undefined} onPointerEnterCapture={undefined} onPointerLeaveCapture={undefined}            />
          </IonItem>

          <IonItem placeholder={undefined} onPointerEnterCapture={undefined} onPointerLeaveCapture={undefined}>
            <IonLabel position="floating" placeholder={undefined} onPointerEnterCapture={undefined} onPointerLeaveCapture={undefined}>Destino</IonLabel>
            <IonInput
              value={formData.destino}
              onIonChange={(e) => handleFieldChange("destino", e.detail.value)} placeholder={undefined} onPointerEnterCapture={undefined} onPointerLeaveCapture={undefined}            />
          </IonItem>
          <IonItem placeholder={undefined} onPointerEnterCapture={undefined} onPointerLeaveCapture={undefined}>
            <IonLabel position="floating" placeholder={undefined} onPointerEnterCapture={undefined} onPointerLeaveCapture={undefined}>Volumen de Carga</IonLabel>
            <IonInput
              type="number"
              value={formData.volumenCarga}
              onIonChange={(e) => handleFieldChange("volumenCarga", e.detail.value)} placeholder={undefined} onPointerEnterCapture={undefined} onPointerLeaveCapture={undefined}            />
          </IonItem>

          <IonItem placeholder={undefined} onPointerEnterCapture={undefined} onPointerLeaveCapture={undefined}>
            <IonLabel position="floating" placeholder={undefined} onPointerEnterCapture={undefined} onPointerLeaveCapture={undefined}>Peso de Carga</IonLabel>
            <IonInput
              type="number"
              value={formData.pesoCarga}
              onIonChange={(e) => handleFieldChange("pesoCarga", e.detail.value)} placeholder={undefined} onPointerEnterCapture={undefined} onPointerLeaveCapture={undefined}            />
          </IonItem>

          <IonItem placeholder={undefined} onPointerEnterCapture={undefined} onPointerLeaveCapture={undefined}>
            <IonSelect
              placeholder="Seleccionar Usuarios"
              multiple
              onIonChange={(e) => handleFieldChange("usuariosInvolucrados", e.detail.value)} onPointerEnterCapture={undefined} onPointerLeaveCapture={undefined}            >
              {usuarios.map((user) => (
                <IonSelectOption key={user.id} value={user.id} placeholder={undefined} onPointerEnterCapture={undefined} onPointerLeaveCapture={undefined}>
                  {user.nombre} ({user.tipoUsuario})
                </IonSelectOption>
              ))}
            </IonSelect>
          </IonItem>

          <IonItem placeholder={undefined} onPointerEnterCapture={undefined} onPointerLeaveCapture={undefined}>
            <IonSelect
              placeholder="Seleccionar Vehículo"
              onIonChange={(e) => handleFieldChange("nombreTipoVehiculo", e.detail.value)} onPointerEnterCapture={undefined} onPointerLeaveCapture={undefined}            >
              {vehiculos.map((vehiculo) => (
                <IonSelectOption key={vehiculo.id} value={vehiculo.nombre} placeholder={undefined} onPointerEnterCapture={undefined} onPointerLeaveCapture={undefined}>
                  {vehiculo.nombre} - Capacidad: {vehiculo.capacidad}
                </IonSelectOption>
              ))}
            </IonSelect>
          </IonItem>

          <IonButton expand="full" type="submit" disabled={loading} placeholder={undefined} onPointerEnterCapture={undefined} onPointerLeaveCapture={undefined}>
            {loading ? <IonSpinner name="dots" placeholder={undefined} onPointerEnterCapture={undefined} onPointerLeaveCapture={undefined} /> : "Crear Presupuesto"}
          </IonButton>
        </IonList>
      </form>
    </IonContent>
  );
}

export default PresupuestoForm;