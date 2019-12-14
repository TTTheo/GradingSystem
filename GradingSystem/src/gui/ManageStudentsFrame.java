package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import backend.Backend;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import objects.Course;
import objects.Student;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.SystemColor;

public class ManageStudentsFrame extends JFrame implements FrameActions{
	private JPanel contentPane;
	private JButton btnAddStudent;
	private JButton btnDeleteStudent;
	private JButton btnWithdrawStudent;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JScrollPane scrollPane;
	private JLabel lblCourse;
	private JLabel lblName;
	private JLabel lblId;
	private JLabel lblEmail;
	private JButton btnImportStudent;
	private JButton btnCancel;
	private DefaultTableModel tableModel;
	private String[][] data;
	private final String[] columnNames= {"First Name","Last Name","ID","Email"};
	private List<Student> students=new ArrayList<Student>();
	private Course course;
	private JTextField textField_3;

	private Backend backend;
	public ManageStudentsFrame(Backend backend) {
	    this.backend = backend;
		course = backend.getCourse();

		setBounds(100, 100, 877, 589);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		init();
		addActions();
	}

	public void init(){
		btnAddStudent = new JButton("Add Student");
		btnAddStudent.setBackground(SystemColor.controlHighlight);
		btnAddStudent.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnAddStudent.setBounds(650, 293, 170, 23);
		contentPane.add(btnAddStudent);

		btnDeleteStudent = new JButton("Delete Student");
		btnDeleteStudent.setBackground(SystemColor.controlHighlight);
		btnDeleteStudent.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnDeleteStudent.setBounds(647, 343, 173, 23);
		contentPane.add(btnDeleteStudent);

		btnWithdrawStudent = new JButton("Withdraw Student");
		btnWithdrawStudent.setBackground(SystemColor.controlHighlight);
		btnWithdrawStudent.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnWithdrawStudent.setBounds(650, 394, 173, 23);
		contentPane.add(btnWithdrawStudent);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(35, 63, 538, 354);
		contentPane.add(scrollPane);

		try {
			students=backend.getAllStudents(course);
		} catch(SQLException e) {
			alert(e.toString());
			e.printStackTrace();
		}
		data=new String[students.size()][4];
		for(int i=0;i<students.size();i++) {
			data[i][0]=students.get(i).getFname();
			data[i][1]=students.get(i).getLname();
			data[i][2]=students.get(i).getSid();
			data[i][3]=students.get(i).getEmail();
		}
		tableModel=new DefaultTableModel(data,columnNames){
			 public boolean isCellEditable(int row, int column){
					 return false;
            }
		};
		table = new JTable(tableModel);
		scrollPane.setViewportView(table);

		lblCourse = new JLabel("Course");
		lblCourse.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCourse.setBounds(35, 27, 91, 23);
		contentPane.add(lblCourse);
		lblCourse.setText(course.getName());

		lblName = new JLabel("Fisrt Name:");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblName.setBounds(650, 47, 113, 16);
		contentPane.add(lblName);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField.setBounds(647, 76, 173, 22);
		contentPane.add(textField);
		textField.setColumns(10);

		lblId = new JLabel("ID:");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblId.setBounds(650, 165, 78, 19);
		contentPane.add(lblId);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField_1.setBounds(647, 197, 173, 22);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblEmail.setBounds(650, 222, 78, 23);
		contentPane.add(lblEmail);

		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField_2.setBounds(647, 246, 173, 22);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		btnImportStudent = new JButton("Import Student");
		btnImportStudent.setBackground(SystemColor.controlHighlight);
		btnImportStudent.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnImportStudent.setBounds(651, 451, 169, 25);
		contentPane.add(btnImportStudent);

		btnCancel = new JButton("Cancel");
		btnCancel.setBackground(SystemColor.controlHighlight);
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnCancel.setBounds(35, 451, 97, 25);
		contentPane.add(btnCancel);

		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblLastName.setBounds(650, 107, 113, 16);
		contentPane.add(lblLastName);

		textField_3 = new JTextField();
		textField_3.setBounds(647, 131, 173, 22);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
	}

