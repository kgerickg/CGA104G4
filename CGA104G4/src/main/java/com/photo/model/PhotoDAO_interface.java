package com.photo.model;

import java.io.IOException;
import java.util.*;

public interface PhotoDAO_interface {
	public void insert(PhotoVO photoVO) throws IOException;

	public void update(PhotoVO photoVO);

	public void delete(Integer photoId);

	public PhotoVO findByPrimaryKey(Integer photoId);

	public List<PhotoVO> getAll();

}