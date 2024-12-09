// src/pages/PresupuestosPage.tsx
import PresupuestoForm from "../components/business/PresupuestoForm";
import PresupuestoList from "../components/business/PresupuestoList";

function PresupuestosPage() {
  return (
    <div>
      <h2>Página de Presupuestos</h2>
      <PresupuestoForm />
      <PresupuestoList />
    </div>
  );
}

export default PresupuestosPage;
