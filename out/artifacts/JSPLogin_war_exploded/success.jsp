<%--
  Created by IntelliJ IDEA.
  User: murphy
  Date: 2021/5/14
  Time: 5:17 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>登录成功</title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <style>
        h4 {
            margin: 0px;
            padding-top: 10px;
            padding-left: 20px;
            display: inline-block;
        }
    </style>
</head>
<body>
    <h4>欢迎你！${sessionScope.uname}</h4>
    <small><a class="btn" href="/logout">注销</a></small>
    <%@include file="success.html"%>
</body>
</html>
