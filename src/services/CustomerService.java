package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import config.DBConfig;
import models.Customer;
import shared.mapper.TravelMapper;

public class CustomerService {

	
	private final TravelMapper cusMapper;
	private final DBConfig dbConfig;

	

	public CustomerService() {
		this.cusMapper = new TravelMapper();
		this.dbConfig = new DBConfig();
		
		
	}

	public void createCustomer(Customer cus) {
		
		
		try {

			PreparedStatement ps = this.dbConfig.getConnection().prepareStatement(
					"INSERT INTO customer (customer_name,customer_email,customer_phone,customer_address,gender) VALUES (?,?,?,?,?)");

			ps.setString(1, cus.getCustomer_name());
			ps.setString(2, cus.getCustomer_email());
			ps.setString(3, cus.getCustomer_phone());
			ps.setString(4, cus.getCustomer_address());
			ps.setString(5, cus.getGender());

			ps.executeUpdate();
			ps.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
public Customer findCustomerId(String id) {
		
		Customer cus = new Customer();

		try (Statement st = this.dbConfig.getConnection().createStatement()) {

			String query = "Select * from customer where customer_id = " + id + ""; //
			System.out.println(query);

			ResultSet rs = st.executeQuery(query);

			if (rs.next()) {
				this.cusMapper.mapToCustomer(cus, rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cus;
		
		
	
	}

	public List<Customer> findAllCustomers()  {
		
		List<Customer> cusList = new ArrayList<>();

		try (Statement st = this.dbConfig.getConnection().createStatement()) {
			String query = "select * from customer order by customer_id";

			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				Customer cus = new Customer();
				cusList.add(this.cusMapper.mapToCustomer(cus, rs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cusList;
		
	}

	public void deleteCustomer(String id) {
		// TODO Auto-generated method stub
		
		
		
		try {

			PreparedStatement ps = this.dbConfig.getConnection()
					.prepareStatement("delete from customer where customer_id = '" + id + "'");

			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Delete Successful");
			ps.close();
		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Try Again");
		}
		
	}
	
public void updateCustomer(String id,Customer cus) {
		
		try {
			PreparedStatement ps = this.dbConfig.getConnection().prepareStatement(
					"Update customer set customer_name=?,customer_email=?,customer_phone=?,customer_address=?,gender=? where customer_id =?");

			ps.setString(1, cus.getCustomer_name());
			ps.setString(2, cus.getCustomer_email());
			ps.setString(3, cus.getCustomer_phone());
			ps.setString(4, cus.getCustomer_address());
			ps.setString(5, cus.getGender());
			ps.setString(6, id);

			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Update Successfully!!!");
			ps.close();
		} catch (Exception e) {
			// if(e instanceof MySQLIntegrityConstraintViolationException)
			JOptionPane.showMessageDialog(null, "Already Exits");
		}
		
		
	}

	
}
