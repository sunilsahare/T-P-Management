package com.tnpserver.helper;

import com.tnpserver.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserHelper {

    private final Logger LOG = LoggerFactory.getLogger(UserHelper.class);

    private UserHelper(){}

    public static com.tnpserver.pojo.User mapEntityToPojo(User user) {
        com.tnpserver.pojo.User userPojo = com.tnpserver.pojo.User.builder().build();
        BeanUtils.copyProperties(user, userPojo);
        userPojo.setPassword("***********");
        return userPojo;
    }

    public static User mapPojoToEntity(com.tnpserver.pojo.User userPojo) {
        User user = User.builder().build();
        BeanUtils.copyProperties(userPojo, user);
        return user;
    }

    public static List<com.tnpserver.pojo.User> mapEntityListToPojoList(List<User> userlist) {
        List<com.tnpserver.pojo.User> userPojoList = new ArrayList<>();
        userlist.forEach(userEntity -> userPojoList.add(mapEntityToPojo(userEntity)));
        return userPojoList;
    }

    public static List<User> mapPojoListToEntityList(List<com.tnpserver.pojo.User> userPojoList) {
        List<User> userlist = new ArrayList<>();
        userPojoList.forEach(userPojo -> userlist.add(mapPojoToEntity(userPojo)));
        return userlist;
    }

    /**
     * Here We can add multiple authorities for a single role
     * @param user
     * @return Authorities
     */
    public static Collection<? extends GrantedAuthority> getAuthorities(List<String> roles) {
        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

}
