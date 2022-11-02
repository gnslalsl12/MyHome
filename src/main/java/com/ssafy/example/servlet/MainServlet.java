package com.ssafy.example.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.example.dao.HomeDao;
import com.ssafy.example.dao.UserDao;
import com.ssafy.example.model.dto.HomeDto;
import com.ssafy.example.model.dto.UserDto;

@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		String id = request.getParameter("id");
		
		int result = UserDao.getInstance().deleteUser(id);
		
		request.setAttribute("msg", "삭제 되었습니다");
		
		logout(request, response);
	}
	
	protected void updateUserInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {	
		String id = request.getParameter("id");
		String pw = request.getParameter("password");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		UserDto user = UserDto.builder().id(id).password(pw).userName(name).email(email).build();
		
		int result = UserDao.getInstance().updateUser(user);
		
		if(result == -1) {
			String msg = "fail";
			request.setAttribute("msg", msg);
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", user);
		}
		
		forward(request, response, "/info.jsp");
	}
	
	protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		forward(request, response, "/index.jsp");	
	}
	
	protected void registUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pw = request.getParameter("password");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		int result = -1;
		try {
			result = UserDao.getInstance().registUser(UserDto.builder().id(id).password(pw).userName(name).email(email).build());
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			String path = "/index.jsp";
			if (result == -1) {
				request.setAttribute("msg", "fail");
				path = "/sign_up.jsp";
			}
			forward(request, response, path);	
		}
		
		System.out.println(request.getAttribute("msg"));
	}
	
	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		String id = request.getParameter("id");
		String pw = request.getParameter("password");
		
		UserDto user = UserDao.getInstance().login(id, pw);
		
		if(user==null) {
			String msg= "login fail";
			request.setAttribute("msg", msg);
		} else {
			HttpSession session =  request.getSession();
			session.setAttribute("loginUser", user);
			//정보를 더 담아야 하네? -> 관심지역 정보를ㄹ 받아야 합니다..
			//정보를 뭔가 request에 담아야함
		}
		
		forward(request, response, "/index.jsp");
	}
	
	protected void addFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		String sido = request.getParameter("sido");
		String gugun = request.getParameter("gugun");
		String dong = request.getParameter("dong");
		
		UserDto user= (UserDto) request.getSession().getAttribute("loginUser");
		
		int result = HomeDao.getInstance().addFavorite(user.getId(), sido, gugun, dong);
	}
	
	protected void temp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	/*
              <select class="form-select bg-secondary text-light" id="sido" name='sido'>

              <select class="form-select bg-secondary text-light" id="gugun" name='gugun'>


              <select class="form-select bg-secondary text-light" id="dong" name='dong'>


              <select class="form-select bg-dark text-light" id="year" name='year'>

              <select class="form-select bg-dark text-light" id="month" name='month'>
	 * 
	 * */
		
	
	protected void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		// TODO Auto-generated method stub
//		String sido = request.getParameter("sido");
//		String gugun = request.getParameter("gugun");
//		System.out.println(sido+gugun+(dong+year)+month);

		String dongName = request.getParameter("dong");
		int year = Integer.parseInt(request.getParameter("year"));
		int month = Integer.parseInt(request.getParameter("month"));
		int pageNo = request.getParameter("pageNo")==null? -1:Integer.parseInt(request.getParameter("pageNo"));
		List<HomeDto> resultList = HomeDao.getInstance().select(dongName, year, month, pageNo);
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("results", resultList);
		map.put("resultLen", resultList.size());
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(map);
		
		response.setContentType("application/json;charset=utf-8");
		System.out.println(json);
		response.getWriter().write(json);
	}
	
	protected void getFavorites(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		// TODO Auto-generated method stub
//		String sido = request.getParameter("sido");
//		String gugun = request.getParameter("gugun");
//		System.out.println(sido+gugun+(dong+year)+month);
		UserDto user = (UserDto) request.getSession().getAttribute("loginUser");
		
		List<HomeDto> favorteList = HomeDao.getInstance().selectFavorite(user.getId());
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("regcodes", favorteList);
//		map.put("resultLen", resultList.size());
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(map);
		
		response.setContentType("application/json;charset=utf-8");
		System.out.println(json);
		response.getWriter().write(json);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String action = request.getParameter("action");
		
		try {
			if(action == null) {
				//보류
				forward(request, response, "/index.jsp");
			} else if (action.equals("search")) {
				search(request, response);
			} else if (action.equals("login")) {
				login(request, response);
			} else if (action.equals("registUser")) {
				registUser(request, response);
			} else if(action.equals("logout")) {
				logout(request, response);
			} else if(action.equals("updateUser")) {
				updateUserInfo(request, response);
			} else if(action.equals("deleteUser")) {
				deleteUser(request, response);
			} else if(action.equals("favorite")) {		
				getFavorites(request,response);
			} else if(action.equals("addFavorite")) {
				addFavorite(request,response);
			} else if(action.equals("searchAptName")) {
				searchApt(request,response);
			} else if(action.equals("bestPath")) {
				calculatePath(request,response);
			}
		} catch(SQLException e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
		System.out.println(action);
	}
	
	protected void calculatePath(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		String dongName = request.getParameter("dong");
		String dealYear = request.getParameter("dealYear");
		String dealMonth = request.getParameter("dealMonth");
		String aptName = request.getParameter("aptName");
		
		String la = request.getParameter("La");
		String ma = request.getParameter("Ma");
		
		List<HomeDto> info = HomeDao.getInstance().bestPath(dongName,dealYear,dealMonth,aptName);
		List<FindWayPrim.selectedApt> path = new ArrayList<>();
		//진짜진짜 최종
		List<Integer> answer = new ArrayList<>();
		List<HomeDto> bestWay = new ArrayList<>();
		
		System.out.println(info.size());

		for(HomeDto i: info) {
			path.add(new FindWayPrim.selectedApt(Double.parseDouble(i.getLat()),Double.parseDouble(i.getLng())));
		}
		
		answer = FindWayPrim.Prim(Double.parseDouble(ma), Double.parseDouble(la), path);
		System.out.println("path size : "+ path.size());
		System.out.println("answer size : "+answer.size());
		
		for(int i=0;i<answer.size();i++) {
			bestWay.add(info.get(answer.get(i)));
		}
		
//		for(HomeDto b: bestWay) {
//			System.out.println(b.getAptName());
//		}
		
		request.setAttribute("bestPath", bestWay);
		
		forward(request,response,"/best_path.jsp");
	}
	
	protected void searchApt(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		//view에서 다 받아오기
		List<HomeDto> info = HomeDao.getInstance().selectAptName();
		List<HomeDto> result = new ArrayList<>();
		//검색단어
		String pattern = request.getParameter("searchName");
		
		//list aptName 클래스 불러와서 넘겨주기
		for(HomeDto h: info) {
			if(AptSearchKMP.KMP(h.getAptName(), pattern)==1) {
				result.add(h);
				System.out.println(h.getAptName());
			}
		}
		
		
		
		//list 받아서 새창으로  1) jsp 2)json으로 돌려서 fetch(비동기)
		request.setAttribute("searchApt", result);
		
		//jsp로 보내면 forward
		forward(request,response,"/search_apt.jsp");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}
	
	protected void forward(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}
	
	protected void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println(request.getContextPath()+path);
		response.sendRedirect(request.getContextPath()+path);
	}
}
