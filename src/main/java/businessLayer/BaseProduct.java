package businessLayer;

/**
 * Un item din meniu, mosteneste MenuItem (de asta este super la toate cele 7 fielduri)
 */
public class BaseProduct extends MenuItem
{
    public BaseProduct(String title, float rating, int calories, int protein, int fat, int sodium, int price)
    {
        super(title, rating, calories, protein, fat, sodium, price);
    }
}
