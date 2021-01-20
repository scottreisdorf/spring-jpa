package com.workday.jpa.repository;

import com.workday.jpa.model.EncryptedEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface EncryptingRepository<T extends EncryptedEntity, ID extends Serializable> extends JpaRepository<T, ID> {

    <S extends T> S saveEncrypted(S entity);

    <S extends T> S save(S entity);
}