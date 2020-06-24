package view;


import dao.PersoanaDao;
import model.AuthenticationService;
import model.Persoana;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LoginPage extends JFrame {


    private JMenuItem homeClick, accountClick, logOutClick;
    private JMenuBar menuBar;
    private JMenu home;
    private JMenu account;
    private JMenu logOut;

    private JPanel panel;
    private PersoanaDao persoanaDao;
    private JButton loginButton;
    private JButton registerButton;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel usernameLabel;
    private JLabel passwordLabel;

    public LoginPage() {
        super("Login Autentification");
        setSize(500, 300);
        setLocationRelativeTo(null);
        EmptyBorder border = new EmptyBorder(0, 10, 0, 0);
        usernameLabel = new JLabel("User Name:");
        usernameLabel.setBorder(border);
        passwordLabel = new JLabel("Password:");
        passwordLabel.setBorder(border);
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");
        panel = new JPanel(new GridLayout(3, 1));
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(registerButton);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(panel, BorderLayout.CENTER);
        setVisible(true);

        loginButton.addActionListener(e -> {
            if (AuthenticationService.login(usernameField.getText(), new String(passwordField.getPassword()))) {
                persoanaDao.create(new Persoana(usernameField.getText(), "", new String(passwordField.getPassword())));
                MainPage mainPage = new MainPage();
                mainPage.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Credentiale incorecte!");
                usernameField.setText("");
                passwordField.setText("");
                usernameField.requestFocus();
            }
        });

        registerButton.addActionListener(e -> {
            RegisterPage registerPage = new RegisterPage();
            registerPage.setVisible(true);
            dispose();
        });

        initMenuBar();
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
        logOutClick.addActionListener(e -> {
            LoginPage loginPage = new LoginPage();
            loginPage.setVisible(true);
            dispose();
        });
        setJMenuBar(menuBar);
    }
}
