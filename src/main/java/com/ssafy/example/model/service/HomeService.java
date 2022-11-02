package com.ssafy.example.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.example.model.dto.HomeDto;

public interface HomeService {
	public int addFavorite(String userId, String sido, String gugun, String dong) throws SQLException;
	
	public List<HomeDto> selectFavorite(String userId) throws SQLException;
	
	public List<HomeDto> bestPath(String dongName, String dealYear, String dealMonth, String aptName) throws SQLException;
	
	public List<HomeDto> selectAptName() throws SQLException;
	
	public List<HomeDto> select(String dongName, int year, int month, int pageNo) throws SQLException;
}
