package ar.edu.utn.frbb.tup.model.dto;

import ar.edu.utn.frbb.tup.model.Asignatura;

public class AsignaturaDto {

    private String nombreMateria;
    private String estado;
    private Integer nota;

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }
}
