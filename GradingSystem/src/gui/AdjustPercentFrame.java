package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumn;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class AdjustPercentFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;



	/**
	 * Create the frame.
	 */
	public AdjustPercentFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 410, 411);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 30, 337, 283);
		contentPane.add(scrollPane);
		
		Object[] title={"Category","Part","Percentage"};
		Object[][] content=new Object[3][3];
		
		DefaultTableModel model = new DefaultTableModel(content, title) {
    		@Override
    		public boolean isCellEditable(int row,int column){
    			if(column == 2 ||column == 3){
    				return true;
    			}else{		
    				return false;
    			}
    		}
		};
		model.isCellEditable(2, 2);
		table = new JTable(model);
		//model.isCellEditable(2, 2);
		//table.isCellEditable(0, 3);
		scrollPane.setViewportView(table);
        table.isCellEditable(0,2);
        		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(270, 339, 93, 23);
		contentPane.add(btnSave);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(26, 339, 93, 23);
		contentPane.add(btnUpdate);
	}
	
	


}
