<%--
  Created by IntelliJ IDEA.
  User: Rick
  Date: 2022/5/20
  Time: 下午 04:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 需要引入jstl1.2jar包 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>上传文件</title>
    </head>
    <body>
        <form action="${pageContext.request.contextPath}/upload" method="post" enctype="multipart/form-data">
            文件1: <input type="file" name="multifiles" /><br />
            文件2: <input type="file" name="multifiles" /><br />
            文件3: <input type="file" name="multifiles" /><br />
            <input type="submit" value="上传">
        </form>
    </body>
</html>

