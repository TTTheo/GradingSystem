package gui.grade;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import gui.CourseMenuFrame;
import gui.FrameActions;
import gui.SemesterFrame;
import objects.Category;
import objects.Course;
import objects.Grade;
import objects.Part;
import objects.Semester;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Component;

public class RecordGradeFrame extends JFrame implements FrameActions{

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblStudentsName;
	private JLabel lblScore;
	private JLabel lbltotalScore;
	private JLabel lblComment;
	private JButton btnPrevious;	
	private JButton btnNext;
	private JTextField textField_2;
	private JTable table;
	private DefaultTableModel tableModel;
	private Course course=new Course("CS591",1);
	private Category category=new Category("Homework",2,"C01", "CC01",30);
	private Part part=new Part("P01","Homwork","C09",100,20);
	private String stuID;
	private List<Grade> grades=new ArrayList<Grade>();
	private String[][] data={{"Mary", "U09",""},
			{"Emma", "U10",""},
			{"Jerry","U11",""}};
	private final String[] columnNames = { "Name", "ID", "Score" };
	private String editValue=new String("");
	
	/**
	 * Create the frame.
	 */
	public RecordGradeFrame() {
		setBackground(new Color(255, 248, 220));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 933, 584);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		init();
		addActions();
	}
	
	public void init(){
		lblStudentsName = new JLabel("Selected Student: ");
		lblStudentsName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblStudentsName.setBounds(619, 88, 175, 15);
		contentPane.add(lblStudentsName);
		
		lblScore = new JLabel("Score:");
		lblScore.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblScore.setBounds(619, 178, 83, 15);
		contentPane.add(lblScore);
		
		textField = new JTextField();
		textField.setBounds(619, 206, 118, 32);
		contentPane.add(textField);
		textField.setColumns(10);
		
		lbltotalScore = new JLabel("/total score");
		lbltotalScore.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbltotalScore.setBounds(749, 210, 113, 15);
		contentPane.add(lbltotalScore);
		lbltotalScore.setText("/ "+String.valueOf(part.getTotalScore()));
		
		lblComment = new JLabel("Comment:");
		lblComment.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblComment.setBounds(619, 257, 102, 15);
		contentPane.add(lblComment);
		
		textField_1 = new JTextField();
		textField_1.setBounds(619, 285, 243, 160);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		btnPrevious = new JButton("Cancel");
		btnPrevious.setForeground(new Color(0, 0, 0));
		btnPrevious.setBackground(new Color(211, 211, 211));
		btnPrevious.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnPrevious.setBounds(50, 471, 118, 32);
		contentPane.add(btnPrevious);
		
		btnNext = new JButton("Save");
		btnNext.setBackground(new Color(192, 192, 192));
		btnNext.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNext.setBounds(749, 471, 113, 32);
		contentPane.add(btnNext);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setBounds(619, 116, 243, 32);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 72, 514, 374);
		contentPane.add(scrollPane);
		

		//Semester demo = new Semester("Fall", 2019);
		//ArrayList<Semester> data;
		/*try {
			data = backend.getAllSemesters();
		} catch (SQLException e) {
			alert(e.toString());
			data = new ArrayList<>();
			data.add(demo);
		}*/
		
		grades.add(new Grade("U09","P01",0));
		grades.add(new Grade("U10","P01",0));
		grades.add(new Grade("U11","P01",0));
		/*String[][] datas= {{"Mary", "U09",String.valueOf(grades.get(0).getGrade())},
				{"Emma", "U10",String.valueOf(grades.get(1).getGrade())},
				{"Jerry","U11",String.valueOf(grades.get(2).getGrade())}};*/
		String[][] datas= {{"Mary", "U09",""},
				{"Emma", "U10",""},
				{"Jerry","U11",""}};
		this.data=datas;

		tableModel=new DefaultTableModel(this.data,columnNames){
			 public boolean isCellEditable(int row, int column){
				 if(column==2) {
					 return true;
				 }else {
					 return false;
				 }
             }  
			 

		};
		table = new JTable(tableModel) {
			protected boolean processKeyBinding(KeyStroke ks, KeyEvent e, int condition, boolean pressed) {
                boolean retValue = super.processKeyBinding(ks, e, condition, pressed);
                // by setting the client property JTable.autoStartsEdit to Boolean.FALSE.
                if (!retValue && condition == WHEN_ANCESTOR_OF_FOCUSED_COMPONENT &&isFocusOwner() && !Boolean.FALSE.equals(getClientProperty("JTable.autoStartsEdit"))) {
                    // We do not have a binding for the event.
                    Component editorComponent = getEditorComponent();
                    if (editorComponent == null) {
                    // Only attempt to install the editor on a KEY_PRESSED,
                        if (e == null) {
                            return false;
                        }
                    // Don't start when just a modifier is pressed
                        int code = e.getKeyCode();
                        if (code == KeyEvent.VK_SHIFT || code == KeyEvent.VK_CONTROL ||
                            code == KeyEvent.VK_ALT) {
                            return false;
                        }
                        // Try to install the editor
                        int leadRow = getSelectionModel().getLeadSelectionIndex();
                        int leadColumn = getColumnModel().getSelectionModel().getLeadSelectionIndex();
                        if (leadRow != -1 && leadColumn != -1 && !isEditing()) {
                            if (!editCellAt(leadRow, leadColumn, e)) {
                                return false;
                            }
                        }
                        editorComponent = getEditorComponent();
                        if (editorComponent == null) {
                            return false;
                        }
                    }
                    if (editorComponent instanceof JComponent) {
                        JComponent jcomponent = (JComponent) editorComponent;
                        try {
                            Method m = JComponent.class.getDeclaredMethod("processKeyBinding", new Class[] {
                                    KeyStroke.class, KeyEvent.class, int.class, boolean.class
                            });
                            m.setAccessible(true);
                            Object v = m.invoke(jcomponent, new Object[] { ks, e, WHEN_FOCUSED, pressed});
                            retValue = (boolean) v;
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        if (getSurrendersFocusOnKeystroke()) {
                            editorComponent.requestFocus();
                        }
                    }
                }
                return retValue;
            }
			
			//private static final long serialVersionUID = 1L;
			public void editingStopped(ChangeEvent changeevent)
		    {
				int r=getEditingRow();
				int c=getEditingColumn();
				//System.out.println(r+"--"+c);
				editValue = (String)table.getValueAt(r, c);
				//System.out.println(editValue);
		        TableCellEditor tablecelleditor = getCellEditor();
		        if(tablecelleditor != null)
		        {
		            Object obj = tablecelleditor.getCellEditorValue();
		            setValueAt(obj, editingRow, editingColumn);
		            
		            //setNameScore();
		            String stuName=textField_2.getText();
		    		//Double newScore=Double.valueOf(textField.getText());		
		    		Double newScore=Double.parseDouble(obj.toString());	
		    		//System.out.println(newScore);
		    		String partID=part.getPid();
		    		for(int i=0;i<grades.size();i++) {
		    			if(grades.get(i).getPid().equals(partID)&&grades.get(i).getSid().equals(stuID)) {
		    				grades.get(i).setGrade(newScore);
		    				break;
		    			}
		    		}
		    		
		            removeEditor();
		        }
		    }
		};

		table.setBackground(new Color(255, 255, 255));
		scrollPane.setViewportView(table);
		
		ActionMap down = (ActionMap)UIManager.get("Table.actionMap");
		down.put("selectNextRow", new AbstractAction(){
	          @Override
	          public void actionPerformed(ActionEvent e){
	        	  if(table.getSelectedRow()+1<table.getRowCount()){
	        		  int row=table.getSelectedRow()+1;
	        		  int column=table.getSelectedColumn();
	        		  //table.editCellAt(table.getSelectedRow()+1,table.getSelectedColumn());
	        		  //table.setRowSelectionInterval(table.getSelectedRow()+1, table.getSelectedRow()+1);
	        		  table.editCellAt(row,column);
	        		  table.setRowSelectionInterval(row, row);
	        		  setNameScore();
	        	  }
	          }
		});
		table.setActionMap(down);
		
		ActionMap up = (ActionMap)UIManager.get("Table.actionMap");
		up.put("selectPreviousRow", new AbstractAction(){
	          @Override
	          public void actionPerformed(ActionEvent e){
	        	  if(table.getSelectedRow()-1>=0){
	        		  if(!editValue.equals("")) {
	        			  //updateScore();
	        		  }	        		 
	        		  table.editCellAt(table.getSelectedRow()-1,table.getSelectedColumn());
	        		  table.setRowSelectionInterval(table.getSelectedRow()-1, table.getSelectedRow()-1);
	        		  setNameScore();
	        	  }
	          }
		});
		table.setActionMap(up);

		
		JLabel lblCourseCategory = new JLabel(course.getName()+" - "+category.getName()+" - "+part.getName());
		lblCourseCategory.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCourseCategory.setBounds(50, 31, 252, 28);
		contentPane.add(lblCourseCategory);
	}
	
	
	public void addActions(){
		table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            	setNameScore();
            }
        });
		
		 textField.addKeyListener(new KeyListener(){   
			 public void keyPressed(KeyEvent arg0) {    
				 if(arg0.getKeyChar()==KeyEvent.VK_ENTER){  
	        		  if(!textField.getText().equals("")) {
	        			  updateScore();
	        		  }			 
					 System.out.println("!!!!");
				 }   
			 };   
			 public void keyReleased(KeyEvent arg0) {    
				 // TODO 
			 }
			 public void keyTyped(KeyEvent arg0) {    
				 // TODO    
			 }  
		 });

		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	}
	
	public void setNameScore() {
   	 	// Get the semester at the selected row index
        int selectedRowIndex = table.getSelectedRow();
        String selectedName = tableModel.getValueAt(selectedRowIndex,0).toString();
        String selectedScore=tableModel.getValueAt(selectedRowIndex, 2).toString();
        stuID=tableModel.getValueAt(selectedRowIndex, 1).toString();
        // set the selected row data into jtextfields
        textField_2.setText(selectedName);
        textField.setText(selectedScore);
	}
	
	public void updateScore() {
		String stuName=textField_2.getText();
		Double newScore=Double.valueOf(textField.getText());		
		String partID=part.getPid();
		for(int i=0;i<grades.size();i++) {
			if(grades.get(i).getPid().equals(partID)&&grades.get(i).getSid().equals(stuID)) {
				grades.get(i).setGrade(newScore);
				this.data[i][2]=String.valueOf(newScore);
				DefaultTableModel dtm2=(DefaultTableModel)table.getModel();
				dtm2.setDataVector(this.data,columnNames);
				dtm2.fireTableStructureChanged();//jt.updateUI();
				break;
			}
		}
		//Grade newgrade=new Grade(stuID,partID,newScore);
	}
	
	public void updateScoretable() {
		String stuName=textField_2.getText();
		//Double newScore=Double.valueOf(textField.getText());		
		Double newScore=Double.valueOf(editValue);	
		String partID=part.getPid();
		for(int i=0;i<grades.size();i++) {
			if(grades.get(i).getPid().equals(partID)&&grades.get(i).getSid().equals(stuID)) {
				grades.get(i).setGrade(newScore);
				this.data[i][2]=String.valueOf(newScore);
				DefaultTableModel dtm2=(DefaultTableModel)table.getModel();
				dtm2.setDataVector(this.data,columnNames);
				dtm2.fireTableStructureChanged();//jt.updateUI();
				break;
			}
		}
		//Grade newgrade=new Grade(stuID,partID,newScore);
	}
	
	public void alert(String message){
        JOptionPane.showMessageDialog(null, message);
    }

	// Open the semester frame next
	public void openNext() {
		CourseMenuFrame next = new CourseMenuFrame();
		next.setVisible(true);
		dispose();
	}

	// This is the first window, no previous window exists
	public void openPrevious() {
		dispose();
	}
}
