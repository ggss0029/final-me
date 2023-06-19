<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div class="outer">
		<h1>KH COMPANY ERP</h1>
		<br>
		<br>
		<form action="login.me" method="post">
			<div>
				<label for="userId">ID</label> <input type="text"
					class="form-control" placeholder="아이디를 입력해주세요" name="userId"
					id="userId" />
			</div>
			<div>
				<label for="userPwd">Password</label> <input type="password"
					class="form-control" placeholder="비밀번호를 입력해주세요" name="userPwd"
					id="userPwd" />
			</div>
			<input type="submit" class="btn btn-primary" value="로그인" />
		</form>
	</div>
</body>
</html>