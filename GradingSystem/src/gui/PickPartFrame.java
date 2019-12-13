package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import backend.CourseBackend;
import gui.grade.RecordGradeFrame;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
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
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private JButton btnNext;
	private JButton btnCancel;
	private ArrayList<Category> category=new ArrayList<Category>();
	private ArrayList<Part> part=new ArrayList<Part>();
	private CourseBackend courseBackend;
	private Course course;
	private Category category_c;
	private Part part_p;

	/**
	 * Create the frame.
	 */
	public PickPartFrame(CourseBackend c) {
	    courseBackend = c;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 572, 589);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.course=course;
		init();
		addActions();
	}
	
	public void init(){
		/*course=new Course("CS591",4);
		course.setCategories(category);
		course.setCourseid("CAS01");
		category.add(new Category("Homework",3,"C01",20));
		ArrayList<Part> partList=new ArrayList<Part>();
//		partList.add(new Part("P01","homework1","C01",100,30));
//		partList.add(new Part("P02","homework2","C01",100,30));
//		partList.add(new Part("P03","homework3","C01",100,40));
		category.get(0).setPartList(partList);
		category.add(new Category("Exam",2,"C02",20));
		ArrayList<Part> partList2=new ArrayList<Part>();
//		partList2.add(new Part("P04","midterm","C02",100,60));
//		partList2.add(new Part("P05","final","C02", 100,40));
		category.get(1).setPartList(partList2);
		category.add(new Category("Attending",1,"C03",20));
		ArrayList<Part> partList3=new ArrayList<Part>();
//		partList3.add(new Part("P06","attending","C04",5,100));
		category.get(2).setPartList(partList3);
		category.add(new Category("Bonus",1,"C04",0));
		ArrayList<Part> partList4=new ArrayList<Part>();
		partList4.add(new Part("P07","bonus","C04",10,100));
		category.get(3).setPartList(partList4);*/
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
		try {
			category=courseBackend.getCategories(course);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=0;i<category.size();i++) {
			comboBox.addItem(category.get(i).getName());
		}
		
        
		comboBox.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		    	for(int i=0;i<category.size();i++) {
		    		if(comboBox.getSelectedItem().equals(category.get(i).getName())){
		    			try {
							part=courseBackend.getParts(category.get(i));
		    				part=category.get(i).getPartList();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
		    		}
		    	}
		        comboBox_1.removeAllItems();
		        for(int i=0;i<part.size();i++) {
		        	comboBox_1.addItem(part.get(i).getName());
		        }
		    }			
		});
		
		comboBox_1 = new JComboBox();
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		comboBox_1.setBounds(112, 256, 328, 33);
		contentPane.add(comboBox_1);
		
		for(int i=0;i<category.size();i++) {
		if(comboBox.getSelectedItem().equals(category.get(i).getName())){
			try {
				part=courseBackend.getParts(category.get(i));
				part=category.get(i).getPartList();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		}
	    comboBox_1.removeAllItems();
	    for(int i=0;i<part.size();i++) {
	    	comboBox_1.addItem(part.get(i).getName());
	    }
		
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
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String CategoryName=comboBox.getSelectedItem().toString();
				String partName=comboBox_1.getSelectedItem().toString();
				int pid;
				for(int i=0;i<part.size();i++) {
					if(part.get(i).getName().equals(partName)) {
						pid = part.get(i).getPid();
						part_p = part.get(i);
						break;
					}
				}
				//part_p=partBack.select(Pid);
				for(int i=0;i<category.size();i++) {
					if(category.get(i).getName().equals(CategoryName)) {
						category_c=category.get(i);
						break;
					}
				}
				
				//System.out.print(part_p.getName());
				//System.out.print(category_c.getName());
				openNext();			
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
	
	public Part getPart() {
		return this.part_p;
	}
	
	public void alert(String message){
        JOptionPane.showMessageDialog(null, message);
    }

	// Open the semester frame next
	public void openNext() {
		RecordGradeFrame next = new RecordGradeFrame(courseBackend, course, category_c,part_p);
		next.setVisible(true);
		dispose();
	}

	// This is the first window, no previous window exists
	public void openPrevious() {
		dispose();
	}
}
