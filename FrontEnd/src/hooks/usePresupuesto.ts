// src/hooks/usePresupuesto.ts
import { useState, useEffect } from "react";
import {
  listarPresupuestos,
  crearPresupuesto,
  listarUsuariosDisponibles,
  listarVehiculosDisponibles,
  PresupuestoRequest,
  PresupuestoResponse,
  UsuarioResponse,
  VehiculoResponse,
} from "../api/services/presupuestoService";

export function usePresupuesto() {
  const [data, setData] = useState<PresupuestoResponse[]>([]);
  const [usuarios, setUsuarios] = useState<UsuarioResponse[]>([]);
  const [vehiculos, setVehiculos] = useState<VehiculoResponse[]>([]);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    (async () => {
      try {
        const [presupuestosRes, usuariosRes, vehiculosRes] = await Promise.all([
          listarPresupuestos(),
          listarUsuariosDisponibles(),
          listarVehiculosDisponibles(),
        ]);
        setData(presupuestosRes);
        setUsuarios(usuariosRes);
        setVehiculos(vehiculosRes);
      } catch (err) {
        setError("Error al cargar datos");
        console.error(err);
      }
    })();
  }, []);

  const addPresupuesto = async (presupuesto: PresupuestoRequest) => {
    try {
      const nuevoPresupuesto = await crearPresupuesto(presupuesto);
      setData((prev) => [...prev, nuevoPresupuesto]);
    } catch (err) {
      setError("Error al crear el presupuesto");
      console.error(err);
    }
  };

  return { data, usuarios, vehiculos, addPresupuesto, error };
}
