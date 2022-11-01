<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>최적의 경로</title>
</head>
<body>
	<c:if test="${!empty bestPath}" >
	<table class="table table-hover text-center" id="t">
                <tr>
                  <th>최적 경로</th>
                </tr>
                <tbody id="aptlist">
	<c:forEach var="apt" items="${bestPath }">
	<tr>
	<td>${apt.aptName }</td>
	</tr>
	</c:forEach>
	</tbody>
    </table>
	</c:if>
	
	<c:if test="${empty bestPath }">
	데이터가 없습니다.
	</c:if>
</body>
</html>