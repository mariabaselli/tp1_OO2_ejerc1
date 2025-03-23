package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ConcursoTest {

    private Participante jose;
    private Concurso unConcurso;
    private LocalDate fechaInscripcionParticipante;
    private LocalDate fechaInicioConcurso;
    private LocalDate fechaFinConcurso;


    @BeforeEach
    public void inicializarVariables () {
        this.jose = Participante.nuevoParticipante("234566", "Jose Perez");
        this.fechaInicioConcurso= LocalDate.now();
        this.fechaFinConcurso = LocalDate.now().plusDays(60);
        this.unConcurso = Concurso.nuevoConcurso("01a", "Un Concurso", fechaInicioConcurso, fechaFinConcurso);
        this.fechaInscripcionParticipante = LocalDate.now().plusDays(5);

    }
    @Test
    public void inscribirAlConcurso() {
        unConcurso.inscribirAConFecha(jose, fechaInscripcionParticipante);
        assertEquals(1, unConcurso.cantidadInscriptos());
    }

    @Test
    public void VerificarInscripcionPrimerDia() {
        assertTrue(unConcurso.inscribirPrimerDia(fechaInicioConcurso));
    }

    @Test
    public void inscribirConFechaFueraDeRango() {
        LocalDate otraFechaInscripcion= fechaFinConcurso.plusDays(1);
        Exception exception = assertThrows(RuntimeException.class, () -> unConcurso.inscribirAConFecha(jose,otraFechaInscripcion));
        assertEquals(Concurso.ERROR_FECHA_INSCRIPCION, exception.getMessage());
    }

    @Test
    public void verificarParticipanteNoInscripto() {
        assertFalse(unConcurso.estaInscripto(jose));
    }

    @Test
    public void verificarSumaPuntosObtenidos () {
        unConcurso.inscribirAConFecha(jose,fechaInicioConcurso);
        assertEquals(10, jose.obtenerPuntos());
    }

    @Test
    public void inscribirYaInscripto() {
        unConcurso.inscribirAConFecha(jose, fechaInscripcionParticipante);
        Exception exception = assertThrows(RuntimeException.class, () -> unConcurso.inscribirAConFecha(jose, fechaInscripcionParticipante));
        assertEquals(1, unConcurso.cantidadInscriptos());
        assertEquals(Concurso.ERROR_PARTICIPANTE_YA_INSCRIPTO, exception.getMessage());
    }

    @Test
    public void verificarDNIParticipante() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> Participante.nuevoParticipante("", "Ramon"));
        assertEquals(Participante.ERROR_DNI_PARTICIPANTE,exception.getMessage());
    }

    @Test
    public void verificarNombreParticipante() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> Participante.nuevoParticipante("35896451", ""));
        assertEquals(Participante.ERROR_NOMBRE_PARTICIPANTE,exception.getMessage());
    }

  }