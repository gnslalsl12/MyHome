package com.ssafy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ssafy.dto.UserDto;
import com.ssafy.util.DBUtil;

public class UserDao {
	
	private static final UserDao instance = new UserDao();
	
	public static UserDao getInstance() {
		return instance;
	}
	
	private UserDao() {}
	
	public int deleteUser(String id) throws SQLException {
		int result = -1;
		
		Connection con = null;
		PreparedStatement pstm = null;
		try {
			con = DBUtil.getInstance().getConnection();
			String sql = "delete from user where id = ?";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, id);
			
			result= pstm.executeUpdate();
		} finally {
			DBUtil.getInstance().close(pstm, con);
		}
		
		
		return result;
		
	}
	
	public int updateUser(UserDto user) throws SQLException {
		int result = -1;
		
		Connection con = null;
		PreparedStatement pstm = null;
		try {
			con = DBUtil.getInstance().getConnection();
			String sql = "update user set password = ?, user_name=?, email=? where id = ?";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, user.getPassword());
			pstm.setString(2, user.getUserName());
			pstm.setString(3, user.getEmail());
			pstm.setString(4, user.getId());
			
			result= pstm.executeUpdate();
		} finally {
			DBUtil.getInstance().close(pstm, con);
		}
		
		
		return result;
	}
	
	public int registUser(UserDto user) throws SQLException {
		int result = -1;
		Connection con = null;
		PreparedStatement pstm = null;
		
		try {
			con = DBUtil.getInstance().getConnection();
			String sql = "insert into user values (?, ?, ? ,?)";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, user.getId());
			pstm.setString(2, user.getPassword());
			pstm.setString(3, user.getUserName());
			pstm.setString(4, user.getEmail());
			
			result = pstm.executeUpdate();
		} finally {
			DBUtil.getInstance().close(pstm, con);
		}
		
		return result;
	}

	
	public UserDto login(String id, String pw) throws SQLException {
		UserDto result = null;
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			con = DBUtil.getInstance().getConnection();
			String sql = "select * from user where id = ? and password = ?";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, id);
			pstm.setString(2, pw);
			rs = pstm.executeQuery();
			
			if(rs.next())
				result = UserDto.builder().id(rs.getString("id")).password(rs.getString("password")).email(rs.getString("email")).userName(rs.getString("user_name")).build();
		} finally {
			DBUtil.getInstance().close(rs, pstm, con);
		}
		
		
		return result;
	}
}
