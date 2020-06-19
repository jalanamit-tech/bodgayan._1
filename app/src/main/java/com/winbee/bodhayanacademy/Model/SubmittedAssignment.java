package com.winbee.bodhayanacademy.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SubmittedAssignment implements Serializable {

@SerializedName("Assignment")
@Expose
private Boolean assignment;

private SubmittedDatum[] Assignment_Data ;

    public SubmittedAssignment(Boolean assignment, SubmittedDatum[] assignment_Data) {
        this.assignment = assignment;
        Assignment_Data = assignment_Data;
    }

    public Boolean getAssignment() {
return assignment;
}

public void setAssignment(Boolean assignment) {
this.assignment = assignment;
}

    public SubmittedDatum[] getData(){
        return Assignment_Data;
    }

}