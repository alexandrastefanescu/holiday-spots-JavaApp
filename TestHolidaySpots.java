/* Aplicatie Java care ajuta oamenii sa aleaga unde sa mearga in vacanta.
   Prin intermediul aplicariei, un utilizator poate cauta prin date,
   obtinand urmatoarele informatii despre o anumita locatie X, top 5
   locatii din tara/judetul/orasul X ordonate dupa cost sau unde este
   cel mai ieftin practicarea unei activitati timp de 10 zile.
*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

// Clasa de test, unde voi incerca obtinerea tuturor tipurilor de
// informatii listate mai sus. Datele de intrare sunt introduse cu
// ajutorul unui fisier primit ca primul argument command line,
// iar cele de iesire sunt printate intr-un alt fisier, acesta fiind
// al doilea argument command line. In cazul exceptiilor, acestea sunt
// luate in considerare prin folosirea "try-catch"-urilor.

public class TestHolidaySpots
{
  // Un HashMap cu toate locatiile pentru a putea obtine rapid informatia
  // despre o anumita locatie in cazul primei cerinte.
  private static HashMap<String,Location> mapOfLocations
                                          = new HashMap<String, Location>();

  // Un ArrayList cu toate locatiile pentru a putea sorta informatia
  // in cazul celor doua considerente pentru ultimele cerinte:
  // pretul total, respectiv pretul pentru 10 zile intr-o anumita locatie.
  private static List<Location> listOfLocations = new ArrayList<Location>();

  // Metoda main, unde voi testa cerintele.
  public static void main(String[] args)
  {
    // Initial, inputul primeste o referinta nula.
    BufferedReader input = null;
    try
    {
      input = new BufferedReader(new FileReader("input.txt"));

      String currentLine;
      String[] arrayElements;
      while((currentLine = input.readLine()) != null)
      {
        arrayElements = currentLine.split(" ");
        int length = arrayElements.length;
        Country country = new Country(arrayElements[1]);
        District district = new District(arrayElements[2], country);
        City city = new City(arrayElements[3], district);
        Location location = new Location(arrayElements[0], city,
                            Double.parseDouble(arrayElements[4]),
                            readDate(arrayElements[length - 2]),
                            readDate(arrayElements[length - 1]));
        for (int index = 5; index < length - 2; index++)
        {
          location.addNewActivity(arrayElements[index]);
        } // for
        mapOfLocations.put(location.getLocationName(), location);
        listOfLocations.add(location);
      } // while

      //Scanner scanner = new Scanner(System.in);
      //System.out.print("Alege locatia despre care vrei sa afli: ");
      //String searchLocation = scanner.next();
      //System.out.println(mapOfLocations.get(searchLocation));

      // Sortarea
      Collections.sort(listOfLocations, (Location location1, Location location2)
        -> {
        if (location1.totalPrice() > location2.totalPrice())
          return 1;
        else if (location1.totalPrice() < location2.totalPrice())
          return -1;
        else
          return location1.getLocationName()
                          .compareTo(location2.getLocationName());
      });
      for (int index = 0; index < 5; index++)
      {
        System.out.println(listOfLocations.get(index).getInfoTotalPrice());
      } // for

      // A treia cerinta: gasirea unei locatii unde este cel mai
      // ieftin sa practic o activitate timp de 10 zile.
      // Compar prima valoare cu toate celelalte valori din arraylist-ul
      // ce contine toate locatiile.
      double cheapestPriceLocation = listOfLocations.get(0).priceFor10Days();
      int indexCheapestLocation = 0;
      for (int index = 1; index < listOfLocations.size(); index++)
      {
        if(cheapestPriceLocation > listOfLocations.get(index).priceFor10Days())
        {
          cheapestPriceLocation = listOfLocations.get(index).priceFor10Days();
          indexCheapestLocation = index;
        } //if
      } // for
      System.out.println(listOfLocations.get(indexCheapestLocation)
                         .getInfoPrice10Days());

    } // try
    catch (Exception exception)
    {
      System.err.println(exception);
    } // catch
    finally
    {
      try { if (input != null) input.close(); }
      catch (IOException exception)
        { System.err.println("Input couldn't be closed " + exception); }
    } // finally
  } // main

  // Metoda ce ajuta la citirea unei date si formeaza o noua data.
  // Am presupus ca toate posibilitatile de vacante au loc in acest an,
  // astfel incat datele de intrare vor fi intervale de forma 'zi/luna'.
  private static Date readDate(String newDate)
  {
    String[] dateElements = newDate.split("/");
    int day = Integer.parseInt(dateElements[0]);
    int month = Integer.parseInt(dateElements[1]);
    int year = Integer.parseInt(dateElements[2]);
    return new Date(year + 100, month - 1, day);
  } // readDate

} // class TestHolidaySpots
