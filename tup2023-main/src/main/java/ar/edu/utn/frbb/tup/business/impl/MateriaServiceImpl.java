package ar.edu.utn.frbb.tup.business.impl;

import ar.edu.utn.frbb.tup.business.MateriaService;
import ar.edu.utn.frbb.tup.model.Materia;
import ar.edu.utn.frbb.tup.model.dto.MateriaDto;
import ar.edu.utn.frbb.tup.persistence.MateriaDao;
import ar.edu.utn.frbb.tup.persistence.exception.MateriaNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MateriaServiceImpl implements MateriaService {

    private final MateriaDao materiaDao;

    public MateriaServiceImpl(MateriaDao materiaDao) {
        this.materiaDao = materiaDao;
    }

    @Override
    public Materia crearMateria(MateriaDto inputData) throws IllegalArgumentException {
        Materia nuevaMateria = new Materia(
                inputData.getNombre(),
                inputData.getAnio(),
                inputData.getCuatrimestre()
        );
        return materiaDao.save(nuevaMateria);
    }

    @Override
    public Materia getMateriaById(int idMateria) throws MateriaNotFoundException {
        Materia materia = materiaDao.findById(idMateria);
        if (materia == null) {
            throw new MateriaNotFoundException("No se encontró la materia con ID " + idMateria);
        }
        return materia;
    }

    @Override
    public List<Materia> getMateriasPorNombre(String nombre) {
        return materiaDao.getMateriasPorNombre(nombre);
    }

    @Override
    public List<Materia> getMateriasOrdenadas(String order) {
        return materiaDao.getMateriasOrdenadas(order);
    }

    @Override
    public Materia modificarMateria(int idMateria, Materia materia) throws MateriaNotFoundException {
        Materia materiaExistente = materiaDao.findById(idMateria);
        if (materiaExistente == null) {
            throw new MateriaNotFoundException("No se encontró la materia con ID " + idMateria);
        }

        materiaExistente.setNombre(materia.getNombre());
        materiaExistente.setAnio(materia.getAnio());
        materiaExistente.setCuatrimestre(materia.getCuatrimestre());

        return materiaDao.save(materiaExistente);
    }

    @Override
    public Materia deleteMateria(int idMateria) throws MateriaNotFoundException {
        Materia materia = materiaDao.findById(idMateria);
        if (materia == null) {
            throw new MateriaNotFoundException("No se encontró la materia con ID " + idMateria);
        }
        materiaDao.delete(materia);
        return materia;
    }
}
