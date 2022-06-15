<%--
  Created by IntelliJ IDEA.
  User: huanglei
  Date: 2022/5/20
  Time: 下午 04:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 需要引入jstl1.2jar包 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>修改用户</title>
    </head>
    <body>
        <form action="${pageContext.request.contextPath}/update" method="post">
            <input type="hidden" name="id" value="${user.id}">
            用户名：<input type="text" name="name" value="${user.name}"><br/>
            年龄：<input type="text" name="age" value="${user.age}"><br/>
            <input type="submit" value="修改">
        </form>
    </body>
</html>
