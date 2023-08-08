package ar.edu.utn.frbb.tup.business;

import ar.edu.utn.frbb.tup.business.impl.CarreraServiceImpl;
import ar.edu.utn.frbb.tup.model.Carrera;
import ar.edu.utn.frbb.tup.model.dto.CarreraDto;
import ar.edu.utn.frbb.tup.persistence.CarreraDao;
import ar.edu.utn.frbb.tup.persistence.exception.CarreraNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CarreraServiceTest {

    private CarreraService carreraService;
    private CarreraDao carreraDao;

    @BeforeEach
    public void setUp() {
        carreraDao = mock(CarreraDao.class);
        carreraService = new CarreraServiceImpl(carreraDao);
    }

    @Test
    public void testGuardarCarrera() {
        CarreraDto carreraDto = new CarreraDto();
        carreraDto.setNombre("Ingeniería en Sistemas");
        carreraDto.setCodigo(1);
        carreraDto.setDepartamento(1);
        carreraDto.setCantidadCuatrimestres(10);

        Carrera carreraGuardada = new Carrera(carreraDto.getNombre(), carreraDto.getCodigo(),
                carreraDto.getDepartamento(), carreraDto.getCantidadCuatrimestres());
        when(carreraDao.save(any(Carrera.class))).thenReturn(carreraGuardada);

        Carrera carrera = carreraService.guardarCarrera(carreraDto);

        assertNotNull(carrera);
        assertEquals(carreraDto.getNombre(), carrera.getNombre());
        assertEquals(carreraDto.getCodigo(), carrera.getCodigo());
        assertEquals(carreraDto.getDepartamento(), carrera.getDepartamento());
        assertEquals(carreraDto.getCantidadCuatrimestres(), carrera.getCantidadCuatrimestres());
    }

    @Test
    public void testObtenerCarreraPorCodigo() throws CarreraNotFoundException {
        int codigo = 1;
        Carrera carrera = new Carrera("Ingeniería en Sistemas", codigo, 1, 10);
        when(carreraDao.obtenerCarreraPorCodigo(codigo)).thenReturn(carrera);

        Carrera result = carreraService.obtenerCarreraPorCodigo(codigo);

        assertNotNull(result);
        assertEquals(carrera, result);
    }

    @Test
    public void testObtenerCarreraPorCodigoNotFound() {
        int codigo = 1;
        when(carreraDao.obtenerCarreraPorCodigo(codigo)).thenReturn(null);

        assertThrows(CarreraNotFoundException.class, () -> carreraService.obtenerCarreraPorCodigo(codigo));
    }

    @Test
    public void testObtenerTodasLasCarreras() {
        List<Carrera> carreras = new ArrayList<>();
        carreras.add(new Carrera("Ingeniería en Sistemas", 1, 1, 10));
        carreras.add(new Carrera("Ingeniería Civil", 2, 1, 10));
        when(carreraDao.obtenerTodasLasCarreras()).thenReturn(carreras);

        List<Carrera> result = carreraService.obtenerTodasLasCarreras();

        assertNotNull(result);
        assertEquals(carreras, result);
    }

    @Test
    public void testDeleteCarrera() throws CarreraNotFoundException {
        int codigo = 1;
        Carrera carrera = new Carrera("Ingeniería en Sistemas", codigo, 1, 10);

        when(carreraDao.obtenerCarreraPorCodigo(codigo)).thenReturn(carrera);

        Carrera result = carreraService.deleteCarrera(codigo);

        assertNotNull(result);
        assertEquals(carrera, result);
    }

    @Test
    public void testDeleteCarreraNotFound() {
        int codigo = 1;
        when(carreraDao.obtenerCarreraPorCodigo(codigo)).thenReturn(null);

        assertThrows(CarreraNotFoundException.class, () -> carreraService.deleteCarrera(codigo));
    }
}
