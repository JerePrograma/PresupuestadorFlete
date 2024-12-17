package ar.com.envios.domain.repository;

import ar.com.envios.domain.model.Vehiculo;

import java.util.List;

public interface IVehiculoRepository {
    void guardar(Vehiculo vehiculo);
    List<Vehiculo> listarTodos();
}
