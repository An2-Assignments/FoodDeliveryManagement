package presentationLayer;

import businessLayer.DeliveryService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Aceeasi idee ca la celelalte guiuri;
 * Despre butoanele din aceasta clasa vorbesc in controller;
 */
public class CreareCont extends JFrame
{
    private DeliveryService model;

    private JLabel l0 = new JLabel("Alege ce cont doresti sa faci:");
    private JLabel l1 = new JLabel("Introduce-ti datele dumneavoastra personale:");
    private JLabel l2 = new JLabel("Numele:");
    private JLabel l3 = new JLabel("Prenumele:");
    private JLabel l4 = new JLabel("Varsta:");
    private JLabel l5 = new JLabel("Parola:");

    private JTextField t1= new JTextField(10);
    private JTextField t2= new JTextField(10);
    private JTextField t3= new JTextField(10);
    private JTextField t4= new JTextField(10);

    private JButton b1 = new JButton("Creeare");
    private JButton b2 = new JButton("Inapoi");

    private String[] conturi = new String[] {"Administrator", "Employee", "Client"};
    private JComboBox c1 = new JComboBox(conturi);

    public CreareCont(DeliveryService model)
    {
        this.model = model;

        l0.setFont(new Font("Times New Roman", Font.BOLD, 40));
        l1.setFont(new Font("Times New Roman", Font.BOLD, 40));
        l2.setFont(new Font("Times New Roman", Font.BOLD, 30));
        l3.setFont(new Font("Times New Roman", Font.BOLD, 30));
        l4.setFont(new Font("Times New Roman", Font.BOLD, 30));
        l5.setFont(new Font("Times New Roman", Font.BOLD, 30));

        t1.setFont(new Font("Times New Roman", Font.BOLD, 30));
        t2.setFont(new Font("Times New Roman", Font.BOLD, 30));
        t3.setFont(new Font("Times New Roman", Font.BOLD, 30));
        t4.setFont(new Font("Times New Roman", Font.BOLD, 30));
        c1.setFont(new Font("Times New Roman", Font.BOLD, 30));
        b1.setFont(new Font("Times New Roman", Font.BOLD, 30));
        b2.setFont(new Font("Times New Roman", Font.BOLD, 30));

        t1.setPreferredSize(new Dimension(200, 60));
        t2.setPreferredSize(new Dimension(200, 60));
        t3.setPreferredSize(new Dimension(200, 60));
        t4.setPreferredSize(new Dimension(200, 60));
        c1.setPreferredSize(new Dimension(300, 50));
        b1.setPreferredSize(new Dimension(250, 70));
        b2.setPreferredSize(new Dimension(250, 70));

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
        JPanel rand4 = new JPanel();
        rand4.setLayout(new FlowLayout());
        rand4.setBackground(Color.blue);
        JPanel rand5 = new JPanel();
        rand5.setLayout(new FlowLayout());
        rand5.setBackground(Color.blue);
        JPanel rand6 = new JPanel();
        rand6.setLayout(new FlowLayout());
        rand6.setBackground(Color.blue);
        JPanel rand7 = new JPanel();
        rand7.setLayout(new FlowLayout());
        rand7.setBackground(Color.blue);

        rand1.add(l0);
        rand1.add(Box.createRigidArea(new Dimension(100, 0)));
        rand1.add(c1);

        rand2.add(l1);
        rand2.add(Box.createRigidArea(new Dimension(0, 10)));

        rand3.add(l2);
        rand3.add(Box.createRigidArea(new Dimension(100, 0)));
        rand3.add(t1);

        rand4.add(l3);
        rand4.add(Box.createRigidArea(new Dimension(100, 0)));
        rand4.add(t2);

        rand5.add(l4);
        rand5.add(Box.createRigidArea(new Dimension(100, 0)));
        rand5.add(t3);

        rand6.add(l5);
        rand6.add(Box.createRigidArea(new Dimension(100, 0)));
        rand6.add(t4);

        rand7.add(b1);
        rand7.add(Box.createRigidArea(new Dimension(100, 0)));
        rand7.add(b2);

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
        c.add(Box.createRigidArea(new Dimension(0, 15)));
        c.add(rand7);

        this.setContentPane(c);
        this.pack();
        this.setTitle("Creeare cont");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void butonCreareContOficial(ActionListener buton)
    {
        b1.addActionListener(buton);
    }
    public void butonInapoiPaginaPrincipala(ActionListener buton)
    {
        b2.addActionListener(buton);
    }

    public String getCont() {
        return c1.getSelectedItem().toString();
    }
    public String getNume() {
        return t1.getText();
    }
    public String getPrenume() {
        return t2.getText();
    }
    public String getVarsta() {
        return t3.getText();
    }
    public String getParola() {
        return t4.getText();
    }

    public void resetare()
    {
        t1.setText("");
        t2.setText("");
        t3.setText("");
        t4.setText("");
    }
}
