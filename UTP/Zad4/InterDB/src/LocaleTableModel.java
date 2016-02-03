

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.swing.table.AbstractTableModel;

public class LocaleTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private String[] headers = {"Id", "Country", "Departure date", "Return date", "Place", "Price", "Currency", "Locale"}; 
	private List<Travel> travels;
	private Locale locale;
	private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
	
	public LocaleTableModel(List<Travel> travels, Locale locale) {
		this.travels = travels;
		this.locale = locale;
	}

	@Override
	public String getColumnName(int column) {
		return ResourceManager.getText(locale, headers[column]);
	}

	@Override
	public int getColumnCount() {
		return headers.length;
	}

	@Override
	public int getRowCount() {
		return travels.size();
	}

	@Override
	public Object getValueAt(int r, int c) {
		Travel t = travels.get(r);
		switch (c) {
			case 0: return t.getId();
			case 1: return ResourceManager.getText(locale, t.getCountry());
			case 2: return dateFormat.format(t.getArrivalDate());
			case 3: return dateFormat.format(t.getReturnDate());
			case 4: return ResourceManager.getText(locale, t.getPlace());
			case 5: return NumberFormat.getInstance(locale).format(t.getPrice());
			case 6: return t.getCurrency();
			case 7: return locale.toString().substring(0, 2);
			default: return "";
		}
	}
}

