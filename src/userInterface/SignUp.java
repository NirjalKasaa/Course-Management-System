package userInterface;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import dbmanager.DbManager;

public class SignUp extends JFrame {


	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JPasswordField pwdField;
	private JPasswordField cpwdField;
	JComboBox<String> comboBox;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp window = new SignUp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void updateCombo() {
		try {
			auth auth = new auth(DbManager.getInstance());
			ResultSet rs = auth.getAddComboItemsStmt().executeQuery();
			while (rs.next()) {
				comboBox.addItem(rs.getString("course_name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public SignUp() {

		try {
			DbManager db = new DbManager("jdbc:mysql://localhost:3306", "root", "");
			new auth(db);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null,
					"Can not connect to database!\nPlease make sure mySQL is correctly setup and running!",
					"Error 500: Server Communication Failed", JOptionPane.ERROR_MESSAGE);
			System.exit(500);
		}

		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setAutoRequestFocus(false);
		frame.setVisible(true);
		frame.setBounds(100, 100, 700, 550);
		frame.setTitle("Course Management System");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(10, 10, 325, 493);
		frame.getContentPane().add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));

		JPanel loginPanel = new JPanel();
		loginPanel.setBackground(new Color(255, 255, 255));
		layeredPane.add(loginPanel, "name_11080095375200");
		loginPanel.setLayout(null);

		JPanel signupPanel = new JPanel();
		signupPanel.setBackground(new Color(255, 255, 255));
		layeredPane.add(signupPanel, "name_17588628291599");

		JLabel liLogo = new JLabel(new ImageIcon(getClass().getResource("../pics/smallLogo.png")));
		liLogo.setBounds(10, 10, 96, 44);
		loginPanel.add(liLogo);

		JLabel loginLabel = new JLabel("WELCOME");
		loginLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		loginLabel.setBounds(61, 116, 199, 53);
		loginPanel.add(loginLabel);

		JLabel emailIcon = new JLabel(new ImageIcon(getClass().getResource("../pics/email.png")));
		emailIcon.setBounds(50, 213, 22, 15);
		loginPanel.add(emailIcon);

		JLabel mailLabel = new JLabel("Email");
		mailLabel.setFont(new Font("Albertus Medium", Font.PLAIN, 10));
		mailLabel.setBounds(90, 196, 55, 13);
		loginPanel.add(mailLabel);


		JTextField emailField = new JTextField();
		emailField.setForeground(new Color(0, 0, 0));
		emailField.setBackground(new Color(255, 255, 255));
		emailField.setBorder(null);
		emailField.setBounds(90, 210, 185, 20);
		loginPanel.add(emailField);

		JSeparator emailseparator = new JSeparator();
		emailseparator.setForeground(new Color(128, 128, 128));
		emailseparator.setBounds(90, 230, 185, 2);
		loginPanel.add(emailseparator);

		JLabel pwdIcon =  new JLabel(new ImageIcon(getClass().getResource("../pics/key.png")));
		pwdIcon.setBounds(45, 270, 32, 28);
		loginPanel.add(pwdIcon);

		JLabel pwdLabel = new JLabel("Password");
		pwdLabel.setFont(new Font("Albertus Medium", Font.PLAIN, 10));
		pwdLabel.setBounds(90, 260, 55, 13);
		loginPanel.add(pwdLabel);

		pwdField = new JPasswordField();
		pwdField.setBounds(90, 275, 185, 20);
		pwdField.setBorder(null);
		pwdField.setForeground(new Color(0, 0, 0));
		loginPanel.add(pwdField);

		JSeparator pwdseparator = new JSeparator();
		pwdseparator.setForeground(new Color(128, 128, 128));
		pwdseparator.setBounds(90, 295, 185, 2);
		loginPanel.add(pwdseparator);

		JButton loginBtn = new JButton("Login");
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					auth.login(emailField.getText(),new String(pwdField.getPassword()));
					frame.dispose();
					JOptionPane.showMessageDialog(null, "Logged in successfully");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),null,JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		loginBtn.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		loginBtn.setBounds(87, 325, 158, 28);
		loginPanel.add(loginBtn);

		JLabel LaccTxt = new JLabel("Don't have an account?");
		LaccTxt.setFont(new Font("Tahoma", Font.PLAIN, 12));
		LaccTxt.setBounds(75, 352, 142, 22);
		loginPanel.add(LaccTxt);

		JLabel clicksuTxt = new JLabel("SIGN UP");
		clicksuTxt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				layeredPane.removeAll();
				layeredPane.add(signupPanel);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		clicksuTxt.setFont(new Font("Tahoma", Font.BOLD, 12));
		clicksuTxt.setBounds(207, 357, 60, 13);
		loginPanel.add(clicksuTxt);
		signupPanel.setLayout(null);

		JLabel suLogo = new JLabel(new ImageIcon(getClass().getResource("../pics/smallLogo.png")));
		suLogo.setBounds(10, 10, 95, 45);
		signupPanel.add(suLogo);

		JLabel accLabel = new JLabel("Create an account");
		accLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		accLabel.setBounds(45, 76, 236, 64);
		signupPanel.add(accLabel);

		JLabel cnameIcon = new JLabel(new ImageIcon(getClass().getResource("../pics/name.png")));
		cnameIcon.setBounds(45, 150, 32, 28);
		signupPanel.add(cnameIcon);

		JLabel cnameLabel = new JLabel("Full Name");
		cnameLabel.setFont(new Font("Albertus Medium", Font.PLAIN, 10));
		cnameLabel.setBounds(90, 145, 55, 13);
		signupPanel.add(cnameLabel);

		JTextField cnameField = new JTextField();
		cnameField.setForeground(Color.BLACK);
		cnameField.setBorder(null);
		cnameField.setBackground(Color.WHITE);
		cnameField.setBounds(90, 158, 185, 20);
		signupPanel.add(cnameField);

		JSeparator cnameseparator = new JSeparator();
		cnameseparator.setForeground(Color.GRAY);
		cnameseparator.setBounds(90, 178, 185, 2);
		signupPanel.add(cnameseparator);

		JLabel cnumIcon = new JLabel(new ImageIcon(getClass().getResource("../pics/phone.png")));
		cnumIcon.setBounds(45, 205, 32, 28);
		signupPanel.add(cnumIcon);

		JLabel cnumLabel = new JLabel("Mobile Number");
		cnumLabel.setFont(new Font("Albertus Medium", Font.PLAIN, 10));
		cnumLabel.setBounds(90, 197, 81, 13);
		signupPanel.add(cnumLabel);

		JTextField cnumField = new JTextField();
		cnumField.setForeground(Color.BLACK);
		cnumField.setBorder(null);
		cnumField.setBackground(Color.WHITE);
		cnumField.setBounds(90, 212, 185, 20);
		signupPanel.add(cnumField);

		JSeparator cnumseparator = new JSeparator();
		cnumseparator.setForeground(Color.GRAY);
		cnumseparator.setBounds(90, 232, 185, 2);
		signupPanel.add(cnumseparator);

		JLabel cemailIcon = new JLabel(new ImageIcon(getClass().getResource("../pics/email.png")));
		cemailIcon.setBounds(50, 270, 22, 15);
		signupPanel.add(cemailIcon);

		JLabel cmailLabel = new JLabel("Email");
		cmailLabel.setFont(new Font("Albertus Medium", Font.PLAIN, 10));
		cmailLabel.setBounds(90, 253, 55, 13);
		signupPanel.add(cmailLabel);


		JTextField cemailField = new JTextField();
		cemailField.setForeground(new Color(0, 0, 0));
		cemailField.setBackground(new Color(255, 255, 255));
		cemailField.setBorder(null);
		cemailField.setBounds(90, 267, 185, 20);
		signupPanel.add(cemailField);

		JSeparator cemailseparator = new JSeparator();
		cemailseparator.setForeground(new Color(128, 128, 128));
		cemailseparator.setBounds(90, 287, 185, 2);
		signupPanel.add(cemailseparator);

		JLabel cpwdIcon =  new JLabel(new ImageIcon(getClass().getResource("../pics/key.png")));
		cpwdIcon.setBounds(45, 320, 32, 28);
		signupPanel.add(cpwdIcon);

		JLabel cpwdLabel = new JLabel("Password");
		cpwdLabel.setFont(new Font("Albertus Medium", Font.PLAIN, 10));
		cpwdLabel.setBounds(90, 309, 55, 13);
		signupPanel.add(cpwdLabel);

		cpwdField = new JPasswordField();
		cpwdField.setBounds(90, 325, 185, 20);
		cpwdField.setBorder(null);
		cpwdField.setForeground(new Color(0, 0, 0));
		signupPanel.add(cpwdField);

		JSeparator cpwdseparator = new JSeparator();
		cpwdseparator.setForeground(new Color(128, 128, 128));
		cpwdseparator.setBounds(90, 345, 185, 2);
		signupPanel.add(cpwdseparator);

		JLabel courseIcon = new JLabel(new ImageIcon(getClass().getResource("../pics/course.png")));
		courseIcon.setBounds(40, 368, 48, 48);
		signupPanel.add(courseIcon);

		comboBox = new JComboBox<String>();
		updateCombo(); 
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBox.setSelectedIndex(1);
		comboBox.setBounds(90, 380, 185, 21);
		signupPanel.add(comboBox);

		JLabel courseLabel = new JLabel("Select Course");
		courseLabel.setFont(new Font("Albertus Medium", Font.PLAIN, 10));
		courseLabel.setBounds(90, 365, 81, 13);
		signupPanel.add(courseLabel);

		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String emailValidator = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
					String email = cemailField.getText();

					Pattern emailpattern = Pattern.compile(emailValidator);
					Matcher emailmatcher = emailpattern.matcher(email);

					final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
					char[] password = cpwdField.getPassword();
					String passwordString = new String(password);

					Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
					Matcher matcher = pattern.matcher(passwordString);


					if (cnameField.getText().isEmpty() || cemailField.getText().isEmpty() || cnumField.getText().isEmpty() || new String(cpwdField.getPassword()).isEmpty()) 
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
					auth.addCredential(cnameField.getText(), cemailField.getText(),new String(cpwdField.getPassword()), cnumField.getText());
					JOptionPane.showMessageDialog(null, "account successfully created");
					frame.setVisible(false);
					new SignUp();


				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btnCreate.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		btnCreate.setBounds(48, 422, 109, 28);
		signupPanel.add(btnCreate);

		JButton btnReset = new JButton("Reset");
		btnReset.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cnameField.setText(null);
				cnumField.setText(null);
				cemailField.setText(null);
				cpwdField.setText(null);
			}
		});
		btnReset.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		btnReset.setBounds(175, 422, 109, 28);
		signupPanel.add(btnReset);

		JLabel CaccTxt = new JLabel("Already have an account?");
		CaccTxt.setFont(new Font("Tahoma", Font.PLAIN, 12));
		CaccTxt.setBounds(70, 455, 147, 22);
		signupPanel.add(CaccTxt);

		JLabel clickliTxt = new JLabel("LOG IN");
		clickliTxt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				layeredPane.removeAll();
				layeredPane.add(loginPanel);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		clickliTxt.setFont(new Font("Tahoma", Font.BOLD, 12));
		clickliTxt.setBounds(215, 455, 48, 22);
		signupPanel.add(clickliTxt);



		JLabel gif = new JLabel(new ImageIcon(getClass().getResource("../gif/mainGIF.gif")));
		gif.setBounds(345, 10, 331, 493);
		frame.getContentPane().add(gif);
	}
}
