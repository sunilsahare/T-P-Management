package com.tnpserver.service;

import com.tnpserver.exception.BusinessException;
import com.tnpserver.pojo.JwtRequest;
import com.tnpserver.pojo.JwtResponse;

public interface AuthenticationService {
    public JwtResponse authenticate(JwtRequest request) throws BusinessException;
}
