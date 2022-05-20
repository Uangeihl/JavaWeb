<%--
  Created by IntelliJ IDEA.
  User: Rick
  Date: 2022/5/20
  Time: 下午 04:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <%@include file="../common/lib.jsp"%>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1.0" />
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="keywords" content="Flat Dark Web Login Form Responsive Templates, Iphone Widget Template, Smartphone login forms,Login form, Widget Template, Responsive Templates, a Ipad 404 Templates, Flat Responsive Templates" />

    </head>
    <body>
        <div class="container-fluid">
            <%@include file="../common/navi.jsp"%>
            <%-- <jsp:include page="../common/navi.jsp">
                <jsp:param value="user" name="fun" />
            </jsp:include> --%>
            <div class="row" style="margin-top: 120px">
                <div class="col-md-2"></div>
                <div class="col-md-8">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">用户列表</h3>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-2">
                                    <a href="${ctx}/toadd">
                                        <button class="btn btn-block btn-primary" type="button">增加用户</button>
                                    </a>

                                    <a href="${ctx}/search">
                                        <button class="btn btn-block btn-primary" type="button">进入文件和json页面</button>
                                    </a>
                                </div>
                            </div>

                            <div class="col-lg-6"></div>
                            <div class="col-lg-4">
                                <form role="form" method="post" action="${ctx}/query">
                                    <div class="input-group">
                                        <input type="text" class="form-control" placeholder="请输入用户名进行搜索" name="username">
                                        <span class="input-group-btn">
											<button class="btn btn-primary" type="submit">搜索</button>
										</span>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <table class="table table-hover table-bordered"
                               style="margin-top: 20px">
                            <thead>
                            <tr align="center" style="font-weight: bold">
                                <td>序号</td>
                                <td>用户名</td>
                                <td>性别</td>
                                <td>邮箱</td>
                                <td>手机号</td>
                                <td>状态</td>
                                <td>所在小区</td>
                                <td>具体房间</td>
                                <td>操作</td>
                            </tr>
                            </thead>
                            <tbody>
                            <c:if test="${userList ne null}">
                                <c:forEach items="${userList}" var="user" varStatus="vs">
                                    <tr align="center">
                                        <td>${vs.count }</td>
                                        <td>${user.name }</td>

                                        <td>${user.age }</td>
                                        <td>${user.age }</td>
                                        <td>${user.age }</td>
                                        <td>${user.age }</td>
                                        <td>${user.age }</td>
                                        <td>${user.age }</td>
                                            <%--
                                            <td><c:if test="${user.sex==1}">男</c:if> <c:if
                                                    test="${user.sex==2}">女</c:if></td>
                                            <td>${user.telephone }</td>
                                            <td><c:if test="${user.isdelete==0}">已入住</c:if> <c:if
                                                    test="${user.isdelete==1}">未入住</c:if></td>
                                            <td><c:forEach items="${communityList}" var="community"
                                                    varStatus="vs">
                                                    <c:if test="${community.id==user.cid}">${community.cname}</c:if>
                                                </c:forEach></td>
                                            <td><c:forEach items="${houseList}" var="house"
                                                    varStatus="vs">
                                                    <c:if test="${house.id==user.hid}">${house.bnum}-${house.hnum}</c:if>
                                                </c:forEach></td> --%>
                                        <td>
                                            <a href="${ctx}/edit/${user.id }"><button class="btn btn-warning btn-sm" type="button">修改</button></a>
                                            <a href="${ctx}/delete/${user.id }"><button class="btn btn-danger btn-sm" type="button">删除</button></a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            </tbody>
                        </table>
                    </div>

                </div>
            </div>
            <div class="col-md-2"></div>
        </div>
        </div>
    </body>
</html>

