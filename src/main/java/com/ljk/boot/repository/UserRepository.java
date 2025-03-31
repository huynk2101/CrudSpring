package com.ljk.boot.repository;

import com.ljk.boot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    @Query("SELECT u FROM User u WHERE u.name LIKE %:keyword% ORDER BY u.id limit :limit offset :offset")
    List<User> searchUsers(String keyword,int limit,int offset);
    @Query("SELECT u FROM User u ORDER BY u.id limit :limit offset :offset")
    List<User> getAllUsers(int limit,int offset);
}
