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
		htmlStr.push('<div class="xhui-layout-row-1 xhui-layout-row-first xhui-layout-nav">');
		htmlStr.push('</div>');
		htmlStr.push('<div class="xhui-layout-row-3 xhui-layout-row-offset-1 xhui-layout-row-offsetbottom-1 ">');	
		htmlStr.push('	<table id="filelist" class="table table-striped "></table>');
		htmlStr.push('</div>');
		htmlStr.push('<div class="xhui-layout-row-1 xhui-layout-row-last xhui-align-center xhui-padding-left-20 xhui-padding-right-20 xhui-padding-top-10 xhui-padding-bottom-10" >分页条保留地</div>');
		this.container.innerHTML = htmlStr.join("");
		this.list = $('#filelist');
		this.list.bootstrapTable({
			striped: true, //行之间间隔色
			cache: false,
			columns: [
				{
					checkbox: true,  
					visible: true       //是否显示复选框  
				},{
					field: 'caption',
					title: "数据源标题"
				},{
					field: 'filename',
					title: "文件名称"
				}, {
					field: "filetype",
					title: "文件类型"
				}, {
					field: "uploadtime",
					title: "上传时间"
				},{
					field: 'oper',
					title: "操作",
					formatter: function(value, row, index){
						var id = value;
						var result = "";
						result += "<a href='javascript:;' class='btn btn-xs green' onclick=\"EditViewById('" + id + "', view='view')\" title='查看'><span class='glyphicon glyphicon-search'></span></a>";
						result += "<a href='javascript:;' class='btn btn-xs blue' onclick=\"EditViewById('" + id + "')\" title='编辑'><span class='glyphicon glyphicon-pencil'></span></a>";
						result += "<a href='javascript:;' class='btn btn-xs red' onclick=\"DeleteByIds('" + id + "')\" title='删除'><span class='glyphicon glyphicon-remove'></span></a>";
						return result;
					}
				}
			]});
		var data = {
				"caption" : "卫生数据",
				"filename" : "卫生数据-2019",
				"filetype":"Excel",
				"uploadtime":"2019.09.10",
				"oper":"1"};
		for(var i =0; i<55; i++){
			this.list.bootstrapTable('append', [data]);
		}
	}
	
	FileList.prototype.initLeftPanel = function() {
		
	}
	
	FileList.prototype.initRightPanel = function() {
		
	}

	return {
		FileList : FileList
	}
});