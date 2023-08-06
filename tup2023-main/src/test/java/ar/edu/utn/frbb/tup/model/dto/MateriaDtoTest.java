package ar.edu.utn.frbb.tup.model.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MateriaDtoTest {

    @Test
    public void testSetNombre() {
        MateriaDto materiaDto = new MateriaDto();
        materiaDto.setNombre("Matemáticas");
        Assertions.assertEquals("Matemáticas", materiaDto.getNombre());
    }

    @Test
    public void testSetAnio() {
        MateriaDto materiaDto = new MateriaDto();
        materiaDto.setAnio(2023);
        Assertions.assertEquals(2023, materiaDto.getAnio());
    }

    @Test
    public void testSetCuatrimestre() {
        MateriaDto materiaDto = new MateriaDto();
        materiaDto.setCuatrimestre(2);
        Assertions.assertEquals(2, materiaDto.getCuatrimestre());
    }
}
