package com.ssafy.example.model.repo;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.example.model.dto.HomeDto;

@Mapper
public interface HomeRepo {
	public int addFavorite(@Param("userId") String userId, @Param("sido") String sido, @Param("gugun") String gugun, @Param("dong") String dong) throws SQLException;
	
	public List<HomeDto> selectFavorite(String userId) throws SQLException;
	
	public List<HomeDto> bestPath(@Param("dongName") String dongName, @Param("dealYear")  String dealYear, @Param("dealMonth") String dealMonth, @Param("aptName") String aptName) throws SQLException;
	
	public List<HomeDto> selectAptName() throws SQLException;
	
	public List<HomeDto> select(@Param("dongName") String dongName, @Param("year") int year, @Param("month") int month) throws SQLException;
}
