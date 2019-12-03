package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PickPartFrame extends JFrame implements FrameActions{

	private JPanel contentPane;
	private JLabel lblPickACategory;	
	private JLabel lblPickAPart;
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private JButton btnNext;

	/**
	 * Create the frame.
	 */
	public PickPartFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 361, 407);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		init();
		addActions();
	}
	
	public void init(){
		lblPickACategory = new JLabel("Pick a Category:");
		lblPickACategory.setBounds(50, 53, 150, 15);
		contentPane.add(lblPickACategory);
		
		lblPickAPart = new JLabel("Pick a Part:");
		lblPickAPart.setBounds(50, 154, 150, 15);
		contentPane.add(lblPickAPart);
		
		comboBox = new JComboBox();
		comboBox.setBounds(50, 78, 244, 21);
		contentPane.add(comboBox);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setBounds(50, 179, 244, 21);
		contentPane.add(comboBox_1);
		
		btnNext = new JButton("Next");
		btnNext.setBounds(211, 307, 93, 23);
		contentPane.add(btnNext);
	}
	
	public void addActions(){
		btnNext.addActionListener(new ActionListener() {
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
