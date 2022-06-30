package frame;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;
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

import config.DBConfig;

import models.Customer;
import services.CustomerService;
import javax.swing.JRadioButton;

public class CustomerForm {


	public static JFrame frameCus;
	private JTextField txtName;
	private JTextField txtPhone;
	private JTextField txtEmail;
	private JTextField txtAddress;
	private JTextField txtSearch;
	private JTable tblCus;
	private Customer cus;
	private CustomerService cusService;
	private DefaultTableModel dtm = new DefaultTableModel();
	private final DBConfig dbConfig = new DBConfig();
	private List<Customer> cusList = new ArrayList();
	private List<Customer> filterCusList = new ArrayList();

	private ImageIcon image1;
	private JTextField textgender;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerForm window = new CustomerForm();
					window.frameCus.setVisible(true);
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
	public CustomerForm() throws SQLException {
		initialize();
		this.initializeDependency();
		this.setTableDesign();
		this.loadAllCustomers(Optional.empty());
	}

	private void initializeDependency() {
			this.cusService = new CustomerService();
	}

	private void setTableDesign() {
		dtm.addColumn("ID");
		dtm.addColumn("Name");
		dtm.addColumn("Email");
		dtm.addColumn("Phone No");
		dtm.addColumn("Address");
		dtm.addColumn("Gender");
		
		this.tblCus.setModel(dtm);
	}

	private void loadAllCustomers(Optional<List<Customer>> optionalCus) {
		this.dtm = (DefaultTableModel) this.tblCus.getModel();
		this.dtm.getDataVector().removeAllElements();
		this.dtm.fireTableDataChanged();

		this.cusList = this.cusService.findAllCustomers();

		this.filterCusList	 = optionalCus.orElseGet(() -> this.cusList).stream().collect(Collectors.toList());

		filterCusList.forEach(e -> {
			Object[] row = new Object[6];
			row[0] = e.getCustomer_id();
			row[1] = e.getCustomer_name();
			row[2] = e.getCustomer_email();
			row[3] = e.getCustomer_phone();
			row[4] = e.getCustomer_address();
			row[5] = e.getGender();
			dtm.addRow(row);
		});

		this.tblCus.setModel(dtm);
	}

	private void resetFormData() {
		txtName.setText("");
		txtEmail.setText("");
		txtPhone.setText("");
		txtAddress.setText("");
		textgender.setText("");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameCus = new JFrame();
		frameCus.getContentPane().setBackground(new Color(153, 204, 255));
		frameCus.setTitle("Supplier Entry");
		frameCus.setBounds(100, 100, 894, 673);
		frameCus.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameCus.getContentPane().setLayout(null);

		JLabel lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.LEFT);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblName.setBounds(47, 59, 74, 29);
		frameCus.getContentPane().add(lblName);

		txtName = new JTextField();
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtName.setColumns(10);
		txtName.setBounds(125, 59, 243, 29);
		frameCus.getContentPane().add(txtName);

		JLabel lblPhone = new JLabel("Phone No");
		lblPhone.setHorizontalAlignment(SwingConstants.LEFT);
		lblPhone.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPhone.setBounds(47, 139, 74, 29);
		frameCus.getContentPane().add(lblPhone);

		txtPhone = new JTextField();
		txtPhone.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtPhone.setColumns(10);
		txtPhone.setBounds(125, 139, 243, 29);
		frameCus.getContentPane().add(txtPhone);

		JButton btnSave = new JButton("Save");
		btnSave.setForeground(Color.BLACK);
		btnSave.setBackground(SystemColor.inactiveCaptionBorder);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Customer cus = new Customer();
				cus.setCustomer_name(txtName.getText());
				cus.setCustomer_email(txtEmail.getText());
				cus.setCustomer_phone(txtPhone.getText());
				cus.setCustomer_address(txtAddress.getText());
				cus.setGender(textgender.getText());
				
