import React from "react";
import {
  IonApp,
  IonRouterOutlet,
  IonSplitPane,
  setupIonicReact,
} from "@ionic/react";
import { IonReactRouter } from "@ionic/react-router";
import { Route } from "react-router-dom";
import PresupuestosPage from "./pages/PresupuestosPage";
import VehiculoPage from "./pages/VehiculoPage";
import UsuarioPage from "./pages/UsuarioPage";
import LoginForm from "./components/forms/LoginForm";
import ProtectedRoute from "./components/utils/ProtectedRoute";
import Menu from "./components/Menu";
import { AuthProvider } from "./hooks/context/AuthContext";

/* CSS imports */
import "@ionic/react/css/core.css";
import "@ionic/react/css/normalize.css";
import "@ionic/react/css/structure.css";
import "@ionic/react/css/typography.css";
import "@ionic/react/css/padding.css";
import "@ionic/react/css/float-elements.css";
import "@ionic/react/css/text-alignment.css";
import "@ionic/react/css/text-transformation.css";
import "@ionic/react/css/flex-utils.css";
import "@ionic/react/css/display.css";
import "@ionic/react/css/palettes/dark.system.css";
import "./theme/variables.css";

setupIonicReact();

const App: React.FC = () => (
  <AuthProvider>
    <IonApp>
      <IonReactRouter>
        <IonSplitPane contentId="main">
          <Menu />
          <IonRouterOutlet id="main">
            <Routes>
              <Route path="/login" element={<LoginForm />} />
              <Route element={<ProtectedRoute />}>
                <Route path="/presupuestos" element={<PresupuestosPage />} />
                <Route path="/usuarios" element={<UsuarioPage />} />
                <Route path="/vehiculos" element={<VehiculoPage />} />
              </Route>
            </Routes>
          </IonRouterOutlet>
        </IonSplitPane>
      </IonReactRouter>
    </IonApp>
  </AuthProvider>
);

export default App;
