package com.ljk.boot.service;

import com.ljk.boot.dto.UserDTO;
import com.ljk.boot.entity.User;

import java.util.List;

public interface UserService {
    UserDTO addUser(UserDTO userDto);
    List<User> getUsers();
    User getUserById(int id);
    UserDTO updateUser(int id, UserDTO user);
    void deleteUser(int id);

    List<User> searchUsers(String keyword,int limit, int offset);

    UserDTO updateCredential(int id, String name, String password);

    UserDTO updatePartial(int id, String address, String age);
}
