package com.setbang.service;

import java.util.List;

import com.setbang.domain.ItemVO;

public interface ItemService {

	public List<ItemVO> comboSelect1(ItemVO vo);
	
	public List<ItemVO> comboSelect2(int iCatCode);
	
	public void insertItemApply(ItemVO vo);
	
//	public void insertItemApplyDetail(ItemVO vo);
	
	
}
