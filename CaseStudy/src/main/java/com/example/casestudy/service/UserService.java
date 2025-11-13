package com.example.casestudy.service;

import com.example.casestudy.model.Role;
import com.example.casestudy.model.Users;
import com.example.casestudy.repo.IRoleRepository;
import com.example.casestudy.repo.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {
    @Autowired
    private IUserRepository iUserRepository;
    @Autowired
    private IRoleRepository iRoleRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    public Users register(Users user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Role userRole = iRoleRepository.findByName("ROLE_USER");
        user.setRoles(Set.of(userRole));
        return iUserRepository.save(user);
    }
}
