package view;


import model.MenuBarAplicatie;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainPage extends JFrame {

    LocalDateTime date = LocalDateTime.now();
    private JLabel label;

    JMenuItem homeClick, accountClick, logOutClick;
    JMenuBar menuBar;
    JMenu home;
    JMenu account;
    JMenu logOut;

    JPanel panel = new JPanel(new GridLayout(1, 7));


    JPanel panelPrincipal = new JPanel(new BorderLayout());
    JPanel panoulCentral = new JPanel(new GridLayout(2, 1));
    JButton butonRosu;
    MenuBarAplicatie menuBarAplicatie = new MenuBarAplicatie();
    static int i = 0;
    static int j = 0;
    static List<List<String>> list = new ArrayList<>();
    JTextField getSursa, getDestinatie, getOraPlecare, getOraSosire, getZile, getPret;
    JButton adaugaZbor;
    MenuBarAplicatie m = new MenuBarAplicatie();


    public MainPage() {
        initComponents();


    }


    private void initComponents() {
        setTitle("Aplicatie principala");
        setSize(700, 700);
        m.initMenuBar();
        JLabel dateText = new JLabel(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        panelPrincipal.add(dateText, BorderLayout.NORTH);

        initMenuBar();
        initCapTabel();
        if (j != 0) {
            initPanouCentral(j);
        }
        panelPrincipal.add(panoulCentral, BorderLayout.CENTER);
        initButonAdaugaZbor();
        add(panelPrincipal, BorderLayout.CENTER);
        setLocationRelativeTo(null);
        setVisible(true);


    }

    private void initButonAdaugaZbor() {
        adaugaZbor = new JButton("Adauga zbor");
        panelPrincipal.add(adaugaZbor, BorderLayout.SOUTH);
        adaugaZbor.addActionListener(e -> {
            FlyDetailsPage flyDetailsPage = new FlyDetailsPage();
            flyDetailsPage.setVisible(true);
            dispose();
            j++;
        });


    }


    private void initCapTabel() {
        JPanel panouCapTabel = new JPanel(new GridLayout(1, 7));
        panouCapTabel.add(new JButton());
        panouCapTabel.add(new JTextField("Sursa"));
        panouCapTabel.add(new JTextField("Destinatie"));
        panouCapTabel.add(new JTextField("Ora Plecare"));
        panouCapTabel.add(new JTextField("Ora Sosire"));
        panouCapTabel.add(new JTextField("Zile"));
        panouCapTabel.add(new JTextField("S"));
        panoulCentral.add(panouCapTabel);
    }

    private void initPanouCentral(int j) {

        butonRosu = new JButton();
        butonRosu.setBackground(Color.RED);
        butonRosu.addActionListener(e -> {
            String text = label.getText();
            int count = Integer.parseInt(text);
            count++;
            label.setText(String.valueOf(count));

            //P4
            if (count == 10) {
                JOptionPane.showMessageDialog(null, "Felicitari!", "Mesaj", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }


        });
        panel.add(butonRosu);
        panel.add(new JTextField(list.get(0).get(0)));
        panel.add(new JTextField(list.get(0).get(1)));
        panel.add(new JTextField(list.get(0).get(2)));
        panel.add(new JTextField(list.get(0).get(3)));
        panel.add(new JTextField(list.get(0).get(4)));
        panel.add(new JTextField(list.get(0).get(5)));

        panoulCentral.add(panel);
    }

    private void initMenuBar() {
        menuBar = new JMenuBar();
        home = new JMenu("Home");
        homeClick = new JMenuItem("Home");
        home.add(homeClick);
        account = new JMenu("Account");
        accountClick = new JMenuItem("Account");
        account.add(accountClick);
        logOut = new JMenu("Log Out");
        logOutClick = new JMenuItem("Log Out");
        logOut.add(logOutClick);
        menuBar.add(home);
        menuBar.add(account);
        menuBar.add(logOut);
        homeClick.addActionListener(e -> {
            MainPage mainPage = new MainPage();
            mainPage.setVisible(true);
            dispose();

        });
        account.addActionListener(e -> {
            MyAccount myAccount = new MyAccount();
            myAccount.setVisible(true);
            dispose();
        });
        logOutClick.addActionListener(e -> {
            LoginPage loginPage = new LoginPage();
            loginPage.setVisible(true);
            dispose();
        });
        setJMenuBar(menuBar);
    }


    public static void registerFly(String sursa, String destinatie, String oraPlecare, String oraSosire, String zile, String pret) {
        list.add(i, Arrays.asList(sursa, destinatie, oraPlecare, oraPlecare, zile, pret));
    }
}
