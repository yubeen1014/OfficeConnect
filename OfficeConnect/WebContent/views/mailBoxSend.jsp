<%@page import="java.text.SimpleDateFormat"%>
<%@page import="vo.MailVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% 
	List<MailVO> sendMailList = (List<MailVO>) request.getAttribute("sendMailList");
	String empNo = (String)request.getSession().getAttribute("empNo");
%>
<!DOCTYPE html>
<html lang="ko">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>보낸 메일함</title>

<!-- 아이콘 설정 -->
<link href="../vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
	type="text/css">
<!-- css 설정 -->
<link href="../css/sb-admin-2.min.css" rel="stylesheet">
<script src="../vendor/jquery/jquery.min.js"></script>
<script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- 드롭다운 자바스크립트  -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</head>

<body id="page-top">

	<!-- 페이지 Wrapper -->
	<div id="wrapper">

		<!-- 사이드바 include -->
		<%@ include file="./aside.jsp"%>

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- 메인 Content -->
			<div id="content">

				<!-- 헤더 include -->
				<%@ include file="./header.jsp"%>

				<!-- 페이지 Content 시작 -->
				<div class="container-fluid">

					<!-- Page Heading -->

					<!-- DataTales Example -->
					<div class="card shadow mb-4">
						<div class="card-body">
							<!-- 메일 사이드바 시작 -->
							<div class="row" style="height: 100%">

								<div class="col-lg-2" style="border-right: 1px solid #ccc;">

									<div style="font-size: 1.8rem; text-align: center;">
										메 일 <img alt="메일" src="../images/mail.png"
											style="width: 35px; height: 40px;">
									</div>
									<hr>

									<div style="text-align: center;">
										<button type="submit" class="btn btn-outline-primary"
											onClick="window.location.href='../mail/insert.do'"
											style="display: inline-block;">메일쓰기</button>

										<button type="submit" class="btn btn-outline-info" onClick="window.location.href='mailWriteMine.jsp'"
											style="display: inline-block;">내게쓰기</button>
									</div>

									<hr>

									<div style="text-align: center;">

										<button type="submit" class="btn btn-outline-warning" onClick="#" style="display: inline-block;">
											안읽음 <span class="badge badge-danger badge-counter">1</span>
										</button>

										<button type="submit" class="btn btn-outline-danger" onClick="#" style="display: inline-block;">
											중요메일<span class="badge badge-danger badge-counter">3</span>
										</button>

									</div>

									<hr>

									<nav id="sidebar" style="padding: inherit;">
										<ul class="list-unstyled">
											<li style="margin-bottom: 3px;"><a href="../mail/receiveList.do?isSend=1"
												style="text-decoration: none; color: inherit; background-color: transparent;">받은
													메일함</a></li>

											<li style="margin-bottom: 3px;"><a href="../mail/sendList.do?isSend=2"
												style="text-decoration: none; color: inherit; background-color: transparent;">보낸
													메일함</a></li>

											<li style="margin-bottom: 3px;"><a href="../views/mailBoxMine.jsp"
												style="text-decoration: none; color: inherit; background-color: transparent;">내게
													쓴 메일함</a></li>

