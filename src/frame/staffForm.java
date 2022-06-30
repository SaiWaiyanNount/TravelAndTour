package frame;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

import config.DBConfig;
import models.staff;
import services.staffService;
import javax.swing.JPasswordField;

public class staffForm{

	private JFrame frameStaff;
	private JTextField txtname;
	private JTextField txtadd;
	private staffService staffService;
	private staff staff;
	private JTable tblStaff;
	private DefaultTableModel dtm = new DefaultTableModel();
	private final DBConfig dbConfig = new DBConfig();
	private JTextField txtSearch;
	private List<staff> staffList = new ArrayList<>();
	private List<staff> filteredstaffList = new ArrayList<>();
	private JTextField txtPhone;
	private JTextField txtEmail;
	private JTextField txtUserName;
	private JTextField txtRole;
	private JTextField txtActive;
	private JPasswordField txtPassword;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					staffForm window = new staffForm();
					window.frameStaff.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public staffForm() throws SQLException {
		initialize();
		this.initializeDependency();
		this.setTableDesign();
		 this.loadAllStaff(Optional.empty());
       
	}

	private void loadAllStaff(Optional<List<staff>> optionalStaff) throws SQLException {
		// TODO Auto-generated method stub
		this.dtm = (DefaultTableModel) this.tblStaff.getModel();
		this.dtm.getDataVector().removeAllElements();
		this.dtm.fireTableDataChanged();
        this.staffList  =this.staffService.findAllStaff();
        this.filteredstaffList = optionalStaff.orElseGet(() -> this.staffList).stream().collect(Collectors.toList());
        filteredstaffList.forEach(s ->{
        	Object[] row =new Object[9];
        	row[0] =s.getStaff_id();
        	row[1] =s.getName();
        	row[2]=s.getEmail();
        	row[3] =s.getAddress();
        	row[4]=s.getPhone();     	
        	
        	row[5]=s.getUserName();
        	row[6]=s.getPasssword();
        	row[7]=s.getRole();
        	row[8]=s.getActive();
        	dtm.addRow(row);
        	

        	});


		this.tblStaff.setModel(dtm);
		
	}

	private void setTableDesign() {
		// TODO Auto-generated method stub
		dtm.addColumn("ID");
		dtm.addColumn("Name");
		dtm.addColumn("Email");
		dtm.addColumn("Address");
		dtm.addColumn("Phone");		
		dtm.addColumn("Username");
		dtm.addColumn("Password");
		dtm.addColumn("Role");
		dtm.addColumn("Active");
		this.tblStaff.setModel(dtm);
		
	}

