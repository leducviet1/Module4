package com.example.casestudy.repo;

import com.example.casestudy.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface IUserRepository extends JpaRepository<Users, Integer> {
    Users findByUsername(String username);
}
