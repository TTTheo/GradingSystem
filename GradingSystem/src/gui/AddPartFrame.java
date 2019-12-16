package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import backend.Backend;
import dao.CategoryDao;
import dao.PartDao;
import gui.grade.EditCategoryFrame;
import objects.Category;
import objects.Course;
import objects.Part;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.SystemColor;

public class AddPartFrame extends JFrame implements FrameActions{

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblCategory;	
	private JComboBox<String> comboBox;	
	private JLabel lblNameOfPart;		
	private JLabel lblPercentage;
	private JLabel lblFullScore;
	private JButton btnAdd;
	private JButton btnNewButton;
	private JTextField textField_2;
	private Course course;
	private String courseID;
	CategoryDao categoryDAO = new CategoryDao();
	PartDao partDAO = new PartDao();
	ArrayList<Category> categoryList = new ArrayList<Category>();
	ArrayList<Part> partList = new ArrayList<Part>();

	private Backend backend;
	/**
	 * Create the frame.
	 */
	public AddPartFrame(Backend c) {
		backend = c;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 589, 609);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		try {
			categoryList = c.getCategories(c.getCourse());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		init();
		addActions();
	}
	
	public void init(){
		lblCategory = new JLabel("Category:");
		lblCategory.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCategory.setBounds(65, 62, 104, 23);
		contentPane.add(lblCategory);
		
		comboBox = new JComboBox<String>();
		for (Category c : categoryList) {
			comboBox.addItem(c.getName());
			//System.out.println("Executed~~");
		}
		comboBox.setBounds(65, 98, 423, 31);
		contentPane.add(comboBox);
		
		lblNameOfPart = new JLabel("Name of Part:");
		lblNameOfPart.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNameOfPart.setBounds(65, 160, 155, 28);
		contentPane.add(lblNameOfPart);
		
		textField = new JTextField();
		textField.setBounds(65, 201, 423, 31);
		contentPane.add(textField);
		textField.setColumns(10);
		
		lblPercentage = new JLabel("Percentage:");
		lblPercentage.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblPercentage.setBounds(67, 258, 118, 23);
		contentPane.add(lblPercentage);
		
		textField_1 = new JTextField();
		textField_1.setBounds(67, 294, 421, 31);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		lblFullScore = new JLabel("Full Score:");
		lblFullScore.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblFullScore.setBounds(65, 335, 97, 31);
		contentPane.add(lblFullScore);
		
		textField_2 = new JTextField();
		textField_2.setBounds(65, 382, 423, 31);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		btnAdd = new JButton("Add");
		btnAdd.setBackground(SystemColor.controlHighlight);
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnAdd.setBounds(395, 435, 93, 23);
		contentPane.add(btnAdd);
		
		btnNewButton = new JButton("Back");
		btnNewButton.setBackground(SystemColor.controlHighlight);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton.setBounds(65, 434, 97, 25);
		contentPane.add(btnNewButton);
	}
	
	public void addActions(){
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String categoryName = (String) comboBox.getSelectedItem();
				for (Category c : categoryList) {
					if (c.getName().equals(categoryName)) {
						Part newPart = new Part(textField.getText(), 
												c.getCid(), 
												Double.parseDouble(textField_2.getText()), 
												Double.parseDouble(textField_1.getText()));
						try {
							partDAO.insert(newPart);
						} catch (SQLException e1) {
							System.out.println("Failed to insert the NEW PART into the DB!");
						}
					}
				}
				EditCategoryFrame nextFrame = new EditCategoryFrame(backend);
				nextFrame.setVisible(true);
				dispose();
			}
		});
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditCategoryFrame nextFrame = new EditCategoryFrame(backend);
				nextFrame.setVisible(true);
				dispose();
			}
		});
	}
	
	public void alert(String message){
        JOptionPane.showMessageDialog(null, message);
    }

	public void openNext() {
		dispose();
	}

	// This is the first window, no previous window exists
	public void openPrevious() {
		dispose();
	}
}
