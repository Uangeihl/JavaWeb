<%--
  Created by IntelliJ IDEA.
  User: huanglei
  Date: 2022/4/17
  Time: 下午 03:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Add</title>
    </head>
    <body align="center">
        <h2>添加</h2>
        <%--记录错误信息--%>
        <%
            String message = (String)request.getAttribute("message");
            if(message!=null){
        %>
        <h4><%=message %></h4>
        <%}%>
        <%--将学号，姓名，操作系统成绩，微机原理成绩和计算机网络成绩发送到ScoresOperateServlet--%>
        <form action="ScoresOperateServlet">
            <table align="center">
                <tr>
                    <td>学号</td>
                    <td><input type="text" name="id" placeholder="请输入学号"></td>
                </tr>
                <tr>
                    <td>姓名</td>
                    <td><input type="text" name="name" placeholder="请输入姓名"></td>
                </tr>
                <tr>
                    <td>操作系统</td>
                    <td><input type="text" name="czxt" placeholder="请输入操作系统成绩"></td>
                </tr>
                    <td>微机原理</td>
                    <td><input type="text" name="wjyl" placeholder="请输入微机原理成绩"></td>
                <tr>
                    <td>计算机网络</td>
                    <td><input type="text" name="jsjwl" placeholder="请输入计算机网络成绩"></td>
                </tr>
                <tr>
                    <%--返回到成绩管理系统的按钮--%>
                    <td><a href="/ShowScoresServlet">返回</a></td>
                    <td align="right"><input type="submit" name="operate" value="add"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
