package businessLayer;

import dataLayer.Administrator;
import dataLayer.Client;
import dataLayer.ContNou;
import dataLayer.Employee;
import java.util.ArrayList;
import java.util.List;

/**
 * Aici tot ce fac este sa retin cele 3 liste:
 * Client, Administrator, Employee: retin toate cele 3 liste de conturi posibile;
 * Voi folosi aceasta clasa in DeliveryService, pentru acces la conturi;
 * La metodele necomentate: Sunt doar add-eri sau sett-eri si gett-eri;
 */
public class GestionareConturi implements java.io.Serializable
{
    private ArrayList<AdministratorModel> administratorList;
    private ArrayList<ClientModel> clientList;
    private ArrayList<EmployeeModel> employeeList;

    /**
     * Initializare simpla;
     */
    public GestionareConturi()
    {
        administratorList = new ArrayList<>();
        clientList = new ArrayList<>();
        employeeList = new ArrayList<>();
    }

    public void addAdiministrator(AdministratorModel a)
    {
        administratorList.add(a);
    }
    public void addClient(ClientModel c)
    {
        clientList.add(c);
    }
    public void addEmployee(EmployeeModel e)
    {
        employeeList.add(e);
    }

    public ArrayList<AdministratorModel> getAdministratorList() {
        return administratorList;
    }
    public void setAdministratorList(ArrayList<AdministratorModel> administratorList)
    { this.administratorList = administratorList; }

    public ArrayList<ClientModel> getClientList() {
        return clientList;
    }
    public void setClientList(ArrayList<ClientModel> clientList) {
        this.clientList = clientList;
    }

    public ArrayList<EmployeeModel> getEmployeeList() {
        return employeeList;
    }
    public void setEmployeeList(ArrayList<EmployeeModel> employeeList) {
        this.employeeList = employeeList;
    }
}
