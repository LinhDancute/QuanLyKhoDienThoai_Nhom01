package UserManagement;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.sql.*;
import java.sql.ResultSet;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ForgotPassword extends JFrame {

	private JPanel contentPane;
	private JPasswordField textNewPassword;
	private JPasswordField textConfirm;
	private JTextField textUsername;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ForgotPassword frame = new ForgotPassword();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection con;
	Statement pst;
	private JTextField textEmail;
	private JTextField textAnswer;

	
	/**
	 * Create the frame.
	 */
	public ForgotPassword() {
		setTitle("Change Password");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 617, 553);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CHANGE PASSWORD");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(213, 22, 223, 49);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_4 = new JLabel("New password:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(71, 289, 116, 30);
		contentPane.add(lblNewLabel_4);
		
		textNewPassword = new JPasswordField();
		textNewPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textNewPassword.setBounds(213, 290, 195, 30);
		contentPane.add(textNewPassword);
		
		JLabel lblNewLabel_5 = new JLabel("Confirm password:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(71, 354, 131, 19);
		contentPane.add(lblNewLabel_5);
		
		textConfirm = new JPasswordField();
		textConfirm.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textConfirm.setBounds(213, 353, 195, 30);
		contentPane.add(textConfirm);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = textUsername.getText();
				String newpassword = new String(textNewPassword.getPassword());
				String confirm = new String(textConfirm.getPassword());
				String email = textEmail.getText();
				String answer = textAnswer.getText();
				
				StringBuilder cd = new StringBuilder();
				
				if(newpassword.equals("")) {
					cd.append("You haven't entered your new password!\n");
				}
				
				if(confirm.equals("")) {
					cd.append("You haven't confirmed your new password!\n");
				}
				if(email.equals("")) {
					cd.append("Please type your email!\n");
				}
				
				if(answer.equals("")) {
					cd.append("You haven't answered!\n");
				}
				
				JFrame frame = new JFrame("JOptionPane showMessageDialog example");
				if(cd.length()>0) {
					JOptionPane.showMessageDialog(frame, cd.toString(), "Invalidation", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					con = DriverManager.getConnection("jdbc:mySQL://localhost:3306/qldt","root","admin");
					Statement pst = con.createStatement();
					ResultSet rs = pst.executeQuery("select * from user where Username ='"+ username + "'and Email = '"+ answer+"'");
						
					if(rs.next()) {
						
						if (newpassword.equals(confirm)) {
							pst.executeUpdate("update user set Password = '"+ newpassword+ "' where Username = '"+ username + "'and Email ='"+ answer+"'");
							JOptionPane.showMessageDialog(null, "Your password is successfully updated");
							setVisible(false);
							new LoginForm().setVisible(true);
						} else {
							JOptionPane.showMessageDialog(frame, "The entered passwords do not match. Try again.", "Failture", JOptionPane.ERROR_MESSAGE);
						}
					}
					else {
						JOptionPane.showMessageDialog(frame, "Please write correct Username or Answer", "Failture", JOptionPane.ERROR_MESSAGE);
					}
				
				
				}catch (SQLException e2) {
					e2.printStackTrace();
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Error in connection");
					e2.printStackTrace();
				}
			}
			
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(93, 443, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new LoginForm().setVisible(true);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.setBounds(396, 443, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Clear");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textUsername.setText("");
				textAnswer.setText("");
				textNewPassword.setText("");
				textConfirm.setText("");
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_2.setBounds(246, 445, 89, 23);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Username:");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_1.setBounds(74, 98, 116, 14);
		contentPane.add(lblNewLabel_2_1);
		
		textUsername = new JTextField();
		textUsername.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textUsername.setColumns(10);
		textUsername.setBounds(216, 97, 195, 30);
		contentPane.add(textUsername);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Question Querity?");
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_1_1.setBounds(74, 159, 116, 30);
		contentPane.add(lblNewLabel_2_1_1);
		
		textEmail = new JTextField();
		textEmail.setText("What's your email?");
		textEmail.setFont(new Font("Tahoma", Font.BOLD, 13));
		textEmail.setColumns(10);
		textEmail.setBounds(216, 158, 195, 30);
		contentPane.add(textEmail);
		
		JLabel lblNewLabel_2_1_2 = new JLabel("Answer:");
		lblNewLabel_2_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_1_2.setBounds(74, 225, 116, 14);
		contentPane.add(lblNewLabel_2_1_2);
		
		textAnswer = new JTextField();
		textAnswer.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textAnswer.setColumns(10);
		textAnswer.setBounds(216, 224, 195, 30);
		contentPane.add(textAnswer);
		
		
	}
}
