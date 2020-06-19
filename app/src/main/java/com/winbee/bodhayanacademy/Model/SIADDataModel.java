package com.winbee.bodhayanacademy.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SIADDataModel {
    private SIADDQuestionDataModel[] QuestionData;
    private SIADDQuestionSectionModel[] QuestionSection;

    public SIADDataModel( SIADDQuestionDataModel[] questionData, SIADDQuestionSectionModel[] questionSection) {
        QuestionData = questionData;
        QuestionSection = questionSection;
    }

    public SIADDQuestionDataModel[] getQuestionData() {
        return QuestionData;
    }

    public SIADDQuestionSectionModel[] getQuestionSection() {
        return QuestionSection;
    }
}
