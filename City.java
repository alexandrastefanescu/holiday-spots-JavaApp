// Clasa prin intermediul careia se poate construi un oras. Constructorul
// va primi ca argument numele orasului, precum si judetul si tara de
// care apartine.
public class City
{
  private String nameOfCity;
  private District district;

  public City(String requiredCity, District requiredDistrict)
  {
    nameOfCity = requiredCity;
    district = requiredDistrict;
  } // constructor

  // Metode ce ajuta la accesarea datelor.
  public String getCityName()
  {
    return nameOfCity + ", " + district.getDistrictName();
  } // getCityName

  public District getCityDistrict()
  {
    return district;
  } // getCityDistrict

} // City
