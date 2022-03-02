<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>param</title>
    <link rel="icon" href="data:;base64,=">
</head>
<body>
    <form action="${pageContext.request.contextPath}/param" method="post">
        <label>
            用户名
            <input type="text" name="name">
        </label><br>
        <label>
            性别
            <input type="radio" name="sex" value="男">男
        </label>
        <label>
            <input type="radio" name="sex" value="女">女
        </label><br>
        <label>
            生日
            <input type="date" name="birthday">
        </label><br>
        <label>
            地址
            <input type="text" name="address">
        </label><br>
        <input type="submit" value="提交" />
    </form>
</body>
</html>
