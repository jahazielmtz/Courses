package com.practices.teachers.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practices.teachers.dao.SocialMediaDao;
import com.practices.teachers.model.SocialMedia;
import com.practices.teachers.model.TeacherSocialMedia;

@Service("socialMediaService")
@Transactional
public class SocialMediaServiceImpl implements SocialMediaService {

	@Autowired
	private SocialMediaDao _socialMediaDao;
	
	@Override
	public void saveSocialMedia(SocialMedia socialMedia) {
		_socialMediaDao.saveSocialMedia(socialMedia);
	}

	@Override
	public SocialMedia findSocialMediaById(Long idSocialMedia) {
		return _socialMediaDao.findSocialMediaById(idSocialMedia);
	}

	@Override
	public List<SocialMedia> findAllSocialMedias() {
		return _socialMediaDao.findAllSocialMedias();
	}

	@Override
	public SocialMedia findSocialMediaByName(String name) {
		return _socialMediaDao.findSocialMediaByName(name);
	}

	@Override
	public TeacherSocialMedia findSocialMediaByIdAndNickname(Long idSocialMedia, String nickname) {
		return _socialMediaDao.findSocialMediaByIdAndNickname(idSocialMedia, nickname);
	}

	@Override
	public TeacherSocialMedia findSocialMediaByIdTeacherAndIdSocialMedia(Long idTeacher, Long idSocialMedia) {
		return _socialMediaDao.findSocialMediaByIdTeacherAndIdSocialMedia(idTeacher, idSocialMedia);
	}

	@Override
	public void updateSocialMedia(SocialMedia socialMedia) {
		_socialMediaDao.updateSocialMedia(socialMedia);
	}

	@Override
	public void deleteSocialMedia(Long idSocialMedia) {
		_socialMediaDao.deleteSocialMedia(idSocialMedia);
	}

}
