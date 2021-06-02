package presentationLayer;

import businessLayer.*;
import businessLayer.MenuItem;
import dataLayer.FileWriter;
import dataLayer.Serializator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

/**
 * Aici am foarte multe butoane, ce fac legatura intre model si viewurile sale;
 * Cu aceste butoane, fac toate operatiile sa functioneze (pentru orice tip de cont, administrator, client, employee)
 * Voi trece prin fiecare, mai in detaliu;
 */
public class Controller
{
    private Serializator ser;
    private DeliveryService model;
    private PaginaPrincipala paginaPrincipala;
    private CreareCont creareCont;
    private AdminGUI adminGUI;
    private ClientGUI clientGUI;
    private EmployeeGUI employeeGUI;
    private ProduseClient produseClient;
    private int tipCont;

    private String nameClient = "";
    private String prenameClient = "";
    private int varstaClient = 0;
    private String parolaClient = "";
    private int idClient = 0;

    private ArrayList<MenuItem> listaClientProducts;
    private ArrayList<BaseProduct> listaAdminComposite;

    private int numarOrder = 0;

    private ArrayList<String> numeList;
    private ArrayList<Float> ratingList;
    private ArrayList<Integer> caloriesList;
    private ArrayList<Integer> proteinList;
    private ArrayList<Integer> fatList;
    private ArrayList<Integer> sodiumList;
    private ArrayList<Integer> priceList;

    private FileWriter fileWriter;

    /**
     * Salvez toate views si modelul, dupa care creez pentru fiecare o instanta de buton, cu ajutorul ActionListener;
     * @param model
     * @param paginaPrincipala
     * @param creareCont
     * @param adminGUI
     * @param clientGUI
     * @param employeeGUI
     * @param produseClient
     * @param ser
     */
    public Controller(DeliveryService model, PaginaPrincipala paginaPrincipala, CreareCont creareCont,
                      AdminGUI adminGUI, ClientGUI clientGUI,
                      EmployeeGUI employeeGUI, ProduseClient produseClient, Serializator ser)
    {
        this.ser = ser;
        this.model = model;
        this.paginaPrincipala = paginaPrincipala;
        this.creareCont = creareCont;
        this.adminGUI = adminGUI;
        this.clientGUI = clientGUI;
        this.employeeGUI = employeeGUI;
        this.produseClient = produseClient;
        listaAdminComposite = new ArrayList<>();
        listaClientProducts = new ArrayList<>();
        fileWriter = new FileWriter();

        model.addObserver(employeeGUI);

        numeList = new ArrayList<>();
        ratingList = new ArrayList<>();
        caloriesList = new ArrayList<>();
        proteinList = new ArrayList<>();
        fatList = new ArrayList<>();
        sodiumList = new ArrayList<>();
        priceList = new ArrayList<>();

        paginaPrincipala.butonLogare(new butonLogareListener());
        paginaPrincipala.butonCreeareCont(new butonCreareContListener());

        creareCont.butonCreareContOficial(new butonCreareContOficialListener());
        creareCont.butonInapoiPaginaPrincipala(new butonInapoiPaginaPrincipalaListener());

        adminGUI.butonImport(new butonImportListener());
        adminGUI.butonAdd(new butonAddListener());
        adminGUI.butonDelete(new butonDeleteListener());
        adminGUI.butonModify(new butonModifyListener());
        adminGUI.butonAddNew(new butonAddNewListener());
        adminGUI.butonRaport1(new butonRaport1Listener());
        adminGUI.butonRaport2(new butonRaport2Listener());
        adminGUI.butonRaport3(new butonRaport3Listener());
        adminGUI.butonRaport4(new butonRaport4Listener());
        adminGUI.butonInapoiAdmin(new butonInapoiAdminListener());
        adminGUI.butonAddBase(new butonAddBaseListener());

        clientGUI.butonView(new butonViewListener());
        clientGUI.butonAddCriteriu(new butonAddCriteriuListener());
        clientGUI.butonSearch(new butonSearchListener());
        clientGUI.butonAddProdus(new butonAddProdusListener());
        clientGUI.butonOrder(new butonOrderListener());
        clientGUI.butonInapoiClient(new butonInapoiClientListener());

        produseClient.butonInapoiProduse(new butonInapoiProduseListener());

        employeeGUI.butonInapoiEmployee(new butonInapoiEmployeeListener());
        employeeGUI.butonNotificareEmployee(new butonNotificareEmployeeListener());
    }



    //Operatii administrator:

