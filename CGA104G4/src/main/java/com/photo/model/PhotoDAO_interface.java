package com.photo.model;

import java.util.List;

public interface PhotoDAO_interface {
	public void insert(PhotoVO photoVO);
	public void update(PhotoVO photoVO);
	public void delete(Integer photoId);
	public PhotoVO findByPrimaryKey(Integer photoId);
	public List<PhotoVO> getAll();

}