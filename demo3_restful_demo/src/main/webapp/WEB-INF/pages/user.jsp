<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <link rel="icon" href="data:;base64,=">
    <title>User</title>
</head>
<body>
    <div >
        <h3>User查询</h3>
    </div>
    <!--默认查询所有用户-->
    <table border="1" cellpadding="0" cellspacing="0" style="text-align: center">
        <tr>
            <th colspan="6">用户信息</th>
        </tr>
        <tr>
            <td colspan="5"></td>
            <td><a href="/userSave">插入新用户</a></td>
        </tr>
        <tr>
            <th>用户ID</th>
            <th>用户名</th>
            <th>性别</th>
            <th>生日</th>
            <th>地址</th>
            <th>操作</th>
        </tr>
        <!---items指定循环的变量名，var指定每次循环的对象-->
        <c:forEach items="${requestScope.users}" var="user">
            <tr>
                <td>${user.getId()}</td>
                <td>${user.getName()}</td>
                <td>${user.getSex()}</td>
                <td>${user.getBirthday()}</td>
                <td>${user.getAddress()}</td>
                <td style="display: flex; flex-direction: row; justify-content: space-around">
                    <div onclick="updateUser('${pageContext.request.contextPath}/userSave/${user.getId()}')">更新</div>
                    <div onclick="deleteUser('${pageContext.request.contextPath}/${user.getId()}')">删除</div>
                </td>
            </tr>
        </c:forEach>
    </table>
    <!--被a连接控制所以不需要action-->
    <form method="post">
        <input type="hidden" name="_method" value="delete">
    </form>
    <script type="text/javascript">
        function updateUser(url){
            // 跳转
            window.location.href = url;
        }
        function deleteUser(url){
            // 创建一个表单
            let form = document.createElement("form");
            // 将form挂载到body上
            document.body.appendChild(form);
            form.method="post";
            // 新建input元素
            let input = document.createElement("input");
            input.type = "hidden";
            input.name = "_method";
            input.value = "delete";
            // 添加input元素到form表单中
            form.appendChild(input);
            // 添加跳转路径
            form.action = window.location.href + url;
            // 提交表单
            form.submit();
        }
    </script>
</body>
</html>
