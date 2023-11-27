package com.tnpserver.controllers;

import com.tnpserver.exception.BusinessException;
import com.tnpserver.exception.HttpApiResponse;
import com.tnpserver.pojo.JwtRequest;
import com.tnpserver.pojo.User;
import com.tnpserver.service.AuthenticationService;
import com.tnpserver.service.UserService;
import com.tnpserver.util.SessionUtil;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    @Qualifier(value = "userService")
    private UserService userService;
    @Autowired
    @Qualifier(value = "authService")
    private AuthenticationService authenticationService;

//    @Autowired
//    private SessionUtil sessionUtil;

    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/authenticate")
    public ResponseEntity<HttpApiResponse> authenticate(@Valid @RequestBody JwtRequest request) throws BusinessException {
        HttpApiResponse response = HttpApiResponse.builder()
                .message("User Successfully Authenticated")
                .status(HttpStatus.OK.name())
                .timestamp(LocalDateTime.now())
                .mapList(Map.of("responseData",authenticationService.authenticate(request)))
                .build();
        LOG.info("'{}' User successfully authenticated.",request.getUsername());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<HttpApiResponse> addUser(@RequestBody @Valid User user) throws BusinessException {
        HttpApiResponse response = HttpApiResponse.builder()
                .message("User Successfully registered.")
                .status(HttpStatus.OK.name())
                .timestamp(LocalDateTime.now())
                .mapList(Map.of("responseData",userService.addUser(user)))
                .build();
        LOG.info("'{}' User successfully registered.",user.getUsername());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/demo")
    public ResponseEntity<String> home() {
        LOG.info("Demo API called by {}",SessionUtil.getCurrentUsername());
        return ResponseEntity.ok("Demo API called by - "+ SessionUtil.getCurrentUsername());
    }

}