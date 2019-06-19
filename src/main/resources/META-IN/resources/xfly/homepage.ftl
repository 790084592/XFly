<html>
<head>
<meta charset="utf-8">
<title>首页 </title>
</head>
<body>
<div id="xfly-layout-container" style="width:100%;height:100%"></div>
</body>
<script data-main="homepage" src="/js/third/require.js"></script>
<script>
   
	require(['../js/homepage'], function (home){
		var demo = new home.HomePage(window);
	});
</script>

</html>