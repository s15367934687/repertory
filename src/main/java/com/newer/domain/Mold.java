package com.newer.domain;

import java.io.Serializable;

public class Mold implements Serializable {

    private int moderId;
    private String brand;
    private String type;

    public int getModerId() {
        return moderId;
    }

    public void setModerId(int moderId) {
        this.moderId = moderId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
