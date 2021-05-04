package com.callor.book.service;

import java.util.List;

import com.callor.book.model.BuyerDTO;

/*
 * tbl_buyer 테이블의 CRUD 구현
 */
public interface BuyerService {

	public List<BuyerDTO> selectAll();
	
	public BuyerDTO findById(String bu_code);
	public List<BuyerDTO> findByName(String bu_name);
	public List<BuyerDTO> findByTel(String bu_tel);
	
	public int insert();
	public int update();
	public int delete();
	
}
