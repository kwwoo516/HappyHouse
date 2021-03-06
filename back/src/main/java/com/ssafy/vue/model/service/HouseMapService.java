package com.ssafy.vue.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.vue.model.HouseInfoDto;
import com.ssafy.vue.model.SidoGugunCodeDto;
import com.ssafy.vue.model.WishListDto;

public interface HouseMapService {

	List<SidoGugunCodeDto> getSido() throws Exception;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;
	List<HouseInfoDto> getDongInGugun(String gugun) throws Exception;
	List<HouseInfoDto> getAptInDong(String dong) throws Exception;
	List<HouseInfoDto> getAptDealInfo(int aptCode) throws Exception;
	boolean registerWishList(WishListDto wishListDto) throws Exception;
	List<WishListDto> getWishList(String userid) throws Exception;
	boolean deleteWishList(WishListDto wishListDto) throws Exception;
	List<WishListDto> getTopAptList() throws SQLException;
}
