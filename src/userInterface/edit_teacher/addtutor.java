package userInterface.edit_teacher;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import userInterface.auth;

public class addtutor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nameField;
	private JTextField emailField;
	private JTextField passField;
	private JTextField phnumField;

	public addtutor() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 275, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblAddTutor = new JLabel("Add Tutor");
		lblAddTutor.setFont(new Font("Annfold", Font.PLAIN, 18));
		lblAddTutor.setBounds(10, 10, 241, 38);
		contentPane.add(lblAddTutor);

		JLabel lblNewLabel = new JLabel("Full Name");
		lblNewLabel.setBounds(20, 58, 62, 13);
		contentPane.add(lblNewLabel);

		nameField = new JTextField();
		nameField.setBounds(92, 58, 135, 19);
		contentPane.add(nameField);
		nameField.setColumns(10);

		emailField = new JTextField();
		emailField.setColumns(10);
		emailField.setBounds(92, 87, 135, 19);
		contentPane.add(emailField);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(20, 87, 62, 13);
		contentPane.add(lblEmail);

		passField = new JTextField();
		passField.setColumns(10);
		passField.setBounds(92, 122, 135, 19);
		contentPane.add(passField);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(20, 122, 62, 13);
		contentPane.add(lblPassword);

		phnumField = new JTextField();
		phnumField.setColumns(10);
		phnumField.setBounds(92, 157, 135, 19);
		contentPane.add(phnumField);

		JLabel lblPhoneNumber = new JLabel("Phone Number");
		lblPhoneNumber.setBounds(20, 157, 72, 13);
		contentPane.add(lblPhoneNumber);

		JButton createaccBtn = new JButton("create");
		createaccBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String emailValidator = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
					String email = emailField.getText();

					Pattern emailpattern = Pattern.compile(emailValidator);
					Matcher emailmatcher = emailpattern.matcher(email);

					final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
					String password = passField.getText();
					String passwordString = new String(password);

					Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
					Matcher matcher = pattern.matcher(passwordString);


					if (nameField.getText().isEmpty() || emailField.getText().isEmpty() || phnumField.getText().isEmpty() || passField.getText().isEmpty()) 
					{
						JOptionPane.showMessageDialog(null, "All fields are required.", null, JOptionPane.ERROR_MESSAGE);
						return;
					}
					if (!emailmatcher.matches()) {
						JOptionPane.showMessageDialog(null, "Email not valid", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					if (!matcher.matches()) {
						JOptionPane.showMessageDialog(null, "The password must contain\n"
								+ " at least 8 characters,\n"
								+ " including a lowercase letter,\n"
								+ " an uppercase letter,\n"
								+ " a number,\n"
								+ " and a special character.", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					auth.addTeach(nameField.getText(),emailField.getText(),passField.getText(),phnumField.getText());
					JOptionPane.showMessageDialog(null, "The data was added successfully :))");

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		createaccBtn.setBounds(166, 219, 85, 21);
		contentPane.add(createaccBtn);
	}

}
