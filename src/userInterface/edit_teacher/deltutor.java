package userInterface.edit_teacher;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import userInterface.auth;

public class deltutor extends JFrame {


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtEmailField;


	public deltutor() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 275, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDeletetutor = new JLabel("Delete Tutor");
		lblDeletetutor.setFont(new Font("Annfold", Font.PLAIN, 18));
		lblDeletetutor.setBounds(10, 10, 241, 38);
		contentPane.add(lblDeletetutor);
		
		JLabel temaillbl = new JLabel("Email");
		temaillbl.setBounds(45, 90, 43, 25);
		contentPane.add(temaillbl);
		
		txtEmailField = new JTextField();
		txtEmailField.setBounds(55, 114, 134, 19);
		contentPane.add(txtEmailField);
		txtEmailField.setColumns(10);
		
		JButton tdelbtn = new JButton("Delete");
//		auth.deleteTeach(txtEmailField.getText());
		tdelbtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	if(txtEmailField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Can't Delete an empty teacher");
                }else {
                int result = JOptionPane.showConfirmDialog(tdelbtn, "Are you sure you want to proceed?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    try {
                        auth.deleteTeach(txtEmailField.getText());
                        JOptionPane.showMessageDialog(null, "The data was removed successfully :))");
                    } catch (Exception e1) {
                    	JOptionPane.showMessageDialog(null, "An error occurred.");
                    }
                    
                }

                }
            }

        });
		tdelbtn.setBounds(144, 215, 85, 21);
		contentPane.add(tdelbtn);
	}

}
