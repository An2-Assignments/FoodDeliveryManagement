package dataLayer;

/**
 * Date personale client;
 * Ce am in plus la client fata de employee si administrator:
 * este un id, pentru a il putea mapa la orderurile pe care le face; (cu ajutorul guiului)
 * De asta am si setter si getter la id;
 */
public class Client extends ContNou
{
    int id;
    public Client(String nume, String prenume, int varsta, String parola, int id)
    {
        super(nume, prenume, varsta, parola);
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
