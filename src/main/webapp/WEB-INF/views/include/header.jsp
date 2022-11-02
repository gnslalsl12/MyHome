<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<c:set var="root" value="${pageContext.request.contextPath}"></c:set>

  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href='https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx' crossorigin='anonymous'>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
  <script src='https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js' integrity='sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa' crossorigin='anonymous'></script>
  <title>Document</title>

  <style>
    ul{
      list-style:none;
    }
  </style>
<script>
	<c:if test="${!empty msg}">	
		alert("${msg}");
	</c:if>
</script>
</head>
<body>

  <div class="header">
    <nav class="navbar" style="background-color: skyblue;">
      <div class="container">
        <div style="padding-left: 80%;">
        <c:if test="${empty loginUser }">
          <button type="button" onclick="location.href='./sign_up'" id="prelogin1" class="btn btn-primary">회원가입</button>
          <button type="button" class="btn btn-primary" data-bs-toggle="modal" id="prelogin2" data-bs-target="#loginModal">로그인</button>
        </c:if>
        <c:if test="${!empty loginUser}">
          <button type="button" onclick="location.href='./info'" class="btn btn-primary" id="login1">회원정보</button>
          <button type="button" onclick="location.href='${root}/user/logout'" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#loginModal" id="login2" name='logout'>로그아웃</button>
        </c:if>
        </div>
      </div>
    </nav>

    <!-- Modal -->
    <div class="modal fade" id="loginModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">로그인해주세요
            </h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            <form class="form-floating mb-3" action="${root}/user/login" id="loginForm" method='post'>
              <div class="form-floating mb-3">
                <input type="text" class="form-control" id="floatingInput" name='id'>
                <label for="floatingInput">아이디를 입력하세요</label>
              </div>
              <div class="form-floating">
                <input type="password" class="form-control" id="floatingPassword" name='password'>
                <label for="floatingPassword">비밀번호를 입력하세요</label>

                <div class="modal-footer">
                  <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                  <button type="submit" class="btn btn-primary" id="b-login">로그인</button>
                </div>
              </div>
            </form>
          </div>
          
        </div>
      </div>
    </div>

    <nav class="navbar navbar-expand-lg navbar-light bg-light">
      <div class="container">
          <a class="navbar-brand" href="${root}/index"><img src="./img/aaaa.jpg" alt="" width="150" height="150"></a>
          <div class="collapse navbar-collapse" id="navbarNav" style="padding-left: 70%;">
              <ul class="navbar-nav">
                  <li class="nav-item">
                      <a class="nav-link active" aria-current="page" href="#">공지사항</a>
                  </li>
                  <li class="nav-item">
                      <a class="nav-link active" aria-current="page" href="#">오늘의 뉴스</a>
                  </li>
              </ul>
          </div>
      </div>
  </nav>
  </div>