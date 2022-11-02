package com.ssafy.example.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.example.model.dto.UserDto;
import com.ssafy.example.model.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepo urepo;
	
	@Override
	public int deleteUser(String id) throws SQLException {
		return urepo.deleteUser(id);
	}

	@Override
	public int updateUser(UserDto user) throws SQLException {
		return urepo.updateUser(user);
	}

	@Override
	public int registUser(UserDto user) throws SQLException {
		return urepo.registUser(user);
	}

	@Override
	public UserDto login(String id, String pw) throws SQLException {
		return urepo.login(id, pw);
	}

	@Override
	public List<UserDto> listMember(Map<String, Object> map) throws Exception {
		return urepo.listMember(map);
	}

}
