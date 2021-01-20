package com.workday.jpa.controller;

import com.workday.jpa.model.EncryptedApplication;
import com.workday.jpa.repository.EncryptedApplicationRepository;
import com.workday.jpa.service.EncryptionService;
import com.workday.model.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/apps")
public class ApplicationController {

    @Autowired
    EncryptedApplicationRepository repository;

    @GetMapping("/all")
    public List<Application> getAll() {
        return EncryptionService.findAll(repository);
    }

    @GetMapping("/id/{id}")
    public Application getId(@PathVariable("id") final String id) {
        return EncryptionService.findById(repository,id);
    }

    @GetMapping("/update/{id}/{name}")
    public Application update(@PathVariable("id") final String id, @PathVariable("name") final String name) {
        EncryptedApplication application = repository.findById(id).get();
        application.getDecryptedData().setAppName(name);
        return repository.save(application).getDecryptedData();
    }

    @GetMapping("/new/{id}/{name}")
    public Application newApplication(@PathVariable("id") final String id, @PathVariable("name") final String name) {
        Application app = new Application(id,name);
        EncryptedApplication encryptedApplication = new EncryptedApplication(app);
        return repository.save(encryptedApplication).getDecryptedData();
    }
}
