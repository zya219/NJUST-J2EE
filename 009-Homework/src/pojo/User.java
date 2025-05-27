package pojo;
public class User {
    private int uId;
    private String uName;
    private String uPw;

    public User(int uId, String uName, String uPw) {
        this.uId = uId;
        this.uName = uName;
        this.uPw = uPw;
    }
    public User() {
    }
    public int getuId() {
        return uId;
    }

    public String getuName() {
        return uName;
    }

    public String getuPw() {
        return uPw;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public void setuPw(String uPw) {
        this.uPw = uPw;
    }
}
