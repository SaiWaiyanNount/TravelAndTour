package frame;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Catagory implements ActionListener{

	private JFrame frameCatagory;
	JComboBox comboCategory;
	
	private staffForm staffForm;
	////test
	
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Catagory window = new Catagory();
					window.frameCatagory.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Catagory() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameCatagory = new JFrame();
		frameCatagory.setBounds(100, 100, 952, 641);
		frameCatagory.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameCatagory.getContentPane().setLayout(null);
		
		
		String [] category= {"Beach","Pagoda"};
		
		comboCategory = new JComboBox(category);
		comboCategory.setBounds(363, 145, 159, 30);
		frameCatagory.getContentPane().add(comboCategory);
		
		JLabel lblCatagory = new JLabel("Choose Category");
		lblCatagory.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCatagory.setBounds(200, 151, 138, 24);
		frameCatagory.getContentPane().add(lblCatagory);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource()==comboCategory) {
			
			System.out.println(comboCategory.getSelectedItem());
					}
	}
}
