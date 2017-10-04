package com.practices.teachers.service;

import java.util.List;

import com.practices.teachers.model.Course;

public interface CourseService {

	void saveCourse(Course course);
	
	Course findCourseById(Long idCourse);
	
	List<Course> findAllCourses();
	
	Course findCourseByName(String name);
	
	List<Course> findCoursesByIdTeacher(Long idTeacher);
	
	void updateCourse(Course course);
	
	void deleteCourse(Long idCourse);
	
}
