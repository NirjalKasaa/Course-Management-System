package userInterface.edit_course;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import userInterface.auth;

public class delcourse extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JPanel delCourse;
	JPanel delModule;
	private JTextField textField;
	private JTextField moduleIDField;

	public delcourse() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 275, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));

		JPanel mainDel = new JPanel();
		contentPane.add(mainDel, "name_24339017161700");
		mainDel.setLayout(null);

		JButton deletecourse = new JButton("Delete Course");
		deletecourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.removeAll();
				contentPane.add(delCourse);
				contentPane.repaint();
				contentPane.revalidate();
			}
		});
		deletecourse.setBounds(61, 71, 130, 21);
		mainDel.add(deletecourse);

		JButton deletemodule = new JButton("Delete Module");
		deletemodule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.removeAll();
				contentPane.add(delModule);
				contentPane.repaint();
				contentPane.revalidate();
			}
		});
		deletemodule.setBounds(61, 129, 130, 21);
		mainDel.add(deletemodule);

		JLabel lblDelete = new JLabel("Delete");
		lblDelete.setFont(new Font("Annfold", Font.PLAIN, 18));
		lblDelete.setBounds(10, 10, 195, 38);
		mainDel.add(lblDelete);

		delCourse = new JPanel();
		contentPane.add(delCourse, "name_24342836283900");
		delCourse.setLayout(null);

		JLabel lblcourseID = new JLabel("Course ID");
		lblcourseID.setBackground(new Color(240, 240, 240));
		lblcourseID.setBounds(36, 68, 68, 13);
		delCourse.add(lblcourseID);

		textField = new JTextField();
		textField.setBounds(46, 91, 135, 19);
		delCourse.add(textField);
		textField.setColumns(10);

		JButton courseDelbtn = new JButton("Delete");
		courseDelbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Can't Delete an empty course");
				}else {
					int result = JOptionPane.showConfirmDialog(courseDelbtn, "Are you sure you want to proceed?", "Confirmation", JOptionPane.YES_NO_OPTION);
					if (result == JOptionPane.YES_OPTION) {
						try {
							auth.deleteModule(textField.getText());
							JOptionPane.showMessageDialog(null, "The data was deleted successfully :))");

						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, e1);
						}
					}
				}
			}
		});
		courseDelbtn.setBounds(131, 199, 85, 21);
		delCourse.add(courseDelbtn);

		JLabel dcBack = new JLabel(new ImageIcon(getClass().getResource("../../pics/back.png")));
		dcBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				contentPane.removeAll();
				contentPane.add(mainDel);
				contentPane.repaint();
				contentPane.revalidate();
			}
		});
		dcBack.setBounds(10, 10, 30, 30);
		delCourse.add(dcBack);

		delModule = new JPanel();
		contentPane.add(delModule, "name_24617901612100");
		delModule.setLayout(null);

		JLabel lblmoduleID = new JLabel("Module ID");
		lblmoduleID.setBackground(SystemColor.menu);
		lblmoduleID.setBounds(31, 68, 68, 13);
		delModule.add(lblmoduleID);

		moduleIDField = new JTextField();
		moduleIDField.setColumns(10);
		moduleIDField.setBounds(64, 91, 135, 19);
		delModule.add(moduleIDField);

		JButton moduleDelbtn = new JButton("Delete");
		moduleDelbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(moduleIDField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Can't Delete an empty module");
				}else {
					int result = JOptionPane.showConfirmDialog(moduleDelbtn, "Are you sure you want to proceed?", "Confirmation", JOptionPane.YES_NO_OPTION);
					if (result == JOptionPane.YES_OPTION) {
						try {
							auth.deleteModule(moduleIDField.getText());
							JOptionPane.showMessageDialog(null, "The data was deleted successfully :))");

						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, e1);
						}
					}
				}
			}
		});
		moduleDelbtn.setBounds(142, 205, 85, 21);
		delModule.add(moduleDelbtn);

		JLabel dmBack = new JLabel(new ImageIcon(getClass().getResource("../../pics/back.png")));
		dmBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				contentPane.removeAll();
				contentPane.add(mainDel);
				contentPane.repaint();
				contentPane.revalidate();
			}
		});
		dmBack.setBounds(10, 10, 30, 30);
		delModule.add(dmBack);

	}
}
