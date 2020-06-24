package view;

import javax.swing.*;
import java.awt.*;

public class StartPage extends JFrame {
    private JPanel buttonPanel;
    private JButton loginButton, registerButton;

    public StartPage() {
        initComponents();
        setSize(300, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {
        buttonPanel = new JPanel();
        LayoutManager layoutManager = new GridLayout(2, 1);
        buttonPanel.setLayout(layoutManager);

        loginButton = new JButton("Login");
        registerButton = new JButton("Register");

        loginButton.addActionListener(event -> {
            LoginPage loginPage = new LoginPage();
            loginPage.setVisible(true);
            dispose();
        });


        registerButton.addActionListener(event -> {
            RegisterPage registerPage = new RegisterPage();
            registerPage.setVisible(true);
            dispose();
        });

        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);
        add(buttonPanel);
    }
}
