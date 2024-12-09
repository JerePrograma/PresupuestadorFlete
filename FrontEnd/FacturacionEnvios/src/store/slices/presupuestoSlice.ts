// src/store/slices/presupuestoSlice.ts
import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import { PresupuestoResponse } from '../../api/services/presupuestoService';

interface PresupuestoState {
  data: PresupuestoResponse[];
}

const initialState: PresupuestoState = {
  data: []
};

const presupuestoSlice = createSlice({
  name: 'presupuesto',
  initialState,
  reducers: {
    setPresupuestos: (state, action: PayloadAction<PresupuestoResponse[]>) => {
      state.data = action.payload;
    }
  }
});

export const { setPresupuestos } = presupuestoSlice.actions;
export default presupuestoSlice.reducer;
