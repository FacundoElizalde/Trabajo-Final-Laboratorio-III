package ar.edu.utn.frbb.tup.controller;

import ar.edu.utn.frbb.tup.business.MateriaService;
import ar.edu.utn.frbb.tup.model.Materia;
import ar.edu.utn.frbb.tup.model.dto.MateriaDto;
import ar.edu.utn.frbb.tup.persistence.exception.MateriaNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MateriaControllerTest {

    @Mock
    private MateriaService materiaService;

    @InjectMocks
    private MateriaController materiaController;

    @Test
    public void testCrearMateria() {
        MateriaDto materiaDto = new MateriaDto();
        materiaDto.setNombre("Laboratorio en Computacion III");
        materiaDto.setAnio(2);
        materiaDto.setCuatrimestre(1);

        Materia nuevaMateria = new Materia();
        nuevaMateria.setNombre("Laboratorio en Computacion III");
        nuevaMateria.setAnio(2);
        nuevaMateria.setCuatrimestre(1);

        when(materiaService.crearMateria(any(MateriaDto.class))).thenReturn(nuevaMateria);
        ResponseEntity<Materia> response = materiaController.crearMateria(materiaDto);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(nuevaMateria);
    }

    @Test
    public void testCrearMateria_InvalidInput() {
        MateriaDto materiaDto = new MateriaDto();
        materiaDto.setNombre("Laboratorio en Computacion III");
        materiaDto.setAnio(0);
        materiaDto.setCuatrimestre(1);

        ResponseEntity<Materia> response = materiaController.crearMateria(materiaDto);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testModificarMateria() throws MateriaNotFoundException {
        int idMateria = 1;
        MateriaDto materiaDto = new MateriaDto();
        materiaDto.setNombre("Matemáticas");
        materiaDto.setAnio(2);
        materiaDto.setCuatrimestre(1);

        Materia materiaModificada = new Materia();
        materiaModificada.setMateriaId(idMateria);
        materiaModificada.setNombre("Matemáticas");
        materiaModificada.setAnio(2);
        materiaModificada.setCuatrimestre(1);

        when(materiaService.modificarMateria(eq(idMateria), any(Materia.class))).thenReturn(materiaModificada);

        ResponseEntity<Materia> response = materiaController.modificarMateria(idMateria, materiaDto);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(materiaModificada);
    }


    @Test
    public void testModificarMateria_MateriaNotFoundException() throws MateriaNotFoundException {
        int idMateria = 1;
        MateriaDto materiaDto = new MateriaDto();
        materiaDto.setNombre("Matemáticas");
        materiaDto.setAnio(2);
        materiaDto.setCuatrimestre(1);

        when(materiaService.modificarMateria(eq(idMateria), any(Materia.class)))
                .thenThrow(new MateriaNotFoundException("Materia no encontrada con ID: " + idMateria));

        ResponseEntity<Materia> response = materiaController.modificarMateria(idMateria, materiaDto);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }


    @Test
    public void testDeleteMateria() throws MateriaNotFoundException {
        int idMateria = 1;

        Materia materiaEliminada = new Materia();
        materiaEliminada.setMateriaId(idMateria);
        materiaEliminada.setNombre("Matemáticas");
        materiaEliminada.setAnio(2);
        materiaEliminada.setCuatrimestre(1);

        when(materiaService.deleteMateria(eq(idMateria))).thenReturn(materiaEliminada);

        ResponseEntity<Materia> response = materiaController.deleteMateria(idMateria);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }



    @Test
    public void testDeleteMateria_MateriaNotFoundException() throws MateriaNotFoundException {
        int idMateria = 1;

        when(materiaService.deleteMateria(eq(idMateria))).thenThrow(MateriaNotFoundException.class);
        ResponseEntity<Materia> response = materiaController.deleteMateria(idMateria);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    private int anyInt() {
        return 0;
    }
}

