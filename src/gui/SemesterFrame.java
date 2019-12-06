package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import backend.CourseBackend;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
public class SemesterFrame extends JFrame implements FrameActions{

	private JPanel contentPane;
	private JTable semesterTable ;
	private JLabel SemesterMenuLabel ;
	private JLabel selectedSemesterLabel ;
	private JButton viewBtn ;
	private JButton editBtn ;
	private JButton deleteBtn ;
	private JButton addBtn ;
	private JScrollPane semesterTableScrollPane ;
	private JTextField selectedSemesterField;
	private CourseBackend backend = new CourseBackend();

	/**
	 * Create the frame.
	 */
	public SemesterFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		String[][] data = {{"2019", "Fall","1"},{"2020", "Spring","1"} }; 
		String[] columnNames = { "Year", "Season", "Number of Courses" };

		semesterTable = new JTable(data, columnNames);
		DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {
			@Override
			public boolean isCellEditable(int row, int column) {
				//all cells false
				return false;
			}
		};
		semesterTable.setModel(tableModel);

		semesterTable.setBounds(52, 69, 333, 221);
		semesterTable.addMouseListener(new java.awt.event.MouseAdapter() {
	            public void mouseClicked(java.awt.event.MouseEvent evt) {
	                jTableMouseClicked(evt);
	            }
	        });
//		contentPane.add(semesterTable);
		
		SemesterMenuLabel = new JLabel("Semester Menu");
		SemesterMenuLabel.setBounds(57, 20, 135, 37);
		contentPane.add(SemesterMenuLabel);
		
		viewBtn = new JButton("View");
		viewBtn.setBounds(426, 125, 117, 29);
		contentPane.add(viewBtn);
		
		editBtn = new JButton("Edit...");
		editBtn.setBounds(426, 166, 117, 29);
		contentPane.add(editBtn);
		
		addBtn = new JButton("Add new");
		addBtn.setBounds(426, 247, 117, 29);
		contentPane.add(addBtn);
		
		deleteBtn = new JButton("Delete");
		deleteBtn.setBounds(426, 207, 117, 29);
		contentPane.add(deleteBtn);
		
		semesterTableScrollPane = new JScrollPane(semesterTable);
		semesterTableScrollPane.setBounds(31, 69, 362, 221);
		contentPane.add(semesterTableScrollPane);
		
		selectedSemesterField = new JTextField();
		selectedSemesterField.setEditable(false);
		selectedSemesterField.setBounds(426, 87, 130, 26);
		contentPane.add(selectedSemesterField);
		selectedSemesterField.setColumns(10);
		
		selectedSemesterLabel = new JLabel("Selected Semester");
		selectedSemesterLabel.setBounds(431, 69, 123, 16);
		contentPane.add(selectedSemesterLabel);
		init();
		addActions();
	}
	
	public void init(){
	}
	
	public void addActions(){
	}
	public void jTableMouseClicked(java.awt.event.MouseEvent evt) {                                     
        
        // get the model from the jtable
       TableModel model = semesterTable.getModel();
       

        // get the selected row index
       int selectedRowIndex = semesterTable.getSelectedRow();
       
        // set the selected row data into jtextfields
       String selected = model.getValueAt(selectedRowIndex, 0).toString() + " " + model.getValueAt(selectedRowIndex, 1).toString() ;
       selectedSemesterField.setText(selected);
        
    }                              
	public void alert(String message){
        JOptionPane.showMessageDialog(null, message);
    }

	// Open ?? next
	public void openNext() {
		dispose();
	}

	// Go back to the login page
	public void openPrevious() {
	    LoginFrame login = new LoginFrame();
	    login.setVisible(true);
		dispose();
	}
}
