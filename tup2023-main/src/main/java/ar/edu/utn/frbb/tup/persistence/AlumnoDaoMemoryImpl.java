package ar.edu.utn.frbb.tup.persistence;

import ar.edu.utn.frbb.tup.model.Alumno;
import ar.edu.utn.frbb.tup.persistence.exception.DaoException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class AlumnoDaoMemoryImpl implements AlumnoDao {

    private static final Map<Long, Alumno> repositorioAlumnos = new HashMap<>();

    @Override
    public Alumno saveAlumno(Alumno alumno) {
        Random random = new Random();
        alumno.setId(random.nextLong());
        repositorioAlumnos.put(alumno.getDni(), alumno);
        return alumno;
    }

    @Override
    public Alumno findAlumno(String apellidoAlumno) throws DaoException {
        for (Alumno a : repositorioAlumnos.values()) {
            if (a.getApellido().equals(apellidoAlumno)) {
                return a;
            }
        }
        throw new DaoException("No existen alumnos con ese apellido.");
    }

    @Override
    public Alumno loadAlumno(Long dni) {
        return repositorioAlumnos.get(dni);
    }

    @Override
    public Alumno deleteAlumno(Long dni) {
        return repositorioAlumnos.remove(dni);
    }
}