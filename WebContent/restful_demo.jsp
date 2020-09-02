<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	// request.setCharacterEncoding("UTF-8") ;
%>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/restful_demo.js"></script>
<title>Restful架构</title>
</head>
<body>
	<div id="butDiv">
		<button id="addMember">增加用户</button>
		<button id="editMember">编辑用户</button>
	</div>
	<div id="showDiv"></div>
</body>
</html>