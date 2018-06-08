<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title}</title>
<link rel="stylesheet" type="text/css" href="./css/style.css">
</head>
<body>
	<header>
	<h3>${title}</h3>
	</header>
	<aside class="left"></aside>
	<aside class="right"></aside>
	<div class="container">
		<div class="calc">
			<c:choose>
			<c:when test="${result == null}">
				<form action="${pageContext.request.contextPath}/calc" method="get">
					<input type="text" name="x" pattern="\d+(\.\d+)?" required>
					<select name="op" id="op">
						<option value="%2B">+</option>
						<option value="-">-</option>
					</select> <input type="text" name="y" pattern="\d+(\.\d+)?" required>
					<input type="submit" value="culc">
				</form>
				</c:when>
				<c:otherwise>
					<div class="res">${x}${operate} ${y} = ${result}</div>
					<form action="${pageContext.request.contextPath}/calc"
						method="post">
						<input type="submit" value="Back" />
					</form>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<footer><small>Markevich Arthur</small></footer>
</body>
</html>