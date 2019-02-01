// JavaScript Document

window.onload=function(){
	/////////////////Tab选择卡//////////////////////
	$('.tab ul.menu li').hover(function(){
        //获得当前被点击的元素索引值
         var Index = $(this).index();
		 var line=120*Index-120; 
		//给菜单添加选择样式
	    $(this).addClass('active').siblings().removeClass('active');
		 $(".line").stop(true,true).animate({left:line},200);
		
		//显示对应的div
		$('.tab').children('div').eq(Index).show().siblings('div').hide();
   
   });
   
	
	//////////////////////焦点图//////////////////////////
	var container = document.getElementById('container');
	var list = document.getElementById('list');
	var buttons = document.getElementById('buttons').getElementsByTagName('span');
	var prev = document.getElementById('prev');
	var next = document.getElementById('next');
	var index = 1;
	var len = 4;
	var animated = false;
	var interval = 3000;
	var timer;
	
	
	function animate (offset) {
		if (offset == 0) {
			return;
		}
		animated = true;
		var time = 300;
		var inteval = 10;
		var speed = offset/(time/inteval);
		var left = parseInt(list.style.left) + offset;
	
		var go = function (){
			if ( (speed > 0 && parseInt(list.style.left) < left) || (speed < 0 && parseInt(list.style.left) > left)) {
				list.style.left = parseInt(list.style.left) + speed + 'px';
				setTimeout(go, inteval);
			}
			else {
				list.style.left = left + 'px';
				if(left>-200){
					list.style.left = -720 * len + 'px';
				}
				if(left<(-720 * len)) {
					list.style.left = '-720px';
				}
				animated = false;
			}
		}
		go();
	}
	
	function showButton() {
		for (var i = 0; i < buttons.length ; i++) {
			if( buttons[i].className == 'on'){
				buttons[i].className = '';
				break;
			}
		}
		buttons[index - 1].className = 'on';
	}
	
	function play() {
		timer = setTimeout(function () {
			next.onclick();
			play();
		}, interval);
	}
	function stop() {
		clearTimeout(timer);
	}
	
	next.onclick = function () {
		if (animated) {
			return;
		}
		if (index == 4) {
			index = 1;
		}
		else {
			index += 1;
		}
		animate(-720);
		showButton();
	}
	prev.onclick = function () {
		if (animated) {
			return;
		}
		if (index == 1) {
			index = 4;
		}
		else {
			index -= 1;
		}
		animate(720);
		showButton();
	}
	
	for (var i = 0; i < buttons.length; i++) {
		buttons[i].onclick = function () {
			if (animated) {
				return;
			}
			if(this.className == 'on') {
				return;
			}
			var myIndex = parseInt(this.getAttribute('index'));
			var offset = -720 * (myIndex - index);
	
			animate(offset);
			index = myIndex;
			showButton();
		}
	}
	
	container.onmouseover = stop;
	container.onmouseout = play;
	
	play();

	///////////////////////文字滚动///////////////////////////////
	function around_text(){
		var area=document.getElementById("area");
		var text=document.getElementById("text");	
		var num=100;
		function show(){
			if(-num==text.offsetHeight){
				num=216;
			}
			area.style.marginTop=num+'px';
			num--;
		}
		var MyMarpic = setInterval(show,50);

	document.getElementById("area").onmouseover = function() {
		clearInterval(MyMarpic);
	}
	document.getElementById("area").onmouseout = function() {
		MyMarpic = setInterval(show,50);
	}
	}
	around_text();
   
   ///////////////////////////图片轮播///////////////////////////////////////
	var speedpic = 20;//速度数值越大速度越慢
	document.getElementById("list2").innerHTML = document.getElementById("list1").innerHTML;
	function Marqueepic() {
		if (document.getElementById("list2").offsetWidth - document.getElementById("photo").scrollLeft <= 0) {
			document.getElementById("photo").scrollLeft -= document.getElementById("list1").offsetWidth;
		} 
		else {
			document.getElementById("photo").scrollLeft++;
		}
	}
	var MyMarpic = setInterval(Marqueepic, speedpic);

	document.getElementById("photo").onmouseover = function() {
		clearInterval(MyMarpic);
	}
	document.getElementById("photo").onmouseout = function() {
		MyMarpic = setInterval(Marqueepic, speedpic);
	}

	/////////////////////图片列表边框///////////////////////
	var showpic=document.getElementById("showpic").getElementsByTagName("img");
	for(var i=0; i<showpic.length; i++){
		showpic[i].id=i;
		showpic[i].onmouseover=function(){
			this.style.borderColor="#51a593";
		}
		showpic[i].onmouseout=function(){
			this.style.borderColor="#cccccc";
		}
	}
	
}


