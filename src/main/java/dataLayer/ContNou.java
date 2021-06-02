package dataLayer;

/**
 * Acestea sunt datele personale ce le folosesc toate tipurile de conturi: A, C, E:
 * Am un constructor cu nume, prenume, varsta, parola si getteri pentru aceste fielduri, nimic special;
 * Folosesc numele si parola pentru autentificare;
 */
public class ContNou implements java.io.Serializable
{
    private String nume;
    private String prenume;
    private int varsta;
    private String parola;

    public ContNou(String nume, String prenume, int varsta, String parola)
    {
        this.nume = nume;
        this.prenume = prenume;
        this.varsta = varsta;
        this.parola = parola;
    }
    public String getNume() {
        return nume;
    }
    public String getPrenume() {
        return prenume;
    }
    public int getVarsta() {
        return varsta;
    }
    public String getParola() {
        return parola;
    }
}
