<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登录失败！</title>
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
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <jsp:useBean id="loginForm" class="pojo.Login" scope="request"></jsp:useBean>
    <%  
    int errorCode = loginForm.getErrorcode();  
	%>  
	<%  
	System.out.println("ErrorCode: " + errorCode); // 打印到控制台
	%>  
    <script>
    	function getFailureInfo(){
    		var failureInfo=document.getElementById("failureInfo");
    		
    		var errorCode = <%=errorCode%>;  
    		switch(errorCode){
    			case 1:
    				failureInfo.innerHTML="用户不存在";
    				break;
    			case 2:
    				failureInfo.innerHTML="密码不正确";
    				break;
    			case 3:
    				failureInfo.innerHTML="学院不匹配";
    				break;
    			case 4:
    				failureInfo.innerHTML="专业不匹配";
    				break;
    			default:
    				failureInfo.innerHTML="其他错误";
    		}
    	}
    </script>
	<div class="content">
    <h2>登录失败！</p>
	失败原因：<span id="failureInfo"><%= errorCode %></span>
    <script>getFailureInfo();</script><br></p>
    <a href="./login.jsp">重新登陆</a>
    </h2>
    </div>
  </body>
</html>

