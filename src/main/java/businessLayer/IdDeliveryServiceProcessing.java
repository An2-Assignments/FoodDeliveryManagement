package businessLayer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * In aceasta interfata, voi mentiona pre si post conditiile pentru fiecare metoda in parte;
 * Toate metodele unde au sens punerea de pre/post conditii vor fi comentate si explicate aceste conditii;
 * In aceasta interfata exista toate operatiile facute de Administrator, Client si Employee;
 * Interfata este implementata de DeliveryService; Acolo, aceste pre/post conditii sunt implementate
 * cu ajutorul conditiilor booleene, cu asserturi; In cazul in care conditiile nu sunt corecte, se opreste
 * programul si se afiseaza mesajul de incalcare a conditiilor;
 * Daca toate conditiile sunt indeplinite, programul merge normal, fara probleme, nu se opreste la nici o conditie neindeplinita;
 */
public interface IdDeliveryServiceProcessing
{
    /**
     * @inv WellFormed()
     * @pre menuItems != null
     * @post rezultat.size() > 0
     * @return
     */
    public ArrayList<BaseProduct> importAdmin();

    /**
     * @inv WellFormed()
     * @pre listaMeniu != null
     * @post baseProduct != null
     * @param nume
     * @param rating
     * @param calorii
     * @param proteine
     * @param grasimi
     * @param sodium
     * @param pret
     * @param listaMeniu
     * @return
     */
    public BaseProduct addAdmin(String nume, float rating, int calorii, int proteine, int grasimi, int sodium, int pret, ArrayList<MenuItem> listaMeniu);

    /**
     * @inv WellFormed()
     * @pre listaMeniu != null
     * @pre listaMeniu.size() > 0
     * @post listaMeniu != null
     * @param nume
     * @param listaMeniu
     * @return
     */
    public boolean deleteAdmin(String nume, ArrayList<MenuItem> listaMeniu);

    /**
     * @inv WellFormed()
     * @pre listaMeniu != null
     * @pre listaMeniu.size() > 0
     * @post assert listaMeniu.size() > 0
     * @param nume
     * @param rating
     * @param calorii
     * @param proteine
     * @param grasimi
     * @param sodium
     * @param pret
     * @param listaMeniu
     * @return
     */
    public boolean modifyAdmin(String nume, float rating, int calorii, int proteine, int grasimi, int sodium, int pret, ArrayList<MenuItem> listaMeniu);

    /**
     * @inv WellFormed()
     * @pre listaBase.size() > 0
     * @pre listaMeniu.size() > 0
     * @post compositeProduct != null
     * @param nume
     * @param rating
     * @param calorii
     * @param proteine
     * @param grasimi
     * @param sodium
     * @param pret
     * @param listaIteme
     * @param listaMeniu
     * @return
     */
    public CompositeProduct createAdmin(String nume, float rating, int calorii, int proteine, int grasimi, int sodium, int pret, ArrayList<BaseProduct> listaIteme, ArrayList<MenuItem> listaMeniu);

    /**
     * @inv WellFormed()
     * @pre oraInceput > -1
     * @pre 24 > oraFinal
     * @post listaOrderuri != null
     * @param oraInceput
     * @param oraFinal
     * @param listaOrderuri
     * @return
     * @throws IOException
     */
    public boolean raport1Admin(int oraInceput, int oraFinal, HashMap<Order, ArrayList<MenuItem>> listaOrderuri) throws IOException;

    /**
     * @inv WellFormed()
     * @pre nrMinim > -1
     * @post listaOrderuri != null
     * @param nrMinim
     * @param listaOrderuri
     * @return
     * @throws IOException
     */
    public boolean raport2Admin(int nrMinim, HashMap<Order, ArrayList<MenuItem>> listaOrderuri) throws IOException;

    /**
     * @inv WellFormed()
     * @pre nrMinim > -1
     * @pre pretMinim > -1
     * @post listaOrderuri != null
     * @param nrMinim
     * @param pretMinim
     * @param listaOrderuri
     * @return
     * @throws IOException
     */
    public boolean raport3Admin(int nrMinim, int pretMinim, HashMap<Order, ArrayList<MenuItem>> listaOrderuri) throws IOException;

    /**
     * @inv WellFormed()
     * @pre ziua > 0
     * @pre 32 > ziua
     * @pre anul > 1900
     * @pre 2200 > anul
     * @pre luna > 0
     * @pre 13 > luna
     * @post listaOrderuri != null
     * @param ziua
     * @param luna
     * @param anul
     * @param listaOrderuri
     * @return
     * @throws IOException
     */
    public boolean raport4Admin(int ziua, int luna, int anul, HashMap<Order, ArrayList<MenuItem>> listaOrderuri) throws IOException;

    /**
     * @inv WellFormed()
     * @pre service.getMenuItems() != null
     * @post
     * @param service
     * @return
     */
    public String[] viewClient(DeliveryService service);

    /**
     * @inv WellFormed()
     * @pre listaMeniu != null
     * @post
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
    public ArrayList<MenuItem> searchClient(ArrayList<String> nume, ArrayList<Float> rating,
                                            ArrayList<Integer> calories, ArrayList<Integer> protein,
                                            ArrayList<Integer> fat, ArrayList<Integer> sodium, ArrayList<Integer> price,
                                            ArrayList<MenuItem> listaMeniu);

    /**
     * @inv WellFormed()
     * @pre orderId > -1
     * @pre clientId > -1
     * @pre listaProduse.size() > 0
     * @post listaProduse != null
     * @param orderId
     * @param clientId
     * @param orderDateZi
     * @param orderDateLuna
     * @param orderDateAn
     * @param orderHour
     * @param listaProduse
     * @return
     */
    public Order makeOrderClient(int orderId, int clientId, int orderDateZi, int orderDateLuna, int orderDateAn, int orderHour, ArrayList<MenuItem> listaProduse);

    //public void notifyEmployee();
}