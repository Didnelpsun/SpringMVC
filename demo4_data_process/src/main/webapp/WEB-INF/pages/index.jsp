<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <link rel="icon" href="data:;base64,=">
    <title>主页</title>
</head>
<body>
<%--<form method="post" action="${pageContext.request.contextPath}/requestBody">--%>
<%--<form method="post" action="${pageContext.request.contextPath}/requestEntity">--%>
<form method="post" id="form">
    <label>
        用户：
        <input type="text" name="name" id="name">
    </label><br>
    <label>
        密码：
        <input type="text" name="password" id="password">
    </label><br>
    <input type="submit" value="提交" onclick="ajaxSubmit('${pageContext.request.contextPath}/ajaxResponse')">
</form>
<a href="${pageContext.request.contextPath}/response/true">通过ServletAPI进行字符流响应</a>
<a href="${pageContext.request.contextPath}/response/false">通过ServletAPI进行字节流响应</a>
<a href="${pageContext.request.contextPath}/responseBody">通过ResponseBody响应</a>
<a href="${pageContext.request.contextPath}/responseBodyUser">通过ResponseBody响应User</a>
<br><label for="filename">文件名：</label><input type="text" id="filename"><div onclick="(()=>{window.location.href += 'download/'+document.getElementById('filename').value;})()">下载文件</div>
<form action="${pageContext.request.contextPath}/upload" method="post" enctype="multipart/form-data">
    头像：<input type="file" name="file"><br>
    <input type="submit" value="上传头像">
</form>
</body>
<script>
    function ajaxSubmit(url){
        // 创建异步对象
        let ajax = new XMLHttpRequest();
        // get模式
        if(document.getElementById("form").method==="get"){
            // 设置方法和url
            ajax.open("get",url + "?name=" + document.getElementById("name").value + "&password=" + document.getElementById("password").value);
            // 发送请求
            ajax.send();
        }
        else{
            // post请求url不变
            ajax.open("post",url);
            // 设置请求报文头部类型为application/x-www-form-urlencoded标识使用AJAX
            ajax.setRequestHeader("Content-type","application/x-www-form-urlencoded");
            // 设置JSON数据
            let data = {
                    'name':document.getElementById("name").value,
                    'password':document.getElementById("password").value
                }
            // 发送数据
            ajax.send(data.toString());
        }
        // 注册事件，表明AJAX传输成功的处理
        ajax.onreadystatechange = ()=>{
            if(ajax.readyState == 4 && ajax.status == 200){
                console.log(ajax.responseText);
                alert("提交成功！");
            }
        }
    }
</script>
</html>