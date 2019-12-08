package com.xpomanager.models;

import com.google.gson.annotations.SerializedName;

public class ExposicionIdioma {

    /*************
     * ATRIBUTOS *
     *************/
    @SerializedName("LangId")
    private int id;
    @SerializedName("StartEnterId")
    private String startEnterId;
    @SerializedName("StartPlayButton")
    private String startPlayButton;
    @SerializedName("StartExposition")
    private String startExposition;
    @SerializedName("StartExpoTitle")
    private String startExpoTitle;
    @SerializedName("StartExpoIntro")
    private String startExpoIntro;
    @SerializedName("StartExpoDateFrom")
    private String startExpoDateFrom;
    @SerializedName("StartExpoDateTo")
    private String startExpoDateTo;
    @SerializedName("StartExpoURL")
    private String startExpoURL;
    @SerializedName("Question")
    private String question;
    @SerializedName("Hits")
    private String hits;
    @SerializedName("QstnTXT1")
    private String qstnTXT1;
    @SerializedName("QstnTXT2")
    private String qstnTXT2;
    @SerializedName("AnswerOK")
    private String answerOK;
    @SerializedName("AnswerKO")
    private String answerKO;
    @SerializedName("NextButton")
    private String nextButton;
    @SerializedName("SummCongrats")
    private String summCongrats;
    @SerializedName("SummGameText")
    private String summGameText;
    @SerializedName("SummComment")
    private String summComment;
    @SerializedName("SummBye")
    private String summBye;
    @SerializedName("SummURLText")
    private String summURLText;
    @SerializedName("SummURL")
    private String summURL;

    /*****************
     * CONSTRUCTORES *
     *****************/
    public ExposicionIdioma(int id, String startEnterId, String startPlayButton,
                            String startExposition, String startExpoTitle, String startExpoIntro,
                            String startExpoDateFrom, String startExpoDateTo, String startExpoURL,
                            String question, String hits, String qstnTXT1, String qstnTXT2,
                            String answerOK, String answerKO, String nextButton, String summCongrats,
                            String summGameText, String summComment, String summBye,
                            String summURLText, String summURL) {
        this.id = id;
        this.startEnterId = startEnterId;
        this.startPlayButton = startPlayButton;
        this.startExposition = startExposition;
        this.startExpoTitle = startExpoTitle;
        this.startExpoIntro = startExpoIntro;
        this.startExpoDateFrom = startExpoDateFrom;
        this.startExpoDateTo = startExpoDateTo;
        this.startExpoURL = startExpoURL;
        this.question = question;
        this.hits = hits;
        this.qstnTXT1 = qstnTXT1;
        this.qstnTXT2 = qstnTXT2;
        this.answerOK = answerOK;
        this.answerKO = answerKO;
        this.nextButton = nextButton;
        this.summCongrats = summCongrats;
        this.summGameText = summGameText;
        this.summComment = summComment;
        this.summBye = summBye;
        this.summURLText = summURLText;
        this.summURL = summURL;
    }

    public ExposicionIdioma() {
    }

    /*********************
     * GETTERS & SETTERS *
     *********************/
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStartEnterId() {
        return startEnterId;
    }

    public void setStartEnterId(String startEnterId) {
        this.startEnterId = startEnterId;
    }

    public String getStartPlayButton() {
        return startPlayButton;
    }

    public void setStartPlayButton(String startPlayButton) {
        this.startPlayButton = startPlayButton;
    }

    public String getStartExposition() {
        return startExposition;
    }

    public void setStartExposition(String startExposition) {
        this.startExposition = startExposition;
    }

    public String getStartExpoTitle() {
        return startExpoTitle;
    }

    public void setStartExpoTitle(String startExpoTitle) {
        this.startExpoTitle = startExpoTitle;
    }

    public String getStartExpoIntro() {
        return startExpoIntro;
    }

    public void setStartExpoIntro(String startExpoIntro) {
        this.startExpoIntro = startExpoIntro;
    }

    public String getStartExpoDateFrom() {
        return startExpoDateFrom;
    }

    public void setStartExpoDateFrom(String startExpoDateFrom) {
        this.startExpoDateFrom = startExpoDateFrom;
    }

    public String getStartExpoDateTo() {
        return startExpoDateTo;
    }

    public void setStartExpoDateTo(String startExpoDateTo) {
        this.startExpoDateTo = startExpoDateTo;
    }

    public String getStartExpoURL() {
        return startExpoURL;
    }

    public void setStartExpoURL(String startExpoURL) {
        this.startExpoURL = startExpoURL;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getHits() {
        return hits;
    }

    public void setHits(String hits) {
        this.hits = hits;
    }

    public String getQstnTXT1() {
        return qstnTXT1;
    }

    public void setQstnTXT1(String qstnTXT1) {
        this.qstnTXT1 = qstnTXT1;
    }

    public String getQstnTXT2() {
        return qstnTXT2;
    }

    public void setQstnTXT2(String qstnTXT2) {
        this.qstnTXT2 = qstnTXT2;
    }

    public String getAnswerOK() {
        return answerOK;
    }

    public void setAnswerOK(String answerOK) {
        this.answerOK = answerOK;
    }

    public String getAnswerKO() {
        return answerKO;
    }

    public void setAnswerKO(String answerKO) {
        this.answerKO = answerKO;
    }

    public String getNextButton() {
        return nextButton;
    }

    public void setNextButton(String nextButton) {
        this.nextButton = nextButton;
    }

    public String getSummCongrats() {
        return summCongrats;
    }

    public void setSummCongrats(String summCongrats) {
        this.summCongrats = summCongrats;
    }

    public String getSummGameText() {
        return summGameText;
    }

    public void setSummGameText(String summGameText) {
        this.summGameText = summGameText;
    }

    public String getSummComment() {
        return summComment;
    }

    public void setSummComment(String summComment) {
        this.summComment = summComment;
    }

    public String getSummBye() {
        return summBye;
    }

    public void setSummBye(String summBye) {
        this.summBye = summBye;
    }

    public String getSummURLText() {
        return summURLText;
    }

    public void setSummURLText(String summURLText) {
        this.summURLText = summURLText;
    }

    public String getSummURL() {
        return summURL;
    }

    public void setSummURL(String summURL) {
        this.summURL = summURL;
    }
}
