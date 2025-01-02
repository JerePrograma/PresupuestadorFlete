import React, { useRef, useCallback, useEffect } from "react";
import { Autocomplete } from "@react-google-maps/api";
import { IonItem, IonLabel, IonInput } from "@ionic/react";
import { debounce } from "lodash";

interface AutocompleteInputProps {
  label: string;
  onPlaceChanged: (place: google.maps.places.PlaceResult | null) => void;
  debounceTime?: number;
}

const AutocompleteInput: React.FC<AutocompleteInputProps> = ({
  label,
  onPlaceChanged,
  debounceTime = 300,
}) => {
  const autocompleteRef = useRef<google.maps.places.Autocomplete | null>(null);

  // Create a memoized version of the debounced callback
  const debouncedPlaceChanged = useCallback(
    debounce((place: google.maps.places.PlaceResult | null) => {
      onPlaceChanged(place);
    }, debounceTime),
    [onPlaceChanged, debounceTime]
  );

  // Cleanup debounced function on unmount
  useEffect(() => {
    return () => {
      debouncedPlaceChanged.cancel();
    };
  }, [debouncedPlaceChanged]);

  const handleLoad = useCallback((autocomplete: google.maps.places.Autocomplete) => {
    autocomplete.setTypes(["geocode"]); // Solo ubicaciones geográficas
    autocomplete.setFields(["formatted_address", "geometry", "name"]); // Define los campos necesarios
    autocompleteRef.current = autocomplete;
  }, []);

  const handlePlaceChanged = useCallback(() => {
    if (autocompleteRef.current) {
      const place = autocompleteRef.current.getPlace();
      debouncedPlaceChanged(place);
    }
  }, [debouncedPlaceChanged]);

  return (
    <IonItem placeholder="Ingrese una dirección" onPointerEnterCapture={() => {}} onPointerLeaveCapture={() => {}}>
      <IonLabel position="floating" placeholder="Ingrese una dirección" onPointerEnterCapture={() => {}} onPointerLeaveCapture={() => {}}>
        {label}
      </IonLabel>
      <Autocomplete onLoad={handleLoad} onPlaceChanged={handlePlaceChanged}>
        <IonInput 
          placeholder="Ingrese una dirección" 
          onPointerEnterCapture={() => {}} 
          onPointerLeaveCapture={() => {}} 
        />
      </Autocomplete>
    </IonItem>
  );
};

export default AutocompleteInput;