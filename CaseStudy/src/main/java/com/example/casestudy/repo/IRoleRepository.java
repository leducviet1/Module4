package com.example.casestudy.repo;

import com.example.casestudy.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IRoleRepository extends JpaRepository<Role, Integer> {
    Role  findByName(String name);
}
