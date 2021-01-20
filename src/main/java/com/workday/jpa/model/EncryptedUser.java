package com.workday.jpa.model;

import com.workday.model.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user", catalog = "test")
@EntityData(entityClass = User.class)
public class EncryptedUser extends AbstractEncryptedEntity<User> {

    @Column(name = "email")
    private String email;

    public EncryptedUser(User data, String email) {
        super(data);
        this.email = email;
    }

    public EncryptedUser(){

    }

    public EncryptedUser(String email) {
        this.email = email;
    }


}
