package com.workday.jpa.model;

import com.workday.model.Application;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "application", catalog = "test")
@EntityData(entityClass = Application.class)
public class EncryptedApplication extends AbstractEncryptedEntity<Application> {

    public EncryptedApplication(Application data) {
        super(data);
    }

    public EncryptedApplication(){}

}
