package com.winbee.bodhayanacademy.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SIADDQuestionDataModel {
    @Expose
    @SerializedName("SectionCode")
    private String SectionCode;
    @Expose
    @SerializedName("QuestionID")
    private String QuestionID;
    @Expose
    @SerializedName("QuestionTitle")
    private String QuestionTitle;
    @Expose
    @SerializedName("Option1")
    private String Option1;
    @Expose
    @SerializedName("Option2")
    private String Option2;
    @Expose
    @SerializedName("Option3")
    private String Option3;
    @Expose
    @SerializedName("Option4")
    private String Option4;
    @Expose
    @SerializedName("QuestionGUID")
    private String QuestionGUID;

    public SIADDQuestionDataModel(String sectionCode, String questionID, String questionTitle, String option1, String option2, String option3, String option4, String questionGUID) {
        SectionCode = sectionCode;
        QuestionID = questionID;
        QuestionTitle = questionTitle;
        Option1 = option1;
        Option2 = option2;
        Option3 = option3;
        Option4 = option4;
        QuestionGUID = questionGUID;
    }

    public String getSectionCode() {
        return SectionCode;
    }

    public String getQuestionID() {
        return QuestionID;
    }

    public String getQuestionTitle() {
        return QuestionTitle;
    }

    public String getOption1() {
        return Option1;
    }

    public String getOption2() {
        return Option2;
    }

    public String getOption3() {
        return Option3;
    }

    public String getOption4() {
        return Option4;
    }

    public String getQuestionGUID() {
        return QuestionGUID;
    }
}
