package ar.edu.utn.frbb.tup.persistence;

import ar.edu.utn.frbb.tup.model.Alumno;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AlumnoDaoMemoryImplTest {

    private AlumnoDao alumnoDao;

    @BeforeEach
    public void setUp() {
        alumnoDao = new AlumnoDaoMemoryImpl();
    }

    @Test
    public void testSaveAlumno() {
        Alumno alumno = new Alumno();
        alumno.setNombre("Juan");
        alumno.setApellido("Perez");
        alumno.setDni(12345678L);

        alumnoDao.saveAlumno(alumno);

        Alumno savedAlumno = alumnoDao.loadAlumno(12345678L);

        assertNotNull(savedAlumno);
        assertEquals(alumno.getNombre(), savedAlumno.getNombre());
        assertEquals(alumno.getApellido(), savedAlumno.getApellido());
        assertEquals(alumno.getDni(), savedAlumno.getDni());
    }

    @Test
    public void testFindAlumno() {
        Alumno alumno1 = new Alumno();
        alumno1.setNombre("Juan");
        alumno1.setApellido("Perez");
        alumno1.setDni(12345678L);

        Alumno alumno2 = new Alumno();
        alumno2.setNombre("Maria");
        alumno2.setApellido("Gomez");
        alumno2.setDni(87654321L);

        alumnoDao.saveAlumno(alumno1);
        alumnoDao.saveAlumno(alumno2);

        Alumno foundAlumno = alumnoDao.findAlumno("Gomez");

        assertNotNull(foundAlumno);
        assertEquals(alumno2.getNombre(), foundAlumno.getNombre());
        assertEquals(alumno2.getApellido(), foundAlumno.getApellido());
        assertEquals(alumno2.getDni(), foundAlumno.getDni());
    }

    @Test
    public void testDeleteAlumno() {
        Alumno alumno = new Alumno();
        alumno.setNombre("Juan");
        alumno.setApellido("Perez");
        alumno.setDni(12345678L);

        alumnoDao.saveAlumno(alumno);

        alumnoDao.deleteAlumno(12345678L);

        Alumno deletedAlumno = alumnoDao.loadAlumno(12345678L);

        assertNull(deletedAlumno);
    }
}
