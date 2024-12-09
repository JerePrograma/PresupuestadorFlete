// src/store/index.ts
import { configureStore } from '@reduxjs/toolkit';
import presupuestoReducer from './slices/presupuestoSlice';

export const store = configureStore({
  reducer: {
    presupuesto: presupuestoReducer,
  },
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
