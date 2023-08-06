package ar.edu.utn.frbb.tup.business;

import ar.edu.utn.frbb.tup.model.Carrera;
import ar.edu.utn.frbb.tup.model.dto.CarreraDto;

import java.util.List;

public interface CarreraService {

    Carrera guardarCarrera(CarreraDto carreraDto);

    Carrera obtenerCarreraPorCodigo(int codigo);

    List<Carrera> obtenerTodasLasCarreras();

    Carrera deleteCarrera(int codigo);
}