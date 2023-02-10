package userInterface.student_report;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import userInterface.auth;

public class CreateReport extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField stdID;
	private JTextField modID;
	private JTextField textField;

	public CreateReport() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 270, 266);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCreateProgressReport = new JLabel("Create Progress Report");
		lblCreateProgressReport.setFont(new Font("Annfold", Font.PLAIN, 18));
		lblCreateProgressReport.setBounds(10, 10, 236, 38);
		contentPane.add(lblCreateProgressReport);

		JLabel lblStdId = new JLabel("Student ID");
		lblStdId.setFont(new Font("Albertus Medium", Font.PLAIN, 12));
		lblStdId.setBounds(20, 58, 75, 20);
		contentPane.add(lblStdId);

		stdID = new JTextField();
		stdID.setBounds(115, 58, 96, 19);
		contentPane.add(stdID);
		stdID.setColumns(10);

		JLabel lblModuleId = new JLabel("Module ID");
		lblModuleId.setFont(new Font("Albertus Medium", Font.PLAIN, 12));
		lblModuleId.setBounds(20, 101, 75, 20);
		contentPane.add(lblModuleId);

		modID = new JTextField();
		modID.setColumns(10);
		modID.setBounds(115, 103, 96, 19);
		contentPane.add(modID);

		JLabel lblMarks = new JLabel("Marks");
		lblMarks.setFont(new Font("Albertus Medium", Font.PLAIN, 12));
		lblMarks.setBounds(20, 144, 75, 20);
		contentPane.add(lblMarks);

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(115, 146, 96, 19);
		contentPane.add(textField);

		JButton button = new JButton("New button");
		button.setBounds(126, 198, 52, -3);
		contentPane.add(button);

		JButton createBtn = new JButton("Create");
		createBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					auth.addResults(stdID.getText(),modID.getText(),textField.getText());
					JOptionPane.showMessageDialog(null, "The report was created successfully.");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null,"An error occured while generating result.");
				}
			}

		});
		createBtn.setBounds(126, 198, 85, 21);
		contentPane.add(createBtn);
	}
}
