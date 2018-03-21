// Clasa prin intermediul careia se poate construi o tara. Constructorul
// primeste ca argument tara, aceasta fiind varful ierarhiei de locatii.
public class Country
{
  // Variabila ce retine numele tarii.
  private String nameOfCountry;

  public Country(String requiredCountry)
  {
    nameOfCountry = requiredCountry;
  } // constructor

  // Metoda ce permite accesarea datelor.
  public String getCountryName()
  {
    return nameOfCountry;
  } // getCountryName

} // class Country
