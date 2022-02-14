package com.shop.myapp.dao;

import java.util.ArrayList;

import com.shop.myapp.dto.Itemlist;

public interface IDao {
	
	public ArrayList<Itemlist> itemList(int start,int end,String searchKeyword,String selNum);
	public int selectBoardTotCount1(String searchKeyword);
	public int selectBoardTotCount0(String searchKeyword);
}
