package dao;

import pojo.Login;
import utils.DBUtil;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class UserDAO {

    public int checkLogin(Login loginForm) {
        String loginUsername = loginForm.getUsername();
        String loginPassword = loginForm.getPassword();
        String loginCollege = loginForm.getCollege();
        String loginMajor = loginForm.getDepartment();

        Map<String, Object> whereMap = new HashMap<>();
        whereMap.put("uName", loginUsername);

        try {
            List<Map<String, Object>> data = DBUtil.query("user", whereMap);
            for (Map<String, Object> user : data) {
                String password = (String) user.get("uPw");
                String college = (String) user.get("uSchool");
                String major = (String) user.get("uDepartment");
                
                if (!loginPassword.equals(password)) {
                    return 2; // 密码错误
                }
                
                if (!loginCollege.equals(college) ) {
                    return 3; // 学院或专业不匹配
                }
                
                if(!loginMajor.equals(major)){
                	return 4;
                }
                return 0; // 登录成功
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1; // 用户不存在
    }

    public Login getForm(Login loginForm) {
        String loginUsername = loginForm.getUsername();
        Map<String, Object> whereMap = new HashMap<>();
        whereMap.put("uName", loginUsername);

        try {
            List<Map<String, Object>> data = DBUtil.query("user", whereMap);
            for (Map<String, Object> user : data) {
                int uId = Integer.parseInt(String.valueOf(user.get("uId")));
                loginForm.setuId(uId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loginForm;
    }
}
