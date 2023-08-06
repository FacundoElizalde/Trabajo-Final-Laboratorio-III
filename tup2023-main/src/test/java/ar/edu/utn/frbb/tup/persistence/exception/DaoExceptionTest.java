package ar.edu.utn.frbb.tup.persistence.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DaoExceptionTest {

    @Test
    public void testConstructorWithMessage() {
        String message = "Error en el DAO";
        DaoException exception = new DaoException(message);
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void testConstructorWithMessageAndCause() {
        String message = "Error en el DAO";
        Throwable cause = new RuntimeException("Causa del error");
        DaoException exception = new DaoException(message, cause);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}