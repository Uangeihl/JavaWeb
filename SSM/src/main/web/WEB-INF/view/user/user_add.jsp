<%--
  Created by IntelliJ IDEA.
  User: Rick
  Date: 2022/5/20
  Time: 下午 04:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../common/header.jsp"%>
<html lang="en">
    <head>
        <%@include file="../common/lib.jsp"%>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>用户信息</title>
    </head>
    <body>
        <%-- <div class="container-fluid">
            <jsp:include page="../admin_nav.jsp">
                <jsp:param value="user" name="fun" />
            </jsp:include>
        </div> --%>
        <div class="container-fluid" style="margin-top: 120px">
            <div class="row">
                <div class="col-md-2"></div>
                <div class="col-md-8">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">用户信息</h3>
                        </div>
                        <div class="panel-body">
                            <form action="${ctx}/add" method="post">
                                <div style="width: 400px; margin-left: 230px">
                                    <div class="input-group">
                                        <span class="input-group-addon">姓名</span>
                                        <input type="text" name="name" class="form-control" placeholder="请输入用户名">
                                    </div>
                                </div>
                                <div style="width: 400px; margin-left: 230px">
                                    <div class="input-group" style="margin-top: 30px">
                                        <span class="input-group-addon">年龄</span>
                                        <input type="text" name="age" class="form-control" placeholder="请输入用户年龄">
                                    </div>
                                </div>
                                <!-- <div style="width: 400px; margin-left: 230px">
                                    <div class="input-group" style="margin-top: 30px">
                                        <span class="input-group-addon">性别</span>
                                        <select class="form-control" name="sex">
                                            <option value="1" selected="selected">男</option>
                                            <option value="2">女</option>
                                        </select>
                                    </div>
                                </div> -->
                                <%-- <div style="width: 400px; margin-left: 230px">
                                    <div class="input-group" style="margin-top: 30px">
                                        <span class="input-group-addon">小区</span>
                                        <select class="form-control" name="cid">
                                            <c:if test="${communityList ne null}">
                                                <c:forEach items="${communityList}" var="community"
                                                    varStatus="vs">
                                                    <option value="${community.id}" selected="selected">${community.cname}</option>
                                                </c:forEach>
                                            </c:if>
                                        </select>
                                    </div>
                                </div> --%>
                                <%-- <div style="width: 400px; margin-left: 230px">
                                    <div class="input-group" style="margin-top: 30px">
                                        <span class="input-group-addon">住宅</span> <select
                                            class="form-control" name="hid">
                                            <c:if test="${houseList ne null}">
                                                <c:forEach items="${houseList}" var="house" varStatus="vs">
                                                    <option value="${house.id}" selected="selected">${house.bnum}-${house.hnum}</option>
                                                </c:forEach>
                                            </c:if>
                                        </select>
                                    </div>
                                </div> --%>
                                <!-- <div style="width: 400px; margin-left: 230px">
                                    <div class="input-group" style="margin-top: 30px">
                                        <span class="input-group-addon">邮箱</span> <input type="email"
                                            name="email" class="form-control" placeholder="请输入邮箱">
                                    </div>
                                </div>
                                <div style="width: 400px; margin-left: 230px">
                                    <div class="input-group" style="margin-top: 30px">
                                        <span class="input-group-addon">电话</span> <input type="number"
                                            name="telephone" class="form-control" placeholder="请输入电话">
                                    </div>
                                </div> -->
                                <button class="btn btn-primary"
                                        style="width: 400px; margin-top: 30px; margin-left: 230px"
                                        type="submit">提交</button>
                            </form>
                            <button class="btn btn-default"
                                    style="width: 400px; margin-top: 20px; margin-left: 230px"
                                    onClick="javascript :history.back(-1);">返回</button>

                        </div>
                    </div>
                </div>
                <div class="col-md-2"></div>
            </div>
        </div>
    </body>
</html>

