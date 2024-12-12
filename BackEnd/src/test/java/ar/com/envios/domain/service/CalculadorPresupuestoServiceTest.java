package ar.com.envios.domain.service;

import ar.com.envios.domain.model.Extra;
import ar.com.envios.domain.model.Presupuesto;
import ar.com.envios.domain.model.TipoVehiculo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class CalculadorPresupuestoServiceTest {

    @Test
    void testCalcularPresupuestoSinExtras() {
        TipoVehiculo tipoVehiculo = new TipoVehiculo(
                "Camioneta",
                1000.0, // Capacidad de volumen
                1000.0, // Capacidad de peso
                new BigDecimal("2.0") // Consumo por km
        );
        Presupuesto presupuesto = new Presupuesto(
                "Buenos Aires",
                "La Plata",
                500.0, // Volumen de carga
                10.0,  // Peso de carga
                tipoVehiculo,
                new BigDecimal("2.0") // Consumo por km
        );

        CalculadorPresupuestoService service = new CalculadorPresupuestoService();
        BigDecimal total = service.calcular(presupuesto);

        // Costo base: 2.0 * 100 km = 200.0, sin extras
        Assertions.assertEquals(0, total.compareTo(BigDecimal.valueOf(200.00)));
    }

    @Test
    void testCalcularPresupuestoConExtras() {
        TipoVehiculo tipoVehiculo = new TipoVehiculo(
                "Camioneta",
                1000.0, // Capacidad de volumen
                1000.0, // Capacidad de peso
                new BigDecimal("2.0") // Consumo por km
        );
        Presupuesto presupuesto = new Presupuesto(
                "Buenos Aires",
                "La Plata",
                500.0, // Volumen de carga
                10.0,  // Peso de carga
                tipoVehiculo,
                new BigDecimal("2.0") // Consumo por km
        );

        presupuesto.agregarExtra(new Extra("Seguro adicional", BigDecimal.valueOf(50.0)));
        presupuesto.agregarExtra(new Extra("Embalaje especial", BigDecimal.valueOf(25.0)));

        CalculadorPresupuestoService service = new CalculadorPresupuestoService();
        BigDecimal total = service.calcular(presupuesto);

        // Costo base: 2.0 * 100 = 200.0 + 50.0 + 25.0 = 275.0 total
        Assertions.assertEquals(0, total.compareTo(BigDecimal.valueOf(275.0)));
    }

    @Test
    void testVehiculoNoSoportaVolumen() {
        TipoVehiculo tipoVehiculo = new TipoVehiculo(
                "Moto",
                100.0, // Capacidad de volumen (insuficiente para este test)
                100.0, // Capacidad de peso
                new BigDecimal("1.0") // Consumo por km
        );
        Presupuesto presupuesto = new Presupuesto(
                "Buenos Aires",
                "La Plata",
                500.0, // Volumen de carga (superior a la capacidad)
                10.0,  // Peso de carga
                tipoVehiculo,
                new BigDecimal("1.0") // Consumo por km
        );

        CalculadorPresupuestoService service = new CalculadorPresupuestoService();
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.calcular(presupuesto));
    }
}
