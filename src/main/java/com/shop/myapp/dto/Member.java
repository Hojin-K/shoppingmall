package com.shop.myapp.dto;

import java.sql.Date;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Member {
	@NotEmpty
	private String member_id;
	
	@NotEmpty
	@Pattern(regexp="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{8,}")
	private String member_pwd; 
	private int member_level;
	@NotEmpty
	private String member_name;
	@NotEmpty
	private String member_adress;
	@NotEmpty
	private String member_tel;
	@NotEmpty
	private Date member_birth;
}
