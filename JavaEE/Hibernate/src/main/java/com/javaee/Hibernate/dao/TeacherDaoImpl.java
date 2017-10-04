package com.javaee.Hibernate.dao;

import java.util.List;

import org.hibernate.query.Query;

import com.javaee.Hibernate.model.Teacher;

public class TeacherDaoImpl extends HibernateSession implements TeacherDao {

	public TeacherDaoImpl() {
		super();
	}

	public void saveTeacher(Teacher teacher) {
		Long idTeacherNew = (Long) getSession().save(teacher);
		getSession().getTransaction().commit();
		System.out.println("Id del profesor nuevo: " + idTeacherNew);
	}

	public List<Teacher> findAllTeachers() {
		Query query = getSession().createQuery("from Teacher");
		return query.list();
	}

	public Teacher findTeacherById(Long idTeacher) {
		return getSession().get(Teacher.class, idTeacher);
	}

	public Teacher findTeacherByName(String name) {
		Query query = getSession().createQuery("from Teacher where name = :name");
		query.setParameter("name", name);
		return (Teacher) query.uniqueResult();
	}

	public void deleteTeacherById(Long idTeacher) {
		Teacher teacher = findTeacherById(idTeacher);
		getSession().delete(teacher);
		getSession().getTransaction().commit();
	}

	public void updateTeacher(Teacher teacher) {
		getSession().merge(teacher);
		getSession().getTransaction().commit();
	}

}
