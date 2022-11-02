package com.ssafy.example.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.example.model.dto.HomeDto;
import com.ssafy.example.model.repo.HomeRepo;

@Service
public class HomeServiceImpl implements HomeService{
	
	@Autowired
	HomeRepo hrepo;
	
	@Override
	public int addFavorite(String userId, String sido, String gugun, String dong) throws SQLException {
		return hrepo.addFavorite(userId, sido, gugun, dong);
	}

	@Override
	public List<HomeDto> selectFavorite(String userId) throws SQLException {
		return hrepo.selectFavorite(userId);
	}

	@Override
	public List<HomeDto> bestPath(String dongName, String dealYear, String dealMonth, String aptName)
			throws SQLException {
		return hrepo.bestPath(dongName, dealYear, dealMonth, aptName);
	}

	@Override
	public List<HomeDto> selectAptName() throws SQLException {
		return hrepo.selectAptName();
	}

	@Override
	public List<HomeDto> select(String dongName, int year, int month, int pageNo) throws SQLException {
		return hrepo.select(dongName, year, month, pageNo);
	}

}
