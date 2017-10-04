package com.javaee.Hibernate;

import java.util.List;

import com.javaee.Hibernate.dao.TeacherDaoImpl;
import com.javaee.Hibernate.model.Teacher;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        TeacherDaoImpl teacherDaoImpl = new TeacherDaoImpl();

//		  //Save a teacher        
//        Teacher teacher = new Teacher("Jahaziel Islas", "Avatar1");
//        teacherDaoImpl.saveTeacher(teacher);
        
//		  //Get all teachers        
//        List<Teacher> teachers= teacherDaoImpl.findAllTeachers();
//        for (Teacher t : teachers) {
//			System.out.println("Nombre: " + t.getName());
//	  	  }
        
//		  //Get teacher by ID        
//        Teacher teacher = teacherDaoImpl.findTeacherById(1L);
//        if (teacher != null) {
//            System.out.println("Teacher: " + teacher.getName());
//        } else {
//      	  System.out.println("Maestro no encontrado");
//        }

//		  //Get teacher by name        
//        Teacher teacher = teacherDaoImpl.findTeacherByName("Jahaziel Martínez");
//        if (teacher != null) {
//            System.out.println("Teacher: " + teacher.getName());
//        } else {
//  	      System.out.println("Maestro no encontrado");
//        }
        
//		  //Delete teacher        
//        teacherDaoImpl.deleteTeacherById(3L);
        
//        //Update teacher
        Teacher teacher = teacherDaoImpl.findTeacherByName("Jahaziel Martínez");
        teacher.setAvatar("Avatar 2");
        teacherDaoImpl.updateTeacher(teacher);
    }
}
