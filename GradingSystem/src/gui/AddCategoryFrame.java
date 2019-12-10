package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import gui.grade.EditCategoryFrame;
import objects.Category;
import objects.Course;
import objects.Part;
import java.awt.Font;

public class AddCategoryFrame extends JFrame implements FrameActions{

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;
	private JLabel lblCategoryName;
	private JLabel lblNumberOfParts;
	private JLabel lblPercentageOfThis;	
	private JScrollPane scrollPane;		
	private JButton btnNext;
	private JButton btnSetParts;
	private Course course;
	private int categoryLeft;
	private JButton btnBack;

	/**
	 * Create the frame.
	 */
	public AddCategoryFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 590, 622);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		init();
		addActions();
	}
	
	public AddCategoryFrame(Course course, int categoryLeft) {
		this();
		this.course = course;
		this.categoryLeft = categoryLeft;
	}
	
	public void init(){
		lblCategoryName = new JLabel("Category name:");
		lblCategoryName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCategoryName.setBounds(50, 38, 190, 22);
		contentPane.add(lblCategoryName);
		
		lblNumberOfParts = new JLabel("Number of parts in this category:");
		lblNumberOfParts.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNumberOfParts.setBounds(50, 116, 350, 21);
		contentPane.add(lblNumberOfParts);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField.setBounds(50, 73, 466, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField_1.setBounds(49, 150, 466, 30);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		lblPercentageOfThis = new JLabel("Percentage of this category:");
		lblPercentageOfThis.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblPercentageOfThis.setBounds(49, 179, 282, 30);
		contentPane.add(lblPercentageOfThis);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField_2.setBounds(50, 212, 466, 30);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 313, 466, 171);
		contentPane.add(scrollPane);
		
		Object[][] content=new Object[1][3];
		Object[] title={"Name","Percentage","Total score"};
		table = new JTable(content,title);
		table.setFont(new Font("Tahoma", Font.PLAIN, 17));
		scrollPane.setViewportView(table);
		
		
		btnNext = new JButton("Next");
		btnNext.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNext.setBounds(423, 512, 93, 23);
		contentPane.add(btnNext);
		
		btnSetParts = new JButton("Set parts");
		btnSetParts.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnSetParts.setBounds(391, 262, 125, 23);
		contentPane.add(btnSetParts);
		
		btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnBack.setBounds(50, 511, 97, 25);
		contentPane.add(btnBack);
	}
	
	public void addActions(){
		btnSetParts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int partNum = 0;
				double cPercentage = 0;
				try {
					partNum = Integer.parseInt(textField_1.getText());
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Invalid Input for the number of parts");
					return;
				}
				try {
					cPercentage = Double.parseDouble(textField_2.getText());
					if (cPercentage > 100) {
						JOptionPane.showMessageDialog(null, "Percentage cover for a single category shall not exceed 100");
						return;
					}
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Invalid Input for the percentage of this category");
					return;
				}
				Object[][] content=new Object[partNum][3];
				Object[] title={"Name","Percentage","Total score"};
				table = new JTable(content,title);
				scrollPane.setViewportView(table);
			}
		});
		
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Capture the Information from Textfield to construct a new Category
				String categoryName = textField.getText();
				int partNum = Integer.parseInt(textField_1.getText());
				double cPercentage = Double.parseDouble(textField_2.getText());
				Category newCategory = new Category (categoryName, partNum, course.getCourseid(), cPercentage);
				
				
				//Ready A Single Part for current new Category
				for (int rowNum = 0; rowNum < table.getRowCount(); rowNum++) {
					String partName = "";
					double percentage = 0;
					double totalScore = 0;
					for (int colNum = 0; colNum <= 2; colNum++) {
						switch (colNum) {
							case 0:
								partName = (String) table.getValueAt(rowNum, colNum);
								break;
							case 1:
								try {
									percentage = Double.parseDouble((String) table.getValueAt(rowNum, colNum));
									break;
								} catch (NumberFormatException e1) {
									JOptionPane.showMessageDialog(null, "Invalid Input for the percentage of " + partName);
								}
							case 2:
								try {
									totalScore = Double.parseDouble((String) table.getValueAt(rowNum, colNum));
									break;
								} catch (NumberFormatException e1) {
									JOptionPane.showMessageDialog(null, "Invalid Input for the score of " + partName);
								}
							default:
								//Prevent from passing incorrect data to the next frame
								return;
						}						
					}
					Part newPart = new Part(partName, course.getCourseid(), totalScore, percentage);
					newCategory.addPart(newPart);
				}
				double p_PercentageSum = 0;
				for (Part p : newCategory.getPartList()) {
					p_PercentageSum += p.getPercentage();
				}
				if (p_PercentageSum > cPercentage) {
					JOptionPane.showMessageDialog(null, "Percentages of Parts DON'T add up, please CHECK");
					return;
				}
				course.addCategory(newCategory);
				
				//Check the Number of Remaining Categories to Create
				if (categoryLeft > 1) {
					categoryLeft--;
					//System.out.println(categoryLeft);
					AddCategoryFrame nextFrame = new AddCategoryFrame(course, categoryLeft);
					nextFrame.setVisible(true);
					dispose();
				} else {
					//System.out.println(course.getCategoryCount());
					double c_PercentageSum = 0;
					for (Category c : course.getCategories()) {
						c_PercentageSum += c.getPercentage();
					}
					if (c_PercentageSum > 100) {
						JOptionPane.showMessageDialog(null, "Percentages of all Categories DON'T add up, please EDIT THEM");
					}
					EditCategoryFrame nextFrame = new EditCategoryFrame(course);
					nextFrame.setVisible(true);
					dispose();
				}
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

