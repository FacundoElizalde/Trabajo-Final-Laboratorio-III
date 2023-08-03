package ar.edu.utn.frbb.tup.persistence;

import ar.edu.utn.frbb.tup.model.Carrera;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarreraDaoMemoryImpl implements CarreraDao {

    // Simulación de una base de datos o almacenamiento en memoria
    private static Map<String, Carrera> carreras = new HashMap<>();

    @Override
    public Carrera save(Carrera carrera) {
        carreras.put(carrera.getNombre(), carrera);
        return carrera;
    }

    @Override
    public Carrera obtenerCarreraPorNombre(String nombre) {
        return carreras.get(nombre);
    }

    @Override
    public List<Carrera> obtenerTodasLasCarreras() {
        return new ArrayList<>(carreras.values());
    }

    @Override
    public void eliminarCarrera(String nombre) {
        carreras.remove(nombre);
    }
}

