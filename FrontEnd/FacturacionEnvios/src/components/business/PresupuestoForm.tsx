// src/components/business/PresupuestoForm.tsx
import { useState } from "react";
import {
  crearPresupuesto,
  PresupuestoRequest,
} from "../../api/services/presupuestoService";

function PresupuestoForm() {
  const [formData, setFormData] = useState<PresupuestoRequest>({
    origen: "",
    destino: "",
    volumenCarga: 0,
    nombreTipoVehiculo: "",
    distanciaKm: 0,
  });

  const handleChange = (
    e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>
  ) => {
    const { name, value } = e.target;
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
    const response = await crearPresupuesto(formData);
    console.log("Presupuesto creado:", response);
  };

  return (
    <form onSubmit={handleSubmit}>
      <input
        name="origen"
        placeholder="Origen"
        value={formData.origen}
        onChange={handleChange}
      />
      <input
        name="destino"
        placeholder="Destino"
        value={formData.destino}
        onChange={handleChange}
      />
      <input
        name="volumenCarga"
        type="number"
        placeholder="Volumen de Carga"
        value={formData.volumenCarga}
        onChange={handleChange}
      />
      <input
        name="nombreTipoVehiculo"
        placeholder="Tipo de Vehículo"
        value={formData.nombreTipoVehiculo}
        onChange={handleChange}
      />
      <input
        name="distanciaKm"
        type="number"
        placeholder="Distancia (km)"
        value={formData.distanciaKm}
        onChange={handleChange}
      />
      <button type="submit">Crear Presupuesto</button>
    </form>
  );
}

export default PresupuestoForm;
