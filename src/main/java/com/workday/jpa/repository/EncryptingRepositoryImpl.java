package com.workday.jpa.repository;

import com.workday.jpa.model.EncryptedEntity;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.Serializable;

public class EncryptingRepositoryImpl<T extends EncryptedEntity, ID extends Serializable> extends SimpleJpaRepository<T,ID> implements EncryptingRepository<T,ID> {

    private final EntityManager entityManager;

    private JpaEntityInformation entityInfo;

    public EncryptingRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
        this.entityManager = entityManager;
        this.entityInfo = JpaEntityInformationSupport.getEntityInformation(domainClass, entityManager);

    }

    public EncryptingRepositoryImpl(JpaEntityInformation entityInformation,
                     EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityInfo = entityInformation;
        // Keep the EntityManager around to used from the newly introduced methods.
        this.entityManager = entityManager;
    }

    @Override
    public <S extends T> S save(S entity) {
       return saveEncrypted(entity);
    }

    @Transactional
    @Override
    public  <S extends T> S saveEncrypted(S entity) {
        entity.encrypt();
        if (entityInfo.isNew(entity)) {
            entityManager.persist(entity);
            return entity;
        } else {
            return entityManager.merge(entity);
        }
    }
}
