package services;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import config.DBConfig;
import models.staff;
import shared.mapper.*;
///import share.mapper.saleMapper;
public class staffService {
	private DBConfig DBConfig;
	private TravelMapper travelMapper;
	public staffService() {
		
		this.DBConfig = new DBConfig();
		this.travelMapper = new TravelMapper();
	}
	public void createstaff (staff staff) {
		try {
			PreparedStatement ps = this.DBConfig.getConnection().prepareStatement("INSERT INTO staff(staff_name,staff_phone,staff_email,staff_address,username,password,role,active)VALUES(?,?,?,?,?,?,?,?)");
			ps.setString(1,staff.getName());
			ps.setString(2, staff.getPhone());
			ps.setString(3,staff.getEmail());
			ps.setString(4,staff.getAddress());
			ps.setString(5,staff.getUserName());
			ps.setString(6,staff.getPasssword());
			ps.setString(7, staff.getRole());
			ps.setInt(8,(int)staff.getActive());
			ps.executeUpdate();
			ps.close();
			
		}catch(Exception e) {
			
				e.printStackTrace();
		}
	}
	public staff findStaffById(String id) {
		// TODO Auto-generated method stub
		staff staff = new staff();

		try (Statement st = this.DBConfig.getConnection().createStatement()) {

			String query = "SELECT * FROM staff WHERE staff_id = " + id + ";";

			ResultSet rs = st.executeQuery(query);

			if (rs.next()) {
				this.travelMapper.mapToStaff(staff, rs);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return staff;
	}
	public void updateStaff(String id,staff staff) {
		try {
            PreparedStatement ps = this.DBConfig.getConnection()
                    .prepareStatement("UPDATE staff SET staff_name=?, staff_phone=?,staff_email=?,staff_address=?,username=?,password=?,role=?,active=?  WHERE staff_id=?");

            ps.setString(1,staff.getName());
			
			ps.setString(3,staff.getEmail());
			ps.setString(4,staff.getAddress());
			ps.setString(2, staff.getPhone());
			ps.setString(5,staff.getUserName());
			ps.setString(6,staff.getPasssword());
			ps.setString(7, staff.getRole());
			ps.setInt(8,staff.getActive());
            ps.setString(9, id);

            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {

        	e.printStackTrace();
        }
	}
	
	public void deleteStaff(String id,staff staff) {
		try {
            PreparedStatement ps = this.DBConfig.getConnection()
                    .prepareStatement("DELETE FROM staff  WHERE staff_id=?");
            ps.setString(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {

        	e.printStackTrace();
        }
	}
	public List<staff> findAllStaff() throws SQLException{
		List<staff> emplyeeList =new ArrayList<>();
		try(Statement st = this.DBConfig.getConnection().createStatement()){
			String query ="SELECT * FROM staff";
			ResultSet rs=st.executeQuery(query);
			while(rs.next())
			{
				staff sf =new staff();
			emplyeeList.add(this.travelMapper.mapToStaff(sf, rs));
			
			
			}
			}
		catch(Exception e) {
			e.printStackTrace();
		}
		return emplyeeList;
	}
	

}
