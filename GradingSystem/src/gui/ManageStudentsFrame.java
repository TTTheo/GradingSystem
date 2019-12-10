package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

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
	private final String[] columnNames= {"Name","ID","Email"};
	private List<Student> students=new ArrayList<Student>();
	private Course course=new Course("CS591",1);
	/**
	 * Create the frame.
	 */
	public ManageStudentsFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		data=new String[students.size()][3];
		for(int i=0;i<students.size();i++) {
			data[i][0]=students.get(i).getFname();
			data[i][1]=students.get(i).getSid();
			data[i][2]=students.get(i).getEmail();
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
		
		lblName = new JLabel("Name:");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblName.setBounds(653, 63, 56, 16);
		contentPane.add(lblName);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField.setBounds(647, 92, 173, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		lblId = new JLabel("ID:");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblId.setBounds(653, 127, 78, 19);
		contentPane.add(lblId);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField_1.setBounds(647, 159, 173, 22);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblEmail.setBounds(653, 199, 78, 23);
		contentPane.add(lblEmail);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField_2.setBounds(647, 233, 173, 22);
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
	}
	
	public void addActions(){		
		table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int selectedRowIndex = table.getSelectedRow();
                String selectedName = tableModel.getValueAt(selectedRowIndex,0).toString();
                String selectedID=tableModel.getValueAt(selectedRowIndex,1).toString();
                String selectedEmail=tableModel.getValueAt(selectedRowIndex, 2).toString();
                textField.setText(selectedName);
                textField_1.setText(selectedID);
                textField_2.setText(selectedEmail);
        	}
        });
		
		btnAddStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=textField.getText();
				String ID=textField_1.getText();
				String Email=textField_2.getText();
				int rowIndex = students.size();
				students.add(new Student(name,ID,Email));
				data=new String[students.size()][3];
				for(int i=0;i<students.size();i++) {
					data[i][0]=students.get(i).getFname();
					data[i][1]=students.get(i).getSid();
					data[i][2]=students.get(i).getEmail();
				}
				tableModel=new DefaultTableModel(data,columnNames);
				table.setModel(tableModel);
			}
		});
		
		btnDeleteStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  int selectedRowIndex = table.getSelectedRow();
	              String selectedID=tableModel.getValueAt(selectedRowIndex,1).toString();
	              for(int j=0;j<students.size();j++) {
	            	  if(students.get(j).getSid().equals(selectedID)) {
	            		  students.remove(j);
	            		  data=new String[students.size()][3];
	            		  for(int i=0;i<students.size();i++) {
	            			  data[i][0]=students.get(i).getFname();
	            			  data[i][1]=students.get(i).getSid();
	            			  data[i][2]=students.get(i).getEmail();
	            		  }
	            		  tableModel=new DefaultTableModel(data,columnNames);
	            		  table.setModel(tableModel);
	            		  break;
	            	  }
	              }
			}
		});
		
		btnWithdrawStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 int selectedRowIndex = table.getSelectedRow();
				 String selectedID=tableModel.getValueAt(selectedRowIndex,1).toString();
				 for(int j=0;j<students.size();j++) {
					 if(students.get(j).getSid().equals(selectedID)) {
						 students.remove(j);
	            		  data=new String[students.size()][3];
	            		  for(int i=0;i<students.size();i++) {
	            			  data[i][0]=students.get(i).getFname();
	            			  data[i][1]=students.get(i).getSid();
	            			  data[i][2]=students.get(i).getEmail();
	            		  }
	            		  tableModel=new DefaultTableModel(data,columnNames);
	            		  table.setModel(tableModel);
	            		  break;
					 }
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
		                    System.out.println(cells[0].getContents());
		                    System.out.println(cells[1].getContents());
		                    System.out.println(cells[2].getContents());
		                }else {
			                Cell[] student=sheet.getRow(j);
			                students.add(new Student(student[0].getContents(),student[1].getContents(),student[2].getContents()));
			                System.out.println(students.get(j-1).getFname());
		                    System.out.println(students.get(j-1).getSid());
		                    System.out.println(students.get(j-1).getEmail());
		                }
		            }
		            data=new String[students.size()][3];
		            for(int i=0;i<students.size();i++) {
	            		data[i][0]=students.get(i).getFname();
	            		data[i][1]=students.get(i).getSid();
	            		data[i][2]=students.get(i).getEmail();
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
