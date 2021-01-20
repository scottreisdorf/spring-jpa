package com.workday.jpa.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.workday.model.Data;
import org.springframework.core.annotation.AnnotationUtils;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.Transient;
import javax.persistence.Version;
import java.util.Base64;

/**
 * Base class for all EncryptedEntity objects
 * The `T Data` class is the data that will be encrypted
 * @param <T>
 */
@MappedSuperclass
public abstract class AbstractEncryptedEntity<T extends Data> implements EncryptedEntity {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "data")
    private String data;

    @Version
    private Integer version;

    @Transient
    T decryptedData;

    public AbstractEncryptedEntity(T data){
        this.decryptedData = data;
        this.id = data.getId();
    }
    public AbstractEncryptedEntity(){

    }

    @Override
    public Class<T> getDataClass() {
        EntityData entityData =  AnnotationUtils.findAnnotation(this.getClass(), EntityData.class);
        return entityData.entityClass();
    }


    /**
     * TODO replace with Encryption algo.. for now it is just Base 64 string
     */
    public void encrypt(){
        try {
            //convert to JSON and then get the bytes
           ObjectMapper mapper = new ObjectMapper();
           String json = mapper.writeValueAsString(decryptedData);
           String encodedString = Base64.getEncoder().encodeToString(json.getBytes());
           this.data = encodedString;
        }catch (Exception e){
            throw new RuntimeException("Error encrypting data",e);
        }
    }

    /**
     * TODO replace with Decryption algo.. for now it is just Base 64 string
     */
    @PostLoad
    @PostUpdate
    @PostPersist
    public void decrypt(){
        try {
        byte[] data = Base64.getDecoder().decode(this.data);
        String json = new String(data);
        ObjectMapper mapper = new ObjectMapper();
        decryptedData =  mapper.readValue(json,getDataClass());
        }catch (Exception e){
            throw new RuntimeException("Error decrypting data",e);
        }
    }

    public T getDecryptedData() {
        return decryptedData;
    }

}
