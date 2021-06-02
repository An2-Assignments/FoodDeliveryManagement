package businessLayer;
import dataLayer.Client;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Alt set de operatii, ca si la Administrator Model;
 * De data asta, sunt operatiile care pot fi facute de client
 * Voi intra in detaliu la fiecare, in rest fieldurile sunt mostenite ca la administrator;
 */
public class ClientModel extends Client
{
    /**
     * Tot ce am in plus este fieldul de id;
     * @param nume
     * @param prenume
     * @param varsta
     * @param parola
     * @param id AM avut nevoie de acest field pentru Orders, pentru ca trebuie mapate idurile clientilor la orderurile facute de acestia (ca o cheie straina)
     */
    public ClientModel(String nume, String prenume, int varsta, String parola, int id)
    {
        super(nume, prenume, varsta, parola, id);
    }

    /**
     * Pentru view: Creez lista ce o voi afisa pe ComboBox; Trec prin toate menuItemurile, pana cand le gasesc pe toate;
     * @param model
     * @return Returnez lista finala, de stringuri;
     */
    public String[] viewClient(DeliveryService model)
    {
        String[] listaAfisata = new String[50000];
        int contor = 0;
        for(MenuItem m: model.getMenuItems())
        {
            listaAfisata[contor] = contor + 1 + ") Nume: " + m.getTitle() + ", Rating: " + m.getRating() + ", Calories: " + m.getCalories() + ", Protein: " + m.getProtein() + ", Fat: " + m.getFat() + ", Sodium: " + m.getSodium() + ", Price: " + m.getPrice();
            contor++;
        }
        return listaAfisata;
    }

    /**
     * Fac astfel aceasta functie:
     * Am 7 liste; Fiecare lista contine unul sau mai multe criterii (fat: 2, fat: 5, etc...) si trebuie sa fac astfel:
     * Inainte de a aplica filterul cu stream, trebuie sa verific daca am sau nu elemente in lista; In functie
     * de daca am sau nu elemente in una din cele 7, ori nu modific lista cu nimic, ori se modifica, si raman
     * doar elementele dorite; (filtrate)
     * Totul este facut cu streamuri, inafara de verificarea if/else pentru a verifica daca lista este sau nu goala;
     * Nu am gasit alta metoda sa verific; Nu mi se pare ca afecteaza cu ceva;
     * Puteam face mai simplu, fara liste, si asa nu mai trebuia sa pun conditii de if, else, am ales sa fac asa:
     * @param nume
     * @param rating
     * @param calories
     * @param protein
     * @param fat
     * @param sodium
     * @param price
     * @param listaMeniu
     * @return
     */
    public ArrayList<MenuItem> searchClient(ArrayList<String> nume, ArrayList<Float> rating, ArrayList<Integer> calories, ArrayList<Integer> protein, ArrayList<Integer> fat, ArrayList<Integer> sodium, ArrayList<Integer> price, ArrayList<MenuItem> listaMeniu)
    {
        if(listaMeniu.size() == 0)
        {
            return null;
        }
        ArrayList<MenuItem> filtrare1;
        if(nume.size() > 0)
        {
            filtrare1 = new ArrayList<>();
            nume.stream().forEach(k -> listaMeniu.stream().filter(p -> p.getTitle().indexOf(k) != -1).forEach(t -> filtrare1.add(t)));
        }
        else
        {
            filtrare1 = new ArrayList<>(listaMeniu);
        }
        ArrayList<MenuItem> filtrare2;
        if(rating.size() > 0)
        {
            filtrare2 = new ArrayList<>();
            rating.stream().forEach(k -> filtrare1.stream().filter(p -> p.getRating() == k).forEach(t -> filtrare2.add(t)));
        }
        else
        {
            filtrare2 = new ArrayList<>(filtrare1);
        }
        ArrayList<MenuItem> filtrare3;
        if(calories.size() > 0)
        {
            filtrare3 = new ArrayList<>();
            calories.stream().forEach(k -> filtrare2.stream().filter(p -> p.getCalories()== k).forEach(t -> filtrare3.add(t)));
        }
        else
        {
            filtrare3 = new ArrayList<>(filtrare2);
        }
        ArrayList<MenuItem> filtrare4;
        if(protein.size() > 0)
        {
            filtrare4 = new ArrayList<>();
            protein.stream().forEach(k -> filtrare3.stream().filter(p -> p.getProtein() == k).forEach(t -> filtrare4.add(t)));
        }
        else
        {
            filtrare4 = new ArrayList<>(filtrare3);
        }
        ArrayList<MenuItem> filtrare5;
        if(fat.size() > 0)
        {
            filtrare5 = new ArrayList<>();
            fat.stream().forEach(k -> filtrare4.stream().filter(p -> p.getFat()== k).forEach(t -> filtrare5.add(t)));
        }
        else
        {
            filtrare5 = new ArrayList<>(filtrare4);
        }
        ArrayList<MenuItem> filtrare6;
        if(sodium.size() > 0)
        {
            filtrare6 = new ArrayList<>();
            sodium.stream().forEach(k -> filtrare5.stream().filter(p -> p.getSodium() == k).forEach(t -> filtrare6.add(t)));
        }
        else
        {
            filtrare6 = new ArrayList<>(filtrare5);
        }
        ArrayList<MenuItem> filtrare7;
        if(price.size() > 0)
        {
            filtrare7 = new ArrayList<>();
            price.stream().forEach(k -> filtrare6.stream().filter(p -> p.getPrice() == k).forEach(t -> filtrare7.add(t)));
        }
        else
        {
            filtrare7 = new ArrayList<>(filtrare6);
        }
        ArrayList<MenuItem> produseCautate = new ArrayList<>();
        produseCautate = (ArrayList<MenuItem>) filtrare7.stream().distinct().collect(Collectors.toList());
        return produseCautate;
    }

    /**
     * Doar creez orderul, si adaug pretul total al tuturor produselor din lista ordonata;
     * @param orderId
     * @param clientId
     * @param orderDateZi
     * @param orderDateLuna
     * @param orderDateAn
     * @param orderHour
     * @param listaProduse Lista comandata pentru order;
     * @return Returnez orderul creat
     */
    public Order makeOrderClient(int orderId, int clientId, int orderDateZi, int orderDateLuna, int orderDateAn, int orderHour, ArrayList<MenuItem> listaProduse)
    {
        Order order = new Order(orderId, clientId, orderDateZi, orderDateLuna, orderDateAn, orderHour);
        order.addPretOrder(listaProduse);
        return order;
    }
}









