import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceManager {

	public static String getText(Locale locale, String str) {
		return ResourceBundle.getBundle("Locale", locale).getString(str);
	}
}
