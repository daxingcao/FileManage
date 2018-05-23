<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Mr Cao的个人网页</title>
<link href="images/page_photo.jpg" rel="shortcut icon" />
<link rel="stylesheet" href="css/index.css" />
</head>
<body data-spy="scroll" data-target="#navbar-example" data-offset="0">
<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="lunbotu.jsp"></jsp:include>
<div id="left-nmue" style="width: 200px;float: left;margin-top: 5px;margin-right: 5px;">
	<div id="nmue-content">
		<nav id="navbar-example" class="navbar navbar-default navbar-static"> 
			<ul class="nav navbar-nav">
				<li class="active"><a href="#tupian-div">世界一角</a></li>
				<li><a href="#hello-world">景秀文章</a></li>
				<li><a href="#hello-world2">SVN</a></li>
				<li><a href="#svn">SVN</a></li>
				<li><a href="#svn">SVN</a></li>
			</ul>
		</nav>
	</div>
</div>
<div class="zhanshi-big">
	<div id="tupian-div">
		<div style="margin: 5px 5px;width:200px;border-radius:5px;background-image: url('images/biaoti_tupian.jpg');">
			<h3 id="tupian-div" style="text-align: center;padding: 10px 20px;">每天一览</h3>
		</div>
		<!-- <div onmouseover="upDiv(this)" onmouseout="dowmDiv(this)" class="zhanshi-tupian-small">
			<a href="javascript:void(0)" data-toggle="modal" data-target="#myModal">
				<img class="img-thumbnail" alt="朝花" src="loadImage.do?fileId=4">
			</a>
		</div>
		<div onmouseover="upDiv(this)" onmouseout="dowmDiv(this)" class="zhanshi-tupian-small">
			<img class="img-thumbnail" alt="夕阳西下" src="images/shangwu_1.jpg">
		</div>
		<div onmouseover="upDiv(this)" onmouseout="dowmDiv(this)" class="zhanshi-tupian-small">
			<img class="img-thumbnail" alt="夕阳西下" src="images/zhongwu_1.jpg">
		</div>
		<div onmouseover="upDiv(this)" onmouseout="dowmDiv(this)" class="zhanshi-tupian-small">
			<img class="img-thumbnail" alt="夕阳西下" src="images/xiawu_1.jpg">
		</div>
		<div onmouseover="upDiv(this)" onmouseout="dowmDiv(this)" class="zhanshi-tupian-small">
			<img class="img-thumbnail" alt="夕阳西下" src="images/bangwan_1.jpg">
		</div>
		<div onmouseover="upDiv(this)" onmouseout="dowmDiv(this)" class="zhanshi-tupian-small">
			<img class="img-thumbnail" alt="夕阳西下" src="images/yewan_1.jpg">
		</div> -->
	</div>
	<!-- 图片预览模态框 -->
	
	<div id="hello-world" class="zhanshi-big">
		
		<p>sdfds</p>
		<p>sdfds</p>
		<p>sdfds</p>
		<p>sdfds</p>
		<p>sdfds</p>
		<p>sdfds</p>
		<p>sdfds</p>
		<p>sdfds</p>
		<p>sdfds</p>
		<p>sdfds</p>
		<p>sdfds</p>
		<p>sdfds</p>
		<p>sdfds</p>
		<p>sdfds</p>
		<p>sdfds</p>
		<p>sdfds</p>
		<p>sdfds</p>
		<p>sdfds</p>
		<p>sdfds</p>
	</div>
	<div id="hello-world2" class="zhanshi-big">
		<p>===========</p>
		<p>=============</p>
		<p>=============</p>
		<p>===========</p>
		<p>============</p>
		<p>============</p>
		<p>===========</p>
		<p>===========</p>
		<p>===========</p>
		<p>================</p>
		<p>===========</p>
		<p>===========</p>
		<p>===========</p>
		<p>===========</p>
		<p>===========</p>
	</div>
</div>
<jsp:include page="tupian_yulan.jsp" />
<script type="text/javascript" src="js/index.js"></script>
</body>
</html>