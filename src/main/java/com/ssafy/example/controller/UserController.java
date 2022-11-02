package com.ssafy.example.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.example.model.dto.UserDto;
import com.ssafy.example.model.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/user")
@CrossOrigin("*")
@Slf4j
public class UserController {
	
	@Autowired
	UserService service;
	
	@PostMapping("/login")
	public String login(@ModelAttribute UserDto user, HttpSession session, Model model) throws SQLException {
		UserDto loginUser = service.login(user.getId(), user.getPassword());
		
		if(loginUser!=null) {
			session.setAttribute("loginUser", loginUser);
			return "redirect:/";
		}
		else {
			model.addAttribute("msg","로그인에 실패하셨습니다.");
			return "index";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "redirect:/index";
	}
	
	@PostMapping("/regist")
	public String regist(UserDto user) {
        log.debug("userRegister memberDto : {}", user);
        try {
            service.registUser(user);
//            return new ResponseEntity<Integer>(cnt, HttpStatus.CREATED);
        } catch (Exception e) {
           e.printStackTrace();
        }
        
        return "index";
    }
	
	@PostMapping("/update")
	public String update(UserDto user, Model model) throws SQLException {
		log.debug("id: {} pw= {} name= {} em = {} ",user.getId(),user.getPassword(),user.getUser_name(),user.getEmail());
		int result = service.updateUser(user);
		
		if(result!=0) {
			model.addAttribute("msg","회원 정보 수정이 완료되었습니다");
		}
		else {
			model.addAttribute("msg","오류가 발생했습니다.");
		}
		
		return "info";
	}
	
	@GetMapping("/delete")
	public String delete(UserDto user, Model model) throws SQLException {
		int result = service.deleteUser(user.getId());
		
		if(result!=0) {
			model.addAttribute("msg","회원 정보 수정이 완료되었습니다");
		}
		else {
			model.addAttribute("msg","오류가 발생했습니다.");
		}
		
		return "redirect:/index";
	}
	
}
