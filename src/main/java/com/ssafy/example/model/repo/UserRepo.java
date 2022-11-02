package com.ssafy.example.model.repo;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.example.model.dto.UserDto;

@Mapper
public interface UserRepo {
	public int deleteUser(String id) throws SQLException;
	
	public int updateUser(UserDto user) throws SQLException;
	
	public int registUser(UserDto user) throws SQLException;
	
	public UserDto login(@Param("id") String id, @Param("pw") String pw) throws SQLException;

	List<UserDto> listMember(Map<String, Object> map) throws Exception;
}
