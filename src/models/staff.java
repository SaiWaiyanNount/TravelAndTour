package models;

import java.util.*;
import config.DBConfig;

public class staff {
	String name, address,phone,username,role,password,email;
	
	int staff_id,active;
	
	public int getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(int staff_id) {
		this.staff_id = staff_id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	
	public String getAddress() {
		return this.address;
	}
	
	

	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	public String getPassword() {
		return password;
	}
	public String getPhone() {
		return this.phone;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
		return this.email;
	}
	
	public void setUserName(String username) {
		this.username = username;
	}
	public String getUserName() {
		return this.username;
	}
	
	public void setPassword(String pass) {
		this.password = pass;
	}
	public String getPasssword() {
		return this.password;
	}
	
	public void setRole(String  role) {
		this.role = role;
	}
	public String getRole() {
		return this.role;
	}
	

	public void setActive(int int1) {
		// TODO Auto-generated method stub
		this.active = int1;
	}
	public int getActive() {
		// TODO Auto-generated method stub
		return this.active;
	}
	

}
