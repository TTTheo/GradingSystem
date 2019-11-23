package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import backend.UserBackend;
import objects.User;

// Reuses parts of ATM's Login class
public class LoginFrame extends JFrame {
	private JPanel contentPane;
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private JLabel signupLabel;
	private JTextField username;
	private JPasswordField password;
	private JButton loginBtn;
	private JButton signupBtn;

	// userBackend exposes functions that we can call.
	private UserBackend userBackend;
	
	public LoginFrame() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 457, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		userBackend = new UserBackend();

		init();
		addAction();
	}
	
	public void init(){
		usernameLabel = new JLabel("Username: ");
		usernameLabel.setBounds(76, 56, 93, 22);
		contentPane.add(usernameLabel);

		passwordLabel = new JLabel("Password:");
		passwordLabel.setBounds(76, 103, 74, 15);
		contentPane.add(passwordLabel);

		signupLabel = new JLabel("Don't have an account?");
		signupLabel.setBounds(48, 238, 200, 15);
		contentPane.add(signupLabel);

		username = new JTextField();
		username.setBounds(158, 57, 179, 21);
		contentPane.add(username);
		username.setColumns(10);

		password = new JPasswordField();
		password.setBounds(160, 100, 177, 21);
		contentPane.add(password);

		loginBtn = new JButton("Login");
		loginBtn.setBounds(173, 170, 93, 23);
		contentPane.add(loginBtn);

		signupBtn = new JButton("Sign up");
		signupBtn.setBounds(258, 234, 154, 23);
		contentPane.add(signupBtn);
	}

	public void alert(String str){
		JOptionPane.showMessageDialog(null, str);
	}

	// Use userBackend as an abstraction. We don't directly look up into
	// the db or authenticate, just call the backend function "loginUser()"
	// Handle any exceptions that the backend may throw
	public void addAction() { 
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user = username.getText();
				String pass = String.valueOf(password.getPassword());
				try {
					User login = userBackend.loginUser(user, pass);
					if (login == null) {
						alert("Invalid login, try again");
					} else {
						alert("Login successful!");
					}
				} catch (SQLException ex) {
					alert(ex.toString());
				}
			}
		}); 

		// This function should get and validate user input
		// then call the backend eg: userBackend.signup(user, pass);
		signupBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alert("Not implemented yet!");
			}
		}); 
	}
}
