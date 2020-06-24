package view;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewPassword extends JFrame {
    private JPanel panel;
    private JPasswordField password, confirmPassword;
    private JLabel passwordLabel, confirmPasswordLabel;
    private JButton success, fail;

    public NewPassword() {
        super("New Password");
        setSize(300, 300);
        panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));
        passwordLabel = new JLabel("Password");
        password = new JPasswordField();
        confirmPasswordLabel = new JLabel("Confirm Password");
        confirmPassword = new JPasswordField();
        success = new JButton("Change");
        success.setBackground(Color.GREEN);
        success.addActionListener(e -> {
            if (password.getPassword() != null && String.valueOf(confirmPassword.getPassword()).equals(String.valueOf(password.getPassword()))) {
                if (valid()) {
                    LoginPage loginPage = new LoginPage();
                    loginPage.setVisible(true);
                    dispose();
                } else {
                    showMessage("password - câmpul trebuie să fie completat cu o parolă care să aibă minim 6 caractere, minim o cifră, o literă mică și o literă mare"
                            , JOptionPane.ERROR_MESSAGE);
                }
            } else {
                showMessage("Nu ai introdus parola/ nu sunt la fel"
                        , JOptionPane.ERROR_MESSAGE);
                password.setText("");
                confirmPassword.setText("");
                password.requestFocus();
            }
        });
        fail = new JButton("Cancel");
        fail.setBackground(Color.RED);
        fail.addActionListener(e -> {
            MyAccount myAccount = new MyAccount();
            myAccount.setVisible(true);
            dispose();
        });
        panel.add(passwordLabel);
        panel.add(password);
        panel.add(confirmPasswordLabel);
        panel.add(confirmPassword);
        panel.add(success);
        panel.add(fail);
        add(panel);


        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void showMessage(String msg, int messageType) {
        JOptionPane.showMessageDialog(null, msg, "Error! Please check the message!", messageType);
    }

    private boolean valid() {
        Pattern patternPassword = Pattern.compile("^.*(?=.{6,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z]).*$");
        Matcher matcherPassword = patternPassword.matcher(password.getText());
        return matcherPassword.matches();
    }

}
