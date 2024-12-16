// src/components/ui/Button.tsx
import { IonButton } from "@ionic/react";
import { FC } from "react";

interface ButtonProps {
  label: string;
  onClick?: () => void;
}

const Button: FC<ButtonProps> = ({ label, onClick }) => {
  return <IonButton onClick={onClick}>{label}</IonButton>;
};

export default Button;
