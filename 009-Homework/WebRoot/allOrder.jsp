<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="pojo.Food" %>
<%@ page import="pojo.Order" %>
<%@ page import="pojo.OrderPage" %>
<%@ page import="pojo.Login" %>
<%@ page import="java.util.List" %>
<%
    Object statusObject = session.getAttribute("status");
    if(statusObject == null) {
        out.println("<script>" +
                "alert('账号未登录，请登录后再试');" +
                "window.location.href='./login.jsp';" +
                "</script>");
    } else {
        int status = (Integer) statusObject;
        if(status == 2) {
            out.println("<script>" +
                    "alert('删除成功');" +
                    "</script>");
        } else if(status == 3) {
            out.println("<script>" +
                    "alert('撤销成功');" +
                    "</script>");
        }
        session.setAttribute("status", 1);
    }
    OrderPage orderPage = (OrderPage) session.getAttribute("orderPage");
    List<Order> curList = null;
    Login loginForm;
    String loginUsername = null;
    if(orderPage != null) {
        curList = orderPage.getOrderList();
        loginForm = (Login) session.getAttribute("loginForm");
        loginUsername = loginForm.getUsername();
    }
%>
<html>
<head>
    <title>全部订单</title>
    <script>
        function selectAction(action) {
            let actionInput = document.orders.action;
            actionInput.value = action;
            document.orders.submit();
        }
    </script>
</head>

<style>
        table {
            width: 600px;
            border-collapse: collapse;
            margin-left:400px;
        }
        th, td {
            border: 1px solid black;
            padding: 8px;
            text-align: center;
        }
        .center {
        	margin-top:20px;
        	line-height: 30px; 
            font-size: 20px;
            text-align: center;
        }
        .button-container {
            text-align: center;
            margin-top: 20px;
        }
        button {
            padding: 10px;
            margin: 10px;
            text-align: center;
            display: inline-block;
            color: black;
        }
    </style>

<body>
<div class="center">
   </p>用户：<%=loginUsername%></p>
</div>
<form name="orders" method="post" action="servlet/OrderController">
    <input type="hidden" name="action" value="choose">
    <table>
        <tr>
            <td class="headStyle"></td>
            <td class="headStyle">序号</td>
            <td class="headStyle">订单ID</td>
            <td class="headStyle">订单时间</td>
            <td class="headStyle">订购菜肴</td>
            <td class="headStyle">订单总价</td>
        </tr>
        <%
            Order order;
            if(!curList.isEmpty()){
                int size = curList.size();
                int index =0;
                while (index < size){
                    order = curList.get(index);
                    if(order != null){
                        %>
                        <tr>
                            <td><label><input name="row" type="checkbox" value=<%=order.getoId()%> /></label></td>
                            <td><%=index + 1%></td>
                            <td><%=order.getoId()%></td>
                            <td><%=order.getTimeString()%></td>
                            <td>
                                <%
                                    List<Food> foodList = order.getFoodList();
                                    int foodSize = foodList.size();
                                    int foodIndex = 0;
                                    Boolean ok = false;
                                    Food food;
                                    while(foodIndex < foodSize) {
                                        food = foodList.get(foodIndex);
                                        if(!ok) {
                                            ok = true;
                                        } else {
                                %>
                                            <br>
                                <%
                                        }
                                %>
                                        <%=food.getfName()%>
                                <%
                                        foodIndex++;
                                    }
                                %>
                            </td>
                            <td><%=order.getTotalPrice()%></td>
                        </tr>
                        <%
                                    }
                                    index++;
                                }
                            }
                        %>
    </table>
    <br>
    <br>
<div class="button-container">
    <a href="allFood.jsp"><button type="button">浏览菜肴</button></a>
    <button type="submit" onclick="selectAction('deleteCheck')">删除订单</button>
    <button type="submit" onclick="selectAction('cancelCheck')">撤销订单</button>
   </div>
</form>
</body>
</html>
