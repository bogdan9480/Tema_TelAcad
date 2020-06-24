package model;


import view.LoginPage;
import view.MainPage;

import javax.swing.*;

public class MenuBarAplicatie extends JFrame {

    JMenuBar menuBar;
    JMenu home;
    JMenu account;
    JMenu logOut;
    JMenuItem homeClick, accountClick, logOutClick;


    public MenuBarAplicatie() {
        initMenuBar();
    }

    public void initMenuBar() {
        menuBar = new JMenuBar();
        home = new JMenu("Home");
        homeClick = new JMenuItem("Home");
        home.add(homeClick);
        account = new JMenu("Account");        accountClick = new JMenuItem("Account");
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
        logOutClick.addActionListener(e -> {
            LoginPage loginPage = new LoginPage();
            loginPage.setVisible(true);
            dispose();
        });
        setJMenuBar(menuBar);
    }
}
