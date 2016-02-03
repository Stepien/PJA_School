

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;


public class TravelData {

	private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	public final List<Travel> travels = new ArrayList<>();
	private Locale locale;
	private ResourceBundle resourceBundle;
	
	public String getTranslation(String s) {
		for (String key : resourceBundle.keySet()) {
			if (resourceBundle.getString(key).equals(s)) {
				return key;
			}
		}
		return null;
	}
	
	public TravelData(File directory) {
		
		int id = 0;
		for (File file : directory.listFiles()) {
			Scanner scanner = null;
			try {
				scanner = new Scanner(new FileInputStream(file), "UTF-8");
				while (scanner.hasNextLine()) {
					
					String inputLine = scanner.nextLine();			
					String[] values = inputLine.split("\t");
					locale = new Locale(values[0].substring(0, 2));
					resourceBundle = ResourceBundle.getBundle("Locale", locale);
					String country = getTranslation(values[1]);
					String place = getTranslation(values[4]);
					String currency = values[6];
					Date arrivaleDate = null;
					Date returnDate = null;
					float price;
					try {
						arrivaleDate = dateFormat.parse(values[2]);
						returnDate = dateFormat.parse(values[3]);
						price = NumberFormat.getInstance(locale).parse(values[5]).floatValue();
					} catch (ParseException e) {
						continue;
					}
					travels.add(new Travel(id++, country, arrivaleDate, returnDate, place, price, currency));
				}
			} catch (FileNotFoundException e) {
				return;
			} finally {
				scanner.close();
			}
	    }
	}
	
	public List<String> getOffersDescriptionsList(String localeStr, String dateFormatStr) {
		List<String> descriptionList = new ArrayList<>();
		locale = new Locale(localeStr.substring(0, 2));
		resourceBundle = ResourceBundle.getBundle("Locale", locale);
		StringBuilder sb = new StringBuilder();
		for (Travel travel : travels) {
			sb = new StringBuilder();
			sb.append(resourceBundle.getString(travel.getCountry())).append(" ");
			sb.append(dateFormat.format(travel.getArrivalDate())).append(" ");
			sb.append(dateFormat.format(travel.getReturnDate())).append(" ");
			sb.append(resourceBundle.getString(travel.getPlace())).append(" ");
			sb.append(NumberFormat.getInstance(locale).format(travel.getPrice())).append(" ");
			sb.append(travel.getCurrency());
			descriptionList.add(sb.toString());
		}
		return descriptionList;
	}
}
