package ar.edu.utn.frbb.tup.persistence;

import ar.edu.utn.frbb.tup.model.Materia;
import ar.edu.utn.frbb.tup.persistence.exception.MateriaNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MateriaDaoMemoryImpl implements MateriaDao {
    private final Map<Integer, Materia> materiasMap;
    private int nextId;

    public MateriaDaoMemoryImpl() {
        this.materiasMap = new HashMap<>();
        this.nextId = 1;
    }

    @Override
    public Materia save(Materia materia) {
        if (materia.getMateriaId() == 0) {
            materia.setMateriaId(nextId++);
        }
        materiasMap.put(materia.getMateriaId(), materia);
        return materia;
    }

    @Override
    public Materia findById(int idMateria) {
        try {
            Materia materia = materiasMap.get(idMateria);
            if (materia == null) {
                throw new MateriaNotFoundException("No se encontró la materia con el id " + idMateria);
            }
            return materia;
        } catch (MateriaNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Materia> getMateriasPorNombre(String nombre) {
        return materiasMap.values().stream()
                .filter(m -> m.getNombre().equalsIgnoreCase(nombre))
                .collect(Collectors.toList());
    }

    @Override
    public List<Materia> getMateriasOrdenadas(String order) {
        List<Materia> materias = new ArrayList<>(materiasMap.values());
        if ("asc".equalsIgnoreCase(order)) {
            materias.sort(Comparator.comparing(Materia::getNombre));
        } else if ("desc".equalsIgnoreCase(order)) {
            materias.sort(Comparator.comparing(Materia::getNombre).reversed());
        }
        return materias;
    }

    @Override
    public void delete(Materia materia) {
        materiasMap.remove(materia.getMateriaId());
    }
}
