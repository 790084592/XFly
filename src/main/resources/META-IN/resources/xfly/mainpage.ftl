<html>
<head>
<meta charset="utf-8">
<title>首页 </title>
</head>
<link rel="stylesheet" type="text/css" href="/css/coolblue/xhui.css" />
<body>
<div class="xhui-layout-body">
	<div class="xhui-layout-container">
	    <div class="xhui-layout-header" id="header"></div>
	    <div class="xhui-layout-main" id="container"></div>
	</div>
</div>
</body>
<script src="/third/require.js"></script>
<script src="/third/jquery-1.9.1.min.js"></script>
<script>
	require(['../js/mainpage'], function (page){
		var demo = new page.MainPage({
			wnd:window,
			header: document.getElementById("header"),
			container: document.getElementById("container")
		});
	});
</script>
</html>