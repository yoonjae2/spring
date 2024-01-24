<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<jsp:include page="../../include/title.jsp" />

<link href="<c:url value='/resources/css/admin/modify_book_form.css' />" rel="stylesheet" type="text/css">

<jsp:include page="../include/modify_book_form_js.jsp" />

</head>
<body>

	<jsp:include page="../../include/header.jsp" />
	
	<jsp:include page="../include/nav.jsp" />
	
	<section>
		
		<div id="section_wrap">
		
			<div class="word">
			
				<h3>MODIFY BOOK FORM</h3>
				
			</div>
		
			<div class="modify_book_form">
			
				<form action="<c:url value='/admin/book/modifyBookConfirm' />" name="modify_book_form" method="post" enctype="multipart/form-data">
					
					<input type="hidden" name="b_no" value="${bookVo.b_no}">
					
					<input type="text" class="w-25" name="b_name" value="${bookVo.b_name}" placeholder="INPUT BOOK NAME."> <br>
					<input type="text" class="w-25" name="b_author" value="${bookVo.b_author}" placeholder="INPUT BOOK AUTHOR."> <br>
					<input type="text" class="w-25" name="b_publisher" value="${bookVo.b_publisher}" placeholder="INPUT BOOK PUBLISHER."> <br>
					<input type="text" class="w-25" name="b_publish_year" value="${bookVo.b_publish_year}" placeholder="INPUT BOOK PUBLISH YEAR."> <br>
					<input type="text" class="w-25" name="b_isbn" value="${bookVo.b_isbn}" placeholder="INPUT BOOK ISBN."> <br>
					<input type="text" class="w-25" name="b_call_number" value="${bookVo.b_call_number}" placeholder="INPUT BOOK CALL NUMBER."> <br>
					
					<select name="b_genre" class="w-25">
						<option value="">장르를 선택해주세요</option>
						<option value="0" <c:if test="${bookVo.b_genre eq '0'}"> selected </c:if>>판타지</option>
						<option value="1" <c:if test="${bookVo.b_genre eq '1'}"> selected </c:if>>무협</option>
						<option value="2" <c:if test="${bookVo.b_genre eq '2'}"> selected </c:if>>현대</option>
						<option value="3" <c:if test="${bookVo.b_genre eq '3'}"> selected </c:if>>로맨스</option>
						<option value="4" <c:if test="${bookVo.b_genre eq '4'}"> selected </c:if>>라이트노벨</option>
						<option value="5" <c:if test="${bookVo.b_genre eq '5'}"> selected </c:if>>대체역사</option>
						<option value="6" <c:if test="${bookVo.b_genre eq '6'}"> selected </c:if>>문학</option>
						<option value="7" <c:if test="${bookVo.b_genre eq '7'}"> selected </c:if>>SF</option>
						<option value="8" <c:if test="${bookVo.b_genre eq '8'}"> selected </c:if>>스포츠</option>
						<option value="9" <c:if test="${bookVo.b_genre eq '9'}"> selected </c:if>>기타</option>
					</select><br>
					
					<select name="b_rantal_able" class="w-25">
						<option value="">SELECT BOOK RANTAL ABLE.</option>
						<option value="0" <c:if test="${bookVo.b_rantal_able eq '0'}"> selected </c:if>>UNABLE.</option>
						<option value="1" <c:if test="${bookVo.b_rantal_able eq '1'}"> selected </c:if>>ABLE.</option>
					</select><br>					
					<input type="file" class="w-25" name="file"><br>
					<input type="button" class='btn btn-secondary' value="modify book" onclick="modifyBookForm();"> 
					<input type="reset" class='btn btn-danger' value="reset">
					
				</form>
				
			</div>
		
		</div>
		
	</section>
	
	<jsp:include page="../../include/footer.jsp" />
	
</body>
</html>