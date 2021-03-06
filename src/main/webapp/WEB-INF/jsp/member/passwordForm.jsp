<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
  trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<title>회원가입</title>
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR&display=swap" rel="stylesheet">
<jsp:include page="../commonCss.jsp" />
</head>
<body>

  <div class="container">

    <h1>회원가입</h1>
    <form action='add' method='post' enctype='multipart/form-data'>

      <div class="row">

        <div class="bit-pro col-7">
          <div class="form-group row">
            <label for="email" class="col-sm-4 col-form-label">ID</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" name="id" id="id" />
            </div>
          </div>

          <div class="form-group row">
            <label for="password" class="col-sm-4 col-form-label">비밀번호</label>
            <div class="col-sm-10">
              <input type="password" class="form-control" name="password" id="password" />
            </div>
          </div>

          <div class="form-group row">
            <label for="name" class="col-sm-4 col-form-label">이름</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" name="name" id="name" />
            </div>
          </div>

          <div class="form-group row">
            <label for="birthday" class="col-sm-4 col-form-label">생년월일</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" name="birthDay" id="birthDay" />
            </div>
          </div>

          <div class="form-group row">
            <label for="gender" class="col-sm-4 col-form-label">성별</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" name="gender" id="gender" />
            </div>
          </div>


          <div class="form-group row">
            <label for="tel" class="col-sm-4 col-form-label">전화번호</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" name="tel" id="tel" />
            </div>
          </div>

          <div class="form-group row">
            <label for="email" class="col-sm-4 col-form-label">이메일</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" name="email" id="email" />
            </div>
          </div>

          <div class="form-group row">
            <label for="baseAddress" class="col-sm-4 col-form-label">기본주소</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" name="baseAddress" id="baseAddress" />
            </div>
          </div>

          <div class="form-group row">
            <label for="detailAddress" class="col-sm-4 col-form-label">상세주소</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" name="detailAddress" id="detailAddress" />
            </div>
          </div>
          
          <div class="form-group row">
            <label for="loginType" class="col-sm-4 col-form-label">로그인유형</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" name="loginType" id="loginType" />
            </div>
          </div>


          <div class="form-group row">
            <div class="col-sm-10">
              <a class="btn btn-primary" href='./'>회원목록</a>
              <button class="btn btn-primary">등록</button>
            </div>
          </div>

        </div>
        <!-- .bit-pro -->

      </div>
      <!-- .row -->

    </form>

  </div>
  <!-- .container -->

  <jsp:include page="../javascript.jsp" />
</body>
</html>
