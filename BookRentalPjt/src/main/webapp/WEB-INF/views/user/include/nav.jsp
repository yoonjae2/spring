<%@page import="com.office.library.user.member.UserMemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link href="<c:url value='/resources/css/user/include/nav.css' />" rel="stylesheet" type="text/css">

<link
   href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
   rel="stylesheet"
   integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
   crossorigin="anonymous">
   
<!-- jquery -->
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>


<jsp:include page="./nav_js.jsp" />

<nav>
	
	<div id="nav_wrap">
		
		<%
		UserMemberVo loginedUserMemberVo = (UserMemberVo) session.getAttribute("loginedUserMemberVo");
		if (loginedUserMemberVo != null) {
		%>
		<div class="menu">
			<ul>
				<li><a href="<c:url value='/user/member/logoutConfirm' />" class='btn btn-danger' role='button'>로그아웃</a></li>
				<li><a href="<c:url value='/user/member/modifyAccountForm' />" class='btn btn-warning' role='button'>계정수정</a></li>
				<li><a href="<c:url value='/book/user/enterBookshelf' />" class='btn btn-primary' role='button'>나의책장</a></li>
			</ul>
		</div>
		<%
		} else {
		%>
		<div class="menu">
			<ul>
				<li><a href="<c:url value='/user/member/loginForm' />" class='btn btn-success' role='button'>로그인</a></li>
				<li><a href="<c:url value='/user/member/createAccountForm' />" class='btn btn-primary' role='button'>회원가입</a></li>
			</ul>
		</div>
		<%
		}
		%>
		
		<form class="form-inline my-2 my-lg-0 d-flex justify-content-center pt-2" action="<c:url value='/book/user/searchBookConfirm' />" name="search_book_form" method="get">
			<input class="form-control mr-sm-2 w-50 p-2" type="search"
				name="b_name" aria-label="Search">
			<button class="btn btn-outline-success my-2 my-sm-0" type="button" onclick="searchBookForm();">Search</button>
		</form>
		
	</div>
	
</nav>