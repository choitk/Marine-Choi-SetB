<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Style CSS -->
<link rel="stylesheet" href="./resources/css/partnerApply.css">

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />  
<link rel="stylesheet" href="./resources/jquery-ui-1.12.1/jquery-ui.min.css">

<!-- Bootstrap JavaScript -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
	crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>

<!-- JS/Jquery -->
<script src="./resources/js/partnerApply.js"></script>
<script src="./resources/jquery-ui-1.12.1/jquery-ui.min.js"></script>
<script src="./resources/jquery-ui-1.12.1/datepicker-ko.js"></script>
<title>협력업체 예약</title>
</head>
<body>

		<div id="header">
			<jsp:include page="../section/header.jsp" />
		</div>
		



<div id="wrap">
	
	<h2>협력업체 예약</h2>
	<h6>업무에 필요한 협력업체들을 손쉽게 예약하실 수 있습니다.<br/>
		원하는 일자를 선택 후 협력업체를 이용해보세요.<br/><br/>
		(관련 요금은 별도 지불입니다.)</h6>
	
	<div class="partnerBooking">
		<form class="partner" name="partner" method="post" action="insertCompany.do">
			<div class="group">
				<label for="name" class="label">신청인</label><br/>
				<input type="text" class="name" id="name" value="${PartnerVO.name}" name="name" required/>
			</div>
			
			<div class="group">
				<label for="tel" class="label">전화번호</label><br/>
				<input type="tel" class="tel" id="tel" value="${PartnerVO.tel}" name="tel" required/>
			</div>
			
			<div class="group">
				<label for="date" class="label">시작일 선택</label><br>
				<input class="datePicker" type="text" name="ptr_start" id="ptr_start" size="12" readonly/>
          		<br/>
          		<label for="date" class="label">종료일 선택</label><br>
          		<input class="datePicker" type="text" name="ptr_end" id="ptr_end" size="12" readonly/>
        </div>

			<div class="group">
    		<div id="partner" class="partner">
        <label for="ptr_name" class="label">분류</label>
      			<select class = "partners" name="ptr_cat_code" id="ptr_cat_code" required></select>
        <label for="ptr_cat_code" class="label">협력업체명</label>
      			<select class = "partners" name="ptr_name" id="ptr_name" required></select>
    		</div>
        </div>

			<button type="submit" class="button">예약하기</button>
		</form>
		</div>




	</div>	
		<div id="footer">
			<jsp:include page="../section/footer.jsp" />
		</div>
		
		
		
<script type="text/javascript">
$(function(){
$("#ptr_start").datepicker({
	showOn: "button",
    buttonImage: "./resources/image/calendar.png",
    buttonImageOnly: false,
	buttonText: "Select date",
	minDate: new Date()
});
$("#ptr_end").datepicker({
	showOn: "button",
    buttonImage: "./resources/image/calendar.png",
    buttonImageOnly: false,
	buttonText: "Select date",
	minDate: new Date()
});
});

$(document).ready(function () {
        $.datepicker.setDefaults($.datepicker.regional['ko']); 
        $( "#ptr_start" ).datepicker({
             changeMonth: true, 
             changeYear: true,
             closeText: '닫기',
             nextText: '다음 달',
             prevText: '이전 달', 
             dayNames: ['일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일'],
             dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'], 
             monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
             monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
             minDate: +1,
             dateFormat: "yyyy-mm-dd",
             onClose: function( selectedDate ) {    
                  //시작일(start 0Date) datepicker가 닫힐때
                  //종료일(endDate)의 선택할수있는 최소 날짜(minDate)를 선택한 시작일로 지정
                 $("#ptr_end").datepicker( "option", "minDate", selectedDate );
             }    

        });
        $( "#ptr_end" ).datepicker({
             changeMonth: true, 
             changeYear: true,
             closeText: '닫기',
             nextText: '다음 달',
             prevText: '이전 달', 
             dayNames: ['일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일'],
             dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'], 
             monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
             monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
             dateFormat: "yyyy-mm-dd",
             onClose: function( selectedDate ) {    
                 // 시작일(startDate)의 선택할수있는 최대 날짜(maxDate)를 선택한 시작일로 지정
                 $("#ptr_start").datepicker( "option", "maxDate", selectedDate );
             }    

        });    
});

var message = "<%= request.getParameter("message") %>";
if (message === "success") {
	alert("협력업체 예약이 완료되었습니다.");
} else if (message === "fail") {
	alert("협력업체 예약에 실패하였습니다.");
}

</script>

</body>
</html>