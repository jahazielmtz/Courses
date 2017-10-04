package com.practices.teachers.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practices.teachers.dao.TeacherDao;
import com.practices.teachers.model.Teacher;

@Service("teacherService")
@Transactional
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	private TeacherDao _teacherDao;
	
	@Override
	public void saveTeacher(Teacher teacher) {
		_teacherDao.saveTeacher(teacher);
	}

	@Override
	public Teacher findTeacherById(Long idTeacher) {
		return _teacherDao.findTeacherById(idTeacher);
	}

	@Override
	public List<Teacher> findAllTeachers() {
		return _teacherDao.findAllTeachers();
	}

	@Override
	public Teacher findTeacherByName(String name) {
		return _teacherDao.findTeacherByName(name);
	}

	@Override
	public void updateTeacher(Teacher teacher) {
		_teacherDao.updateTeacher(teacher);
	}

	@Override
	public void deleteTeacher(Long idTeacher) {
		_teacherDao.deleteTeacher(idTeacher);
	}

}
