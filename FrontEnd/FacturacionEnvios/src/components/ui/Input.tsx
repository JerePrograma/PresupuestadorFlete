// src/components/ui/Input.tsx
import { InputHTMLAttributes, FC } from "react";

type InputProps = InputHTMLAttributes<HTMLInputElement>;

const Input: FC<InputProps> = (props) => {
  return <input {...props} />;
};

export default Input;
