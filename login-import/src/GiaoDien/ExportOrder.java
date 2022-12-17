package GiaoDien;

import java.awt.Desktop;
import java.awt.EventQueue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Connection.ConnectExportOrder;
import Main.exportOrder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.Statement;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
//import org.apache.logging.log4j.core.LoggerContext;


public class ExportOrder extends JFrame {

	private ArrayList<exportOrder> list;
	DefaultTableModel model;
	private JPanel contentPane;
	private JTable tableExportOrder;
	private JTextField tfName;
	private JTextField tfPrice;
	private JTextField tfAmount;
	private JComboBox cbbProductID;
	
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExportOrder frame = new ExportOrder();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public void showTable() {
		
		DefaultTableModel model = (DefaultTableModel)tableExportOrder.getModel();
		model.setRowCount(0);
		for(exportOrder i : list) {
			model.addRow(new Object[] {
					j++,i.getIdE(),i.getProductID(), i.getSupplierID(), i.getName(), i.getPrice(), i.getAmount(),i.getDate()
			});
		}
		//list.clear();
	}
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement ps = null;
	int q;
	public ExportOrder() {
		JComboBox cbbProductID = new JComboBox();
		cbbProductID.setModel(new DefaultComboBoxModel(new String[] {"10", "11", "12"}));
		cbbProductID.setBounds(103, 110, 187, 22);
		cbbProductID.setSelectedItem(null);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mySQL://localhost:3306/qldt","root","admin");
			String sql = "select ID from products";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			cbbProductID.removeAllItems();
			
			while(rs.next()) {
				cbbProductID.addItem(rs.getInt("ID"));
				
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			e.printStackTrace();
		}
		JComboBox cbbSupplierID = new JComboBox();
		cbbSupplierID.setBounds(103, 148, 187, 22);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mySQL://localhost:3306/qldt","root","admin");
			String sql = "select ID from suppliers";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			cbbSupplierID.removeAllItems();
			
			while(rs.next()) {
				cbbSupplierID.addItem(rs.getInt("ID"));
				
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			e.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();
		final String tfDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(calendar.getTime());
		System.out.print(tfDate);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1323, 737);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel LabelExportOrder = new JLabel("EXPORT ORDER");
		LabelExportOrder.setHorizontalAlignment(SwingConstants.CENTER);
		LabelExportOrder.setFont(new Font("Bookman Old Style", Font.BOLD, 26));
		LabelExportOrder.setBounds(10, 11, 251, 48);
		contentPane.add(LabelExportOrder);
		
		scrollPane = new JScrollPane();
		
		scrollPane.setBounds(314, 78, 943, 548);
		contentPane.add(scrollPane);
		
		tableExportOrder = new JTable();
		tableExportOrder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel)tableExportOrder.getModel();
				int row = tableExportOrder.getSelectedRow();
				tfExportOrderID.setText(model.getValueAt(row, 1).toString());
				cbbProductID.setSelectedItem(model.getValueAt(row, 2).toString());
				cbbSupplierID.setSelectedItem(model.getValueAt(row, 3).toString());
				tfName.setText(model.getValueAt(row, 4).toString());
				tfPrice.setText(model.getValueAt(row, 5).toString());
				tfAmount.setText(model.getValueAt(row, 6).toString());
				//textGender.setSelectedItem(model.getValueAt(row, 6).toString());
			}
		});
		JLabel lblNewLabel = new JLabel("ProductID:");
		lblNewLabel.setBounds(10, 114, 83, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Supplier ID:");
		lblNewLabel_1.setBounds(10, 152, 83, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Name:");
		lblNewLabel_2.setBounds(10, 192, 83, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Price:");
		lblNewLabel_3.setBounds(10, 230, 83, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Amount:");
		lblNewLabel_4.setBounds(10, 271, 83, 14);
		contentPane.add(lblNewLabel_4);
		
		tfName = new JTextField();
		tfName.setBounds(103, 189, 187, 20);
		contentPane.add(tfName);
		tfName.setColumns(10);
		
		tfPrice = new JTextField();
		tfPrice.setBounds(103, 227, 187, 20);
		contentPane.add(tfPrice);
		tfPrice.setColumns(10);
		
		tfAmount = new JTextField();
		tfAmount.setBounds(103, 268, 187, 20);
		contentPane.add(tfAmount);
		tfAmount.setColumns(10);
		
//		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
//		LocalDateTime now = LocalDateTime.now();  
//		String tfDate =dtf.format(now);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                            if(tfPrice.getText().chars().allMatch(Character::isDigit) && tfAmount.getText().chars().allMatch(Character::isDigit)) {
                                if(tfExportOrderID.getText().equals("") || cbbProductID.getSelectedItem().equals(null) || cbbSupplierID.getSelectedItem().equals(null) || tfName.getText().equals("") || tfPrice.getText().equals("") || tfAmount.getText().equals("")) {
					JOptionPane.showMessageDialog(rootPane, "Please fill complete information");
				}
				else {
					Calendar calendar = Calendar.getInstance();
					final String tfDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(calendar.getTime());
					exportOrder i = new exportOrder();
					i.setIdE(tfExportOrderID.getText());
					i.setProductID(Integer.parseInt( cbbProductID.getSelectedItem().toString()));
					i.setSupplierID(Integer.parseInt(cbbSupplierID.getSelectedItem().toString()));
					i.setName(tfName.getText());
					i.setPrice(Long.parseLong(tfPrice.getText()) );
					i.setAmount(Long.parseLong(tfAmount.getText()));
					i.setDate(tfDate);
					if(Long.parseLong(tfAmount.getText())<=0 ||Long.parseLong(tfPrice.getText()) <=0 ) {
						if(Long.parseLong(tfAmount.getText())<=0) {
							JOptionPane.showMessageDialog(rootPane, "Amount must be >0");
						}
						else if(Long.parseLong(tfPrice.getText()) <=0) {
							JOptionPane.showMessageDialog(rootPane, "Price must be >0");
						}
					}
					else {
                                            
						try {
                                                int k = 100000000;
                                                String query1 = "select Quantity from qldt.products where ID like '%"+Integer.parseInt(cbbProductID.getSelectedItem().toString())+"%'";
                                            PreparedStatement ps1 = conn.prepareStatement(query1);
                                            ResultSet rs = ps1.executeQuery();
                                       
                                            while( rs.next()){
                                                
                                                 k = rs.getInt("Quantity");
                                                
                                                 
                                            }
                                            if(Integer.parseInt(tfAmount.getText()) < k){
                                                if(new ConnectExportOrder().addProducts(i)) {
							list.add(i);
                                                        try {
                                                        String sqlString ="UPDATE qldt.products SET Quantity= Quantity-? WHERE ID=?";
                                                        PreparedStatement ps = conn.prepareStatement(sqlString);
                                                        ps.setInt(1, Integer.parseInt(tfAmount.getText()));
                                                        ps.setInt(2, Integer.parseInt(cbbProductID.getSelectedItem().toString()));
                                                        ps.executeUpdate();
                                                        //ps.setString(2, cbbProductID.getSelectedItem().toString());
                                                        //ps.setString(1, tfAmount.getText());
                                            
                                                        System.out.println("oke");
                                                    } catch (SQLException ex) {
                                                        Logger.getLogger(ImportOder.class.getName()).log(Level.SEVERE, null, ex);
                                                    }
							JOptionPane.showMessageDialog(rootPane, "Save Successfully");
							showResult();
							tfExportOrderID.setText("");
							cbbProductID.setSelectedItem(null);
							cbbSupplierID.setSelectedItem(null);
							tfName.setText("");
							tfPrice.setText("");
							tfAmount.setText("");
						} 
						else {
							JOptionPane.showMessageDialog(rootPane, "Product's ID cannot be duplicated!");
						}
                                            }
                                            else{
                                                System.out.println("ko okeeela");
                                                 JOptionPane.showMessageDialog(rootPane, "Amount is greater than the number of available phones");
                                            }
						} catch (SQLException ex) {
                                                        Logger.getLogger(ImportOder.class.getName()).log(Level.SEVERE, null, ex);
                                                    }
						
					}
					
				}
                            }else {
						JOptionPane.showMessageDialog(rootPane, "Amount and Price must be integers !");
					}
				
				
				
			}
		});
		
		btnAdd.setBounds(29, 328, 89, 23);
		contentPane.add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tableExportOrder.getSelectedRow();
				if(row >=0) {
					try {
						Calendar calendar = Calendar.getInstance();
						final String tfDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(calendar.getTime());
						conn = DriverManager.getConnection("jdbc:mySQL://localhost:3306/qldt","root","admin");
						String value =(tableExportOrder.getModel().getValueAt(row, 0).toString());
						String query = "update tblexportOrder set ProductID=?,SupplierID=?,Name=?,Price=?,Amount=?,Date=? where ExportOrderID=?";
						PreparedStatement ps = conn.prepareStatement(query);
						ps = conn.prepareStatement(query);
						ps.setString(7, tfExportOrderID.getText());
						ps.setString(1, cbbProductID.getSelectedItem().toString());
						ps.setString(2, cbbSupplierID.getSelectedItem().toString());
						ps.setString(3, tfName.getText());
						ps.setString(4, tfPrice.getText());
						ps.setString(5, tfAmount.getText());
						ps.setString(6, tfDate);
						if(tfPrice.getText().chars().allMatch(Character::isDigit) && tfAmount.getText().chars().allMatch(Character::isDigit)) {
                                                    if(Long.parseLong(tfAmount.getText())<=0 ||Long.parseLong(tfPrice.getText()) <=0 ) {
							if(Long.parseLong(tfAmount.getText())<=0) {
								JOptionPane.showMessageDialog(rootPane, "Amount must be >0");
							}
							else if(Long.parseLong(tfPrice.getText()) <=0) {
								JOptionPane.showMessageDialog(rootPane, "Price must be >0");
							}
						}
						else {
							ps.executeUpdate();
							model.setValueAt(tfExportOrderID.getText(), row, 1);
							model.setValueAt(cbbProductID.getSelectedItem(), row, 2);
							model.setValueAt(cbbSupplierID.getSelectedItem(), row, 3);
							model.setValueAt(tfName.getText(), row, 4);
							model.setValueAt(tfPrice.getText(), row, 5);
							model.setValueAt(tfAmount.getText(), row, 6);
							model.setValueAt(tfDate, row, 7);
							JOptionPane.showMessageDialog(null, "Update Successfully");
						}
                                                }else {
						JOptionPane.showMessageDialog(rootPane, "Amount and Price must be integers !");
					}
                                                
						
						
					} catch (Exception e2) {
						// TODO: handle exception
					}
					
				}
				else {
					JOptionPane.showMessageDialog(null, "Plese select a row first");
				}
			}
		});
		btnUpdate.setBounds(215, 328, 89, 23);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tableExportOrder.getSelectedRow();
				if(row>=0) {
					try {
						conn = DriverManager.getConnection("jdbc:mySQL://localhost:3306/qldt","root","admin");
						String value =(tableExportOrder.getModel().getValueAt(row, 0).toString());
						String query = "delete from tblexportOrder where ExportOrderID=?";
						PreparedStatement ps = conn.prepareStatement(query);
						ps = conn.prepareStatement(query);
						ps.setString(1, tfExportOrderID.getText());
						ps.executeUpdate();
						model.removeRow(row);
						
						JOptionPane.showMessageDialog(null, "Delete Successfully");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
				else {
					JOptionPane.showMessageDialog(null, "Plese select a row first");
				}
			}
		});
		btnDelete.setBounds(29, 376, 89, 23);
		contentPane.add(btnDelete);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfExportOrderID.setText("");
				cbbProductID.setSelectedItem(null);
				cbbSupplierID.setSelectedItem(null);
				tfName.setText("");
				tfPrice.setText("");
				tfAmount.setText("");
				//tfSearch.setText("");
			}
		});
		btnClear.setBounds(215, 376, 89, 23);
		contentPane.add(btnClear);
		tableExportOrder.setModel(new javax.swing.table.DefaultTableModel(
				 new Object [][] {

			            },
				 new String [] {

			            }
			));
			
			scrollPane.setViewportView(tableExportOrder);
			
			JLabel lblNewLabel_5 = new JLabel("ExportOrderID:");
			lblNewLabel_5.setBounds(10, 79, 94, 14);
			contentPane.add(lblNewLabel_5);
			
			tfExportOrderID = new JTextField();
			tfExportOrderID.setBounds(103, 70, 187, 20);
			contentPane.add(tfExportOrderID);
			tfExportOrderID.setColumns(10);
			
			tfSearch = new JTextField();
			tfSearch.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					list.clear();
					float gia =0,soluong=0;
					int maSP=0,maNCC=0;
					try {
						conn = DriverManager.getConnection("jdbc:mySQL://localhost:3306/qldt","root","admin");

						if(tfSearch.getText().isEmpty()) {
							gia = 0;
							soluong=0;
							maSP=0;
							maNCC=0;
						}
						else {
							if(tfSearch.getText().chars().allMatch(Character::isDigit)) {
								gia = Long.parseLong(tfSearch.getText());
								soluong = Long.parseLong(tfSearch.getText());
								maSP = Integer.parseInt(tfSearch.getText());
								maNCC = Integer.parseInt(tfSearch.getText());
							} 
						}
						String query = "Select * from tblexportOrder where Name like N'%" + tfSearch.getText() + "%' or Price=" + gia 
								+ " or ExportOrderID like N'%" + tfSearch.getText() + "%' or ProductID ="+ maSP 
								+ "  or SupplierID ="+ maNCC + " or Date like N'%"+ tfSearch.getText() 
								+ "%' or Amount= "+soluong;
						System.out.print(query);
						PreparedStatement ps = conn.prepareStatement(query);
			            ResultSet rs = ps.executeQuery();
			            System.out.println(query);
			            while(rs.next()) {
			            	exportOrder i = new exportOrder();
			            	i.setIdE(rs.getString("ExportOrderID"));
			            	i.setProductID(rs.getInt("ProductID"));
			            	i.setSupplierID(rs.getInt("SupplierID"));
			            	i.setName(rs.getString("Name"));
			            	i.setPrice(rs.getLong("Price"));
			            	i.setAmount(rs.getLong("Amount"));
			            	i.setDate(rs.getString("Date"));
			            	list.add(i);
			            }
			            conn.close();
			            int j=1;
			            DefaultTableModel model = (DefaultTableModel)tableExportOrder.getModel();
			    		model.setRowCount(0);
			    		for(exportOrder i : list) {
			    			model.addRow(new Object[] {
			    					j++,i.getIdE(),i.getProductID(), i.getSupplierID(), i.getName(), i.getPrice(), i.getAmount(),i.getDate()
			    			});
			    		}
			            //showTable();
					} catch (Exception e2) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, e);
					}
				}
			});
		
			tfSearch.setBounds(292, 31, 475, 20);
			contentPane.add(tfSearch);
			tfSearch.setColumns(10);
			
			JButton Search = new JButton("Search");
			Search.setBounds(788, 30, 89, 23);
			contentPane.add(Search);
			
