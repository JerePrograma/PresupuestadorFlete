// src/App.tsx
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import HomePage from "./pages/HomePage";
import PresupuestosPage from "./pages/PresupuestosPage";
import Navbar from "./components/layout/Navbar"; // Importas el Navbar
import Footer from "./components/layout/Footer";

function App() {
  return (
    <Router>
      <Navbar /> {/* Aquí colocas el Navbar para que siempre se muestre */}
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/presupuestos" element={<PresupuestosPage />} />
      </Routes>
      <Footer />
    </Router>
  );
}

export default App;
