package login;

public class UserVO {
	
	
	
	public UserVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public UserVO(String userid, String password) {
		super();
		this.userid = userid;
		this.password = password;
	}


	@Override
	public String toString() {
		return "UserVO [userid=" + userid + ", password=" + password + "]";
	}
	private String userid;
	private String password;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
