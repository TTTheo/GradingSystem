package gui;

import javax.swing.*;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import backend.CourseBackend;
import objects.Semester;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
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
		final String[] columnNames = { "Year", "Term", "Number of Courses" };

		Semester demo = new Semester("Fall", 2019);
		ArrayList<Semester> data;
		try {
			data = backend.getAllSemesters();
		} catch (SQLException e) {
			alert(e.toString());
			data = new ArrayList<>();
			data.add(demo);
		}

		tableModel = new SemesterTableModel(data, columnNames);
		semesterTable = new JTable(tableModel);  // Create JTable with custom model

		semesterTable.setBounds(52, 69, 333, 221);
		semesterTable.addMouseListener(new java.awt.event.MouseAdapter() {
	            public void mouseClicked(java.awt.event.MouseEvent evt) {
	                jTableMouseClicked(evt);
	            }
	        });
		contentPane.add(semesterTable);

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
		addBtn.setBounds(400, 305, 117, 29);
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

		yearLabel = new JLabel("Year:");
		yearLabel.setBounds(30, 310, 40, 25);
		contentPane.add(yearLabel);

		yearField = new JTextField();
		yearField.setBounds(70, 310, 125, 25);
		contentPane.add(yearField);

		termLabel = new JLabel("Term: ");
		termLabel.setBounds(210, 310, 40, 25);
		contentPane.add(termLabel);

		termField = new JTextField();
		termField.setBounds(250, 310, 125, 25);
		contentPane.add(termField);

		addActions();
	}

	// Handle Add and View, TODO: delete and edit
	public void addActions(){
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
				    backend.addSemester(s);
				} catch (SQLException ex) {
					alert(ex.toString());
				}

				tableModel.addRow(s);
			}
		});
	}

	public void jTableMouseClicked(java.awt.event.MouseEvent evt) {
        // Get the semester at the selected row index
        int selectedRowIndex = semesterTable.getSelectedRow();
        Semester selectedSemester = tableModel.getSemesterAt(selectedRowIndex);

        // set the selected row data into jtextfields
        String semesterInfo = selectedSemester.toString();
        selectedSemesterField.setText(semesterInfo);
    }

	public void alert(String message){
        JOptionPane.showMessageDialog(null, message);
    }

	// Open cousre view next
	public void openNext() {
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
