package ar.edu.utn.frbb.tup.controller;

import ar.edu.utn.frbb.tup.business.AlumnoService;
import ar.edu.utn.frbb.tup.model.Alumno;
import ar.edu.utn.frbb.tup.model.dto.AlumnoDto;
import ar.edu.utn.frbb.tup.model.exception.CorrelatividadesNoAprobadasException;
import ar.edu.utn.frbb.tup.model.exception.EstadoIncorrectoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alumnos")
public class AlumnoController {

    private final AlumnoService alumnoService;

    @Autowired
    public AlumnoController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    @PostMapping
    public ResponseEntity<Alumno> crearAlumno(@RequestBody AlumnoDto alumnoDto) {
        Alumno alumno = alumnoService.crearAlumno(alumnoDto);
        return new ResponseEntity<>(alumno, HttpStatus.CREATED);
    }

    @GetMapping("/{dni}")
    public ResponseEntity<Alumno> buscarAlumnoPorDni(@PathVariable long dni) {
        Alumno alumno = alumnoService.buscarAlumno(String.valueOf(dni));
        if (alumno != null) {
            return new ResponseEntity<>(alumno, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{dni}")
    public ResponseEntity<Alumno> deleteAlumno(@PathVariable long dni) {
        Alumno alumno = alumnoService.deleteAlumno(dni);
        if (alumno != null) {
            return new ResponseEntity<>(alumno, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{materiaId}/aprobar")
    public ResponseEntity<String> aprobarAsignatura(
            @PathVariable int materiaId,
            @RequestParam int nota,
            @RequestParam long dni
    ) {
        try {
            alumnoService.aprobarAsignatura(materiaId, nota, dni);
            return new ResponseEntity<>("Asignatura aprobada correctamente", HttpStatus.OK);
        } catch (EstadoIncorrectoException |
                 CorrelatividadesNoAprobadasException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
