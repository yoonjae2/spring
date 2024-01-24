<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<jsp:include page="../../include/title.jsp" />

<link href="<c:url value='/resources/css/admin/book_detail.css' />" rel="stylesheet" type="text/css">



<jsp:include page="../include/book_detail_js.jsp" />

</head>
<body>

	<jsp:include page="../../include/header.jsp" />
	
	<jsp:include page="../include/nav.jsp" />
	
	<section>
		
		<div id="section_wrap">
		
			<div class="word">
			
				<h3>BOOK DETAIL</h3>
				
			</div>
			
			<div class="book_detail">
			
				<ul class="d-flex justify-content-center align-items-center">
					<li>
						<img src="<c:url value="/libraryUploadImg/${bookVo.b_thumbnail}"/>">
					</li>
					<li>
						<table>
							<tr>
								<td>도서명</td>
								<td>${bookVo.b_name}</td>
							</tr>
							<tr>
								<td>저자</td>
								<td>${bookVo.b_author}</td>
							</tr>
							<tr>
								<td>장르</td>
								<td><c:choose>
										<c:when test="${bookVo.b_genre eq '0'}">
											<c:out value="판타지" />
										</c:when>
										<c:when test="${bookVo.b_genre eq '1'}">
											<c:out value="무협" />
										</c:when>
										<c:when test="${bookVo.b_genre eq '2'}">
											<c:out value="현대" />
										</c:when>
										<c:when test="${bookVo.b_genre eq '3'}">
											<c:out value="로맨스" />
										</c:when>
										<c:when test="${bookVo.b_genre eq '4'}">
											<c:out value="라이트노벨" />
										</c:when>
										<c:when test="${bookVo.b_genre eq '5'}">
											<c:out value="대체역사" />
										</c:when>
										<c:when test="${bookVo.b_genre eq '6'}">
											<c:out value="문학" />
										</c:when>
										<c:when test="${bookVo.b_genre eq '7'}">
											<c:out value="SF" />
										</c:when>
										<c:when test="${bookVo.b_genre eq '8'}">
											<c:out value="스포츠" />
										</c:when>
										<c:when test="${bookVo.b_genre eq '9'}">
											<c:out value="기타" />
										</c:when>
										<c:otherwise>
											<c:out value="기타" />
										</c:otherwise>
									</c:choose></td>
							</tr>
							<tr>
								<td>발행처</td>
								<td>${bookVo.b_publisher}</td>
							</tr>
							<tr>
								<td>발행년도</td>
								<td>${bookVo.b_publish_year}</td>
							</tr>
							<tr>
								<td>ISBN</td>
								<td>${bookVo.b_isbn}</td>
							</tr>
							<tr>
								<td>청구기호</td>
								<td>${bookVo.b_call_number}</td>
							</tr>
							<tr>
								<td>대출가능</td>
								<td>
								<c:choose>
									<c:when test="${bookVo.b_rantal_able eq '0'}"> <c:out value="X" /> </c:when>
									<c:when test="${bookVo.b_rantal_able eq '1'}"> <c:out value="O" /> </c:when>
									<c:otherwise> <c:out value="X" /> </c:otherwise>
								</c:choose>
								</td>
							</tr>
							<tr>
								<td>등록일</td>
								<td>${bookVo.b_reg_date}</td>
							</tr>

						</table>
					</li>
				</ul>
				ㄲ
			</div>
			
			<div class="buttons">
				
				<c:url value='/admin/book/modifyBookForm' var='modify_url'>
					<c:param name='b_no' value='${bookVo.b_no}'/>
				</c:url>
				<a class="modify_book_button" href="${modify_url}">도서 수정</a>
				
				<c:url value='/book/admin/deleteBookForm' var='delete_url'>
					<c:param name='b_no' value='${bookVo.b_no}'/>
				</c:url>
				
				<a class="delete_book_button" href="#none" onclick="deleteBook(${bookVo.b_no},'${bookVo.b_name}')">도서 삭제</a>
				
			</div>
		
		</div>
		
	</section>
	
	<jsp:include page="../../include/footer.jsp" />

</body>
</html>