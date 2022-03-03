<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>share</title>
    <link rel="icon" href="data:;base64,=">
</head>
<body>
<%--<%--%>
<%--    String shareType = (String) request.getAttribute("shareType");--%>
<%--%>--%>
    <!--可以直接在对应的scope中调用值-->
    <h3>${requestScope.shareType}</h3>
    <h3>${sessionScope.session}</h3>
    <h3>${applicationScope.application}</h3>
</body>
</html>