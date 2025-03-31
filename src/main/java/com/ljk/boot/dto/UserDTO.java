package com.ljk.boot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ljk.boot.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDTO {
    private Integer id;
    private String name;
    private Integer age;
    private String address;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = encoder.encode(password);
    }

    public static UserDTO toDto(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setAddress(user.getAddress());
        userDTO.setAge(user.getAge());
        return userDTO;
    }
    public static List<UserDTO> toDto(List<User> users) {
        return users.stream().map(user -> toDto(user)).toList();
    }

    public static User toEntity(UserDTO userDTO){
        User userEntity = new User();
        userEntity.setId(userDTO.getId());
        userEntity.setName(userDTO.getName());
        userEntity.setAddress(userDTO.getAddress());
        userEntity.setAge(userDTO.getAge());
        userEntity.setPassword(userDTO.getPassword());
        return userEntity;
    }
}
