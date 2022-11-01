<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<%@ include file='/include/header.jsp'%>
  
  <div class="container" style="margin-bottom: 60px; margin-top: 40px;">
    <form class="row g-3" id='formId' method='post' action='${root }/main'>
    <input type='hidden' id='hidden_input' name='action' value='updateUser'>
      <div class="col-md-12">
        <label for="inputEmail4" class="form-label">아이디</label>
        <input type="email" class="form-control" id="inputEmail4" name='id' value="${loginUser.id}" readonly>
      </div>
      <div class="col-md-12">
        <label for="inputPassword4" class="form-label">비밀번호</label>
        <input type="password" class="form-control" id="inputPassword4" name='password' value="${loginUser.password}">
      </div>
      <div class="col-12">
        <label for="inputAddress" class="form-label">이름</label>
        <input type="text" class="form-control" id="inputAddress" name='name' value="${loginUser.userName}">
      </div>

      <div class="col-12">
        <label for="inputAddress2" class="form-label">이메일</label>
        <input type="email" class="form-control" id="inputAddress2" name='email' value="${loginUser.email}">
      </div>

      <div class="col-12">
        <button type="button" id='update' class="btn btn-primary">정보수정</button>
        <button type="button" id='delete' class="btn btn-primary">회원탈퇴</button>
      </div>
    </form>
  </div>
  
  <script>
  	document.querySelector('#update').addEventListener('click', ()=>{
  		
  		document.querySelector('#formId').submit();
  		
  	})
  	
  	  	document.querySelector('#delete').addEventListener('click', ()=>{
  		
  	  	document.querySelector('#hidden_input').value = 'deleteUser'
  	  		
  		document.querySelector('#formId').submit();
  		
  	})
  
  </script>
  










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