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
import com.tnpserver.util.SessionUtil;
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
import java.util.stream.Collectors;

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
        // UserID, Username Should not be updated
        com.tnpserver.entity.User existingUser = getUserById(user.getUserId());
        isEmailExists(user.getEmail());
        existingUser.setActive(user.isActive());
        existingUser.setAddress(user.getAddress());
        existingUser.setEmail(user.getEmail());
        existingUser.setGender(user.getGender());
        existingUser.setFullName(user.getFullName());
        existingUser.setMobile(user.getMobile());
        existingUser.setProfilePictureUrl(user.getProfilePictureUrl());

        com.tnpserver.entity.User savedUser = userRepo.save(existingUser);
        User userPojo = UserHelper.mapEntityToPojo(savedUser);
        LOG.info("Updated User - {} ", userPojo);
        return userPojo;
    }

    @Override
    public void updateUserAccountStatus(Long userId, boolean currentStatus, boolean statusToBeUpdated) throws BusinessException {
        com.tnpserver.entity.User user = getUserById(userId.longValue());

        if(user.isActive() == statusToBeUpdated || currentStatus == statusToBeUpdated) {
            String message = user.isActive() ? "User Status is already Active" : "User Status is already DeActive";
            throw new BusinessException(message);
        }

        if (user.getUsername().equals(SessionUtil.getCurrentUsername())) {
            throw new BusinessException("User status of Logged In User Can not be changed.");
        }

        user.setActive(statusToBeUpdated);
        com.tnpserver.entity.User savedUser = userRepo.save(user);
        LOG.info("User status Updated to - {} ", statusToBeUpdated);
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

        validateUserDeletion(userList);

        userRepo.deleteAllById(List.of(userId));

    }

    /**
     * Validates whether a user can be deleted based on certain conditions.
     *
     * This method checks three conditions before allowing the deletion of a user:
     * 1. The user attempting to be deleted is not the currently logged-in user.
     * 2. There are no users with the 'ADMIN' role in the provided list.
     * 3. There are no active users in the provided list.
     *
     * If any of these conditions is not met, a {BusinessException} is thrown with
     * an appropriate error message indicating the reason for the validation failure.
     *
     * @param userList A list of {com.tnpserver.entity.User} objects to be validated.
     * @throws BusinessException If the deletion is not allowed based on the specified conditions.
     */
    private static void validateUserDeletion(List<com.tnpserver.entity.User> userList) {
        String loggedInUserName = SessionUtil.getCurrentUsername();

        boolean loggedInUserExists = userList.stream()
                .anyMatch(user -> user.getUsername().equals(loggedInUserName));

        if (loggedInUserExists) {
            throw new BusinessException("Logged In User Can not be deleted.");
        }

        String adminRoleUsers = userList.stream()
                .filter(user -> RoleEnum.ROLE_ADMIN.name().equals(user.getRole()))
                .map(com.tnpserver.entity.User::getUsername)
                .collect(Collectors.joining(", "));

        if (!adminRoleUsers.isEmpty()) {
            throw new BusinessException("Admin User Can not be deleted - " + adminRoleUsers);
        }

        String activeUsers = userList.stream()
                .filter(com.tnpserver.entity.User::isActive)
                .map(com.tnpserver.entity.User::getUsername)
                .collect(Collectors.joining(", "));

        if (!activeUsers.isEmpty()) {
            throw new BusinessException("Active User Can not be deleted - " + activeUsers);
        }
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
        com.tnpserver.entity.User user = getUserById(userId);
        User userPojo = UserHelper.mapEntityToPojo(user);
        LOG.info("Fetch User - {} ", userPojo);
        return userPojo;
    }

    @Override
    public com.tnpserver.entity.User getUserById(Long userId) throws BusinessException {
        return userRepo.findById(userId).orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND.getError()));
    }

    @Override
    public com.tnpserver.entity.User getUserByUsername(String username) {
        return userRepo.findByUsername(username).orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND.getError()));
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

    @Override
    public void isUserActive(String username) throws BusinessException {
        boolean isUserActive = getUserByUsername(username).isActive();
        if (!isUserActive) {
            LOG.debug("Deactive User -  {}", username);
            throw new BusinessException(ErrorCode.USER_DEACTIVATE.getError());
        }
    }

}
