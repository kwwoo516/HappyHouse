package com.ssafy.vue.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "WishListDto : 아파트 위시리스트 정보", description = "user가 찜해놓은 아파트 정보를 나타낸다")
public class WishListDto {
	@ApiModelProperty(value = "위시리스트 코드")
	private int listno;
	
	@ApiModelProperty(value = "회원 id")
	private String userid;
	
	@ApiModelProperty(value = "아파트 이름")
	private String aptName;
	
	@ApiModelProperty(value = "동 코드")
	private String dongCode;

	@ApiModelProperty(value = "동 이름")
	private String dongName;
	
	@ApiModelProperty(value = "지번")
	private String jibun;

	@ApiModelProperty(value = "가격")
	private String dealAmount;

	public String getDealAmount() {
		return dealAmount;
	}

	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}
	
	public int getListno() {
		return listno;
	}

	public void setListno(int listno) {
		this.listno = listno;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getAptName() {
		return aptName;
	}

	public void setAptName(String aptName) {
		this.aptName = aptName;
	}

	public String getDongCode() {
		return dongCode;
	}

	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}

	public String getDongName() {
		return dongName;
	}

	public void setDongName(String dongName) {
		this.dongName = dongName;
	}

	public String getJibun() {
		return jibun;
	}

	public void setJibun(String jibun) {
		this.jibun = jibun;
	}
}
