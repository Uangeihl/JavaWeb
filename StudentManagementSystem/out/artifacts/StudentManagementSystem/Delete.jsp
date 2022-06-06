<%--
  Created by IntelliJ IDEA.
  User: huanglei
  Date: 2022/4/17
  Time: 下午 03:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Delete</title>
    </head>
    <body align="center">
        <h2>删除</h2>
        <%--记录错误信息--%>
        <%
            String message = (String)request.getAttribute("message");
            if(message!=null){
        %>
        <h4><%=message %></h4>
        <%}%>
        <%--将id发送到ScoresOperateServlet--%>
        <form action="ScoresOperateServlet">
            <table align="center">
                <tr>
                    <td>学号</td>
                    <td><input type="text" name="id" placeholder="请输入学号"></td>
                    <td><input type="submit" name="operate" value="delete"></td>
                </tr>
            </table>
            <br>
            <%--返回到成绩管理系统的按钮--%>
            <td><a href="/ShowScoresServlet">返回</a></td>
        </form>
    </body>
</html>
