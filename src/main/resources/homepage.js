define(function() {   
	"use strict"

    /**
	 * 首页
	 * 加载左树
	 * @param {Window} [options.wnd=window] window对象
	 * 
	 */
	function HomePage(wnd) {
		//resource目录下
		this.wnd = wnd || window;
		this.doc = this.wnd.document;
		this.initPage(); // 初始化界面
	}
    
    /**
     * 分割面板初始化
     * 1.定义左树
     * 2.定义右边标签栏（借阅记录标签页、统计分析标签页）
     */
	HomePage.prototype.initPage = function() {
		this.initSplitpane();	   //初始化界面	
	} 	
	/**
	 * 定义分割面板
	 */
	HomePage.prototype.initSplitpane = function() {
		this.container = this.doc.getElementById("xfly-layout-container")
		this.container.style.cssText += "; background-color:#f1f1f1;";
	}

	/**
	 * 销毁方法
	 */
	HomePage.prototype.dispose = function() {
		this.wnd = null;
		this.doc = null;
		this.container = null;
	};
	
	return {
		HomePage : HomePage
	}
});