	private void initializeDependency() {
		// TODO Auto-generated method stub
	
		this.staffService = new staffService();
		
		
	}
	private void resetFormData() {
		txtname.setText("");
		
		txtEmail.setText("");
		txtadd.setText("");
		txtPhone.setText("");
		txtUserName.setText("");
		txtPassword.setText("");
		txtRole.setText("");
		txtActive.setText("");
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameStaff = new JFrame();
		frameStaff.setTitle("Staff Data Entry Form");
		frameStaff.getContentPane().setBackground(new Color(153, 204, 255));
		frameStaff.setForeground(new Color(153, 153, 0));
		frameStaff.getContentPane().setForeground(new Color(153, 153, 51));
		frameStaff.setBounds(100, 100, 1000, 500);
		frameStaff.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameStaff.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(391, 140, 900, 291);
		frameStaff.getContentPane().add(scrollPane);
		
		tblStaff = new JTable();
		tblStaff.setFont(new Font("Tahoma", Font.PLAIN, 15));
		scrollPane.setViewportView(tblStaff);
		this.tblStaff.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
			if (!tblStaff.getSelectionModel().isSelectionEmpty()) {

				String id = tblStaff.getValueAt(tblStaff.getSelectedRow(), 0).toString();

				staff = staffService.findStaffById(id);

				txtname.setText(staff.getName());
				txtEmail.setText(staff.getEmail());	
				txtadd.setText(staff.getAddress());
				txtPhone.setText(staff.getPhone());				
			    txtUserName.setText(staff.getUserName());
			    txtPassword.setText(staff.getPasssword());
			    txtRole.setText(staff.getRole());
			    
				txtActive.setText(staff.getActive() + "");

			}
		});

		JLabel lblNewLabel = new JLabel("Staff Informations");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel.setForeground(new Color(0, 51, 51));
		lblNewLabel.setBounds(400, 30, 165, 29);
		frameStaff.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setBounds(20, 70, 64, 14);
		frameStaff.getContentPane().add(lblNewLabel_1);
		
		txtname = new JTextField();
		txtname.setBounds(141, 68, 86, 20);
		frameStaff.getContentPane().add(txtname);
		txtname.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Address");
		lblNewLabel_2.setForeground(new Color(0, 0, 0));
		lblNewLabel_2.setBounds(20, 149, 64, 14);
		frameStaff.getContentPane().add(lblNewLabel_2);
		
		txtadd = new JTextField();
		txtadd.setBounds(141, 147, 86, 20);
		frameStaff.getContentPane().add(txtadd);
		txtadd.setColumns(10);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				
				staff s=new staff();
				
				if(!txtname.getText().isBlank() && !txtPhone.getText().isBlank() && !txtEmail.getText().isBlank() && !txtadd.getText().isBlank() && !txtUserName.getText().isBlank() && !txtPassword.getText().isBlank() && !txtRole.getText().isBlank()) {
					if(!txtActive.getText().trim().matches("[0-9]+")) {
						JOptionPane.showMessageDialog(null,"Active should be Digit! Please ReEnter Data ...");
						resetFormData();
					return;
				}
					if(!txtPhone.getText().trim().matches("[0-9 -]+")) {
						JOptionPane.showMessageDialog(null,"Phone should be Digit! Please ReEnter Data ...");
						resetFormData();
					return;
				}
					if(!txtEmail.getText().trim().matches("[a-z @]+")) {
						JOptionPane.showMessageDialog(null,"Email should be correct format!! ReEnter Data ...");
						resetFormData();
					return;
				}
				if(!txtname.getText().trim().matches("[a-zA-Z ]+") ) {
					JOptionPane.showMessageDialog(null,"Name should be format! Please ReEnter Data ..");
					resetFormData();
					return;
				}
					s.setName(txtname.getText());
					
					s.setEmail(txtEmail.getText());
					s.setAddress(txtadd.getText());
					s.setPhone(txtPhone.getText());
					s.setUserName(txtUserName.getText());
					s.setPassword(txtPassword.getText());
					s.setRole(txtRole.getText());
					s.setActive(Integer.parseInt(txtActive.getText()));
					staffService.createstaff(s);
					resetFormData();
					try {
						loadAllStaff(Optional.empty());
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
				}
				else {
					JOptionPane.showMessageDialog(null, "Should not Null! ReEnter Data..");
					
				}
				
			}
		});
		btnNewButton.setForeground(Color.BLUE);
		btnNewButton.setBounds(12, 428, 89, 23);
		frameStaff.getContentPane().add(btnNewButton);
		
		JButton updatebtn = new JButton("Update");
		updatebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				staff.setName(txtname.getText());
				staff.setPhone(txtPhone.getText());
				staff.setEmail(txtEmail.getText());				
				staff.setAddress(txtadd.getText());
				staff.setUserName(txtUserName.getText());
				staff.setPassword(txtPassword.getText());
				staff.setRole(txtRole.getText());			
				staff.setActive(Integer.parseInt(txtActive.getText()));
			
				if (!staff.getName().isBlank() && !staff.getAddress().isBlank() && !staff.getPasssword().isBlank() && !staff.getEmail().isBlank() && !staff.getRole().isBlank() && !staff.getUserName().isBlank()  &&  !staff.getPhone().isBlank()) {
					if(!txtActive.getText().trim().matches("[0-9]+")) {
						JOptionPane.showMessageDialog(null,"Active should be Digit! Please ReEnter Data ...");
						resetFormData();
					return;
				}
					if(!txtPhone.getText().trim().matches("[0-9 -]+")) {
						JOptionPane.showMessageDialog(null,"Phone should be Digit! Please ReEnter Data ...");
						resetFormData();
					return;
				}
				if(!txtname.getText().trim().matches("[a-zA-Z ]+") ) {
					JOptionPane.showMessageDialog(null,"Name should be format! Please ReEnter Data ..");
					resetFormData();
					return;
				}
					staffService.updateStaff(String.valueOf(staff.getStaff_id()), staff);
					resetFormData();
					try {
						loadAllStaff(Optional.empty());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					staff = null;

				}
			}
			
		});
		updatebtn.setForeground(new Color(102, 51, 0));
		updatebtn.setFont(new Font("Tahoma", Font.PLAIN, 11));
		updatebtn.setBounds(138, 429, 89, 23);
		frameStaff.getContentPane().add(updatebtn);
		
		JButton deletebtn = new JButton("Delete");
		deletebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				staff.setName(txtname.getText());
				staff.setPhone(txtPhone.getText());
				staff.setEmail(txtEmail.getText());				
				staff.setAddress(txtadd.getText());
				staff.setUserName(txtUserName.getText());
				staff.setPassword(txtPassword.getText());
				staff.setRole(txtRole.getText());			
				staff.setActive(Integer.parseInt(txtActive.getText()));
				if (!txtname.getText().isBlank() && !txtPhone.getText().isBlank() && !txtEmail.getText().isBlank() && !txtadd.getText().isBlank() && !txtUserName.getText().isBlank() && !txtPassword.getText().isBlank() && !txtRole.getText().isBlank()) {

					staffService.deleteStaff(String.valueOf(staff.getStaff_id()), staff);
					resetFormData();
					try {
						loadAllStaff(Optional.empty());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					staff = null;

				}
			}
			
		});
		deletebtn.setForeground(new Color(204, 0, 0));
		deletebtn.setBounds(250, 428, 89, 23);
		frameStaff.getContentPane().add(deletebtn);
		
		txtSearch = new JTextField();
		txtSearch.setBounds(601, 90, 86, 20);
		frameStaff.getContentPane().add(txtSearch);
		txtSearch.setColumns(10);
		
		JButton btnSearch = new JButton("Serach");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String keyword = txtSearch.getText();
				try {
					loadAllStaff(Optional.of(staffList.stream().filter(staff -> staff.getAddress().toLowerCase().startsWith(keyword.toLowerCase())).collect(Collectors.toList())));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
		});
		btnSearch.setBounds(730, 88, 89, 23);
		frameStaff.getContentPane().add(btnSearch);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setBounds(30, 95, -481, 83);
		frameStaff.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Phone");
		lblNewLabel_5.setBounds(20, 113, 46, 14);
		frameStaff.getContentPane().add(lblNewLabel_5);
		
		txtPhone = new JTextField();
		txtPhone.setBounds(141, 111, 86, 20);
		frameStaff.getContentPane().add(txtPhone);
		txtPhone.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Emali");
		lblNewLabel_6.setBounds(20, 195, 46, 14);
		frameStaff.getContentPane().add(lblNewLabel_6);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(141, 193, 86, 20);
		frameStaff.getContentPane().add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("UserName");
		lblNewLabel_7.setBounds(20, 239, 64, 14);
		frameStaff.getContentPane().add(lblNewLabel_7);
		
		txtUserName = new JTextField();
		txtUserName.setBounds(141, 237, 86, 20);
		frameStaff.getContentPane().add(txtUserName);
		txtUserName.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Password");
		lblNewLabel_8.setBounds(20, 284, 81, 14);
		frameStaff.getContentPane().add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Role");
		lblNewLabel_9.setBounds(20, 326, 46, 14);
		frameStaff.getContentPane().add(lblNewLabel_9);
		
		txtRole = new JTextField();
		txtRole.setBounds(141, 320, 86, 20);
		frameStaff.getContentPane().add(txtRole);
		txtRole.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("Active");
		lblNewLabel_10.setBounds(20, 365, 46, 14);
		frameStaff.getContentPane().add(lblNewLabel_10);
		
		txtActive = new JTextField();
		txtActive.setText("");
		txtActive.setBounds(141, 363, 86, 20);
		frameStaff.getContentPane().add(txtActive);
		txtActive.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(141, 282, 86, 20);
		frameStaff.getContentPane().add(txtPassword);
	}
}
