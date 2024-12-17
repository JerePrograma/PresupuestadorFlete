// src/hooks/usePresupuesto.ts
import { useState, useEffect } from "react";
import {
  listarPresupuestos,
  crearPresupuesto,
  PresupuestoRequest,
  PresupuestoResponse,
} from "../api/services/presupuestoService";

export function usePresupuesto() {
  const [data, setData] = useState<PresupuestoResponse[]>([]);
  const [error, setError] = useState<string | null>(null); // Nuevo para manejar errores

  useEffect(() => {
    (async () => {
      try {
        const res = await listarPresupuestos();
        setData(res);
      } catch (err) {
        setError("Error al cargar presupuestos");
        console.error(err);
      }
    })();
  }, []);

  const addPresupuesto = async (presupuesto: PresupuestoRequest) => {
    try {
      const nuevoPresupuesto = await crearPresupuesto(presupuesto);
      setData((prev) => [...prev, nuevoPresupuesto]); // Actualiza el estado con el nuevo presupuesto
    } catch (err) {
      setError("Error al crear el presupuesto");
      console.error(err);
    }
  };

  return { data, addPresupuesto, error };
}
