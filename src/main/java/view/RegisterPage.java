package view;


import model.AuthenticationService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegisterPage extends JFrame {
    JPanel panel;
    JLabel usernameLabel, passwordLabel, passwordLabelConfirm, emailLabel;
    JTextField usernameField, emailField;
    JPasswordField passwordField, passwordFieldConfirm;
    JButton registerButton, resetButton;
    EmptyBorder border;

    public RegisterPage() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Register");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel(new GridLayout(5, 2));
        border = new EmptyBorder(0, 10, 0, 0);
        initUsername();
        initPassword();
        initPasswordConfirm();
        initEmail();
        initRegisterButton();
        initResetButton();
        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void initUsername() {
        usernameLabel = new JLabel("Username");
        usernameLabel.setBorder(border);
        panel.add(usernameLabel);
        usernameField = new JTextField();
        panel.add(usernameField);
    }

    private void initPassword() {
        passwordLabel = new JLabel("Enter Password");
        passwordLabel.setBorder(border);
        panel.add(passwordLabel);
        passwordField = new JPasswordField();
        panel.add(passwordField);
    }

    private void initPasswordConfirm() {
        passwordLabelConfirm = new JLabel("Confirm Password");
        passwordLabelConfirm.setBorder(border);
        panel.add(passwordLabelConfirm);
        passwordFieldConfirm = new JPasswordField();
        panel.add(passwordFieldConfirm);
    }

    private void initEmail() {
        emailLabel = new JLabel("Email");
        emailLabel.setBorder(border);
        panel.add(emailLabel);
        emailField = new JTextField();
        panel.add(emailField);
    }

    private void initRegisterButton() {
        registerButton = new JButton("Register");
        panel.add(registerButton, BorderLayout.SOUTH);
        registerButton.addActionListener(e -> {
            if (valid()) {
                AuthenticationService.registerUserName(usernameField.getText(), new String(passwordField.getPassword()));
                AuthenticationService.registerEmail(emailField.getText(), new String(passwordField.getPassword()));
                LoginPage loginPage = new LoginPage();
                loginPage.setVisible(true);
                showMessage("Registration Successful!", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        });
    }

    private void initResetButton() {
        resetButton = new JButton("Reset");
        panel.add(resetButton);
        resetButton.addActionListener(event -> {
            usernameField.setText("");
            passwordField.setText("");
            passwordFieldConfirm.setText("");
            emailField.setText("");
            showMessage("Fields Reset!", JOptionPane.INFORMATION_MESSAGE);
        });
    }


    private boolean valid() {
        Pattern patternEmail = Pattern.compile("^(.+)@(.+)$");
        Matcher matcherEmail = patternEmail.matcher(emailField.getText());
        if (usernameField.getText().equals("")) {
            showMessage("Please enter Username", JOptionPane.ERROR_MESSAGE);
            usernameField.requestFocus();
            return false;
        } else if (String.valueOf(passwordField.getPassword()).equals("")) {
            showMessage("Please enter Password", JOptionPane.ERROR_MESSAGE);
            passwordField.requestFocus();
            return false;
        } else if (String.valueOf(passwordFieldConfirm.getPassword()).equals("")) {
            showMessage("Please re-enter Password", JOptionPane.ERROR_MESSAGE);
            passwordFieldConfirm.requestFocus();
            return false;
        } else if (!String.valueOf(passwordFieldConfirm.getPassword()).equals(String.valueOf(passwordField.getPassword()))) {
            showMessage("Confirm Password doesn't match Password", JOptionPane.ERROR_MESSAGE);
            passwordFieldConfirm.requestFocus();
            return false;
        } else if (!matcherEmail.matches()) {
            showMessage("Please enter a valid Email", JOptionPane.ERROR_MESSAGE);
            emailField.requestFocus();
            return false;
        }
        return true;
    }

    public static void showMessage(String msg, int messageType) {
        JOptionPane.showMessageDialog(null, msg, "Error! Please check the message!", messageType);
    }
}