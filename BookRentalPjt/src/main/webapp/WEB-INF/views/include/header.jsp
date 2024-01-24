<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="<c:url value='/resources/css/include/header.css' />"
	rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/jquery-3.6.3.js"
	integrity="sha256-nQLuAZGRRcILA+6dMBOvcRh5Pe310sBpanc6+QBmyVM="
	crossorigin="anonymous"></script>
<script type="text/javascript">
	$(function() {
		console.log('HEADER DOCUMENT READY!!');

		/*
		console.log('---> ' + $(location).attr('href'));
		console.log('---> ' + $(location).attr('pathname'));
		 */

		let pathname = $(location).attr('pathname');
		let isIncludeUser = pathname.includes('/user') || pathname==='/library/';

		if (isIncludeUser) {
			$('#header_wrap .menu ul li a.user').css('text-decoration',
					'green wavy underline');

		} else {
			$('#header_wrap .menu ul li a.admin').css('text-decoration',
					'red wavy underline');
		}

	});
	
</script>

<header style="background-color: black;">

	<div id="header_wrap">

		<div class="menu">
			<ul style="list-style: none;">
				<li><a class="user" href="<c:url value='/' />" style="text-decoration: none; color: #fff; font-size: 18px">USER HOME</a></li>
				<li><a class="admin" href="<c:url value='/admin' />" style="text-decoration: none; color: #fff; font-size: 18px">ADMIN HOME</a></li>
			</ul>
		</div>

		<div class="title">
			 <div class="card md-3" style="width: 10rem;">
				<img src="/library/resources/img/user/로고1.PNG"
					class="card-img-top img-fluid" alt="..." 
					onclick="location.href='http://localhost:8080/library/'" 
					style="cursor: pointer;">
			</div>  
	
		</div>
		
		
		
	</div>

</header>