<!-- 											<li style="margin-bottom: 3px;"><a href="#" -->
<!-- 												style="text-decoration: none; color: inherit; background-color: transparent;">임시 -->
<!-- 													보관함</a></li> -->

											<li style="margin-bottom: 3px;"><a href="#"
												style="text-decoration: none; color: inherit; background-color: transparent;">휴지통</a></li>
										</ul>
									</nav>

									<hr>

									<div class="dropdown">
										<button class="btn btn-outline-Light dropdown-toggle"
											type="button" id="groupDropdown" data-bs-toggle="dropdown"
											aria-expanded="false" style="width: 100%">즐겨찾는 그룹</button>

										<ul class="dropdown-menu" aria-labelledby="groupDropdown"
											style="width: 100%">
											<li style="width: 100%"><a class="dropdown-item"
												href="#">영업 1팀</a></li>

											<li style="width: 100%"><a class="dropdown-item"
												href="#">마케팅팀</a></li>
											<!-- 다른 그룹 항목 추가 -->
										</ul>
									</div>


								</div>

								<!-- 메일 사이드바 종료  -->
								
								<!-- 메일 본문 -->

								<div class="col-lg-10">
									<h4> 보낸 메일함</h4>

									<hr>
									
									<form>
									
									<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                       <tr role="row">
                                          <th class="sorting" tabindex="0" aria-controls="dataTable"
                                             rowspan="1" colspan="1"
                                             aria-label="작성일: activate to sort column ascending"
                                             style="width: 20px;"><input type="checkbox" id="mailAllCheckbox" class="mailCheckbox"></th>
                                          <th class="sorting" tabindex="0" aria-controls="dataTable"
                                             rowspan="1" colspan="1"
                                             aria-label="작성일: activate to sort column ascending"
                                             style="width: 25%;">제목</th>
                                          <th class="sorting" tabindex="0" aria-controls="dataTable"
                                             rowspan="1" colspan="1"
                                             aria-label="작성일: activate to sort column ascending"
                                             style="width: 45%;">본문</th>
                                          <th class="sorting" tabindex="0" aria-controls="dataTable"
                                             rowspan="1" colspan="1"
                                             aria-label="작성일: activate to sort column ascending"
                                             style="width: 15%;">받은사람</th>
                                          <th class="sorting" tabindex="0" aria-controls="dataTable"
                                             rowspan="1" colspan="1"
                                             aria-label="작성일: activate to sort column ascending"
                                             style="width: 15%;">보낸날짜</th>
                                       </tr>
                                    </thead>
                                    <tbody>
										<%
										for (MailVO mail : sendMailList) {
											if(mail.getMailReceiver().equals(empNo))
										%>
										<tr>
										    <td><input type="checkbox" name='mailCheckbox' id='mailCheckbox' class="mailCheckbox" value="<%= mail.getMailNo() %>"></td>
										    <td><a href="detail.do?mailNo=<%=mail.getMailNo() %>" onclick="showMailContent('<%= mail.getMailNo() %>')"><%= mail.getMailTitle() %></a></td>
										    <td><%= mail.getMailCont() %></td>
											<td><%= mail.getMailReceiver() %></td>
										    <td><%= mail.getMailSenddateFormat() %></td>
										</tr>
										<%
										}
										%>
                                    </tbody>
                                </table>
										<input type="button" class="btn btn-primary" style="display: inline-block;" onclick="MailCheckDelete()" value="삭제">
									</form>

								</div>
								<!-- 메일 본문 종료 -->

							</div>
						</div>
					</div>

				</div>

			</div>

			<!-- 페이지 Content 끝 -->

		</div>
		<!-- 메인 Content 끝 -->

	</div>
	<!-- Content Wrapper 끝 -->

	<!-- 페이지 Wrapper 끝 -->

	<!-- 공통속성 설정 include -->

	<%@ include file="./common.jsp"%>
    <!-- 페이지 검색/조회 플러그인 -->
    <script src="../vendor/datatables/jquery.dataTables.js"></script>
    <script src="../vendor/datatables/dataTables.bootstrap4.js"></script>
    <script src="../js/demo/datatables-demo.js"></script>
	<!-- 페이지 검색/조회 플러그인 -->
	<script>
	<%
	if(session.getAttribute("msg") == "성공"){
		session.setAttribute("msg", "");
		%>
		alert("메일을 성공적으로 발신했습니다");	
		<%
	} else if(session.getAttribute("msg") == "실패"){
		session.setAttribute("msg", "");
		%>
		alert("메일 발송중 오류가 발생했습니다");
		<%
	}
	%>
	</script>
	
	<script>
	// 페이지 로딩 시 실행될 체크박스 선택 코드
	$(document).ready(function() {
		$("#mailAllCheckbox").click(function() {// 전체 선택&해제
			if($("#mailAllCheckbox").is(":checked"))// 체크 확인
			   $("input[name=mailCheckbox]").prop("checked", true); //name이 mailCheckbox인 애들 선택
			else $("input[name=mailCheckbox]").prop("checked", false);//체크 상태 아니면 선택해제
		});

		$("input[name=mailCheckbox]").click(function() { // 개별 선택&해제
			var total = $("input[name=mailCheckbox]").length; // name이 mailCheckbox인 체크박스의 개수를 가져옴
			var checked = $("input[name=mailCheckbox]:checked").length; // name이 mailCheckbox이면서 checked(체크된)인 체크박스의 개수를 가져옴

			if(total != checked) $("#mailAllCheckbox").prop("checked", false); // 체크박스 개수와 선택된 체크븍사의 수가 다르다면 체크박스 해제 상태로 변경
			else $("#mailAllCheckbox").prop("checked", true); // 그렇지 않으면 선택으로 변경
		});
	});
	
	// 선택된 메일을 삭제하는 함수
	function MailCheckDelete(){
		let nums = [];
		$('input:checkbox[name=mailCheckbox]').each(function (index) { // name이 mailCehckbox인 모든 체크박스를 선택함, 선택한 체크박스 각각에 대한 반복 실행(.each(index))
			if($(this).is(":checked")==true){ // 현재 반복중인 체크박스($(this))의 선택 상태를 확인
		    	nums.push($(this).val()) // 선택 상태인 체크박스의 값을 nums배열에 추가함
		    }
		});
		if(confirm('삭제하시겠습니까?')){
			$.ajax({ // 선택된 메일을 삭제하고자 서버로 요청을 전송하는 부분
		        type: "POST",
		        url: "../mail/delete.do", // 서버에서 처리될 메일 삭제 기능을 호출
		        traditional: true, // jQuery에서 배열을 데이터를 서버로 전송할때 필요함
		        data: { "nums": nums },
		        dataType: 'json',
		        success: function(response) { // 서버 요청 성공 시 처리될 response 매개변수는 서버에 반환된 응답 데이터를 나타냄
		        	if(response.isSuccess==="성공"){
		        		alert('삭제 되었습니다.');
		        		location.reload(); // 페이지를 다시 로드하여 업데이트 된 메일 목록을 표시함
		        	}else{
		        		alert('삭제 실패했습니다.');
		        	}
		        },
		        error: function(error) {
		            console.error("메일 내용을 가져오는 중 오류 발생: " + error);
		        }
			});
		}
	}
	
	// 메일 제목 눌렀을때 상세 내용으로 이동하는 함수
	function showMailContent(mailId) {
		location.href = '/OfficeConnect/views/mailDetail.jsp?mailNo =' + mailId
	}
	
</script>
</body>

</html>