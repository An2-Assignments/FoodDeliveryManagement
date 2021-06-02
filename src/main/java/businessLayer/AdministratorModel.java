package businessLayer;

import dataLayer.Administrator;

import javax.swing.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * In administrator model am cele mai multe operatii: (discut la fiecare metoda ce am)
 * In clasa am fieldurile preluate de la un cont nou si fieldurile de FileWriter pentru scrierea in rapoarte;
 * In rest, am toate operatiile de pe gui de la administrator;
 */
public class AdministratorModel extends Administrator
{
    private static FileWriter F1;
    private static FileWriter F2;
    private static FileWriter F3;
    private static FileWriter F4;

    /**
     * Aici am doar un constructor ce foloseste super de la Administrator, ContNou;
     * @param nume Nume pentru date personale
     * @param prenume Pentru date personale
     * @param varsta Pentru date personale
     * @param parola Pentru date personale
     */
    public AdministratorModel(String nume, String prenume, int varsta, String parola){ super(nume, prenume, varsta, parola); }

    /**
     * Prima operatie: de import din fisier .csv;
     * Am un path name la folderul cu proiectul (trebuie schimat)
     * Folosesc citirea din csv si lambda expressions cu streams pentru a gasi Base Producturile dorite;
     * Iau linie cu linie, dau skip la prima linie, si mapez din string la obiectul creat;
     * Dau distinct sa nu ia dubluri de obiecte, dupa care termin.
     * @return Dau return la lista de produse base din csv, in totalitate;
     */
    public ArrayList<BaseProduct> importAdmin()
    {
        ArrayList<BaseProduct> meniuCsv = new ArrayList<BaseProduct>();
        try{
            File fisierCsv = new File("C:\\Users\\tudor\\Desktop" +
                    "\\PT2021_30225_Orsan_Tudor_Assignment_4\\products.csv");
            InputStream in = new FileInputStream(fisierCsv);
            BufferedReader fisierFinal = new BufferedReader(new InputStreamReader(in));

            meniuCsv = (ArrayList)fisierFinal.lines().skip(1).map(k ->
            {
                String[] sir = k.split(",");
                BaseProduct produs = new  BaseProduct(sir[0], Float.parseFloat(sir[1]),
                        Integer.parseInt(sir[2]), Integer.parseInt(sir[3]), Integer.parseInt(sir[4]),
                        Integer.parseInt(sir[5]), Integer.parseInt(sir[6]));
                return produs;
            }).distinct().collect(Collectors.toList());
            fisierFinal.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return meniuCsv;
    }

    /**
     * Toti parametrii sunt ce voi folosi sa creez un nou BaseProduct;
     * Am nevoie de un spatiu la nume pentru ca asa a fost in csv, cu spatiu, deci am adaugat;
     * Verific daca a mai aparut sau nu acel obiect in lista de produse pana acum, sa nu am duplicate;
     * @param nume
     * @param rating
     * @param calorii
     * @param proteine
     * @param grasimi
     * @param sodium
     * @param pret
     * @param listaMeniu Verific in aceasta lista daca mai exista sau nu cu acel nume produs;
     * @return Returnez obiectul creat daca nu a mai fost altul cu acel nume
     */
    public BaseProduct addAdmin(String nume, float rating, int calorii, int proteine, int grasimi, int sodium, int pret, ArrayList<MenuItem> listaMeniu) {
        int ok = 0;
        nume = nume + ' ';
        for(MenuItem m: listaMeniu)
        {
            System.out.println(m.getTitle() + " comparat cu " + nume);
            if(m.getTitle().equals(nume))
            {
                ok = 1;
                break;
            }
        }
        if(ok == 1)
        {
            BaseProduct baseProduct = new BaseProduct("Error", 0, 0, 0, 0, 0, 0);
            return baseProduct;
        }
        BaseProduct baseProduct = new BaseProduct(nume, rating, calorii, proteine, grasimi, sodium, pret);
        return baseProduct;
    }

    /**
     * Verific daca exista ce vreau sa sterg, daca exista, il sterg; (si alte observatii asemanatoare cu functia de mai sus)
     * @param nume
     * @param listaMeniu
     * @return Cu boolean verific daca am sters sau nu;
     */
    public boolean deleteAdmin(String nume, ArrayList<MenuItem> listaMeniu) {
        int ok = 0;
        nume = nume + ' ';
        int count = 0;
        for(MenuItem m: listaMeniu)
        {
            if(m.getTitle().equals(nume))
            {
                ok = 1;
                break;
            }
            count++;
        }
        if(ok == 1)
        {
            listaMeniu.remove(count);
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Caut dupa nume produsul; Daca exista il modific, daca nu, nu; (cu noile date luate din GUI)
     * @param nume
     * @param rating
     * @param calorii
     * @param proteine
     * @param grasimi
     * @param sodium
     * @param pret
     * @param listaMeniu
     * @return Din nou returnez boolean daca am dat gasit si modificat obiectul sau daca nu l-am gasit;
     */
    public boolean modifyAdmin(String nume, float rating, int calorii, int proteine, int grasimi, int sodium, int pret, ArrayList<MenuItem> listaMeniu) {
        nume = nume + ' ';
        for(MenuItem m: listaMeniu)
        {
            if(m.getTitle().equals(nume))
            {
                m.setRating(rating);
                m.setCalories(calorii);
                m.setProtein(proteine);
                m.setFat(grasimi);
                m.setSodium(sodium);
                m.setPrice(pret);
                return true;
            }
        }
        return false;
    }

    /**
     * Pentru adaugarea unui compositeProduct; Verific prima data sa nu existe alt composite product sau orice product cu acest nume;
     * Dupa, doar creez obiectul nou;
     * @param nume
     * @param rating
     * @param calorii
     * @param proteine
     * @param grasimi
     * @param sodium
     * @param pret
     * @param listaIteme lista base product din composite product;
     * @param listaMeniu lista totala produse;
     * @return Returnez compositeProduct pentru prelucrare;
     */
    public CompositeProduct createAdmin(String nume, float rating, int calorii, int proteine, int grasimi, int sodium, int pret, ArrayList<BaseProduct> listaIteme, ArrayList<MenuItem> listaMeniu)
    {
        int ok = 0;
        nume = nume + ' ';
        for(MenuItem m: listaMeniu)
        {
            if(m.getTitle().equals(nume))
            {
                ok = 1;
                break;
            }
        }
        if(ok == 1)
        {
            ArrayList<BaseProduct> listaB = new ArrayList<>();
            CompositeProduct compositeProduct = new CompositeProduct("Error", 0, 0, 0, 0, 0, 0, listaB);
            return compositeProduct;
        }
        CompositeProduct CompositeProduct = new CompositeProduct(nume, rating, calorii, proteine, grasimi, sodium, pret, listaIteme);
        return CompositeProduct;
    }

    /**
     * In raport verific toate orderurile intre ora de inceput si de final;
     * Creez obiectul pentru scriere in fisier, dupa care scriu in raport 1; Asa voi face la toate cele 4 raporturi
     * Cu expresii lambda si streamuri aflu lista de orderuri cautate, dupa care in functie de ce am gasit:
     * scriu cu try/catch in fisier; Trebuie sa parcurg element cu element pentru scrierea in fisier, nu pentru altceva (try and catch)
     * Puteam cu un forEach cu stream, dar pentru scrierea cu try catch am ales sa merg din element in element; Asa am facut la toate functiile de genul;
     * @param oraInceput
     * @param oraFinal
     * @param listaOrderuri
     * @return Returnez daca a fost sau nu facuta scrierea in raport sub forma de boolean
     * @throws IOException Daca nu se poate scrie in fisier
     */
    public boolean raport1Admin(int oraInceput, int oraFinal, HashMap<Order, ArrayList<MenuItem>> listaOrderuri) throws IOException {
        F1 = new FileWriter("Raportul 1.txt");
        List<Order> listaOrder = listaOrderuri.entrySet().stream().filter(k -> k.getKey().getOrderHour() > oraInceput).filter(k -> k.getKey().getOrderHour() < oraFinal).map(Map.Entry::getKey).collect(Collectors.toList());

        int ok = 0;
        List<Order> rezultat = listaOrder;
        for(Order o: rezultat)
        {
            ok = 1;
            try {
                F1.write("Orderul " + o.getOrderId() + " a fost facut intre ora " + oraInceput + "" + " si ora " + oraFinal + " ,la exact ora " + o.getOrderHour() + " .\n");
            } catch (IOException e) {
                System.out.println("An error occurred while writing.");
                e.printStackTrace();
            }
        }
        if(ok == 0)
        {
            try {
                F1.write("Nu exista orderuri care sa se incadreze intre ora " + oraInceput + " si intre ora " + oraFinal + " .\n");
            } catch (IOException e) {
                System.out.println("An error occurred while writing.");
                e.printStackTrace();
            }
        }
        F1.close();
        if(ok == 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    /**
     * Toate produsele comandate de un numar minim de ori;
     * Cu expresii lambda si streamuri am gasit lista de elemente cerute, dupa care am parcurs-o pentru a o scrie in fisier (nu pe ecran sau pe gui)
     * Nu am facut nimic special, doar am salvat in mai multe liste rezultatele, pentru a reusi sa ajung pana la urma la ce imi trebuie, fara duplicate sau date eronate;
     * @param nrMinim
     * @param listaOrderuri
     * @return
     * @throws IOException
     */
    public boolean raport2Admin(int nrMinim, HashMap<Order, ArrayList<MenuItem>> listaOrderuri) throws IOException {
        F2 = new FileWriter("Raportul 2.txt");
        int ok = 0;
        List<ArrayList<MenuItem>> listaListeIteme = listaOrderuri.entrySet().stream().map(v1->v1.getValue()).collect(Collectors.toList());
        List<MenuItem> combinate = new ArrayList<>();
        listaListeIteme.stream().forEach(p -> p.stream().filter(v->v.getNumarAparitiiOrder() > nrMinim).distinct().forEach(x -> combinate.add(x)));
        List<MenuItem> rezultatFinal = combinate.stream().distinct().collect(Collectors.toList());

        for(MenuItem m : rezultatFinal)
        {
            ok = 1;
            try {
                F2.write("Produsul comandat care se incadreaza sa fie comandat de mai mult de " + nrMinim + " ori se numeste " + m.getTitle() + " ;\n");
            } catch (IOException e) {
                System.out.println("An error occurred while writing.");
                e.printStackTrace();
            }
        }
        if(ok == 0)
        {
            try {
                F2.write("Nu exista produse care sa fi fost comandate de mai mult de " + nrMinim + " de ori .\n");
            } catch (IOException e) {
                System.out.println("An error occurred while writing.");
                e.printStackTrace();
            }
        }
        F2.close();
        if(ok == 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    /**
     * La fel ca la celelalte 2 produse: Am cautat toti clientii ce au comandat un numar mai mare de NrMinim
     * de orderuri, si orderurile comandate sa fi avut un pret mai mare de pretMinim.
     * Am gasit mai intai din HashMap lista de orderuri, dupa care id clienti care se incadreaza,
     * dupa care am grupat id-urile daca au fost intalnite de mai multe ori (sau si doar 1 data), dupa care am filtrat pentru a lua numarul minim de aparitii mai mare decat ce se cerea;
     * Dupa am parcurs lista rezultata ca sa o scriu in fisier;
     * @param nrMinim
     * @param pretMinim
     * @param listaOrderuri
     * @return
     * @throws IOException
     */
    public boolean raport3Admin(int nrMinim, int pretMinim, HashMap<Order, ArrayList<MenuItem>> listaOrderuri)
            throws IOException {
        F3 = new FileWriter("Raportul 3.txt");
        List<Order> idOrders = listaOrderuri.entrySet().stream().map(Map.Entry::getKey).collect(Collectors.toList());
        List<Integer> idClienti = idOrders.stream().filter(k -> k.getpretTotal() > pretMinim).map(k -> k.getClientId()).collect(Collectors.toList());
        Map<Integer, Long> mapare = idClienti.stream().collect(Collectors.groupingBy(k -> k, Collectors.counting()));
        List<Integer> rezultatId = mapare.entrySet().stream().filter(k -> k.getValue() > nrMinim).map(Map.Entry::getKey).collect(Collectors.toList());

        int ok = 0;
        List<Integer> rezultat = rezultatId;
        for(int i: rezultat)
        {
            ok = 1;
            try {
                F3.write("Clientul care a comandat mai mult de " + nrMinim + " orderuri si valoarea orderului " + "a fost mai mare decat " + pretMinim + " are idul " + i + " ;\n");
            } catch (IOException e) {
                System.out.println("An error occurred while writing.");
                e.printStackTrace();
            }
        }
        if(ok == 0)
        {
            try {
                F3.write("Nu sunt clienti asemenea.");
            } catch (IOException e) {
                System.out.println("An error occurred while writing.");
                e.printStackTrace();
            }
        }
        F3.close();
        if(ok == 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    /**
     * Aceeasi idee ca peste tot;
     * Am folosit lambda si streamuri pentru a afla orderurile cu ziua, luna si anul anume, date ca parametrii;
     * Dupa, am aflat de cate ori a fost comandat un produs in acea zi; (pentru fiecare produs comandat in acea zi)
     * Am luat lista de produse, dupa care am parcurs-o si am adaugat in alta lista toate menuItemurile din toate orderurile;
     * Dupa cu o grupare Map am aflat de cate ori apare fiecare, si dupa am aflat la fiecare MenuItem de cate ori apare; (cu rez1 si rez2)
     * @param ziua
     * @param luna
     * @param anul
     * @param listaOrderuri
     * @return
     * @throws IOException
     */
    public boolean raport4Admin(int ziua, int luna, int anul, HashMap<Order, ArrayList<MenuItem>> listaOrderuri)
            throws IOException  {
        F4 = new FileWriter("Raportul 4.txt");
        System.out.println(ziua + " " + luna + " " + anul);
        List<ArrayList<MenuItem>> listaIteme = listaOrderuri.entrySet().stream().filter(k -> k.getKey().getOrderDateZi() == ziua).filter(k -> k.getKey().getOrderDateLuna() == luna).filter(k -> k.getKey().getOrderDateAn() == anul).map(Map.Entry::getValue).collect(Collectors.toList());
        List<MenuItem> listaMenuItem = new ArrayList<>();
        listaIteme.stream().forEach(k -> k.stream().forEach(t -> listaMenuItem.add(t)));
        Map<MenuItem, Long> mapare = listaMenuItem.stream().collect(Collectors.groupingBy(k -> k, Collectors.counting()));
        List<MenuItem> rez1 = mapare.entrySet().stream().map(Map.Entry::getKey).collect(Collectors.toList());
        List<Long> rez2 = mapare.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());

        int ok = 0;
        List<MenuItem> rezultat = rez1;
        int contor = -1;
        for(MenuItem m: rezultat)
        {
            contor++;
            ok = 1;
            try {
                F4.write("Produsul " + m.getTitle() + " a aparut de " + rez2.get(contor) + " in ziua de " + anul + "/" + luna + "/" + ziua + " ;\n");
            } catch (IOException e) {
                System.out.println("An error occurred while writing.");
                e.printStackTrace();
            }
        }

        if(ok == 0)
        {
            try {
                F4.write("Nu avem produse potrivite!\n");
            } catch (IOException e) {
                System.out.println("An error occurred while writing.");
                e.printStackTrace();
            }
        }
        F4.close();
        if(ok == 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}

