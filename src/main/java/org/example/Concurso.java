package org.example;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Concurso {
    private ArrayList<Participante> listaInscriptos;
    private String nombre;
    private LocalDateTime fechaInicioInscripcion;
    private LocalDateTime fechaFinInscripcion;

    public Concurso(String nombre, LocalDateTime fechaInicioInscripcion, LocalDateTime fechaFinInscripcion) {
        this.nombre = nombre;
        this.fechaInicioInscripcion = fechaInicioInscripcion;
        this.fechaFinInscripcion = fechaFinInscripcion;
        this.listaInscriptos = new ArrayList<>();
    }

    public static Concurso at(String nombre, LocalDateTime fechaInicioInscripcion, LocalDateTime fechaFinInscripcion) {
        assertNombreEsValido(nombre);
        assertFechaValida(fechaInicioInscripcion, fechaFinInscripcion);
        return new Concurso(nombre, fechaInicioInscripcion, fechaFinInscripcion);
    }

    private static void assertFechaValida(LocalDateTime fechaInicioInscripcion, LocalDateTime fechaFinInscripcion) {
        if (fechaInicioInscripcion.isAfter(fechaFinInscripcion) || fechaFinInscripcion.isBefore(fechaInicioInscripcion)) {
            throw new IllegalArgumentException("Las fechas del concurso no son correctas");
        }
    }


    private static void assertNombreEsValido(String nombre) {
        if (nombre.isBlank() || nombre==null) {
            throw new IllegalArgumentException("El nombre no es correcto");
        }
    }

    public void inscribir(Participante unParticipante) {
        if (estaInscripto(unParticipante) || !esValidaInscripcion(unParticipante.obtenerFechaInscripcion())) {
            throw new RuntimeException("No se puede inscribir al participante");
        }
        this.listaInscriptos.add(unParticipante);
        if (inscripcionPrimerDia(unParticipante)) {
           unParticipante.sumarPuntos(10);
       }

    }
    private boolean esValidaInscripcion(LocalDateTime unaFecha) {
        return (entreInicioFin(unaFecha) || esIgual(unaFecha));
    }

    private boolean entreInicioFin(LocalDateTime unaFecha) {
        return unaFecha.isAfter(fechaInicioInscripcion) && unaFecha.isBefore(fechaFinInscripcion);
    }

    private boolean esIgual(LocalDateTime unaFecha) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return unaFecha.format(formatter).equals(this.fechaInicioInscripcion.format(formatter));
    }

    public boolean estaInscripto(Participante unParticipante) {
        return listaInscriptos.contains(unParticipante);
    }

    public int cantidadInscriptos() {
        return this.listaInscriptos.size();
    }

    public boolean inscripcionPrimerDia(Participante unParticipante){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return (fechaInicioInscripcion.format(formatter).equals(unParticipante.obtenerFechaInscripcion().format(formatter)));
    }


   }



