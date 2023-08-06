package ar.edu.utn.frbb.tup.persistence.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MateriaNotFoundExceptionTest {

    @Test
    public void testConstructor() {
        String message = "Materia no encontrada";
        MateriaNotFoundException exception = new MateriaNotFoundException(message);
        assertEquals(message, exception.getMessage());
    }
}
