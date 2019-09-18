define(["third/echarts.min"], function(echarts) {   
	"use strict"

    
	/**
	 * 网格页面
	 */
	function MainPage(options) {
		this.wnd = options.wnd || window;
		this.doc = this.wnd.document;
		this.header = options.header;
		this.container = options.container;
		this.initPage(); // 初始化界面
	}
    
	/**
	 * 销毁方法
	 */
	MainPage.prototype.dispose = function() {
		this.wnd = null;
		this.doc = null;
		this.header = null;
		this.container = null;
	}
	
	
    /**
     * 初始化界面
     */
	MainPage.prototype.initPage = function() {
		this.initSplitpane();	   //初始化界面	
		this.initHeader();         //初始顶部栏
	} 
	
	/**
	 * 定义界面结构
	 */
	MainPage.prototype.initSplitpane = function() {
		var htmlstr = [];
		var waitTitle =  "正在加载...";
		

		htmlstr.push('			<div class="xhui-row xhui-change-bg " style="height:100%;width:100%;" id="line3">');
		//近、离线归档
		htmlstr.push('	  			<div class="xhui-col-xl-4 xhui-padding-top-5 xhui-padding-right-5 xhui-height-max">');
		htmlstr.push('					<div class="xhui-change-item xhui-align-center xhui-height-max xhui-relative">');
		htmlstr.push('		  				<div class="xhui-layout-row-1 xhui-layout-row-first xhui-padding-left-5 xhui-padding-right-10 " id="lineAreaArchiveTab">');
		htmlstr.push('	  	  				</div>');
		htmlstr.push('		  				<div class="xhui-layout-row-3 xhui-layout-row-last xhui-padding-left-10 xhui-padding-right-10 xhui-top-30" id="container3-1">');
		htmlstr.push('	  						<div class="xhui-height-max xhui-padding-top-15" id="nearofflist"></div>');
		htmlstr.push('	  	  				</div>');
		htmlstr.push('						<div class="xhui-change-item xhui-height-max xhui-hide xhui-align-center xhui-weaken" id="wait-container3-1">');
		htmlstr.push('							<div class="xhui-wait">');
		htmlstr.push('								<i class="xhui-icon xhui-anim xhui-anim-rotate xhui-anim-loop">&#xefa1;</i><span>' + waitTitle + '</span>');
		htmlstr.push('							</div>');
		htmlstr.push('						</div>');
		htmlstr.push('					</div>');
		htmlstr.push('	  			</div>');
		//近线归档和销毁Top5
		htmlstr.push('	 			<div class="xhui-col-xl-4 xhui-padding-top-5 xhui-padding-left-5 xhui-height-max">');
		htmlstr.push('					<div class="xhui-change-item xhui-align-center xhui-height-max xhui-relative">');
		htmlstr.push('		  				<div class="xhui-layout-row-1 xhui-layout-row-first xhui-padding-left-5 xhui-padding-right-10 " id="top5Tab">');
		htmlstr.push('	  	  				</div>');
		htmlstr.push('		  				<div class="xhui-layout-row-3 xhui-layout-row-last xhui-padding-left-10 xhui-padding-right-10 xhui-top-30" id="container3-2">');
		htmlstr.push('	  						<div class="xhui-height-max xhui-padding-top-15" id="toplist"></div>'); 
		htmlstr.push('	  					</div>');
		htmlstr.push('						<div class="xhui-change-item xhui-height-max xhui-hide xhui-align-center xhui-weaken" id="wait-container3-2">');
		htmlstr.push('							<div class="xhui-wait">');
		htmlstr.push('								<i class="xhui-icon xhui-anim xhui-anim-rotate xhui-anim-loop">&#xefa1;</i><span>' + waitTitle + '</span>');
		htmlstr.push('							</div>');
		htmlstr.push('						</div>');
		htmlstr.push('					</div>');
		htmlstr.push('	  			</div>');
		//归档资产分布
		htmlstr.push('	 			<div class="xhui-col-xl-4 xhui-padding-top-5 xhui-padding-left-5 xhui-height-max">');
		htmlstr.push('					<div class="xhui-change-item xhui-align-center xhui-height-max xhui-relative">');
		htmlstr.push('		  				<div class="xhui-layout-row-1 xhui-layout-row-first xhui-padding-top-5 xhui-padding-left-20 xhui-title">');
		htmlstr.push('							<img src="images/safe_zc.png" class="xhui-float-left xhui-padding-right-5" style="padding-top:2px;"/>');
		htmlstr.push('							<label>' + 3 + '</label>');
		htmlstr.push('		  				</div>');
		htmlstr.push('		  				<div class="xhui-layout-row-3 xhui-layout-row-last xhui-padding-left-10 xhui-padding-right-10 xhui-top-30" id="container3-3">');
		htmlstr.push('	  						<div class="xhui-col-xl-12 xhui-height-max" id="piechart"></div>');
		htmlstr.push('	  	  				</div>');
		htmlstr.push('						<div class="xhui-change-item xhui-height-max xhui-hide xhui-align-center xhui-weaken" id="wait-container3-3">');
		htmlstr.push('							<div class="xhui-wait">');
		htmlstr.push('								<i class="xhui-icon xhui-anim xhui-anim-rotate xhui-anim-loop">&#xefa1;</i><span>' + waitTitle + '</span>');
		htmlstr.push('							</div>');
		htmlstr.push('						</div>');
		htmlstr.push('					</div>');
		htmlstr.push('	  			</div>');
		htmlstr.push('			</div>');
		this.container.innerHTML = htmlstr.join("");
		
	}
	

	/**
	 * 定义界面结构
	 */
	MainPage.prototype.initHeader = function() {
		var htmlstr = [];
		htmlstr.push('<div class="xhui-layout-logo " ></div>');
		htmlstr.push('<div class="xhui-layout-header-left" >');
		htmlstr.push('	<div class="xhui-coolbar-container" _selectabletype_="false" style="user-select: none;">');
		htmlstr.push('		<ul class="xhui-coolbar-group">');
		htmlstr.push('		<li class="xhui-coolbar-item xhui-coolbar-group" >');
		htmlstr.push('			<div id="datasource"><span class="xhui-coolbar-item-text">' + "数据源"+ '</span></div>');
		htmlstr.push('			<span class="xhui-coolbar-more"></span>');
		htmlstr.push('		</li>');
		htmlstr.push('		<li class="xhui-coolbar-item xhui-coolbar-group" id="datasubjct">');
		htmlstr.push('			<span class="xhui-coolbar-item-text">' + "数据集 "+ '</span>');
		htmlstr.push('			<span class="xhui-coolbar-more"></span>');
		htmlstr.push('		</li>');
		htmlstr.push('		<li class="xhui-coolbar-item xhui-coolbar-group" id="dataanalyse">');
		htmlstr.push('			<span class="xhui-coolbar-item-text">' + "数据分析 "+ '</span>');
		htmlstr.push('			<span class="xhui-coolbar-more"></span>');
		htmlstr.push('		</li>');
		htmlstr.push('		</ul">');
		htmlstr.push('	</div>');
		this.header.innerHTML = htmlstr.join("");
		$("#datasource").hover(function(){						 
			 alert("1");
		},function(){
			 alert("0");
		});
	}
	
	return {
		MainPage : MainPage
	}
});