package com.xpomanager.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Exposicion {

    /*************
     * ATRIBUTOS *
     *************/
    private int id;
    private String nombre;
    private boolean activo;
    private Date fechaInicio;
    private Date fechaFin;
    private String descripcionExpo;
    private String adminUser;
    private String adminPassword;
    private int questionTimeOut;
    private int answerTimeOut;
    private int reviewTimeOut;
    private String appImageSrc;
    private String reviewImageSrc;
    private List<Personaje> personajes;
    private List<Idioma> idiomas;
    private Map<Idioma, ExposicionIdioma> exposicionIdiomas;
    private Map<Nivel, List<Pregunta>> preguntas;
    private List<Nivel> niveles;

    /*****************
     * CONSTRUCTORES *
     *****************/
    public Exposicion(int id, String nombre, boolean activo, Date fechaInicio, Date fechaFin,
                      String descripcionExpo, String adminUser, String adminPassword,
                      int questionTimeOut, int answerTimeOut, int reviewTimeOut, String appImageSrc,
                      String reviewImageSrc, List<Personaje> personajes, List<Idioma> idiomas,
                      Map<Idioma, ExposicionIdioma> exposicionIdiomas,
                      Map<Nivel, List<Pregunta>> preguntas, List<Nivel> niveles) {
        this.id = id;
        this.nombre = nombre;
        this.activo = activo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.descripcionExpo = descripcionExpo;
        this.adminUser = adminUser;
        this.adminPassword = adminPassword;
        this.questionTimeOut = questionTimeOut;
        this.answerTimeOut = answerTimeOut;
        this.reviewTimeOut = reviewTimeOut;
        this.appImageSrc = appImageSrc;
        this.reviewImageSrc = reviewImageSrc;
        this.personajes = personajes;
        this.idiomas = idiomas;
        this.exposicionIdiomas = exposicionIdiomas;
        this.preguntas = preguntas;
        this.niveles = niveles;
    }

    public Exposicion() {
    }

    /**********************
     * GETTTERS & SETTERS *
     **********************/
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getDescripcionExpo() {
        return descripcionExpo;
    }

    public void setDescripcionExpo(String descripcionExpo) {
        this.descripcionExpo = descripcionExpo;
    }

    public String getAdminUser() {
        return adminUser;
    }

    public void setAdminUser(String adminUser) {
        this.adminUser = adminUser;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public int getQuestionTimeOut() {
        return questionTimeOut;
    }

    public void setQuestionTimeOut(int questionTimeOut) {
        this.questionTimeOut = questionTimeOut;
    }

    public int getAnswerTimeOut() {
        return answerTimeOut;
    }

    public void setAnswerTimeOut(int answerTimeOut) {
        this.answerTimeOut = answerTimeOut;
    }

    public int getReviewTimeOut() {
        return reviewTimeOut;
    }

    public void setReviewTimeOut(int reviewTimeOut) {
        this.reviewTimeOut = reviewTimeOut;
    }

    public String getAppImageSrc() {
        return appImageSrc;
    }

    public void setAppImageSrc(String appImageSrc) {
        this.appImageSrc = appImageSrc;
    }

    public String getReviewImageSrc() {
        return reviewImageSrc;
    }

    public void setReviewImageSrc(String reviewImageSrc) {
        this.reviewImageSrc = reviewImageSrc;
    }

    public List<Personaje> getPersonajes() {
        return personajes;
    }

    public void setPersonajes(List<Personaje> personajes) {
        this.personajes = personajes;
    }

    public List<Idioma> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<Idioma> idiomas) {
        this.idiomas = idiomas;
    }

    public Map<Idioma, ExposicionIdioma> getExposicionIdiomas() {
        return exposicionIdiomas;
    }

    public void setExposicionIdiomas(Map<Idioma, ExposicionIdioma> exposicionIdiomas) {
        this.exposicionIdiomas = exposicionIdiomas;
    }

    public Map<Nivel, List<Pregunta>> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(Map<Nivel, List<Pregunta>> preguntas) {
        this.preguntas = preguntas;
    }

    public List<Nivel> getNiveles() {
        return niveles;
    }

    public void setNiveles(List<Nivel> niveles) {
        this.niveles = niveles;
    }

}
