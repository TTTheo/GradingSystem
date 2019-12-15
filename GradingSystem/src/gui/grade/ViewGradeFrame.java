package gui.grade;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import backend.Backend;
import gui.AllCoursesFrame;
import gui.FrameActions;
import gui.ManageStudentsFrame;
import gui.PickPartFrame;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JCheckBox;
import javax.swing.ScrollPaneConstants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import objects.Category;
import objects.Course;
import objects.Grade;
import objects.Part;
import objects.Student;

import java.awt.GridLayout;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;


public class ViewGradeFrame extends JFrame implements FrameActions{

	private JPanel contentPane;
	private JTable table;
	private JLabel lblCourseName;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JPanel panel;
	private DefaultTableModel tableModel;
	private JButton btnApply;
	private ArrayList<JCheckBox> check=new ArrayList<JCheckBox>();
	private ArrayList<Category> category=new ArrayList<Category>();
	private String[] columnNames;
	private String[][] data;
	private ArrayList<Student> students=new ArrayList<Student>();
	private ArrayList<Grade> grades=new ArrayList<Grade>();
	private ArrayList<Part> parts=new ArrayList<Part>();
	private JButton btnStatistic;
	private JButton btnCalculateFinal;
	private JButton btnCurveFinalGrade;
	private JButton btnEditStudents;
	private JButton btnEditCategory;
	private String[] finals;
	private String[] curve;
	private JButton btnCancel;
	private Course course;
	private JButton btnRecordGrade;
	private DefaultTableCellRenderer tcr=null;
	private List<String[]> index=new ArrayList<String[]>();
	private JPopupMenu m_popupMenu;
	private int X;
	private int Y;

