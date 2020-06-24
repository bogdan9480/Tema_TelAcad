package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FlyDetailsPage extends JFrame {
    JPanel panelTitlu, panelDate, panelPrincipal, panelButoane, panel;
    EmptyBorder border;
    JTextField sursaZbor, destinatieZbor, oraPlecare, oraSosire, durata, pret;
    JLabel sursaZborLabel, destinatieZborLabel, oraPlecareLabel, oraSosireLabel, durataLabel, pretLabel, zile;
    JCheckBox day1, day2, day3, day4, day5, day6, day7;
    JButton anulare, adauga;


    public FlyDetailsPage() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Register Fly");
        setSize(1000, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panelPrincipal = new JPanel(new BorderLayout());
        panelButoane = new JPanel(new GridLayout(1, 2));
        panelTitlu = new JPanel(new GridLayout(1, 6));
        panelDate = new JPanel(new GridLayout(1, 6));
        panel = new JPanel(new GridLayout(2, 1));
        border = new EmptyBorder(0, 10, 0, 0);
        initZbor();
        initDestinatie();
        initOraPlecare();
        initOraSosire();
        initDurata();
        initDay();
        initPret();
        initAdauga();
        initAnuleaza();
        panelPrincipal.add(panelButoane, BorderLayout.SOUTH);
        panel.add(panelTitlu);
        panel.add(panelDate);
        panelPrincipal.add(panel, BorderLayout.CENTER);
        add(panelPrincipal, BorderLayout.CENTER);
        setVisible(true);
    }

    private void initZbor() {
        sursaZborLabel = new JLabel("Zbor:");
        sursaZborLabel.setBorder(border);
        panelTitlu.add(sursaZborLabel);
        sursaZbor = new JTextField();
        panelDate.add(sursaZbor);
    }

    private void initDestinatie() {
        destinatieZborLabel = new JLabel("Destinatie:");
        destinatieZborLabel.setBorder(border);
        panelTitlu.add(destinatieZborLabel);
        destinatieZbor = new JTextField();
        panelDate.add(destinatieZbor);
    }

    private void initOraPlecare() {
        oraPlecareLabel = new JLabel("Ora plecare:");
        oraPlecareLabel.setBorder(border);
        panelTitlu.add(oraPlecareLabel);
        oraPlecare = new JTextField();
        panelDate.add(oraPlecare);
    }

    private void initOraSosire() {
        oraSosireLabel = new JLabel("Ora sosire:");
        oraSosireLabel.setBorder(border);
        panelTitlu.add(oraSosireLabel);
        oraSosire = new JTextField();
        panelDate.add(oraSosire);
    }

    private void initDurata() {
        durataLabel = new JLabel("Durata zbor:");
        durataLabel.setBorder(border);
        panelTitlu.add(durataLabel);
        durata = new JTextField();
        panelDate.add(durata);
    }

    private void initDay() {
        zile = new JLabel("Zile");
        zile.setBorder(border);
        panelTitlu.add(zile);
        JPanel panelDay = new JPanel(new GridLayout(1, 7));
        day1 = new JCheckBox("Luni");
        day2 = new JCheckBox("Marti");
        day3 = new JCheckBox("Miercuri");
        day4 = new JCheckBox("Joi");
        day5 = new JCheckBox("Vineri");
        day6 = new JCheckBox("Sambata");
        day7 = new JCheckBox("Duminica");
        JPanel checkBox = new JPanel(new GridLayout(7, 1));
        checkBox.add(day1);
        checkBox.add(day2);
        checkBox.add(day3);
        checkBox.add(day4);
        checkBox.add(day5);
        checkBox.add(day6);
        checkBox.add(day7);
        panelDay.add(checkBox);
        panelDate.add(panelDay);
    }

    private void initPret() {
        pretLabel = new JLabel("Pret:");
        pretLabel.setBorder(border);
        panelTitlu.add(pretLabel);
        pret = new JTextField();
        panelDate.add(pret);
    }

    private void initAnuleaza() {
        anulare = new JButton("Anulare inregistrare");
        panelButoane.add(anulare);
        anulare.addActionListener(e -> {
            MainPage mainPage = new MainPage();
            mainPage.setVisible(true);
            dispose();
        });
    }

    private void initAdauga() {
        adauga = new JButton("Adauga inregistrare");
        panelButoane.add(adauga, BorderLayout.SOUTH);
        adauga.addActionListener(e -> {
            if (valid()) {
                MainPage.registerFly(sursaZbor.getText(), destinatieZbor.getText(), oraPlecare.getText(),
                        oraSosire.getText(), zileCheched(), pret.getText());
                MainPage mainPage = new MainPage();
                mainPage.setVisible(true);
                showMessage("Inregistrare reusita!", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        });
    }

    public static void showMessage(String msg, int messageType) {
        JOptionPane.showMessageDialog(null, msg, "Mesaj", messageType);
    }

    private boolean valid() {
        if (sursaZbor.getText().length() < 3) {
            showMessage("Sursa zbor are prea putine litere", JOptionPane.ERROR_MESSAGE);
            sursaZbor.requestFocus();
            return false;
        } else if (destinatieZbor.getText().length() < 3 || destinatieZbor.getText().equals(sursaZbor.getText())) {
            showMessage("Destinatia nu respecta patternul", JOptionPane.ERROR_MESSAGE);
            destinatieZbor.requestFocus();
            return false;
        } else if (!hourBolean(oraPlecare)) {
            showMessage("Eroare format ora pleare", JOptionPane.ERROR_MESSAGE);
            oraPlecare.requestFocus();
            return false;
        } else if (!hourBolean(oraSosire)) {
            showMessage("Eroare format ora sosire", JOptionPane.ERROR_MESSAGE);
            oraSosire.requestFocus();
            return false;
        } else {
            try {
                if (Integer.valueOf(pret.getText()) <= 0) {
                    showMessage("Pret incorect", JOptionPane.ERROR_MESSAGE);
                    pret.requestFocus();
                    return false;
                }

            } catch (NumberFormatException e) {
                showMessage("Introduceti numere la pret", JOptionPane.ERROR_MESSAGE);
                pret.requestFocus();
                return false;
            }

        }

        return true;
    }


    private boolean hourBolean(JTextField ora) {
        Pattern patternHour = Pattern.compile("^([0-1][0-9]|2[0-3]):([0-5][0-9])$");
        Matcher matcherHour = patternHour.matcher(ora.getText());
        return matcherHour.matches();
    }

    private String zileCheched() {
        StringBuilder zile = new StringBuilder();
        if (day1.isSelected()) {
            zile.append(day1.getText());
            zile.append(",");
        }
        if (day2.isSelected()) {
            zile.append(day2.getText());
            zile.append(",");
        }
        if (day3.isSelected()) {
            zile.append(day3.getText());
            zile.append(",");
        }
        if (day4.isSelected()) {
            zile.append(day4.getText());
            zile.append(",");
        }
        if (day5.isSelected()) {
            zile.append(day5.getText());
            zile.append(",");
        }
        if (day6.isSelected()) {
            zile.append(day6.getText());
            zile.append(",");
        }
        if (day7.isSelected()) {
            zile.append(day7.getText());
            zile.append(",");
        }
        return zile.toString().substring(0, zile.toString().length() - 1);
    }

}