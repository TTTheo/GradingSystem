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
import gui.grade.ViewGradeFrame;
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
import java.util.Arrays;
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
	private JButton btnWithdrawStudent;
	private JTextField fnameField;
	private JTextField idField;
	private JScrollPane scrollPane;
	private JLabel lblCourse;
	private JLabel lblName;
	private JLabel lblId;
	private JButton btnImportStudent;
	private JButton btnCancel;
	private StudentTableModel tableModel;
	private JTable studentTable ;
	private ArrayList<Student> students=new ArrayList<Student>();
	private Course course;
	private JTextField lnameField;
	private Student selectedStudent ;

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
	public void reload() {
		ArrayList<Student> data = null;
		try {
			data = backend.getAllStudents(course);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		final String[] columnNames= {"First Name","Last Name","ID"};
		tableModel = new StudentTableModel(data, columnNames);
		studentTable.setModel(tableModel);
	}
	public void init(){
		btnAddStudent = new JButton("Add Student");
		btnAddStudent.setBackground(SystemColor.controlHighlight);
		btnAddStudent.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnAddStudent.setBounds(643, 323, 170, 23);
		contentPane.add(btnAddStudent);

		btnWithdrawStudent = new JButton("Withdraw Student");
		btnWithdrawStudent.setBackground(SystemColor.controlHighlight);
		btnWithdrawStudent.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnWithdrawStudent.setBounds(643, 382, 173, 23);
		btnWithdrawStudent.setEnabled(false);
		contentPane.add(btnWithdrawStudent);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(35, 63, 596, 374);
		contentPane.add(scrollPane);

		ArrayList<Student> data = null;
		try {
			data = backend.getAllStudents(course);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		final String[] columnNames= {"First Name","Last Name","ID"};
		tableModel = new StudentTableModel(data, columnNames);
		studentTable = new JTable(tableModel);  // Create JTable with custom model
		studentTable.setBounds(35, 63, 538, 354);
		studentTable.addMouseListener(new java.awt.event.MouseAdapter() {
			 public void mouseClicked(java.awt.event.MouseEvent evt) {
	                jTableMouseClicked(evt);
	            }
        });
		scrollPane.setViewportView(studentTable);

		lblCourse = new JLabel("Course");
		lblCourse.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCourse.setBounds(35, 27, 91, 23);
		contentPane.add(lblCourse);
		lblCourse.setText(course.getName());

		lblName = new JLabel("Fisrt Name:");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblName.setBounds(643, 63, 113, 16);
		contentPane.add(lblName);

		fnameField = new JTextField();
		fnameField.setFont(new Font("Tahoma", Font.PLAIN, 17));
		fnameField.setBounds(643, 103, 173, 22);
		contentPane.add(fnameField);
		fnameField.setColumns(10);

		lblId = new JLabel("ID:");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblId.setBounds(643, 225, 78, 19);
		contentPane.add(lblId);

		idField = new JTextField();
		idField.setFont(new Font("Tahoma", Font.PLAIN, 17));
		idField.setBounds(643, 262, 173, 22);
		contentPane.add(idField);
		idField.setColumns(10);

		btnImportStudent = new JButton("Import Student");
		btnImportStudent.setBackground(SystemColor.controlHighlight);
		btnImportStudent.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnImportStudent.setBounds(429, 494, 169, 25);
		contentPane.add(btnImportStudent);

		btnCancel = new JButton("Cancel");
		btnCancel.setBackground(SystemColor.controlHighlight);
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnCancel.setBounds(93, 494, 97, 25);
		contentPane.add(btnCancel);

		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblLastName.setBounds(643, 142, 113, 16);
		contentPane.add(lblLastName);

		lnameField = new JTextField();
		lnameField.setBounds(643, 181, 173, 22);
		contentPane.add(lnameField);
		lnameField.setColumns(10);
	}
	
	public void jTableMouseClicked(java.awt.event.MouseEvent evt) {
        // Get the semester at the selected row index
        int selectedRowIndex = studentTable.getSelectedRow();
        selectedStudent = tableModel.getStudentAt(selectedRowIndex);
		btnWithdrawStudent.setEnabled(true);
    }

	public void addActions(){

		btnAddStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fname=fnameField.getText();
				
				String lname=lnameField.getText();
				String ID=idField.getText();
				if(fname.equals("") || lname.equals("") || ID.equals("")) {
					alert("Can't leave it empty") ;
					return ;
				}
				Student student = null;
				try {
					student = backend.getStudentById(ID) ;
				}catch(Exception table) {
					alert(table.toString()) ;
				}
				if(student == null) {
					student = new Student(fname, lname, ID) ;
					try {
						backend.addStudent(student);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						
						e1.printStackTrace();
						
					}
				}
				try {		
					backend.addStudentToCourse(student, course);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					alert("Student Id taken, please try another one!") ;
					e1.printStackTrace();
					return ;
				}
				reload() ;
				fnameField.setText("");
				lnameField.setText("");
				idField.setText("");
			}
		});

		btnWithdrawStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selectedStudent == null) {
					alert("student not selected") ;
					return ;
				}
				try {
					backend.withdrawStudentfromCourse(selectedStudent, course);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				reload() ;
			}
		});

//		btnImportStudent.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				try {
//					jxl.Workbook wb=null;
//					InputStream is = new FileInputStream("C://Users//82585//Desktop//students.xls");
//		            wb = Workbook.getWorkbook(is);
//
//		            int sheetSize = wb.getNumberOfSheets();
//		            Sheet sheet = wb.getSheet(0);
//		            int row_total = sheet.getRows();
//		            for (int j = 0; j < row_total; j++) {
//		                if(j == 0){
//		                    Cell[] cells = sheet.getRow(j);
//		                    //System.out.println(cells[0].getContents());
//		                    //System.out.println(cells[1].getContents());
//		                    //System.out.println(cells[2].getContents());
//		                }else {
//                            Cell[] student = sheet.getRow(j);
//                            Student newStudent = new Student(student[0].getContents(), student[1].getContents(), student[2].getContents(), student[3].getContents());
//                            students.add(newStudent);
//                            try {
//								backend.addStudent(newStudent);
//								backend.addStudentToCourse(newStudent, course);
//							} catch (SQLException ex) {
//								alert(ex.toString());
//								ex.printStackTrace();
//							}
//			                //students.add(new Student(student[0].getContents(),student[1].getContents()));
//			             //   System.out.println(students.get(j-1).getFname());
//		                   // System.out.println(students.get(j-1).getSid());
//		                    //System.out.println(students.get(j-1).getEmail());
//		                }
//		            }
//		            data=new String[students.size()][3];
//		            for(int i=0;i<students.size();i++) {
//		            	data[i][1]=students.get(i).getLname();
//		            	data[i][2]=students.get(i).getSid();
//		            	data[i][3]=students.get(i).getEmail();
//          			}
//		            tableModel=new DefaultTableModel(data,columnNames);
//		            table.setModel(tableModel);
//		            alert("Add successfully!");
//		        }catch (IOException ex) {
//		            // TODO Auto-generated catch block
//		            ex.printStackTrace();
//		        } catch (BiffException ex){
//		            ex.printStackTrace();
//		        }
//			}
//		});

		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openPrevious();
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
		ViewGradeFrame prevFrame = new ViewGradeFrame(backend) ;
		prevFrame.setLocationRelativeTo(null);
		dispose();
	}
}
