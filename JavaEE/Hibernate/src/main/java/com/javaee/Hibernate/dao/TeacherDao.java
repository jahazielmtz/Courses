package com.javaee.Hibernate.dao;

import java.util.List;

import com.javaee.Hibernate.model.Teacher;

public interface TeacherDao {

	void saveTeacher(Teacher teacher);
	
	Teacher findTeacherById(Long idTeacher);

	List<Teacher> findAllTeachers();
	
	Teacher findTeacherByName(String name);
	
	void deleteTeacherById(Long idTeacher);
	
	void updateTeacher(Teacher teacher);

}
