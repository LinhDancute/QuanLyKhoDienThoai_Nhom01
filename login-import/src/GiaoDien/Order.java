package GiaoDien;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Order extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Order frame = new Order();
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
	public Order() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel LabelOrder = new JLabel("ORDER MANAGER");
		LabelOrder.setHorizontalAlignment(SwingConstants.CENTER);
		LabelOrder.setFont(new Font("Algerian", Font.BOLD, 28));
		LabelOrder.setBounds(78, 27, 241, 42);
		contentPane.add(LabelOrder);
		
		JButton ButtonImportOrder = new JButton("IMPORT ORDER");
		ButtonImportOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImportOder importOrder = new ImportOder();
				importOrder.show();
				
				dispose();
			}
		});
		ButtonImportOrder.setFont(new Font("Bookman Old Style", Font.BOLD, 15));
		ButtonImportOrder.setBounds(10, 122, 183, 51);
		contentPane.add(ButtonImportOrder);
		
		JButton ButtonExportOrder = new JButton("EXPORT ORDER");
		ButtonExportOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExportOrder exportOrder = new ExportOrder();
				exportOrder.show();
				
				dispose();
			}
		});
		ButtonExportOrder.setFont(new Font("Bookman Old Style", Font.BOLD, 15));
		ButtonExportOrder.setBounds(241, 122, 183, 51);
		contentPane.add(ButtonExportOrder);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new GiaoDienMenu().setVisible(true);
			}
		});
		btnBack.setFont(new Font("Bookman Old Style", Font.BOLD, 15));
		btnBack.setBounds(117, 201, 183, 51);
		contentPane.add(btnBack);
	}

}