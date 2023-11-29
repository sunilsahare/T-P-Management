package com.tnpserver.helper;

import com.tnpserver.pojo.Employer;
import com.tnpserver.pojo.Student;
import com.tnpserver.pojo.User;

public class PojoHelper {

    private PojoHelper(){}

    /**
     * Returns a mapper for converting entities of type {Student} to POJOs of type {Student}.
     *
     * @return An {EntityPojoMapper} for {Student} and {Student}.
     */
    public static EntityPojoMapper<com.tnpserver.entity.Student, Student> studentMapper() {
        return new EntityPojoMapper<>(com.tnpserver.entity.Student.class, Student.class);
    }

    /**
     * Returns a mapper for converting entities of type {User} to POJOs of type {User}.
     *
     * @return An {EntityPojoMapper} for {User} and {User}.
     */
    public static EntityPojoMapper<com.tnpserver.entity.User, User> userMapper() {
        return new EntityPojoMapper<>(com.tnpserver.entity.User.class, User.class);
    }

    /**
     * Returns a mapper for converting entities of type {Employer} to POJOs of type {Employer}.
     *
     * @return An {EntityPojoMapper} for {Employer} and {Employer}.
     */
    public static EntityPojoMapper<com.tnpserver.entity.Employer, Employer> employeeMapper() {
        return new EntityPojoMapper<>(com.tnpserver.entity.Employer.class, Employer.class);
    }

}
