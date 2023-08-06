package ar.edu.utn.frbb.tup.controller;

import ar.edu.utn.frbb.tup.business.AlumnoService;
import ar.edu.utn.frbb.tup.model.Alumno;
import ar.edu.utn.frbb.tup.model.dto.AlumnoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alumno")
public class AlumnoController {
    private final AlumnoService alumnoService;
    @Autowired
    public AlumnoController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    @PostMapping
    public ResponseEntity<Alumno> crearAlumno(@RequestBody AlumnoDto alumnoDto) {
        Alumno alumno = convertirDtoAAlumno(alumnoDto);
        Alumno nuevoAlumno = alumnoService.crearAlumno(alumnoDto);
        return new ResponseEntity<>(nuevoAlumno, HttpStatus.CREATED);
    }

    @PutMapping("/alumno/{idAlumno}")
    public ResponseEntity<Alumno> modificarAlumno(@PathVariable long idAlumno, @RequestBody AlumnoDto alumnoDto) {
        Alumno alumno = convertirDtoAAlumno(alumnoDto);
        Alumno alumnoModificado = alumnoService.modificarAlumno(idAlumno, alumno);
        if (alumnoModificado != null) {
            return new ResponseEntity<>(alumnoModificado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/alumno/{idAlumno}")
    public ResponseEntity<Alumno> deleteAlumno(@PathVariable long idAlumno) {
        Alumno alumnoEliminado = alumnoService.deleteAlumno(idAlumno);
        if (alumnoEliminado != null) {
            return new ResponseEntity<>(alumnoEliminado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{idAlumno}/asignatura/{idAsignatura}")
    public ResponseEntity<Alumno> modificarEstadoAsignatura(
            @PathVariable long idAlumno,
            @PathVariable int idAsignatura,
            @RequestParam String estadoAsignatura
    ) {
        Alumno alumno = alumnoService.modificarEstadoAsignatura(idAlumno, idAsignatura, estadoAsignatura);
        if (alumno != null) {
            return new ResponseEntity<>(alumno, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    private Alumno convertirDtoAAlumno(AlumnoDto alumnoDto) {
        Alumno alumno = new Alumno();
        alumno.setNombre(alumnoDto.getNombre());
        alumno.setApellido(alumnoDto.getApellido());
        alumno.setDni(alumnoDto.getDni());
        return alumno;
    }
}

