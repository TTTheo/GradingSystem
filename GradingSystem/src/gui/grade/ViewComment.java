package gui.grade;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class ViewComment extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public ViewComment(String comment) {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 584, 437);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblComment = new JLabel("Comment");
		lblComment.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblComment.setBounds(65, 54, 445, 243);
		contentPane.add(lblComment);
		lblComment.setText(comment);
		
		JButton btnYes = new JButton("Back");
		btnYes.setBackground(SystemColor.controlHighlight);
		btnYes.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnYes.setBounds(241, 326, 97, 38);
		contentPane.add(btnYes);
	}
}
