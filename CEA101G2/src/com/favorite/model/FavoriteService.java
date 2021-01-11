package com.favorite.model;

import java.util.List;

public class FavoriteService {

	private FavoriteDAO_interface dao;

	public FavoriteService() {
		dao = new FavoriteJDBCDAO();
	}

	public FavoriteVO addFavorite(String store_no, String mem_no
			) {

		FavoriteVO favoriteVO = new FavoriteVO();
		favoriteVO.setStore_no(store_no);
		favoriteVO.setMem_no(mem_no);
		dao.insert(favoriteVO);
		return favoriteVO;
	}

	public FavoriteVO updateFavorite(String store_no, String mem_no) {

		FavoriteVO favoriteVO = new FavoriteVO();
		favoriteVO.setStore_no(store_no);
		favoriteVO.setMem_no(mem_no);
		dao.update(favoriteVO);
		return favoriteVO;
	}

	public void deleteFavorite(String store_no,String mem_no) {
		dao.delete(store_no,mem_no);
	}

	public List<FavoriteVO> getOneFavorite(String store_no) {
				return dao.findByPrimaryKey(store_no);
	}

	public List<FavoriteVO> getAll() {
		return dao.getAll();
	}
}
