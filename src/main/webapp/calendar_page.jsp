<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="js/rili/simple-calendar.js"></script>
<link rel="stylesheet" href="css/rili/simple-calendar.css" />
<link href="images/page_photo.jpg" rel="shortcut icon" />
<title>Mr Cao的个人网页</title>
<style type="text/css">
.clock{
	width: 300px;
	height:300px;
	margin: 10px;
	border-radius: 200px;
	border: 0px;
}
</style>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div style="width: 1140px; margin: auto;">
	<div id="container" style="float:left;margin: 80px 10px;"></div>
	<div style="width: 300px;margin: 80px 10px; float: left;">
		<div style="padding: 0px 50px;">
			<iframe width="300" scrolling="no" height="54" frameborder="0" allowtransparency="true" src="http://i.tianqi.com/index.php?c=code&id=42&icon=1&num=3"></iframe>
		</div>
		<iframe class="clock" src="clock.jsp"></iframe>
		<div style="text-align: center;">
			<h3 id="date_format" style="padding-left: 20px;">2017-02-25</h3>
		</div>
	</div>
</div>
<script>
    var myCalendar = new SimpleCalendar('#container');
    myCalendar.updateSize('800px', '500px');
    $(function(){
    	var date = new Date();
    	var year = date.getFullYear();
    	var month = date.getMonth() + 1;
    	if(month < 10){
    		month = "0"+month;
    	}
    	var day = date.getDate();
    	var week = date.getDay();
    	switch(week){
    		case 0: week="星期天"; break;
    		case 1: week="星期一"; break;
    		case 2: week="星期二"; break;
    		case 3: week="星期三"; break;
    		case 4: week="星期四"; break;
    		case 5: week="星期五"; break;
    		case 6: week="星期六"; break;
    	}
    	$("#date_format").text(year+"-"+month+"-"+day+"  "+week);
    })
</script>
</body>
</html>