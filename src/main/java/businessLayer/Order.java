package businessLayer;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Order este ce plaseaza un client;
 * Avem ca fielduri orderul cu id-ul sau, actualizat in functie de cat de multe orderuri au fost inainte,
 * id client, mapam cate un client cu toate orderurile facute de acesta,
 * data cand a fost facut orderurl, imrepuna cu ora, si pretul total, al tuturor produselor;
 * Metodele unde nu am comentat sun setteri si getteri;
 */
public class Order implements java.io.Serializable
{
    private int orderId;
    private int clientId;
    private int orderDateZi;
    private int orderDateLuna;
    private int orderDateAn;
    private int orderHour;
    private int pretTotal;

    /**
     * Un constructor simplu;
     * @param orderId
     * @param clientId
     * @param orderDateZi
     * @param orderDateLuna
     * @param orderDateAn
     * @param orderHour
     */
    public Order(int orderId, int clientId, int orderDateZi, int orderDateLuna, int orderDateAn, int orderHour)
    {
        this.orderId = orderId;
        this.clientId = clientId;
        this.orderDateZi = orderDateZi;
        this.orderDateLuna = orderDateLuna;
        this.orderDateAn = orderDateAn;
        this.orderHour = orderHour;
        this.pretTotal = 0;
    }

    /**
     * Adaug pretul tuturor produselor, pentru a gasi pretul final al orderului;
     * @param listaMeniu
     */
    public void addPretOrder(ArrayList<MenuItem> listaMeniu)
    {
        for(MenuItem m : listaMeniu)
        {
            pretTotal = pretTotal + m.getPrice();
        }
    }

    /**
     * Pentru a reusi sa folosesc order drept cheie in HashMap;
     * A trebui suprascris equals si hashCode, in functie, de Id-ul orderului (care oricum este unic)
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderId == order.orderId;
    }

    /**
     * Pentru a reusi sa folosesc order drept cheie in HashMap;
     * A trebui suprascris equals si hashCode, in functie, de Id-ul orderului (care oricum este unic)
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(orderId);
    }

    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getClientId() {
        return clientId;
    }
    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getOrderDateZi() {
        return orderDateZi;
    }
    public void setOrderDateZi(int orderDateZi) {
        this.orderDateZi = orderDateZi;
    }

    public int getOrderDateLuna() {
        return orderDateLuna;
    }
    public void setOrderDateLuna(int orderDateLuna) {
        this.orderDateLuna = orderDateLuna;
    }

    public int getOrderDateAn() {
        return orderDateAn;
    }
    public void setOrderDateAn(int orderDateAn) {
        this.orderDateAn = orderDateAn;
    }

    public int getOrderHour() {
        return orderHour;
    }
    public void setOrderHour(int orderHour) {
        this.orderHour = orderHour;
    }

    public int getpretTotal() {
        return pretTotal;
    }
    public void setpretTotal(int pretTotal) {
        this.pretTotal = pretTotal;
    }
}