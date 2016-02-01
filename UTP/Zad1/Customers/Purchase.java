package zad2;

import java.util.StringTokenizer;

public class Purchase {
	private String name, id, prod;
	private Double pri, quat, val;
 
	Purchase(String order) {
		StringTokenizer sToken = new StringTokenizer(order, ";");
		this.id = sToken.nextToken();
		this.name = sToken.nextToken();	
		this.prod = sToken.nextToken();
		this.pri = Double.parseDouble(sToken.nextToken());
		this.quat = Double.parseDouble(sToken.nextToken());
		val = this.quat * this.pri;
	}
 
 
	public String getId() {
		return this.id;
	}
 
	public String getName() {
		return this.name;
	}
 
	public String getProduct() {
		return this.prod;
	}
 
	public Double getPrice() {
		return this.pri;
	}
 
	public Double getQty() {
		return this.quat;
	}
 
	public Double getValue() {
		return this.val;
	}
 
	public Purchase get() {
		return this;
	}
 
}
