package UserManagement;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import GiaoDien.GiaoDienMenu;
import OrderUser.ExportOrderUser;
import OrderUser.ImportOrderUser;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class LoginForm extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
        
        //private Map<String, String> userSelection = new HashMap<>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginForm frame = new LoginForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection con;
	Statement pst;

	/**
	 * Create the frame.
	 */
	public LoginForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("LoginForm");
		setBounds(100, 100, 502, 441);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login in System");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(171, 39, 152, 33);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(62, 96, 90, 17);
		contentPane.add(lblNewLabel_1);
		
		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtUsername.setBounds(189, 93, 241, 30);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Password:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(62, 154, 76, 14);
		contentPane.add(lblNewLabel_2);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(189, 151, 241, 30);
		contentPane.add(txtPassword);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(51, 277, 368, 2);
		contentPane.add(separator);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username  = txtUsername.getText();
				String password = new String(txtPassword.getPassword());

				StringBuilder ab = new StringBuilder();
				JFrame frame = new JFrame("JOptionPane showMessageDialog example");
				
				if(username.equals("")) {
					ab.append("Username is empty!\n");
				}
				if(password.equals("")) {
					ab.append("Password is empty!\n");
				}
				if(ab.length()>0) {
					JOptionPane.showMessageDialog(frame, ab.toString(), "Invalidation", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				try {
                    Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/qldt",
                        "root", "admin");

                    PreparedStatement st = (PreparedStatement) connection
                        .prepareStatement("Select * from user where Username = ? and Password = ?");
                    
                    st.setString(1, username);
                    st.setString(2, password);
              
                        
                    ResultSet rs = st.executeQuery();
                    
                    
                    if (rs.next()) {
                        String usertype = rs.getString("UserType");
              
                        dispose();
                        if (usertype.equals("Admin")) {
                        	GiaoDienMenu ah = new GiaoDienMenu();
                            ah.setTitle("Welcome");
                            ah.setVisible(true);
                            
						}else if (usertype.equals("Import_User")){
                                                        ImportOrderUser ah = new ImportOrderUser();
                                                        ah.setTitle("Welcome");
                                                        ah.setVisible(true);
						}else if (usertype.equals("Export_User"))
                                                {
							ExportOrderUser ah = new ExportOrderUser();
                                                        ah.setTitle("Welcome");
                                                        ah.setVisible(true);
						}
                    }
                    else {
                        JOptionPane.showMessageDialog(frame, "Wrong Username & Password");
                    	}
                    } catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLogin.setBounds(79, 313, 126, 33);
		contentPane.add(btnLogin);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtUsername.setText("");
				txtPassword.setText("");		
			}
		});
		
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnReset.setBounds(305, 313, 114, 33);
		contentPane.add(btnReset);
		
		JButton btnForgotPassword = new JButton("Forgot Password?");
		btnForgotPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new ForgotPassword().setVisible(true);
			}
		});
		btnForgotPassword.setFont(new Font("Tahoma", Font.ITALIC, 14));
		btnForgotPassword.setBounds(178, 225, 152, 23);
		contentPane.add(btnForgotPassword);
	}
}
