<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta http-equiv="Cache-Control" content="no-siteapp" />
	<meta name="keywords" content="物流监控系统">
    <meta name="description" content="企业级物流多查平台">
    <title><t:mutiLang langKey="jeect.platform"/></title>
    <link href="plug-in-ui/hplus/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
	<link rel="stylesheet" href="plug-in/themes/fineui/common/css/sccl.css">
	<link rel="stylesheet" type="text/css" href="plug-in/themes/fineui/common/skin/qingxin/skin.css" id="layout-skin"/>
	<link rel="stylesheet" href="plug-in/themes/fineui/common/iconfont/iconfont.css">
	<link rel="stylesheet" href="plug-in/themes/fineui/smart-menu/smartMenu.css">
	<style>
	.titlerow{
		display:table; 
		width: 150px;
		position: absolute;
	    top: 50%;
	    margin-top: -13px;
	    left: 450px;
	    margin-bottom: 0;
	    opacity: .50;
	    filter: alpha(opacity=50);
	    background-color: rgba(255, 255, 255, 0.20);
	}
	.titlecell{
		width:100%;
		position: relative;
		vertical-align: middle;
	    padding: 0;
    }
    
    .searchbox{
        border-radius: 0;    
        color: #ddd;
	    border-width: 1px;
	    border-style: solid;
	    line-height: 16px;
	    outline: none;
	    background-color: transparent;
	    text-rendering: auto;
	    letter-spacing: normal;
	    word-spacing: normal;
	    text-transform: none;
	    text-indent: 0px;
	    text-shadow: none;
	    display: inline-block;
	    text-align: start;
	    margin: 0em 0em 0em 0em;
	    font: 12.6667px Arial;padding: 4px 6px;  
	    width: 100%;    
	    box-sizing: border-box;
    }
    .searchbox-focus{color: #fff;}
    .ui-iconss-focus{color: #fff;}
    .searchbox-focusbg{
     background-color:rgba(255, 255, 255, 0.10);
    }
   .ui-iconss-focus i{
   	 color: #fff; 
   	 font-size:17px;
   }
    .ui-iconss{
	    box-sizing: border-box;
	    min-width: 20px;
	    cursor: pointer;
	    text-align: center;
	    line-height: 16px;
	    vertical-align: top;
	    min-height: 16px;
	    color: #ddd;
	    display: inline-block;
	    font: normal normal normal 16px/1 FontAwesome;
	    font-weight:900;
	    text-rendering: auto;
	    -webkit-font-smoothing: antialiased;
	    -moz-osx-font-smoothing: grayscale;
	    text-indent: 0;    
    }
    .iconssdiv{
	    position: absolute;
	    right: 0;
	    top: 50%;
	    margin-top: -6.5px;
	    margin-right: 3px;
        border-bottom-right-radius: 4px;font-size: 13px;
    }
    .header-bar li{margin-left:5px;}
    a.active .tab-bottom-separator,a.active .sepmm{
  	    position:relative;
        top:-6px;
        width:100%;
        display: block;
   		 height: 2px;
   		 background-color: #007465;
   		 padding:0 15px;
   		 left:-15px;
		}
		
		.colorgray{color:#888;font-size:16px !important;}
		.content-tab.active .colorgray{color:#007465}
		.f-tabstrip-header-inkbar{
		position: absolute;
	    left:27px;
	    width:78px;
	    z-index: 1;
	    bottom: -1px;
		display: block;
	    height: 3px;
	    background-color: #007465; /* #007465; */
	    -webkit-transition: width .3s,left .3s;
	    transition: width .3s,left .3s;
		}
		.mytabbtn:hover{
			color:#007465;
			 background-color: #ddd;
		}
		/*.ccrame{
		 transition:all 1s ease-out 
		
		}*/
	</style>
</head>
<body style="overflow-y:hidden;">
	<div class="layout-admin">
		<!-- top -->
		<header class="layout-header">
			<span class="header-logo"><img alt="image" width="190" height="68" src="plug-in/login/images/xymnlogo.jpg" /></span>
			
			<%--<div class="titlerow" >
				<div class="titlecell">
					<input id="searchbox" placeholder="请输入搜索关键字" class="searchbox" style="padding-right: 23px;border:0">
					<div class="iconssdiv">
						<i class="iconfont icon-close ui-iconss" style="font-weight:700;font-size:14px;display:none"></i>
						<i class="iconfont icon-sousuo ui-iconss"></i>
					</div>
				</div>
			</div>--%>
			<ul class="header-bar">
			
			<%--<li class="header-bar-nav personInfo" style="cursor:pointer;">
				<i class="icon-font">&#xe751;</i>&nbsp;
				<span>控制面板</span>
				<i class="icon-font adminIcon" style="margin-right:5px;">&#xe607;</i>
				<ul class="header-dropdown-menu" style="padding-right:4px">
					<li>
	                         <a href="javascript:openwindow('系统信息','tSSmsController.do?getSysInfos')" title="系统信息">系统信息</a>
					</li>
					<li >
	                         <a href="javascript:window.open('http://yun.jeecg.org')" title="云应用中心">云应用中心</a>
					</li>
					<li><a href="javascript:clearLocalstorage()"><t:mutiLang langKey="common.clear.localstorage"/></a></li>
				</ul>
			</li> --%>
			
			<li class="header-bar-nav">

					<span>
						<span id="Date"></span>
					</span>
			</li>
				
				
			<li class="header-bar-nav personInfo">
				<a href="javascript:;" id="personInfo">
					<span>
						<img src="plug-in/themes/fineui/common/image/head.jpg" style="width:24px;display:inline-block;border-radius:20px">
						<span>${userName}</span>
						<i class="icon-font adminIcon" style="margin-right:5px;">&#xe607;</i>
					</span>
				</a>
				
				<ul class="header-dropdown-menu" style="padding-right:4px">
					<%--<li>
						<a href="javascript:openwindow('个人信息','userController.do?userinfo')">
							个人信息
						</a>
					</li>
					<li>
                        <a href="javascript:add('<t:mutiLang langKey="common.change.password"/>','userController.do?changepassword','',550,200)">
                            <t:mutiLang langKey="common.change.password"/>
                        </a>
                    </li>--%>
					<li>
						<a href="javascript:logout()">
							<i class="icon-off"></i>
							 <t:mutiLang langKey="common.logout"/>
						</a>
					</li>
				</ul>
				
			</li>
		</ul>
	</header>
		
		<!-- 左侧菜单 -->
		<aside class="layout-side">
			<ul class="side-menu">
				<t:menu style="fineui" menuFun="${menuMap}"></t:menu>
			</ul>
		</aside>
		
		<!-- 切换左侧菜单栏 -->
	<!-- 	<div class="layout-side-arrow">
			<div class="layout-side-arrow-icon">
				<i class="icon-font">&#xe60e;</i>
			</div>
		</div> -->
		
		<!-- 右侧home -->
		<section class="layout-main">
			<div class="layout-main-tab">
				<button class="tab-btn btn-left"><i class="icon-font">&#xe628;</i></button>
                <nav class="tab-nav">
                    <div class="tab-nav-content" id="tab-contents-div">
                    	<div id="tytabbottomsepar" class="f-tabstrip-header-inkbar"></div>
                        <a href="javascript:void(0);" id="myhomeAtag" class="content-tab active" data-id="home.html">
                        <span class="fa fa-home colorgray"></span>首页</a>
                    </div>
                </nav>
                 
                <button id="activeTabToolRefresh" class="tab-btn mytabbtn" style="right:30px;" title="刷新本页"><i class="icon-font" style="font-size:16px;">&#xe60b;</i></button>
                <button class="tab-btn btn-right"><i class="icon-font">&#xe629;</i></button>
			</div>
			<div class="layout-main-body" style="margin:0;overflow-y: hidden;">
				<iframe class="body-iframe" name="iframe0" width="100%" height="100%" 
						src="loginController.do?fineuiHome" frameborder="0" data-id="home.html" seamless></iframe>
			</div>
		</section>
		
	</div>
</div>
	<script type="text/javascript" src="plug-in/themes/fineui/common/lib/jquery-1.9.0.min.js"></script>
	<script type="text/javascript" src="plug-in/themes/fineui/common/js/sccl.js"></script>
	<script type="text/javascript" src="plug-in/themes/fineui/common/js/sccl-util.js"></script>
	<t:base type="tools"></t:base>
	<script type="text/javascript" src="plug-in/themes/fineui/smart-menu/jquery-smartMenu.js"></script>
	<script src="plug-in/jquery-plugs/storage/jquery.storageapi.min.js"></script>
	<script type="text/javascript">
	function logout(){
		location.href="loginController.do?logout";
	}
	$(function(){
		//刷新本页面
		$("#activeTabToolRefresh").click(function(){
			var dataId = $("ul.side-menu").find("li.active").find("a").attr("href");
			if(!dataId){
				dataId = "home.html";
			}
			var obj = $('.body-iframe[data-id="'+dataId+'"]');
			var obj_none = obj.css('display');
			if(obj_none=='none'){
				obj = $('.body-iframe[data-id="home.html"]');
			}
			obj.attr('src', obj.attr('src'));
		});

		//搜索框样式效果
		$("#searchbox").focus(function(){
			$(this).addClass("searchbox-focus").addClass("searchbox-focusbg");
			$(this).next("div").addClass("ui-iconss-focus");
		});
		$("#searchbox").blur(function(){
			$(this).removeClass("searchbox-focus").removeClass("searchbox-focusbg");
			$(this).next("div").removeClass("ui-iconss-focus");
		});
		$("body").css("height",document.documentElement.clientHeight);
	});
	
	$(".personInfo").hover(function(){
	    $(this).find(".adminIcon").html("&#xe504;");
	    $(this).children(".header-dropdown-menu").css("width",$(this).width()-5);
	},function(){
		$(this).find(".adminIcon").html("&#xe607;");
	});
    function clearLocalstorage(){
        var storage=$.localStorage;
        if(!storage)
            storage=$.cookieStorage;
        storage.removeAll();
        //bootbox.alert( "浏览器缓存清除成功!");
        layer.msg("浏览器缓存清除成功!");
    }
    window.onload=function(){
        setInterval(function(){
            var date=new Date();
            var year=date.getFullYear(); //获取当前年份
            var mon=date.getMonth()+1; //获取当前月份
            var da=date.getDate(); //获取当前日
            var day=date.getDay(); //获取当前星期几
            var h=date.getHours(); //获取小时
            var m=date.getMinutes(); //获取分钟
            var s=date.getSeconds(); //获取秒
            var d=document.getElementById('Date');
            d.innerHTML=''+year+'年'+mon+'月'+da+'日'+'星期'+day+' '+h+':'+m+':'+s;  },1000)  }
	</script>
</body>
</html>

