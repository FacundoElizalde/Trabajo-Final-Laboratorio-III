package ar.edu.utn.frbb.tup.persistence;

import ar.edu.utn.frbb.tup.model.Carrera;

import java.util.List;

public interface CarreraDao {

    Carrera save(Carrera carrera);

    Carrera obtenerCarreraPorCodigo(String codigo);

    List<Carrera> obtenerTodasLasCarreras();

    void deleteCarrera(String codigo);
}


