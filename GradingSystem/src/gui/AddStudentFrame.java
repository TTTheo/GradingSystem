package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import objects.Student;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.junit.Test;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class AddStudentFrame extends JFrame implements FrameActions{

	private JPanel contentPane;
	private JButton btnAddMultipleStudents;	
	private JButton btnAddASingle;
	private List<Student> students=new ArrayList<Student>();
	/**
	 * Create the frame.
	 */
	public AddStudentFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 347, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		init();
		addActions();
	}
	
	public void init(){
		btnAddMultipleStudents = new JButton("Add Multiple Students");
		btnAddMultipleStudents.setBounds(68, 89, 200, 23);
		contentPane.add(btnAddMultipleStudents);
		
		btnAddASingle = new JButton("Add Single Student");
		btnAddASingle.setBounds(68, 166, 200, 23);
		contentPane.add(btnAddASingle);
	}
	
	public void addActions(){
		btnAddMultipleStudents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//File file=new File("");
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
			                students.add(new Student(student[0].getContents(),student[1].getContents(),student[2].getContents(),null));
			                System.out.println(students.get(j-1).getFname());
		                    System.out.println(students.get(j-1).getLname());
		                    System.out.println(students.get(j-1).getSid());
		                }
		            }
		            alert("Add successfully!");
		        }catch (IOException ex) {
		            // TODO Auto-generated catch block
		            ex.printStackTrace();
		        } catch (BiffException ex){
		            ex.printStackTrace();
		        }
			}
		});
		
	
		
		btnAddASingle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddSingleStuFrame addstu=new AddSingleStuFrame(students);
				addstu.setVisible(true);
				setStudents(addstu.getStudents());
			}
		});
	}
	
	public void setStudents(List<Student> students) {
		this.students=students;
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
