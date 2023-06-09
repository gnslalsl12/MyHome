<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href='https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx' crossorigin='anonymous'>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
  <script src='https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js' integrity='sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa' crossorigin='anonymous'></script>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <c:set var='root' value="${pageContext.request.contextPath}"/>
  
  <title>Document</title>

  <style>
    ul{
      list-style:none;
    }
  </style>
</head>
<body>
  <div class="header">
    <nav class="navbar" style="background-color: skyblue;">
      <div class="container">
        <div style="padding-left: 80%;"> 
          <button type="button" onclick="location.href='./sign_up'" class="btn btn-primary" id="prelogin1">회원가입</button>
          <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#loginModal" id="prelogin2">로그인</button>
          <button type="button" onclick="location.href='./sign_up'" class="btn btn-primary" id="login1" style="display: none;">회원정보</button>
          <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#loginModal" id="login2" style="display: none;">로그아웃</button>
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
            <form class="form-floating mb-3" action="#" id="loginForm">
              <div class="form-floating mb-3">
                <input type="email" class="form-control" id="floatingInput" placeholder="name@example.com">
                <label for="floatingInput">아이디를 입력하세요</label>
              </div>
              <div class="form-floating">
                <input type="password" class="form-control" id="floatingPassword" placeholder="Password">
                <label for="floatingPassword">비밀번호를 입력하세요</label>

                <div class="modal-footer">
                  <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                  <button type="button" class="btn btn-primary" onclick="location.href='./index'" id="b-login">로그인</button>
                </div>
              </div>
            </form>
          </div>
          
        </div>
      </div>
    </div>
    
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
      <div class="container">
          <a class="navbar-brand" href="/index"><img src="./img/aaaa.jpg" alt="" width="150" height="150"></a>
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
  
  <div class="container" style="margin-bottom: 60px; margin-top: 40px;">
    <form class="row g-3" action="${root}/user/regist" method="post">
      <div class="col-md-12">
        <label for="inputEmail4" class="form-label">아이디</label>
        <input type="text" class="form-control" id="inputEmail4" name="id">
      </div>
      <div class="col-md-12">
        <label for="inputPassword4" class="form-label">비밀번호</label>
        <input type="password" class="form-control" id="inputPassword4" name="password">
      </div>
      <div class="col-12">
        <label for="inputAddress" class="form-label">이름</label>
        <input type="text" class="form-control" id="inputAddress" placeholder="김싸피" name="userName">
      </div>
      <div class="col-12">
        <label for="inputAddress" class="form-label">이메일</label>
        <input type="email" class="form-control" id="inputAddress" placeholder="id@site.com" name="email">
      </div>

      <div class="col-12">
        <button type="submit" class="btn btn-primary">가입신청</button>
      </div>
    </form>
  </div>
  
  <footer id="footer" class="clearfix ">

    <!-- .footer start -->
    <!-- ================ -->
    <div class="footer">
      <div class="container">
        <div class="footer-inner">
          <div class="row">
           <div class="col-md-1">
              <div class="footer-content">
                <img alt="" src="./img/logo.png"  width="100">
              </div>
            </div>
            <div class="col-md-8">
              <div class="footer-content">
                <h2 class="title">Find Us</h2>
                <div class="separator-2"></div>
                <ul class="list-icons">
                  <li><i class="bi-geo-alt-fill" style="margin-right: 5px;"></i> (SSAFY) 서울시 강남구  테헤란로 멀티스퀘어</li>
                  <li><i class="bi-telephone-forward-fill" style="margin-right: 5px;"></i> 1544-9001</li>
                  <li><i class="bi-envelope-fill" style="margin-right: 10px;"></i><a href="#">admin@ssafy.com</a></li>
                </ul>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    
    <!-- .footer end -->

    <!-- .subfooter start -->
    <!-- ================ -->
    <div class="subfooter">
      <div class="container">
        <div class="subfooter-inner">
          <div class="row">
            <div class="col-md-12">
              <p class="text-center">Copyright by SSAFY. All rights reserved.</p>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- .subfooter end -->

  </footer>
<!-- footer end -->
<script src="./js/main.js"></script>
</div>
</body>
</html>