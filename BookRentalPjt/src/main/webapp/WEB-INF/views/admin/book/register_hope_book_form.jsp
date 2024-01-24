<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<jsp:include page="../../include/title.jsp" />

<link href="<c:url value='/resources/css/admin/register_hope_book_form.css' />" rel="stylesheet" type="text/css">

<jsp:include page="../include/register_hope_book_form_js.jsp" />

</head>
<body>

	<jsp:include page="../../include/header.jsp" />
	
	<jsp:include page="../include/nav.jsp" />
	
	<section>
		
		<div id="section_wrap">
		
			<div class="word">
			
				<h3>REGISTER HOPE BOOK FORM</h3>
				
			</div>
		
			<div class="register_hope_book_form">
			
				<form action="<c:url value='/book/admin/registerHopeBookConfirm' />" name="register_hope_book_form" method="post" enctype="multipart/form-data">
					
					<input type="hidden" name="hb_no" value="${hopeBookVo.hb_no}">
					
					<input type="text" class="w-25" name="b_name" value="${hopeBookVo.hb_name}" placeholder="INPUT BOOK NAME."> <br>
					<input type="text" class="w-25" name="b_author" value="${hopeBookVo.hb_author}" placeholder="INPUT BOOK AUTHOR."> <br>
					<input type="text" class="w-25" name="b_publisher" value="${hopeBookVo.hb_publisher}" placeholder="INPUT BOOK PUBLISHER."> <br>
					<input type="text" class="w-25" name="b_publish_year" value="${hopeBookVo.hb_publish_year}" placeholder="INPUT BOOK PUBLISH YEAR."> <br>
					<input type="text" class="w-25" name="b_isbn" placeholder="INPUT BOOK ISBN."> <br>
					<input type="text" class="w-25" name="b_call_number" placeholder="INPUT BOOK CALL NUMBER."> <br>
					
					<select name="b_genre" class="w-25">
						<option value="">장르를 선택해주세요</option>
						<option value="0">판타지</option>
						<option value="1">무협</option>
						<option value="2">현대</option>
						<option value="3">로맨스</option>
						<option value="4">라이트노벨</option>
						<option value="5">대체역사</option>
						<option value="6">문학</option>
						<option value="7">SF</option>
						<option value="8">스포츠</option>
						<option value="9">기타</option>
					</select><br>
					
					<select name="b_rantal_able" class="w-25">
						<option value="">SELECT BOOK RANTAL ABLE.</option>
						<option value="0">UNABLE.</option>
						<option value="1">ABLE.</option>
					</select><br>
					<input type="file" class="w-25" name="file"><br>
					<input type="button" class='btn btn-secondary' value="register book" onclick="registerHopeBookForm();"> 
					<input type="reset" class='btn btn-danger' value="reset">
					
				</form>
				
			</div>
		
		</div>
		
	</section>
	
	<jsp:include page="../../include/footer.jsp" />
	
</body>
</html>