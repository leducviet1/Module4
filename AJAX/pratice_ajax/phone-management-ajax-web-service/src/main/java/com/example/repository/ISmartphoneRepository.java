package com.example.repository;

import com.example.model.Smartphone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ISmartphoneRepository extends JpaRepository<Smartphone,Long> {

}

