package presentationLayer;

import businessLayer.DeliveryService;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * Am toate text fieldurile, labelurile, combo boxurile, etc... pentru a putea
 * implementa operatiile cerute;
 * Nu voi explica aici ce fac butoanele, si toate labelurile si alte elemente de design sunt intelese de la sine;
 * Am set si reset pentru afisare, atunci cand dau pe buton de inapoi, sa nu ramana datele precedente;
 */
public class AdminGUI extends JFrame
{
    private DeliveryService model;

    private JLabel l0 = new JLabel("Pagina de administrator:");
    private JLabel l1 = new JLabel("1)Import la produse:");
    private JLabel l2 = new JLabel("2)Manage la produse:");
    private JLabel l3 = new JLabel("Adauga produs:");
    private JLabel l4 = new JLabel("Sterge produs:");
    private JLabel l5 = new JLabel("Modifiy produs:");
    private JLabel l6 = new JLabel("Creeaza produs nou:");
    private JLabel l7 = new JLabel("3)Raporturi despre orderuri:");
    private JLabel l8 = new JLabel("Toate orderurile intre ora de inceput:");
    private JLabel l9 = new JLabel("si ora de final:");
    private JLabel l10 = new JLabel("Toate orderurile facute de un nr > decat:");
    private JLabel l11 = new JLabel("Clientii ce au comandat de > decat:");
    private JLabel l12 = new JLabel(", si pretul orderurilor a fost > decat:");
    private JLabel l13 = new JLabel("Toate orderurile facute intr-o zi:");

    private JTextField t1= new JTextField("Nume", 10);
    private JTextField t2= new JTextField("Rating", 10);
    private JTextField t3= new JTextField("Calorii", 10);
    private JTextField t4= new JTextField("Proteine", 10);
    private JTextField t5= new JTextField("Grasimi", 10);
    private JTextField t6= new JTextField("Sodium", 10);
    private JTextField t7= new JTextField("Pret", 10);
    private JTextField t8= new JTextField("Nume", 10);
    private JTextField t11= new JTextField("Nume", 10);
    private JTextField t12= new JTextField("Rating", 10);
    private JTextField t13= new JTextField("Calorii", 10);
    private JTextField t14= new JTextField("Proteine", 10);
    private JTextField t15= new JTextField("Grasimi", 10);
    private JTextField t16= new JTextField("Sodium", 10);
    private JTextField t17= new JTextField("Pret", 10);
    private JTextField t18= new JTextField(10);
    private JTextField t19= new JTextField(10);
    private JTextField t20= new JTextField(10);
    private JTextField t21= new JTextField(10);
    private JTextField t22= new JTextField(10);
    private JTextField t23= new JTextField("An", 10);
    private JTextField t24= new JTextField("Luna", 10);
    private JTextField t25= new JTextField("Zi", 10);
    private JTextField t26= new JTextField("Nume", 10);

    private JButton b1 = new JButton("Import");
    private JButton b2 = new JButton("Adauga");
    private JButton b3 = new JButton("Sterge");
    private JButton b4 = new JButton("Modifica");
    private JButton b5 = new JButton("Creeaza");
    private JButton b6 = new JButton("Raport 1");
    private JButton b7 = new JButton("Raport 2");
    private JButton b8 = new JButton("Raport 3");
    private JButton b9 = new JButton("Raport 4");
    private JButton b10 = new JButton("Inapoi");
    private JButton b11 = new JButton("Adauga");

    private String[] listaBase = new String[] {};
    private JComboBox c1 = new JComboBox(listaBase);

