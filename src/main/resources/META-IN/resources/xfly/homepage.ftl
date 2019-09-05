<html>
<head>
<meta charset="utf-8">
<title>首页 </title>
</head>
<link rel="stylesheet" type="text/css" href="/css/xhui.css" />
<body>
<div class="xhui-layout-container">
    <div class="eui-layout-container" id="container"></div>
</div>
</body>
<script src="/third/require.js"></script>
<script>
	require(['../js/homepage'], function (home){
		var demo = new home.HomePage({
			wnd:window,
			container:document.getElementById("container")
		});
	});
</script>

</html>