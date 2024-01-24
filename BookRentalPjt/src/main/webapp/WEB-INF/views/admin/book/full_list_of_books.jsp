<%@page import="java.util.List"%>
<%@page import="com.office.library.book.BookVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<jsp:include page="../../include/title.jsp" />

<link
	href="<c:url value='/resources/css/admin/full_list_of_books.css' />"
	rel="stylesheet" type="text/css">
	
	

</head>
<body>

	<jsp:include page="../../include/header.jsp" />

	<jsp:include page="../include/nav.jsp" />

	<section>

		<div id="section_wrap">

			<div class="word">

				<h3>전체도서목록</h3>

			</div>

			<div class="book_list">

				<table>
					<thead>
						<tr>
							<th>도서명</th>
							<th>저자</th>
							<th>발행처</th>
							<th>발행연도</th>
							<th>장르</th>
							<th>ISBN</th>
							<th>청구기호</th>
							<th>대출가능</th>
						</tr>
					</thead>

					<tbody>

						<c:forEach var="item" items="${bookVos}">
							<tr>
								<td><c:url value='/admin/book/bookDetail' var='detail_url'>
										<c:param name='b_no' value='${item.b_no}' />
									</c:url> <a href="${detail_url}">${item.b_name}</a></td>
								<td>${item.b_author}</td>
								<td>${item.b_publisher}</td>
								<td>${item.b_publish_year}</td>
								<td>${item.b_genre}</td>
								<td>${item.b_isbn}</td>
								<td>${item.b_call_number}</td>
								<td><c:choose>
										<c:when test="${item.b_rantal_able eq 0}">
											<c:out value="대출중" />
										</c:when>
										<c:when test="${item.b_rantal_able eq 1}">
											<c:out value="대출가능" />
										</c:when>
										<c:otherwise>
											<c:out value="대출중" />
										</c:otherwise>
									</c:choose></td>
							</tr>
						</c:forEach>

					</tbody>


					<%--
					<tbody>
						<%
							List<BookVo> bookVos = (List<BookVo>) request.getAttribute("bookVos");
							for (int i = 0; i < bookVos.size(); i++) {
						%>
						<tr>
							<td>
								<a href="/library/book/admin/bookDetail?b_no=<%=bookVos.get(i).getB_no()%>"><%=bookVos.get(i).getB_name()%></a>
							</td>
							<td><%=bookVos.get(i).getB_author()%></td>
							<td><%=bookVos.get(i).getB_publisher()%></td>
							<td><%=bookVos.get(i).getB_publish_year()%></td>
							<td><%=bookVos.get(i).getB_isbn()%></td>
							<td><%=bookVos.get(i).getB_call_number()%></td>
							<td><%=bookVos.get(i).getB_rantal_able()%></td>
						</tr>
						<%
							}
						%>
					</tbody>
					--%>

				</table>

				<div class="d-flex justify-content-center">
					<nav aria-label="Page navigation example">
						<ul class="pagination">
							<c:if test="${currentPage > 1}">
								<li class="page-item"><a class="page-link"
									href="/library/admin/book/getAllBooks?pageNum=${currentPage-1}">
										Previous</a></li>
							</c:if>

							<!-- 페이징이 10개 이하면 전부 표시 -->
							<c:choose>
								<c:when test="${totalBookCount <= 10}">
									<c:forEach var="pageNumber" begin="1" end="${totalBookCount}">
										<c:choose>
											<c:when test="${pageNumber eq currentPage }">
												<li class="page-item active" aria-current="page"><a
													class="page-link"
													href="/library/admin/book/getAllBooks?pageNum=${pageNumber}">
														${pageNumber}</a></li>
											</c:when>
											<c:otherwise>
												<li class="page-item"><a class="page-link"
													href="/library/admin/book/getAllBooks?pageNum=${pageNumber}">
														${pageNumber}</a></li>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<c:set var="startPage" value="${currentPage-2}" />
									<c:if test="${startPage < 1}">
										<c:set var="startPage" value="1" />
									</c:if>
									<c:set var="endPage" value="${startPage + 4}" />
									<c:if test="${endPage > totalBookCount}">
										<c:set var="endPage" value="${totalBookCount}" />
										<c:set var="startPage" value="${endPage-4}" />
									</c:if>


									<c:forEach var="pageNumber" begin="${startPage}"
										end="${endPage}">
										<c:choose>
											<c:when test="${pageNumber eq currentPage }">
												<li class="page-item active" aria-current="page"><a
													class="page-link"
													href="/library/admin/book/getAllBooks?pageNum=${pageNumber}">
														${pageNumber}</a></li>
											</c:when>
											<c:otherwise>
												<li class="page-item"><a class="page-link"
													href="/library/admin/book/getAllBooks?pageNum=${pageNumber}">
														${pageNumber}</a></li>
											</c:otherwise>
										</c:choose>
									</c:forEach>




								</c:otherwise>
							</c:choose>

							<c:if test="${currentPage < totalBookCount}">
								<li class="page-item"><a class="page-link"
									href="/library/admin/book/getAllBooks?pageNum=${currentPage+1}">
										Next</a></li>
							</c:if>
						</ul>
					</nav>
				</div>

			</div>

		</div>

	</section>

	<jsp:include page="../../include/footer.jsp" />

</body>
</html>