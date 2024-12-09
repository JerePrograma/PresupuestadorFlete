// src/components/layout/Navbar.tsx
import { Link } from "react-router-dom";

function Navbar() {
  return (
    <nav>
      <Link to="/">Inicio</Link>
      <Link to="/presupuestos">Presupuestos</Link>
    </nav>
  );
}

export default Navbar;
