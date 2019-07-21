<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
	<div>
		<h1>자유게시판 글등록</h1>
	</div>
	<div style="width:650px;" align="right">
		<a href="./main.ino">리스트로</a>
	</div>
	<hr style="width: 600px">
	
	<form action="./freeBoardInsertPro.ino" method="get">
		<input type="hidden" name="boardGroupNo" value="1"> 
		<div style="width: 150px; float: left;">이름 :</div>
		<div style="width: 500px; float: left;" align="left"><input type="text" name="name"/></div>
		
		<div style="width: 150px; float: left;">제목 :</div>
		<div style="width: 500px; float: left;" align="left"><input type="text" name="title"/></div>
	
		<div style="width: 150px; float: left;">내용 :</div>
		<div style="width: 500px; float: left;" align="left"><textarea name="content" rows="25" cols="65" ></textarea></div>
		
		<div align="right">
		<input type="submit" value="글쓰기">
		<input type="button" value="다시쓰기" onclick="FormClear();" >
		<input type="button" value="취소" onclick="FormCancel();">
		&nbsp;&nbsp;&nbsp;
		</div>
	
	</form>
	
<script type="text/javascript">
function FormClear() {
	$("form")[0].reset();

}
function FormCancel() {
	var result = confirm("취소하시겠습니까?")
	if(result){
		location.href="./main.ino"
	}
}
</script>
	
	
</body>
</html>