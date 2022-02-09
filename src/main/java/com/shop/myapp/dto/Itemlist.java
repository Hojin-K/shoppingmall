package com.shop.myapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Itemlist {
	private String item_image;
	private String item_name;
	private String item_cost;
	private int item_code;

}

/*create table "shop_item_tb" (
		"item_code"	number,
		"member_id"	varchar2(20) not null,
		"country_code" number,
		"item_name"	varchar2(50) not null,
		"item_cost"	number not null,
		"item_image" varchar2(100) not null,
		"item_info"	varchar2(4000) null,
		"item_stock" number default 0
	);*/