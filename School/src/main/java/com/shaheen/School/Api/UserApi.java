package com.shaheen.School.Api;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.shaheen.School.Model.User.User;
import com.shaheen.School.Service.User.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author lts
 */
@RestController
@RequestMapping(value = "/api/user")
public class UserApi {

    @Autowired
    UserService userService;

    @GetMapping(value = "/getAll")
    public List<User> getAll() {
        return userService.findAll();
    }

    @GetMapping(value = "/{id}")
    public User getOne(@PathVariable("id") Long id) {
        return userService.findById(id);
    }

    @PostMapping("/save")
    public ResponseEntity saveUser(@RequestBody User user) {
 
        return new ResponseEntity<>(userService.save(user),HttpStatus.CREATED);
    }
    @DeleteMapping(value = "/{id}")
    public boolean deleteUser(@PathVariable("id") Long id) {
//        System.out.println(item.toString());
        return userService.delete(id);
    }
}
