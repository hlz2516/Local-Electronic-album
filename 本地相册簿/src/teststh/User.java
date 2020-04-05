package teststh;

public class User {
	private String name;
	private String birth;
	private static User user;
	public static User getInstance() {
		if(user==null)
			user = new User();
		return user;
	}
	public String getName() {
		return user.name;
	}
	public void setName(String name) {
		user.name = name;
	}
	public String getBirth() {
		return user.birth;
	}
	public void setBirth(String birth) {
		user.birth = birth;
	}
	public void setNullName() {
		user.name = null;
	}
	public void setNullBirth() {
		user.birth = null;
	}
}
