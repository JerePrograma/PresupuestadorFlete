// src/hooks/usePresupuesto.ts
import { useState, useEffect } from "react";
import {
  //listarPresupuestos,
  crearPresupuesto,
  listarUsuariosDisponibles,
  listarVehiculosDisponibles,
  PresupuestoRequest,
  PresupuestoResponse,
  UsuarioResponse,
  VehiculoResponse,
} from "../api/services/presupuestoService";

export function usePresupuesto() {
  const [presupuestos, setPresupuestos] = useState<PresupuestoResponse[]>([]);
  const [usuarios, setUsuarios] = useState<UsuarioResponse[]>([]);
  const [vehiculos, setVehiculos] = useState<VehiculoResponse[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const [/*presupuestosRes,*/ usuariosRes, vehiculosRes] =
          await Promise.all([
            //listarPresupuestos(),
            listarUsuariosDisponibles(),
            listarVehiculosDisponibles(),
          ]);
        //setPresupuestos(presupuestosRes);
        setUsuarios(usuariosRes);
        setVehiculos(vehiculosRes);
      } catch (err) {
        setError("Error al cargar los datos");
        console.error("Error al cargar los datos:", err);
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, []);

  const addPresupuesto = async (presupuesto: PresupuestoRequest) => {
    try {
      const nuevoPresupuesto = await crearPresupuesto(presupuesto);
      setPresupuestos((prev) => [...prev, nuevoPresupuesto]);
    } catch (err) {
      setError("Error al crear el presupuesto");
      console.error("Error al crear el presupuesto:", err);
    }
  };

  return { presupuestos, usuarios, vehiculos, addPresupuesto, loading, error };
}
