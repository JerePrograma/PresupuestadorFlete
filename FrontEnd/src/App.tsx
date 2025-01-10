/***********************************************
 * src/App.tsx
 ***********************************************/

/**
 * Este archivo define el componente raíz de la aplicación.
 * - Configura Ionic y React Router.
 * - Gestiona rutas públicas y protegidas.
 * - Implementa un diseño con barra de tabs y menú lateral.
 */

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

/* Importación de estilos base de Ionic */
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

/**
 * Inicializa la configuración de Ionic.
 */
setupIonicReact();

/**
 * Componente principal `App`:
 * - Envuelve toda la aplicación en el contexto de autenticación (`AuthProvider`).
 * - Define el diseño con un menú lateral y tabs en la parte inferior.
 * - Implementa la lógica de rutas públicas y protegidas.
 */
const App: React.FC = () => (
  <IonApp>
    <IonReactRouter>
      <Route exact path="/">
        <h1>Test Component</h1>
      </Route>
    </IonReactRouter>
  </IonApp>
);

export default App;
