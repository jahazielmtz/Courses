package com.practices.teachers.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.practices.teachers.model.SocialMedia;
import com.practices.teachers.model.TeacherSocialMedia;

@Repository
@Transactional
public class SocialMediaDaoImplHibernate extends AbstractSession implements SocialMediaDao {

	@Override
	public void saveSocialMedia(SocialMedia socialMedia) {
		Long idNewSocialMedia = (Long) getSession().save(socialMedia);
		if (idNewSocialMedia != null) {
			System.out.println("Id del social media nueva: " + idNewSocialMedia);
		} else {
			System.out.println("Error al guardar social media");
		}	
	}

	@Override
	public SocialMedia findSocialMediaById(Long idSocialMedia) {
		return getSession().get(SocialMedia.class, idSocialMedia);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<SocialMedia> findAllSocialMedias() {
		return getSession().createQuery("from SocialMedia").list();
	}

	@Override
	public SocialMedia findSocialMediaByName(String name) {
		return (SocialMedia) getSession().createQuery("from SocialMedia where name = :name")
				.setParameter("name", name)
				.uniqueResult();
	}

	@Override
	public TeacherSocialMedia findSocialMediaByIdAndNickname(Long idSocialMedia, String nickname) {
		@SuppressWarnings("unchecked")
		List<Object[]> objects = getSession().createQuery(""
					+ "from TeacherSocialMedia tsm "
					+ "join tsm.socialMedia sm "
					+ "where sm.idSocialMedia = :idSocialMedia "
					+ "and tsm.nickname = :nickname")
				.setParameter("idSocialMedia", idSocialMedia)
				.setParameter("nickname", nickname)
				.list();
		if (objects.size() > 0) {
			for (Object[] objects2 : objects) {
				for (Object object : objects2) {
					if (object instanceof TeacherSocialMedia) {
						return (TeacherSocialMedia) object;
					}
				}
			}
		}
		return null;
	}

	@Override
	public TeacherSocialMedia findSocialMediaByIdTeacherAndIdSocialMedia(Long idTeacher, Long idSocialMedia) {
		@SuppressWarnings("unchecked")
		List<Object[]> objects = getSession().createQuery(""
					+ "from TeacherSocialMedia tsm "
					+ "join tsm.socialMedia sm "
					+ "join tsm.teacher t "
					+ "where sm.idSocialMedia = :idSocialMedia "
					+ "and t.idTeacher = :idTeacher")
				.setParameter("idSocialMedia", idSocialMedia)
				.setParameter("idTeacher", idTeacher)
				.list();
		if (objects.size() > 0) {
			for (Object[] objects2 : objects) {
				for (Object object : objects2) {
					if (object instanceof TeacherSocialMedia) {
						return (TeacherSocialMedia) object;
					}
				}
			}
		}
		return null;
	}

	@Override
	public void updateSocialMedia(SocialMedia socialMedia) {
		getSession().update(socialMedia);
	}

	@Override
	public void deleteSocialMedia(Long idSocialMedia) {
		SocialMedia socialMedia = findSocialMediaById(idSocialMedia);
		if (socialMedia != null) {
			getSession().delete(socialMedia);
		} else {
			System.out.println("Error al borrar social media");
		}
	}

}