package frame;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Vector;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.html.Option;

import config.DBConfig;
import models.category;
import models.category;
import services.categoryService;
import shared.mapper.TravelMapper;

import java.awt.Color;
import java.awt.SystemColor;

public class categoryForm {

	public JFrame frameCategory;
	private JTextField txtName;
	private JTextField txtSearch;
	private JTable tblCategory;
	private category category;
	private categoryService categoryService;
	private DefaultTableModel dtm = new DefaultTableModel();
	private List<category> categoryList = new ArrayList();
	private List<category> filterCategoryList = new ArrayList();

	private final DBConfig dbConfig = new DBConfig();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					categoryForm window = new categoryForm();
					window.frameCategory.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws SQLException
	 */
	public categoryForm() throws SQLException {
		initialize();
		this.initializeDependency();
		this.setTableDesign();
		this.loadAllCategory(Optional.empty());
	}

	private void initializeDependency() {
		this.categoryService = new categoryService();
	}

	private void setTableDesign() {
		dtm.addColumn("ID");
		dtm.addColumn("Category Name");
		this.tblCategory.setModel(dtm);
	}

	private void loadAllCategory(Optional<List<category>> optionalCategory) {

		this.dtm = (DefaultTableModel) this.tblCategory.getModel();
		this.dtm.getDataVector().removeAllElements();
		this.dtm.fireTableDataChanged();

		this.categoryList = this.categoryService.findAllCategory();

		this.filterCategoryList = optionalCategory.orElseGet(() -> this.categoryList).stream().collect(Collectors.toList());

		filterCategoryList.forEach(c -> {
			Object[] row = new Object[2];
			row[0] =c.getCategory_id();
			row[1] = c.getCategory_name();
			dtm.addRow(row);
		});

		this.tblCategory.setModel(dtm);
	}

	private void resetFormData() {
		txtName.setText("");

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameCategory = new JFrame();
		frameCategory.getContentPane().setBackground(new Color(51, 204, 255));
		frameCategory.setTitle("Category Entry");
		frameCategory.setBounds(100, 100, 857, 500);
		frameCategory.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameCategory.getContentPane().setLayout(null);

		JLabel lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.LEFT);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblName.setBounds(30, 65, 85, 29);
		frameCategory.getContentPane().add(lblName);

		txtName = new JTextField();
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtName.setColumns(10);
		txtName.setBounds(127, 65, 193, 29);
		frameCategory.getContentPane().add(txtName);

		JButton btnSave = new JButton("Save");
		btnSave.setForeground(Color.BLACK);
		btnSave.setBackground(Color.WHITE);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				category category = new category();
				

				if (!txtName.getText().trim().isBlank()) {
					if(!txtName.getText().trim().matches("[a-zA-Z ]+")) {
						JOptionPane.showMessageDialog(null,"Name  should be Digit! Please ReEnter Data ...");
						resetFormData();
					return;
				}
                    category.setCategory_name(txtName.getText());
					categoryService.createCategory(category);
					JOptionPane.showMessageDialog(null, "Save Successfully!!!");
					resetFormData();
					loadAllCategory(Optional.empty());

				} else {
					JOptionPane.showMessageDialog(null, "Enter Required Field");
				}

			}
		});
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSave.setBounds(30, 410, 104, 29);
		frameCategory.getContentPane().add(btnSave);

		txtSearch = new JTextField();
		txtSearch.setToolTipText("");
		txtSearch.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtSearch.setColumns(10);
		txtSearch.setBounds(538, 65, 165, 29);
		frameCategory.getContentPane().add(txtSearch);

		JButton btnSearch = new JButton("Search");
		btnSearch.setForeground(Color.WHITE);
		btnSearch.setBackground(Color.GRAY);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String search = txtSearch.getText();

				loadAllCategory(Optional.of(categoryList.stream()
						
						.filter(category -> category.getCategory_name().toLowerCase().startsWith(search.toLowerCase()))
						.collect(Collectors.toList())));

			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSearch.setBounds(715, 65, 85, 29);
		frameCategory.getContentPane().add(btnSearch);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 104, 770, 296);
		frameCategory.getContentPane().add(scrollPane);

		tblCategory = new JTable();
		tblCategory.setFont(new Font("Tahoma", Font.PLAIN, 15));
		scrollPane.setViewportView(tblCategory);
		this.tblCategory.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
			if (!tblCategory.getSelectionModel().isSelectionEmpty()) {
				String id = tblCategory.getValueAt(tblCategory.getSelectedRow(), 0).toString();

				category = categoryService.findCategoryId(id);
				// System.out.println(employee);

				txtName.setText(category.getCategory_name());

			}
		});

		JButton btnUpdate = new JButton("Update");
		btnUpdate.setForeground(Color.BLACK);
		btnUpdate.setBackground(Color.WHITE);
		btnUpdate.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				category.setCategory_name(txtName.getText());

				if (!category.getCategory_name().isBlank()) {
					if(!txtName.getText().trim().matches("[a-zA-Z ]+")) {
						JOptionPane.showMessageDialog(null,"Name should be Digit! Please ReEnter Data ...");
						resetFormData();
					return;
				}
					categoryService.updateCategory(String.valueOf(category.getCategory_id()), category);
					//JOptionPane.showMessageDialog(null, "Update Successfully!!!");
					resetFormData();
					loadAllCategory(Optional.empty());
					category = null;

				}
			}
		});

		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnUpdate.setBounds(159, 410, 104, 29);
		frameCategory.getContentPane().add(btnUpdate);

		JButton btnDelete = new JButton("Delete");
		btnDelete.setForeground(Color.BLACK);
		btnDelete.setBackground(Color.WHITE);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				categoryService.deleteCategory(String.valueOf(category.getCategory_id()));
				//JOptionPane.showMessageDialog(null, "Delete Successfully!!!");
				resetFormData();
				loadAllCategory(Optional.empty());

				category = null;

			}

		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDelete.setBounds(288, 410, 111, 29);
		frameCategory.getContentPane().add(btnDelete);

		JLabel lblNewLabel = new JLabel("Guide Details");
		lblNewLabel.setBackground(new Color(153, 204, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(359, 10, 202, 29);
		frameCategory.getContentPane().add(lblNewLabel);
		
		
	}
}
