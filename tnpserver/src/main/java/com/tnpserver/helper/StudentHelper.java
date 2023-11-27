package com.tnpserver.helper;

import com.tnpserver.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import java.util.ArrayList;
import java.util.List;

public class StudentHelper {

    private final Logger LOG = LoggerFactory.getLogger(StudentHelper.class);

    private StudentHelper(){}

    public static com.tnpserver.pojo.Student mapEntityToPojo(Student student) {
        com.tnpserver.pojo.Student studentPojo = com.tnpserver.pojo.Student.builder().build();
        BeanUtils.copyProperties(student, studentPojo);
        return studentPojo;
    }

    public static Student mapPojoToEntity(com.tnpserver.pojo.Student studentPojo) {
        Student student = Student.builder().build();
        BeanUtils.copyProperties(studentPojo, student);
        return student;
    }

    public static List<com.tnpserver.pojo.Student> mapEntityListToPojoList(List<Student> userlist) {
        List<com.tnpserver.pojo.Student> userPojoList = new ArrayList<>();
        userlist.forEach(userEntity -> userPojoList.add(mapEntityToPojo(userEntity)));
        return userPojoList;
    }

    public static List<Student> mapPojoListToEntityList(List<com.tnpserver.pojo.Student> userPojoList) {
        List<Student> userlist = new ArrayList<>();
        userPojoList.forEach(userPojo -> userlist.add(mapPojoToEntity(userPojo)));
        return userlist;
    }

}
