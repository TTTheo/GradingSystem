package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import backend.Backend;
import gui.grade.RecordGradeFrame;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import objects.Category;
import objects.Course;
import objects.Part;
import java.awt.Font;
import java.awt.SystemColor;

public class PickPartFrame extends JFrame implements FrameActions{

	private JPanel contentPane;
	private JLabel lblPickACategory;	
	private JLabel lblPickAPart;
	private JComboBox comboBox;   // Displays all categories of this course
	private JComboBox comboBox_1; // Displays all parts of selected category
	private JButton btnNext;
	private JButton btnCancel;
	private Course course;
	private Category category_c;  // Selected category
	private Part part_p; 		  // Selected part

	private ArrayList<Category> categories =new ArrayList<Category>();
	private ArrayList<Part> parts = new ArrayList<Part>();
	private Backend backend;
	/**
	 * Create the frame.
	 */
	public PickPartFrame(Backend backend) {
	    this.backend = backend;
		course=backend.getCourse();

		setBounds(100, 100, 572, 589);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		init();
		addActions();
	}
	
	public void init(){
		lblPickACategory = new JLabel("Pick a Category:");
		lblPickACategory.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblPickACategory.setBounds(110, 79, 150, 23);
		contentPane.add(lblPickACategory);
		
		lblPickAPart = new JLabel("Pick a Part:");
		lblPickAPart.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblPickAPart.setBounds(117, 208, 150, 23);
		contentPane.add(lblPickAPart);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 17));
		comboBox.setBounds(110, 130, 330, 33);
		contentPane.add(comboBox);

		// Add all categories to combo box
		try {
			categories = backend.getCategories(course);
		} catch (SQLException e) {
			alert(e.toString());
			e.printStackTrace();
		}
		for(Category c: categories) {
			comboBox.addItem(c.getName());
		}

		comboBox_1 = new JComboBox();
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		comboBox_1.setBounds(112, 256, 328, 33);
		contentPane.add(comboBox_1);

		btnNext = new JButton("Next");
		btnNext.setBackground(SystemColor.controlHighlight);
		btnNext.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNext.setBounds(334, 368, 106, 33);
		contentPane.add(btnNext);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBackground(SystemColor.controlHighlight);
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnCancel.setBounds(110, 368, 106, 33);
		contentPane.add(btnCancel);
	}
	
	public void addActions(){
	   	// Updates the combo boxes whenever a category is selected
		comboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				for (Category category: categories) {
					if(comboBox.getSelectedItem().equals(category.getName())){
						parts = category.getPartList();
					}
				}

				comboBox_1.removeAllItems();
				for (Part part: parts) {
					comboBox_1.addItem(part.getName());
				}
			}
		});

		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String CategoryName=comboBox.getSelectedItem().toString();
				String partName=comboBox_1.getSelectedItem().toString();
				int pid;
				for(Part part: parts) {
					if(part.getName().equals(partName)) {
						part_p = part;
						break;
					}
				}

				for(Category category: categories) {
					if(category.getName().equals(CategoryName)) {
						category_c= category;
						break;
					}
				}

				backend.setCategory(category_c);
				backend.setPart(part_p);
				openNext();
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openPrevious();
			}
		});
	}
	
	public void alert(String message){
        JOptionPane.showMessageDialog(null, message);
    }

	// Open the semester frame next
	public void openNext() {
		RecordGradeFrame next = new RecordGradeFrame(backend);
		next.setVisible(true);
		dispose();
	}

	public void openPrevious() {
		dispose();
	}
}
