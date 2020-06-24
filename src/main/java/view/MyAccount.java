package view;

import controller.DatabaseConnection;
import dao.PersoanaDao;
import model.Persoana;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyAccount extends JFrame {

    JTextField user, userName, email, emailUser, newEmail, newUsername;
    JButton changeUsername, changeEmail, changePassword;
    JPanel panelPrincipal = new JPanel(new BorderLayout());
    JPanel panel;
    PersoanaDao persoanaDao = new PersoanaDao(DatabaseConnection.getConnection());

    public MyAccount() {
        initComp();
    }


    private void initComp() {
        setSize(600, 600);
        setTitle("MY Account");
        user = new JTextField("User");
        userName = new JTextField(persoanaDao.findByUsername("userName").get().getUsername());
        email = new JTextField("Email");
        emailUser = new JTextField(persoanaDao.findByUsername("email").get().getUsername());
        newEmail = new JTextField("New email");
        newUsername = new JTextField("New user name");
        changeUsername = new JButton("CHANGE USERNAME");
        changeUsername.setBackground(Color.RED);
        changeUsername.addActionListener(e -> {
            if (valid("name")) {
                persoanaDao.updateName(newUsername.getText());
                showMessage("Succes", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        changeEmail = new JButton("CHANGE EMAIL");
        changeEmail.setBackground(Color.RED);
        changeEmail.addActionListener(e -> {
            if (valid("email")) {
                persoanaDao.updateEmail(newEmail.getText());
                showMessage("Succes", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        changePassword = new JButton("CHANGE PASSWORD");
        changePassword.setBackground(Color.RED);
        changePassword.addActionListener(e -> {

        });

        panel = new JPanel(new GridLayout(5, 2));
        panel.add(user);
        panel.add(userName);
        panel.add(email);
        panel.add(emailUser);
        panel.add(newUsername);
        panel.add(newEmail);
        panel.add(changeUsername);
        panel.add(changeEmail);
        panel.add(changePassword);


        panelPrincipal.add(panel, BorderLayout.CENTER);
        add(panelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }


    private boolean valid(String change) {
        Pattern patternEmail = Pattern.compile("^(.+)@(.+)$");
        Matcher matcherEmail = patternEmail.matcher(newEmail.getText());
        switch (change) {
            case "name":
                if (newUsername.getText().isEmpty()) {
                    showMessage("Please enter NewUserNameValid", JOptionPane.ERROR_MESSAGE);
                    newUsername.requestFocus();
                    return false;

                } else if (newUsername.getText().equals(persoanaDao.findByUsername("userName").get().getUsername())) {
                    showMessage("UserName already exist", JOptionPane.ERROR_MESSAGE);
                    newUsername.requestFocus();
                    return false;
                }
                return true;
            case "email":
                if (newEmail.getText().isEmpty()) {
                    showMessage("Please enter NewEmailValid", JOptionPane.ERROR_MESSAGE);
                    newEmail.requestFocus();
                    return false;
                } else if (newEmail.getText().equals(persoanaDao.findByUsername("email").get().getUsername())) {
                    showMessage("NewEmail already exist", JOptionPane.ERROR_MESSAGE);
                    newEmail.requestFocus();
                    return false;
                }
                return true;
            default:
                showMessage("Error", JOptionPane.ERROR_MESSAGE);
                break;
        }
        return false;
    }

    public static void showMessage(String msg, int messageType) {
        JOptionPane.showMessageDialog(null, msg, "Error! Please check the message!", messageType);
    }
}
