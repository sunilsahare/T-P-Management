package com.tnpserver.serviceImpl;

import com.tnpserver.constants.ErrorCode;
import com.tnpserver.constants.RoleEnum;
import com.tnpserver.exception.BusinessException;
import com.tnpserver.exception.ErrorResponse;
import com.tnpserver.helper.UserHelper;
import com.tnpserver.pojo.User;
import com.tnpserver.repo.UserRepository;
import com.tnpserver.service.UserService;
import com.tnpserver.util.EncryptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    private final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private PasswordEncoder encoder;

    @Override
    public User addUser(User user) throws BusinessException {
        com.tnpserver.entity.User userEntity = UserHelper.mapPojoToEntity(user);
        userEntity.setActive(false);
        userEntity.setRole(RoleEnum.fromString(EncryptionUtil.decrypt(user.getRole())));
        userEntity.setPassword(encoder.encode(EncryptionUtil.decrypt(user.getPassword())));
        userEntity.setUsername(EncryptionUtil.decrypt(user.getUsername()));
        isUsernameExists(EncryptionUtil.decrypt(user.getUsername()));
        isEmailExists(user.getEmail());
        com.tnpserver.entity.User savedUser = userRepo.save(userEntity);
        User userPojo = UserHelper.mapEntityToPojo(savedUser);
        LOG.info("Saved User - {} ", userPojo);
        return userPojo;
    }

    @Override
    public User updateUser(User user) throws BusinessException {
        com.tnpserver.entity.User userEntity = UserHelper.mapPojoToEntity(user);
        userEntity.setPassword(encoder.encode(user.getPassword()));
        isUsernameExists(user.getUsername());
        isEmailExists(user.getEmail());
        com.tnpserver.entity.User savedUser = userRepo.save(userEntity);
        User userPojo = UserHelper.mapEntityToPojo(savedUser);
        LOG.info("Updated User - {} ", userPojo);
        return userPojo;
    }

    @Override
    public void removeUser(Long[] userId) throws BusinessException {
        LOG.info("Removing User - {} ", userId);
        List<com.tnpserver.entity.User> userList = userRepo.findAllById(List.of(userId));
        List<Long> invalidIdList = new ArrayList<>();

        List.of(userId).forEach(id -> {
            com.tnpserver.entity.User matchUser = userList.stream().filter(user -> id == user.getUserId()).findAny().orElse(null);
            if (matchUser==null) {
                invalidIdList.add(id);
            }
        });

        if (!invalidIdList.isEmpty()) {
            throw new BusinessException(ErrorResponse.builder()
                    .message("Invalid UserId - "+invalidIdList)
                    .reason("FAILURE")
                    .timestamp(LocalDateTime.now()).build());
        }

        userRepo.deleteAllById(List.of(userId));

    }

    @Override
    public Page<com.tnpserver.entity.User> getAllUser(Pageable pageable) {
        Page<com.tnpserver.entity.User> userPage = userRepo.findAll(pageable);
        List<User> pojoList = UserHelper.mapEntityListToPojoList(userPage.getContent());
        LOG.info("Fetching All User - {} ", pojoList);
        // TODO: Need to return data in pojo
        return userPage;
    }

    @Override
    public Page<com.tnpserver.entity.User> getAllUserByRole(String userRole, Pageable pageable) {
        Page<com.tnpserver.entity.User> userPage = userRepo.findAllByRoleAndIsActive(userRole,true, pageable);
        List<User> pojoList = UserHelper.mapEntityListToPojoList(userPage.getContent());
        LOG.info("Fetching All User - {} ", pojoList);
        // TODO: Need to return data in pojo
        return userPage;
    }

    @Override
    public User getUser(Long userId) throws BusinessException {
        com.tnpserver.entity.User user = userRepo.findById(userId).orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND.getError()));
        User userPojo = UserHelper.mapEntityToPojo(user);
        LOG.info("Fetch User - {} ", userPojo);
        return userPojo;
    }

    @Override
    public com.tnpserver.entity.User getUserByUsername(String username) {
        return userRepo.findByUsername(username).orElse(null);
    }

    @Override
    public com.tnpserver.entity.User getUserByEmail(String email) {
        return userRepo.findByEmail(email).orElse(null);
    }

    @Override
    public void isEmailExists(String email) throws BusinessException {
        if (getUserByEmail(email)!=null) {
            throw new BusinessException(ErrorCode.EMAIL_EXISTS.getError());
        }
    }

    @Override
    public void isUsernameExists(String username) throws BusinessException {
        if (getUserByUsername(username)!=null) {
            throw new BusinessException(ErrorCode.USERNAME_EXISTS.getError());
        }
    }


}
