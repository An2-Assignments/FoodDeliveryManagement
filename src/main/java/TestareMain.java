import businessLayer.AdministratorModel;
import businessLayer.DeliveryService;
import businessLayer.MenuItem;
import businessLayer.Order;
import dataLayer.ContNou;
import dataLayer.Serializator;
import presentationLayer.*;
import java.util.ArrayList;

/**
 * @author Orsan Tudor Alexandru
 * Clasa main, pentru incepere program
 * Am instantiere mode, toate viewurile, controller si terminarea programului.
 * Controllerul foloseste toate views si modelul.
 */
public class TestareMain
{
    public static void main(String[] args)
    {
        DeliveryService model = new DeliveryService();
        Serializator ser = new Serializator();

        model = ser.Deserializare();
        //model = ser.Deserializare();

        PaginaPrincipala viewPrincipal = new PaginaPrincipala(model);
        CreareCont creareCont = new CreareCont(model);
        AdminGUI adminGUI = new AdminGUI(model);
        ClientGUI clientGUI = new ClientGUI(model);
        EmployeeGUI employeeGUI = new EmployeeGUI(model);
        ProduseClient produseClient = new ProduseClient(model);

        Controller controller = new Controller(model, viewPrincipal, creareCont, adminGUI, clientGUI, employeeGUI, produseClient, ser);
        viewPrincipal.setVisible(true);

        System.out.println("Terminare program.");
    }
}

