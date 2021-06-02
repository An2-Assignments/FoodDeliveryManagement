package presentationLayer;

import businessLayer.DeliveryService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Aici ma uit la produsele existente in DeliveryService;
 * Toate produsele adaugate de admin, pot sa fie vazute de catre client cu acest gui;
 */
public class ProduseClient extends JFrame
{
    private DeliveryService model;

    private JLabel l1 = new JLabel("Produsele existente:");
    private JButton b1 = new JButton("Inapoi");
    private String[] listaProduse = new String[]{};
    private JComboBox c1 = new JComboBox(listaProduse);

    public ProduseClient(DeliveryService model)
    {
        this.model = model;

        l1.setFont(new Font("Times New Roman", Font.BOLD, 60));
        c1.setFont(new Font("Times New Roman", Font.BOLD, 20));
        c1.setPreferredSize(new Dimension(1000, 60));
        b1.setFont(new Font("Times New Roman", Font.BOLD, 30));
        b1.setPreferredSize(new Dimension(200, 60));

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

        rand1.add(l1);
        rand1.add(Box.createRigidArea(new Dimension(0, 50)));
        rand2.add(c1);
        rand2.add(Box.createRigidArea(new Dimension(0, 50)));
        rand3.add(b1);
        rand3.add(Box.createRigidArea(new Dimension(0, 50)));

        c.add(rand1);
        c.add(Box.createRigidArea(new Dimension(0, 30)));
        c.add(rand2);
        c.add(Box.createRigidArea(new Dimension(0, 30)));
        c.add(rand3);
        c.add(Box.createRigidArea(new Dimension(0, 30)));

        this.setContentPane(c);
        this.pack();
        this.setTitle("Produse Finale");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void butonInapoiProduse(ActionListener buton)
    {
        b1.addActionListener(buton);
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
            c1.addItem(s);
            min++;
        }
    }

    /**
     * Pentru resetare la combobox, cand este nevoie;
     */
    public void resetMenuItems()
    {
        c1.removeAllItems();
    }
}
