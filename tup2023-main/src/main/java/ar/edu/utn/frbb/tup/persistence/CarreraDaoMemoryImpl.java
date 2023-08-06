package ar.edu.utn.frbb.tup.persistence;

import ar.edu.utn.frbb.tup.model.Carrera;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CarreraDaoMemoryImpl implements CarreraDao {

    private static final Map<String, Carrera> carreras = new HashMap<>();

    @Override
    public Carrera save(Carrera carrera) {
        carreras.put(carrera.getNombre(), carrera);
        return carrera;
    }

    @Override
    public Carrera obtenerCarreraPorCodigo(int codigo) {
        return carreras.get(codigo);
    }

    @Override
    public List<Carrera> obtenerTodasLasCarreras() {
        return new ArrayList<>(carreras.values());
    }

    @Override
    public void deleteCarrera(int codigo) {
        carreras.remove(codigo);
    }
}

