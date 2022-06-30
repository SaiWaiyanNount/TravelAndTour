package frame;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.xdevapi.Statement;

import config.DBConfig;

import models.staff;
import services.AuthService;
import services.LoginService;

public class LoginForm {

	public JFrame frmLogin;
	private JTextField txtUsername;
	private DefaultTableModel dtm = new DefaultTableModel();

	private DBConfig dbConfig = new DBConfig();

	private LoginService loginservice;
	private JPasswordField txtPassword;
	private staff staff;
	private AuthService authservice;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginForm window = new LoginForm();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LoginForm() throws SQLException {
		initialize();
		this.initializeDependency();
		// this.setTableDesign();
		this.loadAllEmployees();
		this.dbConfig=new DBConfig();
	}

	private void initializeDependency() {

	}

	private void loadAllEmployees() {

	}

	private void resetFormData() {
		txtUsername.setText("");
		txtPassword.setText("");
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.getContentPane().setBackground(new Color(102, 204, 204));
		frmLogin.setTitle("Employee Entry");
		frmLogin.setBounds(100, 100, 1000, 500);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);

		JLabel lblusername = new JLabel("Username");
		lblusername.setForeground(new Color(0, 0, 102));
		lblusername.setHorizontalAlignment(SwingConstants.LEFT);
		lblusername.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblusername.setBounds(343, 97, 85, 29);
		frmLogin.getContentPane().add(lblusername);

		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtUsername.setColumns(10);
		txtUsername.setBounds(438, 97, 193, 29);
		frmLogin.getContentPane().add(txtUsername);

		JLabel lblpassword = new JLabel("Password");
		lblpassword.setForeground(new Color(0, 0, 102));
		lblpassword.setHorizontalAlignment(SwingConstants.LEFT);
		lblpassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblpassword.setBounds(343, 155, 85, 29);
		frmLogin.getContentPane().add(lblpassword);

		JButton btnLogin = new JButton("Login");
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setBackground(Color.GRAY);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * String username = txtUsername.getText(); String password =
				 * txtPassword.getText();
				 * 
				 * // loginservice.Checkpassword(username, password)
				 * 
				 * String sql = "select * from staff where username = '" + username +
				 * "' AND password = '" + password + "'";
				 * 
				 * 
				 * 
				 * Statement st; try { st = dbConfig.getConnection().createStatement();
				 * 
				 * 
				 * ResultSet rs; try { rs = st.executeQuery(sql);
				 * 
				 * 
				 * 
				 * if (rs.next()) {
				 * 
				 * if(txtUsername.getText().equals("admin")) { CustomerAdmin cus;
				 * 
				 * cus = new CustomerAdmin(); cus.main(null);
				 * 
				 * frmLogin.hide();} else if(txtUsername.getText().equals("yamin")) {
				 * 
				 * CustomerForm cus;
				 * 
				 * cus = new CustomerForm(); cus.main(null);
				 * 
				 * frmLogin.hide(); }
				 * 
				 * else { JOptionPane.showMessageDialog(null, "Invalid Login"); } } } catch
				 * (HeadlessException | SQLException e1) { // TODO Auto-generated catch block
				 * e1.printStackTrace();
				 * 
				 * 
				 */
				
				
				
				
				
				
				
				
				
				
				
				/*
				 * if (null != staff) { staff.setUsername(txtUsername.getText());
				 * staff.setPassword(String.valueOf(txtPassword.getPassword()));
				 * 
				 * if (!staff.getUsername().isBlank() && !staff.getPassword().isBlank()) {
				 * loginservice.updateEmployee(String.valueOf(staff.getId()), staff);
				 * frmLogin.setVisible(false); CustomerForm customerform = null; try {
				 * customerform = new CustomerForm(); } catch (SQLException e1) { // TODO
				 * Auto-generated catch block e1.printStackTrace(); }
				 * customerform.frameCus.setVisible(true); } else {
				 * JOptionPane.showMessageDialog(null, "Fill required fields"); } } else {
				 * String username = txtUsername.getText(); String password =
				 * String.valueOf(txtPassword.getPassword());
				 * 
				 * if (!username.isBlank() && !password.isBlank()) { String loggedInUserId =
				 * authservice.login(username, password); if(!loggedInUserId.isBlank()) {
				 * CurrentUserHolder.setLoggedInUser((Staff)
				 * loginservice.findEmployeeById(loggedInUserId));
				 * JOptionPane.showMessageDialog(null, "Successfully Login");
				 * frmLogin.setVisible(false); // PurchaseEditForm purchaseEditForm = new
				 * PurchaseEditForm(); CustomerForm customerform = null; try { customerform =
				 * new CustomerForm(); } catch (SQLException e1) { // TODO Auto-generated catch
				 * block e1.printStackTrace(); } customerform.frameCus.setVisible(true); } }
				 * else { JOptionPane.showMessageDialog(null, "Enter required Fields"); } }
				 * 
				 */
						
				
					}
									
			
		});
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLogin.setBounds(346, 300, 92, 29);
		frmLogin.getContentPane().add(btnLogin);

		JButton btnClose = new JButton("Close");
		btnClose.setForeground(Color.WHITE);
		btnClose.setBackground(Color.GRAY);
		btnClose.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				frmLogin.hide();
			}
		});

		btnClose.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnClose.setBounds(473, 300, 101, 29);
		frmLogin.getContentPane().add(btnClose);

		JButton btnForgot = new JButton("ForgotPassword");
		btnForgot.setForeground(Color.WHITE);
		btnForgot.setBackground(Color.GRAY);
		btnForgot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}

		});
		btnForgot.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnForgot.setBounds(438, 211, 193, 29);
		frmLogin.getContentPane().add(btnForgot);

		JLabel lbltitle = new JLabel("Travel and Tour Management");
		lbltitle.setForeground(new Color(0, 0, 51));
		lbltitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		lbltitle.setBounds(343, 0, 353, 29);
		frmLogin.getContentPane().add(lbltitle);

		txtPassword = new JPasswordField();
		txtPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
			
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						
						

				
				
				String username = txtUsername.getText();
				String password = txtPassword.getText();
				
				String sql = "select * from staff where username = '" + username + "' AND password = '" + password
						+ "'";
				
				try {
					java.sql.Statement st =dbConfig.getConnection().createStatement();
					
					  ResultSet rs = ((java.sql.Statement) st).executeQuery(sql);
					  
					  if(rs.next()) {
						  
						  CustomerAdmin customeradmin;
							try {
								customeradmin = new CustomerAdmin();
								CustomerAdmin.main(null);
							}catch (Throwable e1) {
								e1.printStackTrace();
							}
							frmLogin.hide();
						  
					  }
					
					
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
				
				
					}
				
				
				
			}
		});
		
		
		txtPassword.setBounds(438, 157, 193, 29);
		frmLogin.getContentPane().add(txtPassword);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Uset\\Desktop\\Login.jpg"));
		lblNewLabel.setBounds(0, 0, 986, 463);
		frmLogin.getContentPane().add(lblNewLabel);
		
		
			}
			}
