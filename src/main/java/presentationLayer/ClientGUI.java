package presentationLayer;

import businessLayer.DeliveryService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Aceeasi idee ca la administrator;
 * Dau set si reset cand dau pe butonul de setare sau pe butonul de inapoi (depinde de situatie)
 * Asa este mai constant programul si functioneaza intr-un mod mai intuitiv;
 */
public class ClientGUI extends JFrame
{
    private DeliveryService model;

    private JLabel l0 = new JLabel("Pagina de client:");
    private JLabel l1 = new JLabel("Pentru vizionat produse:");
    private JLabel l2 = new JLabel("Pentru cautat produse:");
    private JLabel l3 = new JLabel("Pentru order produse:");
    private JLabel l4 = new JLabel("Criteriul:");
    private JLabel l5 = new JLabel("Produsul:");
    private JLabel l6 = new JLabel("Lista selectata:");

    private JTextField t1= new JTextField(10);

    private JButton b1 = new JButton("View");
    private JButton b2 = new JButton("Add Criteriu");
    private JButton b3 = new JButton("Search");
    private JButton b4 = new JButton("Add Produs");
    private JButton b5 = new JButton("Order");
    private JButton b6 = new JButton("Inapoi");

    private String[] listaCriterii = new String[] {"KeyWord", "Rating", "Number of Calories",
            "Proteins", "Fats", "Sodium", "Price"};
    private JComboBox c1 = new JComboBox(listaCriterii);
    private String[] listaOrder = new String[] {}; //""
    private JComboBox c2 = new JComboBox(listaOrder);
    private String[] listaCriteriiAplicate = new String[] {};
    private JComboBox c3 = new JComboBox(listaCriteriiAplicate);

