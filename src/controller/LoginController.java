package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.LoginService;
import pojo.Login;
import javax.servlet.http.HttpSession;

public class LoginController extends HttpServlet {


	private static final long serialVersionUID = 1L;
	public LoginController() {
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
		
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String college=request.getParameter("college");
		String department=request.getParameter("department");
		
		String collegeName = "";
		if (college.equals("1")) {
		    collegeName = "计算机科学与工程学院";
		} else if (college.equals("2")) {
		    collegeName = "电子与光学工程学院";
		} else if (college.equals("3")) {
		    collegeName = "机械院";
		}

		Login loginForm=new Login(username,password,collegeName,department);
		LoginService service=new LoginService();
		int errorcode=service.checkUser(loginForm);
		loginForm.setErrorcode(errorcode);
		if(errorcode== 0){
			service.getForm(loginForm);                            //设置学号id
			session.setAttribute("loginForm", loginForm);
			session.setAttribute("status", 1);
			request.getRequestDispatcher("../main.jsp").forward(request, response);          //前往登陆成功页面
		}
		else{
			request.setAttribute("loginForm", loginForm);
		    request.getRequestDispatcher("../loginFailure.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}
	public void init() throws ServletException {
	}

}
