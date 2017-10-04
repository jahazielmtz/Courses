package com.practices.teachers.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.practices.teachers.model.SocialMedia;
import com.practices.teachers.model.Teacher;
import com.practices.teachers.model.TeacherSocialMedia;
import com.practices.teachers.service.SocialMediaService;
import com.practices.teachers.service.TeacherService;

@Controller
@RequestMapping("/v1")
public class TeacherController {
	
	public static final String TEACHER_UPLOADED_FOLDER = "images/teachers/";
	
	@Autowired 
	TeacherService _teacherService;
	
	@Autowired 
	SocialMediaService _socialMediaService;

	//POST
	@RequestMapping(value = "/teacher", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<?> createTeacher(@RequestBody Teacher teacher, UriComponentsBuilder uriBuilder) {
		if(teacher.getName() == null || teacher.getName().isEmpty()) {
			return ResponseEntity.badRequest().header("error", "Teacher is incomplete").build();
		}
		
		_teacherService.saveTeacher(teacher);
		
		Teacher teacher2 = _teacherService.findTeacherByName(teacher.getName());
		return ResponseEntity
				.created(uriBuilder
						.path("/v1/teacher/{id}")
						.buildAndExpand(teacher2.getIdTeacher())
						.toUri())
				.build();
	}
	
	//GET
	@RequestMapping(value = "/teacher/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Teacher> getTeacherById(@PathVariable("id") Long idTeacher) {
		if(idTeacher == null || idTeacher <= 0L) {
			return ResponseEntity.badRequest().header("error", "idTeacher is incorrect").build();
		}
		
		Teacher teacher = _teacherService.findTeacherById(idTeacher);
		if(teacher == null) {
			return ResponseEntity.badRequest().header("error", "Teacher not found").build();
		}
		
		return ResponseEntity.ok(teacher);
	}
	
	//GET
	@RequestMapping(value = "/teacher", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Teacher>> getTeachers(@RequestParam(value = "name", required = false) String name) {
		List<Teacher> lstTeachers = new ArrayList<>();
		
		if(name == null || name.isEmpty()) {
			lstTeachers = _teacherService.findAllTeachers();
			if(lstTeachers.isEmpty()) {
				return ResponseEntity.noContent().header("error", "Teachers not found").build();
			}
			return ResponseEntity.ok(lstTeachers);
		} else {
			Teacher teacher = _teacherService.findTeacherByName(name);
			if(teacher == null) {
				return ResponseEntity.noContent().header("error", "Teacher not found").build();
			}
			lstTeachers.add(teacher);
			return ResponseEntity.ok(lstTeachers);
		}
	}
	
	//PATCH
	@RequestMapping(value = "/teacher/{id}", method = RequestMethod.PATCH, consumes = "application/json", produces = "application/json")
	public ResponseEntity<Teacher> updateTeacher(@PathVariable("id") Long idTeacher, @RequestBody Teacher teacher) {
		if(idTeacher == null || idTeacher <= 0L) {
			return ResponseEntity.badRequest().header("error", "idTeacher is incorrect").build();
		}
		
		Teacher currentTeacher = _teacherService.findTeacherById(idTeacher);
		if(teacher == null) {
			return ResponseEntity.badRequest().header("error", "Teacher not found").build();
		}
		
		currentTeacher.setName(teacher.getName());
		currentTeacher.setAvatar(teacher.getAvatar());
		_teacherService.updateTeacher(currentTeacher);
		return ResponseEntity.ok(currentTeacher);
	}
	
	//DELETE
	@RequestMapping(value = "/teacher/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteTeacher(@PathVariable("id") Long idTeacher) {
		if(idTeacher == null || idTeacher <= 0L) {
			return ResponseEntity.badRequest().header("error", "idTeacher is incorrect").build();
		}
		
		Teacher teacher = _teacherService.findTeacherById(idTeacher);
		if(teacher == null) {
			return ResponseEntity.badRequest().header("error", "Teacher not found").build();
		}
		
		_teacherService.deleteTeacher(idTeacher);
		return ResponseEntity.ok().build();
	}
	
	//POST TEACHER IMAGE
	@RequestMapping(value = "/teacher/{id_teacher}/image", method = RequestMethod.POST, consumes = "multipart/form-data", produces = "image/jpeg")
	public ResponseEntity<byte[]> uploadTeacherImage(@PathVariable("id_teacher") Long idTeacher, 
			@RequestParam("file") MultipartFile multipartFile) {
		if(idTeacher == null || idTeacher <= 0L) {
			return ResponseEntity.badRequest().header("error", "idTeacher is incorrect").build();
		}
		if(multipartFile == null || multipartFile.isEmpty()) {
			return ResponseEntity.badRequest().header("error", "File is incorrect").build();
		}
		Teacher teacher = _teacherService.findTeacherById(idTeacher);
		if(teacher == null) {
			return ResponseEntity.badRequest().header("error", "Teacher not found").build();
		}
		
		if (teacher.getAvatar() != null || !teacher.getAvatar().isEmpty()) {
			String fileName = teacher.getAvatar();
			Path path = Paths.get(fileName);
			File f = path.toFile();
			if(f.exists()) {
				f.delete();
			}
		}
		try {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
			String dateName = sdf.format(date);
			
			String fileName = String.valueOf(idTeacher) + "-pictureTeacher-" + dateName + "." + multipartFile.getContentType().split("/")[1];
			teacher.setAvatar(TEACHER_UPLOADED_FOLDER + fileName);
			
			byte[] bytes = multipartFile.getBytes();
			Path path = Paths.get(TEACHER_UPLOADED_FOLDER + fileName);
			Files.write(path, bytes);
			
			_teacherService.updateTeacher(teacher);
			return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("error", "Error to upload image").build();
		}
	}
	
	//GET IMAGE
	@RequestMapping(value = "/teacher/{id_teacher}/image", method = RequestMethod.GET, produces = "image/jpeg")
	public ResponseEntity<byte[]> getTeacherImage(@PathVariable("id_teacher") Long idTeacher) {
		if(idTeacher == null || idTeacher <= 0L) {
			return ResponseEntity.badRequest().header("error", "idTeacher is incorrect").build();
		}
		Teacher teacher = _teacherService.findTeacherById(idTeacher);
		if(teacher == null) {
			return ResponseEntity.badRequest().header("error", "Teacher not found").build();
		}
		
		try {
			String fileName = teacher.getAvatar();
			Path path = Paths.get(fileName);
			File f = path.toFile();
			if(!f.exists()) {
				return ResponseEntity.noContent().header("error", "Imagen not found").build();
			}
			
			byte[] image = Files.readAllBytes(path);
			return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("error", "Error to show image").build();
		}
	}
	
	//DELETE IMAGE
	@RequestMapping(value = "/teacher/{id_teacher}/image", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteTeacherImage(@PathVariable("id_teacher") Long idTeacher) {
		if(idTeacher == null || idTeacher <= 0L) {
			return ResponseEntity.badRequest().header("error", "idTeacher is incorrect").build();
		}
		Teacher teacher = _teacherService.findTeacherById(idTeacher);
		if(teacher == null) {
			return ResponseEntity.badRequest().header("error", "Teacher not found").build();
		}
		if(teacher.getAvatar() == null || teacher.getAvatar().isEmpty()) {
			return ResponseEntity.badRequest().header("error", "Teacher does't have image assigned").build();
		}
		
		String fileName = teacher.getAvatar();
		Path path = Paths.get(fileName);
		File file = path.toFile();
		if(file.exists()) {
			file.delete();
		}
		teacher.setAvatar("");
		_teacherService.updateTeacher(teacher);
		return ResponseEntity.noContent().build();		
	}
	
	//PATCH SOCIAL MEDIA
	@RequestMapping(value = "/teacher/socialMedia", 
			method = RequestMethod.PATCH, 
			headers = "Accept=application/json")
	public ResponseEntity<?> assignTeacherSocialMedia(@RequestBody Teacher teacher,
			UriComponentsBuilder uriBuiler) {
		//Teacher.idTeacher's validation
		if(teacher.getIdTeacher() == null || teacher.getIdTeacher() <= 0) {
			return ResponseEntity.badRequest()
					.header("ERROR", "We need almost id_teacher, id_social_media and nickname")
					.body("We need almost id_teacher, id_social_media and nickname");
		}
		//Validation if teacher don't exist in DB.
		Teacher teacherSaved = _teacherService.findTeacherById(teacher.getIdTeacher());
		if(teacherSaved == null) {
			return ResponseEntity.badRequest()
					.header("ERROR", "Teacher not found")
					.body("Teacher not found");
		}
		//Teacher.teacherSocialMedias validation
		if(teacher.getTeacherSocialMedias() == null || teacher.getTeacherSocialMedias().isEmpty()) {
			return ResponseEntity.badRequest()
					.header("ERROR", "We need almost id_teacher, id_social_media and nickname")
					.body("We need almost id_teacher, id_social_media and nickname");
		} 
		
		Iterator<TeacherSocialMedia> i = teacher.getTeacherSocialMedias().iterator();
		while(i.hasNext()) {
			TeacherSocialMedia teacherSocialMedia = i.next();
			//Teacher.teacherSocialMedias.socialMedia validation
			if(teacherSocialMedia.getSocialMedia() == null || 
					teacherSocialMedia.getSocialMedia().getIdSocialMedia() == null ||
					teacherSocialMedia.getNickname() == null) {
				return ResponseEntity.badRequest()
						.header("ERROR", "We need almost id_teacher, id_social_media and nickname")
						.body("We need almost id_teacher, id_social_media and nickname");
			} 
			//Validation if socialMedia don't exist in DB
			SocialMedia socialMediaSaved = _socialMediaService.findSocialMediaById(
					teacherSocialMedia.getSocialMedia().getIdSocialMedia());
			if(socialMediaSaved == null) {
				return ResponseEntity.badRequest()
						.header("ERROR", "IdSocialMedia not found")
						.body("IdSocialMedia not found");
			}
			teacherSocialMedia.setSocialMedia(socialMediaSaved);
			teacherSocialMedia.setTeacher(teacherSaved);
			//Validation if teacherSocialMedia exist in DB to save or update
			TeacherSocialMedia teacherSocialMediaSaved = 
					_socialMediaService.findSocialMediaByIdTeacherAndIdSocialMedia(
							teacher.getIdTeacher(), 
							teacherSocialMedia.getSocialMedia().getIdSocialMedia());
			if(teacherSocialMediaSaved == null) {
				teacherSaved.getTeacherSocialMedias().add(teacherSocialMedia);
			} else {
				List<TeacherSocialMedia> teacherSocialMedias = new LinkedList<>();
				teacherSocialMedias.addAll(teacherSaved.getTeacherSocialMedias());
				for (int j = 0; j < teacherSocialMedias.size(); j++) {
					TeacherSocialMedia teacherSocialMedia2 = teacherSocialMedias.get(j);
					if(teacherSocialMedia.getTeacher().getIdTeacher() == 
							teacherSocialMedia2.getTeacher().getIdTeacher() &&
							teacherSocialMedia.getSocialMedia().getIdSocialMedia() == 
							teacherSocialMedia2.getSocialMedia().getIdSocialMedia()) {
						teacherSocialMedia2.setNickname(teacherSocialMedia.getNickname());
					}
					teacherSocialMedias.set(j, teacherSocialMedia2);
				}
				teacherSaved.getTeacherSocialMedias().clear();
				teacherSaved.getTeacherSocialMedias().addAll(teacherSocialMedias);
			}
		}
		_teacherService.updateTeacher(teacherSaved);
		return ResponseEntity.ok(teacherSaved);
	}
	
}
