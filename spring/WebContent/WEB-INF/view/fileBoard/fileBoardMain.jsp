<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div>
		<h1>저장소</h1>
	</div>
	<div style="width:650px;" align="right">
		<a href="./fileBoardInsert.ino">글쓰기</a>
	</div>
	<hr style="width: 600px">

	<c:forEach items="${fileBoardList}" var="dto">
			<div style="width: 50px; float: left;">${dto.num }</div>
			<div style="width: 300px; float: left;"><a href="./fileBoardDetail.ino?num=${dto.num }">${dto.title }</a></div>
			<div style="width: 150px; float: left;">${dto.name }</div>
			<div style="width: 150px; float: left;">${dto.regdate }</div> 
		<hr style="width: 600px">
	</c:forEach>

	
	<div id="searchDiv">
		<form class="search" action="./filemain.ino" method="get">
		<select id="searchType" name="searchType">
			<option value="title" >제목</option>
			<option value="name" >작성자</option>
		</select>
			<input type="text" name="keyword" />
			<button>검색</button>
		</form>
	</div>
	
	<div class="page" >
		<c:if test="${page.count != 0}">
			<jsp:include page="/WEB-INF/view/include/page.jsp">
				<jsp:param name="page" value="/filemain.ino" />
			</jsp:include>
		</c:if>
	</div>	
	
</body>
</html>