    /**
     * Pentru import admin:
     * Nu dau nimic din gui, ci presupun ca numele fisierului csv este products;
     * La toate metodele am mesaje pentru eroare si pentru succes, nu intru pe toate cazurile, sunt prea multe;
     * Dupa ce apelez metoda din AdministratorModel, am lista de iteme, pe care o pot afisa in Gui!
     */
    class butonImportListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            ArrayList<BaseProduct> listaMeniu = new ArrayList<>();
            listaMeniu = model.importAdmin();
            for(BaseProduct b: listaMeniu)
            {
                model.addBaseProduct(b);
            }

            ArrayList<BaseProduct> aux = new ArrayList<BaseProduct>();
            aux = (ArrayList)model.getMenuItems().stream().distinct().collect(Collectors.toList());
            model.getMenuItems().removeAll(model.getMenuItems());
            for(MenuItem m: aux)
            {
                model.getMenuItems().add(m);
            }

            adminGUI.resetMenuItems();
            String[] listaAfisata = new String[50000];
            int contor = 0;
            for(MenuItem m: model.getMenuItems())
            {
                listaAfisata[contor] = contor + 1 + ") Nume: " + m.getTitle() + ", Rating: " + m.getRating()
                        + ", Calories: " + m.getCalories() + ", Protein: " + m.getProtein() +
                        ", Fat: " + m.getFat() + ", Sodium: " + m.getSodium() + ", Price: " + m.getPrice();
                contor++;
            }
            int max = model.getMenuItems().size();
            adminGUI.setMenuItems(listaAfisata, max);

