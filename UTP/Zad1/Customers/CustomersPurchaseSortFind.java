/**
 *
 *  @author Stępniewski Jarosław S12391
 *
 */
package zad2;
import java.io.*;
import java.util.*;

public class CustomersPurchaseSortFind {
List<Purchase> orders =  new ArrayList<Purchase>();
 
	public void readFile(String fname) {
		try {
			BufferedReader inputStream = new BufferedReader(new FileReader(fname));
			String line;
			while ((line = inputStream.readLine()) != null ){
				Purchase order = new Purchase(line);						
				orders.add(order);
			}
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Collections.sort(orders, new Comparator<Purchase>() {
	        @Override
	        public int compare(Purchase item1, Purchase item2){
	            return  item1.getId().compareTo(item2.getId());
	        }
	    });
	}
	public void showSortedBy(String type) {
		System.out.println(type);
		if (type == "Nazwiska"){
			Collections.sort(orders, new Comparator<Purchase>() {
		        @Override
		        public int compare(Purchase item1, Purchase item2){
		            return  item1.getName().compareTo(item2.getName());
		        }
		    });		
			for (Purchase purchase : orders) {
				System.out.println(purchase.getId() + ";" + purchase.getName() + ";" + purchase.getProduct() + ";" + purchase.getPrice() + ";" + purchase.getQty());
			}
		}
		else if (type == "Koszty"){
			Collections.sort(orders, new Comparator<Purchase>() {
		        @Override
		        public int compare(Purchase item1, Purchase item2){
		            return  item2.getValue().compareTo(item1.getValue());
		        }
		    });	
			for (Purchase purchase : orders) {
				System.out.println(purchase.getId() + ";" + purchase.getName() + ";" + purchase.getProduct() + ";" + purchase.getPrice() + ";" + purchase.getQty() + " (koszt: " + purchase.getValue() + ")");
			}
		}		
		else 
			System.out.println("Błędny wybór sortowania");
	}
	public void showPurchaseFor(String id) {
		System.out.println("Klient " + id);
		Collections.sort(orders, new Comparator<Purchase>() {
	        @Override
	        public int compare(Purchase item1, Purchase item2){
	            return  item1.getQty().compareTo(item2.getQty());
	        }
	    });	
		for (Purchase purchase : orders) {
			if(purchase.getId().equals(id))
			System.out.println(purchase.getId() + ";" + purchase.getName() + ";" + purchase.getProduct() + ";" + purchase.getPrice() + ";" + purchase.getQty());
		}
	}
}