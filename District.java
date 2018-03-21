// Clasa prin intermediul careia se poate construi un judet. Constructorul
// va primi ca argument numele judetului si tara de care apartine.
public class District
{
  private String nameOfDistrict;
  private Country country;

  public District(String requiredDistrict, Country requiredCountry)
  {
    nameOfDistrict = requiredDistrict;
    country = requiredCountry;
  } // Constructor

  // Metode ce ajuta la accesarea datelor.
  public String getDistrictName()
  {
    return nameOfDistrict + ", " + country.getCountryName();
  } // getDistrictName

  public Country getDistrictCountry()
  {
    return country;
  } // getDistrictCountry

} // District
