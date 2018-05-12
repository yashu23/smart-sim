package com.lucky5.smartsim.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * author : Yashpal_Rawat
 * lastModifiedDate : 4/05/2018 4:26 PM
 */
@Entity
public class Response {

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Scenario getScenario() {
        return scenario;
    }

    public void setScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    public String getResponseString() {
        return responseString;
    }

    public void setResponseString(String responseString) {
        this.responseString = responseString;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getHeaders() {
        return headers;
    }

    public void setHeaders(String headers) {
        this.headers = headers;
    }

    public String getHttpResponseCode() {
        return httpResponseCode;
    }

    public void setHttpResponseCode(String httpResponseCode) {
        this.httpResponseCode = httpResponseCode;
    }

    public Response() {}

    @Id
    private long id;

    @ManyToOne
    private Scenario scenario;

    private String responseString;
    private String contentType;
    private String headers;
    private String httpResponseCode;

    @ManyToOne
    private Rule rule;
}