    public ClientGUI(DeliveryService model)
    {
        this.model = model;

        l0.setFont(new Font("Times New Roman", Font.BOLD, 50));
        l1.setFont(new Font("Times New Roman", Font.BOLD, 30));
        l2.setFont(new Font("Times New Roman", Font.BOLD, 30));
        l3.setFont(new Font("Times New Roman", Font.BOLD, 30));
        l4.setFont(new Font("Times New Roman", Font.BOLD, 30));
        l5.setFont(new Font("Times New Roman", Font.BOLD, 30));
        l6.setFont(new Font("Times New Roman", Font.BOLD, 30));

        t1.setFont(new Font("Times New Roman", Font.BOLD, 20));
        b1.setFont(new Font("Times New Roman", Font.BOLD, 20));
        b2.setFont(new Font("Times New Roman", Font.BOLD, 20));
        b3.setFont(new Font("Times New Roman", Font.BOLD, 20));
        b4.setFont(new Font("Times New Roman", Font.BOLD, 20));
        b5.setFont(new Font("Times New Roman", Font.BOLD, 20));
        b6.setFont(new Font("Times New Roman", Font.BOLD, 20));
        c1.setFont(new Font("Times New Roman", Font.BOLD, 20));
        c2.setFont(new Font("Times New Roman", Font.BOLD, 20));
        c3.setFont(new Font("Times New Roman", Font.BOLD, 20));

        t1.setPreferredSize(new Dimension(50, 30));
        b1.setPreferredSize(new Dimension(150, 50));
        b2.setPreferredSize(new Dimension(150, 50));
        b3.setPreferredSize(new Dimension(150, 50));
        b4.setPreferredSize(new Dimension(150, 50));
        b5.setPreferredSize(new Dimension(150, 50));
        b6.setPreferredSize(new Dimension(150, 50));
        c1.setPreferredSize(new Dimension(200, 50));
        c2.setPreferredSize(new Dimension(700, 50));
        c3.setPreferredSize(new Dimension(400, 50));

        JPanel c = new JPanel();
        c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
        c.setPreferredSize(new Dimension(1500, 750));
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
        JPanel rand4 = new JPanel();
        rand4.setLayout(new FlowLayout());
        rand4.setBackground(Color.blue);
        JPanel rand5 = new JPanel();
        rand5.setLayout(new FlowLayout());
        rand5.setBackground(Color.blue);
        JPanel rand6 = new JPanel();
        rand6.setLayout(new FlowLayout());
        rand6.setBackground(Color.blue);

        rand1.add(l0);
        rand1.add(Box.createRigidArea(new Dimension(800, 0)));
        rand1.add(b6);
        rand1.add(Box.createRigidArea(new Dimension(0, 10)));

        rand2.add(l1);
        rand2.add(Box.createRigidArea(new Dimension(30, 0)));
        rand2.add(b1);

        rand3.add(l2);
        rand3.add(Box.createRigidArea(new Dimension(30, 0)));
        rand3.add(c1);
        rand3.add(Box.createRigidArea(new Dimension(30, 0)));
        rand3.add(l4);
        rand3.add(Box.createRigidArea(new Dimension(30, 0)));
        rand3.add(t1);
        rand3.add(Box.createRigidArea(new Dimension(30, 0)));
        rand3.add(b2);
        rand3.add(Box.createRigidArea(new Dimension(30, 0)));
        rand3.add(b3);

        rand5.add(l6);
        rand5.add(Box.createRigidArea(new Dimension(30, 0)));
        rand5.add(c3);

        rand4.add(l3);
        rand4.add(Box.createRigidArea(new Dimension(30, 0)));
        rand4.add(l5);
        rand4.add(Box.createRigidArea(new Dimension(30, 0)));
        rand4.add(c2);
        rand4.add(Box.createRigidArea(new Dimension(30, 0)));
        rand4.add(b4);
        rand4.add(Box.createRigidArea(new Dimension(30, 0)));
        rand4.add(b5);

        rand6.add(Box.createRigidArea(new Dimension(30, 0)));
        rand6.add(c2);

        c.add(rand1);
        c.add(Box.createRigidArea(new Dimension(0, 10)));
        c.add(rand2);
        c.add(Box.createRigidArea(new Dimension(0, 10)));
        c.add(rand3);
        c.add(Box.createRigidArea(new Dimension(0, 10)));
        c.add(rand5);
        c.add(Box.createRigidArea(new Dimension(0, 10)));
        c.add(rand4);
        c.add(Box.createRigidArea(new Dimension(0, 10)));
        c.add(rand6);
        c.add(Box.createRigidArea(new Dimension(0, 10)));

        this.setContentPane(c);
        this.pack();
        this.setTitle("Client chamber");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void butonView(ActionListener buton)
    {
        b1.addActionListener(buton);
    }
    public void butonAddCriteriu(ActionListener buton)
    {
        b2.addActionListener(buton);
    }
    public void butonSearch(ActionListener buton)
    {
        b3.addActionListener(buton);
    }
    public void butonAddProdus(ActionListener buton)
    {
        b4.addActionListener(buton);
    }
    public void butonOrder(ActionListener buton)
    {
        b5.addActionListener(buton);
    }
    public void butonInapoiClient(ActionListener buton)
    {
        b6.addActionListener(buton);
    }

    public String getCriteriuText() {
        return t1.getText();
    }
    public String getCriteriuCombo() {
        return String.valueOf(c1.getSelectedItem());
    }
    public String getProdusCombo() {
        return String.valueOf(c2.getSelectedItem());
    }

    public void setMenuItems(String[] listaAfisata, int max)
    {
        int min = -1;
        for(String s: listaAfisata)
        {
            if(min == max)
            {
                break;
            }
            c2.addItem(s);
            min++;
        }
    }

    public void resetMenuItems()
    {
        c2.removeAllItems();
    }

    public String getc2()
    {
        return c2.getSelectedItem().toString();
    }

    public void setMenuFiltrat(String[] listaAfisata, int max)
    {
        int min = -1;
        for(String s: listaAfisata)
        {
            if(min == max)
            {
                break;
            }
            c3.addItem(s);
            min++;
        }
    }

    public void resetMenuFiltrat()
    {
        c3.removeAllItems();
    }
    public void reset()
    {
        t1.setText("");
    }
}