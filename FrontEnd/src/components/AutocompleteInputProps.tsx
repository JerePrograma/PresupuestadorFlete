import React, { useRef } from "react";
import { Autocomplete } from "@react-google-maps/api";
import { IonItem, IonLabel, IonInput } from "@ionic/react";

interface AutocompleteInputProps {
  label: string;
  onPlaceChanged: (place: google.maps.places.PlaceResult | null) => void;
}

const AutocompleteInput: React.FC<AutocompleteInputProps> = ({
  label,
  onPlaceChanged,
}) => {
  const autocompleteRef = useRef<google.maps.places.Autocomplete | null>(null);

  const handleLoad = (autocomplete: google.maps.places.Autocomplete) => {
    autocompleteRef.current = autocomplete;
  };

  const handlePlaceChanged = () => {
    if (autocompleteRef.current) {
      const place = autocompleteRef.current.getPlace();
      onPlaceChanged(place);
    }
  };

  return (
    <IonItem>
      <IonLabel position="floating">{label}</IonLabel>
      <Autocomplete onLoad={handleLoad} onPlaceChanged={handlePlaceChanged}>
        <IonInput />
      </Autocomplete>
    </IonItem>
  );
};

export default AutocompleteInput;
