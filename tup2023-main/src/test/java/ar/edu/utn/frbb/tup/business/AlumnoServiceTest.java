package ar.edu.utn.frbb.tup.business;

import ar.edu.utn.frbb.tup.business.impl.AlumnoServiceImpl;
import ar.edu.utn.frbb.tup.model.Alumno;
import ar.edu.utn.frbb.tup.model.dto.AlumnoDto;
import ar.edu.utn.frbb.tup.persistence.AlumnoDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AlumnoServiceTest {

    private AlumnoService alumnoService;
    private AlumnoDao alumnoDao;

    @BeforeEach
    public void setUp() {
        alumnoDao = mock(AlumnoDao.class);
        alumnoService = new AlumnoServiceImpl(alumnoDao);
    }

    @Test
    public void testCrearAlumno() {
        AlumnoDto alumnoDto = new AlumnoDto();
        alumnoDto.setNombre("Juan");
        alumnoDto.setApellido("Perez");
        alumnoDto.setDni(123456789L);

        Alumno alumnoEsperado = new Alumno("Juan", "Perez", 123456789L);
        when(alumnoDao.saveAlumno(any())).thenReturn(alumnoEsperado);

        Alumno alumnoCreado = alumnoService.crearAlumno(alumnoDto);

        assertNotNull(alumnoCreado);
        assertEquals(alumnoEsperado.getNombre(), alumnoCreado.getNombre());
        assertEquals(alumnoEsperado.getApellido(), alumnoCreado.getApellido());
        assertEquals(alumnoEsperado.getDni(), alumnoCreado.getDni());
    }

    @Test
    public void testBuscarAlumno() {
        String apellido = "Perez";
        Alumno alumnoEsperado = new Alumno("Juan", "Perez", 123456789L);
        when(alumnoDao.findAlumno(apellido)).thenReturn(alumnoEsperado);

        Alumno alumnoEncontrado = alumnoService.buscarAlumno(apellido);

        assertNotNull(alumnoEncontrado);
        assertEquals(alumnoEsperado.getNombre(), alumnoEncontrado.getNombre());
        assertEquals(alumnoEsperado.getApellido(), alumnoEncontrado.getApellido());
        assertEquals(alumnoEsperado.getDni(), alumnoEncontrado.getDni());
    }

    @Test
    public void testModificarAlumno() {
        long idAlumno = 1L;
        Alumno alumnoModificado = new Alumno("Juan", "Perez", 123456789L);
        when(alumnoDao.loadAlumno(idAlumno)).thenReturn(alumnoModificado);

        Alumno alumnoNuevo = new Alumno("Carlos", "Gomez", 987654321L);
        when(alumnoDao.saveAlumno(any())).thenReturn(alumnoNuevo);

        Alumno alumnoActualizado = alumnoService.modificarAlumno(idAlumno, alumnoNuevo);

        assertNotNull(alumnoActualizado);
        assertEquals(alumnoNuevo.getNombre(), alumnoActualizado.getNombre());
        assertEquals(alumnoNuevo.getApellido(), alumnoActualizado.getApellido());
        assertEquals(alumnoNuevo.getDni(), alumnoActualizado.getDni());
    }

    @Test
    public void testDeleteAlumno() {
        long dni = 123456789L;
        Alumno alumnoEliminado = new Alumno("Juan", "Perez", dni);
        when(alumnoDao.deleteAlumno(dni)).thenReturn(alumnoEliminado);

        Alumno alumnoBorrado = alumnoService.deleteAlumno(dni);

        assertNotNull(alumnoBorrado);
        assertEquals(alumnoEliminado.getNombre(), alumnoBorrado.getNombre());
        assertEquals(alumnoEliminado.getApellido(), alumnoBorrado.getApellido());
        assertEquals(alumnoEliminado.getDni(), alumnoBorrado.getDni());
    }

    @Test
    public void testModificarEstadoAsignatura() {
        long idAlumno = 1L;
        int idAsignatura = 1;
        String estadoAsignatura = "Aprobada";

        Alumno alumno = new Alumno("Juan", "Perez", 123456789L);
        when(alumnoDao.loadAlumno(idAlumno)).thenReturn(alumno);

        Alumno alumnoActualizado = alumnoService.modificarEstadoAsignatura(idAlumno, idAsignatura, estadoAsignatura);

        assertNotNull(alumnoActualizado);
    }
}
