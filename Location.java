import java.util.Collections;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.time.temporal.ChronoUnit;

public class Location
{
  private String locationName;
  private City city;
  private double meanPriceDay;
  List<String> activities = new ArrayList<String>();
  private Date startDate;
  private Date endDate;

  // Constructorul fiecarei locatii primeste ca argumente numele acesteia,
  // orasul dorit, pretul mediu pe zi, o data de inceput si una de sfarsit.
  // Separat, lista de activitati va fi creata in timpul parcurgerii datelor.
  public Location(String requiredName, City requiredCity, double requiredPrice,
                  Date requiredStart, Date requiredEnd)
  {
    locationName = requiredName;
    city = requiredCity;
    meanPriceDay = requiredPrice;
    startDate = requiredStart;
    endDate = requiredEnd;
  } // constructor

  public String getLocationName()
  {
    return locationName;
  } // getLocationName

  // Metoda pentru a adauga o activitate noua in lista de activitati a
  // fiecarei locatii, in timpul parcurgerii datelor de intrare.
  public void addNewActivity(String newActivity)
  {
    activities.add(newActivity);
  } // addNewActivity

  // Am folosit metoda between() pentru a calcula perioada de timp dintre
  // doua obiecte temporale de tip DAYS. De asemenea, metoda toInstant()
  // transforma cele doua obiecte temporale in momente instante pentru
  // a putea fi corect comparate.
  public double totalPrice()
  {
    return (double) meanPriceDay
      * ChronoUnit.DAYS.between(startDate.toInstant(), endDate.toInstant());
  } // totalPrice

  // Metoda pentru a calcula pretul unei activitati intr-o locatie pe
  // o perioada de 10 zile.
  public double priceFor10Days()
  {
    return (double) meanPriceDay * 10;
  } // priceFor10Days

  // Metoda ce formateaza modul in care locatia va fi afisata pe ecran
  // in urma cautarilor.
  @Override
  public String toString()
  {
    String activitiesToPrint = "";
    for (int index = 0; index < activities.size(); index++)
      activitiesToPrint += activities.get(index) + " ";
    return "\nNume: " + locationName + "\n" + "Locatie: " + city.getCityName()
          + "\n" + "Pret mediu pe zi: " + meanPriceDay + "\n" + "Perioada: "
          + startDate + " - " + endDate + "\n" + "Activitati: "
          + activitiesToPrint + "\n";
  } // toString

} // class Location
