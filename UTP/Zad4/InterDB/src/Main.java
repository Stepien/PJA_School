
import java.io.*;
import java.util.*;
 
public class Main {
 
  public static void main(String[] args) {
    File dataDir = new File("data");
    TravelData travelData = new TravelData(dataDir);
    String dateFormat = "yyyy-MM-dd";
    for (String locale : Arrays.asList("pl_PL", "en_GB")) {
      List<String> odlist = travelData.getOffersDescriptionsList(locale, dateFormat);
      for (String od : odlist) System.out.println(od);
    }

    String id = "12345";
    String url = "jdbc:derby://localhost/"+id;
    Database db = new Database(url, travelData);
    db.create();
    db.showGui();
  }
 
}