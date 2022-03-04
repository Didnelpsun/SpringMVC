<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<html>
<head>
    <link rel="icon" href="data:;base64,=">
    <title>User</title>
</head>
<body>
    <h3>User查询</h3>
    <!--默认查询所有用户-->
    <form action="${pageContext.request.contextPath}/user">
        <label>
            <input type="text" name="id">
        </label><br>
        <input type="submit" value="根据用户ID查询用户信息">
    </form>
    <form action="${pageContext.request.contextPath}/user" method="post">
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
        <input type="submit" value="添加用户信息" />
    </form>
    <form action="${pageContext.request.contextPath}/user" method="post">
        <!--不需要用户知道和填写，所以类型为hidden-->
        <input type="hidden" name="_method" value="put">
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
        <input type="submit" value="添加用户信息" />
    </form>
    <h3>${requestScope.userType}</h3>
</body>
</html>
