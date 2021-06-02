package presentationLayer;

import businessLayer.DeliveryService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Aici ma folosesc de acest gui pentru a vedea ultimele orderuri facute, de diversi clienti;
 * Cu ajutorul Design Patternului Observer, dau notify la o adaugare de order de catre un client
 * la acest gui, si acest gui va afisa toate orderurile facute, o singura data, dupa ce au fost facute;
 * Nu este nimic special, doar ideea de a fi notificat dupa o actiune anume;
 */
public class EmployeeGUI extends JFrame implements Observer
{
    private DeliveryService model;
    private ArrayList<String> mesaje;

    private JLabel l1 = new JLabel("Notificare orderuri clienti:");
    private JButton b1 = new JButton("Notificare ultimele orderuri");
    private JButton b2 = new JButton("Inapoi");

    public EmployeeGUI(DeliveryService model)
    {
        this.model = model;
        mesaje = new ArrayList<>();

        l1.setFont(new Font("Times New Roman", Font.BOLD, 60));
        b1.setFont(new Font("Times New Roman", Font.BOLD, 30));
        b1.setPreferredSize(new Dimension(500, 80));
        b2.setFont(new Font("Times New Roman", Font.BOLD, 40));
        b2.setPreferredSize(new Dimension(200, 60));

        JPanel c = new JPanel();
        c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
        c.setPreferredSize(new Dimension(1000, 750));
        c.setBackground(Color.green);

        JPanel rand1 = new JPanel();
        rand1.setLayout(new FlowLayout());
        rand1.setBackground(Color.blue);

        JPanel rand2 = new JPanel();
        rand2.setLayout(new FlowLayout());
        rand2.setBackground(Color.blue);

        JPanel rand3 = new JPanel();
        rand3.setLayout(new FlowLayout());
        rand3.setBackground(Color.blue);

        rand1.add(l1);
        rand1.add(Box.createRigidArea(new Dimension(0, 10)));

        rand2.add(b1);
        rand2.add(Box.createRigidArea(new Dimension(0, 10)));

        rand3.add(b2);
        rand3.add(Box.createRigidArea(new Dimension(0, 10)));

        c.add(rand1);
        c.add(Box.createRigidArea(new Dimension(0, 15)));
        c.add(rand2);
        c.add(Box.createRigidArea(new Dimension(0, 15)));
        c.add(rand3);
        c.add(Box.createRigidArea(new Dimension(0, 15)));

        this.setContentPane(c);
        this.pack();
        this.setTitle("Angajat chamber");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void butonNotificareEmployee(ActionListener buton)
    {
        b1.addActionListener(buton);
    }

    public void butonInapoiEmployee(ActionListener buton)
    {
        b2.addActionListener(buton);
    }

    public ArrayList<String> getMesaje()
    {
        return mesaje;
    }

    public void setMesaje()
    {
        mesaje.removeAll(mesaje);
    }

    /**
     * Override la metoda update, pentru a adauga un mesaj de la un order specific preluat acum;
     * Toate mesajele vor fi afisate atunci cand apas pe butonul de afisare notificare de pe aceasta interfata;
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        mesaje.add((String) arg);
        System.out.println(arg);
    }
}

