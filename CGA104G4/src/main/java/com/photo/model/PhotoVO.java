package com.photo.model;

import com.prod.model.ProdService;
import com.prod.model.ProdVO;

public class PhotoVO {

	private static final long serialVersionUID = 1L;

	private Integer photoId;
	private Integer prodId;
	private Integer photoStat;
	private byte[] photoPic;
	
	public Integer getPhotoId() {
		return photoId;
	}

	public void setPhotoId(Integer photoId) {
		this.photoId = photoId;
	}

	public Integer getProdId() {
		return prodId;
	}

	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}

	public Integer getPhotoStat() {
		return photoStat;
	}

	public void setPhotoStat(Integer photoStat) {
		this.photoStat = photoStat;
	}

	public byte[] getPhotoPic() {
		return photoPic;
	}

	public void setPhotoPic(byte[] photoPic) {
		this.photoPic = photoPic;
	}

	public ProdVO getProdVO() {
		ProdService prodSvc = new ProdService();
		ProdVO prodVO = prodSvc.getOneProd(prodId);
		return prodVO;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}