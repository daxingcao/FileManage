<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
$(function(){
	$("#myCarousel").carousel('cycle');
})
</script>
<style>
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
<div id="myCarousel" class="carousel slide lunbo">
	<!-- 轮播（Carousel）指标 -->
	<ol class="carousel-indicators">
		<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
		<li data-target="#myCarousel" data-slide-to="1"></li>
		<li data-target="#myCarousel" data-slide-to="2"></li>
		<li data-target="#myCarousel" data-slide-to="3"></li>
	</ol>   

	<!-- 轮播（Carousel）项目 -->
	<div class="carousel-inner">
		<div class="item active">
			<img id="tupian" src="images/lunbo1.jpg" alt="First slide">
		</div>
		<div class="item">
			<img id="tupian" src="images/lunbo2.jpg" alt="Second slide">
		</div>
		<div class="item">
			<img id="tupian" src="images/lunbo3.jpg" alt="Third slide">
		</div>
		<div class="item">
			<img id="tupian" src="images/lunbo4.jpg" alt="Four slide">
		</div>
	</div>
	<!-- 轮播（Carousel）导航 -->
	<a class="carousel-control left" href="#myCarousel" data-slide="prev"></a>
	<a class="carousel-control right" href="#myCarousel" data-slide="next"></a>
</div>
