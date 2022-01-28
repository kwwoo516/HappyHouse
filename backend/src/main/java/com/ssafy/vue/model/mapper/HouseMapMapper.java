package com.ssafy.vue.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.model.HouseInfoDto;
import com.ssafy.vue.model.SidoGugunCodeDto;
import com.ssafy.vue.model.WishListDto;

@Mapper
public interface HouseMapMapper {

	List<SidoGugunCodeDto> getSido() throws SQLException;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws SQLException;
	List<HouseInfoDto> getDongInGugun(String gugun) throws SQLException;
	List<HouseInfoDto> getAptInDong(String dong) throws SQLException;
	List<HouseInfoDto> getAptDealInfo(int aptCode) throws SQLException;
	int registerWishList(WishListDto wishListDto) throws SQLException;
	List<WishListDto> getWishList(String userid) throws SQLException;
	int deleteWishList(WishListDto wishListDto) throws SQLException;
	List<WishListDto> getTopAptList() throws SQLException;
}
