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

	/**
	 * Create the frame.
	 */
	public AddCategoryFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 372, 449);
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
		lblCategoryName.setBounds(30, 26, 112, 15);
		contentPane.add(lblCategoryName);
		
		lblNumberOfParts = new JLabel("Number of parts in this category:");
		lblNumberOfParts.setBounds(30, 82, 236, 15);
		contentPane.add(lblNumberOfParts);
		
		textField = new JTextField();
		textField.setBounds(30, 51, 282, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(30, 107, 282, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		lblPercentageOfThis = new JLabel("Percentage of this category:");
		lblPercentageOfThis.setBounds(30, 138, 282, 15);
		contentPane.add(lblPercentageOfThis);
		
		textField_2 = new JTextField();
		textField_2.setBounds(30, 163, 282, 21);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 249, 282, 107);
		contentPane.add(scrollPane);
		
		Object[][] content=new Object[1][3];
		Object[] title={"Name","Percentage","Total score"};
		table = new JTable(content,title);
		scrollPane.setViewportView(table);
		
		
		btnNext = new JButton("Next");
		btnNext.setBounds(219, 366, 93, 23);
		contentPane.add(btnNext);
		
		btnSetParts = new JButton("Set parts");
		btnSetParts.setBounds(219, 198, 93, 23);
		contentPane.add(btnSetParts);
	}
	
	public void addActions(){
		btnSetParts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int partNum = Integer.parseInt(textField_1.getText());
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
				Category newCategory = new Category (categoryName, partNum, cPercentage);
				
				
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
								percentage = Double.parseDouble((String) table.getValueAt(rowNum, colNum));
								break;
							case 2:
								totalScore = Double.parseDouble((String) table.getValueAt(rowNum, colNum));
								break;
							default:
								System.out.println("Something went Wrong");
								break;
						}						
					}
					Part newPart = new Part(partName, totalScore, percentage);
					newCategory.addPart(newPart);
				}
				
				course.addCategory(newCategory);
				
				//Check the Number of Remaining Categories to Create
				if (categoryLeft > 1) {
					categoryLeft--;
					System.out.println(categoryLeft);
					AddCategoryFrame nextFrame = new AddCategoryFrame(course, categoryLeft);
					nextFrame.setVisible(true);
					dispose();
				} else {
//					System.out.println(course.getCategoryCount());
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

