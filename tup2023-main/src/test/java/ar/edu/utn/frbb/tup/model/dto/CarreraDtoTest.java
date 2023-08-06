package ar.edu.utn.frbb.tup.model.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CarreraDtoTest {

    @Test
    public void testSetNombre() {
        CarreraDto carreraDto = new CarreraDto();

        carreraDto.setNombre("Ingeniería en Sistemas");

        Assertions.assertEquals("Ingeniería en Sistemas", carreraDto.getNombre());
    }

    @Test
    public void testSetCodigo() {
        CarreraDto carreraDto = new CarreraDto();

        carreraDto.setCodigo("IS34");

        Assertions.assertEquals("IS34", carreraDto.getCodigo());
    }

    @Test
    public void testSetDepartamento() {
        CarreraDto carreraDto = new CarreraDto();

        carreraDto.setDepartamento(2);

        Assertions.assertEquals(2, carreraDto.getDepartamento());
    }

    @Test
    public void testSetCantidadCuatrimestres() {
        CarreraDto carreraDto = new CarreraDto();

        carreraDto.setCantidadCuatrimestres(8);

        Assertions.assertEquals(8, carreraDto.getCantidadCuatrimestres());
    }
}

