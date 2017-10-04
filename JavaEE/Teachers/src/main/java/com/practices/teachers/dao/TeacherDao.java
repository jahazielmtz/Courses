package com.practices.teachers.dao;

import java.util.List;

import com.practices.teachers.model.Teacher;

public interface TeacherDao {

	void saveTeacher(Teacher teacher);
	
	Teacher findTeacherById(Long idTeacher);
	
	List<Teacher> findAllTeachers();
	
	Teacher findTeacherByName(String name);
	
	void updateTeacher(Teacher teacher);
	
	void deleteTeacher(Long idTeacher);
	
}
