package com.tnpserver.serviceImpl;

import com.tnpserver.exception.BusinessException;
import com.tnpserver.helper.UserHelper;
import com.tnpserver.pojo.JwtRequest;
import com.tnpserver.pojo.JwtResponse;
import com.tnpserver.repo.UserRepository;
import com.tnpserver.security.JwtHelper;
import com.tnpserver.service.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("authService")
public class AuthenticationServiceImpl extends UserServiceImpl implements AuthenticationService {

    private final Logger LOG = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private JwtHelper jwtHelper;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public JwtResponse authenticate(JwtRequest request) throws BusinessException {
        com.tnpserver.entity.User user = getUserByUsername(request.getUsername());
        if (user == null) {
            LOG.info("Invalid Username {}", request.getUsername());
            throw new BusinessException("Invalid Username or password.");
        }

        authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getUsername(),
                                request.getPassword()
                        )
                );
        org.springframework.security.core.userdetails.User userDetails = new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                UserHelper.getAuthorities(List.of(user.getRole().getRole()))
        );

        String jwtToken = jwtHelper.generateToken(userDetails);
        return JwtResponse.builder().jwtToken(jwtToken).build();
    }
}
