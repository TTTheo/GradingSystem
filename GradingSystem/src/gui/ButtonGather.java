package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.SwingConstants;

public class ButtonGather extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public ButtonGather() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 175, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 5, 149, 246);
		contentPane.add(scrollPane);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Category1");
		btnNewButton.setBounds(10, 10, 93, 23);
		panel.add(btnNewButton);
		
		JButton btnCategory = new JButton("Category2");
		btnCategory.setBounds(10, 53, 93, 23);
		panel.add(btnCategory);
	}

}
