/***********************
 * src/App.tsx
 ***********************/

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
import Page from "./pages/Page";
import TopBar from "./components/layout/TopBar"; // Importa el nuevo componente

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
          <IonContent>
            <TopBar /> {/* Barra superior */}
            <IonRouterOutlet id="main">
              <Route path="/login" exact>
                <LoginForm />
              </Route>
              <PrivateRoute path="/presupuestos" component={PresupuestosPage} />
              <Redirect exact from="/" to="/login" />
              <Route path="/" exact={true}>
                <Redirect to="/folder/Inbox" />
              </Route>

            <IonTabs>
              <IonRouterOutlet id="main">
                <Switch>
                  {/* Ruta de Login (pública) */}
                  <Route exact path="/login" component={LoginForm} />

              <Route path="/folder/:name" exact={true}>
                <Page />
              </Route>
            </IonRouterOutlet>
          </IonContent>
        </IonSplitPane>
      </IonReactRouter>
    </IonApp>
  );
};

export default App;
