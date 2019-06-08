<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" isELIgnored="false"%>
 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <security:authentication property="principal.username" var="username"></security:authentication> 
  ${username} <br>
success html<br>
<%-- <security:authentication property="principal.username"/> --%>

${SPRING_SECURITY_CONTEXT.authentication.principal.username} 
<a href="adminlogout.do">注销</a>
</body>
</html>
