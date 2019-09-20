define(["/third/echarts.min.js"], function(echarts) {   
	"use strict"

    
	/**
	 * 数据源页面
	 */
	function FileList(options) {
		this.wnd = options.wnd || window;
		this.doc = this.wnd.document;
		this.container = options.container;
		this.initPage(); // 初始化界面
	}
    
	/**
	 * 销毁方法
	 */
	FileList.prototype.dispose = function() {
		this.wnd = null;
		this.doc = null;
		this.container = null;
	}
	
	
    /**
     * 初始化界面
     */
	FileList.prototype.initPage = function() {
		this.initUI();	   //初始化界面框架
	} 
	
	/**
	 * 定义界面结构
	 */
	FileList.prototype.initUI = function() {
		var htmlStr = [];
		htmlStr.push('<div class="xhui-layout-row-1 xhui-layout-row-first xhui-padding-right-20 xhui-layout-nav">');
		htmlStr.push('</div>');
		htmlStr.push('<div class="xhui-layout-row-3 xhui-layout-row-offset-1 xhui-layout-row-last">');	
		htmlStr.push('	<div class="xhui-padding-left-20 xhui-padding-right-20">');
		htmlStr.push('		<table id="filelist" class="table table-striped"></table>');
		htmlStr.push('	</div>');
		htmlStr.push('</div>');
		this.container.innerHTML = htmlStr.join("");
		this.list = $('#filelist');
		this.list.bootstrapTable({
			columns: [
				{
					field: 'id',
					title: '账号'
				},{
					field: 'name',
					title: 'Item Name'
				}, {
					field: 'price',
					title: 'Item Price'
				}
			]});
		var data = {
				"id" : 1,
				"name" : 2,
				"price":3};
		this.list.bootstrapTable('append', [data]);
	}
	
	FileList.prototype.initLeftPanel = function() {
		
	}
	
	FileList.prototype.initRightPanel = function() {
		
	}

	return {
		FileList : FileList
	}
});