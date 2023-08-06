package ar.edu.utn.frbb.tup.model.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AlumnoDtoTest {

    @Test
    public void testGettersAndSetters() {
        AlumnoDto alumnoDto = new AlumnoDto();

        alumnoDto.setNombre("Facundo");
        alumnoDto.setApellido("Elizalde");
        alumnoDto.setDni(44883011);

        Assertions.assertEquals("Facundo", alumnoDto.getNombre());
        Assertions.assertEquals("Elizalde", alumnoDto.getApellido());
        Assertions.assertEquals(44883011, alumnoDto.getDni());
    }

    @Test
    public void testDefaultValues() {
        AlumnoDto alumnoDto = new AlumnoDto();

        Assertions.assertNull(alumnoDto.getNombre());
        Assertions.assertNull(alumnoDto.getApellido());
        Assertions.assertEquals(0, alumnoDto.getDni());
    }
}
