package services;

import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import config.DBConfig;

import frame.LoginForm;
import models.staff;
import shared.mapper.TravelMapper;

public class LoginService {

	private final DBConfig dbConfig;

	private LoginForm loginform;
	private TravelMapper travelmapper;

	public LoginService() throws SQLException {

		this.dbConfig = new DBConfig();
		this.loginform=new LoginForm();
		// TODO Auto-generated constructor stub

	}

	public void Checkpassword(String username, String password) {

		try {
			Statement st = this.dbConfig.getConnection().createStatement();
			String sql = "select * from staff where username = '" + username + "' AND password = '" + password + "'";
			ResultSet rs = st.executeQuery(sql);

			if (rs.next()) {
				loginform.frmLogin.hide();
			} else {
				JOptionPane.showMessageDialog(null, "Invalid Login");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}

	public void updateEmployee(String id, staff staff) {
		// TODO Auto-generated method stub
		
		 try {
	            PreparedStatement ps = this.dbConfig.getConnection()
	                    .prepareStatement("UPDATE employee SET staff_name=?, staff_address=?,staff_phone=?,  username=?, password=?, role=? WHERE staff_id=?");

	            ps.setString(1, staff.getName());
	         
	            ps.setString(2, staff.getAddress());
	            ps.setString(3, staff.getPhone());
	            ps.setString(4, staff.getUsername());
	            ps.setString(5, staff.getPassword());
	            ps.setString(6,staff.getRole());
	            ps.setString(7, id);

	            ps.executeUpdate();
	            ps.close();
	        } catch (Exception e) {
	            
	                JOptionPane.showMessageDialog(null, "Already Exists");
	            
	        }
		
	}

	public staff findEmployeeById(String id) {
		// TODO Auto-generated method stub
		 staff staff = new staff();

	        try (Statement st = this.dbConfig.getConnection().createStatement()) {

	            String query = "SELECT * FROM employee WHERE emp_id = " + id + ";";

	            ResultSet rs = st.executeQuery(query);

	            while (rs.next()) {
	                this.travelmapper.mapToStaff(staff, rs);
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return staff;
	    }
		
		
		
		
		
	
	

}