				//
				
	            
	            	            
	            
				/*
				 * if(rbtnMale.isSelected()) { cus.setGender(rbtnMale.getText()); } else
				 * if(rbtnFemalee.isSelected()) { cus.setGender(rbtnFemalee.getText());
				 * 
				 * }
				 */
				if (!cus.getCustomer_name().isBlank() && !cus.getCustomer_phone().isBlank() && !cus.getCustomer_email().isBlank() && !cus.getCustomer_address().isBlank()) {

					if(!txtName.getText().trim().matches("[a-zA-Z ]+"))
					{
						JOptionPane.showMessageDialog(null, "Please ReEnter Name");
						resetFormData();
						return;
					}
					
					if(!txtPhone.getText().trim().matches("[0-9]+"))
					{
						JOptionPane.showMessageDialog(null, "InValid Phone Number ");
						resetFormData();
						return;
					}
					
					
					
					
					
					
					cusService.createCustomer(cus);
					JOptionPane.showMessageDialog(null, "Save Successfully!!!");
					resetFormData();
					loadAllCustomers(Optional.empty());

				} else {
					JOptionPane.showMessageDialog(null, "Enter Required Field");
				}


			}
		});
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSave.setBounds(679, 583, 95, 29);
		frameCus.getContentPane().add(btnSave);

		txtSearch = new JTextField();
		txtSearch.setToolTipText("");
		txtSearch.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtSearch.setColumns(10);
		txtSearch.setBounds(699, 279, 165, 29);
		frameCus.getContentPane().add(txtSearch);

		JButton btnSearch = new JButton("Search");
		btnSearch.setForeground(Color.BLACK);
		btnSearch.setBackground(SystemColor.inactiveCaptionBorder);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String search = txtSearch.getText();
				
				  loadAllCustomers(Optional.of(cusList.stream() .filter(cus ->
				  (cus.getCustomer_name().toLowerCase().startsWith(search.toLowerCase()))
				  ||(cus.getCustomer_phone().startsWith(search))) .collect(Collectors.toList())));
				 
				//loadAllCustomers(Optional.empty());

			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSearch.setBounds(604, 279, 85, 29);
		frameCus.getContentPane().add(btnSearch);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(47, 319, 826, 253);
		frameCus.getContentPane().add(scrollPane);

		tblCus = new JTable();
		tblCus.setFont(new Font("Tahoma", Font.PLAIN, 15));
		scrollPane.setViewportView(tblCus);
		this.tblCus.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
			if (!tblCus.getSelectionModel().isSelectionEmpty()) {
				String id = tblCus.getValueAt(tblCus.getSelectedRow(), 0).toString();

				cus = cusService.findCustomerId(id);
				// System.out.println(employee);

				txtName.setText(cus.getCustomer_name());
				txtPhone.setText(cus.getCustomer_phone());
				txtEmail.setText(cus.getCustomer_email());
				txtAddress.setText(cus.getCustomer_address());
				

			}
		});

		JLabel lblAddress = new JLabel("Address");
		lblAddress.setHorizontalAlignment(SwingConstants.LEFT);
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAddress.setBounds(47, 179, 85, 29);
		frameCus.getContentPane().add(lblAddress);

		txtAddress = new JTextField();
		txtAddress.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtAddress.setColumns(10);
		txtAddress.setBounds(125, 179, 243, 29);
		frameCus.getContentPane().add(txtAddress);

		JLabel lblNewLabel = new JLabel("Customer Details");
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(362, 19, 202, 29);
		frameCus.getContentPane().add(lblNewLabel);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmail.setBounds(47, 99, 74, 29);
		frameCus.getContentPane().add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtEmail.setColumns(10);
		txtEmail.setBounds(125, 99, 243, 29);
		frameCus.getContentPane().add(txtEmail);
		
		JButton btnBeck = new JButton("Back");
		btnBeck.setBackground(SystemColor.inactiveCaptionBorder);
		btnBeck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frameCus.hide();
				//saleForm.show();
			}
		});
		btnBeck.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnBeck.setBounds(784, 583, 91, 29);
		frameCus.getContentPane().add(btnBeck);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGender.setBounds(47, 231, 49, 14);
		frameCus.getContentPane().add(lblGender);
		
		image1 = new ImageIcon(getClass().getResource("/icons/aa.jpg"));
		JLabel lblphoto = new JLabel(image1);
		lblphoto.setBackground(new Color(255, 255, 255));
		lblphoto.setBounds(479, 48, 391, 223);
		frameCus.getContentPane().add(lblphoto);
		
		textgender = new JTextField();
		textgender.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textgender.setColumns(10);
		textgender.setBounds(125, 219, 243, 29);
		frameCus.getContentPane().add(textgender);
	}

	public static void show() {
		// TODO Auto-generated method stub
		try {
			frameCus.show();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
