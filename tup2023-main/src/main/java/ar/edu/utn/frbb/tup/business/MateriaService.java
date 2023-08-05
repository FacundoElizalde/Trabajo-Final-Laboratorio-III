package ar.edu.utn.frbb.tup.business;

import ar.edu.utn.frbb.tup.model.Materia;
import ar.edu.utn.frbb.tup.model.dto.MateriaDto;
import ar.edu.utn.frbb.tup.persistence.exception.MateriaNotFoundException;

import java.util.List;

public interface MateriaService {
    Materia crearMateria(MateriaDto inputData) throws IllegalArgumentException;

    Materia getMateriaById(int idMateria) throws MateriaNotFoundException;

    List<Materia> getMateriasPorNombre(String nombre);

    List<Materia> getMateriasOrdenadas(String order);

    Materia modificarMateria(int idMateria, Materia materia) throws MateriaNotFoundException;

    Materia deleteMateria(int idMateria) throws MateriaNotFoundException;
}
