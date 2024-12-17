import React, { useState } from "react";
import { IonButton, IonInput, IonItem, IonList } from "@ionic/react";
import { crearUsuario } from "../../api/services/usuarioService";

const UsuarioForm: React.FC = () => {
  const [formData, setFormData] = useState({
    nombre: "",
    email: "",
    password: "",
    tipoUsuario: "",
  });

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      await crearUsuario(formData);
      console.log("Usuario creado:", formData);
    } catch (error) {
      console.error("Error al crear usuario:", error);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <IonList>
        {/* Nombre */}
        <IonItem>
          <IonInput
            name="nombre"
            placeholder="Nombre del Usuario"
            value={formData.nombre}
            onIonChange={handleChange}
            required
          />
        </IonItem>

        {/* Email */}
        <IonItem>
          <IonInput
            name="email"
            type="email"
            placeholder="Email"
            value={formData.email}
            onIonChange={handleChange}
            required
          />
        </IonItem>

        {/* Contraseña */}
        <IonItem>
          <IonInput
            name="password"
            type="password"
            placeholder="Contraseña"
            value={formData.password}
            onIonChange={handleChange}
            required
          />
        </IonItem>

        {/* Tipo de Usuario */}
        <IonItem>
          <IonInput
            name="tipoUsuario"
            placeholder="Tipo de Usuario (admin/cliente)"
            value={formData.tipoUsuario}
            onIonChange={handleChange}
            required
          />
        </IonItem>

        {/* Botón de Enviar */}
        <IonButton type="submit">Crear Usuario</IonButton>
      </IonList>
    </form>
  );
};

export default UsuarioForm;
