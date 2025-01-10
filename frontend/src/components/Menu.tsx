/****************************************
 * src/components/Menu.tsx
 ****************************************/
import {
  IonContent,
  IonIcon,
  IonItem,
  IonLabel,
  IonList,
  IonListHeader,
  IonMenu,
  IonMenuToggle,
  IonNote,
} from "@ionic/react";
import { useLocation } from "react-router-dom";
import {
  mailOutline,
  mailSharp,
  peopleOutline,
  carOutline,
  logInOutline,
} from "ionicons/icons";
import "./Menu.css";

interface AppPage {
  url: string;
  iosIcon: string;
  mdIcon: string;
  title: string;
}

const appPages: AppPage[] = [
  {
    title: "Presupuestos",
    url: "/presupuestos",
    iosIcon: mailOutline,
    mdIcon: mailSharp,
  },
  {
    title: "Usuarios",
    url: "/usuarios",
    iosIcon: peopleOutline,
    mdIcon: peopleOutline,
  },
  {
    title: "Vehículos",
    url: "/vehiculos",
    iosIcon: carOutline,
    mdIcon: carOutline,
  },
];

const Menu: React.FC = () => {
  const location = useLocation();

  return (
    <IonMenu contentId="main" type="overlay">
      <IonContent>
        <IonList id="inbox-list">
          <IonListHeader>Gestión</IonListHeader>
          <IonNote>admin@facturacion.com</IonNote>

          {/* Rutas de navegación principal */}
          {appPages.map((appPage, index) => (
            <IonMenuToggle key={index} autoHide={false}>
              <IonItem
                className={location.pathname === appPage.url ? "selected" : ""}
                routerLink={appPage.url}
                routerDirection="none"
                lines="none"
                detail={false}
              >
                <IonIcon
                  aria-hidden="true"
                  slot="start"
                  ios={appPage.iosIcon}
                  md={appPage.mdIcon}
                />
                <IonLabel>{appPage.title}</IonLabel>
              </IonItem>
            </IonMenuToggle>
          ))}
        </IonList>

        <IonList id="actions-list">
          <IonListHeader>Acciones</IonListHeader>
          {/* Ejemplo: Botón de Login o Logout */}
          <IonMenuToggle autoHide={false}>
            <IonItem
              routerLink="/login"
              routerDirection="none"
              lines="none"
              detail={false}
            >
              <IonIcon
                aria-hidden="true"
                slot="start"
                ios={logInOutline}
                md={logInOutline}
              />
              <IonLabel>Iniciar Sesión</IonLabel>
            </IonItem>
          </IonMenuToggle>
        </IonList>
      </IonContent>
    </IonMenu>
  );
};

export default Menu;
