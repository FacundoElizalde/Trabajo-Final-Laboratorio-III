package ar.edu.utn.frbb.tup.model;

import java.util.ArrayList;
import java.util.List;

public class Carrera {
    private String nombre;
    private int codigo;
    private int departamento;
    private int cantidadCuatrimestres;
    private List<Materia> materias;

    // Constructor
    public Carrera(String nombre, int codigo, int departamento, int cantidadCuatrimestres) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.departamento = departamento;
        this.cantidadCuatrimestres = cantidadCuatrimestres;
        this.materias = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getDepartamento() {
        return departamento;
    }

    public void setDepartamento(int departamento) {
        this.departamento = departamento;
    }

    public int getCantidadCuatrimestres() {
        return cantidadCuatrimestres;
    }

    public void setCantidadCuatrimestres(int cantidadCuatrimestres) {
        this.cantidadCuatrimestres = cantidadCuatrimestres;
    }

    public List<Materia> getMaterias() {
        return materias;
    }

    public void setMaterias(List<Materia> materias) {
        this.materias = materias;
    }
}

