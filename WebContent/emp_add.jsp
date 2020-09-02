<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String addUrl = request.getContextPath() + "/pages/emp/add.action" ;
%>
<form action="<%=addUrl%>" method="post" enctype="multipart/form-data">
	雇员编号：<input type="text" name="empno" id="empno" value="7369"><br>
	雇员姓名：<input type="text" name="ename" id="ename" value="阿旺才"><br>
	雇员工资：<input type="text" name="sal" id="sal" value="1.1"><br>
	雇佣日期：<input type="text" name="hiredate" id="hiredate" value="1911-11-11"><br>
	雇员照片：<input type="file" name="photo" id="photo"><br>
	<input type="submit" value="提交">
</form>
</body>
</html>