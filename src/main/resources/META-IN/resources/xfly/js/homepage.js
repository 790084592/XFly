define(["third/echarts.min"], function(echarts) {   
	"use strict"

    
	/**
	 * 网格页面
	 */
	function HomePage(options) {
		this.wnd = options.wnd || window;
		this.doc = this.wnd.document;
		this.container = options.container;
		this.initPage(); // 初始化界面
	}
    
	/**
	 * 销毁方法
	 */
	HomePage.prototype.dispose = function() {
		this.wnd = null;
		this.doc = null;
		this.container = null;
		this.pineChartDom = null;
	}
	
	
    /**
     * 初始化界面
     */
	HomePage.prototype.initPage = function() {
		this.initSplitpane();	   //初始化界面	
		this.initPieChart();	   //初始化饼图
	} 
	
	/**
	 * 定义界面结构
	 */
	HomePage.prototype.initSplitpane = function() {
		var htmlstr = [];
		var sizesTitle = "数据量";
		var countsTitle =  "记录行数";
		var nearLineResTitle = "近线归档资产";
		var offLineResTitle =  "离线归档资产";
		var desResTitle = "销毁资产";
		var pinTitle =  "归档资产分布";
		var waitTitle =  "正在加载...";
		
		htmlstr.push('<div class="xhui-height-max xhui-change-bg">');
		htmlstr.push('	<div class="xhui-layout-container" id="mainpage">');
		//第一行
		htmlstr.push('			<div class="xhui-row " style="height:25%;" id="line1">');
		htmlstr.push('				<div class="xhui-change-item xhui-align-center xhui-height-max xhui-relative">');
		htmlstr.push('		  			<div class="xhui-layout-row-1 xhui-layout-row-first xhui-padding-left-5 xhui-padding-right-10 " id="dataSetTimeTab">');
		htmlstr.push('	  	  			</div>');
		htmlstr.push('		  			<div class="xhui-layout-row-3 xhui-layout-row-last xhui-padding-top-5 xhui-padding-left-10 xhui-padding-right-10 xhui-top-30 " id="container1">');
		//近线归档资产
		htmlstr.push('	  					<div class="xhui-col-xl-4 xhui-height-max xhui-padding-top-5 " id="line1">');
		htmlstr.push('							<div class="xhui-change-item xhui-align-center xhui-height-max xhui-relative" style="border-radius:0px;border-right:1px solid #f0f0f0;">');
		htmlstr.push('								<div class="xhui-layout-row-1 xhui-layout-row-first xhui-padding-top-5 xhui-padding-left-25 xhui-title">');
		htmlstr.push('									<img src="images/safe_jx.png" class=" xhui-float-left xhui-padding-right-5"/>');
		htmlstr.push('									<label >' + nearLineResTitle +'</label>');
		htmlstr.push('								</div>');
		htmlstr.push('								<div class="xhui-layout-row-3 xhui-layout-row-last xhui-row xhui-padding-bottom-10 xhui-top-30" id="container1-1">');
		htmlstr.push('									<div class="xhui-col-xl-6 xhui-height-max xhui-align-center" style="border-right:1px solid #f0f0f0;">');
		htmlstr.push('										<div class="lc-align-center ">');
		htmlstr.push('											<div class=" xhui-num " id="near-size"></div>');
		htmlstr.push('											<label>' + sizesTitle + '</label>');
		htmlstr.push('										</div>');
		htmlstr.push('									</div>');
		htmlstr.push('									<div class="xhui-col-xl-6 xhui-height-max xhui-align-center ">');
		htmlstr.push('										<div class="lc-align-center ">');
		htmlstr.push('											<div class=" xhui-num" id="near-count"></div>');
		htmlstr.push('											<label>' + countsTitle + '</label>');
		htmlstr.push('										</div>');
		htmlstr.push('									</div>');
		htmlstr.push('								</div>');
		htmlstr.push('								<div class="xhui-change-item xhui-height-max xhui-hide xhui-align-center xhui-weaken" id="wait-container1-1">');
		htmlstr.push('									<div class="xhui-wait">');
		htmlstr.push('										<i class="xhui-icon xhui-anim xhui-anim-rotate xhui-anim-loop">&#xefa1;</i><span>' + waitTitle + '</span>');
		htmlstr.push('									</div>');
		htmlstr.push('								</div>');
		htmlstr.push('							</div>');
		htmlstr.push('	  					</div>');
		//离线归档资产
		htmlstr.push('	  					<div class="xhui-col-xl-4 xhui-height-max xhui-padding-top-5">');
		htmlstr.push('							<div class="xhui-change-item xhui-align-center xhui-height-max xhui-relative" style="border-radius:0px;border-right:1px solid #f0f0f0;">');
		htmlstr.push('								<div class="xhui-layout-row-1 xhui-layout-row-first xhui-padding-top-5 xhui-padding-left-25 xhui-title">');
		htmlstr.push('									<img src="images/safe_lx.png" class=" xhui-float-left xhui-padding-right-5"/>');
		htmlstr.push('									<label>' + offLineResTitle + '</label>');
		htmlstr.push('								</div>');
		htmlstr.push('								<div class="xhui-layout-row-3 xhui-layout-row-last xhui-row xhui-padding-bottom-10 xhui-top-30" id="container1-2">');
		htmlstr.push('									<div class="xhui-col-xl-6 xhui-height-max xhui-align-center" style="border-right:1px solid #f0f0f0;">');
		htmlstr.push('										<div class="lc-align-center ">');
		htmlstr.push('											<div class=" xhui-num " id="off-size"></div>');
		htmlstr.push('											<label>' + sizesTitle + '</label>');
		htmlstr.push('										</div>');
		htmlstr.push('									</div>');
		htmlstr.push('									<div class="xhui-col-xl-6 xhui-height-max xhui-align-center ">');
		htmlstr.push('										<div class="lc-align-center ">');
		htmlstr.push('											<div class=" xhui-num" id="off-count"></div>');
		htmlstr.push('											<label>' + countsTitle + '</label>');
		htmlstr.push('										</div>');
		htmlstr.push('									</div>');
		htmlstr.push('								</div>');
		htmlstr.push('								<div class="xhui-change-item xhui-height-max xhui-hide xhui-align-center xhui-weaken" id="wait-container1-2">');
		htmlstr.push('									<div class="xhui-wait">');
		htmlstr.push('										<i class="xhui-icon xhui-anim xhui-anim-rotate xhui-anim-loop">&#xefa1;</i><span>' + waitTitle + '</span>');
		htmlstr.push('									</div>');
		htmlstr.push('								</div>');
		htmlstr.push('							</div>');
		htmlstr.push('	  					</div>');
		//销毁资产
		htmlstr.push('	  					<div class="xhui-col-xl-4 xhui-height-max xhui-padding-top-5">');
		htmlstr.push('							<div class="xhui-change-item xhui-align-center xhui-height-max xhui-relative">');
		htmlstr.push('								<div class="xhui-layout-row-1 xhui-layout-row-first xhui-padding-top-5 xhui-padding-left-25 xhui-title">');
		htmlstr.push('									<img src="images/safe_xh.png" class=" xhui-float-left xhui-padding-right-5"/>');
		htmlstr.push('									<label>' + desResTitle + '</label>');
		htmlstr.push('								</div>');
		htmlstr.push('								<div class="xhui-layout-row-3 xhui-layout-row-last xhui-row xhui-padding-bottom-10 xhui-top-30" id="container1-3">');
		htmlstr.push('									<div class="xhui-col-xl-6 xhui-height-max xhui-align-center" style="border-right:1px solid #f0f0f0;">');
		htmlstr.push('										<div class="lc-align-center ">');
		htmlstr.push('											<div class=" xhui-num " id="destory-size"></div>');
		htmlstr.push('											<label>' + sizesTitle + '</label>');
		htmlstr.push('										</div>');
		htmlstr.push('									</div>');
		htmlstr.push('									<div class="xhui-col-xl-6 xhui-height-max xhui-align-center ">');
		htmlstr.push('										<div class="lc-align-center ">');
		htmlstr.push('											<div class=" xhui-num" id="destory-count"></div>');
		htmlstr.push('											<label>' + countsTitle + '</label>');
		htmlstr.push('										</div>');
		htmlstr.push('									</div>');
		htmlstr.push('								</div>');
		htmlstr.push('								<div class="xhui-change-item xhui-height-max xhui-hide xhui-align-center xhui-weaken" id="wait-container1-3">');
		htmlstr.push('									<div class="xhui-wait">');
		htmlstr.push('										<i class="xhui-icon xhui-anim xhui-anim-rotate xhui-anim-loop">&#xefa1;</i><span>' + waitTitle + '</span>');
		htmlstr.push('									</div>');
		htmlstr.push('								</div>');
		htmlstr.push('							</div>');
		htmlstr.push('	  					</div>');
		htmlstr.push('	  	  			</div>');
		htmlstr.push('					<div class="xhui-change-item xhui-height-max xhui-hide xhui-align-center xhui-weaken" id="wait-container1">');
		htmlstr.push('						<div class="xhui-wait">');
		htmlstr.push('							<i class="xhui-icon xhui-anim xhui-anim-rotate xhui-anim-loop">&#xefa1;</i><span>' + waitTitle + '</span>');
		htmlstr.push('						</div>');
		htmlstr.push('					</div>');
		htmlstr.push('				</div>');
		htmlstr.push('		</div>');
		//第二行
		htmlstr.push('			<div class="xhui-row " style="height:40%" id="line2">');
		htmlstr.push('	  			<div class="xhui-col-xl-12 xhui-padding-top-5 xhui-height-max">');
		htmlstr.push('					<div class="xhui-change-item xhui-align-center xhui-height-max xhui-relative">');
		htmlstr.push('		  				<div class="xhui-layout-row-1 xhui-layout-row-first xhui-padding-left-5 xhui-padding-right-10 " id="barChartTab">');
		htmlstr.push('	  	  				</div>');
		htmlstr.push('		  				<div class="xhui-layout-row-3 xhui-layout-row-last xhui-padding-top-15 xhui-padding-left-10 xhui-padding-right-10 xhui-top-30" id="container2"> ');
		htmlstr.push('	  						<div class="xhui-height-max" id="barchart"></div>');
		htmlstr.push('	  	  				</div>');
		htmlstr.push('						<div class="xhui-change-item xhui-height-max xhui-hide xhui-align-center xhui-weaken" id="wait-container2">');
		htmlstr.push('							<div class="xhui-wait">');
		htmlstr.push('								<i class="xhui-icon xhui-anim xhui-anim-rotate xhui-anim-loop">&#xefa1;</i><span>' + waitTitle + '</span>');
		htmlstr.push('							</div>');
		htmlstr.push('						</div>');
		htmlstr.push('					</div>');
		htmlstr.push('	  			</div>');
		htmlstr.push('			</div>');
		//第三行
		htmlstr.push('			<div class="xhui-row " style="height:35%" id="line3">');
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
		htmlstr.push('							<label>' + pinTitle + '</label>');
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
		htmlstr.push('	</div>');
		htmlstr.push('</div>');

		this.container.innerHTML = htmlstr.join("");
		this.pineChartDom = this.doc.getElementById("container3-3");
	}
	
	/**
	 * 初始化，饼图
	 */
	HomePage.prototype.initPieChart = function() {
		var self = this;
		self.nearPieGraph = echarts.init(self.pineChartDom);
		self.nearPieOption = {
				tooltip : {
					trigger : 'item',
					formatter :"{b} {d}%"
				},
				legend: {
			        x: 'center',
			        y: 'bottom',
			        icon : 'circle',
			        data: ["近线", "离线", "销毁"]
			    },
			     series: [
			        {
			        	type : "pie",
						radius : [ "40%", "60%" ],
						color : [
							'#fc9524',
							'#facf2a',
							'#1eafc5'],
						startAngle : 45,
						label : {
							color : "#000",
							position : "outside",
							formatter : "{b} {d}%"
						},
			          data: null
			        } 
			      ]
		}
		self.nearPieOption.series[0]["data"] = [
			{name: "销毁", value: 6, count: 0},
			{name: "离线", value: 1100, count: 0},
			{name: "近线", value: 7134, count: 0}
		];
		self.nearPieGraph.setOption(self.nearPieOption);
	}
	
//	/**
//	 * 获取并刷新饼图数据
//	 */
//	xhuiMonitor.prototype.refreshPieChart = function() {
//		var self = this;
//		xhui.addClassName(self.container33, "xhui-hide");
//		xhui.removeClassName(self.wContainer33, "xhui-hide");
//		var self = this;
//		xhui.post({
//			url: self._actionUrl + "piegraph.do",
//			callback: function(queryobj){
//				var data = queryobj.getResponseJSON();
//				self.nearPieOption.series[0]["data"] = data;
//				self.nearPieGraph.setOption(self.nearPieOption);
//				xhui.addClassName(self.wContainer33, "xhui-hide");
//				xhui.removeClassName(self.container33, "xhui-hide");
//			}
//		})
//	}
//	
//	/**
//	 * 初始化条形图
//	 */
//	xhuiMonitor.prototype.initBarChart = function() {
//		var self = this;
//		this.barGraph = echarts.init(self.barChartDom);
//		this.barOption = {
//				tooltip: {
//						trigger: 'axis'
//				},
//				grid: {
//					show:false,
//					top:'30',
//					bottom:'30',
//					right:'60',
//					left:'90'
//				},
//				legend: {
//					data: [I18N.getString("edatalifecycle.xhuimoniter.js.xhuimonitor.js.countstitle", "记录行数"), '归档次数']
//				},
//				xAxis: [{
//						type: 'category',
//						data: null,
//						axisPointer: {
//					                type: 'shadow'
//						},
//						axisTick: {
//							show: true,
//							interval: 0
//						}
//					}],
//				//设置两个y轴，左边显示数量，右边显示概率
//				yAxis: [{
//							type: 'value',
//							name: I18N.getString("edatalifecycle.xhuimoniter.js.xhuimonitor.js.countstitle", "记录行数"),
//							show:  true,
//							max: 1000,
//							interval: 200
//						},{
//							type: 'value',
//							name: I18N.getString("edatalifecycle.xhuimoniter.js.xhuimonitor.js.archive_times", "归档次数"),
//							min: 0,
//							max: 100,
//							interval: 20
//						}],
//				series: [{
//							name: I18N.getString("edatalifecycle.xhuimoniter.js.xhuimonitor.js.countstitle", "记录行数"),
//							type: 'bar',
//							data: null,
//							barWidth : '50%',	
//							itemStyle: {
//								normal: {
//									color:"#31B4C9"
//						        	}
//						    	}					            
//					      },{
//								name: I18N.getString("edatalifecycle.xhuimoniter.js.xhuimonitor.js.archive_times", "归档次数"),
//					            type: 'line',
//					            yAxisIndex: 1,    //这里要设置哪个y轴，默认是最左边的是0，然后1，2顺序来。
//					            data: null,
//								smooth:true,
//								symbol:'none',
//					            itemStyle:{
//					            	normal:{
//					            		color:"#FF8C00"
//					           	 	}
//					            }
//					      }]
//					};
//		self.barGraph.setOption(self.barOption);
//	}
//	
//	/**
//	 * 获取并更新条形图数据
//	 */
//	xhuiMonitor.prototype.refreshBarChart = function(index) {
//		var self = this;
//		xhui.addClassName(self.container2, "xhui-hide");
//		xhui.removeClassName(self.wContainer2, "xhui-hide");
//		xhui.post({
//			url: self._actionUrl + "bargraph.do",
//			data: {
//				"type" : index+1,
//				"isforbargraph":true
//			},
//			callback: function(queryobj){
//				var obj = queryobj.getResponseJSON();
//				var names = obj["names"];
//				var times = obj["times"];
//				var counts = obj["counts"];
//				var maxTimes = Math.ceil(obj["maxTimes"]/5)*5;
//				var minTimes = Math.floor(obj["minTimes"]/5)*5;
//				var maxCounts = Math.ceil(obj["maxCounts"]/5)*5;
//				var minCounts = Math.floor(obj["minCounts"]/5)*5;
//				self.barOption.xAxis[0]["data"] = names;
//				self.barOption.series[0]["data"] = counts;
//				self.barOption.series[1]["data"] = times;
//				self.barOption.yAxis[0]["max"] = maxCounts;
//				self.barOption.yAxis[0]["min"] = minCounts;
//				self.barOption.yAxis[0]["interval"] = Math.ceil((maxCounts-minCounts)/5);
//				self.barOption.yAxis[1]["max"] = maxTimes;
//				self.barOption.yAxis[1]["min"] = minTimes;
//				self.barOption.yAxis[1]["interval"] = Math.ceil((maxTimes-minTimes)/5);	
//				self.barGraph.setOption(self.barOption);
//				xhui.addClassName(self.wContainer2, "xhui-hide");
//				xhui.removeClassName(self.container2, "xhui-hide");
//			}
//		});
//	}
	
	return {
		HomePage : HomePage
	}
});