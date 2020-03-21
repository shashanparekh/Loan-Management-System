package Loan_management_system;

import java.awt.BorderLayout;


import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import java.awt.print.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.ImageIcon;


public class Loan_Systems {

	private JFrame frame;
	private JFrame frame1;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextArea textArea_1;
	private int refs;
	private String str,date,time;
	private Connection conn = null;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JButton btnGenerateRecipt;
	private JTextField id;
	private File file;
	private boolean search;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Loan_Systems window = new Loan_Systems();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void createConnection() {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
		    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/loandetails","shashan","shashan");
					 System.out.println("Databse Established");
					 if(conn != null)
					 {
						 JOptionPane.showMessageDialog(null,"Connection Established");
					 }
		}
		catch(ClassNotFoundException ex) {
			JOptionPane.showMessageDialog(null, ex);
		}
		catch(SQLException ex)
		{
			JOptionPane.showMessageDialog(null, ex);
		}
	}

	/**
	 * Create the application.
	 */
	public Loan_Systems() {	
		createConnection();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		
		frame.setBounds(0, 0,1370,700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(63,573,897,77);
		frame.getContentPane().add(panel);
		
		JButton btnLoanCalculator = new JButton("Calculate Loan Amount");
		btnLoanCalculator.setBounds(25, 7, 183, 27);
		btnLoanCalculator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				double InterestRate=Double.parseDouble(textField.getText());
				double monthlyInterestRate=InterestRate/1200;
				int numberOfYears=Integer.parseInt(textField_1.getText());
double loanAmount=Double.parseDouble(textField_2.getText());
double monthlyPayment=loanAmount*monthlyInterestRate/(1-1/Math.pow(1+monthlyInterestRate,numberOfYears*12));
String imonthlyPayment;
imonthlyPayment=Double.toString(monthlyPayment);
imonthlyPayment=String.format("%.2f",monthlyPayment);
textField_3.setText(imonthlyPayment);
double totalPayment=monthlyPayment*numberOfYears*12;
String itotalPayment;
itotalPayment=String.format("%.2f",totalPayment);
textField_4.setText(itotalPayment);

			
			}
		});
		panel.setLayout(null);
		btnLoanCalculator.setHorizontalAlignment(SwingConstants.LEFT);
		btnLoanCalculator.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		panel.add(btnLoanCalculator);
		
	    btnGenerateRecipt = new JButton("Generate Recipt");
		btnGenerateRecipt.setBounds(218, 7, 149, 27);
		btnGenerateRecipt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String InterestRate=String.format(textField.getText());
				String numberOfYears=String.format(textField_1.getText());
				String loanAmount=String.format(textField_2.getText());
				String monthlyPayment=String.format(textField_3.getText());
				String totalPayment=String.format(textField_4.getText());
			    refs=1325+(int)(Math.random()*4238);
				Calendar timer=Calendar.getInstance();
				timer.getTime();
				SimpleDateFormat tTime=new SimpleDateFormat("HH:mm:ss");
			    time = tTime.format(timer.getTime());
				SimpleDateFormat Tdate=new SimpleDateFormat("dd-MMM-yyyy");
			    date = Tdate.format(timer.getTime());
			    str = new String("\tLoan Management Systems\n\n"+"Reference:\t\t\t"+refs+"\n===================================================\t"+"\nInterest Rate:\t\t\t"+InterestRate+"\nRepayment years:\t\t"+numberOfYears+"\nAmount of Loan:\t\t"+"Rs."+loanAmount+"\nMonthly payment:\t\t"+"Rs."+monthlyPayment+"\nTotal payment:\t\t\t"+"Rs."+totalPayment+"\n=========================================\t"+"\nDate:"+Tdate.format(timer.getTime())+"\t\tTime:"+tTime.format(timer.getTime())+"\n\n\t\tThank You\n");
				textArea_1.setText(str);
				btnNewButton_2.setVisible(true);
				btnNewButton_3.setVisible(true);
				databaseManagement();
			}
		});
		btnGenerateRecipt.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		panel.add(btnGenerateRecipt);
		
		JButton btnNewButton = new JButton("Reset Calculator");
		btnNewButton.setBounds(377, 7, 156, 27);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText(null);
				textField_1.setText(null);
				textField_2.setText(null);
				textField_3.setText(null);
				textField_4.setText(null);
				textArea_1.setText(null);
				btnNewButton_2.setVisible(false);
				btnNewButton_3.setVisible(false);
				btnGenerateRecipt.setEnabled(true);
				
				
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		panel.add(btnNewButton);
		
		JLabel lblForLaterUse = new JLabel("Generated Reciept gets Auto Saved in Database ");
		lblForLaterUse.setBounds(228, 45, 273, 25);
		panel.add(lblForLaterUse);
		lblForLaterUse.setHorizontalAlignment(SwingConstants.LEFT);
		lblForLaterUse.setFont(new Font("Tekton Pro", Font.PLAIN, 13));
		
		JButton btnNewButton_1 = new JButton("Search Reciept");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
			    btnGenerateRecipt.setEnabled(false);	
				String sql2 = "select * from loanreciept where idLoanReciept = ?";
				PreparedStatement pstmt2 = conn.prepareStatement(sql2);
				pstmt2.setInt(1,Integer.parseInt(id.getText()));
				ResultSet rs = pstmt2.executeQuery();
				if(rs.next())
				{
				    str = new String("\tLoan Management Systems\n\n"+"Reference:\t\t\t"+Integer.toString(rs.getInt("idloanreciept"))+"\n"+ "==================================================="+ "\t"+"\nInterest Rate:\t\t\t"+Double.toString(rs.getDouble("Interest Rate"))+"\nRepayment years:\t\t"+Integer.toString(rs.getInt("Repayment Year"))+"\nAmount of Loan:\t\t"+"Rs"+Double.toString(rs.getDouble("Amount of Loan"))+"\nMonthly payment:\t\t"+"Rs."+Double.toString(rs.getDouble("Monthly Payment"))+"\nTotal payment:\t\t\t"+"Rs."+Double.toString(rs.getDouble("Total Payment"))+"\n"+ "=========================================\t"+"\nDate:"+rs.getString("date")+"\t\tTime:"+rs.getString("Time")+"\n\n\t\tThank You\n");
				    textArea_1.setText(str);
				    btnNewButton_2.setVisible(true);
				    btnNewButton_3.setVisible(true);
				    search = true;
				}
				else
				{
					JOptionPane.showMessageDialog(null,"The id :" + id.getText() + "  doesn't Exists \n Try Again ");
				}
				}catch(SQLException ex) {
					JOptionPane.showMessageDialog(null, ex);
				}
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnNewButton_1.setBounds(559, 7, 134, 27);
		panel.add(btnNewButton_1);
		
		JButton btnExitCalculator = new JButton("Exit ");
		btnExitCalculator.setBounds(826, 32, 61, 44);
		panel.add(btnExitCalculator);
		btnExitCalculator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame=new JFrame("Exit");
				if(JOptionPane.showConfirmDialog(frame, "Confirm if you want to exit","Loan Management Systems",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION)
				{
					try {
					conn.close();
					}catch(SQLException e) {JOptionPane.showMessageDialog(null, e);}
					System.exit(0);
				}
			}
		});
		btnExitCalculator.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		
		id = new JTextField();
		id.setBounds(712, 7, 97, 25);
		panel.add(id);
		id.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setToolTipText("");
		panel_1.setForeground(Color.BLACK);
		panel_1.setBorder(new CompoundBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), new MatteBorder(2, 1, 1, 4, (Color) new Color(0, 0, 0))), new MatteBorder(1, 1, 1, 2, (Color) new Color(0, 0, 0))));
		panel_1.setBounds(63, 57, 897, 77);
		frame.getContentPane().add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(986,99,358,361);
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		textArea_1 = new JTextArea();
		panel_2.add(textArea_1, BorderLayout.CENTER);
		
		JLabel lblLoanManagementSystem = new JLabel("Loan Management System Calculator");
		lblLoanManagementSystem.setForeground(new Color(0, 0, 51));
		lblLoanManagementSystem.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoanManagementSystem.setFont(new Font("Urdu Typesetting", Font.BOLD | Font.ITALIC, 32));
		panel_1.add(lblLoanManagementSystem);
		
		JLabel lblEnterInterestRate = new JLabel("Enter Interest Rate  :");
		lblEnterInterestRate.setFont(new Font("Times New Roman", Font.ITALIC, 25));
		lblEnterInterestRate.setBounds(63,170,287,31);
		frame.getContentPane().add(lblEnterInterestRate);
		
		JLabel lblEnterNoOf = new JLabel("Enter no. of years :");
		lblEnterNoOf.setFont(new Font("Times New Roman", Font.ITALIC, 25));
		lblEnterNoOf.setBounds(63, 222, 258, 31);
		frame.getContentPane().add(lblEnterNoOf);
		
		JLabel lblEnterLoanAmount = new JLabel("Enter Loan Amount :");
		lblEnterLoanAmount.setFont(new Font("Times New Roman", Font.ITALIC, 25));
		lblEnterLoanAmount.setBounds(63, 282, 258, 31);
		frame.getContentPane().add(lblEnterLoanAmount);
		
		JLabel lblMonthlyPayment = new JLabel("Monthly Payment :");
		lblMonthlyPayment.setFont(new Font("Times New Roman", Font.ITALIC, 25));
		lblMonthlyPayment.setBounds(63, 433, 258, 31);
		frame.getContentPane().add(lblMonthlyPayment);
		
		JLabel lblTotalPayment = new JLabel("Total Payment :");
		lblTotalPayment.setFont(new Font("Times New Roman", Font.ITALIC, 25));
		lblTotalPayment.setBounds(63, 484, 287, 31);
		frame.getContentPane().add(lblTotalPayment);
		
		textField = new JTextField();
		textField.setFont(new Font("Times New Roman", Font.ITALIC, 25));
		textField.setBounds(419, 170, 227, 31);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Times New Roman", Font.ITALIC, 25));
		textField_1.setColumns(10);
		textField_1.setBounds(419, 222, 227, 31);
		frame.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Times New Roman", Font.ITALIC, 25));
		textField_2.setColumns(10);
		textField_2.setBounds(419, 282, 227, 31);
		frame.getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Times New Roman", Font.ITALIC, 25));
		textField_3.setColumns(10);
		textField_3.setBounds(419, 429, 227, 31);
		frame.getContentPane().add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Times New Roman", Font.ITALIC, 25));
		textField_4.setColumns(10);
		textField_4.setBounds(419, 484, 227, 31);
		frame.getContentPane().add(textField_4);
		
	    btnNewButton_2 = new JButton("Soft Copy");
	    btnNewButton_2.setVisible(false);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {if(search ==true)
				{
			         file = new File("C:\\Users\\hp\\eclipse-workspace\\LMS\\Reciepts\\Reciept" + id.getText() + ".rtf");
				     search = false;
				}
				else
				{
					
					 file = new File("C:\\Users\\hp\\eclipse-workspace\\LMS\\Reciepts\\Reciept" + Integer.toString(refs) + ".rtf");
				}   
					FileWriter fr = new FileWriter(file);
					fr.write(str);
					fr.close();
					frame1 = new JFrame("Reciept");
					JOptionPane.showMessageDialog(frame1, "Soft copy successfully generated " + file.getName());
				
					
					}
					catch(Exception e)
					{
						System.out.println(e);
					}
			}
		});
		btnNewButton_2.setBounds(1025, 471, 89, 23);
		frame.getContentPane().add(btnNewButton_2);
		
	    btnNewButton_3 = new JButton("Print");
	    btnNewButton_3.setVisible(false);
	    btnNewButton_3.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		try {
					textArea_1.print();
				}catch(java.awt.print.PrinterException e) {
					System.err.format("NO printer Found ",e.getMessage());
				}
	    	}
	    });
		btnNewButton_3.setBounds(1146, 471, 89, 23);
		frame.getContentPane().add(btnNewButton_3);
		
	
		
	}

	protected void databaseManagement() {
		// TODO Auto-generated method stub
		try {
			String sql = "insert into loanreciept values (?,?,?,?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,refs);
			pstmt.setDouble(2,Double.parseDouble(textField.getText()));
			pstmt.setInt(3,Integer.parseInt(textField_1.getText()));
			pstmt.setDouble(4,Double.parseDouble(textField_2.getText()));
			pstmt.setDouble(5,Double.parseDouble(textField_3.getText()));
			pstmt.setDouble(6,Double.parseDouble(textField_4.getText()));
			pstmt.setString(7,time);
			pstmt.setString(8,date);
			pstmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "Insertion Successful");
			}catch(SQLException ex) {JOptionPane.showMessageDialog(null, ex);}
		}
	}