    public AdminGUI(DeliveryService model)
    {
        this.model = model;

        l0.setFont(new Font("Times New Roman", Font.BOLD, 40));
        l1.setFont(new Font("Times New Roman", Font.BOLD, 20));
        l2.setFont(new Font("Times New Roman", Font.BOLD, 20));
        l3.setFont(new Font("Times New Roman", Font.BOLD, 20));
        l4.setFont(new Font("Times New Roman", Font.BOLD, 20));
        l5.setFont(new Font("Times New Roman", Font.BOLD, 20));
        l6.setFont(new Font("Times New Roman", Font.BOLD, 20));
        l7.setFont(new Font("Times New Roman", Font.BOLD, 20));
        l8.setFont(new Font("Times New Roman", Font.BOLD, 20));
        l9.setFont(new Font("Times New Roman", Font.BOLD, 20));
        l10.setFont(new Font("Times New Roman", Font.BOLD, 20));
        l11.setFont(new Font("Times New Roman", Font.BOLD, 20));
        l12.setFont(new Font("Times New Roman", Font.BOLD, 20));
        l13.setFont(new Font("Times New Roman", Font.BOLD, 20));

        t1.setFont(new Font("Times New Roman", Font.BOLD, 10));
        t2.setFont(new Font("Times New Roman", Font.BOLD, 10));
        t3.setFont(new Font("Times New Roman", Font.BOLD, 10));
        t4.setFont(new Font("Times New Roman", Font.BOLD, 10));
        t5.setFont(new Font("Times New Roman", Font.BOLD, 10));
        t6.setFont(new Font("Times New Roman", Font.BOLD, 10));
        t7.setFont(new Font("Times New Roman", Font.BOLD, 10));
        t8.setFont(new Font("Times New Roman", Font.BOLD, 10));
        t11.setFont(new Font("Times New Roman", Font.BOLD, 10));
        t12.setFont(new Font("Times New Roman", Font.BOLD, 10));
        t13.setFont(new Font("Times New Roman", Font.BOLD, 10));
        t14.setFont(new Font("Times New Roman", Font.BOLD, 10));
        t15.setFont(new Font("Times New Roman", Font.BOLD, 10));
        t16.setFont(new Font("Times New Roman", Font.BOLD, 10));
        t17.setFont(new Font("Times New Roman", Font.BOLD, 10));
        t18.setFont(new Font("Times New Roman", Font.BOLD, 10));
        t19.setFont(new Font("Times New Roman", Font.BOLD, 10));
        t20.setFont(new Font("Times New Roman", Font.BOLD, 10));
        t21.setFont(new Font("Times New Roman", Font.BOLD, 10));
        t22.setFont(new Font("Times New Roman", Font.BOLD, 10));
        t23.setFont(new Font("Times New Roman", Font.BOLD, 10));
        t24.setFont(new Font("Times New Roman", Font.BOLD, 10));
        t25.setFont(new Font("Times New Roman", Font.BOLD, 10));
        t26.setFont(new Font("Times New Roman", Font.BOLD, 10));

        b1.setFont(new Font("Times New Roman", Font.BOLD, 15));
        b2.setFont(new Font("Times New Roman", Font.BOLD, 15));
        b3.setFont(new Font("Times New Roman", Font.BOLD, 15));
        b4.setFont(new Font("Times New Roman", Font.BOLD, 15));
        b5.setFont(new Font("Times New Roman", Font.BOLD, 15));
        b6.setFont(new Font("Times New Roman", Font.BOLD, 15));
        b7.setFont(new Font("Times New Roman", Font.BOLD, 15));
        b8.setFont(new Font("Times New Roman", Font.BOLD, 15));
        b9.setFont(new Font("Times New Roman", Font.BOLD, 15));
        b10.setFont(new Font("Times New Roman", Font.BOLD, 15));
        b11.setFont(new Font("Times New Roman", Font.BOLD, 15));

        c1.setFont(new Font("Times New Roman", Font.BOLD, 15));

        t1.setPreferredSize(new Dimension(100, 30));
        t2.setPreferredSize(new Dimension(100, 30));
        t3.setPreferredSize(new Dimension(100, 30));
        t4.setPreferredSize(new Dimension(100, 30));
        t5.setPreferredSize(new Dimension(100, 30));
        t6.setPreferredSize(new Dimension(100, 30));
        t7.setPreferredSize(new Dimension(100, 30));
        t8.setPreferredSize(new Dimension(100, 30));
        t11.setPreferredSize(new Dimension(100, 30));
        t12.setPreferredSize(new Dimension(100, 30));
        t13.setPreferredSize(new Dimension(100, 30));
        t14.setPreferredSize(new Dimension(100, 30));
        t15.setPreferredSize(new Dimension(100, 30));
        t16.setPreferredSize(new Dimension(100, 30));
        t17.setPreferredSize(new Dimension(100, 30));
        t18.setPreferredSize(new Dimension(100, 30));
        t19.setPreferredSize(new Dimension(100, 30));
        t20.setPreferredSize(new Dimension(100, 30));
        t21.setPreferredSize(new Dimension(100, 30));
        t22.setPreferredSize(new Dimension(100, 30));
        t23.setPreferredSize(new Dimension(100, 30));
        t24.setPreferredSize(new Dimension(100, 30));
        t25.setPreferredSize(new Dimension(100, 30));
        t26.setPreferredSize(new Dimension(100, 30));

        b1.setPreferredSize(new Dimension(100, 40));
        b2.setPreferredSize(new Dimension(100, 40));
        b3.setPreferredSize(new Dimension(100, 40));
        b4.setPreferredSize(new Dimension(100, 40));
        b5.setPreferredSize(new Dimension(100, 40));
        b6.setPreferredSize(new Dimension(100, 40));
        b7.setPreferredSize(new Dimension(100, 40));
        b8.setPreferredSize(new Dimension(100, 40));
        b9.setPreferredSize(new Dimension(100, 40));
        b10.setPreferredSize(new Dimension(100, 40));
        b11.setPreferredSize(new Dimension(100, 40));

        c1.setPreferredSize(new Dimension(700, 40));

        JPanel c = new JPanel();
        c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
        c.setPreferredSize(new Dimension(1000, 750));
        c.setBackground(Color.red);

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
        JPanel rand8 = new JPanel();
        rand8.setLayout(new FlowLayout());
        rand8.setBackground(Color.blue);
        JPanel rand9 = new JPanel();
        rand9.setLayout(new FlowLayout());
        rand9.setBackground(Color.blue);
        JPanel rand10 = new JPanel();
        rand10.setLayout(new FlowLayout());
        rand10.setBackground(Color.blue);
        JPanel rand11 = new JPanel();
        rand11.setLayout(new FlowLayout());
        rand11.setBackground(Color.blue);
        JPanel rand12 = new JPanel();
        rand12.setLayout(new FlowLayout());
        rand12.setBackground(Color.blue);
        JPanel rand13 = new JPanel();
        rand13.setLayout(new FlowLayout());
        rand13.setBackground(Color.blue);
        JPanel rand14 = new JPanel();
        rand14.setLayout(new FlowLayout());
        rand14.setBackground(Color.blue);

        rand2.add(l0);
        rand2.add(Box.createRigidArea(new Dimension(360, 0)));
        rand2.add(b10);
        rand2.add(Box.createRigidArea(new Dimension(0, 20)));

        rand3.add(l1);
        rand3.add(Box.createRigidArea(new Dimension(10, 0)));
        rand3.add(b1);

        rand4.add(l2);
        rand4.add(Box.createRigidArea(new Dimension(100, 0)));

        rand5.add(l3);
        rand5.add(Box.createRigidArea(new Dimension(10, 0)));
        rand5.add(t1);
        rand5.add(Box.createRigidArea(new Dimension(10, 0)));
        rand5.add(t2);
        rand5.add(Box.createRigidArea(new Dimension(10, 0)));
        rand5.add(t3);
        rand5.add(Box.createRigidArea(new Dimension(10, 0)));
        rand5.add(t4);
        rand5.add(Box.createRigidArea(new Dimension(10, 0)));
        rand5.add(t5);
        rand5.add(Box.createRigidArea(new Dimension(10, 0)));
        rand5.add(t6);
        rand5.add(Box.createRigidArea(new Dimension(10, 0)));
        rand5.add(t7);
        rand5.add(Box.createRigidArea(new Dimension(10, 0)));
        rand5.add(b2);

        rand6.add(l4);
        rand6.add(Box.createRigidArea(new Dimension(10, 0)));
        rand6.add(t8);
        rand6.add(Box.createRigidArea(new Dimension(100, 0)));
        rand6.add(b3);

        rand7.add(l5);
        rand7.add(Box.createRigidArea(new Dimension(10, 0)));
        rand7.add(t11);
        rand7.add(Box.createRigidArea(new Dimension(10, 0)));
        rand7.add(t12);
        rand7.add(Box.createRigidArea(new Dimension(10, 0)));
        rand7.add(t13);
        rand7.add(Box.createRigidArea(new Dimension(10, 0)));
        rand7.add(t14);
        rand7.add(Box.createRigidArea(new Dimension(10, 0)));
        rand7.add(t15);
        rand7.add(Box.createRigidArea(new Dimension(10, 0)));
        rand7.add(t16);
        rand7.add(Box.createRigidArea(new Dimension(10, 0)));
        rand7.add(t17);
        rand7.add(Box.createRigidArea(new Dimension(10, 0)));
        rand7.add(b4);

        rand8.add(l6);
        rand8.add(Box.createRigidArea(new Dimension(10, 0)));
        rand8.add(t26);
        rand8.add(Box.createRigidArea(new Dimension(10, 0)));
        rand8.add(b11);
        rand8.add(Box.createRigidArea(new Dimension(10, 0)));
        rand8.add(b5);

        rand14.add(Box.createRigidArea(new Dimension(10, 0)));
        rand14.add(c1);

        rand9.add(l7);
        rand9.add(Box.createRigidArea(new Dimension(0, 0)));

        rand10.add(l8);
        rand10.add(Box.createRigidArea(new Dimension(10, 0)));
        rand10.add(t18);
        rand10.add(Box.createRigidArea(new Dimension(10, 0)));
        rand10.add(l9);
        rand10.add(Box.createRigidArea(new Dimension(10, 0)));
        rand10.add(t19);
        rand10.add(Box.createRigidArea(new Dimension(50, 0)));
        rand10.add(b6);

        rand11.add(l10);
        rand11.add(Box.createRigidArea(new Dimension(10, 0)));
        rand11.add(t20);
        rand11.add(Box.createRigidArea(new Dimension(50, 0)));
        rand11.add(b7);

        rand12.add(l11);
        rand12.add(Box.createRigidArea(new Dimension(10, 0)));
        rand12.add(t21);
        rand12.add(Box.createRigidArea(new Dimension(10, 0)));
        rand12.add(l12);
        rand12.add(Box.createRigidArea(new Dimension(10, 0)));
        rand12.add(t22);
        rand12.add(Box.createRigidArea(new Dimension(50, 0)));
        rand12.add(b8);

        rand13.add(l13);
        rand13.add(Box.createRigidArea(new Dimension(10, 0)));
        rand13.add(t23);
        rand13.add(Box.createRigidArea(new Dimension(10, 0)));
        rand13.add(t24);
        rand13.add(Box.createRigidArea(new Dimension(10, 0)));
        rand13.add(t25);
        rand13.add(Box.createRigidArea(new Dimension(50, 0)));
        rand13.add(b9);

        c.add(rand1);
        c.add(Box.createRigidArea(new Dimension(0, 3)));
        c.add(rand2);
        c.add(Box.createRigidArea(new Dimension(0, 3)));
        c.add(rand3);
        c.add(Box.createRigidArea(new Dimension(0, 3)));
        c.add(rand4);
        c.add(Box.createRigidArea(new Dimension(0, 3)));
        c.add(rand5);
        c.add(Box.createRigidArea(new Dimension(0, 3)));
        c.add(rand6);
        c.add(Box.createRigidArea(new Dimension(0, 3)));
        c.add(rand7);
        c.add(Box.createRigidArea(new Dimension(0, 3)));
        c.add(rand8);
        c.add(Box.createRigidArea(new Dimension(0, 3)));
        c.add(rand14);
        c.add(Box.createRigidArea(new Dimension(0, 3)));
        c.add(rand9);
        c.add(Box.createRigidArea(new Dimension(0, 3)));
        c.add(rand10);
        c.add(Box.createRigidArea(new Dimension(0, 3)));
        c.add(rand11);
        c.add(Box.createRigidArea(new Dimension(0, 3)));
        c.add(rand12);
        c.add(Box.createRigidArea(new Dimension(0, 3)));
        c.add(rand13);

        this.setContentPane(c);
        this.pack();
        this.setTitle("Administrator chamber");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void butonImport(ActionListener buton)
    {
        b1.addActionListener(buton);
    }
    public void butonAdd(ActionListener buton)
    {
        b2.addActionListener(buton);
    }
    public void butonDelete(ActionListener buton)
    {
        b3.addActionListener(buton);
    }
    public void butonModify(ActionListener buton)
    {
        b4.addActionListener(buton);
    }
    public void butonAddNew(ActionListener buton)
    {
        b5.addActionListener(buton);
    }
    public void butonRaport1(ActionListener buton)
    {
        b6.addActionListener(buton);
    }
    public void butonRaport2(ActionListener buton)
    {
        b7.addActionListener(buton);
    }
    public void butonRaport3(ActionListener buton)
    {
        b8.addActionListener(buton);
    }
    public void butonRaport4(ActionListener buton)
    {
        b9.addActionListener(buton);
    }
    public void butonInapoiAdmin(ActionListener buton)
    {
        b10.addActionListener(buton);
    }
    public void butonAddBase(ActionListener buton)
    {
        b11.addActionListener(buton);
    }

    public String getNume1() {
        return t1.getText();
    }
    public String getRating1() {
        return t2.getText();
    }
    public String getCalorii1() {
        return t3.getText();
    }
    public String getProteine1() {
        return t4.getText();
    }
    public String getGrasimi1() {
        return t5.getText();
    }
    public String getSodium1() {
        return t6.getText();
    }
    public String getPret1() {
        return t7.getText();
    }
    public String getNume3() {
        return t8.getText();
    }
    public String getNume2() {
        return t11.getText();
    }
    public String getRating2() {
        return t12.getText();
    }
    public String getCalorii2() {
        return t13.getText();
    }
    public String getProteine2() {
        return t14.getText();
    }
    public String getGrasimi2() {
        return t15.getText();
    }
    public String getSodium2() {
        return t16.getText();
    }
    public String getPret2() {
        return t17.getText();
    }
    public String getRaport11() {
        return t18.getText();
    }
    public String getRaport12() {
        return t19.getText();
    }
    public String getRaport2() {
        return t20.getText();
    }
    public String getRaport31() {
        return t21.getText();
    }
    public String getRaport32() {
        return t22.getText();
    }
    public String getRaport41() {
        return t23.getText();
    }
    public String getRaport42() {
        return t24.getText();
    }
    public String getRaport43() {
        return t25.getText();
    }
    public String getNumeBase() {
        return t26.getText();
    }

    public void reset()
    {
        t1.setText("");
        t2.setText("");
        t3.setText("");
        t4.setText("");
        t5.setText("");
        t6.setText("");
        t7.setText("");
        t8.setText("");
        t11.setText("");
        t12.setText("");
        t13.setText("");
        t14.setText("");
        t15.setText("");
        t16.setText("");
        t17.setText("");
        t18.setText("");
        t19.setText("");
        t20.setText("");
        t21.setText("");
        t22.setText("");
        t23.setText("");
        t24.setText("");
        t25.setText("");
        t26.setText("");
    }

    public void setMenuItems(String[] listaAfisata, int max)
    {
        int min = 0;
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

    public void resetMenuItems()
    {
        c1.removeAllItems();
    }

    public String getc1()
    {
        return c1.getSelectedItem().toString();
    }

    public void set()
    {
        t1.setText("Nume");
        t2.setText("Rating");
        t3.setText("Calorii");
        t4.setText("Proteine");
        t5.setText("Grasimi");
        t6.setText("Sodium");
        t7.setText("Pret");
        t8.setText("Nume");
        t11.setText("Nume");
        t12.setText("Rating");
        t13.setText("Calorii");
        t14.setText("Proteine");
        t15.setText("Grasimi");
        t16.setText("Sodium");
        t17.setText("Pret");
        t23.setText("An");
        t24.setText("Luna");
        t25.setText("Zi");
        t26.setText("Nume");
    }
}