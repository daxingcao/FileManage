<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/animate.css" />
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
</head>
<body>
<form action="updateFile.do" method="post" enctype="multipart/form-data">
<input type="hidden"name="basicPath" value="/picture" />
<input type="file" name="myFile" />&nbsp;&nbsp;
<input type="submit" value="submit" />
</form>
<a href="#" data-toggle="modal" data-target="#myModal" >查看详情</a>
<jsp:include page="tupian_yulan.jsp" />
<!-- <img alt="ss" src="loadImage.do" /> -->
</body>
</html>