package com.flockit.examen.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flockit.examen.model.User;

public interface UsersRepository extends JpaRepository<User,Long> {

    public User findByEmailAndPassword(String email, String password);
}