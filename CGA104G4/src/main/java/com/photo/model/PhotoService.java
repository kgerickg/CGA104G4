package com.photo.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class PhotoService {

	private PhotoDAO_interface dao;

	public PhotoService() {
		dao = new PhotoJDBCDAO();
	}

	public PhotoVO addPhoto(Integer prodId, Integer photoStat) throws IOException {

		PhotoVO photoVO = new PhotoVO();

		photoVO.setPhotoId(prodId);
		photoVO.setPhotoStat(photoStat);
		byte[] pic = getPictureByteArray("C:/CGA104G4_Workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/CGA104G4/resources/images/PROD_ID_" + photoVO.getPhotoId() +".jpg");
		photoVO.setPhotoPic(pic);
	
		dao.insert(photoVO);

		return photoVO;
	}

	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}

	//預留給 Struts 2 或 Spring MVC 用
	public void addPhoto(PhotoVO PhotoVO) throws IOException {
		dao.insert(PhotoVO);
	}
	
	public PhotoVO updatePhoto(Integer photoId, Integer prodId, Integer photoStat) throws IOException {

		PhotoVO photoVO = new PhotoVO();
		
		photoVO.setPhotoId(prodId);
		photoVO.setPhotoStat(photoStat);
		byte[] pic = getPictureByteArray("C:/CGA104G4_Workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/CGA104G4/resources/images/PROD_ID_" + photoVO.getPhotoId() +".jpg");
		photoVO.setPhotoPic(pic);
		dao.update(photoVO);

		return dao.findByPrimaryKey(photoId);
	}
	
	//預留給 Struts 2 用的
	public void updatePhoto(PhotoVO PhotoVO) {
		dao.update(PhotoVO);
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