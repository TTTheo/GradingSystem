package gui;

import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import backend.* ;
import objects.Part;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.Font;
import java.awt.SystemColor;

public class PartFrame extends JFrame implements FrameActions, TableModelListener {

    private JPanel contentPane;

    private JTable partTable;
    private PartTableModel tableModel ;

    private JLabel categoryMenuLabel ;
    private JLabel selectedPartLabel ;

    private JButton addBtn ;
    private JButton deleteBtn ;

    private JScrollPane partTableScrollPane ;
    private JTextField selectedPartField;
    private Part selectedPart;
    private JButton btnBack;

    private ArrayList<Part> data=new ArrayList<Part>();
    private final String[] columnNames = { "Name", "Percentage", "Max Score"};
    private Backend backend;
    public PartFrame(Backend backend) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 893, 574);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        this.backend=backend;

        try {
            data = backend.getParts(backend.getCategory());
        } catch (SQLException e) {
            alert(e.toString());
            e.printStackTrace();
        }

        tableModel = new PartTableModel(data, columnNames);
        tableModel.addTableModelListener(this);  // This class listens to updates
        partTable = new JTable(tableModel);  // Create JTable with custom model
        partTable.setFont(new Font("Tahoma", Font.PLAIN, 17));

        partTable.setBounds(52, 69, 333, 221);
        partTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableMouseClicked(evt);
            }
        });
        contentPane.add(partTable);

        categoryMenuLabel = new JLabel("Part Menu");
        categoryMenuLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
        categoryMenuLabel.setBounds(31, 27, 135, 37);
        contentPane.add(categoryMenuLabel);

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

        partTableScrollPane = new JScrollPane(partTable);
        partTableScrollPane.setBounds(31, 69, 599, 336);
        contentPane.add(partTableScrollPane);

        selectedPartField = new JTextField();
        selectedPartField.setFont(new Font("Tahoma", Font.PLAIN, 17));
        selectedPartField.setEditable(false);
        selectedPartField.setBounds(700, 92, 130, 26);
        contentPane.add(selectedPartField);
        selectedPartField.setColumns(10);

        selectedPart = null;
        selectedPartLabel = new JLabel("Selected Part");
        selectedPartLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
        selectedPartLabel.setBounds(700, 53, 163, 26);
        contentPane.add(selectedPartLabel);

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
                Part added = new Part("New Part");
                added.setCid(backend.getCategory().getCid());
                try {
                    backend.addPart(added);  // this sets the CID
                } catch (SQLException ex) {
                    alert(ex.toString());
                    ex.printStackTrace();
                }
                tableModel.addRow(added);
            }
        });

        deleteBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Delete the selected row
                if (selectedPart == null) {
                    return;
                }
                try {
                    backend.deletePart(selectedPart);
                } catch (SQLException ex) {
                    alert(ex.toString());
                    ex.printStackTrace();
                }
                tableModel.deleteRow(selectedPart);
                selectedPart = null;
                selectedPartField.setText("");
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
        int selectedRowIndex = partTable.getSelectedRow();
        selectedPart = tableModel.getPartAt(selectedRowIndex);

        // set the selected row data into jtextfields
        String partInfo = selectedPart.getName();
        selectedPartField.setText(partInfo);
    }

    public void alert(String message){
        JOptionPane.showMessageDialog(null, message);
    }

    // No next frame
    public void openNext() {
        dispose();
    }

    // Go back to the category frame
    public void openPrevious() {
        CategoryFrame back = new CategoryFrame(backend);
        back.setLocationRelativeTo(null);
        back.setVisible(true);
        dispose();
    }

    // Listen to changes in the table and update the DB accordingly
    public void tableChanged(TableModelEvent e) {
        int row = e.getFirstRow();
        int type = e.getType();

        try {
            // Insert and delete are already handled by the Add button
            if (type == TableModelEvent.UPDATE) {
                Part changed = tableModel.getPartAt(row);
                backend.updatePart(changed);
            }
        } catch (SQLException ex) {
            alert(ex.toString());
            ex.printStackTrace();
        }
    }
}
