<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <link rel="icon" href="data:;base64,=">
</head>
<body>
<%--<form method="post" action="${pageContext.request.contextPath}/requestBody">--%>
<form method="post" action="${pageContext.request.contextPath}/requestEntity">
    <label>
        用户：
        <input type="text" name="name">
    </label><br>
    <label>
        密码：
        <input type="text" name="password">
    </label><br>
    <input type="submit" value="提交">
</form>
<a href="${pageContext.request.contextPath}/response/true">通过ServletAPI进行字符流响应</a>
<a href="${pageContext.request.contextPath}/response/false">通过ServletAPI进行字节流响应</a>
</body>
</html>