package businessLayer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

/**
 * In Delivery Service, sunt toate operatiile din client, administrator si employee;
 * Pe langa, aici retine toate conturile, toate menu itemurile si toate orderurile facute;
 * Ce se cere in plus la aceasta clasa este includerea de pre/post/invarianti, impreuna cu interfata
 * unde sunt declarate aceste operatii, metode;
 * Voi include la fiecare metoda aceste conditii de pre/post/invarianti, si voi aplica asserturi pentru a indeplini
 * verificarea acestor conditii; (sa fie bune datele de intrare, altfel se opreste programul)
 */
public class DeliveryService extends Observable implements IdDeliveryServiceProcessing, java.io.Serializable
{
    private GestionareConturi conturi;
    private ArrayList<MenuItem> menuItems;
    private HashMap<Order, ArrayList<MenuItem>> listaOrderuri;

    public DeliveryService()
    {
        conturi = new GestionareConturi();
        menuItems = new ArrayList<MenuItem>();
        listaOrderuri = new HashMap<>();
    }

    /**
     * Aceasta este conditia de invariant;
     * Returneaza true tot timpul, se va orpi cu false pe cazurile ce nu ar trebui sa continue;
     * Se afla la inceputul catorva din metode;
     */
    protected boolean WellFormed()
    {
        if(conturi.getClientList() == null)
        {
            return false;
        }
        if(conturi.getAdministratorList() == null)
        {
            return false;
        }
        if(conturi.getEmployeeList() == null)
        {
            return false;
        }
        if(menuItems == null)
        {
            return false;
        }
        if(listaOrderuri == null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public void addBaseProduct(BaseProduct b)
    {
        assert WellFormed() : "Nu sunt bune conditiile de inceput pentru Delivery Service!";
        menuItems.add(b);
    }

    public void addCompositeProduct(CompositeProduct c)
    {
        assert WellFormed() : "Nu sunt bune conditiile de inceput pentru Delivery Service!";
        menuItems.add(c);
    }

    public void addOrder(Order order, ArrayList<MenuItem> listaMeniu)
    {
        assert WellFormed() : "Nu sunt bune conditiile de inceput pentru Delivery Service!";
        listaOrderuri.put(order, listaMeniu);
        setChanged();
        String mesaj = "Orderul a fost plasat de catre userul cu id" + order.getClientId()  + " in data de " + order.getOrderDateZi() + "/" +
                order.getOrderDateLuna() + "/" + order.getOrderDateAn() + " la ora " + order.getOrderHour() +
                " , pretul total fiind " + order.getpretTotal() + ";\n" + "Produsele cumparate sunt :\n\n";
        for(MenuItem m: listaMeniu)
        {
            mesaj = mesaj + "Nume: " + m.getTitle() + " ,Rating: " + m.getRating() + //Bun, il ia ala bun!
                    " ,Calorii: " + m.getCalories() + " ,Proteine: " + m.getProtein() +
                    " ,Grasimi: " + m.getFat() + " ,Sodiu: " + m.getSodium() +
                    " ,Pretul: " + m.getPrice() + "\n" ;
        }
        notifyObservers(mesaj);
    }

    @Override
    public ArrayList<BaseProduct> importAdmin() {
        assert menuItems != null : "Nu putem importa!";
        assert WellFormed() : "Nu sunt bune conditiile de inceput pentru Delivery Service!";
        ArrayList<BaseProduct> rezultat = getConturi().getAdministratorList().get(0).importAdmin();
        assert rezultat.size() > 0 : "Nu putem importa!";
        return rezultat;
    }

    @Override
    public BaseProduct addAdmin(String nume, float rating, int calorii, int proteine, int grasimi, int sodium, int pret, ArrayList<MenuItem> listaMeniu) {
        assert listaMeniu != null : "Nu putem adauga!";
        assert WellFormed() : "Nu sunt bune conditiile de inceput pentru Delivery Service!";
        BaseProduct baseProduct = getConturi().getAdministratorList().get(0).addAdmin(nume, rating, calorii, proteine, grasimi, sodium, pret, listaMeniu);
        assert baseProduct != null : "Nu putem adauga!";
        return baseProduct;
    }

    @Override
    public boolean deleteAdmin(String nume, ArrayList<MenuItem> listaMeniu) {
        assert listaMeniu != null : "Nu putem sterge!";
        assert listaMeniu.size() > 0 : "Nu putem sterge!";
        assert WellFormed() : "Nu sunt bune conditiile de inceput pentru Delivery Service!";
        boolean ok = getConturi().getAdministratorList().get(0).deleteAdmin(nume, listaMeniu);
        assert listaMeniu != null : "Nu putem sterge!";
        return ok;
    }

    @Override
    public boolean modifyAdmin(String nume, float rating, int calorii, int proteine, int grasimi, int sodium, int pret, ArrayList<MenuItem> listaMeniu) {
        assert listaMeniu != null : "Nu putem modifica!";
        assert listaMeniu.size() > 0 : "Nu putem modifica!";
        assert WellFormed() : "Nu sunt bune conditiile de inceput pentru Delivery Service!";
        boolean ok = getConturi().getAdministratorList().get(0).modifyAdmin(nume, rating, calorii, proteine, grasimi, sodium, pret, listaMeniu);
        assert listaMeniu.size() > 0 : "Nu putem modifica!";
        return ok;
    }

    @Override
    public CompositeProduct createAdmin(String nume, float rating, int calorii, int proteine, int grasimi, int sodium, int pret, ArrayList<BaseProduct> listaBase, ArrayList<MenuItem> listaMeniu)
    {
        assert listaBase.size() > 0 : "Nu putem creea composite!";
        assert listaMeniu.size() > 0 : "Nu putem creea composite!";
        assert WellFormed() : "Nu sunt bune conditiile de inceput pentru Delivery Service!";
        CompositeProduct compositeProduct = getConturi().getAdministratorList().get(0).createAdmin(nume, rating, calorii, proteine, grasimi, sodium, pret, listaBase, listaMeniu);
        assert compositeProduct != null : "Nu putem creea composite!";
        return compositeProduct;
    }

    @Override
    public boolean raport1Admin(int oraInceput, int oraFinal, HashMap<Order, ArrayList<MenuItem>> listaOrderuri) throws IOException {
        assert oraInceput > -1 : "Nu putem genera raportul 1!";
        assert oraFinal < 24 : "Nu putem genera raportul 1!";
        assert WellFormed() : "Nu sunt bune conditiile de inceput pentru Delivery Service!";
        boolean ok = getConturi().getAdministratorList().get(0).raport1Admin(oraInceput, oraFinal, listaOrderuri);
        assert listaOrderuri != null : "Nu putem genera raportul 1!";
        return ok;
    }

    @Override
    public boolean raport2Admin(int nrMinim, HashMap<Order, ArrayList<MenuItem>> listaOrderuri) throws IOException {
        assert nrMinim > -1 : "Nu putem genera raportul 2!";
        assert WellFormed() : "Nu sunt bune conditiile de inceput pentru Delivery Service!";
        boolean ok = getConturi().getAdministratorList().get(0).raport2Admin(nrMinim, listaOrderuri);
        assert listaOrderuri != null : "Nu putem genera raportul 2!";
        return ok;
    }

    @Override
    public boolean raport3Admin(int nrMinim, int pretMinim, HashMap<Order, ArrayList<MenuItem>> listaOrderuri) throws IOException {
        assert nrMinim > -1 : "Nu putem genera raportul 3!";
        assert pretMinim > -1 : "Nu putem genera raportul 3!";
        assert WellFormed() : "Nu sunt bune conditiile de inceput pentru Delivery Service!";
        boolean ok = getConturi().getAdministratorList().get(0).raport3Admin(nrMinim, pretMinim, listaOrderuri);
        assert listaOrderuri != null : "Nu putem genera raportul 3!";
        return ok;
    }

    @Override
    public boolean raport4Admin(int ziua, int luna, int anul, HashMap<Order, ArrayList<MenuItem>> listaOrderuri) throws IOException {
        assert ziua > 0 : "Nu putem genera raportul 4!";
        assert ziua < 32 : "Nu putem genera raportul 4!";
        assert anul > 1900 : "Nu putem genera raportul 4!";
        assert anul < 2200 : "Nu putem genera raportul 4!";
        assert luna > 0 : "Nu putem genera raportul 4!";
        assert luna < 13 : "Nu putem genera raportul 4!";
        assert WellFormed() : "Nu sunt bune conditiile de inceput pentru Delivery Service!";
        boolean ok = getConturi().getAdministratorList().get(0).raport4Admin(ziua, luna, anul, listaOrderuri);
        assert listaOrderuri != null : "Nu putem genera raportul 4!";
        return ok;
    }

    @Override
    public String[] viewClient(DeliveryService service) {
        assert service.getMenuItems() != null : "Nu putem prezenta itemele!";
        assert WellFormed() : "Nu sunt bune conditiile de inceput pentru Delivery Service!";
        return service.getConturi().getClientList().get(0).viewClient(service);
    }

    @Override
    public ArrayList<MenuItem> searchClient(ArrayList<String> nume, ArrayList<Float> rating,
                             ArrayList<Integer> calories, ArrayList<Integer> protein,
                             ArrayList<Integer> fat, ArrayList<Integer> sodium, ArrayList<Integer> price,
                                            ArrayList<MenuItem> listaMeniu) {
        assert listaMeniu != null : "Nu putem aplica filterele!";
        assert WellFormed() : "Nu sunt bune conditiile de inceput pentru Delivery Service!";
        return getConturi().getClientList().get(0).searchClient(nume, rating, calories, protein, fat, sodium, price, listaMeniu);
    }

    @Override
    public Order makeOrderClient(int orderId, int clientId, int orderDateZi, int orderDateLuna,
                                int orderDateAn, int orderHour, ArrayList<MenuItem> listaProduse) {
        assert orderId > -1 : "Nu putem genera orderul!";
        assert clientId > -1 : "Nu putem genera orderul!";
        assert listaProduse.size() > 0 : "Nu putem genera orderul!";
        assert WellFormed() : "Nu sunt bune conditiile de inceput pentru Delivery Service!";
        assert listaProduse != null : "Nu putem genera orderul!";
        return getConturi().getClientList().get(0).makeOrderClient(orderId, clientId, orderDateZi, orderDateLuna, orderDateAn, orderHour, listaProduse);
    }

    //@Override
    //public void notifyEmployee() {
    //}

    public GestionareConturi getConturi() {
        return conturi;
    }
    public void setConturi(GestionareConturi conturi) {
        this.conturi = conturi;
    }

    public ArrayList<MenuItem> getMenuItems() {
        return menuItems;
    }
    public void setMenuItems(ArrayList<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public HashMap<Order, ArrayList<MenuItem>> getListaOrderuri() {
        return listaOrderuri;
    }
    public void setListaOrderuri(HashMap<Order, ArrayList<MenuItem>> listaOrderuri) { this.listaOrderuri = listaOrderuri; }
}







