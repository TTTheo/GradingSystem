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
import java.awt.Font;

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
		setBounds(100, 100, 532, 524);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		init();
		addActions();
	
	}
	
	public void init(){
		btnAddCategory = new JButton("Add Category");
		btnAddCategory.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnAddCategory.setBounds(167, 111, 188, 37);
		contentPane.add(btnAddCategory);
		
		btnAddPart = new JButton("Add Part");
		btnAddPart.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnAddPart.setBounds(167, 209, 188, 37);
		contentPane.add(btnAddPart);
		
		btnAdjustPercentage = new JButton("Adjust Percentage");
		btnAdjustPercentage.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnAdjustPercentage.setBounds(167, 307, 188, 37);
		contentPane.add(btnAdjustPercentage);
	}
	
	public void addActions(){
		btnAddCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddCategoryFrame addCategory=new AddCategoryFrame();
				addCategory.setVisible(true);
			}
		});
		
		btnAddPart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				AddPartFrame addPart=new AddPartFrame();
//				addPart.setVisible(true);
			}
		});
		
		btnAdjustPercentage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdjustPercentFrame adjust=new AdjustPercentFrame();
				adjust.setVisible(true);
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
