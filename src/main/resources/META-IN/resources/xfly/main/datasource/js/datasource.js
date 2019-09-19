define(["third/echarts.min"], function(echarts) {   
	"use strict"

    
	/**
	 * 数据源页面
	 */
	function DataSource(options) {
		this.wnd = options.wnd || window;
		this.doc = this.wnd.document;
		this.container = options.container;
		this.initPage(); // 初始化界面
	}
    
	/**
	 * 销毁方法
	 */
	DataSource.prototype.dispose = function() {
		this.wnd = null;
		this.doc = null;
		this.container = null;
	}
	
	
    /**
     * 初始化界面
     */
	DataSource.prototype.initPage = function() {
		this.initUI();	   //初始化界面框架
	} 
	
	/**
	 * 定义界面结构
	 */
	DataSource.prototype.initUI = function() {
		var waitTitle = "加载中...";
		var htmlStr = [];
		//左侧
		htmlStr.push('<div class="xhui-layout-left-tab" style=";background: #1c1541;">');
		htmlStr.push('	<div class="xhui-coolbar-container xhui-coolbar-verticaltab" _selectabletype_="false" style="user-select: none;">');
		htmlStr.push('		<ul id="iconpage" class="xhui-coolbar-group">');
		htmlStr.push('			<li id="conndatasource" class="xhui-coolbar-item xhui-coolbar-item-group xhui-coolbar-item-group-first">');
		htmlStr.push('				<i class="xhui-icon">&#xe774</i><span class="xhui-coolbar-item-text">连接池<br>管理</span>');
		htmlStr.push('			</li>');
		htmlStr.push('			<li id="filedatasource" class="xhui-coolbar-item xhui-coolbar-item-group xhui-coolbar-item-group-last xhui-coolbar-active" style="">');
		htmlStr.push('				<i class="xhui-icon">&#xef9d</i><span class="xhui-coolbar-item-text">文件<br>数据源</span>');
		htmlStr.push('			</li>');
		htmlStr.push('			<li class="xhui-coolbar-line"></li>');
		htmlStr.push('		</ul>');
		htmlStr.push('</div>');
		//右侧
		htmlStr.push('<div class="xhui-layout-content-tab xhui-tab-addnav  ">');
		htmlStr.push('</div>');
		this.container.innerHTML = htmlStr.join("");
	}
	
	DataSource.prototype.initLeftPanel = function() {
		
	}
	
	DataSource.prototype.initRightPanel = function() {
		
	}

	return {
		DataSource : DataSource
	}
});