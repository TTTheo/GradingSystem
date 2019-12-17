package gui;

import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import backend.* ;
import gui.grade.EditCategoryFrame;
import objects.Category;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.Font;
import java.awt.SystemColor;
public class CategoryFrame extends JFrame implements FrameActions, TableModelListener {

    private JPanel contentPane;

    private JTable categoryTable;
    private CategoryTableModel tableModel ;

    private JLabel categoryMenuLabel ;
    private JLabel selectedCategoryLabel ;

    private JButton viewBtn ;
    private JButton deleteBtn ;
    private JButton addBtn ;

    private JScrollPane categoryTableScrollPane ;
    private JTextField selectedCategoryField;
    private Category selectedCategory;
    private JButton btnBack;

    private ArrayList<Category> data=new ArrayList<Category>();
    private final String[] columnNames = { "Name", "Percentage", "Number of Parts"};
    private Backend backend;
    public CategoryFrame(Backend backend) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 893, 574);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        this.backend=backend;

        try {
            data = backend.getCategories(backend.getCourse());
        } catch (SQLException e) {
            alert(e.toString());
            e.printStackTrace();
        }

        tableModel = new CategoryTableModel(data, columnNames);
        tableModel.addTableModelListener(this);  // This class listens to updates
        categoryTable = new JTable(tableModel);  // Create JTable with custom model


        categoryTable.setFont(new Font("Tahoma", Font.PLAIN, 17));

        categoryTable.setBounds(52, 69, 333, 221);
        categoryTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableMouseClicked(evt);
            }
        });
        contentPane.add(categoryTable);

        categoryMenuLabel = new JLabel("Category Menu");
        categoryMenuLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
        categoryMenuLabel.setBounds(31, 27, 135, 37);
        contentPane.add(categoryMenuLabel);

        viewBtn = new JButton("View");
        viewBtn.setBackground(SystemColor.controlHighlight);
        viewBtn.setFont(new Font("Tahoma", Font.PLAIN, 17));
        viewBtn.setBounds(700, 207, 130, 37);
        contentPane.add(viewBtn);

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

        categoryTableScrollPane = new JScrollPane(categoryTable);
        categoryTableScrollPane.setBounds(31, 69, 599, 336);
        contentPane.add(categoryTableScrollPane);

        selectedCategoryField = new JTextField();
        selectedCategoryField.setFont(new Font("Tahoma", Font.PLAIN, 17));
        selectedCategoryField.setEditable(false);
        selectedCategoryField.setBounds(700, 92, 130, 26);
        contentPane.add(selectedCategoryField);
        selectedCategoryField.setColumns(10);

        selectedCategory = null;
        selectedCategoryLabel = new JLabel("Selected Category");
        selectedCategoryLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
        selectedCategoryLabel.setBounds(700, 53, 163, 26);
        contentPane.add(selectedCategoryLabel);

        viewBtn.setEnabled(false);

        btnBack = new JButton("Back");
        btnBack.setBackground(SystemColor.controlHighlight);
        btnBack.setFont(new Font("Tahoma", Font.PLAIN, 17));
        btnBack.setBounds(700, 429, 130, 37);
        contentPane.add(btnBack);
        addActions();
    }

    public void addActions(){
        addBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Category added = new Category("New category");
                added.setCourseId(backend.getCourse().getCourseId());
                try {
                    backend.addCategory(added);  // this sets the CID
                } catch (SQLException ex) {
                    alert(ex.toString());
                    ex.printStackTrace();
                }
                tableModel.addRow(added);
            }
        });

        viewBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                backend.setCategory(selectedCategory);
                openNext();
            }
        });

        deleteBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedCategory == null) {
                    return;
                }
                try {
                    backend.deleteCategory(selectedCategory);
                } catch (SQLException ex) {
                    alert(ex.toString());
                }
                tableModel.deleteRow(selectedCategory);
                selectedCategory = null;
                selectedCategoryField.setText("");
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
        int selectedRowIndex = categoryTable.getSelectedRow();
        selectedCategory = tableModel.getCategoryAt(selectedRowIndex);

        // set the selected row data into jtextfields
        String categoryInfo = selectedCategory.getName();
        selectedCategoryField.setText(categoryInfo);
        viewBtn.setEnabled(true);
    }

    public void alert(String message){
        JOptionPane.showMessageDialog(null, message);
    }

    // Open course view next
    public void openNext() {
        PartFrame next = new PartFrame(backend);
        next.setLocationRelativeTo(null);
        next.setVisible(true);
        dispose();
    }

    // Go back to the edit category frame
    public void openPrevious() {
        EditCategoryFrame back = new EditCategoryFrame(backend);
        back.setLocationRelativeTo(null);
        back.setVisible(true);
        dispose();
    }

    // Listen to changes in the table and update the DB accordingly
	public void tableChanged(TableModelEvent e) {
		int row = e.getFirstRow();
		int type = e.getType();

		try {
		    // Insert and Delete are already handled by their buttons
            if (type == TableModelEvent.UPDATE) {
                Category changed = tableModel.getCategoryAt(row);
                backend.updateCategory(changed);
            }
        } catch (SQLException ex) {
		    alert(ex.toString());
		    ex.printStackTrace();
        }
	}
}
