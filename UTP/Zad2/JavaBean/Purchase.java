import java.beans.*;
import java.io.*;

public class Purchase implements Serializable, PropertyChangeListener, VetoableChangeListener{

	private static final long serialVersionUID = 1L;
	private String prod;
	private String data;
	private double price;
	
	double min =1000;
	
	public Purchase() {
		
	}	
	public Purchase(String prod, String data, double price) {
		
		this.prod =prod;
		this.data = data;
		this.price = price; 		
		
	}	
	public void setProd(String p){
		
		prod = p;
		
	}
	public String getProd(){
		
		return this.prod;
	}
	private PropertyChangeSupport changes = new PropertyChangeSupport(this);
	public synchronized void addPropertyChangeListener(PropertyChangeListener p){
				changes.addPropertyChangeListener(p);
			}
	public synchronized void removePropertyChangeListener(PropertyChangeListener p){
				changes.removePropertyChangeListener (p);
			}

	public synchronized void setData(String data){
		
		String oldData = new String (this.data);
		this.data = data;
		changes.firePropertyChange("data", oldData, new String(this.data));		
		
	}
	public String getData(){
		
		return this.data;
	}
	private VetoableChangeSupport vetoes = new VetoableChangeSupport (this);
			public synchronized void addVetoableChangeListener (VetoableChangeListener v) {
			vetoes.addVetoableChangeListener (v);
			}
			public synchronized void removeVetoableChangeListener (VetoableChangeListener v) {
			vetoes.removeVetoableChangeListener (v);
			}

	public synchronized void setPrice(double price) throws PropertyVetoException{
		
		Double oldPrice = new Double (this.price);
		vetoes.fireVetoableChange ("price", oldPrice, new Double (price));
		this.price = price;
		changes.firePropertyChange("price", oldPrice, new Double(this.price));
			
	}
	public double getPrice(){
		
		return this.price;
	}
	@Override
	public void propertyChange(PropertyChangeEvent evt) {	
										
		
		System.out.println("Change value of: " + evt.getPropertyName() +" from: " + evt.getOldValue() + " to: " + evt.getNewValue());
		  		   	

	}
	public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException{			
			
		Double newVal = (Double) evt.getNewValue();
		double val = newVal.doubleValue();
		
		if (val < min){

			throw new PropertyVetoException("Price change to: "+newVal+" not allowed", evt);
		}
	}
	
	public String toString(){
				
		return "Purchase [prod="+prod+", "+"data="+data+","+"price="+price+"]";
	}
	
}  