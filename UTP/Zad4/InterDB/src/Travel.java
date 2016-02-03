
import java.util.Date;
public class Travel {
	
	private int id;
	private String country;
	private Date arrivalDate;
	private Date returnDate;
	private String place;
	private float price;
	private String currency;
	
	public Travel(int id, String country, Date arrivalDate, Date returnDate,
			String place, float price, String currency) {
		this.id = id;
		this.country = country;
		this.arrivalDate = arrivalDate;
		this.returnDate = returnDate;
		this.place = place;
		this.price = price;
		this.currency = currency;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCountry() {
		return country;
	}
	public Date getArrivalDate() {
		return arrivalDate;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public String getPlace() {
		return place;
	}
	public float getPrice() {
		return price;
	}
	public String getCurrency() {
		return currency;
	}	
}
