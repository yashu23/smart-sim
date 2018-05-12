package com.lucky5.smartsim.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * author : Yashpal_Rawat
 * lastModifiedDate : 4/05/2018 4:27 PM
 */
@Entity
public class Scenario {
    @Id
    @GeneratedValue
    private long id;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private String desc;
}
