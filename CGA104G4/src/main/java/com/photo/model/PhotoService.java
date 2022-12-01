package com.photo.model;

import java.util.List;

public class PhotoService {

	private PhotoDAO_interface dao;

	public PhotoService() {
		dao = new PhotoJDBCDAO();
	}

	public PhotoVO addPhoto(Integer prodId, Integer photoStat, byte[] photoPic) {

		PhotoVO photoVO = new PhotoVO();

		photoVO.setProdId(prodId);
		photoVO.setPhotoStat(photoStat);
		photoVO.setPhotoPic(photoPic);
		dao.insert(photoVO);
		
		return photoVO;
	}

	//預留給 Struts 2 或 Spring MVC 用
	public void addPhoto(PhotoVO photoVO) {
		dao.insert(photoVO);
	}
	
	public PhotoVO updatePhoto(Integer photoId, Integer prodId, Integer photoStat, byte[] photoPic) {

		PhotoVO photoVO = new PhotoVO();
		
		photoVO.setPhotoId(photoId);
		photoVO.setProdId(prodId);
		photoVO.setPhotoStat(photoStat);
		photoVO.setPhotoPic(photoPic);
		dao.update(photoVO);

		return dao.findByPrimaryKey(photoId);
	}
	
	//預留給 Struts 2 或 Spring MVC 用
	public void updatePhoto(PhotoVO photoVO) {
		dao.update(photoVO);
	}

	public void deletePhoto(Integer photoId) {
		dao.delete(photoId);
	}

	public PhotoVO getOnePhoto(Integer photoId) {
		return dao.findByPrimaryKey(photoId);
	}

	public List<PhotoVO> getAll() {
		return dao.getAll();
	}
}