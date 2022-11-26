package com.detail.model;

import java.util.List;
import java.util.Set;

public class DetailService {

	private DetailDAO_interface dao;

	public DetailService() {
		dao = new DetailJDBCDAO();
	}

	public DetailVO addDetail(Integer prodQty, Integer ordId) {

		DetailVO detailVO = new DetailVO();

		detailVO.setProdQty(prodQty);
		detailVO.setOrdId(ordId);

		dao.insert(detailVO);

		return detailVO;
	}

	//預留給 Struts 2 或 Spring MVC 用
	public void addDetail(DetailVO detailVO) {
		dao.insert(detailVO);
	}
	
	public DetailVO updateDetail(Integer prodId, Integer prodQty, Integer ordId) {

		DetailVO detailVO = new DetailVO();

		detailVO.setProdId(prodId);
		detailVO.setProdQty(prodQty);
		detailVO.setOrdId(ordId);
		dao.update(detailVO);

		return dao.findByPrimaryKey(prodId);
	}
	
	//預留給 Struts 2 用的
	public void updateDetail(DetailVO detailVO) {
		dao.update(detailVO);
	}

	public void deleteDetail(Integer prodId) {
		dao.delete(prodId);
	}

	public DetailVO getOneDetail(Integer prodId) {
		return dao.findByPrimaryKey(prodId);
	}

	public List<DetailVO> getAll() {
		return dao.getAll();
	}
	
	public Set<DetailVO> getDetailsByOrdId(Integer ordId) {
		return dao.getDetailsByOrdId(ordId);
	}
}