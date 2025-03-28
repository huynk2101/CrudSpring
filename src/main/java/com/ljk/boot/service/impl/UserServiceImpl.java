package com.ljk.boot.service.impl;

import com.ljk.boot.dto.UserDTO;
import com.ljk.boot.entity.User;
import com.ljk.boot.repository.UserRepository;
import com.ljk.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDTO addUser(UserDTO userDTO) {
        return UserDTO.toDto(userRepository.save(UserDTO.toEntity(userDTO)));
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public UserDTO updateUser(int id, UserDTO user) {
        userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        user.setId(id);
        return UserDTO.toDto(userRepository.save(UserDTO.toEntity(user)));
    }

    public void deleteUser(int id) {
        userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        userRepository.deleteById(id);
    }

    @Override
    public List<User> searchUsers(String keyword, int limit, int offset) {
        return userRepository.searchUsers(keyword, limit, offset);
    }

    @Override
    public UserDTO updateCredential(int id, String name, String password) {
        User user1 = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        UserDTO user = UserDTO.toDto(user1);
        if (name == null || name == "") {
            user.setName(user.getName());
        } else {
            user.setName(name);
        }
        if (password == null || name == "") {
            user.setName(user.getName());
        } else {
            user.setPassword(password);
        }

        return UserDTO.toDto(userRepository.save(UserDTO.toEntity(user)));
    }

    @Override
    public UserDTO updatePartial(int id, String address, String age) {
        User user1 = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        UserDTO user = UserDTO.toDto(user1);
        if (age != null && !age.isEmpty()) {
            if ( Integer.parseInt(age) > 0) {
                user.setAge(Integer.parseInt(age));
            }
        }
        if (address == null || address.isEmpty()) {
            user.setAddress(user.getAddress());
        }else {
            user.setAddress(address);
        }


        return UserDTO.toDto(userRepository.save(UserDTO.toEntity(user)));
    }
}
