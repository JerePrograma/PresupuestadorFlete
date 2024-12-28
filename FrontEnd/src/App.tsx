import {
  IonApp,
  IonRouterOutlet,
  IonSplitPane,
  setupIonicReact,
} from "@ionic/react";
import { IonReactRouter } from "@ionic/react-router";
import { Redirect, Route } from "react-router-dom";
import PresupuestosPage from "./pages/PresupuestosPage";
import VehiculoPage from "./pages/VehiculoPage";
import UsuarioPage from "./pages/UsuarioPage";
import LoginForm from "./components/forms/LoginForm";
import PrivateRoute from "./components/utils/PrivateRoute";
import Menu from "./components/Menu";

/* Core CSS required for Ionic components to work properly */
import "@ionic/react/css/core.css";

/* Basic CSS for apps built with Ionic */
import "@ionic/react/css/normalize.css";
import "@ionic/react/css/structure.css";
import "@ionic/react/css/typography.css";

/* Optional CSS utils that can be commented out */
import "@ionic/react/css/padding.css";
import "@ionic/react/css/float-elements.css";
import "@ionic/react/css/text-alignment.css";
import "@ionic/react/css/text-transformation.css";
import "@ionic/react/css/flex-utils.css";
import "@ionic/react/css/display.css";

/* Dark mode */
import "@ionic/react/css/palettes/dark.system.css";

/* Theme variables */
import "./theme/variables.css";

setupIonicReact();

const App: React.FC = () => {
  return (
    <IonApp>
      <IonReactRouter>
        <IonSplitPane contentId="main">
          <Menu />
          <IonRouterOutlet id="main">
            {/* Ruta para iniciar sesión */}
            <Route path="/login" exact>
              <LoginForm />
            </Route>

            {/* Rutas protegidas */}
            <PrivateRoute path="/presupuestos" component={PresupuestosPage} />
            <PrivateRoute path="/usuarios" component={UsuarioPage} />
            <PrivateRoute path="/vehiculos" component={VehiculoPage} />
          </IonRouterOutlet>
        </IonSplitPane>
      </IonReactRouter>
    </IonApp>
  );
};

export default App;
