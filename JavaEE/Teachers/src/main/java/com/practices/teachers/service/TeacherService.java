package com.practices.teachers.service;

import java.util.List;

import com.practices.teachers.model.Teacher;

public interface TeacherService {

	void saveTeacher(Teacher teacher);
	
	Teacher findTeacherById(Long idTeacher);
	
	List<Teacher> findAllTeachers();
	
	Teacher findTeacherByName(String name);
	
	void updateTeacher(Teacher teacher);
	
	void deleteTeacher(Long idTeacher);
	
}
