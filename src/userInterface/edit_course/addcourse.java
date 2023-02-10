package userInterface.edit_course;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import userInterface.auth;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class addcourse extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JPanel addCourse;
	JPanel addModule;
	private JLabel lblcourseName;
	private JTextField addCourseField;
	private JButton addC;
	private JLabel lblmoduleName;
	private JTextField modNameField;
	private JLabel lblmoduleType;
	private JTextField modTypeField;
	private JLabel lblCourseId;
	private JTextField crsIDField;
	private JButton addM;
	private JLabel lblAdd;
	private JLabel amBack;

	public addcourse() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 275, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));

		JPanel mainpanel = new JPanel();
		contentPane.add(mainpanel, "name_18389480992200");
		mainpanel.setLayout(null);

		JButton addcourse = new JButton("Add Course");
		addcourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.removeAll();
				contentPane.add(addCourse);
				contentPane.repaint();
				contentPane.revalidate();
			}
		});
		addcourse.setBounds(65, 75, 110, 21);
		mainpanel.add(addcourse);

		JButton addmodule = new JButton("Add Module");
		addmodule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.removeAll();
				contentPane.add(addModule);
				contentPane.repaint();
				contentPane.revalidate();
			}
		});
		addmodule.setBounds(65, 125, 110, 21);
		mainpanel.add(addmodule);

		lblAdd = new JLabel("Add");
		lblAdd.setFont(new Font("Annfold", Font.PLAIN, 18));
		lblAdd.setBounds(10, 10, 186, 38);
		mainpanel.add(lblAdd);

		addCourse = new JPanel();
		contentPane.add(addCourse, "name_18409750137600");
		addCourse.setLayout(null);

		lblcourseName = new JLabel("Course Name");
		lblcourseName.setBounds(39, 68, 81, 20);
		addCourse.add(lblcourseName);

		addCourseField = new JTextField();
		addCourseField.setBounds(49, 87, 143, 19);
		addCourse.add(addCourseField);
		addCourseField.setColumns(10);

		addC = new JButton("add");
		addC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(addCourseField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Can't add an empty course");
				}else {
					try {
						auth.addCourse(addCourseField.getText());
						JOptionPane.showMessageDialog(null, "The data was added successfully :))");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		addC.setBounds(141, 196, 85, 21);
		addCourse.add(addC);

		JLabel acBack = new JLabel(new ImageIcon(getClass().getResource("../../pics/back.png")));
		acBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				contentPane.removeAll();
				contentPane.add(mainpanel);
				contentPane.repaint();
				contentPane.revalidate();
			}
		});
		acBack.setBounds(10, 10, 30, 30);
		addCourse.add(acBack);

		addModule = new JPanel();
		contentPane.add(addModule, "name_18435071754600");
		addModule.setLayout(null);

		lblmoduleName = new JLabel("Module Name");
		lblmoduleName.setBounds(30, 53, 79, 13);
		addModule.add(lblmoduleName);

		modNameField = new JTextField();
		modNameField.setBounds(44, 70, 149, 19);
		addModule.add(modNameField);
		modNameField.setColumns(10);

		lblmoduleType = new JLabel("Module Type");
		lblmoduleType.setBounds(30, 99, 79, 13);
		addModule.add(lblmoduleType);

		modTypeField = new JTextField();
		modTypeField.setColumns(10);
		modTypeField.setBounds(44, 115, 149, 19);
		addModule.add(modTypeField);

		lblCourseId = new JLabel("Course ID");
		lblCourseId.setBounds(30, 144, 79, 13);
		addModule.add(lblCourseId);

		crsIDField = new JTextField();
		crsIDField.setColumns(10);
		crsIDField.setBounds(44, 162, 149, 19);
		addModule.add(crsIDField);

		addM = new JButton("Add");
		addM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(modNameField.getText().isEmpty() || modTypeField.getText().isEmpty() || crsIDField.getText().isEmpty()) {

					JOptionPane.showMessageDialog(null, "All fields are required.");
				}else {
					try {
						auth.addModule(modNameField.getText(),modTypeField.getText(), crsIDField.getText());
						JOptionPane.showMessageDialog(null, "The data was added successfully :))");
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(),null,JOptionPane.ERROR_MESSAGE);
					}

				}
			}
		});
		addM.setBounds(144, 209, 85, 21);
		addModule.add(addM);

		amBack = new JLabel(new ImageIcon(getClass().getResource("../../pics/back.png")));
		amBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				contentPane.removeAll();
				contentPane.add(mainpanel);
				contentPane.repaint();
				contentPane.revalidate();
			}
		});
		amBack.setBounds(10, 10, 30, 30);
		addModule.add(amBack);
	}
}
