package com.example.casestudy.service;

import com.example.casestudy.model.UserPrincipal;
import com.example.casestudy.model.Users;
import com.example.casestudy.repo.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private IUserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByUsername(username);
        if(user == null) {
            System.out.println(username + " not found");
            throw new UsernameNotFoundException(username + " not found");
        }
        return UserPrincipal.build(user);
    }
}
