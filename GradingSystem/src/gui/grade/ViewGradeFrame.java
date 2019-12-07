package gui.grade;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;
import gui.FrameActions;
import gui.SemesterFrame;
import objects.Category;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTree;

public class ViewGradeFrame extends JFrame implements FrameActions{

	
	// Frames
	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	private JLabel CourseNameLabel;
	private JTree CategoryTree ;
	
	// Objects
	private List<String> cids = new ArrayList<>() ;
	
	
	
	public ViewGradeFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		init();
		addActions();
	}

	
	public void init(){
		
		Object[] title={"Student","hw1","hw2","Midterm","Final"};
		Object[][] content=new Object[2][5];
		contentPane.setLayout(null);
		table = new JTable(content, title);
		
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(203, 57, 568, 461);
		contentPane.add(scrollPane);
		
		CourseNameLabel = new JLabel("<Course Name>");
		CourseNameLabel.setBounds(32, 18, 172, 16);
		contentPane.add(CourseNameLabel);
		
		JButton EditModeBtn = new JButton("Edit Mode");
		EditModeBtn.setBounds(341, 530, 129, 35);
		EditModeBtn.setForeground(Color.RED);
		contentPane.add(EditModeBtn);
		
//		String[] cats = {"hw","exam"} ;
		CategoryTree = new JTree();
		DefaultMutableTreeNode cat1 = new DefaultMutableTreeNode("Categories") ;
		DefaultMutableTreeNode cat1_1 = new DefaultMutableTreeNode("hw") ;
		DefaultMutableTreeNode cat1_2 = new DefaultMutableTreeNode("exam") ;
		cat1.add(cat1_1);
		cat1.add(cat1_2);
		DefaultMutableTreeNode cat1_1_1 = new DefaultMutableTreeNode("hw1") ;
		DefaultMutableTreeNode cat1_1_2 = new DefaultMutableTreeNode("hw2") ;
		DefaultMutableTreeNode cat1_2_1 = new DefaultMutableTreeNode("midterm") ;
		DefaultMutableTreeNode cat1_2_2 = new DefaultMutableTreeNode("final") ;
		cat1_1.add(cat1_1_1);
		cat1_1.add(cat1_1_2);
		cat1_2.add(cat1_2_1);
		cat1_2.add(cat1_2_2);
		DefaultTreeModel treeModel = new DefaultTreeModel(cat1) ;
		CategoryTree.setModel(treeModel);
		CategoryTree.setBounds(15, 57, 188, 457);
		contentPane.add(CategoryTree);
	}
	
//	public void jTree1MouseClicked(java.awt.event.MouseEvent evt) {
//		  TreeSelectionModel smd = CategoryTree.getSelectionModel();
//	        if(smd.getSelectionCount() > 0){
//	            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) CategoryTree.getSelectionPath().getLastPathComponent();
//	            jTextField1.setText(selectedNode.getUserObject().toString());
//	        }
//	                                     
//
//	}
	
	public void addActions(){
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
