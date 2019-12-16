package gui.grade;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import gui.FrameActions;
import gui.SemesterFrame;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.SystemColor;

public class CurveGradeFrame extends JFrame implements FrameActions{

	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblPercentageOfCurve;
	private JButton btnSure;
	private JButton btnCancel;
	private ViewGradeFrame view;
	/**
	 * Create the frame.
	 */
	public CurveGradeFrame(ViewGradeFrame view) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 699, 477);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		init();
		addActions();
		this.view=view;
	}
	
	public void init(){
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField.setBounds(180, 164, 303, 34);
		contentPane.add(textField);
		textField.setColumns(10);
		
		lblPercentageOfCurve = new JLabel("Percentage of curve:");
		lblPercentageOfCurve.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblPercentageOfCurve.setBounds(178, 116, 192, 23);
		contentPane.add(lblPercentageOfCurve);
		
		btnSure = new JButton("Proceed");
		btnSure.setBackground(SystemColor.controlHighlight);
		btnSure.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnSure.setBounds(386, 264, 97, 34);
		contentPane.add(btnSure);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBackground(SystemColor.controlHighlight);
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnCancel.setBounds(180, 264, 97, 32);
		contentPane.add(btnCancel);
	}
	
	public void addActions(){
		btnSure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double curve=getCurve();
				if(curve>100) {
					alert("The percentage should be less than 100!");
				}else {
					String[] finals=view.getFinals();
					String[] finalgrade=new String[finals.length];
					//double curve=getCurve();
					for(int i=0;i<finals.length;i++) {
						//finalgrade[i]=Double.parseDouble(finals[i]);
						finalgrade[i]=String.valueOf(Double.parseDouble(finals[i])*(100+curve)/100);
					}
					String[] columnNames=view.getColumn();
					String[][] data=view.getData();
					boolean curveExist=false;
					if(columnNames[columnNames.length-1].equals("Curve")) {
						curveExist=true;
					}
					if(curveExist&&view.getCheck().get(view.getCheck().size()-1).isSelected()) {
						
						
					}else {
						String[] tempColumn=new String[columnNames.length+1];
						String[][] tempData=new String[data.length][tempColumn.length];
						System.out.println(tempData.length);
						System.out.println(tempColumn.length);
						//ArrayList<JCheckBox> check=view.getCheck();
						view.getCheck().add(new JCheckBox("Curve"));
						view.getCheck().get(view.getCheck().size()-1).setSelected(true);
						view.getPanel().add(view.getCheck().get(view.getCheck().size()-1));
						view.getPanel().updateUI();
						
						//columnNames=new String[columnNames.length+1];
						for(int i=0;i<tempColumn.length;i++) {
							if(i==tempColumn.length-1){
								tempColumn[i]="Curve";
							}else if(i==columnNames.length-2){
								tempColumn[i]="Final";
							}else{
								tempColumn[i]=columnNames[i];
							}
						}
						//ta=new String[students.size()][columnNames.length];
						
						for(int i=0;i<tempData.length;i++) {
							for(int j=0;j<tempColumn.length;j++) {
								if(j==tempColumn.length-1) {
									tempData[i][j]=finalgrade[i];
								}else {
									tempData[i][j]=data[i][j];
								}
							}
						}
						view.setCurve(finalgrade);
						view.setColumn(tempColumn);
						view.setData(tempData);
						view.setModel(new DefaultTableModel(tempData,tempColumn));
						view.getTabel().setModel(view.getModel());
						
						//view.getModel()=new DefaultTabelModel(te)
					}
					dispose();
				}
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
	

	
	public double getCurve() {
		double percentage=Double.parseDouble(textField.getText());
		return percentage;
	}
	
	public void alert(String message){
        JOptionPane.showMessageDialog(null, message);
    }

	// Open the semester frame next
	public void openNext() {
		dispose();
	}

	// This is the first window, no previous window exists
	public void openPrevious() {
		dispose();
	}

}
