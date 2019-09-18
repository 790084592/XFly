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
		this.bindHeaderEvent();    //绑定顶部按钮栏事件
	} 
	
	/**
	 * 定义界面结构
	 */
	MainPage.prototype.initSplitpane = function() {
		var htmlstr = [];
	    htmlstr.push("<iframe style='width:100%; height:calc(100% - 34px)' frameborder='0' marginheight='0' marginwidth='0' allowfullscreen='true'></iframe>");
	    
		this.container.innerHTML = htmlstr.join("");
		debugger
		this.mainFrame = this.container.firstChild;
		this.mainFrame.src = "/login";
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
		htmlstr.push('			<span class="xhui-coolbar-more  xhui-hide"></span>');
		htmlstr.push('		</li>');
		htmlstr.push('		<li class="xhui-coolbar-item xhui-coolbar-group" id="datasubjct">');
		htmlstr.push('			<span class="xhui-coolbar-item-text">' + "数据集 "+ '</span>');
		htmlstr.push('			<span class="xhui-coolbar-more  xhui-hide"></span>');
		htmlstr.push('		</li>');
		htmlstr.push('		<li class="xhui-coolbar-item xhui-coolbar-group" id="dataanalyse">');
		htmlstr.push('			<span class="xhui-coolbar-item-text">' + "数据分析 "+ '</span>');
		htmlstr.push('			<span class="xhui-coolbar-more xhui-hide"></span>');
		htmlstr.push('		</li>');
		htmlstr.push('		<li class="xhui-coolbar-item xhui-coolbar-group" id="systemset">');
		htmlstr.push('			<span class="xhui-coolbar-item-text">' + "系统设置"+ '</span>');
		htmlstr.push('			<span class="xhui-coolbar-more"></span>');
		htmlstr.push('		</li>');
		htmlstr.push('		</ul">');
		htmlstr.push('	</div>');
		this.header.innerHTML = htmlstr.join("");
	}
	
	/**
	 * 绑定顶部按钮栏事件
	 */
	MainPage.prototype.bindHeaderEvent = function() {
		this.dSourceBtn = this.doc.getElementById("datasource");
		this.dSourceBtn.onclick = function(){
			this.mainFrame.src = "/UIDemo";
		}
		this.dSubjectBtn = this.doc.getElementById("datasubject");
		this.dAnalyseBtn = this.doc.getElementById("dataanalyse");
		this.systemBtn = this.doc.getElementById("systemset");
	}
	
	return {
		MainPage : MainPage
	}
});