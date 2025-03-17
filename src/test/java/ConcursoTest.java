import org.example.Concurso;
import org.example.Participante;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class ConcursoTest {
    @Test
    public void inscribirAlConcurso() {
        Participante jose = new Participante("234566", "Jose Perez");
        Concurso unConcurso = new Concurso("Un Concurso", LocalDateTime.now().minusDays(2), LocalDateTime.now().plusDays(60));
        unConcurso.inscribir(jose);
        assertEquals(1, unConcurso.cantidadInscriptos());

    }

    @Test
    public void inscribirElPrimerDia() {
        Participante jose = new Participante("234566", "Jose Perez");
        Concurso unConcurso = new Concurso("Un Concurso", LocalDateTime.now(), LocalDateTime.now().plusDays(60));
        unConcurso.inscribir(jose);
        assertTrue(unConcurso.inscripcionPrimerDia(jose));
    }


    @Test
    public void inscribirConFechaFueraDeRango() {
        Participante jose = new Participante("234566", "Jose Perez");
        Concurso unConcurso = new Concurso("Un Concurso", LocalDateTime.now().plusDays(3), LocalDateTime.now().plusDays(60));
        assertThrows(RuntimeException.class, () -> unConcurso.inscribir(jose));
    }

    @Test
    public void verificarSumaPuntosObtenidos () {
        Participante jose = new Participante("234566", "Jose Perez");
        Concurso unConcurso = new Concurso("Un Concurso", LocalDateTime.now(), LocalDateTime.now().plusDays(60));
        unConcurso.inscribir(jose);
        assertEquals(10, jose.obtenerPuntos());
    }

    @Test
    public void inscribirYaInscripto() {
        Participante jose = new Participante("234566", "Jose Perez");
        Concurso unConcurso = new Concurso("Un Concurso", LocalDateTime.now(), LocalDateTime.now().plusDays(60));
        unConcurso.inscribir(jose);
        assertThrows(RuntimeException.class, () -> unConcurso.inscribir(jose));
    }

    @Test
    public void verificarFechasConcurso() {
        assertThrows(IllegalArgumentException.class, () -> Concurso.at("Un Concurso", LocalDateTime.now().plusDays(60), LocalDateTime.now()));
    }

    @Test
    public void verificarNombreConcurso() {
        assertThrows(IllegalArgumentException.class, () -> Concurso.at("", LocalDateTime.now(), LocalDateTime.now().plusDays(60)));
    }

    @Test
    public void verificarDNIParticipante() {
        assertThrows(IllegalArgumentException.class, () -> Participante.at("", "Ramon"));
    }

    @Test
    public void verificarNombreParticipante() {
        assertThrows(IllegalArgumentException.class, () -> Participante.at("35896451", ""));
    }

  }