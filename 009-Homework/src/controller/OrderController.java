package controller;

import pojo.Login;
import pojo.Order;
import pojo.OrderPage;
import service.OrderService;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class OrderController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        OrderService orderService = new OrderService();
        PrintWriter out = response.getWriter();
        Login loginForm = (Login) session.getAttribute("loginForm");

        String action = request.getParameter("action");
        String jumpUrl = "../allOrder.jsp";

        OrderPage orderPage = (OrderPage) session.getAttribute("orderPage");
        OrderPage delOrderPage = (OrderPage) session.getAttribute("delOrderPage");
        OrderPage cancelOrderPage = (OrderPage) session.getAttribute("cancelOrderPage");
        if(orderPage == null) {
            orderPage = new OrderPage();
        }
        if(delOrderPage == null) {
            delOrderPage = new OrderPage();
        }
        if(cancelOrderPage == null) {
            cancelOrderPage = new OrderPage();
        }
        if(action == null) {
            Object statusObject = session.getAttribute("status");
            if(statusObject == null) {
                out.println("<script>" +
                        "alert('账号未登录，请登录后再试');" +
                        "window.location.href='./login.jsp';" +
                        "</script>");
            } else {
                List<Order> orderList = orderService.getOrders(loginForm);
                orderPage.setOrderList(orderList);
                session.setAttribute("orderPage", orderPage);
                response.sendRedirect("../allOrder.jsp");
            }
            return;
        }

        int errorCode = 0;
        switch (action) {
            case "deleteCheck": {
                String[] rows = request.getParameterValues("row");
                if(rows == null) {
                    errorCode = 4;
                } else {
                    List<Order> delOrders = new ArrayList<>();
                    Order delOrder;
                    for(String tmp:rows){
                        delOrder = new Order();
                        delOrder.setoId(Integer.parseInt(tmp));
                        delOrders.add(delOrder);
                    }
                    delOrders = orderService.checkOrders(delOrders);
                    delOrderPage.setOrderList(delOrders);
                    session.setAttribute("delOrderPage", delOrderPage);
                }
                jumpUrl = "../delOrder.jsp";
                break;
            }
            case "cancelCheck": {
                String[] rows = request.getParameterValues("row");
                if(rows == null) {
                    errorCode = 4;
                } else {
                    List<Order> cancelOrders = new ArrayList<>();
                    Order cancelOrder;
                    for(String tmp:rows){
                        cancelOrder = new Order();
                        cancelOrder.setoId(Integer.parseInt(tmp));
                        cancelOrders.add(cancelOrder);
                    }
                    cancelOrders = orderService.checkOrders(cancelOrders);
                    cancelOrderPage.setOrderList(cancelOrders);
                    session.setAttribute("cancelOrderPage", cancelOrderPage);
                }
                jumpUrl = "../cancelOrder.jsp";
                break;
            }
            case "delete": {
                String[] rows = request.getParameterValues("row");
                List<Order> delOrders = new ArrayList<>();
                if(rows == null) {
                    errorCode = 4;
                } else {
                    Order delOrder;
                    for(String tmp:rows){
                        delOrder = new Order();
                        delOrder.setoId(Integer.parseInt(tmp));
                        delOrders.add(delOrder);
                    }
                }
                if(errorCode == 0) {
                    errorCode = orderService.delCheck(delOrders);
                }
                if(errorCode == 0) {
                    orderService.delOrders(delOrders);
                    session.setAttribute("status", 2);
                    jumpUrl = "../allOrder.jsp";
                } else {
                    session.setAttribute("status", 1);
                    session.setAttribute("errorCode", errorCode);
                    jumpUrl = "../OrderFailure.jsp";
                }
                break;
            }
            case "cancel": {
                String[] rows = request.getParameterValues("row");
                List<Order> cancelOrders = new ArrayList<>();
                if(rows == null) {
                    errorCode = 4;
                } else {
                    Order cancelOrder;
                    for(String tmp:rows){
                        cancelOrder = new Order();
                        cancelOrder.setoId(Integer.parseInt(tmp));
                        cancelOrders.add(cancelOrder);
                    }
                }
                if(errorCode == 0) {
                    errorCode = orderService.cancelCheck(cancelOrders);
                }
                if(errorCode == 0) {
                    orderService.cancelOrders(cancelOrders);
                    session.setAttribute("status", 3);
                    jumpUrl = "../allOrder.jsp";
                } else {
                    session.setAttribute("status", 1);
                    session.setAttribute("errorCode", errorCode);
                    jumpUrl = "../OrderFailure.jsp";
                }
                break;
            }
        }
        if(errorCode == 0) {
            List<Order> orderList = orderService.getOrders(loginForm);
            orderPage.setOrderList(orderList);
            session.setAttribute("orderPage", orderPage);
            response.sendRedirect(jumpUrl);
        } else {
            request.setAttribute("errorCode", errorCode);
            request.getRequestDispatcher("../OrderFailure.jsp").forward(request, response);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
