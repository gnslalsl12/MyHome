<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>검색 결과</title>
</head>
<body>
	<c:if test="${!empty searchApt}" >
	<table class="table table-hover text-center" id="t">
                <tr>
                  <th>아파트이름</th>
                  <th>층</th>
                  <th>면적</th>
                  <th>법정동</th>
                  <th>거래금액</th>
                  <th>매매년도</th>
                  <th>매매월</th>
                </tr>
                <tbody id="aptlist">
	<c:forEach var="apt" items="${searchApt }">
	<tr>
	<td>${apt.aptName }</td>
	<td>${apt.floor }</td>
	<td>${apt.area }</td>
	<td>${apt.dongName }</td>
	<td>${apt.dealAmount }</td>
	<td>${apt.dealYear }</td>
	<td>${apt.dealMonth }</td>
	</tr>
	</c:forEach>
	</tbody>
    </table>
	</c:if>
	
	<c:if test="${empty searchApt }">
	데이터가 없습니다.
	</c:if>
</body>
</html>