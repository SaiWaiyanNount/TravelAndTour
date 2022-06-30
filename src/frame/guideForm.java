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
import models.guide;
import services.guideService;
import shared.mapper.TravelMapper;

import java.awt.Color;
import java.awt.SystemColor;

public class guideForm {

	public JFrame frame;
	private JTextField txtName;
	private JTextField txtSearch;
	private JTable tblGuide;
	private guide guide;
	private guideService guideService;
	private DefaultTableModel dtm = new DefaultTableModel();
	private List<guide> guideList = new ArrayList();
	private List<guide> filterGuideList = new ArrayList();

	private final DBConfig dbConfig = new DBConfig();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					guideForm window = new guideForm();
					window.frame.setVisible(true);
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
	public guideForm() throws SQLException {
		initialize();
		this.initializeDependency();
		this.setTableDesign();
		this.loadAllGuide(Optional.empty());
	}

	private void initializeDependency() {
		this.guideService = new guideService();
	}

	private void setTableDesign() {
		dtm.addColumn("ID");
		dtm.addColumn("Guide Name");
		this.tblGuide.setModel(dtm);
	}

	private void loadAllGuide(Optional<List<guide>> optionalGuide) {

		this.dtm = (DefaultTableModel) this.tblGuide.getModel();
		this.dtm.getDataVector().removeAllElements();
		this.dtm.fireTableDataChanged();

		this.guideList = this.guideService.findAllGuide();

		this.filterGuideList = optionalGuide.orElseGet(() -> this.guideList).stream().collect(Collectors.toList());

		filterGuideList.forEach(g -> {
			Object[] row = new Object[2];
			row[0] = g.getGuide_id();
			row[1] = g.getGuideName();
			dtm.addRow(row);
		});

		this.tblGuide.setModel(dtm);
	}

	private void resetFormData() {
		txtName.setText("");

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(51, 204, 255));
		frame.setTitle("Guide Entry");
		frame.setBounds(100, 100, 857, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.LEFT);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblName.setBounds(30, 65, 85, 29);
		frame.getContentPane().add(lblName);

		txtName = new JTextField();
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtName.setColumns(10);
		txtName.setBounds(127, 65, 193, 29);
		frame.getContentPane().add(txtName);

		JButton btnSave = new JButton("Save");
		btnSave.setForeground(Color.BLACK);
		btnSave.setBackground(Color.WHITE);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				guide guide = new guide();
				

				if (!txtName.getText().trim().isBlank()) {
					if(!txtName.getText().trim().matches("[a-zA-Z ]+")) {
						JOptionPane.showMessageDialog(null,"Name  should be Digit! Please ReEnter Data ...");
						resetFormData();
					return;
				}
                    guide.setGuideName(txtName.getText());
					guideService.createGuide(guide);
					JOptionPane.showMessageDialog(null, "Save Successfully!!!");
					resetFormData();
					loadAllGuide(Optional.empty());

				} else {
					JOptionPane.showMessageDialog(null, "Enter Required Field");
				}

			}
		});
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSave.setBounds(30, 410, 104, 29);
		frame.getContentPane().add(btnSave);

		txtSearch = new JTextField();
		txtSearch.setToolTipText("");
		txtSearch.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtSearch.setColumns(10);
		txtSearch.setBounds(538, 65, 165, 29);
		frame.getContentPane().add(txtSearch);

		JButton btnSearch = new JButton("Search");
		btnSearch.setForeground(Color.WHITE);
		btnSearch.setBackground(Color.GRAY);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String search = txtSearch.getText();

				loadAllGuide(Optional.of(guideList.stream()
						.filter(guide -> guide.getGuideName().toLowerCase().startsWith(search.toLowerCase()))
						.collect(Collectors.toList())));

			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSearch.setBounds(715, 65, 85, 29);
		frame.getContentPane().add(btnSearch);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 104, 770, 296);
		frame.getContentPane().add(scrollPane);

		tblGuide = new JTable();
		tblGuide.setFont(new Font("Tahoma", Font.PLAIN, 15));
		scrollPane.setViewportView(tblGuide);
		this.tblGuide.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
			if (!tblGuide.getSelectionModel().isSelectionEmpty()) {
				String id = tblGuide.getValueAt(tblGuide.getSelectedRow(), 0).toString();

				guide = guideService.findGuideId(id);
				// System.out.println(employee);

				txtName.setText(guide.getGuideName());

			}
		});

		JButton btnUpdate = new JButton("Update");
		btnUpdate.setForeground(Color.BLACK);
		btnUpdate.setBackground(Color.WHITE);
		btnUpdate.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				guide.setGuideName(txtName.getText());

				if (!guide.getGuideName().isBlank()) {
					if(!txtName.getText().trim().matches("[a-zA-Z ]+")) {
						JOptionPane.showMessageDialog(null,"Name should be Digit! Please ReEnter Data ...");
						resetFormData();
					return;
				}
					guideService.updateGuide(String.valueOf(guide.getGuide_id()), guide);
					//JOptionPane.showMessageDialog(null, "Update Successfully!!!");
					resetFormData();
					loadAllGuide(Optional.empty());
					guide = null;

				}
			}
		});

		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnUpdate.setBounds(159, 410, 104, 29);
		frame.getContentPane().add(btnUpdate);

		JButton btnDelete = new JButton("Delete");
		btnDelete.setForeground(Color.BLACK);
		btnDelete.setBackground(Color.WHITE);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				guideService.deleteGuide(String.valueOf(guide.getGuide_id()));
				//JOptionPane.showMessageDialog(null, "Delete Successfully!!!");
				resetFormData();
				loadAllGuide(Optional.empty());

				guide = null;

			}

		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDelete.setBounds(288, 410, 111, 29);
		frame.getContentPane().add(btnDelete);

		JLabel lblNewLabel = new JLabel("Guide Details");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(359, 10, 202, 29);
		frame.getContentPane().add(lblNewLabel);
		
		
	}
}
