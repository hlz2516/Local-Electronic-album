package beans;

public class UserManager {
	private static UserBean user;
	
	public static UserBean getCurUser() {
		return user;
	}
	
	public static void setCurUser(UserBean userbean) {
		user = userbean;
	}
}
