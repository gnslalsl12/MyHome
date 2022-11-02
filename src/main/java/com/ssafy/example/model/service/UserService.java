package com.ssafy.example.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.example.model.dto.UserDto;

public interface UserService {
	public int deleteUser(String id) throws SQLException;
	
	public int updateUser(UserDto user) throws SQLException;
	
	public int registUser(UserDto user) throws SQLException;
	
	public UserDto login(String id, String pw) throws SQLException;
	
	List<UserDto> listMember(Map<String, Object> map) throws Exception;
}
