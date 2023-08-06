package ar.edu.utn.frbb.tup.business;

import ar.edu.utn.frbb.tup.model.Alumno;
import ar.edu.utn.frbb.tup.model.dto.AlumnoDto;
import ar.edu.utn.frbb.tup.model.exception.CorrelatividadesNoAprobadasException;
import ar.edu.utn.frbb.tup.model.exception.EstadoIncorrectoException;
import ar.edu.utn.frbb.tup.persistence.exception.DaoException;

public interface AlumnoService {
    void aprobarAsignatura(int materiaId, int nota, long dni) throws EstadoIncorrectoException, CorrelatividadesNoAprobadasException;
    Alumno crearAlumno(AlumnoDto alumno);
    Alumno buscarAlumno(String apellidoAlumno) throws DaoException;
    Alumno modificarAlumno(long idAlumno, Alumno alumno);
    Alumno deleteAlumno(Long dni);
    Alumno modificarEstadoAsignatura(long idAlumno, int idAsignatura, String estadoAsignatura);
}
