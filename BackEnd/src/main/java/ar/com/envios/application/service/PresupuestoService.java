package ar.com.envios.application.service;

import ar.com.envios.application.dto.PresupuestoRequest;
import ar.com.envios.application.dto.PresupuestoResponse;
import ar.com.envios.application.mapper.PresupuestoMapper;
import ar.com.envios.application.mapper.UsuarioMapper;
import ar.com.envios.application.usecase.GenerarPresupuestoUseCase;
import ar.com.envios.domain.model.Extra;
import ar.com.envios.domain.model.Presupuesto;
import ar.com.envios.domain.model.Usuario;
import ar.com.envios.domain.repository.IPresupuestoRepository;
import ar.com.envios.domain.service.CalculadorPresupuestoService;
import ar.com.envios.infrastructure.entity.PresupuestoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PresupuestoService {

    private final IPresupuestoRepository presupuestoRepository;
    private final CalculadorPresupuestoService calculadorPresupuestoService;
    private final GenerarPresupuestoUseCase generarPresupuestoUseCase;

    @Autowired
    public PresupuestoService(
            IPresupuestoRepository presupuestoRepository,
            CalculadorPresupuestoService calculadorPresupuestoService, GenerarPresupuestoUseCase generarPresupuestoUseCase) {
        this.presupuestoRepository = presupuestoRepository;
        this.calculadorPresupuestoService = calculadorPresupuestoService;
        this.generarPresupuestoUseCase = generarPresupuestoUseCase;
    }

    public PresupuestoResponse crearPresupuesto(PresupuestoRequest request) {
        List<Usuario> usuariosInvolucrados = request.getUsuariosInvolucrados().stream()
                .map(UsuarioMapper::toUsuario)
                .collect(Collectors.toList());

        Presupuesto presupuesto = generarPresupuestoUseCase.ejecutar(
                request.getOrigen(),
                request.getDestino(),
                request.getVolumenCarga(),
                request.getPesoCarga(),
                request.getNombreTipoVehiculo(),
                usuariosInvolucrados
        );

        BigDecimal costoTotal = calculadorPresupuestoService.calcular(presupuesto);

        presupuesto.agregarExtra(new Extra("Costo Total", costoTotal)); // Agrega el costo calculado

        PresupuestoEntity entity = PresupuestoMapper.toEntity(presupuesto);
        // Persistir el presupuesto
        presupuestoRepository.guardar(entity);

        return new PresupuestoResponse(
                request.getOrigen(),
                request.getDestino(),
                request.getVolumenCarga(),
                request.getNombreTipoVehiculo(),
                costoTotal,
                List.of("Costo Total")
        );
    }
}
