<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title}</title>
<link rel="stylesheet" type="text/css" href="./css/vote.css">
</head>
<body>
	<header>
	<h3>${title}</h3>
	</header>
	<aside class="left"></aside>
	<aside class="right"></aside>

	<div class="container">
	<div class="content">
		<c:if test="${message != null}"><span class="message">${message}</span></c:if>
		<c:choose>
			<c:when test="${showTable == false}">
				<div class="frm">
					<form action="vote" method="post">
						Name: <input type="text" name="name" required="true"> Choose: <select
							name="sportKind">
							<c:forEach items="${votes}" var="vote">
								<option value="${vote.sport}">${vote.sport}</option>
							</c:forEach>
						</select> <input type="submit" value="vote">
					</form>
				</div>
			</c:when>

			<c:otherwise>
				<div class="table_form">
					<div class="result">Results:</div>
					<table>
						<c:forEach items="${votes}" var="vote">
							<tr>
								<td>${vote.sport}</td>
								<td>${vote.voteCount}</td>
								<td>
								<c:forEach items="${vote.names}" var="name" varStatus="loop">
									${name } ${!loop.last ? ', ' : '' }
								</c:forEach>
								</td>
							</tr>
						</c:forEach>
					</table>
					<div class="back">
						<form action="vote" method="get">
							<input type="submit" class="back_input" value="Vote more">
						</form>
					</div>
					<div class="hide" name="showTable"></div>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</div>
	<footer>Atrthur Markevich</footer>
</body>
</html>