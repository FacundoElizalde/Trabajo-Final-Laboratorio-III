package ar.edu.utn.frbb.tup.business.impl;

import ar.edu.utn.frbb.tup.persistence.CarreraDao;
import ar.edu.utn.frbb.tup.model.dto.CarreraDto;
import ar.edu.utn.frbb.tup.model.Carrera;
import ar.edu.utn.frbb.tup.business.CarreraService;
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
    public Carrera obtenerCarreraPorCodigo(int codigo) {
        return carreraDao.obtenerCarreraPorCodigo(codigo);
    }

    @Override
    public List<Carrera> obtenerTodasLasCarreras() {
        return carreraDao.obtenerTodasLasCarreras();
    }

    @Override
    public Carrera deleteCarrera(int codigo) {
        carreraDao.deleteCarrera(codigo);
        return null;
    }
}