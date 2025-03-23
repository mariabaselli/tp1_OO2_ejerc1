package org.example;

import java.util.Objects;

public class Participante {

    //------------------------------- ATRIBUTOS -------------------------------
    private int puntosObtenidos;
    private String DNI;
    private String nombre;

    static String ERROR_DNI_PARTICIPANTE = "El DNI del participante no es correcto";
    static String ERROR_NOMBRE_PARTICIPANTE = "El nombre del participante no es correcto";


    //-------------------------------- MÉTODOS --------------------------------
    static Participante nuevoParticipante(String DNI, String nombre) {
        assertValidarDNI(DNI);
        assertNombreEsValido(nombre);
        return new Participante(DNI, nombre);
    }

    private static void assertValidarDNI(String DNI) {
        if (DNI.isBlank() || DNI==null) {
            throw new IllegalArgumentException(ERROR_DNI_PARTICIPANTE);
        }
    }

    private static void assertNombreEsValido(String nombre) {
        if (nombre.isBlank() || nombre==null) {
            throw new IllegalArgumentException(ERROR_NOMBRE_PARTICIPANTE);
        }
    }

    void sumarPuntos(int puntos) {
        this.puntosObtenidos += puntos;
    }


    int obtenerPuntos() {
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

    //----------------------------- CONSTRUCTORES -----------------------------
    private Participante (String DNI, String nombre) {
        this.DNI = DNI;
        this.nombre = nombre;
    }
}
