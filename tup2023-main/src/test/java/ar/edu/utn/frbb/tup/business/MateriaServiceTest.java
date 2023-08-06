package ar.edu.utn.frbb.tup.business;

import ar.edu.utn.frbb.tup.business.impl.MateriaServiceImpl;
import ar.edu.utn.frbb.tup.model.Materia;
import ar.edu.utn.frbb.tup.model.dto.MateriaDto;
import ar.edu.utn.frbb.tup.persistence.MateriaDao;
import ar.edu.utn.frbb.tup.persistence.exception.MateriaNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MateriaServiceTest {

    private MateriaService materiaService;
    private MateriaDao materiaDao;

    @BeforeEach
    public void setUp() {
        materiaDao = mock(MateriaDao.class);
        materiaService = new MateriaServiceImpl(materiaDao);
    }

    @Test
    public void testCrearMateria() {
        MateriaDto inputData = new MateriaDto();
        inputData.setNombre("Legislacion");
        inputData.setAnio(1);
        inputData.setCuatrimestre(1);

        Materia materiaCreada = new Materia(inputData.getNombre(), inputData.getAnio(), inputData.getCuatrimestre());
        when(materiaDao.save(any(Materia.class))).thenReturn(materiaCreada);

        Materia materia = materiaService.crearMateria(inputData);

        assertNotNull(materia);
        assertEquals(inputData.getNombre(), materia.getNombre());
        assertEquals(inputData.getAnio(), materia.getAnio());
        assertEquals(inputData.getCuatrimestre(), materia.getCuatrimestre());
    }

    @Test
    public void testGetMateriaById() throws MateriaNotFoundException {
        int idMateria = 1;
        Materia materia = new Materia("Legislacion", 1, 1);
        when(materiaDao.findById(idMateria)).thenReturn(materia);

        Materia result = materiaService.getMateriaById(idMateria);

        assertNotNull(result);
        assertEquals(materia, result);
    }

    @Test
    public void testGetMateriaByIdNotFound() {
        int idMateria = 1;
        when(materiaDao.findById(idMateria)).thenReturn(null);

        assertThrows(MateriaNotFoundException.class, () -> materiaService.getMateriaById(idMateria));
    }

    @Test
    public void testGetMateriasPorNombre() {
        String nombre = "Legislacion";
        List<Materia> materias = new ArrayList<>();
        materias.add(new Materia(nombre, 1, 1));
        materias.add(new Materia(nombre, 2, 2));
        when(materiaDao.getMateriasPorNombre(nombre)).thenReturn(materias);

        List<Materia> result = materiaService.getMateriasPorNombre(nombre);

        assertNotNull(result);
        assertEquals(materias, result);
    }

    @Test
    public void testGetMateriasOrdenadas() {
        String order = "nombre_asc";
        List<Materia> materias = new ArrayList<>();
        materias.add(new Materia("Laboratorio en Computacion I", 1, 1));
        materias.add(new Materia("Legislacion", 2, 2));
        when(materiaDao.getMateriasOrdenadas(order)).thenReturn(materias);

        List<Materia> result = materiaService.getMateriasOrdenadas(order);

        assertNotNull(result);
        assertEquals(materias, result);
    }

    @Test
    public void testModificarMateria() throws MateriaNotFoundException {
        int idMateria = 1;
        Materia materiaExistente = new Materia("Laboratorio en Computacion I", 1, 1);
        Materia materiaModificada = new Materia("Laboratorio en Computacion IV", 2, 2);

        when(materiaDao.findById(idMateria)).thenReturn(materiaExistente);
        when(materiaDao.save(any(Materia.class))).thenReturn(materiaModificada);

        Materia result = materiaService.modificarMateria(idMateria, materiaModificada);

        assertNotNull(result);
        assertEquals(materiaModificada.getNombre(), result.getNombre());
        assertEquals(materiaModificada.getAnio(), result.getAnio());
        assertEquals(materiaModificada.getCuatrimestre(), result.getCuatrimestre());
    }

    @Test
    public void testModificarMateriaNotFound() {
        int idMateria = 1;
        Materia materiaModificada = new Materia("Física", 2, 2);

        when(materiaDao.findById(idMateria)).thenReturn(null);

        assertThrows(MateriaNotFoundException.class, () -> materiaService.modificarMateria(idMateria, materiaModificada));
    }

    @Test
    public void testDeleteMateria() throws MateriaNotFoundException {
        int idMateria = 1;
        Materia materia = new Materia("Matemática", 1, 1);

        when(materiaDao.findById(idMateria)).thenReturn(materia);

        Materia result = materiaService.deleteMateria(idMateria);

        assertNotNull(result);
        assertEquals(materia, result);
    }

    @Test
    public void testDeleteMateriaNotFound() {
        int idMateria = 1;

        when(materiaDao.findById(idMateria)).thenReturn(null);

        assertThrows(MateriaNotFoundException.class, () -> materiaService.deleteMateria(idMateria));
    }
}
