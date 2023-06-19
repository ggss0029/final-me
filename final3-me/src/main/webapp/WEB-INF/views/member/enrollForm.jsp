<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="content">
		<br> <br>
		<div class="innerOuter">
			<h2>회원가입</h2>
			<br>

			<form action="insert.me" method="post" id="insertForm">
				<div class="form-group">
					<label for="userId">* ID : </label> <input type="text"
						class="form-control" id="userId" placeholder="Please Enter ID"
						name="userId" required>
					<div id="checkResult" style="font-size: 5px; display: none;"></div>

					<label for="userPwd">* Password : </label> <input type="password"
						class="form-control" id="userPwd"
						placeholder="Please Enter Password" name="userPwd" required>
					<br>
				</div>
				<br>
				<div class="btns" align="center">
					<button type="submit" class="btn btn-primary">회원가입</button>
					<button type="reset" class="btn btn-danger">초기화</button>
				</div>
			</form>

		</div>
	</div>

	<script>
		$(function() {
			//아이디를 입력하는 input 요소 객체를 추출해두기
			const $idInput = $("#insertForm input[name=userId]");
			console.log($idInput); //요소 안잡힘

			$idInput
					.keyup(function() {
						//최소 5글자 이상 입력되었을때 ajax 요청해보기
						if ($idInput.val().trim().length >= 5) {
							console.log($idInput.val().trim().length);
							console.log("확인");

							$
									.ajax({
										url : "checkId.me",
										data : {
											checkId : $idInput.val()
										},
										success : function(result) {
											console.log(result);

											if (result == "NN") { //사용 불가
												$("#checkResult").show();
												$("#checkResult")
														.css("color", "red")
														.text(
																"중복된 아이디가 존재합니다. 다시 입력해주세요");

												//전송 버튼 비활성화
												$(
														"#insertForm button[type=submit]")
														.attr("disabled", true);
											} else { //사용가능
												$("#checkResult").show();
												$("#checkResult").css("color",
														"green").text(
														"멋진 아이디네요");

												//전송 버튼 활성화
												$(
														"#insertForm button[type=submit]")
														.attr("disabled", false);
											}
										},
										error : function() {
											alert("통신오류")
										}
									});
						} else { //5글자 미만일때 -버튼 비활성화, 메세지 숨기기
							$("#checkResult").hide();
							$("#insertForm button[type=submit]").attr(
									"disabled", true);

						}
					});
		});
	</script>
</body>
</html>