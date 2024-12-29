import React, { useState } from "react";
import {
  IonButton,
  IonInput,
  IonItem,
  IonLabel,
  IonList,
  IonToggle,
} from "@ionic/react";
import { crearUsuario } from "../../api/services/usuarioService";

const UsuarioForm: React.FC = () => {
  const [formData, setFormData] = useState<{
    nombre: string;
    email: string;
    password: string;
    tipoUsuario: string;
    disponible: boolean;
  }>({
    nombre: "",
    email: "",
    password: "",
    tipoUsuario: "",
    disponible: false,
  });

  // Maneja cambios de IonInput (texto)
  const handleInputChange = (e: any) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  // Maneja cambios de IonToggle
  const handleToggleChange = (e: CustomEvent) => {
    // IonToggle expone e.detail.checked (true/false)
    setFormData((prev) => ({ ...prev, disponible: e.detail.checked }));
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      await crearUsuario(formData);
      console.log("Usuario creado:", formData);

      // Limpia el formulario si quieres
      setFormData({
        nombre: "",
        email: "",
        password: "",
        tipoUsuario: "",
        disponible: false,
      });
    } catch (error) {
      console.error("Error al crear usuario:", error);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <IonList>
        {/* Nombre */}
        <IonItem>
          <IonLabel position="floating">Nombre</IonLabel>
          <IonInput
            name="nombre"
            placeholder="Nombre del Usuario"
            value={formData.nombre}
            onIonChange={handleInputChange}
            required
          />
        </IonItem>

        {/* Email */}
        <IonItem>
          <IonLabel position="floating">Email</IonLabel>
          <IonInput
            name="email"
            type="email"
            placeholder="Email"
            value={formData.email}
            onIonChange={handleInputChange}
            required
          />
        </IonItem>

        {/* Contraseña */}
        <IonItem>
          <IonLabel position="floating">Contraseña</IonLabel>
          <IonInput
            name="password"
            type="password"
            placeholder="Contraseña"
            value={formData.password}
            onIonChange={handleInputChange}
            required
          />
        </IonItem>

        {/* Tipo de Usuario */}
        <IonItem>
          <IonLabel position="floating">Tipo de Usuario</IonLabel>
          <IonInput
            name="tipoUsuario"
            placeholder='Ej: "CHOFER", "AYUDANTE", "ADMIN"...'
            value={formData.tipoUsuario}
            onIonChange={handleInputChange}
            required
          />
        </IonItem>

        {/* Botón de Enviar */}
        <IonButton type="submit" expand="full" style={{ marginTop: "1rem" }}>
          Crear Usuario
        </IonButton>
      </IonList>
    </form>
  );
};

export default UsuarioForm;