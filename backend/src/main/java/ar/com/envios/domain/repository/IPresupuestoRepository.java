package ar.com.envios.domain.repository;

import ar.com.envios.domain.model.Presupuesto;
import ar.com.envios.infrastructure.entity.PresupuestoEntity;

import java.util.List;

public interface IPresupuestoRepository {
    void guardar(PresupuestoEntity presupuesto);

    List<Presupuesto> obtenerTodos();
}