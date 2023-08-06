package ar.edu.utn.frbb.tup.persistence;

import ar.edu.utn.frbb.tup.model.Alumno;
import ar.edu.utn.frbb.tup.persistence.exception.DaoException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AlumnoDaoTest {

    private AlumnoDao alumnoDao;

    @BeforeEach
    public void setUp() {
        alumnoDao = new AlumnoDaoMemoryImpl();
    }

    @Test
    public void testSaveAndLoadAlumno() {
        Alumno alumno = new Alumno("Juan", "Perez", 123456789L);

        alumnoDao.saveAlumno(alumno);
        Alumno alumnoCargado = alumnoDao.loadAlumno(123456789L);

        Assertions.assertNotNull(alumnoCargado);
        Assertions.assertEquals(alumno.getNombre(), alumnoCargado.getNombre());
        Assertions.assertEquals(alumno.getApellido(), alumnoCargado.getApellido());
        Assertions.assertEquals(alumno.getDni(), alumnoCargado.getDni());
    }

    @Test
    public void testFindAlumnoByApellido() throws DaoException {
        Alumno alumno1 = new Alumno("Juan", "Perez", 44883011);
        Alumno alumno2 = new Alumno("Ana", "Lopez", 45887012);

        alumnoDao.saveAlumno(alumno1);
        alumnoDao.saveAlumno(alumno2);
        Alumno alumnoEncontrado = alumnoDao.findAlumno("Lopez");

        Assertions.assertNotNull(alumnoEncontrado);
        Assertions.assertEquals("Ana", alumnoEncontrado.getNombre());
        Assertions.assertEquals("Lopez", alumnoEncontrado.getApellido());
        Assertions.assertEquals(45887012, alumnoEncontrado.getDni());
    }

    @Test
    public void testDeleteAlumno() {
        Alumno alumno = new Alumno("Juan", "Perez", 44883011L);
        alumnoDao.saveAlumno(alumno);

        Alumno alumnoEliminado = alumnoDao.deleteAlumno(44883011L);

        Assertions.assertNotNull(alumnoEliminado);
        Assertions.assertEquals("Juan", alumnoEliminado.getNombre());
        Assertions.assertEquals("Perez", alumnoEliminado.getApellido());
        Assertions.assertEquals(44883011L, alumnoEliminado.getDni());

        Alumno alumnoNoEncontrado = alumnoDao.loadAlumno(44883011L);
        Assertions.assertNull(alumnoNoEncontrado);
    }
}

