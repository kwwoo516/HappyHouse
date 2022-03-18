package com.ssafy.vue.controller;

import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.util.JsoupComponentLocalMain;
import com.ssafy.vue.model.HouseInfoDto;
import com.ssafy.vue.model.MemberDto;
import com.ssafy.vue.model.SidoGugunCodeDto;
import com.ssafy.vue.model.WishListDto;
import com.ssafy.vue.model.service.HouseMapService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/map")
@Api("Map 컨트롤러  API V1")
public class HouseMapController {
	
	private final Logger logger = LoggerFactory.getLogger(HouseMapController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private HouseMapService houseMapService;
	
	@ApiOperation(value = "시도 정보", notes = "전국의 시도를 반환한다.", response = List.class)
	@GetMapping("/sido")
	public ResponseEntity<List<SidoGugunCodeDto>> sido() throws Exception {
		logger.info("sido - 호출");
		return new ResponseEntity<List<SidoGugunCodeDto>>(houseMapService.getSido(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "구군 정보", notes = "전국의 구군을 반환한다.", response = List.class)
	@GetMapping("/gugun")
	public ResponseEntity<List<SidoGugunCodeDto>> gugun(@RequestParam("sido") @ApiParam(value = "시도코드.", required = true) String sido) throws Exception {
		logger.info("gugun - 호출");
		return new ResponseEntity<List<SidoGugunCodeDto>>(houseMapService.getGugunInSido(sido), HttpStatus.OK);
	}
	
	@ApiOperation(value = "동 정보", notes = "전국의 동을 반환한다.", response = List.class)
	@GetMapping("/dong")
	public ResponseEntity<List<HouseInfoDto>> dong(@RequestParam("gugun") @ApiParam(value = "구군코드.", required = true) String gugun) throws Exception {
		logger.info("dong - 호출");
		return new ResponseEntity<List<HouseInfoDto>>(houseMapService.getDongInGugun(gugun), HttpStatus.OK);
	}
	
	@ApiOperation(value = "house 정보", notes = "전국의 집 정보를 반환한다.", response = List.class)
	@GetMapping("/apt")
	public ResponseEntity<List<HouseInfoDto>> apt(@RequestParam("dong") @ApiParam(value = "동코드.", required = true) String dong) throws Exception {
		logger.info("apt - 호출");
		return new ResponseEntity<List<HouseInfoDto>>(houseMapService.getAptInDong(dong), HttpStatus.OK);
	}

	@ApiOperation(value = "위시리스트 등록", notes = "아파트 위시리스트를 등록한다.", response = String.class)
	@PostMapping("/wishlist")
	public ResponseEntity<String> registerWishList(
			@RequestBody @ApiParam(value = "회원정보 ", required = true) WishListDto wishListDto) throws Exception{
		logger.info("registerWishList - 호출");
		if (houseMapService.registerWishList(wishListDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "위시리스트 정보", notes = "회원별 위시리스트를 반환한다", response = List.class)
	@GetMapping("/wishlist")
	public ResponseEntity<List<WishListDto>> getWishList(@RequestParam("userid") @ApiParam(value = "회원아이디", required = true) String userid) throws Exception {
		logger.info("getWishList");
		return new ResponseEntity<List<WishListDto>>(houseMapService.getWishList(userid), HttpStatus.OK);
	}
	
	@ApiOperation(value = "위시리스트 삭제", notes = "회원의 위시리스트를 삭제한다", response = String.class)
	@DeleteMapping("/wishlist")
	public ResponseEntity<String> deleteWishList(@RequestParam("userid") @ApiParam(value = "회원아이디", required = true) String userid, @RequestParam("aptname") @ApiParam(value = "아파트이름", required = true) String aptname) throws Exception {
		logger.info("deleteWishList - 호출");
		WishListDto wishListDto = new WishListDto();
		wishListDto.setAptName(aptname);
		wishListDto.setUserid(userid);
		if (houseMapService.deleteWishList(wishListDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "위시리스트 상위 5개 아파트 목록", notes = "위시리스트에 가장 많이 담긴 아파트 5개를 출력", response = List.class)
	@GetMapping("/TopAptList")
	public ResponseEntity<List<WishListDto>> getTopAptList() throws Exception {
		logger.info("getTopAptList");
		return new ResponseEntity<List<WishListDto>>(houseMapService.getTopAptList(), HttpStatus.OK);
	}

	@GetMapping("/marketnews")
	public ResponseEntity<Map<String, Map<String, String>>> getMarketNews() throws Exception {
		logger.info("getMarketNews");
		return new ResponseEntity<Map<String, Map<String, String>>> (JsoupComponentLocalMain.getMarketNews(), HttpStatus.OK);
	}
	
	@GetMapping("/policynews")
	public ResponseEntity<Map<String, Map<String, String>>> getPolicyNews() throws Exception {
		logger.info("getPolicyNews");
		return new ResponseEntity<Map<String, Map<String, String>>> (JsoupComponentLocalMain.getPolicyNews(), HttpStatus.OK);
	}
	
	@GetMapping("/housenews")
	public ResponseEntity<Map<String, Map<String, String>>> getHouseNews() throws Exception {
		logger.info("getHouseNews");
		return new ResponseEntity<Map<String, Map<String, String>>> (JsoupComponentLocalMain.getHouseNews(), HttpStatus.OK);
	}

	
}
