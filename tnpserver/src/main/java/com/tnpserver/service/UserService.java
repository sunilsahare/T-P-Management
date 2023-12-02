package com.tnpserver.service;

import com.tnpserver.exception.BusinessException;
import com.tnpserver.pojo.JwtRequest;
import com.tnpserver.pojo.JwtResponse;
import com.tnpserver.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    public User addUser(User user) throws BusinessException;
    public User updateUser(User user) throws BusinessException;

    void updateUserAccountStatus(Long userId, boolean currentStatus, boolean statusToBeUpdated) throws BusinessException;

    public void removeUser(Long[] userId) throws BusinessException;
    public Page<com.tnpserver.entity.User> getAllUser(Pageable pageable);
    Page<com.tnpserver.entity.User> getAllUserByRole(String userRole, Pageable pageable);
    public User getUser(Long userId) throws BusinessException;

    com.tnpserver.entity.User getUserById(Long userId) throws BusinessException;

    public com.tnpserver.entity.User getUserByUsername(String username) throws BusinessException;
    public Object getUserByEmail(String email) throws BusinessException;
    void isEmailExists(String email) throws BusinessException;
    void isUsernameExists(String username) throws BusinessException;
}
