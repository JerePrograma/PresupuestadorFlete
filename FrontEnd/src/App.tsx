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
import { Route, Redirect, Switch } from "react-router-dom";
import { triangle, ellipse, square } from "ionicons/icons";

import LoginForm from "./components/forms/LoginForm";
import PresupuestosPage from "./pages/PresupuestosPage";
import UsuarioPage from "./pages/UsuarioPage";
import VehiculoPage from "./pages/VehiculoPage";
import ProtectedRoute from "./routes/ProtectedRoute";
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
import "./theme/variables.css";

setupIonicReact();

const App: React.FC = () => {
  return (
    <AuthProvider>
      <IonApp>
        <IonReactRouter>
          <IonSplitPane contentId="main" placeholder="" onPointerEnterCapture={() => {}} onPointerLeaveCapture={() => {}}>
            {/* Side menu */}
            <Menu />

            <IonTabs>
              <IonRouterOutlet id="main" placeholder="" onPointerEnterCapture={() => {}} onPointerLeaveCapture={() => {}}>
                <Switch>
                  {/* Ruta de Login (pública) */}
                  <Route exact path="/login" component={LoginForm} />

                  {/* Rutas protegidas (si no hay token, ProtectedRoute redirige a /login) */}
                  <ProtectedRoute
                    exact
                    path="/presupuestos"
                    component={PresupuestosPage}
                  />
                  <ProtectedRoute
                    exact
                    path="/usuarios"
                    component={UsuarioPage}
                  />
                  <ProtectedRoute
                    exact
                    path="/vehiculos"
                    component={VehiculoPage}
                  />

                  {/* Redirección por defecto a /login */}
                  <Route exact path="/">
                    <Redirect to="/login" />
                  </Route>
                </Switch>
              </IonRouterOutlet>

              {/*
                Barra de Tabs en la parte inferior
                Nota: fíjate que en href ponemos la ruta del FRONTEND,
                no la del backend (/api/...).  
              */}
              <IonTabBar slot="bottom" color="primary" hidden={false} dir="ltr" title="" translate="yes" placeholder="" onPointerEnterCapture={() => {}} onPointerLeaveCapture={() => {}}>
                <IonTabButton tab="presupuestos" href="/presupuestos">
                  <IonIcon aria-hidden="true" icon={triangle} placeholder="" onPointerEnterCapture={() => {}} onPointerLeaveCapture={() => {}} />
                  <IonLabel placeholder="" onPointerEnterCapture={() => {}} onPointerLeaveCapture={() => {}}>Presupuestos</IonLabel>
                </IonTabButton>

                <IonTabButton tab="usuarios" href="/usuarios">
                  <IonIcon aria-hidden="true" icon={ellipse} placeholder="" onPointerEnterCapture={() => {}} onPointerLeaveCapture={() => {}} />
                  <IonLabel placeholder="" onPointerEnterCapture={() => {}} onPointerLeaveCapture={() => {}}>Usuarios</IonLabel>
                </IonTabButton>

                <IonTabButton tab="vehiculos" href="/vehiculos">
                  <IonIcon aria-hidden="true" icon={square} placeholder="" onPointerEnterCapture={() => {}} onPointerLeaveCapture={() => {}} />
                  <IonLabel placeholder="" onPointerEnterCapture={() => {}} onPointerLeaveCapture={() => {}}>Vehículos</IonLabel>
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