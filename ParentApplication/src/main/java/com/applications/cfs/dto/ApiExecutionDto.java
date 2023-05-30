package com.applications.cfs.dto;

import java.io.Serializable;

public class ApiExecutionDto implements Serializable {
    private String nameofFeature;

    private String body;

    public String getNameofFeature() {
        return nameofFeature;
    }

    public void setNameofFeature(String nameofFeature) {
        this.nameofFeature = nameofFeature;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "ApiExecutionDto [nameofFeature=" + nameofFeature + ", body=" + body + "]";
    }

}
