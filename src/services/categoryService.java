package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import config.DBConfig;
import models.category;
import shared.mapper.TravelMapper;

public class categoryService {

	private final TravelMapper categoryMapper;
	private final DBConfig dbConfig;

	public categoryService() {
		this.categoryMapper = new TravelMapper();
		this.dbConfig = new DBConfig();
	}

	public void createCategory(category category) {
		try {

			PreparedStatement ps = this.dbConfig.getConnection()
					.prepareStatement("INSERT INTO category (category_name) VALUES (?)");

			ps.setString(1, category.getCategory_name());

			ps.executeUpdate();
			ps.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateCategory(String id, category category) {
		try {
			PreparedStatement ps = this.dbConfig.getConnection()
					.prepareStatement("Update category set category_name=? where category_id =?");

			ps.setString(1, category.getCategory_name());
			ps.setString(2, id);

			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteCategory(String id) {
		try {

			PreparedStatement ps = this.dbConfig.getConnection()
					.prepareStatement("delete from category where category_id = '" + id + "'");

			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "!!!!!");
		}
	}

	public category findCategoryId(String id) {
		category category = new category();

		try (Statement st = this.dbConfig.getConnection().createStatement()) {

			String query = "Select * from category where category_id = " + id + ""; //
			System.out.println(query);

			ResultSet rs = st.executeQuery(query);

			if (rs.next()) {
				this.categoryMapper.mapToCategory(category, rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return category;
	}

	public List<category> findAllCategory() {

		List<category> categoryList = new ArrayList<>();

		try (Statement st = this.dbConfig.getConnection().createStatement()) {
			String query = "select * from category";

			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				category category = new category();
				categoryList.add(this.categoryMapper.mapToCategory(category, rs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return categoryList;
	}
}
