<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<jsp:include page="../../include/title.jsp" />

<link href="<c:url value='/resources/css/user/create_account_form.css' />" rel="stylesheet" type="text/css">

<jsp:include page="../include/create_account_form_js.jsp" />

</head>
<body>

	<jsp:include page="../../include/header.jsp" />
	
	<jsp:include page="../include/nav.jsp" />
	
	<section>
		
		<div id="section_wrap">
		
			<div class="word">
			
				<h3>회원가입</h3>
				
			</div>
		
			<div class="create_account_form">
			
				<form action="<c:url value='/user/member/createAccountConfirm' />" name="create_account_form" method="post">
					
					<input type="text" class="w-25" name="u_m_id" placeholder="USER ID."> <br>
					<input type="password" class="w-25" name="u_m_pw" placeholder="USER PW."> <br>
					<input type="password" class="w-25" name="u_m_pw_again" placeholder="USER PW AGAIN."> <br>
					<input type="text" class="w-25" name="u_m_name" placeholder="USER NAME."> <br>
					<select name="u_m_gender" class="w-25">
						<option value="">SELECET USER GENDER.</option>
						<option value="M">Man</option>
						<option value="W">Woman</option>
					</select> <br>
					<input type="email" class="w-25" name="u_m_mail"	placeholder="E-MAIL." ><br>
					<input type="text" class="w-25" name="u_m_phone"	placeholder="PHONE_NUMBER."> <br>
					<input type="button" class='btn btn-secondary' value="완료" onclick="createAccountForm();"> 
					<input type="reset" class='btn btn-danger' value="취소">
					
				</form>
				
			</div>
			
			<div class="login">
				
				<a href="<c:url value='/user/member/loginForm' />">login</a>
				
			</div>
		
		</div>
		
	</section>
	
	<jsp:include page="../../include/footer.jsp" />
	
</body>
</html>