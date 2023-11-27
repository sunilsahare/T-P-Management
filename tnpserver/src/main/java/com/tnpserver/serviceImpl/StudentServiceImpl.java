package com.tnpserver.serviceImpl;

import com.tnpserver.constants.ErrorCode;
import com.tnpserver.exception.BusinessException;
import com.tnpserver.helper.EntityPojoMapper;
import com.tnpserver.pojo.Student;
import com.tnpserver.pojo.User;
import com.tnpserver.repo.StudentRepository;
import com.tnpserver.service.StudentService;
import com.tnpserver.util.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("studentService")
public class StudentServiceImpl extends UserServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    private Logger LOG = LoggerFactory.getLogger(StudentServiceImpl.class);

    /**
     * Adds or updates academic information for a student.
     *
     * @param student The {Student} object containing the updated academic information.
     * @return The {Student} object after the academic information has been added or updated.
     * @throws BusinessException If there is an issue with the studentId or if the academic information update is not allowed.
     */
    @Override
    public Student addOrUpdateAcademicInfo(Student student) throws BusinessException {
        validateStudentId(student.getStudentId());
        Optional<com.tnpserver.entity.Student> existingDetailsOptional = studentRepository.findById(student.getStudentId());
        com.tnpserver.entity.Student studentEntity = existingDetailsOptional.orElseThrow(() ->
                new BusinessException("Invalid Student Id!!! Please provide a valid studentId."));

        LOG.debug("Updating academic info by '{}' user", SessionUtil.getCurrentUsername());
        isAcademicInfoUpdateAllowed(student.getUserId());
        updateStudentDetails(studentEntity, student);
        com.tnpserver.entity.Student savedStudent = studentRepository.save(studentEntity);
        LOG.debug("Updated academic info: {}", savedStudent);
        return studentMapper().mapEntityToPojo(savedStudent);
    }

    /**
     * Validates the studentId, throwing a {BusinessException} if it is null.
     *
     * @param studentId The studentId to validate.
     * @throws BusinessException If the studentId is null.
     */
    private void validateStudentId(Long studentId) throws BusinessException {
        if (studentId == null || studentId < 1) {
            throw new BusinessException("Student Id is required. Please provide a valid studentId.");
        }
    }

    /**
     * Updates the academic details of an existing student entity with the information provided in a new student object.
     *
     * @param existingDetails The existing {Student} entity to be updated.
     * @param newDetails      The {Student} object containing the updated academic information.
     */
    private void updateStudentDetails(com.tnpserver.entity.Student existingDetails, Student newDetails) {
        existingDetails.setAcademicInfo(newDetails.getAcademicInfo());
        existingDetails.setSkills(newDetails.getSkills());
        existingDetails.setResumeUrl(newDetails.getResumeUrl());
        existingDetails.setCareerObjectives(newDetails.getCareerObjectives());
        existingDetails.setInternshipHistory(newDetails.getInternshipHistory());
        existingDetails.setFeedbackAndRatings(newDetails.getFeedbackAndRatings());
    }

    /**
     * Creates a {Student} entity for updating academic information based on the details
     * provided in a {Student} object.
     *
     * @param student The {Student} object containing the updated academic information.
     * @return A {Student} entity ready for updating in the database.
     */
    private com.tnpserver.entity.Student createStudentEntityForUpdate(Student student) {
        com.tnpserver.entity.Student studentEntity = studentMapper().mapPojoToEntity(student);
        studentEntity.setUser(userMapper().mapPojoToEntity(getUser(student.getUserId())));
        studentEntity.setId(student.getStudentId() != null ? student.getStudentId() : 0);
        return studentEntity;
    }


    /**
     * Removes the academic information of a student by their unique identifier.
     *
     * @param id The unique identifier (ID) of the student.
     * @throws BusinessException If the academic information for the specified ID is not found.
     */
    @Override
    public void removeAcademicInfo(Long id) throws BusinessException {
        getAcademicInfo(id);
        studentRepository.deleteById(id);
        LOG.info("Removed User academic info of user - {}", id);
    }

    /**
     * Retrieves the academic information of a student by their unique identifier.
     *
     * @param id The unique identifier (ID) of the student.
     * @return A {Student} object containing the academic information.
     * @throws BusinessException If the academic information for the specified ID is not found.
     */
    @Override
    public Student getAcademicInfo(Long id) throws BusinessException {
        com.tnpserver.entity.Student studentEntity = studentRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.ACADEMIC_INFO_NOT_FOUND.getError()));
        Student studentPojo = studentMapper().mapEntityToPojo(studentEntity);
        // studentPojo.setUser(studentEntity.getUser());
        return studentPojo;
    }


    /**
     * Returns a mapper for converting entities of type {Student} to POJOs of type {Student}.
     *
     * @return An {EntityPojoMapper} for {Student} and {Student}.
     */
    private static EntityPojoMapper<com.tnpserver.entity.Student, Student> studentMapper() {
        return new EntityPojoMapper<>(com.tnpserver.entity.Student.class, Student.class);
    }

    /**
     * Returns a mapper for converting entities of type {User} to POJOs of type {User}.
     *
     * @return An {EntityPojoMapper} for {User} and {User}.
     */
    private static EntityPojoMapper<com.tnpserver.entity.User, User> userMapper() {
        return new EntityPojoMapper<>(com.tnpserver.entity.User.class, User.class);
    }

    /**
     * Validates whether the update of academic information is allowed for the specified user based on the logged-in session.
     *
     * @param userId The user ID for whom the academic information update is being checked.
     * @throws BusinessException If the logged-in username is not found, the user is not found, or the academic info update is not allowed.
     */
    private void isAcademicInfoUpdateAllowed(Long userId) {
        String loggedInUsername = SessionUtil.getCurrentUsername();

        if (loggedInUsername == null) {
            LOG.debug(ErrorCode.USERNAME_NOT_FOUND.getError());
            throw new BusinessException(ErrorCode.USERNAME_NOT_FOUND.getError());
        }

        com.tnpserver.entity.User user = getUserByUsername(loggedInUsername);

        if (user == null) {
            LOG.debug(ErrorCode.USER_NOT_FOUND.getError());
            throw new BusinessException(ErrorCode.USER_NOT_FOUND.getError());
        }

        // Validate that only the logged-in user should be able to update their Academic info.
        if (!user.getUserId().equals(userId)) {
            LOG.debug(ErrorCode.ACADEMIC_INFO_UPDATE_NOT_ALLOWED.getError());
            throw new BusinessException(ErrorCode.ACADEMIC_INFO_UPDATE_NOT_ALLOWED.getError());
        }
    }


}
