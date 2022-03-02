<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <link rel="icon" href="data:;base64,=">
</head>
<body>
<h2><a href="${pageContext.request.contextPath}/">Hello World!</a></h2>
<h2><a href="${pageContext.request.contextPath}/?value=hello">携带value="hello"</a></h2>
<h2><a href="${pageContext.request.contextPath}/test/ant">ant风格路径</a></h2>
<h2><a href="${pageContext.request.contextPath}/restful/hello">restful携带value="hello"</a></h2>
<h2><a href="${pageContext.request.contextPath}/param">获取请求参数</a></h2>
</body>
</html>
