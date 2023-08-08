package ar.edu.utn.frbb.tup.persistence;

import ar.edu.utn.frbb.tup.model.Carrera;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
public class CarreraDaoTest {

    private CarreraDao carreraDao;

    @BeforeEach
    public void setUp() {
        carreraDao = new CarreraDaoMemoryImpl();
    }
    @Test
    public void testSaveAndObtenerCarreraPorCodigo() {
        Carrera carrera = new Carrera("Ingeniería en Sistemas", 1, 4, 8);

        when(carreraDao.save(carrera)).thenReturn(carrera);

        Carrera carreraGuardada = carreraDao.save(carrera);
        verify(carreraDao).save(carrera);
        Carrera carreraObtenida = carreraDao.obtenerCarreraPorCodigo(carrera.getCodigo());

        verify(carreraDao).obtenerCarreraPorCodigo(carrera.getCodigo());
        Assertions.assertNotNull(carreraObtenida);
        Assertions.assertEquals(carrera.getNombre(), carreraObtenida.getNombre());
        Assertions.assertEquals(carrera.getCodigo(), carreraObtenida.getCodigo());
        Assertions.assertEquals(carrera.getCantidadCuatrimestres(), carreraObtenida.getCantidadCuatrimestres());
    }

    @Test
    public void testObtenerTodasLasCarreras() {
        Carrera carrera1 = new Carrera("Ingeniería en Sistemas", 1, 4, 8);
        Carrera carrera2 = new Carrera("Licenciatura en Administración", 2, 6, 9);
        carreraDao.save(carrera1);
        carreraDao.save(carrera2);

        List<Carrera> carreras = carreraDao.obtenerTodasLasCarreras();

        Assertions.assertEquals(2, carreras.size());
    }

    @Test
    public void testDeleteCarrera() {
        Carrera carrera = new Carrera("Ingeniería en Sistemas", 1, 4, 8);
        carreraDao.save(carrera);

        carreraDao.deleteCarrera(carrera.getCodigo());

        Carrera carreraEliminada = carreraDao.obtenerCarreraPorCodigo(carrera.getCodigo());
        Assertions.assertNull(carreraEliminada);
    }
}