	private Backend backend;
	public ViewGradeFrame(Backend backend) {
		this.backend = backend;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1032, 618);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.course=backend.getCourse();
		init();
		addActions();
	}
	
	public JLabel getLabel() {
		return this.lblCourseName;
	}
	
	public void init() {
		try {
			System.out.print(course.getCourseId());
			category= backend.getCategories(course);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*category.add(new Category("Homework",3,"C01",20));
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

		lblCourseName = new JLabel("Course name");
		lblCourseName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCourseName.setBounds(62, 35, 118, 16);
		contentPane.add(lblCourseName);
		lblCourseName.setText(course.getName());
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(190, 78, 617, 402);
		contentPane.add(scrollPane);
		
//		grades=backend.getAllGrade(); TODO
        try {
			students=backend.getAllStudents(course);
		} catch (SQLException e) {
        	alert(e.toString());
        	e.printStackTrace();
		}
		/*grades.add(new Grade("U09","P01",50));
		grades.add(new Grade("U10","P01",89));
		grades.add(new Grade("U11","P01",100));
		students.add(new Student("Jerry","U09"));
		students.add(new Student("Emma","U10"));
		students.add(new Student("Lisa","U11"));
		grades.add(new Grade("U09","P02",20));
		grades.add(new Grade("U10","P02",40));
		grades.add(new Grade("U11","P02",88));
		grades.add(new Grade("U09","P05",87));
		grades.add(new Grade("U10","P05",89));
		grades.add(new Grade("U11","P05",88));*/
		
		//ArrayList<Part> parts=new ArrayList<Part>();
		for(int i=0;i<category.size();i++) {
			for(int j=0;j<category.get(i).getPartList().size();j++) {
				parts.add(category.get(i).getPartList().get(j));
			}
		}
		tableModel=setNewModel();
		table = new JTable(tableModel) {
			public boolean isCellEditable(int row, int column){
					 return false;
            }
		};
		changeColor();
		table.setDefaultRenderer(Object.class, tcr);
		scrollPane.setViewportView(table);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_1.setBounds(51, 78, 140, 402);
		contentPane.add(scrollPane_1);
		
		panel = new JPanel();
		scrollPane_1.setViewportView(panel);
		panel.setLayout(new GridLayout(10, 0, 0, 0));
		
		btnApply = new JButton("Apply");
		btnApply.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnApply.setBounds(51, 497, 97, 29);
		contentPane.add(btnApply);
		
		btnStatistic = new JButton("Statistic");
		btnStatistic.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnStatistic.setBounds(843, 117, 145, 29);
		contentPane.add(btnStatistic);
		
		btnCalculateFinal = new JButton("Calculate Final");
		btnCalculateFinal.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnCalculateFinal.setBounds(843, 171, 145, 29);
		contentPane.add(btnCalculateFinal);
		
		btnCurveFinalGrade = new JButton("Curve Final");
		btnCurveFinalGrade.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnCurveFinalGrade.setBounds(843, 223, 145, 29);
		contentPane.add(btnCurveFinalGrade);
		
		btnCancel = new JButton("Back");
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnCancel.setBounds(843, 497, 145, 29);
		contentPane.add(btnCancel);
		
		btnEditStudents = new JButton("Edit Students");
		btnEditStudents.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnEditStudents.setBounds(843, 317, 145, 29);
		contentPane.add(btnEditStudents);
		
		btnEditCategory = new JButton("Edit Category");
		btnEditCategory.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnEditCategory.setBounds(843, 371, 145, 29);
		contentPane.add(btnEditCategory);
		
		btnRecordGrade = new JButton("Record Grade");
		btnRecordGrade.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnRecordGrade.setBounds(843, 423, 145, 29);
		contentPane.add(btnRecordGrade);
		
		for(int i=0;i<category.size();i++) {
			check.add(new JCheckBox(category.get(i).getName()));
			check.get(i).setSelected(true);
			panel.add(check.get(i));
		}
			
	}
	
	private void createPopupMenu() {
        m_popupMenu = new JPopupMenu();
        
        JMenuItem delMenItem = new JMenuItem();
        delMenItem.setText("View comment");
        delMenItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	//System.out.println(X+" "+Y);
            	boolean judge=false;
            	for(String[] rowAndCol : index){
                	if(X==(Integer.valueOf(rowAndCol[0]))&&Y==(Integer.valueOf(rowAndCol[1]))){
                		System.out.println(X+" "+Y);
                		int pid =parts.get(Y-2).getPid();
                		String sid=students.get(X).getSid();
                		for(int k=0;k<grades.size();k++) {
							if(grades.get(k).getSid().equals(sid)&&grades.get(k).getPid()==(pid)) {
								String comment=grades.get(k).getComment();
								if(comment!=null) {
									judge=true;
									ViewComment view=new ViewComment(comment);
									view.setVisible(true);
									break;
								}else {
									alert("No comment!");
									break;
								}
							}else {
								//table.setToolTipText(null);
							}
						}
                	}
                }
            	if(!judge) {
            		alert("No comment!");
            	}
            }
        });
        m_popupMenu.add(delMenItem);
	}
	
	private void mouseRightButtonClick(java.awt.event.MouseEvent evt) {
		createPopupMenu();
        if (evt.getButton() == java.awt.event.MouseEvent.BUTTON3) {
            int focusedRowIndex = table.rowAtPoint(evt.getPoint());
            int col = table.columnAtPoint(evt.getPoint()); 
            if (focusedRowIndex == -1) {
                return;
            }
            table.setRowSelectionInterval(focusedRowIndex, focusedRowIndex);
            X=focusedRowIndex;
            Y=col;
            m_popupMenu.show(table, evt.getX(), evt.getY());
        }
	}
	
	public void addActions() {
		table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            	mouseRightButtonClick(evt);
            }
		});
		btnEditStudents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManageStudentsFrame manageStu=new ManageStudentsFrame(backend);
				manageStu.setLocationRelativeTo(null);
				manageStu.setVisible(true);
			}
		});
		
		btnEditCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditCategoryFrame edit=new EditCategoryFrame(backend);
				edit.setLocationRelativeTo(null);
				edit.setVisible(true);
			}
		});
		
		btnRecordGrade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PickPartFrame pick=new PickPartFrame(backend);
				pick.setLocationRelativeTo(null);
				pick.setVisible(true);
			}
		});
		
		btnApply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<JCheckBox> newCheck=new ArrayList<JCheckBox>();
				for(int i=0;i<check.size();i++) {
					if(check.get(i).isSelected()) {
						newCheck.add(check.get(i));
					}
				}
				//System.out.println(newCheck.size());
				parts.clear();
				//ArrayList<Part> part=new ArrayList<Part>();
				for(int i=0;i<newCheck.size();i++) {
					for(int j=0;j<category.size();j++) {
						if(newCheck.get(i).getText().equals(category.get(j).getName())){
							for(int k=0;k<category.get(j).getPartList().size();k++) {
								//System.out.println("add");
								parts.add(category.get(j).getPartList().get(k));
							}
							break;
						}
					}
				}
				//System.out.println(part.size());
				tableModel=setNewModel();
				table.setModel(tableModel);
				changeColor();
				table.setDefaultRenderer(Object.class, tcr);
			}
		});
		
		btnStatistic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double median;
				double average;
				double deviation;
				if(finals!=null) {
					double totalGrade=0;
					for(int i=0;i<finals.length;i++) {
						totalGrade+=Double.parseDouble(finals[i]);
					}
					average=totalGrade/finals.length;
					String[] tempfinals=finals;
					Arrays.sort(tempfinals);
					double size=tempfinals.length;
					if(size/2==0) {
						median=(Double.parseDouble(tempfinals[(int) (size/2)])+Double.parseDouble(tempfinals[(int) (size/2-1)]))/2;
					}else {
						double index=size/2-0.5;
						median=Double.parseDouble(tempfinals[(int)index]);
					}
					double cal=0;
					for(int i=0;i<finals.length;i++) {
						cal+=Double.parseDouble(finals[i])-average;
					}
					deviation=Math.sqrt(cal/finals.length);
					double statistic[]= {average,median,deviation};
					ShowStatisticFrame show=new ShowStatisticFrame(statistic);
					show.setVisible(true);
				}else {
					alert("Calculate final first!");
				}
				
			}
		});
		
		btnCalculateFinal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean finalExist=false;
				for(int i=0;i<check.size();i++) {
					if(check.get(i).getText().equals("Final")) {
						finalExist=true;
						break;
					}
				}
				if(finalExist) {
					
				}else {
					check.add(new JCheckBox("Final"));
					check.get(check.size()-1).setSelected(true);
					panel.add(check.get(check.size()-1));
					panel.updateUI();
					
					getFinalGrade();
				}
			}
		});
		
		btnCurveFinalGrade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean finalExist=false;
				for(int i=0;i<check.size();i++) {
					if(check.get(i).getText().equals("Final")) {
						finalExist=true;
						break;
					}
				}
				if(!finalExist) {
					alert("You must calculate the final first!");
				}else {
					CurveGradeFrame curve=new CurveGradeFrame(ViewGradeFrame.this);
					curve.setVisible(true);
				}
				
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openPrevious();
			}
		});
	}
	
	public String[] getFinals() {
		return this.finals;
	}
	
	public String[][] getData(){
		return this.data;
	}
	
	public String[] getColumn() {
		return this.columnNames;
	}
	
	public void setColumn(String[] column) {
		this.columnNames=column;
	}
	
	public void setData(String[][] data) {
		this.data=data;
	}
	
	public void setModel(DefaultTableModel tableModel) {
		this.tableModel=tableModel;
	}
	
	public ArrayList<JCheckBox> getCheck(){
		return this.check;
	}
	
	public JPanel getPanel() {
		return this.panel;
	}
	
	public DefaultTableModel getModel() {
		return this.tableModel;
	}
	
	public JTable getTabel() {
		return this.table;
	}
	
	public void setCurve(String[] curve) {
		this.curve=curve;
	}
	
	public DefaultTableModel setNewModel() {
		boolean finalExist=false;
		boolean curveExist=false;
		for(int l=0;l<check.size();l++) {
			if(check.get(l).getText().equals("Final")) {
				finalExist=true;
			}
			if(check.get(l).getText().equals("Curve")) {
				curveExist=true;
			}
		}
		
		if((finalExist&&check.get(check.size()-1).isSelected()&&!curveExist)||
				(finalExist&&check.get(check.size()-2).isSelected()&&curveExist&&!check.get(check.size()-1).isSelected())) {
			columnNames=new String[parts.size()+3];
			for(int i=0;i<columnNames.length;i++) {
				if(i==0) {
					columnNames[i]="Name";
				}else if(i==1){
					columnNames[i]="ID";
				}else if(i==columnNames.length-1){
					columnNames[i]="Final";
				}else {
					columnNames[i]=parts.get(i-2).getName();
				}
			}
			//index=new int[][];
			data=new String[students.size()][columnNames.length];
			for(int i=0;i<students.size();i++) {
				for(int j=0;j<columnNames.length;j++) {
					if(j==0) {
						data[i][j]=students.get(i).getFname();
					}else if(j==1) {
						data[i][j]=students.get(i).getSid();
					}else if(j==columnNames.length-1){
						data[i][j]=finals[i];
					}else {
						for(int k=0;k<grades.size();k++) {
							if(grades.get(k).getSid().equals(students.get(i).getSid())&&grades.get(k).getPid() == parts.get(j-2).getPid()) {
								data[i][j]=String.valueOf(grades.get(k).getGrade());
								String comment=grades.get(k).getComment();
								if(comment!=null) {
									String[] commentindex= {String.valueOf(i),String.valueOf(j)};
									index.add(commentindex);
								}
								break;
							}
						}
					}			
				}
			}
			
		}else if(finalExist&&check.get(check.size()-2).isSelected()&&curveExist&&check.get(check.size()-1).isSelected()) {
			columnNames=new String[parts.size()+4];
			for(int i=0;i<columnNames.length;i++) {
				if(i==0) {
					columnNames[i]="Name";
				}else if(i==1){
					columnNames[i]="ID";
				}else if(i==columnNames.length-1){
					columnNames[i]="Curve";
				}else if(i==columnNames.length-2) {
					columnNames[i]="Final";
				}else {
					columnNames[i]=parts.get(i-2).getName();
				}
			}
			data=new String[students.size()][columnNames.length];
			for(int i=0;i<students.size();i++) {
				for(int j=0;j<columnNames.length;j++) {
					if(j==0) {
						data[i][j]=students.get(i).getFname();
					}else if(j==1) {
						data[i][j]=students.get(i).getSid();
					}else if(j==columnNames.length-2){
						data[i][j]=finals[i];
					}else if(j==columnNames.length-1) {
						data[i][j]=curve[i];
					}else {
						for(int k=0;k<grades.size();k++) {
							if(grades.get(k).getSid().equals(students.get(i).getSid())&&grades.get(k).getPid() == parts.get(j-2).getPid()) {
								data[i][j]=String.valueOf(grades.get(k).getGrade());
								String comment=grades.get(k).getComment();
								if(comment!=null) {
									String[] commentindex= {String.valueOf(i),String.valueOf(j)};
									index.add(commentindex);
								}
								break;
							}
						}
					}			
				}
			}
		}else if(finalExist&&!check.get(check.size()-2).isSelected()&&curveExist&&check.get(check.size()-1).isSelected()) {
			columnNames=new String[parts.size()+3];
			for(int i=0;i<columnNames.length;i++) {
				if(i==0) {
					columnNames[i]="Name";
				}else if(i==1){
					columnNames[i]="ID";
				}else if(i==columnNames.length-1){
					columnNames[i]="Curve";
				}else {
					columnNames[i]=parts.get(i-2).getName();
				}
			}
			data=new String[students.size()][columnNames.length];
			for(int i=0;i<students.size();i++) {
				for(int j=0;j<columnNames.length;j++) {
					if(j==0) {
						data[i][j]=students.get(i).getFname();
					}else if(j==1) {
						data[i][j]=students.get(i).getSid();
					}else if(j==columnNames.length-1){
						data[i][j]=curve[i];
					}else {
						for(int k=0;k<grades.size();k++) {
							if(grades.get(k).getSid().equals(students.get(i).getSid())&&grades.get(k).getPid() == parts.get(j-2).getPid()) {
								data[i][j]=String.valueOf(grades.get(k).getGrade());
								String comment=grades.get(k).getComment();
								if(comment!=null) {
									String[] commentindex= {String.valueOf(i),String.valueOf(j)};
									index.add(commentindex);
								}
								break;
							}
						}
					}			
				}
			}
		}else {
			columnNames=new String[parts.size()+2];
			for(int i=0;i<columnNames.length;i++) {
				if(i==0) {
					columnNames[i]="Name";
				}else if(i==1){
					columnNames[i]="ID";
				}else {
					columnNames[i]=parts.get(i-2).getName();
				}
			}
			data=new String[students.size()][columnNames.length];
			for(int i=0;i<students.size();i++) {
				for(int j=0;j<columnNames.length;j++) {
					if(j==0) {
						data[i][j]=students.get(i).getFname();
					}else if(j==1) {
						data[i][j]=students.get(i).getSid();
					}else {
						for(int k=0;k<grades.size();k++) {
							if(grades.get(k).getSid().equals(students.get(i).getSid())&&grades.get(k).getPid() == parts.get(j-2).getPid()) {
								data[i][j]=String.valueOf(grades.get(k).getGrade());
								String comment=grades.get(k).getComment();
								if(comment!=null) {
									String[] commentindex= {String.valueOf(i),String.valueOf(j)};
									index.add(commentindex);
								}
								break;
							}
						}
					}			
				}		
			}
		}
		tableModel=new DefaultTableModel(data, columnNames);
		return tableModel;
	}
	
	public void changeColor() {
		 tcr= new DefaultTableCellRenderer() {
			//private List<String[]> indexes=index;

			public Component getTableCellRendererComponent(JTable table,
					Object value, boolean isSelected, boolean hasFocus,int row, int col) {
				super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
				for(int i=0; i<table.getRowCount(); i++){
					if (row == i){
					this.setBackground(Color.white);
					}
					}
					for(String[] rowAndCol : index){
					int _row= Integer.valueOf(rowAndCol[0]);
					int _col= Integer.valueOf(rowAndCol[1]);

					if( _row == row && _col == col) {
					this.setBackground(Color.yellow);
					}
					}
					//this.setText(value.toString());
					return this;
					}
			};
	}

	
	public void getFinalGrade() {
		int row=table.getRowCount();
		finals=new String[row];
		for(int k=0;k<row;k++) {
			String ID=data[k][1];
			double finalgrade=0;
			for(int i=0;i<category.size();i++) {
				double Cpercentage=category.get(i).getPercentage();
				double categoryfinal=0;
				for(int j=0;j<category.get(i).getPartList().size();j++) {
					Part part=category.get(i).getPartList().get(j);
					double Ppercentage=part.getPercentage();
					for(int l=0;l<grades.size();l++) {
						if(grades.get(l).getSid().equals(ID)&&grades.get(l).getPid() == part.getPid()){
							if(grades.get(l).getGrade()<0) {
								double grade=(part.getTotalScore()+grades.get(l).getGrade())/part.getTotalScore()*100;
								categoryfinal+=grade*Ppercentage;
							}else {
								double grade=grades.get(l).getGrade()/part.getTotalScore()*100;
								categoryfinal+=grade*Ppercentage/100;
							}
						}
					}	
				}
				finalgrade+=categoryfinal*Cpercentage/100;
			}
			finals[k]=String.valueOf(finalgrade);
		}
		String[] tempColumn=new String[columnNames.length+1];
		for(int i=0;i<tempColumn.length;i++) {
			if(i==tempColumn.length-1) {
				tempColumn[i]="Final";
			}else {
				tempColumn[i]=columnNames[i];
			}
		}
		columnNames=tempColumn;
		String[][] tempdata=new String[data.length][columnNames.length];
		for(int i=0;i<tempdata.length;i++) {
			for(int j=0;j<tempdata[0].length;j++) {
				if(j==tempdata[0].length-1) {
					tempdata[i][j]=finals[i];
				}else {
					tempdata[i][j]=data[i][j];
				}
			}			
		}
		data=tempdata;
		tableModel=new DefaultTableModel(data,columnNames);
		table.setModel(tableModel);
	}
	
	public void alert(String message){
        JOptionPane.showMessageDialog(null, message);
    }

	public void openNext() {
		dispose();
	}

	// This is the first window, no previous window exists
	public void openPrevious() {
		AllCoursesFrame prevframe = new AllCoursesFrame(backend) ;
		prevframe.setLocationRelativeTo(null);
		prevframe.setVisible(true);
		dispose();
	}
}

