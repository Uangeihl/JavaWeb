<%--
  Created by IntelliJ IDEA.
  User: huanglei
  Date: 2022/5/20
  Time: 下午 04:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="common/header.jsp"%>

<html>
    <head>
        <%@include file="common/lib.jsp"%>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1.0" />
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="keywords" content="Flat Dark Web Login Form Responsive Templates, Iphone Widget Template, Smartphone login forms,Login form, Widget Template, Responsive Templates, a Ipad 404 Templates, Flat Responsive Templates" />

        <link rel="stylesheet" type="text/css" href="<c:url value="../resources/css/login/style.css"/>" />

        <script type="application/x-javascript">
            addEventListener(
                "load",
                function() {
                    setTimeout(hideURLbar, 0);
                },
                false
            );
            function hideURLbar(){
                window.scrollTo(0,1);
            }
        </script>

        <title>用户登陆</title>
    </head>
    <body>
        <script>
            $(document).ready(function(c) {
                $('.close').on('click', function(c) {
                    $('.login-form').fadeOut('slow', function(c) {
                        $('.login-form').remove();
                    });
                });
            });
        </script>
        <!--SIGN UP-->
        <h1>用户登陆</h1>
        <div class="login-form">
            <div class="close"></div>
            <div class="head-info">
                <label class="lbl-1"> </label>
                <label class="lbl-2"> </label>
                <label class="lbl-3"> </label>
            </div>
            <div class="clear"></div>
            <div class="avtar">
                <img src="<c:url value="../resources/img/login/avtar.png"/>" />
            </div>
            <form action="${ctx}/doLogin" method="post">
                <input type="text" class="text" name="username">
                <div class="key">
                    <input type="password" name="password">
                </div>

                <div class="signin">
                    <input type="submit" value="登陆">
                </div>
            </form>
        </div>
        <div class="copy-rights">
            <p>
                Copyright &copy; 2017.6.25 All rights reserved.
                CSDN<a href="http://blog.csdn.net/qq_23994787?viewmode=contents" target="_blank" title="Git资源">小落的CSDN</a>
            </p>
        </div>

    </body>
</html>

