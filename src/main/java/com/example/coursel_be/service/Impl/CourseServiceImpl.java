package com.example.coursel_be.service.Impl;

import com.example.coursel_be.entity.Course;
import com.example.coursel_be.entity.User;
import com.example.coursel_be.exceptions.AppException;
import com.example.coursel_be.exceptions.ErrorCode;
import com.example.coursel_be.repository.CourseRepository;
import com.example.coursel_be.repository.UserRepository;
import com.example.coursel_be.request.CourseRequest;
import com.example.coursel_be.response.ApiResponse;
import com.example.coursel_be.response.CourseResponse;
import com.example.coursel_be.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private CourseRepository courseRepository;

    @Autowired
    public void setCourseRepository(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String saveCourse(CourseRequest courseRequest) {
        try {
            if (courseRequest != null) {
                boolean existsByTitle = courseRepository.existsByTitle(courseRequest.getTitle());
                User user = userRepository.findById(courseRequest.getIdUserCreate()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

                if (existsByTitle) {
                    return "Course already exists";
                }
                Course course = getCourse(courseRequest, user);
                courseRepository.save(course);
                return "Course saved successfully";
            } else {
                return "Course not found";
            }
        } catch (Exception e) {
            throw new AppException(ErrorCode.COURSE_SAVE_ERROR);
        }

    }

    @Override
    public CourseResponse getCourseById(Long courseId) {
        if (courseId != null) {
            boolean existsById = courseRepository.existsById(courseId);
            if (existsById) {
                Course course = courseRepository.findById(courseId).orElseThrow(() -> new AppException(ErrorCode.COURSE_NOT_FOUND));
                CourseResponse courseResponse = new CourseResponse();
                courseResponse.setId(course.getId());
                courseResponse.setTitle(course.getTitle());
                courseResponse.setDescription(course.getDescription());
                courseResponse.setCoursePrice(course.getCoursePrice());
                courseResponse.setActive(course.getActive());
                courseResponse.setCreateBy(course.getCreateBy());
                courseResponse.setCover(course.getCover());
                courseResponse.setDeleted(course.getDeleted());
                courseResponse.setUpdateBy(course.getUpdateBy());
                courseResponse.setCreatedAt(course.getCreatedAt());
                courseResponse.setDeleted(course.getDeleted());
                return courseResponse;

            }
        }
        throw new AppException(ErrorCode.COURSE_NOT_FOUND );
    }

    @Override
    public List<CourseResponse> getAllCourses() {
        List<Course> listCourses = courseRepository.findAll();
        if (listCourses.isEmpty()) {
            throw new AppException(ErrorCode.COURSE_NOT_FOUND);
        }else {
            List<CourseResponse> listCouresResponse = new ArrayList<>();
            for (Course course : listCourses) {
                CourseResponse courseResponse = new CourseResponse();
                courseResponse.setId(course.getId());
                courseResponse.setTitle(course.getTitle());
                courseResponse.setDescription(course.getDescription());
                courseResponse.setCoursePrice(course.getCoursePrice());
                courseResponse.setActive(course.getActive());
                courseResponse.setCreateBy(course.getCreateBy());
                courseResponse.setCover(course.getCover());
                courseResponse.setDeleted(course.getDeleted());
                courseResponse.setUpdateBy(course.getUpdateBy());
                courseResponse.setCreatedAt(course.getCreatedAt());
                courseResponse.setDeleted(course.getDeleted());
                listCouresResponse.add(courseResponse);
            }
            return listCouresResponse;
        }
    }

    @Override
    public boolean deleteCourseById(Long courseId) {
        return false;
    }


    private static Course getCourse(CourseRequest courseRequest, User user) {
        Course course = new Course();
        BigDecimal coursePrice = courseRequest.getCoursePrice();
        course.setTitle(courseRequest.getTitle());
        course.setDescription(courseRequest.getDescription());
        course.setCoursePrice(coursePrice);
        if (coursePrice.compareTo(BigDecimal.ZERO) != 0) {
            course.setActive(false);
        } else {
            course.setActive(true);
        }
        course.setCreateBy(user.getFullName());
        course.setDeleted(true);
        return course;
    }
}
