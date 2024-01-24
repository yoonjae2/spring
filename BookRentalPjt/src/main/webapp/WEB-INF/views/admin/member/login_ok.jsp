<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<jsp:include page="../../include/title.jsp" />

<link href="<c:url value='/resources/css/admin/login_result.css' />" rel="stylesheet" type="text/css">

<jsp:include page="../include/login_js.jsp" />

</head>
<body>

	<jsp:include page="../../include/header.jsp" />
	
	<jsp:include page="../include/nav.jsp" />
	
	<section>
		
		<div class="chart d-flex justify-content-center mt-20">
			<div style="width: 900px; height: 900px;">
				<!--차트가 그려질 부분-->
				<canvas id="myChart"></canvas>
			</div>
			<div style="width: 400px; height: 400px;">
				<!--차트가 그려질 부분-->
				<canvas id="myChart2"></canvas>
			</div>
		</div>
		
	</section>
	
	<jsp:include page="../../include/footer.jsp" />
	
	<script type="text/javascript">
		var context = document.getElementById('myChart').getContext('2d');
		var myChart = new Chart(context, {
			type : 'bar', // 차트의 형태
			data : { // 차트에 들어갈 데이터
				labels : [
				//x 축
				'판타지', '무협', '코미디', '로맨스', '추리', '대체역사', '기타' ],
				datasets : [
						{ //데이터
							label : '대출도서', //차트 제목
							fill : false, // line 형태일 때, 선 안쪽을 채우는지 안채우는지
							data : [ 7, 5, 3, 12, 3, 6, 2 //x축 label에 대응되는 데이터 값
							],
							backgroundColor : [
							//색상
							'rgba(255, 99, 132, 0.2)',
									'rgba(54, 162, 235, 0.2)',
									'rgba(255, 206, 86, 0.2)',
									'rgba(75, 192, 192, 0.2)',
									'rgba(153, 102, 255, 0.2)',
									'rgba(255, 159, 64, 0.2)' ],
							borderColor : [
							//경계선 색상
							'rgba(255, 99, 132, 1)', 
							        'rgba(54, 162, 235, 1)',
									'rgba(255, 206, 86, 1)',
									'rgba(75, 192, 192, 1)',
									'rgba(153, 102, 255, 1)',
									'rgba(255, 159, 64, 1)'],
							borderWidth : 1
						//경계선 굵기
						}, {
							label : '장르별 보유도서',
							fill : false,
							data : [ 19, 34, 12, 24, 15, 20, 30 ],
							backgroundColor : 'rgb(12, 157, 19, 0.5)',
							borderColor : 'rgb(12, 157, 19, 0.5)'
						} ]
			},
			options : {
				scales : {
					yAxes : [ {
						ticks : {
							beginAtZero : true
						}
					} ]
				}
			}
		});

		new Chart(document.getElementById("myChart2"), {
			type : 'pie',
			data : {
				labels : [ '판타지', '무협', '코미디', '로맨스', '추리', '대체역사', '기타' ],
				datasets : [ {
					label : "Population (millions)",
					backgroundColor : [ "#3e95cd", "#8e5ea2", "#7ab955",
							"#e8c3b9", "#c45850", "#1c66d4", "#dfa831" ],
					data : [ 7, 5, 3, 12, 3, 6, 2 ]
				} ]
			},
			options : {
				title : {
					display : true,
					text : '장르별 도서비율'
				}
			}
		});
	</script>
	
</body>
</html>