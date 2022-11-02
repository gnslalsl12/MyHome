package com.ssafy.example.controller;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.example.model.dto.HomeDto;
import com.ssafy.example.model.dto.UserDto;
import com.ssafy.example.model.service.HomeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/main")
@Slf4j
public class HomeRestController {

	@Autowired
	HomeService service;

	@RequestMapping("/addFavorite")
	public ResponseEntity<Object> addFavorite(@RequestParam String sido, @RequestParam String gugun,
			@RequestParam String dong, HttpSession session) throws SQLException {
		log.debug("{} {} {} ", sido, gugun, dong);
		Object tempobj = session.getAttribute("loginUser");
		String id = "";
		if (tempobj != null) {
			UserDto tempuser = (UserDto) tempobj;
			id = tempuser.getId();
		} else {
			return new ResponseEntity<Object>(new ArrayList<Object>(), HttpStatus.BAD_REQUEST);
		}
		// 정보 추가 성공
		try {
		service.addFavorite(id, sido, gugun, dong);
		
		}catch(SQLIntegrityConstraintViolationException e) {
			return new ResponseEntity<Object>("해당 지역에는 아파트가 없습니다.", HttpStatus.BAD_REQUEST);
		}catch(DuplicateKeyException e) {
			return new ResponseEntity<Object>("해당 지역은 이미 관심 지역으로 등록되어있습니다.", HttpStatus.BAD_REQUEST);
		}
		// 즐겨찾기 목록 조회
		List<HomeDto> favorites = service.selectFavorite(id);
		log.debug("favorite size: {}", favorites.size());
		return new ResponseEntity<Object>(favorites, HttpStatus.OK);
	}
	
	@RequestMapping("/search")
	public ResponseEntity<List<HomeDto>> search(@RequestBody Map<String,Object> map, HttpSession session, HttpServletRequest request) throws SQLException {
	
		log.debug("!!!!!!!!!!!!!!!!!!!!!"+map.toString());
		
		//log.debug("search=> {} {} {} {}", dongName, year, month);
		
		// 정보 추가 성공
		
			List<HomeDto> list = service.select((String) map.get("dongName"), Integer.parseInt((String) map.get("year")), Integer.parseInt((String)map.get("month")));
			log.debug("List size: {}", list.size());
			return new ResponseEntity<List<HomeDto>>(list, HttpStatus.OK);	
		
		
	}
}
