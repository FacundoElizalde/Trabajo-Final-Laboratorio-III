package ar.edu.utn.frbb.tup.business;

import ar.edu.utn.frbb.tup.model.Carrera;
import ar.edu.utn.frbb.tup.model.Materia;
import ar.edu.utn.frbb.tup.model.dto.CarreraDto;
import ar.edu.utn.frbb.tup.persistence.exception.CarreraNotFoundException;

import java.util.List;

public interface CarreraService {

    Carrera guardarCarrera(CarreraDto carreraDto);

    Carrera obtenerCarreraPorCodigo(int codigo);

    List<Carrera> obtenerTodasLasCarreras();

    Carrera modificarCarrera(int codigo, CarreraDto carreraDto) throws CarreraNotFoundException;

    Carrera deleteCarrera(int codigo) throws CarreraNotFoundException;
}
