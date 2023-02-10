//report
package userInterface.student_report;

import java.awt.Font;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dbmanager.DbManager;
import javax.swing.SwingConstants;

public class Report {

	private JFrame frame;
	private JTable table;

	public Report(DbManager db) {

		frame = new JFrame();
		frame.setBounds(100, 100, 660, 385);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);

		JLabel reportTitle = new JLabel("Student Progress Report");
		reportTitle.setFont(new Font("Annfold", Font.PLAIN, 18));
		reportTitle.setBounds(20, 20, 273, 38);
		frame.getContentPane().add(reportTitle);

		java.sql.Statement stmt;

		try {
			stmt = db.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT student_id, module_id, marks FROM results");
			DefaultTableModel model = new DefaultTableModel();
			model.addColumn("Student id");
			model.addColumn("Module id");
			model.addColumn("Marks");
			model.addColumn("Remarks");

			int passCount = 0;
			int totalModules = 8;
			while (rs.next()) {
				String studentId = rs.getString("student_id");
				String moduleId = rs.getString("module_id");
				int marks = Integer.parseInt(rs.getString("marks"));

				String remarks = "Fail";
				if (marks >= 40) {
					passCount++;
					remarks = "Pass";
				}
				model.addRow(new Object[] { studentId, moduleId, marks, remarks });
			}

			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(30, 65, 580, 235);
			scrollPane.setEnabled(false);
			frame.getContentPane().add(scrollPane);

			table = new JTable(model);
			table.setEnabled(false);
			scrollPane.setViewportView(table);

			JLabel lblPromotion = new JLabel("");
			lblPromotion.setHorizontalAlignment(SwingConstants.RIGHT);
			lblPromotion.setBounds(354, 324, 268, 14);
			frame.getContentPane().add(lblPromotion);

			int totalRows = model.getRowCount();
			if (totalRows == totalModules) {
				if (passCount >= 4) {
					lblPromotion.setText("Promoted");
				} else {
					lblPromotion.setText("Not Promoted");
				}
			} else {
				lblPromotion.setText("Marks of all modules are not published");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		frame.setVisible(true);
	}
}
