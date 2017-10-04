package com.practices.teachers.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.practices.teachers.model.Course;

/**
 * @author everardo_martinez
 * DAO using JdbcConfiguration
 */
//@Repository
public class CourseDaoImplJdbcTemplate extends AbstractSession implements CourseDao {

	@Autowired 
    JdbcTemplate jdbcTemplate; 
	
	public CourseDaoImplJdbcTemplate() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void saveCourse(Course course) {
		// TODO Auto-generated method stub

	}

	@Override
	public Course findCourseById(Long idCourse) {
		Course course = (Course) jdbcTemplate.queryForObject("SELECT * FROM course where id_course = ? ",
	            new Object[] { idCourse }, new BeanPropertyRowMapper<Course>(Course.class));
		return course;
	}

	@Override
	public List<Course> findAllCourses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Course findCourseByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Course> findCoursesByIdTeacher(Long idTeacher) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateCourse(Course course) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteCourse(Long idCourse) {
		// TODO Auto-generated method stub

	}

}
