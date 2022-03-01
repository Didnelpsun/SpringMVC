<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>demo</title>
    </head>
    <body>
        <!--点击跳转到{动态前缀}/hello-->
        <a href="${pageContext.request.contextPath}/hello">跳转到Hello</a>
    </body>
</html>