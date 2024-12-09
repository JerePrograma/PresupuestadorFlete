// src/components/ui/Button.tsx
import { ButtonHTMLAttributes, FC } from "react";

interface ButtonProps extends ButtonHTMLAttributes<HTMLButtonElement> {
  label: string;
}

const Button: FC<ButtonProps> = ({ label, ...props }) => {
  return <button {...props}>{label}</button>;
};

export default Button;
