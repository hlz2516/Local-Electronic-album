package beans;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class UserBean {
	@JSONField(ordinal = 0)
	private String name;
	@JSONField(ordinal = 1)
	private String password;
	@JSONField(ordinal = 2)
	private Date birth;
	@JSONField(ordinal = 3)
	private boolean gender; //true:男 false:女
	@JSONField(ordinal = 4)
	private String email;
	@JSONField(ordinal = 5)
	private String phone;

	public UserBean() { }
	//创建用户
	public UserBean(String name,String password,Date birth,boolean gender,
			String email,String phone) {
		this.name = name;
		this.password = password;
		this.birth = birth;
		this.gender = gender;
		this.email = email;
		this.phone = phone;
	}
	//以下所有的set方法均为编辑用户信息服务,所有get方法均为读取用户信息服务
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
