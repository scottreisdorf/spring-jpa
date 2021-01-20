package com.workday.jpa.controller;

import com.workday.jpa.model.AbstractEncryptedEntity;
import com.workday.jpa.model.EncryptedUser;
import com.workday.jpa.repository.EncryptedUserRepository;
import com.workday.jpa.service.EncryptionService;
import com.workday.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/users")
public class UsersController {

    @Autowired
    EncryptedUserRepository repository;

    @GetMapping("/all")
    public List<User> getAll() {
        return EncryptionService.findAll(repository);
    }


    @GetMapping("/{email}")
    public List<User> getUser(@PathVariable("email") final String email) {
        return repository.findByEmail(email)
                .stream()
                .map(AbstractEncryptedEntity::getDecryptedData)
                .collect(Collectors.toList());
   }

    @GetMapping("/id/{id}")
    public User getId(@PathVariable("id") final String id) {
        return EncryptionService.findById(repository,id);
    }

    @GetMapping("/update/{id}/{name}")
    public User update(@PathVariable("id") final String id, @PathVariable("name") final String name) {
        EncryptedUser encryptedUser = repository.findById(id).get();
        encryptedUser.getDecryptedData().setFirstName(name);
        return repository.save(encryptedUser).getDecryptedData();
    }

    @GetMapping("/new/{id}/{name}")
    public User newUser(@PathVariable("id") final String id, @PathVariable("name") final String name) {
        User user = new User(id);
        String email = name.replaceAll(" ","")+id+"@gmail.com";
        user.setEmail(email);
        user.setFirstName(name);
        user.setAge(randomAge());
        EncryptedUser encryptedUser = new EncryptedUser(user,email);
        return repository.save(encryptedUser).getDecryptedData();
    }

    private Integer randomAge(){
        //random age between 10 and 80
        Random random = new Random();
        return random.nextInt(80 - 10 + 1) + 10;
    }
}
