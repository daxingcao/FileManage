<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Mr Cao的个人网页</title>
<link href="images/page_photo.jpg" rel="shortcut icon" />
<link rel="stylesheet" href="css/index-left.css" />
<style type="text/css">
iframe{
float: left;
}
.main_show{
	width: calc(100% - 205px);
	height: 600px;
}
</style>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div style="margin-top: 100px;min-width: 1000px;">
	<iframe scrolling="no" width="200px" height="600px" src="video_menu.html" ></iframe>
	<iframe class="main_show" scrolling="auto" name="video_show"></iframe>
</div>
<script type="text/javascript">
function skipPage(e){
	var id = $(e).attr("id");
	$(".main_show").attr("src",id);
}
</script>
</body>
</html>