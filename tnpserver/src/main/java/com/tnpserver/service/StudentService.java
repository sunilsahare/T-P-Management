package com.tnpserver.service;

import com.tnpserver.exception.BusinessException;
import com.tnpserver.pojo.Student;
import com.tnpserver.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentService {

    public Student addOrUpdateAcademicInfo(Student student) throws BusinessException;
    public void removeAcademicInfo(Long id) throws BusinessException;
    public Student getAcademicInfo(Long id) throws BusinessException;

}
