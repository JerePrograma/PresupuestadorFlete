package ar.com.envios.application.service;

import ar.com.envios.application.dto.PresupuestoRequest;
import ar.com.envios.application.dto.PresupuestoResponse;
import ar.com.envios.application.usecase.GenerarPresupuestoUseCase;
import ar.com.envios.domain.model.Extra;
import ar.com.envios.domain.model.Presupuesto;
import ar.com.envios.domain.model.Usuario;
import ar.com.envios.domain.repository.IPresupuestoRepository;
import ar.com.envios.domain.service.CalculadorPresupuestoService;
import ar.com.envios.infrastructure.entity.PresupuestoEntity;
import ar.com.envios.application.mapper.PresupuestoMapper;
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
    private final UsuarioService usuarioService;

    @Autowired
    public PresupuestoService(
            IPresupuestoRepository presupuestoRepository,
            CalculadorPresupuestoService calculadorPresupuestoService,
            GenerarPresupuestoUseCase generarPresupuestoUseCase,
            UsuarioService usuarioService) {

        this.presupuestoRepository = presupuestoRepository;
        this.calculadorPresupuestoService = calculadorPresupuestoService;
        this.generarPresupuestoUseCase = generarPresupuestoUseCase;
        this.usuarioService = usuarioService;
    }

    public PresupuestoResponse crearPresupuesto(PresupuestoRequest request) {
        // Obtener la lista de usuarios desde sus IDs
        List<Usuario> usuariosInvolucrados = request.getUsuariosInvolucrados().stream()
                .map(usuarioService::obtenerUsuarioPorId)
                .collect(Collectors.toList());

        // Generar el presupuesto
        Presupuesto presupuesto = generarPresupuestoUseCase.ejecutar(
                request.getOrigen(),
                request.getDestino(),
                request.getVolumenCarga(),
                request.getPesoCarga(),
                request.getNombreTipoVehiculo(),
                usuariosInvolucrados
        );

// Calcular costo
        BigDecimal costoTotal = calculadorPresupuestoService.calcular(presupuesto);
        presupuesto.agregarExtra(new Extra("Costo Total", costoTotal));

// Mapear a entidad y persistir
        PresupuestoEntity entity = PresupuestoMapper.toEntity(presupuesto);
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
