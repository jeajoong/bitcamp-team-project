<%@page import="org.aspectj.weaver.patterns.TypePatternQuestions.Question"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
  trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>

<head>
<title>고객센터_list</title>
<!-- common.css -->
<link rel="stylesheet" href="${contextRootPath}/css/common.css">

<!-- header -->
<jsp:include page="../commonSideHeaderFooter/commonHeaderCss.jsp" />

<!-- commonSidebar css -->
<jsp:include page="../commonSideHeaderFooter/commonSidebarCss.jsp" />

<!-- footer.css -->
<link rel="stylesheet" href="${contextRootPath}/css/footer.css">

<link rel="stylesheet" href="${contextRootPath}/css/question_board/question_list.css">

<!-- Font Awesome -->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">

<!-- Bootstrap core CSS -->
<link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/css/bootstrap.min.css"
  rel="stylesheet">

<!-- Your custom styles (optional) -->
<link href="${contextRootPath}/node_modules/mdbootstrap/css/style.css" rel="stylesheet">

<!-- MDBootstrap Datatables  -->
<link href="${contextRootPath}/node_modules/mdbootstrap/css/addons/datatables.min.css" rel="stylesheet">
       <script src="${contextRootPath}/node_modules/sweetalert2/dist/sweetalert2.min.js"></script>
<link href="${contextRootPath}/node_modules/sweetalert2/dist/sweetalert2.min.css" rel="stylesheet"> 

     
<style>
/* 페이지 컨트롤러 가운데 정렬 style */
.pagination {
  display: inline-flex;
}

div.dataTables_wrapper div.dataTables_paginate {
  text-align: left;
  width: 100%;
}

.th-sm {
  text-align: center;
}

  /* 테이블 헤더 색상 변경 */
  .dataTable thead tr
  {
  background-color: #f9f7f7;
}
/* 테이블 목록 색상 변경 */
.table tbody tr.even {
  /* background-color: #eaeaea; */
  
}
/* 테이블 목록 색상 변경 */
.table tbody tr.odd {
  /* background-color: white; */
  
}
/* 테이블 목록 hover 색상 변경 */
#dtBasicExample tbody tr:hover {
  background-color: #f7f4f4;
}
/* 테이블 목록 hover 색상 변경 */
#dtBasicExample tbody tr:hover>.sorting_1 {
  /* background-color: #ffa; */
  
}

table tbody tr {
  height: 80px;
  vertical-align: center;
}

.table>tbody>tr>td {
  vertical-align: middle;
}
</style>

</head>

<body>
  <jsp:include page="../commonSideHeaderFooter/commonHeader.jsp" />

  <jsp:include page="../commonSideHeaderFooter/commonSidebarTop.jsp" />

    <div id="main-text">
    <img src="${contextRootPath}/images/qna.png" style="width:100%; height:100%;">
      <h1>
        고객 센터
      </h1>
    </div>
    
  <div id="main-wrap" class="container">

    <table id="dtBasicExample" class="table" cellspacing="0" width="100%" style="text-align: center">
      <thead>
        <tr>
          <th scope="col">번호</th>
          <th scope="col">제목</th>
          <th scope="col">작성자</th>
          <th scope="col">작성일</th>
          <th scope="col">답변상태</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${question}" var="question">
          <tr>
            <td>${question.questionNo}</td>
            <td><c:choose>
                <c:when test="${member.no == question.memberNo || member.no == 1}">
                  <a href="${contextRootPath}/app/question/${question.questionNo}">${question.title}</a>
                  <c:if test="${question.password == 'true' }">
                    <img src="${contextRootPath}/images/locked.png" style="width: 20px">
                  </c:if>
                </c:when>
                <c:otherwise>
                  <c:choose>
                    <c:when test="${question.password == true }">
                      <a onclick="return aa()" style="cursor: pointer;">${question.title}</a>
                      <img src="${contextRootPath}/images/locked.png" style="width: 20px">
                    </c:when>
                    <c:otherwise>
                      <a href="${contextRootPath}/app/question/${question.questionNo}">${question.title}</a>
                    </c:otherwise>
                  </c:choose>
                </c:otherwise>   
              </c:choose></td>
            <td>${question.member.id}</td>
            <td>${question.createdDate}</td>

            <td><c:choose>
                <c:when test="${(question.questionStatus) == 'false'}">
                  <button type="button" class="btn btn-sm btn-ans" >답변대기</button>
                </c:when>
                <c:otherwise>
                  <button type="button" class="btn btn-sm btn-ans" style="color:green">답변완료</button>
                </c:otherwise>
              </c:choose></td>
          </tr>
        </c:forEach>
      </tbody>
    </table>

    <div id="in">
      <c:if test="${sessionScope.loginUser.no == 1}">
        <a class="btn btn-outline-dark" id="btnbtn1"
          href="${contextRootPath}/app/question/answerlist">답변 목록</a>
      </c:if>
    </div>
   
    <div id="btnbtn">
      <c:if test="${sessionScope.loginUser != null && sessionScope.loginUser.no != 1}">
        <a class="btn btn-outline-dark" href="${contextRootPath}/app/question/form">문의 하기</a>
        <a class="btn  btn-outline-dark" href="${contextRootPath}/app/question/mylist/${member.no}">나의 문의내역</a>
      </c:if>
    </div>
  </div>

  <jsp:include page="../commonSideHeaderFooter/commonSidebarBottom.jsp" />

  <jsp:include page="../commonSideHeaderFooter/commonSidebarBottomScript.jsp" />

  <jsp:include page="../commonSideHeaderFooter/commonHeaderJs.jsp" />

  <script>
			function aa() {
				  Swal.fire({
			            type: 'error',
			            title: '작성자와 관리자만<br>확인할 수 있습니다.',
			          })
			}

			var lang_kor = {
				"lengthMenu" : "_MENU_ 개씩 보기",
				"search" : "검색 : ",
				"zeroRecords" : "검색된 데이터가 없습니다.",
				"searchPlaceholder" : "검색어 입력",
				"paginate" : {
					"next" : "다음",
					"previous" : "이전"
				}
			};

			$(document).ready(function() {
				$('#dtBasicExample').DataTable({
					order : [ [ 0, "desc" ] ],
					"info" : false,
					"paging" : true,
					language : lang_kor
				});
				$('.dataTables_length').addClass('bs-select');
			});
		</script>


</body>
</html>