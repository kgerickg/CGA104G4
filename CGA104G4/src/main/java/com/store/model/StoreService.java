package com.store.model;

import java.util.List;

public class StoreService {

	private StoreDAO_interface dao;

	public StoreService() {
		dao = new StoreJDBCDAO();
	}

	public StoreVO addStore(String storeAcc, String storePwd, String storeName, String storeGui, String storeRep,
			String storeTel, String storeFax, String storeAd, String storeCon, String storeConTel, String storeConAd,
			String storeEmail) {

		StoreVO storeVO = new StoreVO();
		storeVO.setStoreAcc(storeAcc);
		storeVO.setStorePwd(storePwd);
		storeVO.setStoreName(storeName);
		storeVO.setStoreGui(storeGui);
		storeVO.setStoreRep(storeRep);
		storeVO.setStoreTel(storeTel);
		storeVO.setStoreFax(storeFax);
		storeVO.setStoreAd(storeAd);
		storeVO.setStoreCon(storeCon);
		storeVO.setStoreConTel(storeConTel);
		storeVO.setStoreConAd(storeConAd);
		storeVO.setStoreEmail(storeEmail);
		dao.insert(storeVO);

		return storeVO;

	}

	public StoreVO updateStore(Integer storeId, String storeAcc, String storePwd, String storeName, String storeGui,
			String storeRep, String storeTel, String storeFax, String storeAd, String storeCon, String storeConTel,
			String storeConAd, String storeEmail) {

		StoreVO storeVO = new StoreVO();

		storeVO.setStoreId(storeId);
		storeVO.setStoreAcc(storeAcc);
		storeVO.setStorePwd(storePwd);
		storeVO.setStoreName(storeName);
		storeVO.setStoreGui(storeGui);
		storeVO.setStoreRep(storeRep);
		storeVO.setStoreTel(storeTel);
		storeVO.setStoreFax(storeFax);
		storeVO.setStoreAd(storeAd);
		storeVO.setStoreCon(storeCon);
		storeVO.setStoreConTel(storeConTel);
		storeVO.setStoreConAd(storeConAd);
		storeVO.setStoreEmail(storeEmail);
		dao.update(storeVO);

		return storeVO;

	}

	public void deleteStore(Integer storeId) {
		dao.delete(storeId);
	}

	public StoreVO getOneStore(Integer storeId) {
		return dao.findByPrimaryKey(storeId);
	}

	public List<StoreVO> getAll() {
		return dao.getAll();
	}
}
