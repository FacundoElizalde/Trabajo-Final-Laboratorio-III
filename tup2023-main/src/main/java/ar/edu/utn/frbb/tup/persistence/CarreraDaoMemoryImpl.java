package ar.edu.utn.frbb.tup.persistence;

import ar.edu.utn.frbb.tup.model.Carrera;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CarreraDaoMemoryImpl implements CarreraDao {

    private static final Map<String, Carrera> carrerasPorNombre = new HashMap<>();
    private static final Map<Integer, Carrera> carrerasPorCodigo = new HashMap<>();


    @Override
    public Carrera save(Carrera carrera) {
        carrerasPorNombre.put(carrera.getNombre(), carrera);
        return carrera;
    }

    @Override
    public Carrera obtenerCarreraPorCodigo(int codigo) {
        return carrerasPorCodigo.get(codigo);
    }

    @Override
    public List<Carrera> obtenerTodasLasCarreras() {
        return new ArrayList<>(carrerasPorNombre.values());
    }

    @Override
    public void deleteCarrera(int codigo) {
        Carrera carrera = carrerasPorCodigo.get(codigo);
        if (carrera != null) {
            carrerasPorCodigo.remove(codigo);
            carrerasPorNombre.remove(carrera.getNombre());
        }
    }
}