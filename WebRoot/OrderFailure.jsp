<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    int errorCode = (Integer) request.getAttribute("errorCode");
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
    <title>操作失败</title>
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
</head>

<script>
    function getFailureInfo() {
        const failureInfo = document.getElementById("failureInfo");
        switch(<%=errorCode%>) {
            case 4:
                failureInfo.innerHTML="未选中订单";
                break;
            case 5:
                failureInfo.innerHTML="无法删除，订单于24小时内创建";
                break;
            case 6:
                failureInfo.innerHTML="无法撤销，订单于10分钟前创建";
                break;
            default:
                failureInfo.innerHTML="未知错误";
        }
    }
</script>

<body>
	<div class="content">
    <h2>
   	操作失败！</p>
	失败原因：<span id="failureInfo"></span>
    <script>getFailureInfo();</script><br></p>
    <a href="./allOrder.jsp">返回全部订单</a>
  </h2>
    </div>
</body>
</html>
