package com.afrosimova.prmanager.repositories;

import com.afrosimova.prmanager.entities.User;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from USER u " +
            "where lower(u.loginUser) = lower(:loginUser)")
    List<User> findUser(@Param("loginUser") String loginUser);
}