<html>
<head>
<meta charset="utf-8">
<title>首页 </title>
</head>
<link rel="stylesheet" type="text/css" href="/css/xhui.css" />
<body>
<div class="xhui-layout-container">
    <div id="container" 
    class="xhui-layout-row-3 xhui-layout-row-first xhui-layout-row-offsetbottom-1 xhui-padding-top-10 xhui-padding-left-20 xhui-padding-right-20 xhui-padding-bottom-5 xhui-scroll-auto">
    </div>
    <div id="buttonDom" class="xhui-layout-row-1 xhui-layout-row-last xhui-padding-left-20 xhui-padding-right-20 xhui-padding-top-10 xhui-padding-bottom-10"></div>
</div>
</body>
<script src="/js/third/require.js"></script>
<script>
	require(['../js/homepage'], function (home){
		var demo = new home.HomePage({
		wnd:window,
		container:document.getElementById("container"),
		buttonDom:document.getElementById("buttonDom")});
	});
</script>

</html>