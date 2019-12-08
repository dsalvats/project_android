package com.xpomanager.models;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Exposicion {

    /*************
     * ATRIBUTOS *
     *************/
    @SerializedName("Id")
    private int id;
    @SerializedName("Nombre")
    private String nombre;
    @SerializedName("Activo")
    private boolean activo;
    @SerializedName("FechaInicio")
    private String fechaInicio;
    @SerializedName("FechaFin")
    private String fechaFin;
    @SerializedName("DescripcionExpo")
    private String descripcionExpo;
    @SerializedName("AdminUser")
    private String adminUser;
    @SerializedName("AdminPassowrd")
    private String adminPassword;
    @SerializedName("QuestionTimeOut")
    private int questionTimeOut;
    @SerializedName("AnswerTimeOut")
    private int answerTimeOut;
    @SerializedName("ReviewTimeOut")
    private int reviewTimeOut;
    @SerializedName("AppImageSrc")
    private String appImageSrc;
    @SerializedName("ReviewImageSrc")
    private String reviewImageSrc;
    @SerializedName("Personaje")
    private List<Personaje> personajes;
    @SerializedName("Idiomas")
    private List<Idioma> idiomas;
    @SerializedName("ExposicionIdiomas")
    private List<IdiomaExposicionIdioma> idiomaExposicionIdiomas;
    private Map<Idioma, ExposicionIdioma> exposicionIdiomas;
    @SerializedName("Preguntas")
    private List<NivelPreguntas> nivelPreguntas;
    private HashMap<Nivel, List<Pregunta>> preguntas;
    @SerializedName("Niveles")
    private List<Nivel> niveles;

    /*****************
     * CONSTRUCTORES *
     *****************/
    public Exposicion(int id, String nombre, boolean activo, String fechaInicio, String fechaFin,
                      String descripcionExpo, String adminUser, String adminPassword,
                      int questionTimeOut, int answerTimeOut, int reviewTimeOut, String appImageSrc,
                      String reviewImageSrc, List<Personaje> personajes, List<Idioma> idiomas,
                      List<IdiomaExposicionIdioma> idiomaExposicionIdiomas,
                      Map<Idioma, ExposicionIdioma> exposicionIdiomas,
                      List<NivelPreguntas> nivelPreguntas, List<Nivel> niveles) {
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
        this.idiomaExposicionIdiomas = idiomaExposicionIdiomas;
        this.exposicionIdiomas = exposicionIdiomas;
        this.nivelPreguntas = nivelPreguntas;
        this.preguntas = null;
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

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
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

    public List<IdiomaExposicionIdioma> getIdiomaExposicionIdiomas() {
        return idiomaExposicionIdiomas;
    }

    public void setIdiomaExposicionIdiomas(List<IdiomaExposicionIdioma> idiomaExposicionIdiomas) {
        this.idiomaExposicionIdiomas = idiomaExposicionIdiomas;
    }

    public void setExposicionIdiomas(Map<Idioma, ExposicionIdioma> exposicionIdiomas) {
        this.exposicionIdiomas = exposicionIdiomas;
    }

    public List<NivelPreguntas> getNivelPreguntas() {
        return nivelPreguntas;
    }

    public void setNivelPreguntas(List<NivelPreguntas> nivelPreguntas) {
        this.nivelPreguntas = nivelPreguntas;
    }

    public List<Nivel> getNiveles() {
        return niveles;
    }

    public void setNiveles(List<Nivel> niveles) {
        this.niveles = niveles;
    }

    public HashMap<Nivel, List<Pregunta>> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(HashMap<Nivel, List<Pregunta>> preguntas) {
        this.preguntas = preguntas;
    }
}
