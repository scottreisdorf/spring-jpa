package com.workday.jpa.model;

import com.workday.model.Data;

public interface EncryptedEntity<T extends Data> {

    /**
     * return the decrypted data
     * @return
     */
    T getDecryptedData();

    /**
     * The Class that represents the decrypted data object
     * @return
     */
    Class<T> getDataClass();

    void encrypt();

    void decrypt();
}
