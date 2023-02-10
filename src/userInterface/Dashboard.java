package userInterface;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import dbmanager.DbManager;
import userInterface.edit_course.addcourse;
import userInterface.edit_course.delcourse;
import userInterface.edit_teacher.addtutor;
import userInterface.edit_teacher.deltutor;
import userInterface.student_report.CreateReport;
import userInterface.student_report.Report;

public class Dashboard extends JFrame{

	DbManager db;

	private static final long serialVersionUID = 1L;
	private JTable stdTable;
	private JTable tutorTable;
	private JTable coursetable;
	private JTable moduletable;

	public Dashboard(DbManager db, String role) {
		this.db=db;
		JFrame frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.getContentPane().setLayout(null);
		frame.setBounds(100, 100, 900, 475);
		frame.setTitle("Course Management System");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		JLabel logo = new JLabel(new ImageIcon(getClass().getResource("../pics/Logo.png")));
		logo.setBounds(10, 10, 170, 70);
		frame.getContentPane().add(logo);

		JPanel layout = new JPanel();
		layout.setBounds(10, 90, 669, 338);
		frame.getContentPane().add(layout);
		layout.setLayout(new CardLayout(0, 0));

		JPanel home = 	new JPanel();
		home.setBackground(new Color(255, 255, 255));
		layout.add(home, "name_36331726322300");
		home.setLayout(null);

		JPanel coursePane = new JPanel();
		coursePane.setBackground(new Color(255, 255, 255));
		layout.add(coursePane, "name_36608701922000");
		coursePane.setLayout(null);

		JLabel cback = new JLabel(new ImageIcon(getClass().getResource("../pics/back.png")));
		cback.setForeground(new Color(0, 0, 0));
		cback.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				layout.removeAll();
				layout.add(home);
				layout.repaint();
				layout.revalidate();
			}
		});
		cback.setEnabled(false);
		cback.setBounds(0, 0, 30, 30);
		coursePane.add(cback);

		JLabel cLabel = new JLabel("COURSES");
		cLabel.setHorizontalAlignment(SwingConstants.LEFT);
		cLabel.setFont(new Font("Annfold", Font.PLAIN, 18));
		cLabel.setBounds(268, 0, 118, 30);
		coursePane.add(cLabel);

		java.sql.Statement stmt;
		try {
			stmt = db.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT course_id, course_name FROM courses");
			DefaultTableModel model = new DefaultTableModel();
			model.addColumn("CourseID");
			model.addColumn("Course Name");

			while (rs.next()) {
				model.addRow(new Object[]{rs.getString("course_id"),rs.getString("course_name")});
			}
			JScrollPane CscrollPane = new JScrollPane();
			CscrollPane.setBounds(10, 40, 649, 90);
			CscrollPane.setEnabled(false);
			coursePane.add(CscrollPane);

			coursetable = new JTable(model);
			coursetable.setEnabled(false);
			CscrollPane.setViewportView(coursetable);

		} catch (SQLException e1) {
			e1.printStackTrace();
		}		


		JLabel mLabel = new JLabel("MODULES");
		mLabel.setHorizontalAlignment(SwingConstants.LEFT);
		mLabel.setFont(new Font("Annfold", Font.PLAIN, 18));
		mLabel.setBounds(268, 140, 118, 30);
		coursePane.add(mLabel);

		try {
			stmt = db.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT module_id, module_name, course_id FROM modules");
			DefaultTableModel model = new DefaultTableModel();
			model.addColumn("Course ID");
			model.addColumn("Module ID");
			model.addColumn("Module Name");

			while (rs.next()) {
				model.addRow(new Object[]{rs.getString("course_id"), rs.getString("module_id"),rs.getString("module_name")});
			}
			JScrollPane MscrollPane = new JScrollPane();
			MscrollPane.setBounds(10, 170, 649, 158);
			MscrollPane.setEnabled(false);
			coursePane.add(MscrollPane);

			moduletable = new JTable(model);
			moduletable.setEnabled(false);
			MscrollPane.setViewportView(moduletable);

		} catch (SQLException e1) {
			e1.printStackTrace();
		}		

		JLabel tadd = new JLabel(new ImageIcon(getClass().getResource("../pics/add.png")));
		tadd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				addtutor addt= new addtutor();
				addt.setVisible(true);
			}
		});
		tadd.setForeground(new Color(0, 0, 0));
		tadd.setEnabled(false);
		tadd.setBounds(640, 5, 25, 25);		

		JLabel tdel = new JLabel(new ImageIcon(getClass().getResource("../pics/bin.png")));
		tdel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				deltutor delt= new deltutor();
				delt.setVisible(true);
			}
		});
		tdel.setForeground(Color.BLACK);
		tdel.setEnabled(false);
		tdel.setBounds(605, 5, 25, 25);

		JPanel stdPane = new JPanel();
		stdPane.setBackground(new Color(255, 255, 255));
		layout.add(stdPane, "name_36631030660100");
		stdPane.setLayout(null);

		JLabel sback = new JLabel(new ImageIcon(getClass().getResource("../pics/back.png")));
		sback.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				layout.removeAll();
				layout.add(home);
				layout.repaint();
				layout.revalidate();
			}
		});
		sback.setForeground(Color.BLACK);
		sback.setEnabled(false);
		sback.setBounds(0, 0, 30, 30);
		stdPane.add(sback);

		JLabel sLabel = new JLabel("STUDENTS");
		sLabel.setHorizontalAlignment(SwingConstants.LEFT);
		sLabel.setFont(new Font("Annfold", Font.PLAIN, 18));
		sLabel.setBounds(268, 0, 215, 30);
		stdPane.add(sLabel);

		try {

			stmt = db.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, fullName, email, phoneNo FROM auth WHERE role='Student'");
			DefaultTableModel model = new DefaultTableModel();
			model.addColumn("id");
			model.addColumn("Full Name");
			model.addColumn("Email");
			model.addColumn("PhoneNo");

			while (rs.next()) {
				model.addRow(new Object[]{rs.getString("id"),rs.getString("fullName"), rs.getString("email"),
						rs.getString("phoneNo")});
			}
			JScrollPane SscrollPane = new JScrollPane();
			SscrollPane.setBounds(10, 40, 649, 288);
			SscrollPane.setEnabled(false);
			stdPane.add(SscrollPane);

			stdTable = new JTable(model);
			stdTable.setEnabled(false);
			SscrollPane.setViewportView(stdTable);

		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		JButton creatrptBtn = new JButton("Create Report");
		creatrptBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateReport cr= new CreateReport();
				cr.setVisible(true);
			}
		});
		creatrptBtn.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		creatrptBtn.setBounds(533, 9, 126, 21);



		JPanel tutorPane = new JPanel();
		tutorPane.setBackground(new Color(255, 255, 255));
		layout.add(tutorPane, "name_36649183250200");
		tutorPane.setLayout(null);

		JLabel tback = new JLabel(new ImageIcon(getClass().getResource("../pics/back.png")));
		tback.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				layout.removeAll();
				layout.add(home);
				layout.repaint();
				layout.revalidate();
			}
		});
		tback.setForeground(Color.BLACK);
		tback.setEnabled(false);
		tback.setBounds(0, 0, 30, 30);
		tutorPane.add(tback);

		JLabel tLabel = new JLabel("TUTORS");
		tLabel.setHorizontalAlignment(SwingConstants.LEFT);
		tLabel.setFont(new Font("Annfold", Font.PLAIN, 18));
		tLabel.setBounds(272, 0, 215, 30);
		tutorPane.add(tLabel);

		try {

			stmt = db.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, fullName, email, phoneNo FROM auth WHERE role='Teacher'");
			DefaultTableModel model = new DefaultTableModel();
			model.addColumn("id");
			model.addColumn("Full Name");
			model.addColumn("Email");
			model.addColumn("PhoneNo");

			while (rs.next()) {
//				System.out.println(rs.getString("fullName"));
//				System.out.println(rs.getString("email"));
				model.addRow(new Object[]{rs.getString("id"),rs.getString("fullName"), rs.getString("email"),
						rs.getString("phoneNo")});
			}
			JScrollPane TscrollPane = new JScrollPane();
			TscrollPane.setBounds(10, 40, 649, 288);
			TscrollPane.setEnabled(false);
			tutorPane.add(TscrollPane);

			tutorTable = new JTable(model);
			tutorTable.setEnabled(false);
			TscrollPane.setViewportView(tutorTable);

		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		JLabel cdel = new JLabel(new ImageIcon(getClass().getResource("../pics/bin.png")));
		cdel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				delcourse tdel= new delcourse();
				tdel.setVisible(true);
			}
		});
		cdel.setForeground(Color.BLACK);
		cdel.setEnabled(false);
		cdel.setBounds(602, 5, 25, 25);

		JLabel cadd = new JLabel(new ImageIcon(getClass().getResource("../pics/add.png")));
		cadd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				addcourse addc= new addcourse();
				addc.setVisible(true);
			}
		});
		cadd.setForeground(Color.BLACK);
		cadd.setEnabled(false);
		cadd.setBounds(634, 5, 25, 25);

		JLabel course = new JLabel(new ImageIcon(getClass().getResource("../pics/book.png")));
		course.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				layout.removeAll();
				layout.add(coursePane);
				layout.repaint();
				layout.revalidate();
			}
		});
		course.setBounds(10, 10, 200, 318);
		home.add(course);
		course.setBorder(new LineBorder(new Color(0, 0, 0)));

		JLabel students = new JLabel(new ImageIcon(getClass().getResource("../pics/std.png")));
		students.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				layout.removeAll();
				layout.add(stdPane);
				layout.repaint();
				layout.revalidate();
			}
		});
		students.setBounds(235, 10, 200, 318);
		home.add(students);
		students.setBorder(new LineBorder(new Color(0, 0, 0)));

		JLabel tutor = new JLabel(new ImageIcon(getClass().getResource("../pics/teach.png")));
		tutor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				layout.removeAll();
				layout.add(tutorPane);
				layout.repaint();
				layout.revalidate();
			}
		});
		tutor.setBounds(460, 10, 200, 318);
		home.add(tutor);
		tutor.setBorder(new LineBorder(new Color(0, 0, 0)));

		JLabel courseTitle = new JLabel("Modules");
		courseTitle.setFont(new Font("Amphion", Font.PLAIN, 20));
		courseTitle.setHorizontalAlignment(SwingConstants.CENTER);
		courseTitle.setBounds(10, 285, 200, 43);
		home.add(courseTitle);

		JLabel stdTitle = new JLabel("Students");
		stdTitle.setHorizontalAlignment(SwingConstants.CENTER);
		stdTitle.setFont(new Font("Amphion", Font.PLAIN, 20));
		stdTitle.setBounds(235, 285, 200, 43);
		home.add(stdTitle);

		JLabel teachTitle = new JLabel("Tutors");
		teachTitle.setHorizontalAlignment(SwingConstants.CENTER);
		teachTitle.setFont(new Font("Amphion", Font.PLAIN, 20));
		teachTitle.setBounds(460, 285, 200, 43);
		home.add(teachTitle);



		JPanel notification = new JPanel();
		notification.setBounds(684, 90, 192, 338);
		frame.getContentPane().add(notification);
		notification.setLayout(null);

		JLabel notifLabel = new JLabel("Notifications");
		notifLabel.setFont(new Font("Annfold", Font.PLAIN, 18));
		notifLabel.setBounds(51, 0, 141, 30);
		notification.add(notifLabel);

		JLabel notifIcon = new JLabel(new ImageIcon(getClass().getResource("../pics/notif.png")));
		notifIcon.setBounds(2, 0, 30, 30);
		notification.add(notifIcon);

		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(0, 0, 0));
		separator.setBounds(50, 25, 115, 2);
		notification.add(separator);

		JLabel lblNewLabel = new JLabel("No new notifications.");
		lblNewLabel.setForeground(new Color(128, 128, 128));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(2, 127, 190, 38);
		notification.add(lblNewLabel);

		JLabel logout = new JLabel(new ImageIcon(getClass().getResource("../pics/lout.png")));
		logout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				new SignUp();

			}
		});
		logout.setBounds(833, 10, 30, 30);
		frame.getContentPane().add(logout);

		JLabel settings = new JLabel(new ImageIcon(getClass().getResource("../pics/settings.png")));
		settings.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Settings();
			}
		});
		settings.setBounds(785, 10, 30, 30);
		frame.getContentPane().add(settings);

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("MMMMM dd, yyyy");
		JLabel dateLabel = new JLabel(sdf.format(cal.getTime()));
		dateLabel.setForeground(new Color(0, 0, 0));
		dateLabel.setFont(new Font("Ink Free", Font.PLAIN, 15));
		dateLabel.setBounds(726, 50, 150, 21);
		frame.getContentPane().add(dateLabel);

		JLabel result = new JLabel(new ImageIcon(getClass().getResource("../pics/result.png")));
		result.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Report(db);
			}
		});
		result.setBounds(738, 10, 30, 30);



		if(role.equals("Student"))
		{
			frame.getContentPane().add(result);

		}
		if(role.equals("Admin"))
		{
			tutorPane.add(tadd);
			tutorPane.add(tdel);
			coursePane.add(cadd);
			coursePane.add(cdel);
		}
		if(role.equals("Teacher"))
		{
			stdPane.add(creatrptBtn);
			frame.getContentPane().add(result);
		}


	}
}
