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
import java.io.FileReader;
import java.io.IOException;

public class TestHolidaySpots
{
  public static void main(String[] args)
  {
    //
    FileReader input = null;
    try
    {

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


} // class TestHolidaySpots
