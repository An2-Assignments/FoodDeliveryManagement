package businessLayer;

import java.util.ArrayList;

/**
 * La fel ca si BaseProduct, extinde MenuItem:
 * Ce are in plus este o lista de BaseProduct, din care este compusa;
 */
public class CompositeProduct extends MenuItem
{
    private ArrayList<BaseProduct> listaIteme;

    /**
     * Un constructor simplu; Dau direct si lista de baseProducts;
     * @param title
     * @param rating
     * @param calories
     * @param protein
     * @param fat
     * @param sodium
     * @param price
     * @param listaIteme
     */
    public CompositeProduct(String title, float rating, int calories, int protein, int fat, int sodium, int price, ArrayList<BaseProduct> listaIteme)
    {
        super(title, rating, calories, protein, fat, sodium, price);
        this.listaIteme = new ArrayList<>();
        this.listaIteme = listaIteme;
    }

    /**
     * Pentru compute price, mostenita de la MenuItem;
     */
    @Override
    public void computePrice()
    {
        for(MenuItem m: listaIteme)
        {
            int aux = getPrice();
            setPrice(aux + m.getPrice());
        }
    }

    /**
     * Ca si la compute price, adun la ce era inainte, deci fac un numar total pentru fiecare field din lista de BaseProducts;
     * Pentru rating, fac media aritmetica;
     */
    public void computeAllElse()
    {
        float ok = 0;
        for(MenuItem m: listaIteme)
        {
            ok++;
            float aux1 = getRating();
            int aux2 = getCalories();
            int aux3 = getProtein();
            int aux4 = getFat();
            int aux5 = getSodium();
            setRating(aux1 + m.getRating());
            setCalories(aux2 + m.getCalories());
            setProtein(aux3 + m.getProtein());
            setFat(aux4 + m.getFat());
            setSodium(aux5 + m.getSodium());
        }
        if(getRating() == 0 && getCalories() == 0 && getProtein() == 0 && getFat() == 0 && getSodium() == 0)
        {
            return;
        }
        float aux = getRating();
        setRating(aux/ok);
    }

    public void addItem(BaseProduct b)
    {
        listaIteme.add(b);
    }
}

