package presentationLayer;

import businessLayer.DeliveryService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Aceeasi idee ca la celelalte guiuri;
 * Despre butoanele din aceasta clasa vorbesc in controller;
 */
public class PaginaPrincipala extends JFrame
{
    private DeliveryService model;

    private JLabel l0 = new JLabel("Food online:");
    private JLabel l1 = new JLabel("Pagina pentru logare:");
    private JLabel l2 = new JLabel("Introduce-ti numele si parola:");
    private JLabel l3 = new JLabel("Numele:");
    private JLabel l4 = new JLabel("Parola:");
    private JLabel l5 = new JLabel("Daca nu aveti cont, apasati aici:");

    private JTextField t1= new JTextField(10);
    private JTextField t2= new JTextField(10);

    private JButton b1 = new JButton("Logare");
    private JButton b2 = new JButton("Creeare cont");

    public PaginaPrincipala(DeliveryService model)
    {
        this.model = model;

        l0.setFont(new Font("Times New Roman", Font.BOLD, 50));
        l1.setFont(new Font("Times New Roman", Font.BOLD, 40));
        l2.setFont(new Font("Times New Roman", Font.BOLD, 30));
        l3.setFont(new Font("Times New Roman", Font.BOLD, 30));
        l4.setFont(new Font("Times New Roman", Font.BOLD, 30));
        l5.setFont(new Font("Times New Roman", Font.BOLD, 30));

        t1.setFont(new Font("Times New Roman", Font.BOLD, 30));
        t2.setFont(new Font("Times New Roman", Font.BOLD, 30));
        b1.setFont(new Font("Times New Roman", Font.BOLD, 30));
        b2.setFont(new Font("Times New Roman", Font.BOLD, 30));
        t1.setPreferredSize(new Dimension(200, 60));
        t2.setPreferredSize(new Dimension(200, 60));
        b1.setPreferredSize(new Dimension(200, 60));
        b2.setPreferredSize(new Dimension(300, 60));

        JPanel c = new JPanel();
        c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
        c.setPreferredSize(new Dimension(1000, 750));
        c.setBackground(Color.blue);

        JPanel rand1 = new JPanel();
        rand1.setLayout(new FlowLayout());
        rand1.setBackground(Color.green);
        JPanel rand2 = new JPanel();
        rand2.setLayout(new FlowLayout());
        rand2.setBackground(Color.green);
        JPanel rand3 = new JPanel();
        rand3.setLayout(new FlowLayout());
        rand3.setBackground(Color.green);
        JPanel rand4 = new JPanel();
        rand4.setLayout(new FlowLayout());
        rand4.setBackground(Color.green);
        JPanel rand5 = new JPanel();
        rand5.setLayout(new FlowLayout());
        rand5.setBackground(Color.green);
        JPanel rand6 = new JPanel();
        rand6.setLayout(new FlowLayout());
        rand6.setBackground(Color.green);

        rand1.add(l0);
        rand1.add(Box.createRigidArea(new Dimension(0, 30)));

        rand2.add(l1);
        rand2.add(Box.createRigidArea(new Dimension(0, 10)));

        rand3.add(l2);
        rand3.add(Box.createRigidArea(new Dimension(100, 0)));
        rand3.add(b1);

        rand4.add(l3);
        rand4.add(Box.createRigidArea(new Dimension(100, 0)));
        rand4.add(t1);

        rand5.add(l4);
        rand5.add(Box.createRigidArea(new Dimension(100, 0)));
        rand5.add(t2);

        rand6.add(l5);
        rand6.add(Box.createRigidArea(new Dimension(100, 0)));
        rand6.add(b2);

        c.add(rand1);
        c.add(Box.createRigidArea(new Dimension(0, 15)));
        c.add(rand2);
        c.add(Box.createRigidArea(new Dimension(0, 15)));
        c.add(rand3);
        c.add(Box.createRigidArea(new Dimension(0, 15)));
        c.add(rand4);
        c.add(Box.createRigidArea(new Dimension(0, 15)));
        c.add(rand5);
        c.add(Box.createRigidArea(new Dimension(0, 15)));
        c.add(rand6);

        this.setContentPane(c);
        this.pack();
        this.setTitle("Pagina principala");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void butonLogare(ActionListener buton)
    {
        b1.addActionListener(buton);
    }
    public void butonCreeareCont(ActionListener buton)
    {
        b2.addActionListener(buton);
    }

    public String getNume()
    {
        return t1.getText();
    }
    public String getParola()
    {
        return t2.getText();
    }

    public void resetare()
    {
        t1.setText("");
        t2.setText("");
    }
}