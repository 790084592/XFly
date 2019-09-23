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
		this.initRightPanel();
	}
    
	/**
	 * 销毁方法
	 */
	FileList.prototype.dispose = function() {
		this.wnd = null;
		this.doc = null;
		this.container = null;
		this.toolbar = null;
		this.list = null;
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
		htmlStr.push('<div id="toolbar" class="xhui-layout-row-1 xhui-layout-row-first xhui-layout-nav">');
		htmlStr.push('</div>');
		htmlStr.push('<div class="xhui-layout-row-3 xhui-layout-row-offset-1 xhui-layout-row-last xhui-layout-row-offsetbottom-2list ">');	
		htmlStr.push('	<table id="filelist" class="table table-striped "></table>');
		htmlStr.push('</div>');
		this.container.innerHTML = htmlStr.join("");

		this.toolbar = this.doc.getElementById("toolbar");
		this.list = $('#filelist');
		this.list.bootstrapTable({
			striped: true, //行之间间隔色
			cache: false,
			pagination: true, 
			pageList:[10,50,100],
			pageSize: 10,                     //每页的记录行数（*）
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
		var htmlStr = [];
		htmlStr.push('<div class="btn-group">');
		htmlStr.push('	<div class="btn-group">');
		htmlStr.push('		<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">新建');
		htmlStr.push('     		<span class="caret"></span>');
		htmlStr.push('		 </button>');
		htmlStr.push('		 <ul class="dropdown-menu">');
		htmlStr.push('     		<li><button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">Excel</button></li>');
		htmlStr.push('     		<li><a href="#">其他</a></li>');
		htmlStr.push(' 		</ul>');
		htmlStr.push('	</div>');
		htmlStr.push('	<button type="button" class="btn btn-default">删除</button>');
		htmlStr.push('	<button type="button" class="btn btn-default">刷新</button>');
		htmlStr.push('</div>');
		this.toolbar.innerHTML = htmlStr.join("");
		
		var model = this.doc.createElement("div");
		var modelHtml = [];
		modelHtml.push('<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">');
		modelHtml.push('<div class="modal-dialog">');
		modelHtml.push('    <div class="modal-content">');
		modelHtml.push('        <div class="modal-header">');
		modelHtml.push('             <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>');
		modelHtml.push('            <h4 class="modal-title" id="myModalLabel">模态框（Modal）标题</h4>');
		modelHtml.push('        </div>');
		modelHtml.push('        <div class="modal-body">在这里添加一些文本</div>');
		modelHtml.push('        <div class="modal-footer">');
		modelHtml.push('            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>');
		modelHtml.push('            <button type="button" class="btn btn-primary">提交更改</button>');
		modelHtml.push('        </div>');
		modelHtml.push('    </div><!-- /.modal-content -->');
		modelHtml.push('</div><!-- /.modal -->');
		modelHtml.push('</div>');
	    model.innerHTML = modelHtml.join("");
	    debugger
	    this.container.appendChild(model);
	}

	return {
		FileList : FileList
	}
});