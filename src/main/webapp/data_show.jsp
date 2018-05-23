<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Mr Cao的个人主页</title>
<link rel="stylesheet" href="css/bootstrap-theme.css" />
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/fileinput.min.css" />
<link href="images/page_photo.jpg" rel="shortcut icon" />
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/fileinput.min.js"></script>
<script type="text/javascript" src="js/zh.js"></script>
</head>
<body>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
        <li class="fa fa-remove"></li>
    </button>
    <h5 class="modal-title">Excel文件上传</h5>
</div>
<div class="modal-body">
    <form id="importFile" name="importFile" class="form-horizontal" method="post"
          enctype="multipart/form-data">
        <div class="box-body">
            <div>
                <label class="control-label">请选择要导入的Excel文件：</label>
                <input id="excelFile" name="excelFile" class="file-loading" type="file" 
                	multiple accept=".xls,.xlsx"> <br>
            </div>
        </div>
    </form>
</div> 
<script>
    initUpload("excelFile", "importFile");
    function initUpload(ctrlName, uploadUrl) {
        var control = $('#' + ctrlName);
        control.fileinput({
            language: 'zh', //设置语言
            uploadUrl: uploadUrl, //上传的地址
            uploadAsync: true, //默认异步上传
            showCaption: true,//是否显示标题
            showUpload: true, //是否显示上传按钮
            browseClass: "btn btn-primary", //按钮样式
            allowedFileExtensions: ["xls", "xlsx","jpg","txt"], //接收的文件后缀
            maxFileCount: 5,//最大上传文件数限制
            previewFileIcon: '<i class="glyphicon glyphicon-file"></i>',
            showPreview: true, //是否显示预览
            previewFileIconSettings: {
                'xlsx': '<i class="fa fa-file-excel-o text-success"></i>',
                'xls': '<i class="fa fa-file-excel-o text-success"></i>',
                'jpg': '<i class="fa fa-file-photo-o text-warning"></i>'
            },
            uploadExtraData: function () {
                var extraValue = "text";
                return {"excelType": extraValue};
            }
        });
    }
    $("#excelFile").on("fileuploaded", function (event, data, previewId, index) {
        console.log(data);
     
        if(data.response != 'zero')
        {
            alert( "成功导入"+data.response+"条客户信息！");
        	//关闭
            $(".close").click();
        }
        else{
            alert( "没有导入客户信息!");
        	//重置
        	$("#excelFile").fileinput("clear");
        	$("#excelFile").fileinput("reset");
        	$('#excelFile').fileinput('refresh');
        	$('#excelFile').fileinput('enable');
        }
    });
</script>
</body>
</html>