package ar.edu.utn.frbb.tup.controller;

import ar.edu.utn.frbb.tup.business.MateriaService;
import ar.edu.utn.frbb.tup.model.Materia;
import ar.edu.utn.frbb.tup.model.dto.MateriaDto;
import ar.edu.utn.frbb.tup.persistence.exception.MateriaNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/materia")
public class MateriaController {

    private final MateriaService materiaService;

    @Autowired
    public MateriaController(MateriaService materiaService) {
        this.materiaService = materiaService;
    }

    @PostMapping
    public ResponseEntity<Materia> crearMateria(@RequestBody MateriaDto materiaDto) {
        Materia materia = convertirDtoAMateria(materiaDto);
        if (materiaDto.getAnio() <= 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            Materia nuevaMateria = materiaService.crearMateria(materiaDto);
            return new ResponseEntity<>(nuevaMateria, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{idMateria}")
    public ResponseEntity<Materia> modificarMateria(@PathVariable int idMateria, @RequestBody MateriaDto materiaDto) throws MateriaNotFoundException {
        Materia materia = convertirDtoAMateria(materiaDto);
        Materia materiaModificada = materiaService.modificarMateria(idMateria, materia);
        if (materiaModificada != null) {
            return new ResponseEntity<>(materiaModificada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{idMateria}")
    public ResponseEntity<Materia> deleteMateria(@PathVariable int idMateria) {
        try {
            Materia materiaEliminada = materiaService.deleteMateria(idMateria);
            return new ResponseEntity<>(materiaEliminada, HttpStatus.NO_CONTENT);
        } catch (MateriaNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping
    public ResponseEntity<List<Materia>> obtenerMateriasPorNombre(@RequestParam(required = false) String nombre) {
        if (nombre != null && !nombre.isEmpty()) {
            List<Materia> materiasPorNombre = materiaService.getMateriasPorNombre(nombre);
            return new ResponseEntity<>(materiasPorNombre, HttpStatus.OK);
        } else {
            List<Materia> todasLasMaterias = materiaService.getMateriasOrdenadas(nombre);
            return new ResponseEntity<>(todasLasMaterias, HttpStatus.OK);
        }
    }

    @GetMapping("/ordenar")
    public ResponseEntity<List<Materia>> obtenerMateriasOrdenadas(@RequestParam(required = false) String order) {
        List<Materia> materiasOrdenadas = materiaService.getMateriasOrdenadas(order);
        return new ResponseEntity<>(materiasOrdenadas, HttpStatus.OK);
    }

    private Materia convertirDtoAMateria(MateriaDto materiaDto) {
        Materia materia = new Materia();
        materia.setNombre(materiaDto.getNombre());
        materia.setAnio(materiaDto.getAnio());
        materia.setCuatrimestre(materiaDto.getCuatrimestre());
        return materia;
    }
}