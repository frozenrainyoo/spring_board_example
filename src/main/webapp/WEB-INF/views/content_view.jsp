<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--JSTL 사용하려면 아래를 추가해야 사용가 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<table width="500" cellpadding="0" cellspaceing="0" border="1">
		<form action="modify" method="post">
			<input type="hidden" name="bId" value=${content_view.bId}>
		
			<tr>
				<td>이름</td>
				<td> <input type="text" name="bName" value="${content_view.bName }"></td>
			</tr>
			
			<tr>
				<td> 제목 </td>
				<td><input type="text" name="bTitle" value="${content_view.bTitle }"></td>
			</tr>	
			<tr>
				<td> 내용 </td>
				<td> <input type="text" name="bContent" value="${content_view.bContent }"> </td>
			</tr>
			<tr>
				<td> 조회 </td>
				<td> ${content_view.bHit } </td>
			</tr>
			<tr>
				<td> 그 </td>
				<td> ${content_view.bGroup } </td>
			</tr>
			<tr>
				<td> 스텝 </td>
				<td> ${content_viewb.bStep } </td>
			</tr>
			<tr>
				<td> Indent </td>
				<td> ${content_view.bIndent } </td>
			</tr>
			
			<tr>
				<td colspan="2"> <input type="submit" value="수정">  <a href="list">list</a>  <a href="delete?bId=${content_view.bId}">삭제</a> <a href="reply_view?bId=${content_view.bId}">답변</a> </td>
				
			</tr>
		</form>
	</table>

</body>
</html>