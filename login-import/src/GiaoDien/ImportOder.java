package GiaoDien;

import java.awt.Desktop;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Connection.ConnectImportOrder;
import Main.exportOrder;
import Main.importOrder;
import UserManagement.ForgotPassword;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class ImportOder extends JFrame {
	private ArrayList<importOrder> list;
	DefaultTableModel model;
	private JPanel contentPane;
	private JTable tableImportOrder;
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
					ImportOder frame = new ImportOder();
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
		for(importOrder i : list) {
			model.addRow(new Object[] {
					j++,i.getIdI(),i.getProductID(), i.getSupplierID(), i.getName(), i.getPrice(), i.getAmount(), i.getDate()
			});
		}
	}
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement ps = null;
	int q;
	public ImportOder() {
		JComboBox cbbProductID = new JComboBox();
		cbbProductID.setModel(new DefaultComboBoxModel(new String[] {"10", "11", "12"}));
		cbbProductID.setBounds(120, 119, 219, 22);
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
		cbbSupplierID.setBounds(120, 157, 219, 22);
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
		setBounds(100, 100, 1262, 675);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel LabelImportOrder = new JLabel("IMPORT ORDER");
		LabelImportOrder.setHorizontalAlignment(SwingConstants.CENTER);
		LabelImportOrder.setFont(new Font("Bookman Old Style", Font.BOLD, 26));
		LabelImportOrder.setBounds(10, 11, 251, 48);
		contentPane.add(LabelImportOrder);
		
		scrollPane = new JScrollPane();
		
		scrollPane.setBounds(349, 85, 849, 529);
		contentPane.add(scrollPane);
		
		tableImportOrder = new JTable();
		tableImportOrder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel)tableImportOrder.getModel();
				int row = tableImportOrder.getSelectedRow();
				tfImportOrderID.setText(model.getValueAt(row, 1).toString());
				cbbProductID.setSelectedItem(model.getValueAt(row, 2).toString());
				cbbSupplierID.setSelectedItem(model.getValueAt(row, 3).toString());
				tfName.setText(model.getValueAt(row, 4).toString());
				tfPrice.setText(model.getValueAt(row, 5).toString());
				tfAmount.setText(model.getValueAt(row, 6).toString());
				
			}
		});
		JLabel lblNewLabel = new JLabel("ProductID:");
		lblNewLabel.setBounds(10, 123, 83, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Supplier ID:");
		lblNewLabel_1.setBounds(10, 161, 83, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Name:");
		lblNewLabel_2.setBounds(10, 201, 83, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Price:");
		lblNewLabel_3.setBounds(10, 239, 83, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Amount:");
		lblNewLabel_4.setBounds(10, 280, 83, 14);
		contentPane.add(lblNewLabel_4);
		
		tfName = new JTextField();
		tfName.setBounds(120, 198, 219, 20);
		contentPane.add(tfName);
		tfName.setColumns(10);
		
		tfPrice = new JTextField();
		tfPrice.setBounds(120, 236, 219, 20);
		contentPane.add(tfPrice);
		tfPrice.setColumns(10);
		
		tfAmount = new JTextField();
		tfAmount.setBounds(120, 277, 219, 20);
		contentPane.add(tfAmount);
		tfAmount.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                            if(tfPrice.getText().chars().allMatch(Character::isDigit) && tfAmount.getText().chars().allMatch(Character::isDigit)) {
                                if(tfImportOrderID.getText().equals("") ||cbbProductID.getSelectedItem().equals(null) || cbbSupplierID.getSelectedItem().equals(null) || tfName.getText().equals("") || tfPrice.getText().equals("") || tfAmount.getText().equals("")) {
					JOptionPane.showMessageDialog(rootPane, "Please fill complete information");
				}
				else {
                                    
					Calendar calendar = Calendar.getInstance();
					final String tfDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(calendar.getTime());
					System.out.print(tfDate);
					importOrder i = new importOrder();
					i.setIdI(tfImportOrderID.getText());
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
                                            
//                                            try {
//                                                int k = 100000000;
//                                                String query1 = "select Quantity from qldt.products where ID like '%"+Integer.parseInt(cbbProductID.getSelectedItem().toString())+"%'";
//                                            PreparedStatement ps1 = conn.prepareStatement(query1);
//                                            ResultSet rs = ps1.executeQuery();
//                                       
//                                            while( rs.next()){
//                                                
//                                                 k = rs.getInt("Quantity");
//                                                
//                                                 
//                                            }
//                                            if(Integer.parseInt(tfAmount.getText()) < k){
//                                                System.out.println("okeeela");
                                               
                                                if(new ConnectImportOrder().addProducts(i)) {
							list.add(i);
                              
                                                           
                                                    try {
                                                        String sqlString ="UPDATE qldt.products SET Quantity= Quantity+? WHERE ID=?";
                                                        PreparedStatement ps = conn.prepareStatement(sqlString);
                                                        ps.setInt(1, Integer.parseInt(tfAmount.getText()));
                                                        ps.setInt(2, Integer.parseInt(cbbProductID.getSelectedItem().toString()));
                                                        ps.executeUpdate();
                                                     
                                            
                                                        System.out.println("oke");
                                                    } catch (SQLException ex) {
                                                        Logger.getLogger(ImportOder.class.getName()).log(Level.SEVERE, null, ex);
                                                    }
							JOptionPane.showMessageDialog(rootPane, "Save Successfully");
							showResult();
                                                        
                                                        
                                                     
							tfImportOrderID.setText("");
							cbbProductID.setSelectedItem(null);
							cbbSupplierID.setSelectedItem(null);
							tfName.setText("");
							tfPrice.setText("");
							tfAmount.setText("");
						} 
						else {
							JOptionPane.showMessageDialog(rootPane, "Product's ID cannot be duplicated!");
						}
//                                            }
//                                            else{
//                                                System.out.println("ko okeeela");
//                                                 JOptionPane.showMessageDialog(rootPane, "Amount is greater than the number of available phones");
//                                            }
//                                            } catch (SQLException ex) {
//                                                        Logger.getLogger(ImportOder.class.getName()).log(Level.SEVERE, null, ex);
//                                                    }
						
					}
					
					
				}
                            }
				
				else {
						JOptionPane.showMessageDialog(rootPane, "Amount and Price must be integers !");
					}
				
			}
		});
                
		btnAdd.setBounds(29, 328, 89, 23);
		contentPane.add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tableImportOrder.getSelectedRow();
				if(row >=0) {
					try {
						Calendar calendar = Calendar.getInstance();
						final String tfDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(calendar.getTime());
						conn = DriverManager.getConnection("jdbc:mySQL://localhost:3306/qldt","root","admin");
						String value =(tableImportOrder.getModel().getValueAt(row, 0).toString());
						String query = "update tblimportOrder set ProductID=?,SupplierID=?,Name=?,Price=?,Amount=?,Date=? where ImportOrderID=?";
						PreparedStatement ps = conn.prepareStatement(query);
						ps = conn.prepareStatement(query);
						ps.setString(7, tfImportOrderID.getText());
						ps.setString(1, cbbProductID.getSelectedItem().toString());
						ps.setString(2, cbbSupplierID.getSelectedItem().toString());
						ps.setString(3, tfName.getText());
						ps.setString(4, tfPrice.getText());
						ps.setString(5, tfAmount.getText());
						ps.setString(6, tfDate);
                                                System.out.print(query);
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
							model.setValueAt(tfImportOrderID.getText(), row, 1);
							model.setValueAt(cbbProductID.getSelectedItem(), row, 2);
							model.setValueAt(cbbSupplierID.getSelectedItem(), row, 3);
							model.setValueAt(tfName.getText(), row, 4);
							model.setValueAt(tfPrice.getText(), row, 5);
							model.setValueAt(tfAmount.getText(), row, 6);
							model.setValueAt(tfDate, row, 7);
							JOptionPane.showMessageDialog(null, "Update Successfully");
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
				int row = tableImportOrder.getSelectedRow();

				if(row>=0) {
					try {
						conn = DriverManager.getConnection("jdbc:mySQL://localhost:3306/qldt","root","admin");
						String value =(tableImportOrder.getModel().getValueAt(row, 0).toString());
						String query = "delete from tblimportOrder where ImportOrderID=?";
						PreparedStatement ps = conn.prepareStatement(query);
						ps = conn.prepareStatement(query);
						ps.setString(1, tfImportOrderID.getText());
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
				tfImportOrderID.setText("");
				cbbProductID.setSelectedItem(null);
				cbbSupplierID.setSelectedItem(null);
				tfName.setText("");
				tfPrice.setText("");
				tfAmount.setText("");
			}
		});
		btnClear.setBounds(215, 376, 89, 23);
		contentPane.add(btnClear);
		tableImportOrder.setModel(new javax.swing.table.DefaultTableModel(
				 new Object [][] {

			            },
				 new String [] {

			            }
			));
			
			scrollPane.setViewportView(tableImportOrder);
			
			JLabel lblNewLabel_5 = new JLabel("Import Order ID:");
			lblNewLabel_5.setBounds(10, 86, 99, 14);
			contentPane.add(lblNewLabel_5);
			
			tfImportOrderID = new JTextField();
			tfImportOrderID.setBounds(120, 83, 219, 20);
			contentPane.add(tfImportOrderID);
			tfImportOrderID.setColumns(10);
			
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
						String query = "Select * from tblimportOrder where Name like N'%" + tfSearch.getText() + "%' or Price=" + gia 
								+ " or ImportOrderID like N'%" + tfSearch.getText() + "%' or ProductID ="+ maSP 
								+ "  or SupplierID ="+ maNCC + " or Date like N'%"+ tfSearch.getText() 
								+ "%' or Amount= "+soluong;
						System.out.print(query);
						PreparedStatement ps = conn.prepareStatement(query);
			            ResultSet rs = ps.executeQuery();
			            System.out.println(query);
			            while(rs.next()) {
			            	importOrder i = new importOrder();
			            	i.setIdI(rs.getString("ImportOrderID"));
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
			            DefaultTableModel model = (DefaultTableModel)tableImportOrder.getModel();
			    		model.setRowCount(0);
			    		for(importOrder i : list) {
			    			model.addRow(new Object[] {
			    					j++,i.getIdI(),i.getProductID(), i.getSupplierID(), i.getName(), i.getPrice(), i.getAmount(),i.getDate()
			    			});
			    		}
			            //showTable();
					} catch (Exception e2) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, e);
					}
				}
			});
			tfSearch.setBounds(303, 31, 519, 20);
			contentPane.add(tfSearch);
			tfSearch.setColumns(10);
			
			JButton btnNewButton = new JButton("Search");
			btnNewButton.setBounds(871, 30, 89, 23);
			contentPane.add(btnNewButton);
			
			contentPane.add(cbbProductID);
			
			contentPane.add(cbbSupplierID);
			
			JButton btnBack = new JButton("Back");
			btnBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					new GiaoDienMenu().setVisible(true);
				}
			});
			btnBack.setBounds(143, 482, 89, 23);
			contentPane.add(btnBack);
			
			JButton btnExportExcelFile = new JButton("Export Excel File");
			btnExportExcelFile.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					exportarExcel(tableImportOrder);
				}
			});
			btnExportExcelFile.setBounds(91, 434, 170, 23);
			contentPane.add(btnExportExcelFile);
		this.setLocationRelativeTo(null);
		list = new ConnectImportOrder().getListimportOrder();
		model = (DefaultTableModel) tableImportOrder.getModel();
		model.setColumnIdentifiers(new Object[] {
				"STT", "ID","ProductID", "SupplierID", "Name", "Price", "Amount", "Date"
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
	private JTextField tfImportOrderID;
	private JTextField tfSearch;
	public void showResult() {
		importOrder i = list.get(list.size() -1 );
		model.addRow(new Object[] {
				j++,i.getIdI(),i.getProductID(), i.getSupplierID(),i.getName(), i.getPrice(), i.getAmount(), i.getDate()
		});
	}
}
