package com.workday.model;

import java.util.Date;

/**
 * Application Data
 */
public class Application implements Data {
    private String id;
    private String appName;
    private Date createDate;

    public Application() {
        this.createDate = new Date();

    }

    public Application(String id, String appName) {
        this();
        this.id = id;
        this.appName = appName;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
