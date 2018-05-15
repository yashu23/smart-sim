package com.lucky5.smartsim.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * author : Yashpal_Rawat
 * lastModifiedDate : 4/05/2018 8:21 PM
 */
@Entity
public class Rule {

    @Id
    @GeneratedValue
    private long id;

    private String requestPattern;

    private String keyPattern;

    public Rule() { }

    public Rule(String requestPattern, String keyPattern) {
        this.requestPattern = requestPattern;
        this.keyPattern = keyPattern;
    }

    public String getRequestPattern() {
        return requestPattern;
    }

    public void setRequestPattern(String requestPattern) {
        this.requestPattern = requestPattern;
    }

    public String getKeyPattern() {
        return keyPattern;
    }

    public void setKeyPattern(String keyPattern) {
        this.keyPattern = keyPattern;
    }

    public long getId() { return this.id; }
}
