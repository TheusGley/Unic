package com.example.unic.model;

public class Aulas {
    private String  materia, data, horario, sala;

    public Aulas (){

    }
    public Aulas(String materia, String data, String horario, String sala) {
        this.materia = materia;
        this.data = data;
        this.horario = horario;
        this.sala = sala;
    }
//para configurar os modelos das aulas
    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }
}
