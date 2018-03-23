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
  private static HashMap<String,Location> mapOfLocations = new HashMap<String, Location>();

  private static HashMap<String, ArrayList<Location>> countriesMap = new HashMap<>();

  private static HashMap<String, ArrayList<Location>> citiesMap = new HashMap<>();

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
      // Fisierul din care se vor citi datele de intrare.
      input = new BufferedReader(new FileReader("input.txt"));

      // O variabila de tip String care va citi din fisier linie cu linie.
      String currentLine;
      // Un vector ce va contine fiecare cuvant din fiecare linie citita,
      // ajutand ulterior la crearea unui obiect de tip Location.
      String[] arrayElements;

      while((currentLine = input.readLine()) != null)
      {
        // Folosesc metoda split() din clasa String pentru a separa
        // cuvintele citite si a le memora ca elemente in vector.
        arrayElements = currentLine.split(" ");
        int length = arrayElements.length;

        // Pentru a forma un obiect de tip City, este nevoie de crearea
        // unui obiect de tip Country, respectiv unui obiect de tip District.
        Country country = new Country(arrayElements[1]);
        District district = new District(arrayElements[2], country);
        City city = new City(arrayElements[3], district);

        // Obiectul de tip Location este creat pe baza elementelor din vector.
        Location location = new Location(arrayElements[0], city,
                            Double.parseDouble(arrayElements[4]),
                            readDate(arrayElements[length - 2]),
                            readDate(arrayElements[length - 1]));
        // Adaugarea fiecarei locatii in ArrayList-ul aferent fiecarui obiect
        // din clasa Location.
        for (int index = 5; index < length - 2; index++)
        {
          location.addNewActivity(arrayElements[index]);
        } // for

        // Adaugarea noului obiect Location in HashMap.
        mapOfLocations.put(location.getLocationName(), location);

        // Adaugarea noului obiect Location in ArrayList.
        listOfLocations.add(location);

        // Adaugarea obiectelor in HashMap-ul de Countries.
        String countryName = country.getCountryName();
        if (!countriesMap.containsKey(countryName))
          countriesMap.put(countryName, new ArrayList<Location>());
        countriesMap.get(countryName).add(location);

        // Adaugarea obiectelor in HashMap-ul de Cities.
        String cityName = city.getName();
        if (!citiesMap.containsKey(cityName))
          citiesMap.put(cityName, new ArrayList<Location>());
        citiesMap.get(cityName).add(location);

      } // while

      // Utilizatorul poate sa aleaga sa obtina urmatoarele informatii,
      // pe care le va gasi ordonate pe ecran.
      System.out.println();
      System.out.println("Aceasta aplicatie te va ajuta sa alegi unde poti"
                         + " merge in vacanta! Optiunile tale: ");
      System.out.println("1: Afla totul despre o anumita locatie");
      System.out.println("2: Gaseste top 5 locatii dintr-o anumita zona"
                         + " in functie de pretul total pentru acea perioada");
      System.out.println("3: Afla unde este cel mai ieftin sa practici o activitate");

      // Acest scanner va permite utilizatorului sa aleaga o optiune.
      Scanner scanner = new Scanner(System.in);
      System.out.print("Alege una din cele 3 optiuni: ");
      int userOption = scanner.nextInt();

      // Pentru prima optiune, voi cauta informatia despre locatia dorita.
      if (userOption == 1)
      {
        System.out.print("Scrie numele locatiei despre care vrei sa afli: ");
        String searchLocation = scanner.next();
        System.out.println(mapOfLocations.get(searchLocation));
      } // if
      // Pentru a doua optiune, voi sorta arrayList-ul in functie de zona dorita.
      else if (userOption == 2)
      {
        // Aici, utilizatorul isi alege criteriul de cautare.
        System.out.print("Scrie 1 daca vrei sa cauti dupa tari sau 2"
                         + " daca vrei sa cauti dupa orase: ");
        int optionPick = scanner.nextInt();

        // Optiunea #1 reprezinta alegerea cautarii in functie de tara.
        if (optionPick == 1)
          System.out.print("Scrie numele tarii despre care vrei sa afli "
                           + "cele mai bune 5 locatii: ");
        // Optiunea #2 reprezinta alegerea cautarii in functie de oras.
        else if (optionPick == 2)
        System.out.print("Scrie numele orasului despre care vrei sa afli "
                         + "cele mai bune 5 locatii: ");

        String searchArea = scanner.next();
        // In functie de criteriul ales de utilizator, voi crea un Arraylist
        // de locatii, folosindu-ma de HashMap-ul criteriului respectiv.
        // Odata sortat arraylist-ul, primele 5 pozitii vor fi ocupate de
        // cele mai avantajoase locatii.
        if (countriesMap.containsKey(searchArea))
        {
          List<Location> listOfCountryPlaces = countriesMap.get(searchArea);
          Collections.sort(listOfCountryPlaces,
            (Location location1, Location location2) -> {
            if (location1.totalPrice() > location2.totalPrice())
              return 1;
            else if (location1.totalPrice() < location2.totalPrice())
              return -1;
            else
              return location1.getLocationName()
                              .compareTo(location2.getLocationName());
          });
          for (int index = 0; index < 5; index++)
            System.out.println(listOfCountryPlaces.get(index).getInfoTotalPrice());
        } // if
        else if (citiesMap.containsKey(searchArea))
        {
          List<Location> listOfCityPlaces = citiesMap.get(searchArea);
          Collections.sort(listOfCityPlaces,
            (Location location1, Location location2) -> {
            if (location1.totalPrice() > location2.totalPrice())
              return 1;
            else if (location1.totalPrice() < location2.totalPrice())
              return -1;
            else
              return location1.getLocationName()
                              .compareTo(location2.getLocationName());
          });
          for (int index = 0; index < 5; index++)
            System.out.println(listOfCityPlaces.get(index).getInfoTotalPrice());
        } // else if
      } // if

      // Pentru a treia optiune, gasesc locatia unde este cel mai ieftin
      // sa practic o activitate timp de 10 zile.
      else if (userOption == 3)
      {
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
      } // if

    } // try
    catch (IndexOutOfBoundsException exception)
    {
      System.err.println("\nExista mai putin de 5 locatii in zona cautata: "
                        + "\n" + exception);
    } // catch
    catch (Exception exception)
    {
      System.err.println(exception);
    } // catch
    finally
    {
      try { if (input != null) input.close(); }
      catch (IOException exception)
        { System.err.println("Fisierul de input nu a putut fi inchis "
                             + exception); }
    } // finally
  } // main

  // Metoda ce ajuta la citirea unei date si formeaza o noua data.
  private static Date readDate(String newDate)
  {
    String[] dateElements = newDate.split("/");
    int day = Integer.parseInt(dateElements[0]);
    int month = Integer.parseInt(dateElements[1]);
    int year = Integer.parseInt(dateElements[2]);
    return new Date(year + 100, month - 1, day);
  } // readDate

} // class TestHolidaySpots
