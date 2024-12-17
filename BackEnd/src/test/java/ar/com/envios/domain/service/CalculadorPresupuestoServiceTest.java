package ar.com.envios.domain.service;

import ar.com.envios.domain.enumeraciones.TipoUsuario;
import ar.com.envios.domain.model.Extra;
import ar.com.envios.domain.model.Usuario;
import ar.com.envios.domain.model.Presupuesto;
import ar.com.envios.domain.model.Vehiculo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.math.BigDecimal;
import java.util.List;

class CalculadorPresupuestoServiceTest {

    @Test
    void testCalcularPresupuestoSinExtras() {
        // Configuración
        Vehiculo vehiculo = new Vehiculo("Camioneta", 1000.0, 1000.0, new BigDecimal("2.0"));
        List<Usuario> usuariosInvolucrados = List.of(new Usuario(1L, "Chofer", "chofer@mail.com", "asd", TipoUsuario.CHOFER));
        Presupuesto presupuesto = new Presupuesto("Buenos Aires", "La Plata", 500.0, 10.0, vehiculo, usuariosInvolucrados);

        // Servicio
        CalculadorPresupuestoService service = new CalculadorPresupuestoService();
        BigDecimal total = service.calcular(presupuesto);

        // Validación
        Assertions.assertEquals(0, total.compareTo(new BigDecimal("200.00")));
    }

    @Test
    void testCalcularPresupuestoConExtras() {
        // Configuración
        Vehiculo vehiculo = new Vehiculo("Camioneta", 1000.0, 1000.0, new BigDecimal("2.0"));
        List<Usuario> usuariosInvolucrados = List.of(new Usuario(1L, "Chofer", "chofer@mail.com", "asd", TipoUsuario.CHOFER));
        Presupuesto presupuesto = new Presupuesto("Buenos Aires", "La Plata", 500.0, 10.0, vehiculo, usuariosInvolucrados);

        // Agrega extras
        presupuesto.agregarExtra(new Extra("Seguro adicional", new BigDecimal("50.0")));
        presupuesto.agregarExtra(new Extra("Embalaje especial", new BigDecimal("25.0")));

        // Mock del servicio que calcula distancia y obtiene precio de combustible
        CalculadorPresupuestoService service = Mockito.spy(new CalculadorPresupuestoService());

        // Simula la distancia entre Buenos Aires y La Plata
        Mockito.doReturn(100.0).when(service).obtenerDistanciaReal("Buenos Aires", "La Plata");

        // Simula el precio actual del combustible
        Mockito.doReturn(new BigDecimal("100.0")).when(service).obtenerPrecioCombustibleActual();

        // Calcula el total
        BigDecimal total = service.calcular(presupuesto);

        // Normalizar valores
        BigDecimal costoEsperado = new BigDecimal("20075.0").setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal totalNormalizado = total.setScale(2, BigDecimal.ROUND_HALF_UP);

        // Depuración
        System.out.println("Costo esperado: " + costoEsperado);
        System.out.println("Total calculado: " + totalNormalizado);

        // Validación
        Assertions.assertEquals(0, totalNormalizado.compareTo(costoEsperado), "El costo calculado no coincide con el esperado.");
    }


}
