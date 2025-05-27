package controller;

import java.io.IOException;
import service.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pojo.*;
import java.util.ArrayList;
import java.util.List;

public class UserController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public UserController() {
        super();
    }

    public void destroy() {
        super.destroy();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        List<UserReservation> userReservations = new ArrayList<>();
        UserService userService = new UserService();
        OrderService orderService = new OrderService();
        List<User> users = userService.getAllusers();

        if (users != null) {
            for (User user : users) {
                if (user == null) {
                    continue;
                }
                List<Integer> oIds = orderService.getOrdersByUserId(user);
                if (oIds == null) {
                    continue;
                }
                UserReservation userReservation = new UserReservation(user, oIds);
                userReservations.add(userReservation);
            }
        } else {
            System.out.println("获取用户列表失败");
        }
        session.setAttribute("userReservations", userReservations);
        request.getRequestDispatcher("./allUsers.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    public void init() throws ServletException {
    }
}
