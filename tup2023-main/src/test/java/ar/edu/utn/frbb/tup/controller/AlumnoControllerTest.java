package ar.edu.utn.frbb.tup.controller;

import ar.edu.utn.frbb.tup.business.AlumnoService;
import ar.edu.utn.frbb.tup.model.Alumno;
import ar.edu.utn.frbb.tup.model.dto.AlumnoDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AlumnoControllerTest {

    @Mock
    private AlumnoService alumnoService;

    @InjectMocks
    private AlumnoController alumnoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCrearAlumno() {
        AlumnoDto alumnoDto = new AlumnoDto();
        alumnoDto.setNombre("Juan");
        alumnoDto.setApellido("Perez");
        alumnoDto.setDni(123456789L);

        Alumno alumno = new Alumno();
        alumno.setNombre(alumnoDto.getNombre());
        alumno.setApellido(alumnoDto.getApellido());
        alumno.setDni(alumnoDto.getDni());

        when(alumnoService.crearAlumno(any(AlumnoDto.class))).thenReturn(alumno);

        ResponseEntity<Alumno> responseEntity = alumnoController.crearAlumno(alumnoDto);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(alumno, responseEntity.getBody());

        verify(alumnoService, times(1)).crearAlumno(any(AlumnoDto.class));
    }

    @Test
    void testModificarAlumno() {
        long idAlumno = 1L;
        AlumnoDto alumnoDto = new AlumnoDto();
        alumnoDto.setNombre("Juan");
        alumnoDto.setApellido("Perez");
        alumnoDto.setDni(123456789L);

        Alumno alumno = new Alumno();
        alumno.setId(idAlumno);
        alumno.setNombre(alumnoDto.getNombre());
        alumno.setApellido(alumnoDto.getApellido());
        alumno.setDni(alumnoDto.getDni());

        when(alumnoService.modificarAlumno(eq(idAlumno), any(Alumno.class))).thenReturn(alumno);

        ResponseEntity<Alumno> responseEntity = alumnoController.modificarAlumno(idAlumno, alumnoDto);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(alumno, responseEntity.getBody());

        verify(alumnoService, times(1)).modificarAlumno(eq(idAlumno), any(Alumno.class));
    }

    @Test
    void testDeleteAlumno() {
        long idAlumno = 1L;
        Alumno alumno = new Alumno();
        alumno.setId(idAlumno);
        alumno.setNombre("Juan");
        alumno.setApellido("Perez");
        alumno.setDni(123456789L);

        when(alumnoService.deleteAlumno(eq(idAlumno))).thenReturn(alumno);

        ResponseEntity<Alumno> responseEntity = alumnoController.deleteAlumno(idAlumno);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(alumno, responseEntity.getBody());

        verify(alumnoService, times(1)).deleteAlumno(eq(idAlumno));
    }

    @Test
    void testDeleteAlumnoNotFound() {
        long idAlumno = 1L;

        when(alumnoService.deleteAlumno(eq(idAlumno))).thenReturn(null);

        ResponseEntity<Alumno> responseEntity = alumnoController.deleteAlumno(idAlumno);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

        verify(alumnoService, times(1)).deleteAlumno(eq(idAlumno));
    }

    @Test
    void testModificarEstadoAsignatura() {
        long idAlumno = 1L;
        int idAsignatura = 100;
        String estadoAsignatura = "Aprobada";

        Alumno alumno = new Alumno();
        alumno.setId(idAlumno);
        alumno.setNombre("Juan");
        alumno.setApellido("Perez");
        alumno.setDni(123456789L);

        when(alumnoService.modificarEstadoAsignatura(eq(idAlumno), eq(idAsignatura), eq(estadoAsignatura))).thenReturn(alumno);

        ResponseEntity<Alumno> responseEntity = alumnoController.modificarEstadoAsignatura(idAlumno, idAsignatura, estadoAsignatura);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(alumno, responseEntity.getBody());

        verify(alumnoService, times(1)).modificarEstadoAsignatura(eq(idAlumno), eq(idAsignatura), eq(estadoAsignatura));
    }

    @Test
    void testModificarEstadoAsignaturaNotFound() {
        long idAlumno = 1L;
        int idAsignatura = 100;
        String estadoAsignatura = "Aprobada";

        when(alumnoService.modificarEstadoAsignatura(eq(idAlumno), eq(idAsignatura), eq(estadoAsignatura))).thenReturn(null);

        ResponseEntity<Alumno> responseEntity = alumnoController.modificarEstadoAsignatura(idAlumno, idAsignatura, estadoAsignatura);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

        verify(alumnoService, times(1)).modificarEstadoAsignatura(eq(idAlumno), eq(idAsignatura), eq(estadoAsignatura));
    }
}

