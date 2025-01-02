import React from "react";
import { IonInput, IonItem, IonLabel } from "@ionic/react";

interface ControlledInputProps {
  label: string;
  name: string;
  type?: "text" | "number";
  value: string | number;
  onChange: (name: string, value: string | number) => void;
  required?: boolean;
  min?: number;
}

const ControlledInput: React.FC<ControlledInputProps> = ({
  label,
  name,
  type = "text",
  value,
  onChange,
  required = false,
  min,
}) => {
  const handleInputChange = (e: CustomEvent) => {
    const inputValue =
      type === "number" ? Number(e.detail.value) : e.detail.value;
    onChange(name, inputValue);
  };

  return (
    <IonItem placeholder={undefined} onPointerEnterCapture={undefined} onPointerLeaveCapture={undefined}>
      <IonLabel position="floating" placeholder={undefined} onPointerEnterCapture={undefined} onPointerLeaveCapture={undefined}>{label}</IonLabel>
      <IonInput
        name={name}
        type={type}
        value={value}
        onIonChange={handleInputChange}
        required={required}
        min={min} placeholder={undefined} onPointerEnterCapture={undefined} onPointerLeaveCapture={undefined}      />
    </IonItem>
  );
};

export default ControlledInput;
