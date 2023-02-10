package userInterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;

public class Settings {

	private JFrame frame;
	private JTextField oldPwdF;
	private JTextField newPwdF;

	public Settings() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 670, 402);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setTitle("Settings");
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);

		JLabel settings = new JLabel(new ImageIcon(getClass().getResource("../pics/settings.png")));
		settings.setBounds(10, 10, 30, 30);
		frame.getContentPane().add(settings);

		JLabel sLabel = new JLabel("  SETTINGS");
		sLabel.setFont(new Font("Annfold", Font.PLAIN, 18));
		sLabel.setBounds(40, 10, 221, 30);
		frame.getContentPane().add(sLabel);

		JLabel lblSecurity = new JLabel("Security  & Login");
		lblSecurity.setFont(new Font("Amphion", Font.PLAIN, 15));
		lblSecurity.setBounds(20, 50, 205, 13);
		frame.getContentPane().add(lblSecurity);

		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(new Color(160, 160, 160));
		separator_2.setBounds(20, 65, 610, 2);
		frame.getContentPane().add(separator_2);

		JLabel oldPwdlbl = new JLabel("Old Password");
		oldPwdlbl.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		oldPwdlbl.setBounds(30, 91, 75, 15);
		frame.getContentPane().add(oldPwdlbl);

		JLabel newPwdlbl = new JLabel("New Password");
		newPwdlbl.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		newPwdlbl.setBounds(30, 125, 75, 15);
		frame.getContentPane().add(newPwdlbl);

		newPwdF = new JTextField();
		newPwdF.setColumns(10);
		newPwdF.setBounds(105, 120, 140, 20);
		frame.getContentPane().add(newPwdF);

		oldPwdF = new JTextField();
		oldPwdF.setBounds(105, 86, 140, 20);
		frame.getContentPane().add(oldPwdF);
		oldPwdF.setColumns(10);

		JButton changepwdBtn = new JButton("Change Password");
		changepwdBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					//auth Ah = new auth(DbManager.getInstance());
					auth.editPassword(newPwdF.getText(), oldPwdF.getText());
					JOptionPane.showMessageDialog(null, "Password changed successfully :))");
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null, "an error occured while changing password");
				}

			}
		});
		changepwdBtn.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		changepwdBtn.setBounds(30, 155, 135, 21);
		frame.getContentPane().add(changepwdBtn);

		JLabel lblHelpSupport = new JLabel("Help & Support");
		lblHelpSupport.setFont(new Font("Amphion", Font.PLAIN, 15));
		lblHelpSupport.setBounds(20, 200, 205, 13);
		frame.getContentPane().add(lblHelpSupport);

		JSeparator separator_2_1 = new JSeparator();
		separator_2_1.setForeground(SystemColor.controlShadow);
		separator_2_1.setBounds(20, 215, 610, 2);
		frame.getContentPane().add(separator_2_1);

		JLabel lblcantact = new JLabel("Contact Us:");
		lblcantact.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblcantact.setBounds(30, 225, 75, 13);
		frame.getContentPane().add(lblcantact);

		JLabel lblemail = new JLabel("Email : info@heraldcolege.edu.np");
		lblemail.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblemail.setBounds(40, 245, 255, 13);
		frame.getContentPane().add(lblemail);

		JLabel lblpno = new JLabel("Phone Number :");
		lblpno.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblpno.setBounds(40, 268, 94, 13);
		frame.getContentPane().add(lblpno);

		JLabel lblpnum1 = new JLabel("+977 9801022637");
		lblpnum1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblpnum1.setBounds(70, 291, 94, 13);
		frame.getContentPane().add(lblpnum1);

		JLabel lblpnum2 = new JLabel("+977 01-5970120");
		lblpnum2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblpnum2.setBounds(70, 314, 94, 13);
		frame.getContentPane().add(lblpnum2);

		JLabel lblpnum3 = new JLabel("+977 9801000078");
		lblpnum3.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblpnum3.setBounds(70, 337, 94, 13);
		frame.getContentPane().add(lblpnum3);

		frame.setVisible(true);
	}
}
