package com.workday.jpa.repository;

import com.workday.jpa.model.EncryptedApplication;

public interface EncryptedApplicationRepository extends EncryptingRepository<EncryptedApplication,String> {

    EncryptedApplication save(EncryptedApplication entity);}
