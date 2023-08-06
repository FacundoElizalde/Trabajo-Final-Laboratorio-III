package ar.edu.utn.frbb.tup.persistence;

import ar.edu.utn.frbb.tup.model.Carrera;

import java.util.List;

public interface CarreraDao {

    Carrera save(Carrera carrera);

    Carrera obtenerCarreraPorCodigo(int codigo);

    List<Carrera> obtenerTodasLasCarreras();

    void deleteCarrera(int codigo);
}


