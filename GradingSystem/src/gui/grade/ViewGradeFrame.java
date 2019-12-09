package gui.grade;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JCheckBox;
import javax.swing.ScrollPaneConstants;
import java.util.ArrayList;

import objects.Category;
import objects.Part;
import java.awt.GridLayout;

public class ViewGradeFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JLabel lblCourseName;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JPanel panel;
	private ArrayList<Category> category=new ArrayList<Category>();

	/**
	 * Create the frame.
	 */
	public ViewGradeFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 895, 584);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		init();

	}
	
	public void init() {
		category.add(new Category("Homework",3,"C01",20));
		ArrayList<Part> partList=new ArrayList<Part>();
		partList.add(new Part("homework1","C01",100,30));
		partList.add(new Part("homework2","C01",100,30));
		partList.add(new Part("homework3","C01",100,40));
		category.get(0).setPartList(partList);
		category.add(new Category("Exam",2,"C01",20));
		ArrayList<Part> partList2=new ArrayList<Part>();
		partList.add(new Part("midterm","C01",100,60));
		partList.add(new Part("final","C01",100,40));
		category.get(1).setPartList(partList2);
		category.add(new Category("Attending",1,"C01",20));
		ArrayList<Part> partList3=new ArrayList<Part>();
		partList.add(new Part("attending","C01",5,100));
		category.get(2).setPartList(partList3);
		category.add(new Category("Bonus",1,"C01",0));
		ArrayList<Part> partList4=new ArrayList<Part>();
		partList.add(new Part("Bonus","C01",10,100));
		category.get(3).setPartList(partList4);
		
		lblCourseName = new JLabel("Course name");
		lblCourseName.setBounds(62, 35, 118, 16);
		contentPane.add(lblCourseName);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(190, 78, 617, 387);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_1.setBounds(51, 78, 140, 387);
		contentPane.add(scrollPane_1);
		
		panel = new JPanel();
		scrollPane_1.setViewportView(panel);
		panel.setLayout(new GridLayout(10, 0, 0, 0));
		
		ArrayList<JCheckBox> check=new ArrayList<JCheckBox>();
		for(int i=0;i<category.size();i++) {
			check.add(new JCheckBox(category.get(i).getName()));
			panel.add(check.get(i));
		}
		
		
	}
}
