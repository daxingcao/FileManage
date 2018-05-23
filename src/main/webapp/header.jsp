<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/animate.css" />
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/headroom.js"></script>
<script type="text/javascript" src="js/jQuery.headroom.js"></script>
<style type="text/css">  
	.headroom {position: fixed;
		top: 0;
		left: 0;
		right: 0;
		transition: all .2s ease-in-out;
		z-index:100;
		margin:auto;
		height: 43px; 
		width: 80%;
		min-width:800px;
		border-radius: 10px; 
		padding: 0px 10px;
		margin-top:10px;
		background: beige;
		opacity: 0.8;
	}
	.lunbo{
		display:block;
		width: 100%;
		height:600px;
		margin: auto;
	}
	#tupian{
		width:100%;
		height: 600px;
	}
</style> 
<div id="header" class="headroom" role="banner">
	<ul class="nav nav-tabs nav-justified">
		<li><a href="index.jsp">首页</a></li>
		<li><a href="#">照片</a></li>
		<li><a href="video_page.jsp">视频</a></li>
		<li><a href="#">音乐</a></li>
		<li><a href="data_show.jsp">资料</a></li>
		<li><a href="calendar_page.jsp">日历</a></li>
	</ul>
</div>
<script type="text/javascript">
$("#header").headroom({
	  "tolerance": 5,
	  "offset": 200,
	  "classes": {
	    "initial": "animated",
	    "pinned": "zoomIn",
	    "unpinned": "zoomOut"
	  }
});
 	/* headroom.init(); */
	/* headroom.destroy();  */
</script>