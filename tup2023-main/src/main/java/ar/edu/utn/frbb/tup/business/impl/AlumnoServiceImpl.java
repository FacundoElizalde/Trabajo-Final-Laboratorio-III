package ar.edu.utn.frbb.tup.business.impl;

import ar.edu.utn.frbb.tup.business.AlumnoService;
import ar.edu.utn.frbb.tup.business.AsignaturaService;
import ar.edu.utn.frbb.tup.model.Alumno;
import ar.edu.utn.frbb.tup.model.Asignatura;
import ar.edu.utn.frbb.tup.model.EstadoAsignatura;
import ar.edu.utn.frbb.tup.model.Materia;
import ar.edu.utn.frbb.tup.model.dto.AlumnoDto;
import ar.edu.utn.frbb.tup.model.exception.CorrelatividadesNoAprobadasException;
import ar.edu.utn.frbb.tup.model.exception.EstadoIncorrectoException;
import ar.edu.utn.frbb.tup.persistence.AlumnoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AlumnoServiceImpl implements AlumnoService {

    private final AlumnoDao alumnoDao;
    private final AsignaturaService asignaturaService;

    @Autowired
    public AlumnoServiceImpl(AlumnoDao alumnoDao, AsignaturaService asignaturaService) {
        this.alumnoDao = alumnoDao;
        this.asignaturaService = asignaturaService;
    }

    @Override
    public void aprobarAsignatura(int materiaId, int nota, long dni) throws EstadoIncorrectoException, CorrelatividadesNoAprobadasException {
        Asignatura a = asignaturaService.getAsignatura(materiaId, dni);
        for (Materia m : a.getMateria().getCorrelatividades()) {
            Asignatura correlativa = asignaturaService.getAsignatura(m.getMateriaId(), dni);
            if (!EstadoAsignatura.APROBADA.equals(correlativa.getEstado())) {
                throw new CorrelatividadesNoAprobadasException("La materia " + m.getNombre() + " debe estar aprobada para aprobar " + a.getNombreAsignatura());
            }
        }
        a.aprobarAsignatura(nota);
        asignaturaService.actualizarAsignatura(a);
        Alumno alumno = alumnoDao.loadAlumno(dni);
        alumno.actualizarAsignatura(a);
        alumnoDao.saveAlumno(alumno);
    }

    @Override
    public Alumno crearAlumno(AlumnoDto alumno) {
        Alumno a = new Alumno();
        a.setNombre(alumno.getNombre());
        a.setApellido(alumno.getApellido());
        a.setDni(alumno.getDni());
        Random random = new Random();
        a.setId(random.nextLong());
        alumnoDao.saveAlumno(a);
        return a;
    }

    @Override
    public Alumno buscarAlumno(String apellido) {
        return alumnoDao.findAlumno(apellido);
    }

    @Override
    public Alumno deleteAlumno(Long dni) {
        return alumnoDao.deleteAlumno(dni);
    }

    @Override
    public Alumno modificarAlumno(long idAlumno, Alumno alumno) {
        Alumno alumnoExistente = alumnoDao.loadAlumno(idAlumno);
        if (alumnoExistente != null) {
            alumnoExistente.setNombre(alumno.getNombre());
            alumnoExistente.setApellido(alumno.getApellido());
            alumnoExistente.setDni(alumno.getDni());
            alumnoDao.saveAlumno(alumnoExistente);
        }
        return alumnoExistente;
    }

    @Override
    public Alumno modificarEstadoAsignatura(long idAlumno, int idAsignatura, String estadoAsignatura) {
        Alumno alumno = alumnoDao.loadAlumno(idAlumno);
        if (alumno != null) {
            for (Asignatura asignatura : alumno.getAsignaturas()) {
                if (asignatura.getId() == idAsignatura) {
                    asignatura.setEstado(EstadoAsignatura.valueOf(estadoAsignatura.toUpperCase()));
                    alumnoDao.saveAlumno(alumno);
                    return alumno;
                }
            }
        }
        return null;
    }
}

