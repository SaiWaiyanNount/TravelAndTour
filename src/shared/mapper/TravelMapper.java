package shared.mapper;

import java.sql.ResultSet;
import java.util.Locale.Category;

import models.Customer;
import models.staff;
import models.guide;
import models.category;


public class TravelMapper {
	

	public Customer mapToCustomer(Customer customer, ResultSet rs) {
		try {
			customer.setCustomer_id(rs.getInt("customer_id"));
			customer.setCustomer_name(rs.getString("customer_name"));
			customer.setCustomer_phone(rs.getString("customer_phone"));
			customer.setCustomer_email(rs.getString("customer_email"));
			//customer.setCus_address(rs.getString("cus_address"));
			
			customer.setCustomer_address(rs.getString("customer_address"));
			customer.setGender(rs.getString("gender"));
			
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customer;
	}
	

	public staff mapToStaff(staff staff, ResultSet rs) {
        try {
            staff.setStaff_id(rs.getInt("staff_id"));
            staff.setName(rs.getString("staff_name"));
            staff.setEmail(rs.getString("staff_email"));
            staff.setAddress(rs.getString("staff_address"));
            staff.setPhone(rs.getString("staff_phone"));
           
            staff.setUserName(rs.getString("username"));
            staff.setPassword(rs.getString("password"));
            staff.setRole(rs.getString("role"));
            staff.setActive(rs.getInt("active"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return staff;
    }
	
	
	public guide mapToGuide(guide guide, ResultSet rs) {
		try {
			guide.setGuide_id(rs.getInt("guide_id"));
			guide.setGuideName(rs.getString("guide_name"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guide;
	}
	public category mapToCategory(category category, ResultSet rs) {
		try {
			category.setCategory_id(rs.getInt("category_id"));
			category.setCategory_name(rs.getString("category_name"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return category;
	}
	
	
}
