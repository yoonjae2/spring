<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<jsp:include page="../../include/title.jsp" />

<link href="<c:url value='/resources/css/user/request_hope_book_form.css' />" rel="stylesheet" type="text/css">

<jsp:include page="../include/request_hope_book_form_js.jsp" />

</head>
<body>

	<jsp:include page="../../include/header.jsp" />
	
	<jsp:include page="../include/nav.jsp" />
	
	<section>
		
		<div id="section_wrap">
		
			<div class="word">
			
				<h3>희망도서 요청</h3>
				
			</div>
		
			<div class="request_hope_book_form">
			
<%-- 				<form action="<c:url value='/book/user/requestHopeBookConfirm' />" name="request_hope_book_form" method="get">
 --%>					
 					<form name="request_hope_book_form" method="post">
					<input type="text"		name="hb_name" 			placeholder="* 요청하는 책의 이름."> <br>
					<input type="text"		name="hb_author" 		placeholder="* 요청하는 책의 작가명."> <br>
					<input type="text"		name="hb_publisher"		placeholder="요청하는 책의 출판사."> <br>
					<input type="text"		name="hb_publish_year" 	placeholder="요청하는 책의 출판년도."> <br>
					<input type="button"	value="request hope book" onclick="requestHopeBookForm();"> 
					<input type="reset"		value="reset">
					
				</form>
				
			</div>
		
		</div>
		
	</section>
	
	<jsp:include page="../../include/footer.jsp" />
	
</body>
</html>