package com.example.unic.model;
public class Aulas {

    private String materia;
    private String data;
    private String horas;
    private String sala;
    private String idDocumento ;

    // Construtor vazio necessário para Firebase
    public Aulas() {
        // Nada precisa ser feito aqui, mas o construtor vazio é necessário
    }

    // Construtor para inicializar os dados da aula
    public Aulas(String materia, String data, String horas, String sala, String idDocumento) {
        this.materia = materia;
        this.data = data;
        this.horas = horas;
        this.sala = sala;
        this.idDocumento = idDocumento;

    }

    // Métodos getter e setter
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

    public String getHoras() {
        return horas;
    }

    public void setHorario(String horas) {
        this.horas = horas;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }
    public String getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(String idDocumento) {
        this.idDocumento = idDocumento;
    }
}
