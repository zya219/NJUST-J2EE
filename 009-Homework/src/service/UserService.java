package service;

import java.util.List;
import pojo.*;
import dao.UserDAO;

public class UserService {
	static UserDAO userDAO = new UserDAO();
	public List<User>getAllusers(){
		return userDAO.findAllUsers();
	}
}

