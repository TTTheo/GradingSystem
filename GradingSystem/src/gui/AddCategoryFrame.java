package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import backend.Backend;
import dao.CategoryDao;
import dao.PartDao;
import gui.grade.EditCategoryFrame;
import gui.grade.ViewGradeFrame;
import objects.Category;
import objects.Course;
import objects.Part;
import java.awt.Font;
import java.awt.SystemColor;

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
	private JButton btnBack;

	private Backend backend;
	private Course course;
	private int categoryLeft;

	public AddCategoryFrame(Backend backend, Course course, int categoryLeft) {
		this.backend = backend;
		this.backend.setCourse(course);
		this.course = course;
		this.categoryLeft = categoryLeft;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 590, 622);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		init();
		addActions();
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
		btnNext.setBackground(SystemColor.controlHighlight);
		btnNext.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNext.setBounds(423, 534, 93, 23);
		contentPane.add(btnNext);
		
		btnSetParts = new JButton("Set parts");
		btnSetParts.setBackground(SystemColor.controlHighlight);
		btnSetParts.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnSetParts.setBounds(391, 262, 125, 30);
		contentPane.add(btnSetParts);
		
		btnBack = new JButton("Back");
		btnBack.setBackground(SystemColor.controlHighlight);
		
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnBack.setBounds(50, 533, 97, 24);
		contentPane.add(btnBack);
		
		JLabel lblNewLabel = new JLabel("Category Left to Create: " + (categoryLeft - 1));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(308, 497, 208, 30);
		contentPane.add(lblNewLabel);
	}
	
	public void addActions(){
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openPrevious();
			}
		});
		
		btnSetParts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int partNum = 0;
				double cPercentage = 0;
				try {
					partNum = Integer.parseInt(textField_1.getText());
				} catch (NumberFormatException e1) {
					alert("Invalid Input for the number of parts");
					return;
				}
				try {
					cPercentage = Double.parseDouble(textField_2.getText());
					if (cPercentage > 100) {
						alert("Percentage cover for a single category shall not exceed 100");
						return;
					}
				} catch (NumberFormatException e1) {
					alert("Invalid Input for the percentage of this category");
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
				// Capture the Information from Textfield to construct a new Category
				String categoryName = textField.getText();
				int partNum = Integer.parseInt(textField_1.getText());
				double cPercentage = Double.parseDouble(textField_2.getText());

				Category newCategory = new Category (categoryName, partNum, course.getCourseId(), cPercentage);
				try {
					backend.addCategory(newCategory);
				} catch (SQLException e1) {
				    alert(e1.toString());
					e1.printStackTrace();
				}
				
				//Ready A Single Part for current new Category
				for (int rowNum = 0; rowNum < table.getRowCount(); rowNum++) {
					String partName = "";
					double percentage = 0;
					double totalScore = 0;
					PartDao p_DAO = new PartDao();
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
									alert("Invalid Input for the percentage of " + partName);
								}
							case 2:
								try {
									totalScore = Double.parseDouble((String) table.getValueAt(rowNum, colNum));
									break;
								} catch (NumberFormatException e1) {
									alert("Invalid Input for the score of " + partName);
								}
							default:
								//Prevent from passing incorrect data to the next frame
								return;
						}						
					}
					//
					Part newPart = new Part(partName, newCategory.getCid(), totalScore, percentage);
					try {
						p_DAO.insert(newPart);
					} catch (SQLException e1) {
					    alert(e1.toString());
						e1.printStackTrace();
					}
					newCategory.addPart(newPart);
				}
				double p_PercentageSum = 0;
				for (Part p : newCategory.getPartList()) {
					p_PercentageSum += p.getPercentage();
				}
				if (p_PercentageSum > cPercentage) {
					alert("Percentages of Parts DON'T add up, please CHECK");
					return;
				}
				course.addCategory(newCategory);
				
				//Check the Number of Remaining Categories to Create
				if (categoryLeft > 1) {
					categoryLeft--;
					//System.out.println(categoryLeft);
					AddCategoryFrame nextFrame = new AddCategoryFrame(backend, course, categoryLeft);
					nextFrame.setVisible(true);
					dispose();
				} else {
					//System.out.println(course.getCategoryCount());
					double c_PercentageSum = 0;
					for (Category c : course.getCategories()) {
						c_PercentageSum += c.getPercentage();
					}
					if (c_PercentageSum > 100) {
						alert("Percentages of all Categories DON'T add up, please EDIT THEM");
					}
					EditCategoryFrame nextFrame = new EditCategoryFrame(backend);
					nextFrame.setVisible(true);
					dispose();
				}
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
		EditCategoryFrame prevframe = new EditCategoryFrame(backend) ;
		prevframe.setLocationRelativeTo(null);
		prevframe.setVisible(true);
		dispose();
	}
}

