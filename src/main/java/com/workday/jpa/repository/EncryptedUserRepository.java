package com.workday.jpa.repository;

import com.workday.jpa.model.EncryptedUser;

import java.util.List;

public interface EncryptedUserRepository extends EncryptingRepository<EncryptedUser,String> {

    //implemented in Custom Repo
    EncryptedUser save(EncryptedUser entity);

    List<EncryptedUser> findByEmail(String email);
}
