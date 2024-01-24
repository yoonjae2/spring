<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<jsp:include page="../include/title.jsp" />

<link href="<c:url value='/resources/css/user/home.css' />"
	rel="stylesheet" type="text/css">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">

</head>
<body>

	<jsp:include page="../include/header.jsp" />

	<jsp:include page="./include/nav.jsp" />

	<section>

		<div id="section_wrap">

			<div class="banners d-flex flex-wrap">

				<c:forEach var="item" items="${bookVos}">
					<div class="card md-3 m-2" style="width: 17%;">
						<div>
							<c:url value='/book/user/bookDetail' var='detail_url'>
								<c:param name='b_no' value='${item.b_no}' />
							</c:url>
						</div>
						<a id="box" href="/library/book/user/bookDetail?b_no=${item.b_no}">
						<img
							src="<c:url value='/libraryUploadImg/${item.b_thumbnail}'/>"
							class="card-img-top img-fluid" style="height: 250px;" alt="..."/></a>

						<div class="card-body">
							<h5 class="card-title h-25">${item.b_name}</h5><br>
							<p class="card-text">${item.b_author} <br>(${item.b_publisher})</p>
							<a href="/library/book/user/bookDetail?b_no=${item.b_no}" class="btn btn-primary position-static mb-3">상세정보</a>
						</div>
					</div>
				</c:forEach>


			</div>
			
			

			<div class="d-flex justify-content-center">
					<nav aria-label="Page navigation example">
						<ul class="pagination">
							<c:if test="${currentPage > 1}">
								<li class="page-item"><a class="page-link"
									href="/library?pageNum=${currentPage-1}">
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
													href="/library/home?pageNum=${pageNumber}">
														${pageNumber}</a></li>
											</c:when>
											<c:otherwise>
												<li class="page-item"><a class="page-link"
													href="/library/home?pageNum=${pageNumber}">
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
													href="/library/home?pageNum=${pageNumber}">
														${pageNumber}</a></li>
											</c:when>
											<c:otherwise>
												<li class="page-item"><a class="page-link"
													href="/library/home?pageNum=${pageNumber}">
														${pageNumber}</a></li>
											</c:otherwise>
										</c:choose>
									</c:forEach>




								</c:otherwise>
							</c:choose>

							<c:if test="${currentPage < totalBookCount}">
								<li class="page-item"><a class="page-link"
									href="/library?pageNum=${currentPage+1}">
										Next</a></li>
							</c:if>
						</ul>
					</nav>
				</div>


		</div>
		</div>

	</section>

	<jsp:include page="../include/footer.jsp" />

</body>
</html>