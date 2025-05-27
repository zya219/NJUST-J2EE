<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
     <style>
        form{
        	margin-top: 180px;
            height: 600px;
            line-height: 30px; 
            font-size: 16px;
            width: auto;
            margin-left:520px;
        }
        .bt {
            display: block;
            height: 30px;
            width: 60px;
            text-align: center;
            line-height: 20px; 
            margin: 10px 150px auto; 
            font-size: 15px;
        }
        form #captchaDisplay {
        	display: inline-block;
            width: 100px;
            margin-left: 30px;
            color: #ff0000;
        }
    
    </style>
    
    <title>Login</title>
    
        <script>
        var arr = new Array(3);
        arr[0] = [" ", "---请选择---"];
        arr[1] = ["计算机科学与技术", "软件工程", "智能科学与技术"];
        arr[2] = ["光电子技术科学系", "电子工程系", "通信工程系","电子信息科学与技术系","光电子技术与系统系","电磁场与无线技术系"];
        arr[3] = ["机械工程系", "自动化系", "材料科学与工程系", "能源与动力工程系"];

        function validateForm() {
            var password = document.getElementById("password").value;
            var hasNumber = /\d/.test(password); // 检查密码是否包含数字
            var hasLetter = /[a-zA-Z]/.test(password); // 检查密码是否包含字母

            if (!hasNumber || !hasLetter) {
                alert("密码必须同时包含数字和字母");
                return false;
            }

            // 验证码验证逻辑
            var inputCaptcha = document.getElementById("captchaInput").value;
            var generatedCaptcha = document.getElementById("captchaDisplay").textContent.toLowerCase();
            if (inputCaptcha.toLowerCase() !== generatedCaptcha) {
                alert("验证码错误，请重新输入！");
                generateCaptcha(); // 重新生成验证码
                return false;
            }

            return true;
        }

        // 生成随机验证码
        function generateCaptcha() {
            var length = 4; // 验证码长度
            var charset = "0123456789abcdefghijklmnopqrstuvwxyz"; // 验证码字符集
            var captcha = "";
            for (var i = 0; i < length; i++) {
                captcha += charset.charAt(Math.floor(Math.random() * charset.length));
            }
            document.getElementById("captchaDisplay").textContent = captcha; // 显示验证码
        }

        // 页面加载时生成初始验证码
        window.onload = generateCaptcha;
		
		//下拉框联动
        function updateDepartments() {
            var college = document.getElementById("college").value;
            var departmentSelect = document.getElementById("department");
            departmentSelect.innerHTML = "---请选择---"; // 清空原有选项
            for (var i = 0; i < arr[college].length; i++) {
                var option = document.createElement("option");
                var text = document.createTextNode(arr[college][i]);
                option.appendChild(text);
                departmentSelect.appendChild(option);
            }
        }

        // 辅助函数，用于向下拉框添加选项
        function addOption(select, value) {
            var option = document.createElement("option");
            option.text = value;
            option.value = value;
            select.add(option);
        }

    </script>
    
</head>
<body>
    <form name="loginForm" onsubmit="return validateForm()"  action="servlet/LoginController"  method="post">
    	<table>
    		<tr><td>用户名：</td>
    		<td><input type="text" id="username" name="username" required></td></tr>
    		<tr><td>密码：</td>
    		<td><input type="password" id="password" name="password" required></td></tr>
    		<tr><td>所在学院：</td>
    			<td>
    				<select name="college" id="college" onchange="updateDepartments()">
    					<option value="0">---请选择---</option>
            			<option value="1">计算机科学与工程学院</option>
			            <option value="2">电子与光学工程学院</option>
			            <option value="3">机械院</option>
    				</select>
    			</td>
    		</tr>
    		<tr><td>所在系：</td>
    			<td>
    				<select name="department" id="department">
    				 	<option value="">---请选择---</option>
    				</select>
    			</td>
    		</tr>
    		<tr><td>验证码：</td>
    		<td>
    			<input type="text" id="captchaInput" name="captcha" >
    		</td>
    		<td>
    			<span id="captchaDisplay"></span><br>
    		</td>
    		</tr>
    	</table>
        <input class="bt" type="submit" value="登录">
    </form> 
</body>
</html>
