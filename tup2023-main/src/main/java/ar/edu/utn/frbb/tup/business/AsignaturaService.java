package ar.edu.utn.frbb.tup.business;

public interface AsignaturaService {
    Asignatura getAsignatura(int materiaId, long dni);

    void actualizarAsignatura(Asignatura a);
}
