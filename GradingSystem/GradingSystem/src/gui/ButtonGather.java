package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ButtonGather extends JFrame implements FrameActions{

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JPanel panel;
	private JButton btnNewButton;
	private JButton btnCategory;;
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
		init();
		addActions();

	}
	
	public void init(){
		scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 5, 149, 246);
		contentPane.add(scrollPane);
		
		panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(null);
		
		btnNewButton = new JButton("Category1");
		btnNewButton.setBounds(10, 10, 93, 23);
		panel.add(btnNewButton);
		
		btnCategory = new JButton("Category2");
		btnCategory.setBounds(10, 53, 93, 23);
		panel.add(btnCategory);
	}
	
	public void addActions(){
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btnCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
	}
	
	public void alert(String message){
        JOptionPane.showMessageDialog(null, message);
    }

	// Open the semester frame next
	public void openNext() {
		SemesterFrame next = new SemesterFrame();
		next.setVisible(true);
		dispose();
	}

	// This is the first window, no previous window exists
	public void openPrevious() {
		dispose();
	}

}
