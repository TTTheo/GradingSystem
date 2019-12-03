package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ManageCourseFrame extends JFrame implements FrameActions{

	private JPanel contentPane;
	private JButton btnAddCategory;
	private JButton btnAddPart;
	private JButton btnAdjustPercentage;
	/**
	 * Create the frame.
	 */
	public ManageCourseFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 315, 343);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		init();
		addActions();
	
	}
	
	public void init(){
		btnAddCategory = new JButton("Add Category");
		btnAddCategory.setBounds(67, 52, 164, 23);
		contentPane.add(btnAddCategory);
		
		btnAddPart = new JButton("Add Part");
		btnAddPart.setBounds(67, 112, 164, 23);
		contentPane.add(btnAddPart);
		
		btnAdjustPercentage = new JButton("Adjust Percentage");
		btnAdjustPercentage.setBounds(67, 174, 164, 23);
		contentPane.add(btnAdjustPercentage);
	}
	
	public void addActions(){
		btnAddCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btnAddPart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btnAdjustPercentage.addActionListener(new ActionListener() {
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
