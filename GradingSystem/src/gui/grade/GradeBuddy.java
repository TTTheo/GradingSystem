package gui.grade;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

import backend.Backend;
import gui.*;

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
import java.awt.SystemColor;
import java.util.Random;



// The only class worthy of wielding the application's name.
public class GradeBuddy extends JFrame implements FrameActions {
    private JPanel contentPane;
    private JTable table;
    private JLabel lblCourseName;
    private JScrollPane scrollPane;
    private JScrollPane scrollPane_1;
    private JPanel panel;
    private GradeModel tableModel;
    private JButton btnApply;
    private ArrayList<JCheckBox> check=new ArrayList<JCheckBox>();
    private ArrayList<Category> category=new ArrayList<Category>();
    private String[] columnNames;
    private String[][] data;
    private ArrayList<Student> students=new ArrayList<Student>();
    private ArrayList<Grade> grades=new ArrayList<Grade>();
    private ArrayList<Part> parts=new ArrayList<Part>();

    ArrayList<Part> allParts = new ArrayList<>();
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

    private Student selectedStudent = null;

    private Backend backend;
    public GradeBuddy(Backend backend) {
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

    public void init() {
        try {
            allParts = backend.getParts(course);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ArrayList<String> columnNames = new ArrayList<>();
        columnNames.add("Name");
        for (Part p: allParts) {
            columnNames.add(p.getName() + " (" + p.getTotalScore() + ")");
        }
        columnNames.add("Final Grade");
        tableModel = new GradeModel(backend, columnNames);
        table = new JTable(tableModel);

        lblCourseName = new JLabel("Course name");
        lblCourseName.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblCourseName.setBounds(62, 35, 118, 16);
        contentPane.add(lblCourseName);
        lblCourseName.setText(course.getName());

        scrollPane = new JScrollPane();
        scrollPane.setBounds(190, 78, 617, 402);
        contentPane.add(scrollPane);

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

        btnApply = new JButton("Collapse");
        btnApply.setBackground(SystemColor.controlHighlight);
        btnApply.setFont(new Font("Tahoma", Font.PLAIN, 17));
        btnApply.setBounds(51, 497, 97, 29);
        contentPane.add(btnApply);


        btnCalculateFinal = new JButton("Add Student");
        btnCalculateFinal.setBackground(SystemColor.controlHighlight);
        btnCalculateFinal.setFont(new Font("Tahoma", Font.PLAIN, 17));
        btnCalculateFinal.setBounds(843, 171, 160, 29);
        contentPane.add(btnCalculateFinal);

        btnCurveFinalGrade = new JButton("Delete Student");
        btnCurveFinalGrade.setBackground(SystemColor.controlHighlight);
        btnCurveFinalGrade.setFont(new Font("Tahoma", Font.PLAIN, 17));
        btnCurveFinalGrade.setBounds(843, 223, 160, 29);
        contentPane.add(btnCurveFinalGrade);

        btnCancel = new JButton("Back");
        btnCancel.setBackground(SystemColor.controlHighlight);
        btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 17));
        btnCancel.setBounds(843, 497, 160, 29);
        contentPane.add(btnCancel);

        btnEditStudents = new JButton("Edit Students");
        btnEditStudents.setBackground(SystemColor.controlHighlight);
        btnEditStudents.setFont(new Font("Tahoma", Font.PLAIN, 17));
        btnEditStudents.setBounds(843, 317, 160, 29);
        contentPane.add(btnEditStudents);

        btnEditCategory = new JButton("Grade Rubric");
        btnEditCategory.setBackground(SystemColor.controlHighlight);
        btnEditCategory.setFont(new Font("Tahoma", Font.PLAIN, 17));
        btnEditCategory.setBounds(843, 371, 160, 29);
        contentPane.add(btnEditCategory);

        btnRecordGrade = new JButton("Record Grade");
        btnRecordGrade.setBackground(SystemColor.controlHighlight);
        btnRecordGrade.setFont(new Font("Tahoma", Font.PLAIN, 17));
        btnRecordGrade.setBounds(843, 423, 160, 29);
        contentPane.add(btnRecordGrade);

        for(int i=0; i<allParts.size();i++) {
            check.add(new JCheckBox(allParts.get(i).getName()));
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
                                if(comment!=null&&!isBlankString(comment)) {
                                    judge=true;
                                    ViewComment view=new ViewComment(comment);
                                    view.setLocationRelativeTo(null);
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

    public void jTableMouseClicked(java.awt.event.MouseEvent evt) {
        // Get the semester at the selected row index
        int selectedRowIndex = table.getSelectedRow();
        selectedStudent = tableModel.getStudentAt(selectedRowIndex);

        // set the selected row data into jtextfields
        btnCurveFinalGrade.setEnabled(true);
    }

    public void addActions() {
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mouseRightButtonClick(evt);
                jTableMouseClicked(evt);
            }
        });

        btnEditCategory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EditCategoryFrame edit=new EditCategoryFrame(backend);
                edit.setLocationRelativeTo(null);
                edit.setVisible(true);
                dispose() ;
            }
        });

        // Add a new student
        btnCalculateFinal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Random rand = new Random();
                int randomNum = rand.nextInt((1000000 - 1000) + 1) + 1000;
                Student added = new Student("New student");
                added.setSid("" + randomNum);
                try {
                    backend.addStudent(added);
                    backend.addStudentToCourse(added, course);
                } catch (SQLException ex) {
                    alert(ex.toString());
                    ex.printStackTrace();
                }
                tableModel.addRow(added);
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
                ArrayList<Part> customParts = new ArrayList<Part>();
                for(int i=0;i<check.size();i++) {
                    if(check.get(i).isSelected()) {
                        String partName = check.get(i).getText();
                        for (Part p: allParts) {
                            if (p.getName().equals(partName)) {
                                customParts.add(p);
                            }
                        }
                    }
                }

                System.out.println("Custom parts");
                System.out.println(customParts.size());

                ArrayList<String> columnNames = new ArrayList<>();
                columnNames.add("Name");
                for (Part p: customParts) {
                    columnNames.add(p.getName() + " (" + p.getTotalScore() + ")");
                }
                columnNames.add("Final Grade");
                System.out.println(columnNames.size());
                tableModel.setColumnNames(columnNames);
                tableModel.setParts(customParts);

                // I'm a god
                tableModel.fireTableStructureChanged();
            }
        });

        btnCurveFinalGrade.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedStudent == null) {
                    return;
                }
                try {
                    backend.deleteStudent(selectedStudent);
                } catch (SQLException ex) {
                    alert(ex.toString());
                }
                tableModel.deleteRow(selectedStudent);
                selectedStudent = null;
                btnCurveFinalGrade.setEnabled(false);
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openPrevious();
            }
        });

        btnRecordGrade.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PickPartFrame pick=new PickPartFrame(backend);
                pick.setLocationRelativeTo(null);
                pick.setVisible(true);
                dispose();
            }
        });

        btnEditStudents.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ManageStudentsFrame manageStu=new ManageStudentsFrame(backend);
                manageStu.setLocationRelativeTo(null);
                manageStu.setVisible(true);
                dispose();
            }
        });


    }

    boolean isBlankString(String string) {
        return string == null || string.trim().isEmpty();
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

