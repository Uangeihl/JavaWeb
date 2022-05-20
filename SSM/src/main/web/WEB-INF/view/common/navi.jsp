<%@page pageEncoding="utf-8"%>
<div class="row">
	<div class="col-md-12">
		<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span><span
						class="icon-bar"></span><span class="icon-bar"></span><span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">员工管理</a>
			</div>

			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li
						class="<%="user".equals(request.getParameter("fun")) ? "active" : ""%>"><a
						href="${ctx}/getall">家庭成员信息管理</a></li>
					<li
						class="<%="community".equals(request.getParameter("fun")) ? "active" : ""%>"><a
						href="${ctx}/community/communitylist.action">工作单位信息管理</a></li>
					<li
						class="<%="house".equals(request.getParameter("fun")) ? "active" : ""%>"><a
						href="${ctx}/house/houselist.action">住宅信息管理</a></li>
					<li
						class="<%="communityimgs".equals(request.getParameter("fun")) ? "active" : ""%>"><a
						href="${ctx}/communityimgs/communityimgslist.action">小区图片管理</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">admin<strong class="caret">${loginUser}</strong></a>
						<ul class="dropdown-menu">
							<li><a href="#">管理员信息</a></li>
							<li class="divider"></li>
							<li><a href="#">退出登录</a></li>
						</ul></li>
				</ul>
			</div>

		</nav>
	</div>
</div>