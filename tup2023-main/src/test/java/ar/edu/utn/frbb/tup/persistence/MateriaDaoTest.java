package ar.edu.utn.frbb.tup.persistence;

import ar.edu.utn.frbb.tup.model.Materia;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MateriaDaoTest {

    private MateriaDao materiaDao;

    @BeforeEach
    public void setUp() {
        materiaDao = new MateriaDaoMemoryImpl();
    }

    @Test
    public void testSaveMateria() {
        Materia materia = new Materia("Programación I", 1, 1);
        Materia materiaGuardada = materiaDao.save(materia);

        Assertions.assertNotNull(materiaGuardada);
        Assertions.assertEquals("Programación I", materiaGuardada.getNombre());
        Assertions.assertEquals(1, materiaGuardada.getAnio());
        Assertions.assertEquals(1, materiaGuardada.getCuatrimestre());
    }

    @Test
    public void testFindMateriaById() {
        Materia materia1 = new Materia("Programación I", 1, 1);
        Materia materia2 = new Materia("Matemática I", 1, 2);

        materiaDao.save(materia1);
        materiaDao.save(materia2);

        Materia materiaEncontrada = materiaDao.findById(1);

        Assertions.assertNotNull(materiaEncontrada);
        Assertions.assertEquals("Programación I", materiaEncontrada.getNombre());
    }

    @Test
    public void testGetMateriasPorNombre() {
        Materia materia1 = new Materia("Programación I", 1, 1);
        Materia materia2 = new Materia("Matemática I", 1, 2);
        Materia materia3 = new Materia("Programación II", 2, 1);

        materiaDao.save(materia1);
        materiaDao.save(materia2);
        materiaDao.save(materia3);

        List<Materia> materiasPorNombre = materiaDao.getMateriasPorNombre("Programación");

        Assertions.assertEquals(2, materiasPorNombre.size());
        Assertions.assertTrue(materiasPorNombre.contains(materia1));
        Assertions.assertTrue(materiasPorNombre.contains(materia3));
    }

    @Test
    public void testGetMateriasOrdenadas() {
        Materia materia1 = new Materia("Programación I", 1, 1);
        Materia materia2 = new Materia("Matemática I", 1, 2);

        materiaDao.save(materia1);
        materiaDao.save(materia2);

        List<Materia> materiasAscendente = materiaDao.getMateriasOrdenadas("nombre_asc");
        List<Materia> materiasDescendente = materiaDao.getMateriasOrdenadas("nombre_desc");

        Assertions.assertEquals(2, materiasAscendente.size());
        Assertions.assertEquals(2, materiasDescendente.size());

        Assertions.assertEquals("Matemática I", materiasAscendente.get(0).getNombre());
        Assertions.assertEquals("Programación I", materiasAscendente.get(1).getNombre());

        Assertions.assertEquals("Programación I", materiasDescendente.get(0).getNombre());
        Assertions.assertEquals("Matemática I", materiasDescendente.get(1).getNombre());
    }

    @Test
    public void testDeleteMateria() {
        Materia materia = new Materia("Programación I", 1, 1);
        materiaDao.save(materia);

        materiaDao.delete(materia);
        Materia materiaEliminada = materiaDao.findById(1);

        Assertions.assertNull(materiaEliminada);
    }
}
