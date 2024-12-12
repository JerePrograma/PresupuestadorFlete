// src/hooks/usePresupuesto.ts
import { useState, useEffect } from "react";
import { listarPresupuestos, PresupuestoResponse } from "../api/services/presupuestoService";

export function usePresupuesto() {
  const [data, setData] = useState<PresupuestoResponse[]>([]);

  useEffect(() => {
    (async () => {
      const res = await listarPresupuestos();
      setData(res);
    })();
  }, []);

  return data;
}