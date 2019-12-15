package gui;

import javax.swing.*;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;


import backend.* ;
import objects.Semester;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.SystemColor;
public class SemesterFrame extends JFrame implements FrameActions {

	private JPanel contentPane;

	private JTable semesterTable ;
	private SemesterTableModel tableModel ;

	private JLabel SemesterMenuLabel ;
	private JLabel selectedSemesterLabel ;

	private JLabel yearLabel ;
	private JLabel termLabel ;
	private JTextField yearField;
	private JTextField termField;

	private JButton viewBtn ;
	private JButton editBtn ;
	private JButton deleteBtn ;
	private JButton addBtn ;
	private JScrollPane semesterTableScrollPane ;
	private JTextField selectedSemesterField;
	private Backend backend = new Backend();
	private Semester selectedSemester;
	private JButton btnBack;
	private ArrayList<Semester> data=new ArrayList<Semester>();
	private final String[] columnNames = { "Year", "Term"};
	/**
	 * Create the frame.
	 */
	public SemesterFrame(Backend backend) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 893, 574);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.backend=backend;

		//Semester demo = new Semester("Fall", 2019);
		try {
			data = backend.getAllSemesters();
		} catch (SQLException e) {
			alert(e.toString());
			//data = new ArrayList<>();
			//data.add(demo);
		}

		tableModel = new SemesterTableModel(data, columnNames);
		semesterTable = new JTable(tableModel);  // Create JTable with custom model
		semesterTable.setFont(new Font("Tahoma", Font.PLAIN, 17));

		semesterTable.setBounds(52, 69, 333, 221);
		semesterTable.addMouseListener(new java.awt.event.MouseAdapter() {
	            public void mouseClicked(java.awt.event.MouseEvent evt) {
	                jTableMouseClicked(evt);
	            }
	        });
		contentPane.add(semesterTable);

		SemesterMenuLabel = new JLabel("Semester Menu");
		SemesterMenuLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		SemesterMenuLabel.setBounds(31, 27, 135, 37);
		contentPane.add(SemesterMenuLabel);

		viewBtn = new JButton("View");
		viewBtn.setBackground(SystemColor.controlHighlight);
		viewBtn.setFont(new Font("Tahoma", Font.PLAIN, 17));
		viewBtn.setBounds(700, 142, 130, 37);
		contentPane.add(viewBtn);

		editBtn = new JButton("Edit");
		editBtn.setBackground(SystemColor.controlHighlight);
		editBtn.setFont(new Font("Tahoma", Font.PLAIN, 17));
		editBtn.setBounds(700, 207, 130, 37);
		contentPane.add(editBtn);

		addBtn = new JButton("Add new");
		addBtn.setBackground(SystemColor.controlHighlight);
		addBtn.setFont(new Font("Tahoma", Font.PLAIN, 17));
		addBtn.setBounds(513, 429, 117, 37);
		contentPane.add(addBtn);

		deleteBtn = new JButton("Delete");
		deleteBtn.setBackground(SystemColor.controlHighlight);
		deleteBtn.setFont(new Font("Tahoma", Font.PLAIN, 17));
		deleteBtn.setBounds(700, 273, 130, 37);
		contentPane.add(deleteBtn);

		semesterTableScrollPane = new JScrollPane(semesterTable);
		semesterTableScrollPane.setBounds(31, 69, 599, 336);
		contentPane.add(semesterTableScrollPane);

		selectedSemesterField = new JTextField();
		selectedSemesterField.setFont(new Font("Tahoma", Font.PLAIN, 17));
		selectedSemesterField.setEditable(false);
		selectedSemesterField.setBounds(700, 92, 130, 26);
		contentPane.add(selectedSemesterField);
		selectedSemesterField.setColumns(10);

		selectedSemester = null;
		selectedSemesterLabel = new JLabel("Selected Semester");
		selectedSemesterLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		selectedSemesterLabel.setBounds(700, 53, 163, 26);
		contentPane.add(selectedSemesterLabel);

		yearLabel = new JLabel("Year:");
		yearLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		yearLabel.setBounds(31, 431, 70, 25);
		contentPane.add(yearLabel);

		yearField = new JTextField();
		yearField.setFont(new Font("Tahoma", Font.PLAIN, 17));
		yearField.setBounds(96, 431, 125, 27);
		contentPane.add(yearField);

		termLabel = new JLabel("Term: ");
		termLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		termLabel.setBounds(255, 431, 60, 25);
		contentPane.add(termLabel);

		termField = new JTextField();
		termField.setFont(new Font("Tahoma", Font.PLAIN, 17));
		termField.setBounds(327, 429, 125, 29);
		contentPane.add(termField);

		viewBtn.setEnabled(false);  // enabled when a semester is selected
		
		btnBack = new JButton("Back");
		btnBack.setBackground(SystemColor.controlHighlight);
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnBack.setBounds(700, 429, 130, 37);
		contentPane.add(btnBack);
		addActions();
	}

	// Handle Add and View, TODO: delete and edit
	public void addActions(){	
		editBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<semesterTable.getRowCount();i++) {
					String newyear=(String) semesterTable.getValueAt(i, 0);
					String newterm=(String) semesterTable.getValueAt(i, 1);
					boolean semesterExist=false;
					for(Semester semester:data) {
						if(semester.getTerm().equals(newterm)&&semester.getYear().equals(Integer.parseInt(newyear))) {
							alert("This semester exist!");
							semesterExist=true;
						}
					}
					if(!semesterExist) {
						data.get(i).setTerm(newterm);
						data.get(i).setYear(Integer.parseInt(newyear));
						try {
							backend.updateSemester(data.get(i));
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
				
				
			}
		});
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String term = termField.getText().trim();
				String year = yearField.getText().trim();

				// Input validation
				if (!year.matches("\\d+")) {
					alert("Not a valid year");
					return;
				}
				if (!Semester.isValidTerm(term)) {
					alert("Not a valid term. Please enter (Summer, Spring, Fall, Winter)");
					return;
				}

				// Capitalize term
				term = term.substring(0,1).toUpperCase() + term.substring(1).toLowerCase();

				Semester s = new Semester(term, Integer.parseInt(year));
				try {
					ArrayList<Semester> sem=backend.getAllSemesters();
					boolean semesterExist=false;
					for(int i=0;i<sem.size();i++) {
						if(sem.get(i).getTerm().equals(term)&&(sem.get(i).getYear().equals(Integer.parseInt(year)))) {
							alert("This semester exist!");
							semesterExist=true;
							break;
						}
					}
					if(!semesterExist) {
						backend.addSemester(s);
						tableModel.addRow(s);
					}
				} catch (SQLException ex) {
					alert(ex.toString());
				}

			}
		});

		viewBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backend.setSemester(selectedSemester);
			    openNext();
			}
		});
		
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        int selectedRowIndex = semesterTable.getSelectedRow();
		        selectedSemester = tableModel.getSemesterAt(selectedRowIndex);
		        // set the selected row data into jtextfields
		        String semesterInfo = selectedSemester.toString();
		        selectedSemesterField.setText(semesterInfo);
				try {
				   backend.deleteSemester(selectedSemester);
				} catch (SQLException ex) {
					alert(ex.toString());
				}
				tableModel.deleteRow(selectedSemester);
			}
		});
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openPrevious();
			}
		});
	}

	public void jTableMouseClicked(java.awt.event.MouseEvent evt) {
        // Get the semester at the selected row index
        int selectedRowIndex = semesterTable.getSelectedRow();
        selectedSemester = tableModel.getSemesterAt(selectedRowIndex);

        // set the selected row data into jtextfields
        String semesterInfo = selectedSemester.toString();
        selectedSemesterField.setText(semesterInfo);
		viewBtn.setEnabled(true);
    }

	public void alert(String message){
        JOptionPane.showMessageDialog(null, message);
    }

	// Open course view next
	public void openNext() {
		AllCoursesFrame c = new AllCoursesFrame(backend);
		c.setVisible(true);
		dispose();
	}

	// Go back to the login page
	public void openPrevious() {
	    LoginFrame login = new LoginFrame();
	    login.setVisible(true);
		dispose();
	}

//	@Override
//	public void tableChanged(TableModelEvent e) {
//		int row = e.getFirstRow();
//		int column = e.getColumn();
////		TableModel model = (TableModel)e.getSource();
//		String columnName = tableModel.getColumnName(column);
//		Object data = tableModel.getValueAt(row, column);
//
//		// Do something with the data...
//	}
}
