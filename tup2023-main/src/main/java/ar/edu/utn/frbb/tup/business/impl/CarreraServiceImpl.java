package ar.edu.utn.frbb.tup.business.impl;

import ar.edu.utn.frbb.tup.model.Materia;
import ar.edu.utn.frbb.tup.persistence.CarreraDao;
import ar.edu.utn.frbb.tup.model.dto.CarreraDto;
import ar.edu.utn.frbb.tup.model.Carrera;
import ar.edu.utn.frbb.tup.business.CarreraService;
import ar.edu.utn.frbb.tup.persistence.exception.CarreraNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarreraServiceImpl implements CarreraService {

    private final CarreraDao carreraDao;

    public CarreraServiceImpl(CarreraDao carreraDao) {
        this.carreraDao = carreraDao;
    }

    @Override
    public Carrera guardarCarrera(CarreraDto carreraDto) {
        Carrera carrera = new Carrera(
                carreraDto.getNombre(),
                carreraDto.getCodigo(),
                carreraDto.getDepartamento(),
                carreraDto.getCantidadCuatrimestres()
        );
        return carreraDao.save(carrera);
    }

    @Override
    public List<Carrera> obtenerTodasLasCarreras() {
        return carreraDao.obtenerTodasLasCarreras();
    }

    @Override
    public Carrera obtenerCarreraPorCodigo(int codigo) throws CarreraNotFoundException {
        Carrera carrera = carreraDao.obtenerCarreraPorCodigo(codigo);
        if (carrera == null) {
            throw new CarreraNotFoundException("No se encontró una carrera con el código " + codigo);
        }
        return carrera;
    }


    @Override
    public Carrera modificarCarrera(int codigo, CarreraDto carreraDto) throws CarreraNotFoundException {
        Carrera carreraExistente = carreraDao.obtenerCarreraPorCodigo(codigo);
        if (carreraExistente == null) {
            throw new CarreraNotFoundException("Carrera no encontrada con código: " + codigo);
        }

        carreraExistente.setNombre(carreraDto.getNombre());
        carreraExistente.setDepartamento(carreraDto.getDepartamento());
        carreraExistente.setCantidadCuatrimestres(carreraDto.getCantidadCuatrimestres());

        return carreraDao.save(carreraExistente);
    }

    @Override
    public Carrera deleteCarrera(int codigo) throws CarreraNotFoundException {
        Carrera carrera = carreraDao.obtenerCarreraPorCodigo(codigo);
        if (carrera == null) {
            throw new CarreraNotFoundException("Carrera no encontrada con código: " + codigo);
        }
        carreraDao.deleteCarrera(codigo);
        return carrera;
    }
}
