package com.ljk.boot.controller;


import com.ljk.boot.dto.UserDTO;
import com.ljk.boot.entity.User;
import com.ljk.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody UserDTO userDto){
        UserDTO user1 =  userService.addUser(userDto);
        if (user1 == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(user1,HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getUsers(){
        List<User> users = userService.getUsers();
        if (users == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(UserDTO.toDto(users),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable int id){
        User user = userService.getUserById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(UserDTO.toDto(user),HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id,@RequestBody UserDTO user){
        UserDTO user1 = userService.updateUser(id,user);
        return new ResponseEntity<>(user1,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id){
        userService.deleteUser(id);
        return new ResponseEntity<>("deleted success",HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserDTO>> searchUsers(String keyword,int page, int limit){
        int offset = (page-1)*limit;
        List<User> products = userService.searchUsers(keyword,limit,offset);
        return new ResponseEntity<>(UserDTO.toDto(products),HttpStatus.OK);
    }
    @PatchMapping("/update-credential/{id}")
    public ResponseEntity<UserDTO> updateCredential(@PathVariable int id,String name,String password){
        UserDTO userDTO = userService.updateCredential(id,name,password);
        return new ResponseEntity<>(userDTO,HttpStatus.OK);
    }
    @PatchMapping("/update-partial/{id}")
    public ResponseEntity<UserDTO> updatePartial(@PathVariable int id,String address,String age){
        UserDTO userDTO = userService.updatePartial(id,address,age);
        return new ResponseEntity<>(userDTO,HttpStatus.OK);
    }

}
