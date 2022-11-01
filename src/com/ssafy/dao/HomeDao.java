package com.ssafy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.dto.HomeDto;
import com.ssafy.util.DBUtil;

public class HomeDao {
	
	private static final HomeDao instance = new HomeDao();
	
	public static HomeDao getInstance() {
		return instance;
	}

	private HomeDao() {}
	
	public int addFavorite(String userId, String sido, String gugun, String dong) throws SQLException {
		int result = -1;
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			con = DBUtil.getInstance().getConnection();
			String sql = "select distinct dongcode from home where sidoName=? and gugunName=? and dongName=?";
			
			pstm = con.prepareStatement(sql);
			pstm.setString(1, sido);
			pstm.setString(2, gugun);
			pstm.setString(3, dong);

			rs = pstm.executeQuery();
			
			if(rs.next()) {
				sql="insert into interestarea values (?,?)";
				pstm= con.prepareStatement(sql);
				pstm.setString(1, userId);
				pstm.setString(2, rs.getString("dongCode"));
				
				result=pstm.executeUpdate();
			}
		}finally {
			DBUtil.getInstance().close(rs,pstm,con);
		}
		
		return result;
	}
	
	public List<HomeDto> selectFavorite(String userId) throws SQLException{
		List<HomeDto> favoriteList = new ArrayList<HomeDto>();
		
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			
			con = DBUtil.getInstance().getConnection();
			String sql = "select * from home join (select dongCode from interestarea where user_id=?) as inter on home.dongCode = inter.dongCode group by home.dongcode";
			
			pstm = con.prepareStatement(sql);
			pstm.setString(1, userId);

			rs = pstm.executeQuery();
			
			while(rs.next()) {
				favoriteList.add(HomeDto.builder()
								.aptName(rs.getString("aptName"))
								.floor(rs.getInt("floor"))
								.dealAmount(rs.getString("dealAmount"))
								.area(rs.getInt("area"))
								.dongName(rs.getString("dongName"))
								.lat(rs.getString("lat"))
								.lng(rs.getString("lng"))
								.dealYear(rs.getInt("dealYear"))
								.dealMonth(rs.getInt("dealMonth"))
								.sidoName(rs.getString("sidoName"))
								.gugunName(rs.getString("gugunName"))
								.build());
			}
		}
		finally {
			DBUtil.getInstance().close(rs, pstm, con);
		}
		return favoriteList;
		
	}
	
	public List<HomeDto> bestPath(String dongName) throws SQLException{
		List<HomeDto> returnList = new ArrayList<HomeDto>();
		
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			
			con = DBUtil.getInstance().getConnection();
			String sql = "select DISTINCT aptName, lat, lng from home where dongName=? Limit 10";
			
			pstm= con.prepareStatement(sql);
			pstm.setString(1, dongName);
			
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				returnList.add(HomeDto.builder()
								.aptName(rs.getString("aptName"))
								.lat(rs.getString("lat"))
								.lng(rs.getString("lng"))
								.build());
			}
		}
		finally {
			DBUtil.getInstance().close(rs, pstm, con);
		}
		
		return returnList;
	
	}
	
	public List<HomeDto> selectAptName() throws SQLException{
		List<HomeDto> returnList = new ArrayList<HomeDto>();
		
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			
			con = DBUtil.getInstance().getConnection();
			String sql = "select * from home";
			
			pstm= con.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				returnList.add(HomeDto.builder()
								.aptName(rs.getString("aptName"))
								.floor(rs.getInt("floor"))
								.dealAmount(rs.getString("dealAmount"))
								.area(rs.getInt("area"))
								.dongName(rs.getString("dongName"))
								.lat(rs.getString("lat"))
								.lng(rs.getString("lng"))
								.dealYear(rs.getInt("dealYear"))
								.dealMonth(rs.getInt("dealMonth"))
								.build());
			}
		}
		finally {
			DBUtil.getInstance().close(rs, pstm, con);
		}
		
		return returnList;
	
	}
	
	public List<HomeDto> select(String dongName, int year, int month, int pageNo) throws SQLException{
		List<HomeDto> returnList = new ArrayList<HomeDto>();
		
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			
			con = DBUtil.getInstance().getConnection();
			String sql = "select * from home where dongName=? and dealYear=? and dealMonth=?";
			if(pageNo!=-1)
				sql += " LIMIT ?,10";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dongName);
			pstm.setInt(2, year);
			pstm.setInt(3, month);
			if(pageNo !=-1)
				// 1번 페이지 (0~9) 2번 (10~19),
				pstm.setInt(4, (pageNo-1)*10); //3-1*10=-7
			
			System.out.println(sql);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				returnList.add(HomeDto.builder()
								.aptName(rs.getString("aptName"))
								.floor(rs.getInt("floor"))
								.dealAmount(rs.getString("dealAmount"))
								.area(rs.getInt("area"))
								.dongName(dongName)
								.lat(rs.getString("lat"))
								.lng(rs.getString("lng"))
								.build());
			}
		}
		finally {
			DBUtil.getInstance().close(rs, pstm, con);
		}
		return returnList;
		
	}
}
