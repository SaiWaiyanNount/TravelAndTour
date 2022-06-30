package shared.mapper;

import models.staff;

public class CurrentUserHolder {

private static staff staff;
	
	private CurrentUserHolder() {}
	
	public static staff getCurrentUser() {
		return staff;
	}
	
	public static void setLoggedInUser(staff staff) {
		CurrentUserHolder.staff = staff;
	}
	
	
}
