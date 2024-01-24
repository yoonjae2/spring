<%@page import="com.office.library.admin.member.AdminMemberVo"%>

<%@page import="com.office.library.admin.member.AdminMemberController"%>

<%@page import="org.springframework.beans.factory.annotation.Autowired"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<jsp:include page="../include/title.jsp" />

<link href="<c:url value='/resources/css/admin/home.css' />"
	rel="stylesheet" type="text/css">

</head>
<body>

	<jsp:include page="../include/header.jsp" />

	<jsp:include page="./include/nav.jsp" />
	

	<section>

		<%
		AdminMemberVo loginedAdminMemberVo = (AdminMemberVo) session.getAttribute("loginedAdminMemberVo");
		if (loginedAdminMemberVo == null) {
		%>
		<div id="section_wrap">

			<div class="word">

				<div class="d-flex card md-3 mx-auto"
					style="width: 20rem; border: none;">
					<h2>관리자 전용페이지</h2>

					<img src="/library/resources/img/admin/admin.jpg"
						class="card-img-top img-fluid"
						onclick="location.href='http://localhost:8080/library/admin/member/loginForm'"
						style="cursor: pointer;">
				</div>

			</div>

		</div>
		<%
		} else {
		%>
		<c:url value='/admin/memeber/login_ok' var='okurl'>
					
				</c:url>
		<%-- <jsp:forward page="/admin/member/login_ok"/> --%>
	
			<%
		}
			%>
		</section>

	<jsp:include page="../include/footer.jsp" />



</body>
</html>