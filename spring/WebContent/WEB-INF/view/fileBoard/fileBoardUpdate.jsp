<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
	<div>
		<h1>자유게시판 수정페이지</h1>
	</div>
	<div style="width:650px;" align="right">
		<a href="./main.ino">리스트로</a>
	</div>
	<hr style="width: 600px">
	
		<form action="./fileBoardUpdatePro.ino">
		<input type="hidden" id="boardNum" name="num" value="${fileBoardList.num}" >
		
		<div style="width: 150px; float: left;">이름 :</div>
		<div style="width: 500px; float: left;" align="left"><input type="text" name="name" value="${fileBoardList.name }" readonly/></div>
		
		<div style="width: 150px; float: left;">제목 :</div>
		<div style="width: 500px; float: left;" align="left"><input type="text" name="title"  value="${fileBoardList.title }"  /></div>
	
		<div style="width: 150px; float: left;">작성날자</div>
		<div style="width: 500px; float: left;" align="left"><input type="text" name="regdate"  value="${fileBoardList.regdate}" readonly /></div>
	
		<div style="width: 150px; float: left;">내용 :</div>
		<div style="width: 500px; float: left;" align="left"><textarea name="content" rows="25" cols="65"  >${fileBoardList.content }</textarea></div>
		
		<div style="width: 150px; float: left;">파일다운로드 :</div>
		<c:forEach items="${fileList}" var="dto">
		<div style="width: 500px; float: left;" align="left" id="box${dto.fileNo }">${dto.oriName }</div><button onclick="fileRemove(${dto.fileNo });">삭제</button>
		</c:forEach>
		
		<div align="right">
		<input type="submit" value="수정" >
		<input type="button" value="삭제" onclick="FormDelete();">
		&nbsp;&nbsp;&nbsp;
		</div>
	
	</form>
<script type="text/javascript">

function FormDelete() {
	var result = confirm("삭제하시겠습니까?")
	if(result){
		let num = $("#boardNum").val();
		location.href="./fileBoardDelete.ino?num="+num;
	}
}

function fileRemove(fNo) {
	console.log(fNo);
	$.ajax({
	    url: "/fileDelete.ino",
	    data: { fileNo: fNo },            
	    success : function() {
	    	$("#box"+fNo).remove();
	    	alert("삭제되었습니다");
	    }
	});
}

</script>
	
	
</body>
</html>