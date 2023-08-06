package ar.edu.utn.frbb.tup.controller;

import ar.edu.utn.frbb.tup.model.Carrera;
import ar.edu.utn.frbb.tup.model.dto.CarreraDto;
import ar.edu.utn.frbb.tup.business.CarreraService;
import ar.edu.utn.frbb.tup.persistence.exception.CarreraNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carrera")
public class CarreraController {
    private final CarreraService carreraService;

    @Autowired
    public CarreraController(CarreraService carreraService) {
        this.carreraService = carreraService;
    }

    @GetMapping("/carrera/{codigo}")
    public ResponseEntity<Carrera> obtenerCarreraPorCodigo(@PathVariable int codigo) {
        Carrera carrera = carreraService.obtenerCarreraPorCodigo(codigo);
        if (carrera != null) {
            return new ResponseEntity<>(carrera, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Carrera>> obtenerTodasLasCarreras() {
        List<Carrera> carreras = carreraService.obtenerTodasLasCarreras();
        return new ResponseEntity<>(carreras, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Carrera> guardarCarrera(@RequestBody CarreraDto carreraDto) {
        Carrera carreraGuardada = carreraService.guardarCarrera(carreraDto);
        return new ResponseEntity<>(carreraGuardada, HttpStatus.CREATED);
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> deleteCarrera(@PathVariable int codigo) {
        try {
            carreraService.deleteCarrera(codigo);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (CarreraNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
