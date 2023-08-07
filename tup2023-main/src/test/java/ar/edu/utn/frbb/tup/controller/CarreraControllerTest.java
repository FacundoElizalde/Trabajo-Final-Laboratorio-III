package ar.edu.utn.frbb.tup.controller;

import ar.edu.utn.frbb.tup.business.CarreraService;
import ar.edu.utn.frbb.tup.model.Carrera;
import ar.edu.utn.frbb.tup.model.dto.CarreraDto;
import ar.edu.utn.frbb.tup.persistence.exception.CarreraNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class CarreraControllerTest {

    @Mock
    private CarreraService carreraService;

    @InjectMocks
    private CarreraController carreraController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testObtenerCarreraPorCodigoExistente() {
        int codigo = 1;
        Carrera carrera = new Carrera("Carrera Test", codigo, 123, 8);

        when(carreraService.obtenerCarreraPorCodigo(eq(codigo))).thenReturn(carrera);

        ResponseEntity<Carrera> responseEntity = carreraController.obtenerCarreraPorCodigo(codigo);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(carrera, responseEntity.getBody());

        verify(carreraService, times(1)).obtenerCarreraPorCodigo(eq(codigo));
    }

    @Test
    void testObtenerCarreraPorCodigoNoExistente() {
        int codigo = 1;

        when(carreraService.obtenerCarreraPorCodigo(eq(codigo))).thenReturn(null);

        ResponseEntity<Carrera> responseEntity = carreraController.obtenerCarreraPorCodigo(codigo);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

        verify(carreraService, times(1)).obtenerCarreraPorCodigo(eq(codigo));
    }

    @Test
    void testObtenerTodasLasCarreras() {
        List<Carrera> carreras = new ArrayList<>();
        carreras.add(new Carrera("Carrera 1", 1, 123, 8));
        carreras.add(new Carrera("Carrera 2", 2, 456, 10));

        when(carreraService.obtenerTodasLasCarreras()).thenReturn(carreras);

        ResponseEntity<List<Carrera>> responseEntity = carreraController.obtenerTodasLasCarreras();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(carreras, responseEntity.getBody());

        verify(carreraService, times(1)).obtenerTodasLasCarreras();
    }

    @Test
    void testGuardarCarrera() {
        CarreraDto carreraDto = new CarreraDto();
        carreraDto.setNombre("Carrera Test");
        carreraDto.setCodigo(1);
        carreraDto.setDepartamento(123);
        carreraDto.setCantidadCuatrimestres(8);

        Carrera carreraGuardada = new Carrera("Carrera Test", 1, 123, 8);

        when(carreraService.guardarCarrera(any(CarreraDto.class))).thenReturn(carreraGuardada);

        ResponseEntity<Carrera> responseEntity = carreraController.guardarCarrera(carreraDto);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(carreraGuardada, responseEntity.getBody());

        verify(carreraService, times(1)).guardarCarrera(any(CarreraDto.class));
    }

    @Test
    void testModificarCarreraExistente() throws CarreraNotFoundException {
        int codigo = 1;
        CarreraDto carreraDto = new CarreraDto();
        carreraDto.setNombre("Carrera Modificada");
        carreraDto.setCodigo(codigo);
        carreraDto.setDepartamento(789);
        carreraDto.setCantidadCuatrimestres(12);

        Carrera carreraModificada = new Carrera("Carrera Modificada", codigo, 789, 12);

        when(carreraService.modificarCarrera(eq(codigo), any(CarreraDto.class))).thenReturn(carreraModificada);

        ResponseEntity<Carrera> responseEntity = carreraController.modificarCarrera(codigo, carreraDto);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(carreraModificada, responseEntity.getBody());

        verify(carreraService, times(1)).modificarCarrera(eq(codigo), any(CarreraDto.class));
    }

    @Test
    void testModificarCarreraNoExistente() throws CarreraNotFoundException {
        int codigo = 1;
        CarreraDto carreraDto = new CarreraDto();
        carreraDto.setNombre("Carrera Modificada");
        carreraDto.setCodigo(codigo);
        carreraDto.setDepartamento(789);
        carreraDto.setCantidadCuatrimestres(12);

        Carrera carreraModificada = new Carrera("Licenciatura en Computacion", 25043, 1, 6);
        when(carreraService.modificarCarrera(eq(codigo), any(CarreraDto.class))).thenReturn(carreraModificada);

        ResponseEntity<Carrera> responseEntity = carreraController.modificarCarrera(codigo, carreraDto);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(carreraModificada, responseEntity.getBody());

        verify(carreraService, times(1)).modificarCarrera(eq(codigo), any(CarreraDto.class));
    }


    @Test
    void testDeleteCarreraExistente() throws CarreraNotFoundException {
        int codigo = 1;
        Carrera carreraEliminada = new Carrera("Tecnicatura en Programación", 2508, 1, 3);
        when(carreraService.deleteCarrera(eq(codigo))).thenReturn(carreraEliminada);

        ResponseEntity<Carrera> responseEntity = carreraController.deleteCarrera(codigo);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());

        verify(carreraService, times(1)).deleteCarrera(eq(codigo));
    }



    @Test
    void testDeleteCarreraNoExistente() throws CarreraNotFoundException {
        int codigo = 1;
        doThrow(new CarreraNotFoundException("Carrera no encontrada con código: " + codigo))
                .when(carreraService).deleteCarrera(eq(codigo));

        ResponseEntity<Carrera> responseEntity = carreraController.deleteCarrera(codigo);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

        verify(carreraService, times(1)).deleteCarrera(eq(codigo));
    }
}