            JOptionPane.showMessageDialog(paginaPrincipala,
                    "Am importat cu succes!",
                    "Succes!", JOptionPane.INFORMATION_MESSAGE);
            ser.Serializare(model); //Nu merge bine la adaugare? Am deja model menu itemele!
        }
    }

    /**
     * Pentru add, dau toate datele din gui (de intrare)
     * Dupa care, cu ajutorul clasei AdministratorModel fac obiectul, si il adaug in lista
     * Acum va fi actualizat si ComboBoxul, pentru a putea sa observ cum a fost adaugat itemul nou;
     */
    class butonAddListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                String nume = adminGUI.getNume1();
                float rating = Float.parseFloat(adminGUI.getRating1());
                int calorii = Integer.parseInt(adminGUI.getCalorii1());
                int proteine = Integer.parseInt(adminGUI.getProteine1());
                int grasimi = Integer.parseInt(adminGUI.getGrasimi1());
                int sodium = Integer.parseInt(adminGUI.getSodium1());
                int pret = Integer.parseInt(adminGUI.getPret1());

                BaseProduct baseProduct = model.addAdmin(nume, rating, calorii, proteine, grasimi,
                        sodium, pret, model.getMenuItems());

                if(baseProduct.getTitle().equals("Error") == true)
                {
                    JOptionPane.showMessageDialog(paginaPrincipala,
                            "Avem deja acest produs!",
                            "Error!", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                model.addBaseProduct(baseProduct);

                adminGUI.resetMenuItems();
                String[] listaAfisata = new String[50000];
                int contor = 0;
                for(MenuItem m: model.getMenuItems())
                {
                    listaAfisata[contor] = contor + 1 + ") Nume: " + m.getTitle() + ", Rating: " + m.getRating()
                            + ", Calories: " + m.getCalories() + ", Protein: " + m.getProtein() +
                            ", Fat: " + m.getFat() + ", Sodium: " + m.getSodium() + ", Price: " + m.getPrice();
                    contor++;
                }
                int max = model.getMenuItems().size();
                adminGUI.setMenuItems(listaAfisata, max);

                JOptionPane.showMessageDialog(paginaPrincipala,
                        "Am adaugat cu succes!",
                        "Succes!", JOptionPane.INFORMATION_MESSAGE);
                ser.Serializare(model); //Doar dupa mesaje de succes!
            }
            catch(Exception e1)
            {
                JOptionPane.showMessageDialog(paginaPrincipala,
                        "Nu sunt bune datele de intrare!", "Eroare", //parseint si spatii goale;
                        JOptionPane.INFORMATION_MESSAGE);
                e1.printStackTrace();
            }
        }
    }

    /**
     * Delete merge pe acelasi concept ca add, doar ca va cauta itemul si sterge daca este cazul;
     * Se actualieaza dupa ComboBoxul ca mai sus;
     */
    class butonDeleteListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                String nume = adminGUI.getNume3();
                boolean ok = model.deleteAdmin(nume, model.getMenuItems());
                if(ok)
                {
                    adminGUI.resetMenuItems();
                    String[] listaAfisata = new String[50000];
                    int contor = 0;
                    for(MenuItem m: model.getMenuItems())
                    {
                        listaAfisata[contor] = contor + 1 + ") Nume: " + m.getTitle() + ", Rating: " + m.getRating()
                                + ", Calories: " + m.getCalories() + ", Protein: " + m.getProtein() +
                                ", Fat: " + m.getFat() + ", Sodium: " + m.getSodium() + ", Price: " + m.getPrice();
                        contor++;
                    }
                    int max = model.getMenuItems().size();
                    adminGUI.setMenuItems(listaAfisata, max);


                    JOptionPane.showMessageDialog(paginaPrincipala,
                            "Am sters cu succes!",
                            "Succes!", JOptionPane.INFORMATION_MESSAGE);
                    ser.Serializare(model);
                    return;
                }
            }
            catch(Exception e1)
            {
                JOptionPane.showMessageDialog(paginaPrincipala,
                        "Nu sunt bune datele de intrare!", "Eroare",
                        JOptionPane.INFORMATION_MESSAGE);
                e1.printStackTrace();
                return;
            }

            JOptionPane.showMessageDialog(paginaPrincipala,
                    "Nu a fost gasit elementul!", "Eroare",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * La fel ca delete si add, o combinatie intre cele 2;
     * Dau alte date de la tastatura pentru modificare, pe gui, si caut dupa numele oferit daca este sau nu in lista deja;
     */
    class butonModifyListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                String nume = adminGUI.getNume2();
                float rating = Float.parseFloat(adminGUI.getRating2());
                int calorii = Integer.parseInt(adminGUI.getCalorii2());
                int proteine = Integer.parseInt(adminGUI.getProteine2());
                int grasimi = Integer.parseInt(adminGUI.getGrasimi2());
                int sodium = Integer.parseInt(adminGUI.getSodium2());
                int pret = Integer.parseInt(adminGUI.getPret2());

                boolean ok = model.modifyAdmin(nume, rating, calorii, proteine, grasimi, sodium, pret, model.getMenuItems());
                if(ok)
                {
                    adminGUI.resetMenuItems();
                    String[] listaAfisata = new String[50000];
                    int contor = 0;
                    for(MenuItem m: model.getMenuItems())
                    {
                        listaAfisata[contor] = contor + 1 + ") Nume: " + m.getTitle() + ", Rating: " + m.getRating() + ", Calories: " + m.getCalories() + ", Protein: " + m.getProtein() + ", Fat: " + m.getFat() + ", Sodium: " + m.getSodium() + ", Price: " + m.getPrice();
                        contor++;
                    }
                    int max = model.getMenuItems().size();
                    adminGUI.setMenuItems(listaAfisata, max);

                    JOptionPane.showMessageDialog(paginaPrincipala, "Am modificat cu succes!", "Succes!", JOptionPane.INFORMATION_MESSAGE);
                    ser.Serializare(model);
                    return;
                }
            }
            catch(Exception e1)
            {
                JOptionPane.showMessageDialog(paginaPrincipala, "Nu sunt bune datele de intrare!", "Eroare", JOptionPane.INFORMATION_MESSAGE);
                e1.printStackTrace();
                return;
            }

            JOptionPane.showMessageDialog(paginaPrincipala, "Nu a fost gasit elementul!", "Eroare", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Pentru adaugarea unui Produs Composite:
     * Din lista de BaseProducturi creeata tot din gui cu ajutorul unui alt buton,
     * voi adauga composite product; Calculez inainte datele din obiect, cu ajutorul metodelor de compute,
     * dau clear la lista de base products dupa ce il adaug, tratez daca numele dat este deja in lista, etc...;
     */
    class butonAddNewListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                String nume = adminGUI.getNumeBase();
                if(listaAdminComposite.size() == 0)
                {
                    JOptionPane.showMessageDialog(paginaPrincipala, "Selectati base products inainte!", "Error!", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                CompositeProduct comp = new CompositeProduct(nume, 0, 0, 0, 0, 0, 0, listaAdminComposite);
                comp.computePrice();
                comp.computeAllElse();

                CompositeProduct compositeProduct = model.createAdmin(nume, comp.getRating(), comp.getCalories(), comp.getProtein(), comp.getFat(), comp.getSodium(), comp.getPrice(), listaAdminComposite , model.getMenuItems());
                if(compositeProduct.getTitle().equals("Error") == true)
                {
                    listaAdminComposite.clear();

                    JOptionPane.showMessageDialog(paginaPrincipala, "Avem deja acest produs composite!", "Error!", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                model.addCompositeProduct(compositeProduct);
                listaAdminComposite.clear();

                adminGUI.resetMenuItems();
                String[] listaAfisata = new String[50000];
                int contor1 = 0;
                for(MenuItem m: model.getMenuItems())
                {
                    listaAfisata[contor1] = contor1 + 1 + ") Nume: " + m.getTitle() + ", Rating: " + m.getRating() + ", Calories: " + m.getCalories() + ", Protein: " + m.getProtein() + ", Fat: " + m.getFat() + ", Sodium: " + m.getSodium() + ", Price: " + m.getPrice();
                    contor1++;
                }
                int max = model.getMenuItems().size();
                adminGUI.setMenuItems(listaAfisata, max);
                JOptionPane.showMessageDialog(paginaPrincipala, "Am adaugat cu succes composite product!", "Succes!", JOptionPane.INFORMATION_MESSAGE);
                ser.Serializare(model);
            }
            catch(Exception e1)
            {
                JOptionPane.showMessageDialog(paginaPrincipala, "Nu sunt bune datele de intrare!", "Eroare", JOptionPane.INFORMATION_MESSAGE);
                e1.printStackTrace();
            }
        }
    }

    /**
     * Folosesc metoda din AdministratorModel, si dau datele dorite de la tastatura;
     * Dupa metoda, se afiseaza ori mesaj de succes cu datele anume (in fisier), ori mesaj de eroare;
     * Pe acest principiu merg toate cele 4 butoane de raporturi, nu le mai comentez;
     */
    class butonRaport1Listener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                int oraInceput = Integer.parseInt(adminGUI.getRaport11());
                int oraFinal = Integer.parseInt(adminGUI.getRaport12());
                boolean ok = model.raport1Admin(oraInceput, oraFinal, model.getListaOrderuri());
                if(ok)
                {
                    JOptionPane.showMessageDialog(paginaPrincipala, "Am creat raportul 1 cu succes!", "Succes!", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            }
            catch(Exception e1)
            {
                JOptionPane.showMessageDialog(paginaPrincipala, "Nu sunt bune datele de intrare!", "Eroare", JOptionPane.INFORMATION_MESSAGE);
                e1.printStackTrace();
                return;
            }

            JOptionPane.showMessageDialog(paginaPrincipala, "Nu exista orderi potrivite pentru aceste ore!", "Eroare", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    class butonRaport2Listener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                int nrMinim = Integer.parseInt(adminGUI.getRaport2());
                boolean ok = model.raport2Admin(nrMinim, model.getListaOrderuri());
                if(ok)
                {
                    JOptionPane.showMessageDialog(paginaPrincipala,
                            "Am creat raportul 2 cu succes!",
                            "Succes!", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            }
            catch(Exception e1)
            {
                JOptionPane.showMessageDialog(paginaPrincipala,
                        "Nu sunt bune datele de intrare!", "Eroare",
                        JOptionPane.INFORMATION_MESSAGE);
                e1.printStackTrace();
                return;
            }
            JOptionPane.showMessageDialog(paginaPrincipala,
                    "Nu exista astfel de produse!",
                    "Eroare!", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    class butonRaport3Listener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                int nrMinim = Integer.parseInt(adminGUI.getRaport31());
                int pretMinim = Integer.parseInt(adminGUI.getRaport32());
                boolean ok = model.raport3Admin(nrMinim, pretMinim, model.getListaOrderuri());
                if(ok)
                {
                    JOptionPane.showMessageDialog(paginaPrincipala,
                            "Am creat raportul 3 cu succes!",
                            "Succes!", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            }
            catch(Exception e1)
            {
                JOptionPane.showMessageDialog(paginaPrincipala,
                        "Nu sunt bune datele de intrare!", "Eroare",
                        JOptionPane.INFORMATION_MESSAGE);
                e1.printStackTrace();
            }

            JOptionPane.showMessageDialog(paginaPrincipala, "Nu exista astfel de clienti!", "Eroare!", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    class butonRaport4Listener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                int ziua = Integer.parseInt(adminGUI.getRaport43());
                int luna = Integer.parseInt(adminGUI.getRaport42());
                int anul = Integer.parseInt(adminGUI.getRaport41());
                boolean ok = model.raport4Admin(ziua, luna, anul, model.getListaOrderuri());
                if(ok)
                {
                    JOptionPane.showMessageDialog(paginaPrincipala, "Am creat raportul 4 cu succes!", "Succes!", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            }
            catch(Exception e1)
            {
                JOptionPane.showMessageDialog(paginaPrincipala, "Nu sunt bune datele de intrare!", "Eroare", JOptionPane.INFORMATION_MESSAGE);
                e1.printStackTrace();
                return;
            }

            JOptionPane.showMessageDialog(paginaPrincipala, "Nu exista astfel de produse!", "Eroare!", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    class butonInapoiAdminListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            adminGUI.reset();
            paginaPrincipala.setVisible(true);
            adminGUI.setVisible(false);
        }
    }

    /**
     * Pentru adaugarea unui base product:
     * Selectez din combo box un base product, si dupa il adaug la lista;
     * Verific daca este base product inainte, si in functie de daca este si alte criterii dau un mesaj adecvat de ce s-a intamplat;
     * Folosesc aceasta lista pentru cand adaug un CompositeProduct; (deci urmatorul buton)
     */
    class butonAddBaseListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String base = adminGUI.getc1();
            String firstInt = base.replaceFirst(".*?(\\d+).*", "$1");
            int numar = Integer.parseInt(firstInt);

            if(model.getMenuItems().get(numar-1) instanceof BaseProduct)
            {
                listaAdminComposite.add((BaseProduct) model.getMenuItems().get(numar-1));
            }
            else
            {
                JOptionPane.showMessageDialog(paginaPrincipala, "Nu este selectat un item bun!", "Error!", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            try
            {
                JOptionPane.showMessageDialog(paginaPrincipala, "Am adaugat produsul " + base + " la composite product cu succes!", "Succes!", JOptionPane.INFORMATION_MESSAGE);
                //Aici nu serializam!
            }
            catch(Exception e1)
            {
                JOptionPane.showMessageDialog(paginaPrincipala,
                        "Nu sunt bune datele de intrare!", "Eroare",
                        JOptionPane.INFORMATION_MESSAGE);
                e1.printStackTrace();
            }
        }
    }


    //Operatii client:

    /**
     * Pentru view, dau modelul, pentru ca acolo sunt toate menuItemele;
     * Dupa ce trece prin metoda clientului, setez toate stringurile si in Gui;
     */
    class butonViewListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            int max = model.getMenuItems().size();

            String[] listaAfisata = model.viewClient(model);

            System.out.println("Verificare:");
            model.getListaOrderuri().entrySet().stream().forEach(k -> System.out.println(k.getKey().getOrderId() + " " + k.getKey().getOrderDateAn() + " " + k.getKey().getOrderDateLuna() + " " + k.getKey().getOrderDateZi()));
            for(ArrayList<MenuItem> lista : model.getListaOrderuri().values())
            {
                for(MenuItem m: lista)
                {
                    System.out.println(m.getTitle() + " " + m.getNumarAparitiiOrder());
                }
            }

            produseClient.setMenuItems(listaAfisata, max);
            produseClient.setVisible(true);
            clientGUI.setVisible(false);
        }
    }

    /**
     * Pentru adaugarea unui criteriu de filtrare:
     * In functie de ce selectez din combo boxul cu 7 optiuni, salvez intr-o lista
     * ce criteriu am ales. Dupa, folosesc cele 7 liste pentru a filtra si arata ce am gasit;
     */
    class butonAddCriteriuListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                String criteriuActual = clientGUI.getCriteriuText();
                String criteriu = clientGUI.getCriteriuCombo();
                if(criteriu.equals("KeyWord")) { numeList.add(criteriuActual); }
                else if(criteriu.equals("Rating"))
                { float ratingActual = Float.parseFloat(criteriuActual);
                ratingList.add(ratingActual); }
                else if(criteriu.equals("Number of Calories"))
                { int caloriesActual = Integer.parseInt(criteriuActual);
                caloriesList.add(caloriesActual); }
                else if(criteriu.equals("Proteins"))
                { int proteinsActual = Integer.parseInt(criteriuActual);
                proteinList.add(proteinsActual); }
                else if(criteriu.equals("Fats"))
                { int fatsActual = Integer.parseInt(criteriuActual);
                fatList.add(fatsActual); }
                else if(criteriu.equals("Sodium"))
                { int sodiumActual = Integer.parseInt(criteriuActual);
                sodiumList.add(sodiumActual); }
                else if(criteriu.equals("Price"))
                { int priceActual = Integer.parseInt(criteriuActual);
                priceList.add(priceActual); }
                JOptionPane.showMessageDialog(paginaPrincipala, "Am adaugat criteriul: " + criteriu + ":" + criteriuActual + " cu succes!", "Succes!", JOptionPane.INFORMATION_MESSAGE);
                //Nici aici nu serializez!
            }
            catch(Exception e1)
            {
                JOptionPane.showMessageDialog(paginaPrincipala, "Nu sunt bune datele de intrare!", "Eroare", JOptionPane.INFORMATION_MESSAGE);
                e1.printStackTrace();
            }
        }
    }

    /**
     * Dupa ce am gasit cele 7 liste de cautare, unele goale, altele nu,
     * butonul de search apeleaza metoda din ClientModel pentru a afisa produsele gasite (filtrate);
     * Dupa ce am gasit ce doream, dau clear la cele 7 liste. Asta o fac pentru a putea da search la alte produse,
     * in functie de urmatoarele criterii alese (deci se sterg cele prezente anterior)
     */
    class butonSearchListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                ArrayList<MenuItem> listaFiltrata = new ArrayList<>();
                listaFiltrata = model.searchClient(numeList, ratingList, caloriesList, proteinList, fatList, sodiumList, priceList, model.getMenuItems());

                if(listaFiltrata == null)
                {
                    JOptionPane.showMessageDialog(paginaPrincipala, "Nu avem produse in lista de produse! Introduce-ti produse pentru cautare!", "Eroare", JOptionPane.INFORMATION_MESSAGE);
                    numeList.clear();
                    ratingList.clear();
                    caloriesList.clear();
                    proteinList.clear();
                    fatList.clear();
                    sodiumList.clear();
                    priceList.clear();

                    clientGUI.resetMenuFiltrat();
                    return;
                }
                numeList.clear();
                ratingList.clear();
                caloriesList.clear();
                proteinList.clear();
                fatList.clear();
                sodiumList.clear();
                priceList.clear();

                clientGUI.resetMenuFiltrat();
                String[] listaAfisata = new String[5000];
                int contor1 = 0;

                if(listaFiltrata.size() == 0)
                {
                    listaAfisata[contor1] = "Nu avem produse ce indeplinesc aceste criterii!";
                    clientGUI.setMenuFiltrat(listaAfisata, 0);
                    return;
                }

                for(MenuItem m: listaFiltrata)
                {
                    listaAfisata[contor1] = contor1 + 1 + ") Nume: " + m.getTitle() + ", Rating: " + m.getRating() + ", Calories: " + m.getCalories() + ", Protein: " + m.getProtein() + ", Fat: " + m.getFat() + ", Sodium: " + m.getSodium() + ", Price: " + m.getPrice();
                    contor1++;
                }
                int max = listaFiltrata.size();
                clientGUI.setMenuFiltrat(listaAfisata, max);

                JOptionPane.showMessageDialog(paginaPrincipala, "Am gasit produsele cautate!", "Succes!", JOptionPane.INFORMATION_MESSAGE);
            }
            catch(Exception e1)
            {
                JOptionPane.showMessageDialog(paginaPrincipala,
                        "Nu sunt bune datele de intrare!", "Eroare",
                        JOptionPane.INFORMATION_MESSAGE);
                e1.printStackTrace();
            }
        }
    }

    /**
     * Pentru un order: Am o lista de produse, din care aleg, la fel ca si ideea cu combo box de la criterii;
     * Dupa ce am selectat un produs existent, in adaug la lista de produse;
     */
    class butonAddProdusListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String item = clientGUI.getc2();
            String firstInt = item.replaceFirst(".*?(\\d+).*", "$1");
            int numar = Integer.parseInt(firstInt);

            listaClientProducts.add(model.getMenuItems().get(numar-1));
            model.getMenuItems().get(numar-1).addAparitie();

            try
            {
                JOptionPane.showMessageDialog(paginaPrincipala, "Am adaugat produsul " + item + " la lista pentru order cu succes!", "Succes!", JOptionPane.INFORMATION_MESSAGE);
            }
            catch(Exception e1)
            {
                JOptionPane.showMessageDialog(paginaPrincipala,
                        "Nu sunt bune datele de intrare!", "Eroare",
                        JOptionPane.INFORMATION_MESSAGE);
                e1.printStackTrace();
            }
        }
    }

    /**
     * Cu lista de produse de mai sus, acum pot da order la aceasta lista;
     * Dupa ce dau add order, dau clear la lista de produse din aceasta clasa;
     * Pe langa, mai dau la FileWriter(clasa) datele specifice pentru a putea scrie billul (pentru ca un order a fost executat)
     * Mesaje adecvate sunt puse pentru fiecare situatie: date proaste de intrare, nu au fost selectate produse, etc...;
     */
    class butonOrderListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                if(listaClientProducts.size() == 0)
                {
                    JOptionPane.showMessageDialog(paginaPrincipala, "Selectati meniu inainte de plasare order!", "Error!", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                ArrayList<MenuItem> listaNouaOrder = new ArrayList<>(listaClientProducts);

                LocalDateTime timpActual = LocalDateTime.now();
                int day = timpActual.getDayOfMonth();
                int month = timpActual.getMonthValue();
                int year = timpActual.getYear();
                int hour = timpActual.getHour();
                int size = model.getListaOrderuri().size() + 1;
                int idOrder = size;
                Order order = model.makeOrderClient(idOrder, idClient, day, month, year, hour, listaClientProducts);
                model.addOrder(order, listaNouaOrder);

                System.out.println("Verificare:");
                model.getListaOrderuri().entrySet().stream().forEach(k -> System.out.println(k.getKey().getOrderId() +
                        " " + k.getKey().getOrderDateAn() + " " + k.getKey().getOrderDateLuna() +
                        " " + k.getKey().getOrderDateZi()));

                for(ArrayList<MenuItem> lista : model.getListaOrderuri().values())
                {
                    for(MenuItem m: lista)
                    {
                        System.out.println(m.getTitle() + " " + m.getNumarAparitiiOrder());
                    }
                }

                fileWriter.writeOrderBill(order, listaClientProducts, nameClient, prenameClient, varstaClient);

                listaClientProducts.clear();

                JOptionPane.showMessageDialog(paginaPrincipala, "Am plasat cu succes orderul!", "Succes!", JOptionPane.INFORMATION_MESSAGE);
                ser.Serializare(model);
            }
            catch(Exception e1)
            {
                JOptionPane.showMessageDialog(paginaPrincipala, "Nu sunt bune datele de intrare!", "Eroare", JOptionPane.INFORMATION_MESSAGE);
                e1.printStackTrace();
            }
        }
    }

    class butonInapoiClientListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            clientGUI.resetMenuFiltrat();
            paginaPrincipala.resetare();
            paginaPrincipala.setVisible(true);
            clientGUI.setVisible(false);
        }
    }

    //Interfata view produse:
    class butonInapoiProduseListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            clientGUI.reset();
            produseClient.resetMenuItems();
            clientGUI.setVisible(true);
            produseClient.setVisible(false);
        }
    }


    //Operatii employee:
    class butonInapoiEmployeeListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            paginaPrincipala.resetare();
            paginaPrincipala.setVisible(true);
            employeeGUI.setVisible(false);
        }
    }

    /**
     * Pentru employee: cu acest buton afisez toate stringurile rezultate din orderurile facute;
     * La apasarea acestui buton, ultimele orderuri sunt afisate, deci notificarile orderurilor sunt afisate;
     * Doar la apasarea butonului se merge prin toate orderurile facute recent, pana atunci notificarile sunt lasate ascunse;
     */
    class butonNotificareEmployeeListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            ArrayList<String> mesaje = employeeGUI.getMesaje();
            for(String s : mesaje)
            {
                JOptionPane.showMessageDialog(paginaPrincipala,
                        s, "Succes", JOptionPane.INFORMATION_MESSAGE);
            }
            JOptionPane.showMessageDialog(paginaPrincipala,
                    "Acestea au fost toate notificarile.", "Succes", JOptionPane.INFORMATION_MESSAGE);
            employeeGUI.setMesaje();
        }
    }


    //Operatii pagina principala:

    /**
     * Verifica daca este sau nu pe lista de conturi;
     * Daca este, verifica daca este Client, Administrator sau Emplyee;
     * Retine ce este, si pentru combo boxuri si afiseaza lista de menu item actuala (pentru actualizare in real time)
     * In cazul clientului, se retin si datele sale personale in caz ca face un order;
     * Daca face un order, se va scrie pe bill ce avea in aceste date personale;
     * Daca nu sunt gasite datele cautate, inseamna ca nu exista contul si trebuie ori creeat, ori reincercat (poate s-a gresit scrierea parolei)
     */
    class butonLogareListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            tipCont = 0;

            for(AdministratorModel ad : model.getConturi().getAdministratorList())
            {
                if(ad.getNume().equals(paginaPrincipala.getNume()) &&
                ad.getParola().equals(paginaPrincipala.getParola()))
                {
                    adminGUI.resetMenuItems();
                    String[] listaAfisata = new String[50000];

                    int contor = 0;
                    for(MenuItem m: model.getMenuItems())
                    {
                        listaAfisata[contor] = contor + 1 + ") Nume: " + m.getTitle() + ", Rating: " + m.getRating()
                                + ", Calories: " + m.getCalories() + ", Protein: " + m.getProtein() +
                                ", Fat: " + m.getFat() + ", Sodium: " + m.getSodium() + ", Price: " + m.getPrice();
                        contor++;
                    }

                    int max = model.getMenuItems().size();
                    adminGUI.setMenuItems(listaAfisata, max);
                    tipCont = 5;
                    adminGUI.set();
                    adminGUI.setVisible(true);
                    paginaPrincipala.resetare();
                    paginaPrincipala.setVisible(false);
                    return;
                }
            }

            for(EmployeeModel em : model.getConturi().getEmployeeList())
            {
                if(em.getNume().equals(paginaPrincipala.getNume()) && em.getParola().equals(paginaPrincipala.getParola()))
                {
                    tipCont = 10;
                    employeeGUI.setVisible(true);
                    paginaPrincipala.resetare();
                    paginaPrincipala.setVisible(false);
                    return;
                }
            }

            for(ClientModel cl : model.getConturi().getClientList())
            {
                if(cl.getNume().equals(paginaPrincipala.getNume()) && cl.getParola().equals(paginaPrincipala.getParola()))
                {
                    clientGUI.resetMenuItems();
                    String[] listaAfisata = new String[50000];

                    int contor = 0;
                    for(MenuItem m: model.getMenuItems())
                    {
                        listaAfisata[contor] = contor + 1 + ") Nume: " + m.getTitle() + ", Rating: " + m.getRating()
                                + ", Calories: " + m.getCalories() + ", Protein: " + m.getProtein() +
                                ", Fat: " + m.getFat() + ", Sodium: " + m.getSodium() + ", Price: " + m.getPrice();
                        contor++;
                    }
                    int max = model.getMenuItems().size();
                    clientGUI.setMenuItems(listaAfisata, max);

                    tipCont = 15;
                    nameClient = cl.getNume();
                    prenameClient = cl.getPrenume();
                    varstaClient = cl.getVarsta();
                    parolaClient = cl.getParola();
                    idClient = cl.getId();

                    clientGUI.reset();
                    clientGUI.setVisible(true);
                    paginaPrincipala.resetare();
                    paginaPrincipala.setVisible(false);
                    return;
                }
            }
            JOptionPane.showMessageDialog(paginaPrincipala, "Nu exista acest cont!", "Eroare", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    class butonCreareContListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            paginaPrincipala.setVisible(false);
            paginaPrincipala.resetare();
            creareCont.setVisible(true);
        }
    }


    //Operatii creare cont:

    /**
     * Pentru crearea conturilor:
     * In functie de ce este selectat din combo box, se poate creea un cont de Admin, Client sau Employee;
     * In functie de care a fost ales, se face un obiect nou de acel tip, si se adauga in lista de conturi;
     * Se iau datele personale date (la toate tipurile de conturi sunt aceleasi date);
     * Dupa terminare creeare cont, se revine pe pagina principala in mod automat, dupa un mesaj de succes;
     * Daca nu sunt bune datele, nu se poate creea contul;
     */
    class butonCreareContOficialListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if(creareCont.getCont().equals("Administrator"))
            {
                try
                {
                    AdministratorModel ad = new AdministratorModel(creareCont.getNume(), creareCont.getPrenume(), Integer.parseInt(creareCont.getVarsta()), creareCont.getParola());
                    model.getConturi().addAdiministrator(ad);
                    JOptionPane.showMessageDialog(paginaPrincipala, "Am creeat cu succes administratorul!", "Succes!", JOptionPane.INFORMATION_MESSAGE);
                    ser.Serializare(model); //Pe tot, dupa ce am adaugat;
                }
                catch(Exception e1)
                {
                    JOptionPane.showMessageDialog(paginaPrincipala, "Nu sunt bune datele de intrare!", "Eroare", JOptionPane.INFORMATION_MESSAGE);
                    e1.printStackTrace();
                    return;
                }
            }
            else if(creareCont.getCont().equals("Employee"))
            {
                try
                {
                    EmployeeModel em = new EmployeeModel(creareCont.getNume(), creareCont.getPrenume(), Integer.parseInt(creareCont.getVarsta()), creareCont.getParola());model.getConturi().addEmployee(em);
                    JOptionPane.showMessageDialog(paginaPrincipala, "Am creeat cu succes angajatul!", "Succes!", JOptionPane.INFORMATION_MESSAGE);
                    ser.Serializare(model);
                }
                catch(Exception e1)
                {
                    JOptionPane.showMessageDialog(paginaPrincipala, "Nu sunt bune datele de intrare!", "Eroare", JOptionPane.INFORMATION_MESSAGE);
                    e1.printStackTrace();
                    return;
                }
            }
            else if(creareCont.getCont().equals("Client"))
            {
                try
                {
                    ClientModel cl = new ClientModel(creareCont.getNume(), creareCont.getPrenume(), Integer.parseInt(creareCont.getVarsta()), creareCont.getParola(), model.getConturi().getClientList().size());
                    model.getConturi().addClient(cl);
                    JOptionPane.showMessageDialog(paginaPrincipala, "Am creeat cu succes clientul!", "Succes!", JOptionPane.INFORMATION_MESSAGE);
                    ser.Serializare(model); //Este acel model! nu altceva!
                }
                catch(Exception e1)
                {
                    JOptionPane.showMessageDialog(paginaPrincipala, "Nu sunt bune datele de intrare!", "Eroare", JOptionPane.INFORMATION_MESSAGE);
                    e1.printStackTrace();
                    return;
                }
            }
            paginaPrincipala.setVisible(true);
            creareCont.resetare();
            creareCont.setVisible(false);
        }
    }

    class butonInapoiPaginaPrincipalaListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            creareCont.resetare();
            paginaPrincipala.setVisible(true);
            creareCont.setVisible(false);
        }
    }
}




