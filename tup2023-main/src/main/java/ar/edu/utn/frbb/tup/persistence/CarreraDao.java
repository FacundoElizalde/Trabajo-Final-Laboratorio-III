package ar.edu.utn.frbb.tup.persistence;

import ar.edu.utn.frbb.tup.model.Carrera;

import java.util.List;

public interface CarreraDao {

    Carrera save(Carrera carrera);

    Carrera obtenerCarreraPorNombre(String nombre);

    List<Carrera> obtenerTodasLasCarreras();

    void eliminarCarrera(String nombre);
}


