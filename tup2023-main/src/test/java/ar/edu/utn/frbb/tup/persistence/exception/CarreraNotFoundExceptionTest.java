package ar.edu.utn.frbb.tup.persistence.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarreraNotFoundExceptionTest {

    @Test
    public void testConstructorWithMessage() {
        String message = "Carrera no encontrada";
        CarreraNotFoundException exception = new CarreraNotFoundException(message);
        assertEquals(message, exception.getMessage());
    }
}