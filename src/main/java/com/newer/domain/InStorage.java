package com.newer.domain;

import java.io.Serializable;
import java.sql.Timestamp;


public class InStorage implements Serializable {

    private Integer rkId;
    private Integer cid;
    private Integer moderId;
    private String stall;
    private Timestamp rkTime;
    private String czUser;
    private Mold mold;

    public Mold getMold() {
        return mold;
    }

    public void setMold(Mold mold) {
        this.mold = mold;
    }

    public Integer getRkId() {
        return rkId;
    }

    public void setRkId(Integer rkId) {
        this.rkId = rkId;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getModerId() {
        return moderId;
    }

    public void setModerId(Integer moderId) {
        this.moderId = moderId;
    }

    public String getStall() {
        return stall;
    }

    public void setStall(String stall) {
        this.stall = stall;
    }

    public Timestamp getRkTime() {
        return rkTime;
    }

    public void setRkTime(Timestamp rkTime) {
        this.rkTime = rkTime;
    }

    public String getCzUser() {
        return czUser;
    }

    public void setCzUser(String czUser) {
        this.czUser = czUser;
    }
}
