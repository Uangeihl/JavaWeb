<%--
  Created by IntelliJ IDEA.
  User: huanglei
  Date: 2022/4/15
  Time: 下午 10:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Control.Student" %>
<%@ page import="java.util.ArrayList" %>
<html>
    <head>
        <title>showselect</title>
    </head>
    <body align="center">
        <h1>学生成绩表</h1>
        <%--定义三个操作按钮 添加 修改 删除--%>
        <table align="center">
            <tr>
                <td>
                    <form action="Add.jsp" method="post"><input type="submit" name="operate" value="add"></form>
                </td>
                <td>
                    <form action="Modify.jsp" method="post"><input type="submit" name="operate" value="modify"></form>
                </td>
                <td>
                    <form action="Delete.jsp" method="post"><input type="submit" name="operate" value="delete"></form>
                </td>
            </tr>
        </table>
        <table border='1' align='center'>
        <tr>
            <td>学号</td><td>姓名</td><td>操作系统</td><td>微机原理</td><td>计算机网络</td>
        </tr>
        <%
//            将ShowScoresServlet传来的当前页码，页码总数，记录总数接收，并转换为int
            String str_pageNow=(String)request.getAttribute("pageNow");
            int pageNow=Integer.parseInt(str_pageNow);
            String str_pageCount=(String)request.getAttribute("pageCount");
            int pageCount=Integer.parseInt(str_pageCount);
            String str_rowCount=(String)request.getAttribute("rowCount");
            int rowCount=Integer.parseInt(str_rowCount);
//            将传来的学生成绩列表接收，循环输出
            Object obj = request.getAttribute("selresult");
//            需要先以Object方式读入，再逐条转换为Student
            ArrayList<Student> al = new ArrayList<>();
            if (obj instanceof ArrayList<?>) {
                for (Object o : (ArrayList<?>) obj) {
                    al.add((Student) o);
                }
            }
            for (Student student : al) {
                out.println("<tr>");
                out.println("<td>"+ student.getId()+"</td><td>"+ student.getName()+"</td><td>"+ student.getCzxt()+
                "</td><td>"+ student.getWjyl()+"</td><td>"+ student.getJsjwl()+"</td>");
                out.println("</tr>");
            }

            out.println("</table><br>");
            out.println("共有【"+rowCount+"】条记录，当前显示第【"+pageNow+"】页<br><br>");

            out.println("<a href=/ShowScoresServlet?pageNow=1>首页</a>");
//            如果当前页码大于1，添加上一页按钮，并将当前页码传入ShowScoresServlet
            if(pageNow>1){
                out.println("<a href=/ShowScoresServlet?pageNow="+(pageNow-1)+">上一页</a>");
            }
//            循环输出每一个页码链接
            for (int i = 1; i <= pageCount; i++) {
                out.println("<a href=/ShowScoresServlet?pageNow="+i+">["+i+"]</a>");
            }
//            添加下一页与尾页按钮，最后一页不显示
            if(pageNow<pageCount){
                out.println("<a href=/ShowScoresServlet?pageNow="+(pageNow+1)+">下一页</a>");
                out.println("<a href=/ShowScoresServlet?pageNow="+pageCount+">尾页</a>");
            }
        %>
    </body>
</html>
