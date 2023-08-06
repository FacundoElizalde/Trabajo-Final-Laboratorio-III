package ar.edu.utn.frbb.tup.persistence.exception;

public class MateriaNotFoundException extends Exception {
    public MateriaNotFoundException(String message) {
        message = "Materia no encontrada";
        MateriaNotFoundException exception = new MateriaNotFoundException(message);
    }
}