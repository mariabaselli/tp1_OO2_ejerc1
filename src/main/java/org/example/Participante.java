package org.example;

import java.time.LocalDateTime;
import java.util.Objects;

public class Participante {
    private LocalDateTime fechaInscripcion;
    private int puntosObtenidos;
    private String DNI;
    private String nombre;

    public Participante (String DNI, String nombre) {
        this.fechaInscripcion=LocalDateTime.now();
        this.DNI = DNI;
        this.nombre = nombre;
    }

    public static Participante at (String DNI, String nombre) {
        assertValidarDNI(DNI);
        assertNombreEsValido(nombre);
        return new Participante(DNI, nombre);
    }

    private static void assertValidarDNI(String DNI) {
        if (DNI.isBlank() || DNI==null) {
            throw new IllegalArgumentException("El DNI no es correcto");
        }
    }

    private static void assertNombreEsValido(String nombre) {
        if (nombre.isBlank() || nombre==null) {
            throw new IllegalArgumentException("El nombre no es correcto");
        }
    }

    LocalDateTime obtenerFechaInscripcion() {
        return this.fechaInscripcion;
    }

    void sumarPuntos(int puntos) {
        this.puntosObtenidos += puntos;
    }

    public int obtenerPuntos() {
        return this.puntosObtenidos;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Participante that = (Participante) o;
        return DNI == that.DNI;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(DNI);
    }



}
