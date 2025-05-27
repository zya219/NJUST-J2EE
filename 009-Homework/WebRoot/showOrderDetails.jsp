<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="pojo.Reservation" %>
<%@ page import="pojo.Product" %>
<%@ page import="java.util.List" %>
<% Reservation reservation = (Reservation) session.getAttribute("reservation"); %>
<html>
<head>
    <title>订单详情</title>
    <script>
        function selectAction(action) {
            let actionInput = document.orders.action;
            actionInput.value = action;
            document.orders.submit();
        }
        function valid() {
            let status = <%= reservation != null ? reservation.getoStatus() : -1 %>;
            if (status !== 0) {
                alert("无法更改");
                return false;
            }
            return true;
        }
    </script>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            width: 40%;
            padding: 20px;
            text-align: center;
        }
        h1 {
            text-align: center;
            color: #333;
        }
        table {
            width: 80%;
            border-collapse: collapse;
            margin: 30px 0 30px 45px;
        }
        th, td {
            padding: 10px;
            text-align: center;
            border: 2px solid black;
        }
        ul {
            list-style: none;
            padding: 0;
        }
        a {
            color: #007bff;
            text-decoration: none;
        }
        a:hover {
            text-decoration: underline;
        }
        button, input[type="submit"] {
            padding: 5px 10px;
            border: 1.5px solid black;
            border-radius: 5px;
            background-color: #d3d3d3;
            color: black;
            font-size: 16px;
            cursor: pointer;
        }
        button:hover, input[type="submit"]:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<form class="container" name="orders" onsubmit="return valid()" method="post" action="OrderController" >
    <input type="hidden" name="action" value="">
    <h1>订单详情</h1>
    <%
        String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null && !errorMessage.isEmpty()) {
    %>
    <p class="error"><%= errorMessage %></p>
    <%
        }
    %>
    <%
        if (reservation != null) {
    %>
    <input type="hidden" name="oId" value="<%= reservation.getoId() %>">
    <table>
        <tr>
            <th>用户ID</th>
            <td><%= reservation.getuId() %></td>
        </tr>
        <tr>
            <th>订单ID</th>
            <td><%= reservation.getoId() %></td>
        </tr>
        <tr>
            <th>订单时间</th>
            <td><%= reservation.getTimeString() %></td>
        </tr>
        <tr>
            <th>预定商品列表</th>
            <td>
                <ul>
                    <%
                        List<Product> productList = reservation.getProductList();
                        if (productList != null) {
                            for (Product product : productList) {
                    %>
                    <li> <%= product.getpName() %> （<%= product.getoCount() %>）</li>
                    <%
                            }
                        } else {
                    %>
                    <li>未找到商品信息</li>
                    <%
                        }
                    %>
                </ul>
            </td>
        </tr>
        <tr>
            <th>订单总价</th>
            <td><%= reservation.getoTotal() %></td>
        </tr>
        <tr>
            <th>订单状态</th>
            <td>
                <%
                    int status = reservation.getoStatus();
                    String statusStr = "";
                    switch (status) {
                        case 0:
                            statusStr = "进行中";
                            break;
                        case 1:
                            statusStr = "已完成";
                            break;
                        case 2:
                            statusStr = "已撤销";
                            break;
                        default:
                            statusStr = "未知状态";
                    }
                %>
                <%= statusStr %>
            </td>
        </tr>
    </table>
    <%
        } else {
    %>
    <p>没有找到订单详情。</p>
    <%
        }
    %>
    <input type="submit" name="ChangeOrder" value="更改订单">
</form>
</body>
</html>
