define(["third/echarts.min"], function(echarts) {   
	"use strict"

    
	/**
	 * bootstrap示例页面
	 * 1.暂时只尝试了按钮
	 */
	function POIDemo(options) {
		this.wnd = options.wnd || window;
		this.doc = this.wnd.document;
		this.container = options.container;
		this.initPage(); // 初始化界面
	}
    
	/**
	 * 销毁方法
	 */
	POIDemo.prototype.dispose = function() {
		this.wnd = null;
		this.doc = null;
		this.container = null;
	}
	
	
    /**
     * 初始化界面
     */
	POIDemo.prototype.initPage = function() {
		this.initUI();	   //初始化界面	框架
	} 
	
	/**
	 * 定义界面结构
	 */
	POIDemo.prototype.initUI = function() {
		var self = this;
		var htmlStr = [];
		htmlStr.push('<input type="file" id="file" name="myfile" />');
		htmlStr.push('<input type="button" onclick="UpladFile()" value="上传" />');
		htmlStr.push('<button type="button" id="myStateButton" data-complete-text="finished!" class="btn btn-primary" autocomplete="off">按钮</button>');
		this.container.innerHTML = htmlStr.join("");
		var button = this.doc.getElementById("myStateButton");
		button.onclick = function(){

			self.UpladFile();
		}
// 		此为一种获取后台数据的办法
//		$.get("http://127.0.0.1:8888//excel/import", function(data) {
//	   		alert(data);
//		});
	}
	
	//上传文件方法
	POIDemo.prototype.UpladFile = function() {
        var fileObj = this.doc.getElementById("file").files[0]; // js 获取文件对象
        var url =  "http://127.0.0.1:8888//excel/import"; // 接收上传文件的后台地址

        var form = new FormData(); // FormData 对象
        form.append("file", fileObj); // 文件对象

        var xhr = new XMLHttpRequest();  // XMLHttpRequest 对象
        xhr.open("post", url, false); //post方式，url为服务器请求地址，true 该参数规定请求是否异步处理。
        xhr.onload = uploadComplete; //请求完成
        xhr.onerror =  uploadFailed; //请求失败
        xhr.send(form); //开始上传，发送form数据
    }
	
	//上传成功响应
    function uploadComplete(evt) {
        //服务断接收完文件返回的结果
        var data = evt.target.responseText;
        if(data){
        	 alert("上传成功！");
        }else{
        	alert("上传失败1！");
        }
         

    }
    //上传失败
    function uploadFailed(evt) {
    	alert("上传失败2！");
    }

	
	return {
		POIDemo : POIDemo
	}
});