package ar.edu.utn.frbb.tup.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CarreraTest {

    @Test
    public void testCarreraConstructor() {

        String nombre = "Ingeniería Informática";
        String codigo = "4567";
        int departamento = 1;
        int cantidadCuatrimestres = 10;

        String nombre2 = "Tecnicatura en Programacion";
        String codigo2 = "2345";
        int departamento2 = 1;
        int cantidadCuatrimestres2 = 4;

        Carrera carrera = new Carrera(nombre, codigo, departamento, cantidadCuatrimestres);
        Carrera carrera2 = new Carrera(nombre2, codigo2, departamento2, cantidadCuatrimestres2);

        Assertions.assertEquals(nombre, carrera.getNombre());
        Assertions.assertEquals(codigo, carrera.getCodigo());
        Assertions.assertEquals(departamento, carrera.getDepartamento());
        Assertions.assertEquals(cantidadCuatrimestres, carrera.getCantidadCuatrimestres());}
}