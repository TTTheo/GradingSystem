package gui.grade;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import backend.Backend;
import gui.FrameActions;
import objects.Category;
import objects.Course;
import objects.Grade;
import objects.Part;
import objects.Student;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
	private Course course;//=new Course(new Semester("Spring",2019), "CS591",1);
	private Category category;//=new Category("Homework",2,"C01", "CC01",30);
	private Part part;//=new Part("P01","Homwork","C09",100,20);
	private String stuID;
	private List<Grade> grades=new ArrayList<Grade>();
	private String[][] data;//={{"Mary", "U09",""},
		//	{"Emma", "U10",""},
			//{"Jerry","U11",""}};
	private final String[] columnNames = { "Name", "ID", "Score" };
	private String editValue=new String("");
	private ArrayList<Student> students=new ArrayList<Student>();
	private Backend backend;
	
	/**
	 * Create the frame.
	 */
	public RecordGradeFrame(Backend backend) {
        this.backend = backend;

        // Get the course, category, and part that we are grading
        course = backend.getCourse();
        category = backend.getCategory();
		part = backend.getPart();
		
		//System.out.print(course.getName()+" "+category.getName()+" "+part.getName());
		setBackground(new Color(255, 248, 220));
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
		
//		grades.add(new Grade("U09","P01",0));
//		grades.add(new Grade("U10","P01",0));
//		grades.add(new Grade("U11","P01",0));
		/*String[][] datas= {{"Mary", "U09",String.valueOf(grades.get(0).getGrade())},
				{"Emma", "U10",String.valueOf(grades.get(1).getGrade())},
				{"Jerry","U11",String.valueOf(grades.get(2).getGrade())}};*/
		try {
			grades=backend.getPartGrades(part);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			students=backend.getAllStudents(course);
		} catch (SQLException e) {
			alert(e.toString());
			e.printStackTrace();
		}
				/*String[][] datas= {{"Mary", "U09",""},
				{"Emma", "U10",""},
				{"Jerry","U11",""}};*/
		/*String datas[][]=new String[students.size()][columnNames.length];
		for(int i=0;i<students.size();i++) {
			datas[i][0]=students.get(i).getFname()+" "+students.get(i).getLname();
			datas[i][1]=students.get(i).getSid();
			for(int j=0;j<grades.size();j++) {
				if(grades.get(j).getPid() == (part.getPid())&&grades.get(j).getSid().equals(datas[i][1])){
					datas[i][2]=String.valueOf(grades.get(j).getGrade());
				}
			}
		}*/
		
		data=new String[students.size()][columnNames.length];
		for(int i=0;i<students.size();i++) {
			data[i][0]=students.get(i).getFname()+" "+students.get(i).getLname();
			data[i][1]=students.get(i).getSid();
			for(int j=0;j<grades.size();j++) {
				if(grades.get(j).getPid() == (part.getPid())&&grades.get(j).getSid().equals(data[i][1])){
					data[i][2]=String.valueOf(grades.get(j).getGrade());
				}
			}
		}
		
		
		//this.data=datas;

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
		};

		table.setBackground(new Color(255, 255, 255));
		scrollPane.setViewportView(table);
		rewriteKeys();
		
		
		JLabel lblCourseCategory = new JLabel(course.getName()+" - "+category.getName()+" - "+part.getName());
		lblCourseCategory.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCourseCategory.setBounds(50, 31, 496, 28);
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
					int selectedRowIndex = table.getSelectedRow();
					if(selectedRowIndex==-1) {
						alert("Please select a student!");
					}else {
						if(!textField.getText().equals("")||!isNumeric(textField.getText())) {
							updateScore();
							alert("Save grade!");
						}else {
							alert("Please input numbers!");
						}
					}
					//System.out.println("!!!!");
				}   
			};   
			public void keyReleased(KeyEvent arg0) {    
				// TODO 
			}
			public void keyTyped(KeyEvent arg0) {    
				// TODO    
			}  
		});
		
		textField_1.addKeyListener(new KeyListener(){   
			public void keyPressed(KeyEvent arg0) {    
				if(arg0.getKeyChar()==KeyEvent.VK_ENTER){  
					//if(!textField_1.getText().equals("")) {
						String comment=textField_1.getText();
						System.out.println(comment);
						int selectedRowIndex = table.getSelectedRow();
						if(selectedRowIndex!=-1) {
					        String ID=tableModel.getValueAt(selectedRowIndex, 1).toString();
					        for(int i=0;i<grades.size();i++) {
					        	if(grades.get(i).getPid() == (part.getPid())&&grades.get(i).getSid().equals(ID)) {
					        		if(isBlankString(comment)) {
					        			grades.get(i).setComment("");
					        			//System.out.println("!!!!");
					        		}else {
					        			grades.get(i).setComment(comment);
					        		}
	//				        		gradeBack.updateGrade(grades.get(i)); TODO
					        		try {
		        						  backend.updateGrade(grades.get(i));
		        					  } catch (SQLException e1) {
		        						  // TODO Auto-generated catch block
		        						  e1.printStackTrace();
		        					 }
					        		alert("Save comment!");
					        	}
					        }
						}else {
							alert("Please choose a student!");
						}
					}			 
					//System.out.println("!!!!");
				//}   
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
				openPrevious();
			}
		});
		
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    openNext();
			}
		});
	}
	
	boolean isBlankString(String string) {    
		return string == null || string.trim().isEmpty();
	}
	
	public void rewriteKeys() {
		ActionMap down = (ActionMap)UIManager.get("Table.actionMap");
		down.put("selectNextRow", new AbstractAction(){
	          @Override
	          public void actionPerformed(ActionEvent e){
	        	  if(table.getSelectedRow()+1<table.getRowCount()){
        			  table.editCellAt(table.getSelectedRow()+1,2);
	        		  table.setRowSelectionInterval(table.getSelectedRow()+1, table.getSelectedRow()+1);
	        		  int row=table.getSelectedRow();
	        		  setNameScore();
	        		  editValue=tableModel.getValueAt(row-1, 2).toString();
		        	  //System.out.print(editValue);
		        	  if(editValue.equals("")) {
	        			  //updateScore();
	        		  }else {
		        	  stuID=tableModel.getValueAt(row-1, 1).toString();	
        			  double newScore=Double.parseDouble(editValue);
        			  int partID=part.getPid();
        			  for(int i=0;i<grades.size();i++) {
        				  if(grades.get(i).getPid() == (partID)&&grades.get(i).getSid().equals(stuID)) {
        					  grades.get(i).setGrade(newScore);
//        					  gradeBack.updateGrade(grades.get(i)); TODO
        					  try {
        						  backend.updateGrade(grades.get(i));
        					  } catch (SQLException e1) {
        						  // TODO Auto-generated catch block
        						  e1.printStackTrace();
        					  }
        					  data[i][2]=String.valueOf(newScore);
        					  tableModel=(DefaultTableModel) table.getModel();
        					  tableModel.setDataVector(data,columnNames);
        					  tableModel.fireTableStructureChanged();
        					  break;
        				  }
        			  }
        			  table.editCellAt(table.getSelectedRow(),2);
        			  table.setRowSelectionInterval(table.getSelectedRow(), table.getSelectedRow());
        			  //table.editCellAt(row,2);
        			 // System.out.println(table.getSelectedRow()+" "+table.getSelectedColumn());
	        	  }
	        	  }
	          }
		});
		table.setActionMap(down);
		
		ActionMap up = (ActionMap)UIManager.get("Table.actionMap");
		up.put("selectPreviousRow", new AbstractAction(){
	          @Override
	          public void actionPerformed(ActionEvent e){
	        	  if(table.getSelectedRow()-1>=0){
		        	  table.editCellAt(table.getSelectedRow()-1,2);
	        		  table.setRowSelectionInterval(table.getSelectedRow()-1, table.getSelectedRow()-1);
	        		  setNameScore();
	        		  int row=table.getSelectedRow();
	        		  editValue=tableModel.getValueAt(row+1, 2).toString();
		        	  //System.out.print(editValue);
		        	  if(editValue.equals("")) {
	        			  //updateScore();
	        		  }else {
		        	  stuID=tableModel.getValueAt(table.getSelectedRow()+1, 1).toString();	
        			  double newScore=Double.parseDouble(editValue);
        			  int partID=part.getPid();
        			  for(int i=0;i<grades.size();i++) {
        				  if(grades.get(i).getPid() == (partID)&&grades.get(i).getSid().equals(stuID)) {
        					  grades.get(i).setGrade(newScore);
//        					  gradeBack.updateGrade(grades.get(i)); TODO
        					  try {
        						  backend.updateGrade(grades.get(i));
        					  } catch (SQLException e1) {
        						  // TODO Auto-generated catch block
        						  e1.printStackTrace();
        					  }
        					  data[i][2]=String.valueOf(newScore);
        					  //table.updateUI();
        					  tableModel=(DefaultTableModel) table.getModel();
        					  tableModel.setDataVector(data,columnNames);
        					  tableModel.fireTableStructureChanged();
        					  break;
        				  }
        			  }
        			  table.setRowSelectionInterval(row, row);
        			  table.editCellAt(row,2);
	        		  }
		        	  
	        	  }
	          }
	          
		});
		table.setActionMap(up);

	}
	
	public void setNameScore() {
   	 	// Get the semester at the selected row index
        int selectedRowIndex = table.getSelectedRow();
        String selectedName = tableModel.getValueAt(selectedRowIndex,0).toString();
        if(tableModel.getValueAt(selectedRowIndex, 2)!=null) {
        	String selectedScore=tableModel.getValueAt(selectedRowIndex, 2).toString();
        	textField.setText(selectedScore);
        }
        stuID=tableModel.getValueAt(selectedRowIndex, 1).toString();
        for(int i=0;i<grades.size();i++) {
        	if(grades.get(i).getPid() == (part.getPid())&&grades.get(i).getSid().equals(stuID)) {
        		String comment=grades.get(i).getComment();
        		//System.out.println(comment);
        		if(comment==null&&comment.equals("")) {
        			textField_1.setText(null);
        		}else {
        			textField_1.setText(comment);
        		}
        	}
        }
        // set the selected row data into jtextfields
        textField_2.setText(selectedName);
        
	}
	



	public static boolean isNumeric(String str)
	{
	  try{
	     Integer.parseInt(str);
	     return true;
	  }catch(NumberFormatException e){
		  return false;
	  }
	}


	
	public void updateScore() {
		int selectedRowIndex = table.getSelectedRow();
		stuID=tableModel.getValueAt(selectedRowIndex, 1).toString();
		String stuName=textField_2.getText();
		String score=textField.getText();
			double newScore=Double.valueOf(textField.getText());
			int partID=part.getPid();
			//System.out.println(partID+stuID);
			for(int i=0;i<grades.size();i++) {
				if(grades.get(i).getPid() == partID &&grades.get(i).getSid().equals(stuID)) {	
					//System.out.println(newScore);
					grades.get(i).setGrade(newScore);
	//				gradeBack.updateGrade(grades.get(i)); TODO
					 try {
						  backend.updateGrade(grades.get(i));
					  } catch (SQLException e1) {
						  // TODO Auto-generated catch block
						  e1.printStackTrace();
					  }
					 
					this.data[i][2]=String.valueOf(newScore);
					//System.out.println(this.data[i][2]);
					tableModel=(DefaultTableModel) table.getModel();
					tableModel.setDataVector(data,columnNames);
					tableModel.fireTableStructureChanged();
					break;
				}
			}
		
		//Grade newgrade=new Grade(stuID,partID,newScore);
	}

	
	public void alert(String message){
        JOptionPane.showMessageDialog(null, message);
    }

	public void openNext() {
		int selectedRowIndex = table.getSelectedRow();
		String ID=tableModel.getValueAt(selectedRowIndex, 1).toString();
		if(selectedRowIndex!=-1) {
			updateScore();
			//if(!textField_1.getText().equals("")) {
				String comment=textField_1.getText();
				if(comment!=null) {
			        for(int i=0;i<grades.size();i++) {
			        	if(grades.get(i).getPid() == (part.getPid())&&grades.get(i).getSid().equals(ID)) {
			        		if(isBlankString(comment)) {
			        			grades.get(i).setComment("");
			        			System.out.println(".....");
			        		}else {
			        			grades.get(i).setComment(comment);
			        		}
			        		//grades.get(i).setComment(comment);
	//		        		gradeBack.updateGrade(grades.get(i)); TODO
			        		try {
								  backend.updateGrade(grades.get(i));
							  } catch (SQLException e1) {
								  // TODO Auto-generated catch block
								  e1.printStackTrace();
							  }
			        		//alert("Save comment!");
			        	}
			        }
				}
			}	
		//}
		ViewGradeFrame next = new ViewGradeFrame(backend);
		next.setVisible(true);
		dispose();
	}

	// This is the first window, no previous window exists
	public void openPrevious() {
		ViewGradeFrame next=new ViewGradeFrame(backend);
		next.setVisible(true);
		dispose();
	}
}

