package com.ljk.boot.controller;


import com.ljk.boot.dto.ApiResponseDTO;
import com.ljk.boot.dto.InfoDTO;
import com.ljk.boot.dto.UserDTO;
import com.ljk.boot.entity.User;
import com.ljk.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<ApiResponseDTO> getUsers(int page,int limit){
        int offset = (page-1)*limit;
        List<User> users = userService.getUsers(limit,offset);
        if (users == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<UserDTO> userDTOS = UserDTO.toDto(users);
        int totalPage = Math.ceilDiv(userService.getTotalPage(), limit);


        return new ResponseEntity<>(ApiResponseDTO.toDTO(userDTOS,totalPage,"200",page,limit),HttpStatus.OK);
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
    public ResponseEntity<ApiResponseDTO> searchUsers(String keyword,int page, int limit){
        int offset = (page-1)*limit;
        List<User> users = userService.searchUsers(keyword,limit,offset);
        List<UserDTO> userDTOS = UserDTO.toDto(users);
        int totalPage = Math.ceilDiv(userService.getTotalPage(), limit);
        return new ResponseEntity<>(ApiResponseDTO.toDTO(userDTOS,totalPage,"200",page,limit),HttpStatus.OK);
    }
    @PatchMapping("/update-credential/{id}")
    public ResponseEntity<UserDTO> updateCredential(@PathVariable int id,@RequestBody InfoDTO info){
        InfoDTO infoDTO = info;
        String name = infoDTO.getName();
        String password = infoDTO.getPassword();
        UserDTO userDTO = userService.updateCredential(id,name,password);
        return new ResponseEntity<>(userDTO,HttpStatus.OK);
    }
    @PatchMapping("/update-partial/{id}")
    public ResponseEntity<UserDTO> updatePartial(@PathVariable int id,String address,String age){
        UserDTO userDTO = userService.updatePartial(id,address,age);
        return new ResponseEntity<>(userDTO,HttpStatus.OK);
    }


}
