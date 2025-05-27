package service;
import dao.UserDAO;
import pojo.Login;

public class LoginService {
	
	UserDAO loginDAO=new UserDAO();
	
	public int checkUser(Login loginForm){
		return loginDAO.checkLogin(loginForm);
	}
	
	public Login getForm(Login loginForm){
		return loginDAO.getForm(loginForm);
	}
		
}
