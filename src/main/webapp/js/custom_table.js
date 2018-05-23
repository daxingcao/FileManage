
var tableData = $('#customList');
tableData.bootstrapTable({
url: "fileinfo/findFileInfo.do", 
dataType: "json",
pagination: true, //分页
singleSelect: false,
clickToSelect : true,
searchOnEnterKey : true,
/*searchAlign : "left",*/
showToggle : true,
showExport: true, //是否显示导出
exportDataType: 'selected', //basic', 'all', 'selected'
cache : false,
toolbar:"#search",
showRefresh:true,// 显示刷新按钮
showColumns:true, // 显示所有的列
//data-locale:"zh-CN", //表格汉化
search: false, //显示搜索框
striped:true,
sidePagination: "server", //服务端处理分页
pageList:[5,10,15,20,50],
sortName : 'cusCreatetime', // 排序字段
sortOrder : 'desc', // 排序方式
sortable: true, //是否启用排序
exportOptions:{  
    ignoreColumn: [0],  //忽略某一列的索引  
    fileName: '总台帐报表',  //文件名称设置  
    worksheetName: 'sheet1',  //表格工作区名称  
    tableName: '总台帐报表',  
    excelstyles: ['background-color', 'color', 'font-size', 'font-weight']
},
queryParams: function (params) {
    return {
            //rows: this.pageSize,
            //page: this.pageNumber,
            currentPage: params.offset,  //页码
            pageSize: params.limit,   //页面大小
            realName : params.search, //搜索
            order : params.order, //排序
//            ordername : params.sort, //排序
//            createDate : $("#createDate").val(),
//            customName : $("#customName").val(),
//            inviteName : $("#inviteName").val(),
//            customTel : $("#customTel").val()
            /*projectName:$("#projectName").val()*/
        };
    },
      columns: [
	    	  {
	    		checkbox:true
	    	  },
	          {
	            title: '文件id',
	              field: 'fileid',
	              align: 'center',
	              valign: 'middle'
	          }, 
	          {
	              title: '文件名',
	              field: 'filename',
	              align: 'center',
	              valign: 'middle',
	          }, 
	          {
	              title: '创建时间',
	              field: 'createdate',
	              align: 'center',
	              formatter : function(value,row,index) {
	            	  return dateFormat(value);
				}
	          },
	          {
	        	  title: '文件类型',
	        	  field: 'filetype',
	        	  align: 'center'
	          },
	          {
	              title: '文件路径',
	              field: 'filepath',
	              align: 'center',
	              valign: 'middle',
	          },
	          {
	              title: '操作',
	              field: 'cusId',
	              align: 'center',
	              formatter:function(value,row,index){
	           var u = '<a href="javascript:void(0)" onclick="editCustom(\''+ row.cusId +'\')">修改</a> ';
	           return u;
	            } 
	          }
          ]  
  });
function dateFormat(cellval) {
    if (cellval != null) {
        var date = new Date(cellval);
        var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
        var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
        var hour = date.getHours() <10 ? "0" + date.getHours() : date.getHours();
        var minute = date.getMinutes() <10 ? "0" + date.getMinutes() : date.getMinutes();
        var second = date.getSeconds() <10 ? "0" + date.getSeconds() : date.getSeconds();
        return date.getFullYear() + "-" + month + "-" + currentDate + " "+hour+":"+minute+":"+second;
    }
}

function editCustom(cusId) {
	$("#update_cusId").val(cusId);
	$("#customModal").modal("show");
}

function search() {
	$("#customList").bootstrapTable('refresh', {url: 'getCustomList'});
}

function update() {
	var customName = $("#update_customName").val();
	var email = $("#update_Email").val();
	var qq = $("#update_qq").val();
	var customTel = $("#update_customTel").val();
	var cusId = $("#update_cusId").val();
	$.ajax({
		 url: "updateCustom",
		 processData: false,
		 type: "post",
		 data:"customName="+customName+"&email="+email+"&qq="+qq+"&customTel="+customTel+"&cusId="+cusId,
		 dataType: "json",
		 success: function(msg){
			 if(msg != 0){
				 $("#customModal").modal("hide");
				 setTimeout('alert("修改成功！")',500);
				 // 刷新数据
				 $('#customList').bootstrapTable('refresh', {url: 'getCustomList'});
			 }else{
				 setTimeout('alert("客户不存在!")',500);
			 }
		 }
	})
}
  