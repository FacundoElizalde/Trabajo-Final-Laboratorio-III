package ar.edu.utn.frbb.tup.persistence;

import ar.edu.utn.frbb.tup.model.Materia;
import java.util.List;

public interface MateriaDao {
    Materia save(Materia materia);

    Materia findById(int idMateria);

    List<Materia> getMateriasPorNombre(String nombre);

    List<Materia> getMateriasOrdenadas(String order);

    void delete(Materia materia);
}
