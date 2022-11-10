package com.lkcompl.model;

import java.util.List;

public interface LkComplService {
	
	boolean insert(LkComplVO lkComplVO);
	
	boolean update(LkComplVO lkComplVO);
	
	LkComplVO selectByLkCcId(Integer lkCcId);
	
	List<LkComplVO> selectAll();
	
	
	
	
//	public interface NewsService {
//	    //增
//	    Boolean addNews(News news);
//	    //删
//	    Boolean delNews(Integer id);
//	    //改
//	    Boolean updateNews(News news);
//	    //全查
//	    List getAllNews(Map map);
//	    //单查
//	    News getNewsById(Integer id);
//	    //查总数
//	    Integer getNewsCount();
//	}

}