//			JComboBox cbbProductID = new JComboBox();
//			cbbProductID.setModel(new DefaultComboBoxModel(new String[] {"10", "11", "12"}));
//			cbbProductID.setBounds(120, 110, 293, 22);
			contentPane.add(cbbProductID);
			
		
			contentPane.add(cbbSupplierID);
			
			JButton btnBack = new JButton("Back");
			btnBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					new GiaoDienMenu().setVisible(true);
				}
			});
			btnBack.setBounds(120, 481, 89, 23);
			contentPane.add(btnBack);
			
			JButton btnExportExcelFile = new JButton("Export Excel File");
			btnExportExcelFile.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					 exportarExcel(tableExportOrder);
				}
			});
			btnExportExcelFile.setBounds(105, 422, 115, 23);
			contentPane.add(btnExportExcelFile);
		this.setLocationRelativeTo(null);
		list = new ConnectExportOrder().getListexportOrder();
		model = (DefaultTableModel) tableExportOrder.getModel();
		model.setColumnIdentifiers(new Object[] {
				"STT", "ID","Product ID","Supplier ID", "Name", "Price", "Amount", "Date"
		});
		showTable();
	}
	
	public void exportarExcel(JTable jt) {
		try {
			JFileChooser jFileChooser = new JFileChooser();
			jFileChooser.showSaveDialog(jt);
			File saveFile = jFileChooser.getSelectedFile();
			if(saveFile != null) {
				saveFile = new File(saveFile.toString()+".xlsx");
				//Workbook wb = new XSSFWorkbook();
				XSSFWorkbook wb = new XSSFWorkbook();
				//Sheet sheet = (Sheet) wb.createSheet("tblExportOrder");
				XSSFSheet sheet = wb.createSheet();
				Row rowCol = sheet.createRow(0);
				for(int i=0;i<jt.getColumnCount();i++) {
					Cell cell = rowCol.createCell(i);
					cell.setCellValue(jt.getColumnName(i));
				}
				
				for(int j=0;j<jt.getRowCount();j++) {
					Row row =  sheet.createRow(j+1);
					for(int k=0;k<jt.getColumnCount();k++) {
						Cell cell = row.createCell(k);
						if(jt.getValueAt(j, k) != null) {
							cell.setCellValue(jt.getValueAt(j, k).toString());
						}
					}
				}
				FileOutputStream out = new FileOutputStream(new File(saveFile.toString()));
				wb.write(out);
				wb.close();
				out.close();
				openFile(saveFile.toString());
				
			}
			else {
				JOptionPane.showMessageDialog(null, "Unable to create file");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Unable to create file !");
		}
	}
	
	public void openFile(String file) {
		try {
			File path = new File(file);
			Desktop.getDesktop().open(path);
		} catch (Exception e) {
			// TODO: handle exception
			//System.out.println(ioe);
			JOptionPane.showMessageDialog(null, "Can not open file !");
		}
	}
	
	int j = 1;
	private JTextField tfExportOrderID;
	private JTextField tfSearch;
	public void showResult() {
		exportOrder i = list.get(list.size() -1 );
		model.addRow(new Object[] {
				j++,i.getIdE(),i.getProductID(), i.getSupplierID(), i.getName(), i.getPrice(), i.getAmount(),i.getDate()
		});
	}
}