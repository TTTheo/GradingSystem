package gui.grade;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class ShowStatisticFrame extends JFrame {

	private JPanel contentPane;
	private JLabel lblAverage;
	private JLabel lblMedian;
	private JLabel lblStandardDeviation;
	private JButton btnBack;
	private double[] statistic;
	/**
	 * Create the frame.
	 */
	public ShowStatisticFrame(double[] statistic) {
		this.statistic=statistic;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 710, 448);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		init();
		addAction();
	}
	
	public void init() {
		lblAverage = new JLabel("Average: ");
		lblAverage.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblAverage.setBounds(256, 106, 349, 29);
		contentPane.add(lblAverage);
		lblAverage.setText("Average: "+statistic[0]);
		
		lblMedian = new JLabel("Median: ");
		lblMedian.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblMedian.setBounds(256, 161, 349, 29);
		contentPane.add(lblMedian);
		lblMedian.setText("Median: "+statistic[1]);
		
		lblStandardDeviation = new JLabel("Standard deviation: ");
		lblStandardDeviation.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblStandardDeviation.setBounds(256, 223, 320, 29);
		contentPane.add(lblStandardDeviation);
		lblStandardDeviation.setText("Standard deviation: "+statistic[2]);
		
		btnBack = new JButton("Back");
		btnBack.setBackground(SystemColor.controlHighlight);
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnBack.setBounds(292, 329, 97, 25);
		contentPane.add(btnBack);
	}
	
	public void addAction() {
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
}
