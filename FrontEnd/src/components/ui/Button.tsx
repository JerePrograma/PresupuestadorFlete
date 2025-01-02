// src/components/ui/Button.tsx
import { IonButton } from "@ionic/react";
import { FC } from "react";

interface ButtonProps {
  label: string;
  onClick?: () => void;
}

const Button: FC<ButtonProps> = ({ label, onClick }) => {
  return <IonButton onClick={onClick} placeholder={undefined} onPointerEnterCapture={undefined} onPointerLeaveCapture={undefined}>{label}</IonButton>;
};

export default Button;
