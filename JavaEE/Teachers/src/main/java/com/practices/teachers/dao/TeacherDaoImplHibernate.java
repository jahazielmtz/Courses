package com.practices.teachers.dao;

import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.practices.teachers.model.Teacher;
import com.practices.teachers.model.TeacherSocialMedia;

@Repository
@Transactional
public class TeacherDaoImplHibernate extends AbstractSession implements TeacherDao {

	@Override
	public void saveTeacher(Teacher teacher) {
		Long idNewTeacher = (Long) getSession().save(teacher);
		if (idNewTeacher != null) {
			System.out.println("Id del profesor nuevo: " + idNewTeacher);
		} else {
			System.out.println("Error al guardar profesor");
		}	
	}

	@Override
	public Teacher findTeacherById(Long idTeacher) {
		return getSession().get(Teacher.class, idTeacher);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Teacher> findAllTeachers() {
		return getSession().createQuery("from Teacher").list();
	}

	@Override
	public Teacher findTeacherByName(String name) {
		return (Teacher) getSession().createQuery("from Teacher where name = :name")
				.setParameter("name", name)
				.uniqueResult();
	}

	@Override
	public void updateTeacher(Teacher teacher) {
		getSession().update(teacher);
	}

	@Override
	public void deleteTeacher(Long idTeacher) {
		Teacher teacher = findTeacherById(idTeacher);
		if (teacher != null) {
			Iterator<TeacherSocialMedia> i = teacher.getTeacherSocialMedias().iterator();
			while (i.hasNext()) {
				TeacherSocialMedia teacherSocialMedia = i.next();
				i.remove();
				getSession().delete(teacherSocialMedia);
			}
			teacher.getTeacherSocialMedias().clear();		
			getSession().delete(teacher);
		} else {
			System.out.println("Error al borrar profesor");
		}
	}

}
