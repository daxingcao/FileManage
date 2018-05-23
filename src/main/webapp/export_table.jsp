<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%= request.getContextPath()%>/css/bootstrap.min.css">
<link rel="stylesheet" href="<%= request.getContextPath()%>/css/bootstrap-theme.css">
<link rel="stylesheet" href="<%= request.getContextPath()%>/css/bootstrap-table.min.css">
<script src="<%= request.getContextPath()%>/js/jquery-3.3.1.min.js"></script>
<script src="<%= request.getContextPath()%>/js/bootstrap.min.js"></script>
<script src="<%= request.getContextPath()%>/js/bootstrap-table.min.js"></script>
<script src="<%= request.getContextPath()%>/js/bootstrap-table-export.min.js"></script>
<script src="<%= request.getContextPath()%>/js/tableExport.min.js"></script>
<script src="<%= request.getContextPath()%>/js/bootstrap-table-zh-CN.min.js"></script>
</head>
<body>
<div>
	<table id="customList"></table>
</div>
<div id="search">
	<div style="width: 250px;float: left;">
	    <label class="col-sm-3 control-label" for="inputError" style="padding: 0;">导入时间:</label>
	    <div class="col-sm-8">
	      <input id="createDate" type="date" class="form-control">
	    </div>
	</div>
	<div style="width: 250px;float: left;">
	    <label class="col-sm-3 control-label" for="inputError" style="padding: 0;">客户姓名:</label>
	    <div class="col-sm-8">
	      <input id="customName" type="text" class="form-control">
	    </div>
	</div>
	<div style="width: 250px;float: left;">
	    <label class="col-sm-3 control-label" for="inputError" style="padding: 0;">邀约人:</label>
	    <div class="col-sm-8">
	      <input id="inviteName" type="text" class="form-control">
	    </div>
	</div>
	<div style="width: 250px;float: left;">
	    <label class="col-sm-3 control-label" for="inputError" style="padding: 0;">联系电话:</label>
	    <div class="col-sm-8">
	      <input  id="customTel" type="text" class="form-control">
	    </div>
	</div>
	<div style="width: 50px;float: left;">
	    <button type="button" onclick="search()" class="btn btn-success">Search</button>
	</div>
</div>
<script src="<%= request.getContextPath()%>/js/custom_table.js"></script>
</body>
</html>