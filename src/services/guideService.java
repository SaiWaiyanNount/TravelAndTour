package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import config.DBConfig;
import models.guide;
import shared.mapper.TravelMapper;

public class guideService {

	private final TravelMapper guideMapper;
	private final DBConfig dbConfig;

	public guideService() {
		this.guideMapper = new TravelMapper();
		this.dbConfig = new DBConfig();
	}

	public void createGuide(guide guide) {
		try {

			PreparedStatement ps = this.dbConfig.getConnection()
					.prepareStatement("INSERT INTO guide (guide_name) VALUES (?)");

			ps.setString(1, guide.getGuideName());

			ps.executeUpdate();
			ps.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateGuide(String id, guide guide) {
		try {
			PreparedStatement ps = this.dbConfig.getConnection()
					.prepareStatement("Update guide set guide_name=? where guide_id =?");

			ps.setString(1, guide.getGuideName());
			ps.setString(2, id);

			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteGuide(String id) {
		try {

			PreparedStatement ps = this.dbConfig.getConnection()
					.prepareStatement("delete from guide where guide_id = '" + id + "'");

			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "!!!!!");
		}
	}

	public guide findGuideId(String id) {
		guide guide = new guide();

		try (Statement st = this.dbConfig.getConnection().createStatement()) {

			String query = "Select * from guide where guide_id = " + id + ""; //
			System.out.println(query);

			ResultSet rs = st.executeQuery(query);

			if (rs.next()) {
				this.guideMapper.mapToGuide(guide, rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guide;
	}

	public List<guide> findAllGuide() {

		List<guide> guideList = new ArrayList<>();

		try (Statement st = this.dbConfig.getConnection().createStatement()) {
			String query = "select * from guide";

			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				guide guide = new guide();
				guideList.add(this.guideMapper.mapToGuide(guide, rs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guideList;
	}
}