	public void addActions(){
		table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int selectedRowIndex = table.getSelectedRow();
                String selectedName = tableModel.getValueAt(selectedRowIndex,0).toString();
                String selectedLname=tableModel.getValueAt(selectedRowIndex, 1).toString();
                String selectedID=tableModel.getValueAt(selectedRowIndex,2).toString();
                String selectedEmail=tableModel.getValueAt(selectedRowIndex, 3).toString();
                textField.setText(selectedName);
                textField_3.setText(selectedLname);
                textField_1.setText(selectedID);
                textField_2.setText(selectedEmail);
        	}
        });

		btnAddStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=textField.getText();
				String lname=textField_3.getText();
				String ID=textField_1.getText();
				String Email=textField_2.getText();
				int rowIndex = students.size();
				Student stu=null;
				try {
					stu=backend.getStudentById(ID);
				} catch (SQLException ex) {
					alert(ex.toString());
					ex.printStackTrace();
				}
				if(stu!=null) {
					alert("The student exist!");
				}else {
				    Student newStudent = new Student(name, lname, ID, Email);
                    students.add(newStudent);
                    try {
						backend.addStudent(newStudent);
						backend.addStudentToCourse(newStudent, course);
					} catch (SQLException ex) {
						alert(ex.toString());
						ex.printStackTrace();
					}
					data=new String[students.size()][4];
					for(int i=0;i<students.size();i++) {
						data[i][0]=students.get(i).getFname();
						data[i][1]=students.get(i).getLname();
						data[i][2]=students.get(i).getSid();
						data[i][3]=students.get(i).getEmail();
					}
					tableModel=new DefaultTableModel(data,columnNames);
					table.setModel(tableModel);
				}
			}
		});

		btnDeleteStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 /* int selectedRowIndex = table.getSelectedRow();
				  String name = tableModel.getValueAt(selectedRowIndex,0).toString();
				  String lname=tableModel.getValueAt(selectedRowIndex, 1).toString();
				  String ID=tableModel.getValueAt(selectedRowIndex,2).toString();
				  String Email=tableModel.getValueAt(selectedRowIndex, 3).toString();*/
				  String name=textField.getText();
				  String lname=textField_3.getText();
				  String ID=textField_1.getText();
				  String Email=textField_2.getText();
	              boolean stuExist=false;
	              if(!ID.equals("")) {
	            	  if(name.equals("")&&lname.equals("")&&Email.equals("")) {
			              for(int j=0;j<students.size();j++) {
			            	  if(students.get(j).getSid().equals(ID)) {
                                  stuExist = true;
                                  students.remove(j);
                                  try {
									  backend.deleteStudent(new Student(name, lname, ID, Email));
								  } catch (SQLException ex) {
									  alert(ex.toString());
									  ex.printStackTrace();
								  }
			            		  data=new String[students.size()][4];
			            		  for(int i=0;i<students.size();i++) {
			            			  data[i][0]=students.get(i).getFname();
			            			  data[i][1]=students.get(i).getLname();
			            			  data[i][2]=students.get(i).getSid();
			            			  data[i][3]=students.get(i).getEmail();
			            		  }
			            		  tableModel=new DefaultTableModel(data,columnNames);
			            		  table.setModel(tableModel);
			            		  break;
			            	  }
			              }
			              if(!stuExist) {
			            	  alert("Do not exist!");
			              }
	            	  }else if(!name.equals("")&&lname.equals("")||name.equals("")&&!lname.equals("")) {
	            		  alert("Please fill full name!");
	            	  }else if(!name.equals("")&&!lname.equals("")) {
	            		  for(int j=0;j<students.size();j++) {
			            	  if(students.get(j).getSid().equals(ID)&&students.get(j).getFname().equals(name)&&students.get(j).getLname().equals(lname)) {
                                  stuExist = true;
                                  students.remove(j);
                                  try {
									  backend.deleteStudent(new Student(name, lname, ID, Email));
								  } catch (SQLException ex) {
									  alert(ex.toString());
									  ex.printStackTrace();
								  }
			            		  data=new String[students.size()][4];
			            		  for(int i=0;i<students.size();i++) {
			            			  data[i][0]=students.get(i).getFname();
			            			  data[i][1]=students.get(i).getLname();
			            			  data[i][2]=students.get(i).getSid();
			            			  data[i][3]=students.get(i).getEmail();
			            		  }
			            		  tableModel=new DefaultTableModel(data,columnNames);
			            		  table.setModel(tableModel);
			            		  break;
			            	  }
			              }
			              if(!stuExist) {
			            	  alert("Do not exist!");
			              }
	            	  }else {
	            		  for(int j=0;j<students.size();j++) {
			            	  if(students.get(j).getSid().equals(ID)&&students.get(j).getFname().equals(name)&&
			            			  students.get(j).getLname().equals(lname)&&students.get(j).getEmail().equals(Email)) {
                                  stuExist = true;
                                  students.remove(j);
                                  try {
									  backend.deleteStudent(new Student(name, lname, ID, Email));
								  } catch (SQLException ex) {
									  alert(ex.toString());
									  ex.printStackTrace();
								  }
			            		  data=new String[students.size()][4];
			            		  for(int i=0;i<students.size();i++) {
			            			  data[i][0]=students.get(i).getFname();
			            			  data[i][1]=students.get(i).getLname();
			            			  data[i][2]=students.get(i).getSid();
			            			  data[i][3]=students.get(i).getEmail();
			            		  }
			            		  tableModel=new DefaultTableModel(data,columnNames);
			            		  table.setModel(tableModel);
			            		  break;
			            	  }
			              }
			              if(!stuExist) {
			            	  alert("Do not exist!");
			              }
	            	  }
	              }
			}
		});

		btnWithdrawStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=textField.getText();
				String lname=textField_3.getText();
				String ID=textField_1.getText();
				String Email=textField_2.getText();
				  int selectedRowIndex = table.getSelectedRow();
	              String selectedID=tableModel.getValueAt(selectedRowIndex,1).toString();
	              boolean stuExist=false;
	              for(int j=0;j<students.size();j++) {
	            	  if(students.get(j).getSid().equals(selectedID)) {
	            		  stuExist=true;
	            		  students.remove(j);
	            		  //studentBack.deleteStudent(new Student(name,lname,ID,Email));
	            		  data=new String[students.size()][4];
	            		  for(int i=0;i<students.size();i++) {
	            			  data[i][0]=students.get(i).getFname();
	            			  data[i][1]=students.get(i).getLname();
	            			  data[i][2]=students.get(i).getSid();
	            			  data[i][3]=students.get(i).getEmail();
	            		  }
	            		  tableModel=new DefaultTableModel(data,columnNames);
	            		  table.setModel(tableModel);
	            		  break;
	            	  }
	              }
	              if(!stuExist) {
	            	  alert("Do not exist!");
	              }
			}
		});

		btnImportStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					jxl.Workbook wb=null;
					InputStream is = new FileInputStream("C://Users//82585//Desktop//students.xls");
		            wb = Workbook.getWorkbook(is);

		            int sheetSize = wb.getNumberOfSheets();
		            Sheet sheet = wb.getSheet(0);
		            int row_total = sheet.getRows();
		            for (int j = 0; j < row_total; j++) {
		                if(j == 0){
		                    Cell[] cells = sheet.getRow(j);
		                    //System.out.println(cells[0].getContents());
		                    //System.out.println(cells[1].getContents());
		                    //System.out.println(cells[2].getContents());
		                }else {
                            Cell[] student = sheet.getRow(j);
                            Student newStudent = new Student(student[0].getContents(), student[1].getContents(), student[2].getContents(), student[3].getContents());
                            students.add(newStudent);
                            try {
								backend.addStudent(newStudent);
								backend.addStudentToCourse(newStudent, course);
							} catch (SQLException ex) {
								alert(ex.toString());
								ex.printStackTrace();
							}
			                //students.add(new Student(student[0].getContents(),student[1].getContents()));
			             //   System.out.println(students.get(j-1).getFname());
		                   // System.out.println(students.get(j-1).getSid());
		                    //System.out.println(students.get(j-1).getEmail());
		                }
		            }
		            data=new String[students.size()][3];
		            for(int i=0;i<students.size();i++) {
		            	data[i][1]=students.get(i).getLname();
		            	data[i][2]=students.get(i).getSid();
		            	data[i][3]=students.get(i).getEmail();
          			}
		            tableModel=new DefaultTableModel(data,columnNames);
		            table.setModel(tableModel);
		            alert("Add successfully!");
		        }catch (IOException ex) {
		            // TODO Auto-generated catch block
		            ex.printStackTrace();
		        } catch (BiffException ex){
		            ex.printStackTrace();
		        }
			}
		});

		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
