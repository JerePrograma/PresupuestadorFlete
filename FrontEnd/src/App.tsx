/***********************************************
 * src/App.tsx
 ***********************************************/

import React from "react";
import {
  IonApp,
  IonRouterOutlet,
  IonSplitPane,
  IonTabs,
  IonTabBar,
  IonTabButton,
  IonIcon,
  IonLabel,
  setupIonicReact,
} from "@ionic/react";
import { IonReactRouter } from "@ionic/react-router";
import { Route, Switch } from "react-router-dom";
import { triangle, ellipse, square } from "ionicons/icons";

import LoginForm from "./components/forms/LoginForm";
import PresupuestosPage from "./pages/PresupuestosPage";
import UsuarioPage from "./pages/UsuarioPage";
import VehiculoPage from "./pages/VehiculoPage";
import Menu from "./components/Menu";
import { AuthProvider } from "./hooks/context/AuthContext";

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
import "./theme/variables.css";

setupIonicReact();

const App: React.FC = () => {
  return (
    <AuthProvider>
      <IonApp>
        <IonReactRouter>
          <IonSplitPane contentId="main">
            <Menu />

            <IonTabs>
              <IonRouterOutlet id="main">
                <Switch>
                  <Route exact path="/login" component={LoginForm} />
                  <Route exact path="/" component={PresupuestosPage} />
                  <Route
                    exact
                    path="/presupuestos"
                    component={PresupuestosPage}
                  />
                  <Route exact path="/usuarios" component={UsuarioPage} />
                  <Route exact path="/vehiculos" component={VehiculoPage} />
                </Switch>
              </IonRouterOutlet>

              <IonTabBar slot="bottom">
                <IonTabButton tab="presupuestos" href="/presupuestos">
                  <IonIcon aria-hidden="true" icon={triangle} />
                  <IonLabel>Presupuestos</IonLabel>
                </IonTabButton>
                <IonTabButton tab="usuarios" href="/usuarios">
                  <IonIcon aria-hidden="true" icon={ellipse} />
                  <IonLabel>Usuarios</IonLabel>
                </IonTabButton>
                <IonTabButton tab="vehiculos" href="/vehiculos">
                  <IonIcon aria-hidden="true" icon={square} />
                  <IonLabel>Vehículos</IonLabel>
                </IonTabButton>
              </IonTabBar>
            </IonTabs>
          </IonSplitPane>
        </IonReactRouter>
      </IonApp>
    </AuthProvider>
  );
};

export default App;
