<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <link rel="icon" href="data:;base64,=">
    <title>UserSave</title>
</head>
<body>
    <!--添加POST和修改PUT的目标uri都是/user，所以表单的提交目标是/user-->
    <form action="${pageContext.request.contextPath}/user" method="post">
        <!--如果是修改，那么就是PUT请求，那么就需要设置_method标签，value值设为request的参数type，需要在控制器方法中设置为put-->
        <!--如果是添加，那么就是POST请求，_method的value只能是delete、put和patch而不能是post，所以此时应该把这个标签设为disabled-->
        <input type="hidden" name="_method" value="${requestScope.type}" disabled="${requestScope.type}=='post'">
        <!--使用隐藏域获取post的request域数据，并用disabled表示不提交sexValue到表单-->
        <input type="hidden" name="sexValue" value="${requestScope.user.getSex()}" id="sex" disabled>
        <!--用户id是不变的，只起到展示的作用-->
        <label>
            用户ID
            <input type="text" name="id" readonly value="${requestScope.user.getId()}">
        </label><br>
        <label>
            用户名
            <input type="text" name="name" value="${requestScope.user.getName()}">
        </label><br>
        <label>
            性别
            <input type="radio" name="sex" value="男" id="male">男
        </label>
        <label>
            <input type="radio" name="sex" value="女" id="female">女
        </label><br>
        <label>
            生日
            <input type="date" name="birthday" value="${requestScope.user.getBirthday()}">
        </label><br>
        <label>
            地址
            <input type="text" name="address" value="${requestScope.user.getAddress()}">
        </label><br>
        <input type="submit" value="保存用户信息" />
    </form>
</body>
<script>
    // 获取隐藏域标签所得的值
    if(document.getElementById("sex").value === "男"){
        document.getElementById("male").checked=true;
        document.getElementById("female").removeAttribute("checked");
    }
    else if(document.getElementById("sex").value === "女"){
        document.getElementById("female").checked=true;
        document.getElementById("male").removeAttribute("checked");
    }
</script>
</html>
