$(function(){
	$.ajax({
		url:"index/imageList.do",
        type:"post",
        dataType:"json",
		success:function(data){
			$("#tupian-div").children().remove(".zhanshi-tupian-small");
			var html = "";
			$(data).each(function(i,item){
				html += "<div onmouseover='upDiv(this)' onmouseout='dowmDiv(this)' class='zhanshi-tupian-small'>"+
							"<a href='javascript:void(0)' onclick='showImage("+item.fileid+")'>"+
								"<img class='img-thumbnail' alt='朝花' src='loadImage.do?fileId="+item.fileid+"'>"+
							"</a>"+
						"</div>";
			})
			$("#tupian-div").append(html);
		}
	})
})

function upDiv(thiss){
	thiss.setAttribute("class","zhanshi-tupian-smalls");
}
function dowmDiv(thiss){
	thiss.setAttribute("class","zhanshi-tupian-small");
}
//保证左侧菜单栏在滚动一定高度后跟随在左上角滚动
var height = $("#left-nmue").offset().top;
$(window).scroll(function(){
	var scroll = $(this).scrollTop();
	if(height<=scroll) {
		$("#nmue-content").css({"position":"fixed","top":"5px","width":"200px"});
		$("#left-nmue").css({"height":scroll+"px"});
	}else {
		$("#nmue-content").css({"position":"static"});
	}
});

function showImage(fileid){
	$("#showImage").children().remove();
	var html = "<img onmousedown='clickMouse(event)' src='loadImage.do?fileId="+fileid+"'>";
	$("#showImage").append(html);
	$("#myModal").modal('show');
}
function clickMouse(e){
	if(e.button == 0){
		$("#myModal").modal('hide');
	}
}