<html>
<head>
<meta charset="utf-8">
<title>${title}</title>
</head>
<link rel="stylesheet" type="text/css" href="/css/coolblue/xhui.css" />
<body>
<div class="xhui-layout-container">
    <div class="eui-layout-container" id="container"></div>
</div>
</body>
<script src="webjars/jquery/3.1.1/jquery.min.js"></script>  
<script src="webjars/bootstrap/3.3.5/js/bootstrap.min.js"></script> 
<link rel="stylesheet" href="webjars/bootstrap/3.3.5/css/bootstrap.min.css" />

<script src="third/require.js"></script>
<script>
	require(['../js/bootstrap_demo'], function (bsdemo){
		var demo = new bsdemo.BootStrapDemo({
			wnd:window,
			container:document.getElementById("container")
		});
	});
</script>

</html>