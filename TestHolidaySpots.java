/* Aplicatie Java care ajuta oamenii sa aleaga unde sa mearga in vacanta.
   Prin intermediul aplicariei, un utilizator poate cauta prin date,
   obtinand urmatoarele informatii despre o anumita locatie X, top 5
   locatii din tara/judetul/orasul X ordonate dupa cost sau unde este
   cel mai ieftin practicarea unei activitati timp de 10 zile.
*/

// Clasa de test, unde vom incerca obtinerea tuturor tipurilor de
// informatii listate mai sus. Datele de intrare sunt introduse cu
// ajutorul unui fisier primit ca primul argument command line,
// iar cele de iesire sunt printate intr-un alt fisier, acesta fiind
// al doilea argument command line. In cazul exceptiilor, acestea sunt
// luate in considerare prin folosirea "try-catch"-urilor.
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TestHolidaySpots
{
  private static HashMap<String,Location> mapOfLocations
    = new HashMap<String, Location>();

  public static void main(String[] args)
  {
    //
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
      } // while

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
    return new Date(2018, month, day);
  } // readDate

} // class TestHolidaySpots
