package service;

import dao.FoodDAO;
import dao.OrderDAO;
import pojo.Login;
import pojo.Order;

import java.util.List;

public class OrderService {
    static OrderDAO orderDAO = new OrderDAO();
    static FoodDAO foodDAO = new FoodDAO();

    public List<Order> getOrders(Login loginForm) {
        List<Order> orders = orderDAO.getOrders(loginForm);
        orders = foodDAO.getFoodNames(orders);
        return orders;
    }

    public List<Order> checkOrders(List<Order> orders) {
        orders = orderDAO.checkOrders(orders);
        orders = foodDAO.getFoodNames(orders);
        return orders;
    }

    public int delCheck(List<Order> orders) {
        return orderDAO.delCheck(orders);
    }

    public int cancelCheck(List<Order> orders) {
        return orderDAO.cancelCheck(orders);
    }

    public void delOrders(List<Order> orders) {
        orderDAO.delOrders(orders);
    }

    public void cancelOrders(List<Order> orders) {
        orderDAO.cancelOrders(orders);
    }
}
