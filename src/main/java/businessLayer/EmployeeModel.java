package businessLayer;

import dataLayer.Employee;

/**
 * Nu am mai ajuns sa folosesc aceasta functie (tot ce fac pentru Employee este in GUI, la notificare, nu am avut nevoie de aceasta clasa)
 * Am ales sa o las in daca mai doresc sa adaug in viitor alte metode;
 */
public class EmployeeModel extends Employee
{
    public EmployeeModel(String nume, String prenume, int varsta, String parola)
    {
        super(nume, prenume, varsta, parola);
    }
}
