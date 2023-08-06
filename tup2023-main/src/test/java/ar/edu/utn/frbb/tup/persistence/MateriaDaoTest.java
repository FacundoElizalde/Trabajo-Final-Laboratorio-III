package ar.edu.utn.frbb.tup.persistence;

import ar.edu.utn.frbb.tup.model.Materia;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals("Programación I", materiaGuardada.getNombre());
        assertEquals(1, materiaGuardada.getAnio());
        assertEquals(1, materiaGuardada.getCuatrimestre());
    }

    @Test
    public void testFindMateriaById() {
        Materia materia1 = new Materia("Programación I", 1, 1);
        Materia materia2 = new Materia("Matemática I", 1, 2);

        materiaDao.save(materia1);
        materiaDao.save(materia2);

        Materia materiaEncontrada = materiaDao.findById(1);

        Assertions.assertNotNull(materiaEncontrada);
        assertEquals("Programación I", materiaEncontrada.getNombre());
    }

    @Test
    public void testGetMateriasPorNombre() {
        Materia materia1 = new Materia();
        materia1.setMateriaId(1);
        materia1.setNombre("Matemáticas");

        Materia materia2 = new Materia();
        materia2.setMateriaId(2);
        materia2.setNombre("Historia");

        Materia materia3 = new Materia();
        materia3.setMateriaId(3);
        materia3.setNombre("Informática");

        // Guardar las materias
        materiaDao.save(materia1);
        materiaDao.save(materia2);
        materiaDao.save(materia3);

        List<Materia> materiasPorNombre = materiaDao.getMateriasPorNombre("Matemáticas");
        Assertions.assertNotNull(materiasPorNombre);
        assertEquals(1, materiasPorNombre.size());
        assertEquals("Matemáticas", materiasPorNombre.get(0).getNombre());

        // Prueba cuando no se encuentra ninguna materia con el nombre especificado
        List<Materia> materiasPorNombreNoEncontradas = materiaDao.getMateriasPorNombre("Inexistente");
        Assertions.assertNotNull(materiasPorNombreNoEncontradas);
        assertEquals(0, materiasPorNombreNoEncontradas.size());
    }

    @Test
    public void testGetMateriasOrdenadas() {
        Materia materia1 = new Materia();
        materia1.setMateriaId(1);
        materia1.setNombre("Matemáticas");

        Materia materia2 = new Materia();
        materia2.setMateriaId(2);
        materia2.setNombre("Historia");

        Materia materia3 = new Materia();
        materia3.setMateriaId(3);
        materia3.setNombre("Informática");

        materiaDao.save(materia1);
        materiaDao.save(materia2);
        materiaDao.save(materia3);

        List<Materia> materiasAscendentes = materiaDao.getMateriasOrdenadas("asc");
        assertEquals(3, materiasAscendentes.size());
        assertEquals("Historia", materiasAscendentes.get(0).getNombre());
        assertEquals("Informática", materiasAscendentes.get(1).getNombre());
        assertEquals("Matemáticas", materiasAscendentes.get(2).getNombre());

        List<Materia> materiasDescendentes = materiaDao.getMateriasOrdenadas("desc");
        assertEquals(3, materiasDescendentes.size());
        assertEquals("Matemáticas", materiasDescendentes.get(0).getNombre());
        assertEquals("Informática", materiasDescendentes.get(1).getNombre());
        assertEquals("Historia", materiasDescendentes.get(2).getNombre());
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
