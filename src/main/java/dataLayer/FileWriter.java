package dataLayer;

import businessLayer.MenuItem;
import businessLayer.Order;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Folosesc aceasta clasa pentru a scrie un bill atunci cand un client face un order;
 * Nu am decat un field pentru obiectul de scriere in fisier text, si o functie pentru a scrie billul;
 * Fac billuri noi pentru fiecare nou order (de la toti clientii)
 */
public class FileWriter
{
    private static java.io.FileWriter F1;

    /**
     * Ce informatii doresc sa scriu in fisier despre order:
     * @param order Date despre order
     * @param listaProduse Ce produse au fost alese pentru acel order
     * @param nameClient Nume client care a facut order
     * @param prenameClient Alte date despre client
     * @param varstaClient ALte date despre client
     * @throws IOException Daca nu se poate scrie in fisier
     */
    public void writeOrderBill(Order order, ArrayList<MenuItem> listaProduse, String nameClient, String prenameClient, int varstaClient) throws IOException {
        F1 = new java.io.FileWriter("OrderBill"+ order.getOrderId() +".txt");

        try {
            F1.write("Orderul a fost plasat de catre userul " + nameClient + " " + prenameClient + " , varsta " + varstaClient + " in data de " + order.getOrderDateZi() + "/" + order.getOrderDateLuna() + "/" + order.getOrderDateAn() + " la ora " + order.getOrderHour() + " , pretul total fiind " + order.getpretTotal() + " ;\n" + "Produsele cumparate sunt :\n\n");
        } catch (IOException e) {
            System.out.println("An error occurred while writing.");
            e.printStackTrace();
        }

        for(MenuItem m: listaProduse)
        {
            try {
                F1.write("Nume: " + m.getTitle() + " ,Rating: " + m.getRating() +
                        " ,Calorii: " + m.getCalories() + " ,Proteine: " + m.getProtein() +
                        " ,Grasimi: " + m.getFat() + " ,Sodiu: " + m.getSodium() +
                        " ,Pretul: " + m.getPrice() + "\n");
            } catch (IOException e) {
                System.out.println("An error occurred while writing.");
                e.printStackTrace();
            }
        }
        F1.close();
    }
}
