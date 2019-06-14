<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
  trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>자유게시판</title>
<!-- common.css --> 
<link rel="stylesheet" href="${contextRootPath}/css/common.css">

<!-- header -->
<jsp:include page="../commonSideHeaderFooter/commonHeaderCss.jsp" />

<!-- footer.css -->
<link rel="stylesheet" href="${contextRootPath}/css/footer.css">

<!-- commonSidebar css -->
<jsp:include page="../commonSideHeaderFooter/commonSidebarCss.jsp" />

<!-- recruit_board_update.css -->
<link rel="stylesheet" href="${contextRootPath}/css/recruit_board/recruit_board_update.css">

<!-- Font Awesome -->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
      
<!-- Bootstrap core CSS -->
<link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">

<!-- Your custom styles (optional) -->
<link href="${contextRootPath}/node_modules/mdbootstrap/css/style.css" rel="stylesheet">

<!-- MDBootstrap Datatables  -->
<link href="${contextRootPath}/node_modules/mdbootstrap/css/addons/datatables.min.css" rel="stylesheet">

<!-- summernote -->
<link href="${contextRootPath}/node_modules/summernote/dist/summernote-bs4.css" rel="stylesheet">
</head>

<body>
 <jsp:include page="../commonSideHeaderFooter/commonHeader.jsp"/>
  
  <jsp:include page="../commonSideHeaderFooter/commonSidebarTop.jsp"/>
  
   <div id="main-wrap" class="container">
   
     <div id="main-text">
      <div class="text-field">
        <span style="font-weight: bold; font-size: 2.0em;">쓴 게시물을 </span> 
        <span style="font-weight: bold; font-size: 2.0em;">수정</span>
        <span style="font-size: 2.0em;">할 수 있습니다. </span> 
        <br> <br> 
        <span>수정할 항목을 변경 후 <span style="font-weight: bold;">저장</span> 버튼을 누르세요.</span>
      </div>
    </div>
    
    
     <form id='update_form' action='../update' name="change" method='post'>
      <div id="table">
        <table class="table table-bordered">
          <tbody>
            <!-- 번호 넘겨줘야함 / 화면에 보이지 않음 -->
            <div class="content-number">
              <label for="no" class="col-sm-2 col-form-label">번호</label>
              <div class="col-sm-10">
                <input type="text" class="form-control" name="teamNo" id="no"
                  value="${free.no}" readonly />
              </div>
            </div>

            <tr>
              <th id="table-header" scope="row">제목</th>
              <td><input type="text" class="form-control" name="teamTitle" id="title"
                value="${free.title}" /></td>
            </tr>
            <tr>
              <th id="table-header" scope="row">내용</th>
              <td><textarea class="form-control" id="summernote" name='teamContents' rows='5'>${free.contents}</textarea></td>
            </tr>
          </tbody>
        </table>
      </div>
      <div class="form-group row">
        <div class="col-sm-12 text-center">
          <a class="btn btn-dark" href='${contextRootPath}/app/free'>취소</a>
          <a class="btn btn-dark" href='' id="update" >저장</a>
        </div>
      </div>
    </form>
    </div>


<jsp:include page="../javascript.jsp" />
<script src="${contextRootPath}/node_modules/summernote/dist/summernote-bs4.js"></script>
<script>
"use strict"

$(document).ready(function() {
  $('#summernote').summernote({
    height: 300,
    minHeight: null,
    maxHeight: null,
    focus: true
  });
  
  $('#update').click((e) =>{
    submitAgree();
    return false;
  })
});

function checkTerms() {
  var res = true;
  var titleStr = $('#title').val();
  var titleCheck = $.trim(titleStr);
  var contentsStr = $('#summernote').summernote('code');
  var contentsCheck = $.trim($('#summernote').val());

  if (titleCheck.length <= 0 ||
      contentsCheck.length <= 0) {
    alert("내용을 입력해주세요!");
    res = false;
  }

  return res;
}

function submitAgree() {
  if (checkTerms() != true) {
    return false;
  }

  $("#update_form").submit();
  return true;
}

/*
$(document).ready(function() {
  $('#summernote').summernote({
    height : 300,
    minHeight : null,
    maxHeight : null,
    focus : true
  });
});
  
$('#update').click((e) =>{
  e.preventDefault();
  var xhr = new XMLHttpRequest();
  xhr.onreadystatechange = () => {
    if (xhr.readyState == 4) {
      if (xhr.status == 200) {
        location.href = '.'
      } else {
        alert("실행 오류 입니다!");
      }
    }
  };
  xhr.open("POST", ".", true);
  xhr.setRequestHeader("Content-type", "application/json");
  
  var freeNo = $('#no').val();
  
  var titleStr = $('#title').val();
  var titleCheck = $.trim(titleStr);
  if (titleCheck.length <= 0) {
    alert("제목을 입력해주세요!");
    return;
  }
  var contentsStr = $('#summernote').summernote('code');
  var contentsCheck = $.trim($('#summernote').val());
  if (contentsCheck.length <= 0) {
    alert("내용을 입력해주세요!");
    return;
  }
  console.log(contentsStr);
  var aJson = new Object();
  aJson.no = freeNo;
  aJson.title = titleStr;
  aJson.contents = contentsStr;
  var sJson = JSON.stringify(aJson);
  
  xhr.send(sJson);
});*/

</script>
</body>
</html>






