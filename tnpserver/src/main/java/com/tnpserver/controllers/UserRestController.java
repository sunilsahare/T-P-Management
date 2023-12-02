package com.tnpserver.controllers;

import com.tnpserver.exception.BusinessException;
import com.tnpserver.exception.HttpApiResponse;
import com.tnpserver.pojo.User;
import com.tnpserver.service.UserService;
import com.tnpserver.util.SessionUtil;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("api/")
@CrossOrigin(origins = "http://localhost:4200")
public class UserRestController {

    private static final Logger LOG = LoggerFactory.getLogger(UserRestController.class);

    private UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("v1/user/")
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

    @PutMapping("v1/user/")
    public ResponseEntity<HttpApiResponse> updateUser(@RequestBody User user) throws BusinessException {
        HttpApiResponse response = HttpApiResponse.builder()
                .message("User Successfully Updated.")
                .status(HttpStatus.OK.name())
                .timestamp(LocalDateTime.now())
                .mapList(Map.of("responseData",userService.updateUser(user)))
                .build();
        LOG.info("'{}' User successfully Updated.",user.getUsername());
        return ResponseEntity.ok(response);
    }

    @GetMapping("v1/user/")
    public ResponseEntity<Page<com.tnpserver.entity.User>> getAllUser(Pageable pageable) {
        LOG.info("Demo API called by {}", SessionUtil.getCurrentUsername());
        return ResponseEntity.ok(userService.getAllUser(pageable));
    }

    @GetMapping("v1/user/{userRole}/")
    public ResponseEntity<Page<com.tnpserver.entity.User>> getAllUserByRole(@NotEmpty @PathVariable("userRole") String userRole, Pageable pageable) {
        LOG.info("v1/user API called by {}", SessionUtil.getCurrentUsername());
        return ResponseEntity.ok(userService.getAllUserByRole(userRole, pageable));
    }

    @GetMapping("v1/user/{userId}")
    public ResponseEntity<User> getUser(@NotEmpty @PathVariable("userId") Long userId) throws BusinessException {
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @DeleteMapping("v1/user/")
    public ResponseEntity<HttpApiResponse> deleteUser(@NotEmpty @RequestBody Long[] userId) throws BusinessException {
        userService.removeUser(userId);
        HttpApiResponse apiResponse = HttpApiResponse.builder()
                .message("User Successfully Deleted.")
                .reason("SUCCESS")
                .status(HttpStatus.OK.name())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("v1/user/{userId}")
    public ResponseEntity<HttpApiResponse> updateUserAccountStatus(
            @PathVariable("userId") @Valid @Positive Long userId,
            @RequestParam("currentStatus") boolean currentStatus,
            @RequestParam("statusToBeUpdated") boolean statusToBeUpdated) throws BusinessException {
        userService.updateUserAccountStatus(userId, currentStatus, statusToBeUpdated);
        HttpApiResponse response = HttpApiResponse.builder()
                .message("User Status Successfully Updated.")
                .status(HttpStatus.OK.name())
                .timestamp(LocalDateTime.now())
                .build();
        LOG.info("'{}' User account status successfully Updated.",statusToBeUpdated);
        return ResponseEntity.ok(response);
    }
}
