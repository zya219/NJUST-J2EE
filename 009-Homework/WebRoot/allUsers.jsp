<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="pojo.UserReservation" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>商品预订系统之用户订单一览表</title>
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
            width: 50%;
            margin: auto;
            padding: 20px;
        }
        h2 {
            text-align: center;
            color: black;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
        }
        th, td {
            padding: 12px;
            text-align: center;
            border: 2px solid black;
        }
        a {
            color: black;
            text-decoration: none;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="container">
        <form name="UsersForm" action="allUsers" method="post">
            <h2>商品预订系统之用户订单一览表</h2>
            <table>
                <tr>
                    <th>用户ID</th>
                    <th>用户名</th>
                    <th>商品订单</th>
                </tr>
                <% 
                    List<UserReservation> userReservations = (List<UserReservation>) session.getAttribute("userReservations");
                	System.out.println("Hello, World!!!!");
                	System.out.println(session);
                    if (userReservations != null && !userReservations.isEmpty()) {
                    System.out.println("FUCK World!!!!");
                        for (UserReservation userReservation : userReservations) {
                %>
                <tr>
                    <td><%= userReservation.getUser().getuId() %></td>
                    <td><%= userReservation.getUser().getuName() %></td>
                    <td>
                        <%
                            List<Integer> orderIds = userReservation.getReservations();
                            for (int i = 0; i < orderIds.size(); i++) {
                        %>
                        <a href="servlet/OrderController?method=showOrderDetails&oId=<%= orderIds.get(i) %>">订单ID-<%= orderIds.get(i) %></a>                      
                        <% 
                            if (i < orderIds.size() - 1) {
                                out.print("、");
                            }
                        %>
                        <% } %>
                    </td>
                </tr>
                <% 
                        }
                    } else {
                %>
                <tr>
                    <td colspan="3">暂无数据</td>
                </tr>
                <% } %>
            </table>
        </form>
    </div>
</body>
</html>
