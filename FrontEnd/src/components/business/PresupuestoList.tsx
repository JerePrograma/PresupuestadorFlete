// src/components/business/PresupuestoList.tsx
import { useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import { RootState } from "../../store";
import { setPresupuestos } from "../../store/slices/presupuestoSlice";
import {
  listarPresupuestos,
  PresupuestoResponse,
} from "../../api/services/presupuestoService";
import { IonList, IonItem, IonLabel } from "@ionic/react";

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
    <IonList>
      {presupuestos.map((p: PresupuestoResponse, idx: number) => (
        <IonItem key={idx}>
          <IonLabel>
            {p.origen} - {p.destino} - Total: {p.costoTotal}
          </IonLabel>
        </IonItem>
      ))}
    </IonList>
  );
}

export default PresupuestoList;
