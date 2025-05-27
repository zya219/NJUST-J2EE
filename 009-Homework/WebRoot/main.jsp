<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
    Object statusObject = session.getAttribute("status");
    if(statusObject == null) {
        out.println("<script>" +
                "alert('账号未登录，请登录后再试');" +
                "window.location.href='./login.jsp';" +
                "</script>");
    }
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>登录成功</title>
    <style>
	  body {
	    display: flex;
	    justify-content: center;
	    align-items: center;
	    height: 100vh;
	  }
	.content {
	    text-align: left;
	    line-height: 1.5;
        font-size: 15px;
	  }
	</style>
    <script>
        function CountDown(sec, url) {
            const jumpTo = document.getElementById("jumpTo");
            jumpTo.innerHTML = sec;
            sec--;
            if(sec < 0) {
                location.href=url;
            } else {
                setTimeout("CountDown(" + sec + ", '" + url + "')", 1000);
            }
        }
    </script>
</head>
<body>
	<div class="content">
    <h2>
	登录成功
<b>将在 <span id="jumpTo">5</span> 秒后进入主页……</b>
<script>
    CountDown(5, "servlet/OrderController");
</script>
  </h2>
    </div>
</body>
</html>
