package ar.edu.utn.frbb.tup.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;

public class MateriaTest {

    @Test
    public void testMateriaConstructor() {
        String nombre = "Matemáticas";
        int anio = 3;
        int cuatrimestre = 2;

        Materia materia = new Materia(nombre, anio, cuatrimestre);

        Assertions.assertEquals(nombre, materia.getNombre());
        Assertions.assertEquals(anio, materia.getAnio());
        Assertions.assertEquals(cuatrimestre, materia.getCuatrimestre());
        Assertions.assertNotNull(materia.getCorrelatividades());
        Assertions.assertTrue(materia.getCorrelatividades().isEmpty());
    }

    @Test
    public void testAgregarCorrelatividad() {
        Materia materia1 = new Materia("Laboratorio en Computacion I", 1, 1);
        Materia materia2 = new Materia("Laboratorio en Computacion III", 2, 1);

        materia1.agregarCorrelatividad(materia2);

        List<Materia> correlatividades = materia1.getCorrelatividades();
        Assertions.assertEquals(1, correlatividades.size());
        Assertions.assertTrue(correlatividades.contains(materia2));
    }

    @Test
    public void testAgregarCorrelatividadMultiple() {
        Materia materia1 = new Materia("Laboratorio en Computacion I", 3, 2);
        Materia materia2 = new Materia("Laboratorio en Computacion II", 2, 1);
        Materia materia3 = new Materia("Laboratorio en Computacion III", 2, 1);

        materia1.agregarCorrelatividad(materia2);
        materia1.agregarCorrelatividad(materia3);

        List<Materia> correlatividades = materia1.getCorrelatividades();
        Assertions.assertEquals(2, correlatividades.size());
        Assertions.assertTrue(correlatividades.contains(materia2));
        Assertions.assertTrue(correlatividades.contains(materia3));
    }
}

