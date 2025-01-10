/***********************************************
 * src/hooks/usePresupuesto.ts
 ***********************************************/

/**
 * Hook personalizado `usePresupuesto`:
 * - Gestiona la lógica relacionada con presupuestos, usuarios y vehículos.
 * - Maneja el estado local, la carga de datos inicial y la creación de presupuestos.
 */

import { useState, useEffect } from "react";
import {
  //listarPresupuestos, // (comentado en este ejemplo)
  crearPresupuesto, // Función para crear un nuevo presupuesto
  listarUsuariosDisponibles, // Función para obtener usuarios disponibles
  listarVehiculosDisponibles, // Función para obtener vehículos disponibles
  PresupuestoRequest, // Tipo de datos para crear presupuestos
  PresupuestoResponse, // Tipo de respuesta de presupuestos
  UsuarioResponse, // Tipo de respuesta de usuarios
  VehiculoResponse, // Tipo de respuesta de vehículos
} from "../api/services/presupuestoService";

export function usePresupuesto() {
  /**
   * Estado local:
   * - `presupuestos`: Lista de presupuestos.
   * - `usuarios`: Lista de usuarios disponibles.
   * - `vehiculos`: Lista de vehículos disponibles.
   * - `loading`: Indica si se están cargando los datos.
   * - `error`: Mensaje de error en caso de fallos.
   */
  const [presupuestos, setPresupuestos] = useState<PresupuestoResponse[]>([]);
  const [usuarios, setUsuarios] = useState<UsuarioResponse[]>([]);
  const [vehiculos, setVehiculos] = useState<VehiculoResponse[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  /**
   * Efecto secundario:
   * - Carga inicial de datos para usuarios y vehículos.
   * - Usa `Promise.all` para ejecutar múltiples solicitudes en paralelo.
   */
  useEffect(() => {
    const fetchData = async () => {
      try {
        const [/*presupuestosRes,*/ usuariosRes, vehiculosRes] =
          await Promise.all([
            //listarPresupuestos(), // Comentado, pero puede habilitarse si necesario
            listarUsuariosDisponibles(),
            listarVehiculosDisponibles(),
          ]);

        // setPresupuestos(presupuestosRes); // Comentado, pero se puede usar para presupuestos
        setUsuarios(usuariosRes); // Actualiza el estado con la lista de usuarios
        setVehiculos(vehiculosRes); // Actualiza el estado con la lista de vehículos
      } catch (err) {
        setError("Error al cargar los datos"); // Captura y muestra el error
        console.error("Error al cargar los datos:", err);
      } finally {
        setLoading(false); // Detiene el estado de carga
      }
    };

    fetchData();
  }, []);

  /**
   * Función `addPresupuesto`:
   * - Crea un nuevo presupuesto mediante `crearPresupuesto`.
   * - Actualiza el estado local para incluir el presupuesto recién creado.
   */
  const addPresupuesto = async (presupuesto: PresupuestoRequest) => {
    try {
      const nuevoPresupuesto = await crearPresupuesto(presupuesto);
      setPresupuestos((prev) => [...prev, nuevoPresupuesto]); // Agrega el nuevo presupuesto a la lista existente
    } catch (err) {
      setError("Error al crear el presupuesto"); // Captura errores al crear un presupuesto
      console.error("Error al crear el presupuesto:", err);
    }
  };

  /**
   * Retorna:
   * - `presupuestos`: Lista de presupuestos.
   * - `usuarios`: Lista de usuarios disponibles.
   * - `vehiculos`: Lista de vehículos disponibles.
   * - `addPresupuesto`: Función para agregar un nuevo presupuesto.
   * - `loading`: Estado de carga.
   * - `error`: Mensaje de error.
   */
  return { presupuestos, usuarios, vehiculos, addPresupuesto, loading, error };
}
