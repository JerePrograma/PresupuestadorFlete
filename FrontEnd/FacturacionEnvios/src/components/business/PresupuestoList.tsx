// src/components/business/PresupuestoList.tsx
import { useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import { RootState } from "../../store";
import { setPresupuestos } from "../../store/slices/presupuestoSlice";
import {
  listarPresupuestos,
  PresupuestoResponse,
} from "../../api/services/presupuestoService";

function PresupuestoList() {
  const dispatch = useDispatch();
  const presupuestos = useSelector(
    (state: RootState) => state.presupuesto.data
  );

  useEffect(() => {
    (async () => {
      const data = await listarPresupuestos();
      dispatch(setPresupuestos(data));
    })();
  }, [dispatch]);

  return (
    <div>
      <h3>Presupuestos Existentes:</h3>
      <ul>
        {presupuestos.map((p: PresupuestoResponse, idx: number) => (
          <li key={idx}>
            {p.origen} - {p.destino} - Total: {p.costoTotal}
          </li>
        ))}
      </ul>
    </div>
  );
}

export default PresupuestoList;
