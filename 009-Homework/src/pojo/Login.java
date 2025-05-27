package pojo;

public class Login {
	private String username;
	private String password;
	private String college;
	private String department;
	private int uid;
	private int errorcode;
	
	public Login(){}
	public Login(String username,String password,String college,String department){
		this.username=username;
		this.password=password;
		this.college=college;
		this.department=department;
	}
	public void setUsername(String username){
		this.username=username;
	}
	public void setPassword(String password){
		this.password=password;
	}
	public void setCollege(String college){
		this.college=college;
	}
	public void setDepartment(String department){
		this.department=department;
	}
	public void setuId(int uid){
		this.uid=uid;
	}
	public void setErrorcode(int errorcode){
		this.errorcode=errorcode;
	}
	public String getUsername(){
		return this.username;
	}
	public String getPassword(){
		return this.password;
	}
	public String getCollege(){
		return this.college;
	}
	public String getDepartment(){
		return this.department;
	}
	public int getuId(){
		return this.uid;
	}
	public int getErrorcode(){
		return this.errorcode;
	}
	
}
