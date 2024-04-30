package com.example.coursel_be.service.impl;

import com.example.coursel_be.entity.Course;
import com.example.coursel_be.entity.User;
import com.example.coursel_be.exceptions.AppException;
import com.example.coursel_be.exceptions.ErrorCode;
import com.example.coursel_be.repository.CourseRepository;
import com.example.coursel_be.repository.UserRepository;
import com.example.coursel_be.request.course.CourseRequest;
import com.example.coursel_be.request.course.CourseUpdateRequest;
import com.example.coursel_be.response.course.CourseResponse;
import com.example.coursel_be.service.CourseService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class CourseServiceImpl implements CourseService {

    private static final Logger log = LoggerFactory.getLogger(CourseServiceImpl.class);
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;


    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }


    @Override
    @Transactional
    public String saveCourse(CourseRequest courseRequest) {
        try {

            if (courseRequest == null) {
                return "Course not found";
            }

            boolean existsByTitle = courseRepository.existsByTitle(courseRequest.getTitle());
            if (existsByTitle) {
                return "Course already exists";
            }

            User user = userRepository.findById(courseRequest.getIdUserCreate()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
            Course course = getCourseFromRequest(courseRequest, user);
            courseRepository.save(course);
            return "Course saved successfully";
        } catch (Exception e) {
            throw new AppException(ErrorCode.COURSE_SAVE_ERROR);
        }
    }

    @Override
    public CourseResponse getCourseById(Long courseId) {
        if (courseId == null) {
            throw new AppException(ErrorCode.COURSE_NOT_FOUND);
        }
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new AppException(ErrorCode.COURSE_NOT_FOUND));
        return convertCourseToResponse(course);
    }

    @Override
    public List<CourseResponse> getAllCourses() {
        List<Course> listCourses = courseRepository.findAll();
        if (listCourses.isEmpty()) {
            throw new AppException(ErrorCode.COURSE_NOT_FOUND);
        }
        List<CourseResponse> listCoresResponse = new ArrayList<>();
        for (Course course : listCourses) {
            listCoresResponse.add(convertCourseToResponse(course));
        }
        return listCoresResponse;
    }


    @Override
    public String deleteCourseById(Long courseId) {
        Optional<Course> course = Optional.ofNullable(courseRepository.findById(courseId).orElseThrow(() -> new AppException(ErrorCode.COURSE_NOT_FOUND)));
        if (course.isPresent()) {
            courseRepository.delete(course.get());
            return "Course deleted successfully";
        }
        return "Course not found to delete";
    }

    @Override
    @Transactional
    public String updateCourse(CourseUpdateRequest courseUpdateRequest) {
        Optional<Course> existingCourse = Optional.ofNullable(courseRepository.findById(courseUpdateRequest.getIdCourse()).orElseThrow(() -> new AppException(ErrorCode.COURSE_NOT_FOUND)));
        boolean existsByTitle = courseRepository.existsByTitle(courseUpdateRequest.getTitle());

        if (Objects.equals(existingCourse.get().getTitle(), courseUpdateRequest.getTitle())) {
            existsByTitle = false;
        }
        if (existsByTitle) {
            return "Course already exists";
        }
        if (existingCourse.isPresent()) {
            Course courseToUpdate = getCourse(courseUpdateRequest, existingCourse);
            courseRepository.save(courseToUpdate);
            return "Course updated successfully";
        }
        return "Course not found to update";
    }


    @Override
    @Transactional
    public String changeStatusCourse(Long courseId) {
        Optional<Course> course = Optional.ofNullable(courseRepository.findById(courseId).orElseThrow(() -> new AppException(ErrorCode.COURSE_NOT_FOUND)));
        if (course.isPresent()) {
            course.get().setDeleted(!course.get().getDeleted());
            courseRepository.save(course.get());
            return "Course status changed successfully";
        }
        return "Course not found to change status";
    }

    private Course getCourseFromRequest(CourseRequest courseRequest, User user) {
        Course course = new Course();
        course.setTitle(courseRequest.getTitle());
        course.setDescription(courseRequest.getDescription());
        course.setCoursePrice(courseRequest.getCoursePrice());
        course.setCreateBy(user.getFullName());
//        course.setCreatedAt(new Date(System.currentTimeMillis()));
        course.setDeleted(true);
        return course;
    }

    private Course getCourse(CourseUpdateRequest courseUpdateRequest, Optional<Course> existingCourse) {
        Course courseToUpdate = existingCourse.get();
        User user = userRepository.findById(courseUpdateRequest.getIdUserUpdate()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        courseToUpdate.setUpdateBy(user.getFullName());
        if (courseUpdateRequest.getTitle() != null) {
            courseToUpdate.setTitle(courseUpdateRequest.getTitle());
        } else {
            courseToUpdate.setTitle(existingCourse.get().getTitle());
        }
        if (courseUpdateRequest.getDescription() != null) {
            courseToUpdate.setDescription(courseUpdateRequest.getDescription());
        }
        if (courseUpdateRequest.getCoursePrice() != null) {
            courseToUpdate.setCoursePrice(courseUpdateRequest.getCoursePrice());
        }
        if (courseUpdateRequest.getCover() != null) {
            courseToUpdate.setCover(courseUpdateRequest.getCover());
        }
        return courseToUpdate;
    }

    private CourseResponse convertCourseToResponse(Course course) {
        CourseResponse courseResponse = new CourseResponse();
        courseResponse.setId(course.getId());
        courseResponse.setTitle(course.getTitle());
        courseResponse.setDescription(course.getDescription());
        courseResponse.setCoursePrice(course.getCoursePrice());
        courseResponse.setCreateBy(course.getCreateBy());
        courseResponse.setCover(course.getCover());
        courseResponse.setDeleted(course.getDeleted());
        courseResponse.setUpdateBy(course.getUpdateBy());
        courseResponse.setCreatedAt(formatTimestamp(course.getCreatedAt()));
        courseResponse.setUpdateAt(formatTimestamp(course.getUpdateAt()));
        return courseResponse;
    }

    private String formatTimestamp(long timestampMillis) {
        Date date = new Date(timestampMillis);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }
}