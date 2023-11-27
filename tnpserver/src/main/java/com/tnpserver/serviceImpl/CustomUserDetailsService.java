package com.tnpserver.serviceImpl;

import com.tnpserver.entity.User;
import com.tnpserver.helper.UserHelper;
import com.tnpserver.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                UserHelper.getAuthorities(List.of(user.getRole().getRole()))
        );
    }

}
