package Main;

import java.util.ArrayList;

public class exportOrder {
	private String idE, name, date;
	private int productID,supplierID;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	private long price, amount;
	public String getIdE() {
		return idE;
	}
	public void setIdE(String idE) {
		this.idE = idE;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public int getSupplierID() {
		return supplierID;
	}
	public void setSupplierID(int supplierID) {
		this.supplierID = supplierID;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	
	public exportOrder() {
		
	}
	public exportOrder(String idE, int productID, int supplierID, String name, String date, long price, long amount) {
		//super();
		this.idE = idE;
		this.productID = productID;
		this.supplierID = supplierID;
		this.name = name;
		this.date = date;
		this.price = price;
		this.amount = amount;
	}
	
}
