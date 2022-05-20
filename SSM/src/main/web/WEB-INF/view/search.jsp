<%--
  Created by IntelliJ IDEA.
  User: Rick
  Date: 2022/5/20
  Time: 下午 04:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <!-- <script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.1/jquery.min.js"></script>
         -->
        <script type="text/javascript" src="<c:url value="/resources/js/jquery-1.8.3.min.js"/>" ></script>
        <script>
            function show(){
                $('#info').val('clicked');
            }

            function jsonOper(){
                $.ajax({
                    type : 'POST',
                    dataType: 'json',
                    url : '${pageContext.request.contextPath}/jsonOper',
                    //设置contentType类型为json
                    //contentType : 'application/json;charset=utf-8',
                    //json数据
                    data : {
                        id : '12345',
                        type : 'pc'

                    },
                    //请求成功后的回调函数
                    success : function(data) {
                        alert(1);
                        console.log(data);
                    }
                });
            }
        </script>


        <title>查找用户信息</title>
        <base href="<%=basePath%>">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <center>
            <form action="jsonOper" method="post">
                请输入用户ID:<input type="text" name="id">
                <input type="submit" value="确定">
            </form>
            <a href="toUploadPage">上传图片</a>
            <a href="download?fileName=201701241716221.jpg">下载图片</a>
        </center>
        <%-- <img src="<c:url value="/resources/img/1.jpg" />" /> --%>
    </body>
</html>
