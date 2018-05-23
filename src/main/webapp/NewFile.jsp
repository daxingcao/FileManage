<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/bootstrap-theme.css" />
<script src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
function basicPath(){
	$.ajax({
		url:"caodaxing/showFile",
		type:"post",
		dataType:"json",
		success:function(data){
			$(".fileList").children().remove();
			var html = "";
			$(data).each(function(i,item){
				if(item.kind == 1){
					html += "<li class='dir'><i class='glyphicon glyphicon-folder-open'></i>&nbsp;&nbsp;<a href='javascript:void(0)' onclick='fileLink(this)' value='"+item.basicPath+"'>"+item.fileName+"</a></li>";
				}else {
					if(item.type == "tupian"){
						html += "<li class='file'><i class='glyphicon glyphicon-picture'></i>&nbsp;&nbsp;<a href='caodaxing/downloadFTP2?basicPath="+item.basicPath+"&fileName="+item.fileName+"'>"+item.fileName+"</a></li>";
					}else if (item.type == "wenjian") {
						html += "<li class='file'><i class='glyphicon glyphicon-file'></i>&nbsp;&nbsp;<a href='caodaxing/downloadFTP2?basicPath="+item.basicPath+"&fileName="+item.fileName+"'>"+item.fileName+"</a></li>";
					}else if (item.type == "yinyue") {
						html += "<li class='file'><i class='glyphicon glyphicon-music'></i>&nbsp;&nbsp;<a href='caodaxing/downloadFTP2?basicPath="+item.basicPath+"&fileName="+item.fileName+"'>"+item.fileName+"</a></li>";
					}else if (item.type == "yasuo") {
						html += "<li class='file'><i class='glyphicon glyphicon-picture'></i>&nbsp;&nbsp;<a href='caodaxing/downloadFTP2?basicPath="+item.basicPath+"&fileName="+item.fileName+"'>"+item.fileName+"</a></li>";
					}else if (item.type == "shipin") {
						html += "<li class='file'><i class='glyphicon glyphicon-film'></i>&nbsp;&nbsp;<a href='caodaxing/downloadFTP2?basicPath="+item.basicPath+"&fileName="+item.fileName+"'>"+item.fileName+"</a></li>";
					}else{
						html += "<li class='file'><i class='glyphicon glyphicon-list-alt'></i>&nbsp;&nbsp;<a href='caodaxing/downloadFTP2?basicPath="+item.basicPath+"&fileName="+item.fileName+"'>"+item.fileName+"</a></li>";
					}
				}
			});
			$(".fileList").append(html);
		}
	})
}
function fileLink(filePath){
	var basicPath = filePath.attributes.value.value;
	var fileName = filePath.innerText;
	$.ajax({
		url:"caodaxing/showFile",
		type:"post",
		dataType:"json",
		data:{filePath : basicPath+"/"+fileName},
		success:function(data){
			$(".fileList").children().remove();
			var html = "";
			$(data).each(function(i,item){
				if(item.kind == 1){
					html += "<li class='dir'><i class='glyphicon glyphicon-folder-open'></i>&nbsp;&nbsp;<a href='javascript:void(0)' onclick='fileLink(this)' value='"+item.basicPath+"'>"+item.fileName+"</a></li>";
				}else {
					if(item.type == "tupian"){
						html += "<li class='file'><i class='glyphicon glyphicon-picture'></i>&nbsp;&nbsp;<a href='caodaxing/downloadFTP2?basicPath="+item.basicPath+"&fileName="+item.fileName+"'>"+item.fileName+"</a></li>";
					}else if (item.type == "wenjian") {
						html += "<li class='file'><i class='glyphicon glyphicon-file'></i>&nbsp;&nbsp;<a href='caodaxing/downloadFTP2?basicPath="+item.basicPath+"&fileName="+item.fileName+"'>"+item.fileName+"</a></li>";
					}else if (item.type == "yinyue") {
						html += "<li class='file'><i class='glyphicon glyphicon-music'></i>&nbsp;&nbsp;<a href='caodaxing/downloadFTP2?basicPath="+item.basicPath+"&fileName="+item.fileName+"'>"+item.fileName+"</a></li>";
					}else if (item.type == "yasuo") {
						html += "<li class='file'><i class='glyphicon glyphicon-picture'></i>&nbsp;&nbsp;<a href='caodaxing/downloadFTP2?basicPath="+item.basicPath+"&fileName="+item.fileName+"'>"+item.fileName+"</a></li>";
					}else if (item.type == "shipin") {
						html += "<li class='file'><i class='glyphicon glyphicon-film'></i>&nbsp;&nbsp;<a href='caodaxing/downloadFTP2?basicPath="+item.basicPath+"&fileName="+item.fileName+"'>"+item.fileName+"</a></li>";
					}else{
						html += "<li class='file'><i class='glyphicon glyphicon-list-alt'></i>&nbsp;&nbsp;<a href='caodaxing/downloadFTP2?basicPath="+item.basicPath+"&fileName="+item.fileName+"'>"+item.fileName+"</a></li>";
					}
				}
			});
			$(".fileList").append(html);
		}
	})
}

</script>
<style type="text/css">
	.dir{
		color: blue;
		list-style: none;
		font-size: 16px;
	}
	.file{
		color:blue;
		list-style: none;
		font-size: 16px;
	}
</style>
</head>
<body onload="basicPath()">
<!-- <a href="javascript:void(0)" onclick="oldpwbackCheck()" >dsdsfdsfds</a> -->
文件列表：<button class="btn btn-info" style="padding: 0px;" onclick="basicPath()">根目录</button>
<ul class="fileList">
<!-- <li></li>
	<li></li>
	<li></li>
	<li></li> -->
</ul>
</body>
</html>