package dataLayer;

import businessLayer.AdministratorModel;
import businessLayer.DeliveryService;
import java.io.*;

/**
 * Clasa folosita pentru a pastra datele folosite anterior;
 * Aproape toate datele importante sunt stocate in Delivery Service:
 * Lista de conturi (toate 3 tipurile), menu itemurile si orderurile;
 * Pe langa aceste liste mai sunt si alte fielduri, dar in mare acestea sunt obiectele ce vreau sa le serializez;
 * Prin serializare, retin datele din clasa DeliveryService ca un sir de bytes, dupa care deserializez la inceputul
 * unei noi rulari a programului. Asa retin datele ce au fost rulate inainte, deci cele de la o rulare precedenta a programului;
 * Deci actioneaza ca o baza de date precedenta. (ce creste la fiecare rulare)
 */
public class Serializator
{
    public Serializator()
    {
    }

    /**
     * Serializez la fiecare schimbare a modelului, la fiecare schimbare a listelor din model, etc...
     * Asta inseamna ca de fiecare data pun datele cele mai noi; (Obiectul serializat am ales sa fie in folderul proiectului,
     * si este instanta a clasei DeliveryService)
     * @param model
     */
    public void Serializare(DeliveryService model)
    {
        try
        {
            FileOutputStream fisierOut = new FileOutputStream("C:\\Users\\tudor\\Desktop" +
                    "\\PT2021_30225_Orsan_Tudor_Assignment_4\\Serializare.ser");
            ObjectOutputStream iesire = new ObjectOutputStream(fisierOut);

            iesire.writeObject(model);
            iesire.close();
            fisierOut.close();
            System.out.println("Am serializat.");
        }
        catch (IOException i)
        {
            i.printStackTrace();
        }
    }

    /**
     * Pentru deserializare, iau ce am serializat mai devreme;
     * Singurul moment in care am nevoie de aceasta deserializare este la inceputul porgramului;
     * Deci in main, inainte de restul operatiilor;
     * @return Returnez obiectul DeliveryService, cel ce a retinut datele de la rularile anterioare;
     */
    public DeliveryService Deserializare()
    {
        DeliveryService model = new DeliveryService();
        try {
            FileInputStream fisierIn = new FileInputStream("C:\\Users\\tudor\\Desktop" +
                    "\\PT2021_30225_Orsan_Tudor_Assignment_4\\Serializare.ser");
            ObjectInputStream intrare = new ObjectInputStream(fisierIn);
            model = (DeliveryService) intrare.readObject();
            intrare.close();
            fisierIn.close();
        } catch (IOException i) {
            i.printStackTrace();

            return model;
        }
        catch (ClassNotFoundException c) {
            c.printStackTrace();

            Serializare(model);

            return model;
        }
        return model;
    }
}

