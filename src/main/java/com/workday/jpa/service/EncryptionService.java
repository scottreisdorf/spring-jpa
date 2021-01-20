package com.workday.jpa.service;

import com.workday.jpa.model.EncryptedEntity;
import com.workday.jpa.repository.EncryptingRepository;
import com.workday.model.Data;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service for common repo functions...
 * TODO make a SpringFactory rather than static methods
 */
public class EncryptionService {

    public static <T extends Data,E extends EncryptedEntity<T>,I extends Serializable> List<T> findAll(EncryptingRepository<E,I> repository) {
        return StreamSupport.stream(
                repository.findAll().spliterator(), false)
                .map(EncryptedEntity::getDecryptedData)
                .collect(Collectors.toList());
    }

    public static <T extends Data,E extends EncryptedEntity<T>,I extends Serializable> T findById(EncryptingRepository<E,I> repository, I id){
        return repository.findById(id).get().getDecryptedData();
    }
}
