/*import React, { useEffect, useState } from "react";
import { listarPresupuestos } from "../../api/services/presupuestoService";

const PresupuestoList: React.FC = () => {
  const [presupuestos, setPresupuestos] = useState<any[]>([]); // Inicializar como array vacío
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const fetchPresupuestos = async () => {
      try {
        const response = await listarPresupuestos();
        if (Array.isArray(response)) {
          setPresupuestos(response);
        } else {
          setPresupuestos([]); // Si no es un array, setea a vacío
          console.error("La respuesta no es un array:", response);
        }
      } catch (err) {
        console.error("Error al cargar presupuestos:", err);
        setError("Error al cargar presupuestos");
      }
    };

    fetchPresupuestos();
  }, []);

  return (
    <div>
      <h2>Lista de Presupuestos</h2>
      {error && <p style={{ color: "red" }}>{error}</p>}
      <ul>
        {presupuestos.length > 0 ? (
          presupuestos.map((presupuesto, index) => (
            <li key={index}>
              Origen: {presupuesto.origen}, Destino: {presupuesto.destino}
            </li>
          ))
        ) : (
          <p>No hay presupuestos disponibles.</p>
        )}
      </ul>
    </div>
  );
};

export default PresupuestoList;
*/
