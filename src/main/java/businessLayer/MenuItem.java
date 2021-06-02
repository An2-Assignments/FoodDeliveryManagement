package businessLayer;

import java.util.Objects;

/**
 * Din MenuItem este compusa lista de produse;
 * MenuItem are ca mostenitori pe BaseProduct si CompositeProduct;
 * Asta inseamna ca si base si composite au aceste 7 fielduri;
 * Pentru a le distinge, ne uitam la nume, la title; (sa fie diferit)
 * Metodele necomentate sunt in mare getteri si setteri;
 */
public class MenuItem implements java.io.Serializable
{
    private String title;
    private float rating;
    private int calories;
    private int protein;
    private int fat;
    private int sodium;
    private int price;
    private int numarAparitiiOrder;

    /**
     * Un constructor simplu, cu toate fieldurile ca parametrii;
     * @param title
     * @param rating
     * @param calories
     * @param protein
     * @param fat
     * @param sodium
     * @param price
     */
    public MenuItem(String title, float rating, int calories, int protein, int fat, int sodium, int price)
    {
        this.title = title;
        this.rating = rating;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.sodium = sodium;
        this.price = price;
        this.numarAparitiiOrder = 0;
    }

    /**
     * Pentru a folosi distinct pe acest obiect, MenuItem;
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuItem menuItem = (MenuItem) o;
        return title.equals(menuItem.title);
    }

    /**
     * Pentru a folosi distinct pe acest obiect, MenuItem;
     * Fac distinct in functie de title, deci de un field ales de mine;
     * @return
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(title);
    }

    /**
     * Mostenit de celelalte 2 clase;
     */
    public void computePrice()
    {
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public float getRating() {
        return rating;
    }
    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getCalories() {
        return calories;
    }
    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getProtein() {
        return protein;
    }
    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getFat() {
        return fat;
    }
    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getSodium() {
        return sodium;
    }
    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    public int getNumarAparitiiOrder() {
        return numarAparitiiOrder;
    }
    public void setNumarAparitiiOrder(int numarAparitiiOrder) {
        this.numarAparitiiOrder = numarAparitiiOrder;
    }

    /**
     * De cate ori apare in orderuri; (pentru rapoarte administrator)
     */
    public void addAparitie() {numarAparitiiOrder = numarAparitiiOrder + 1;}
}
