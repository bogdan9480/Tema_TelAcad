package dao;

import model.Persoana;

import javax.swing.text.html.Option;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersoanaDao {

    private Connection connection;

    private PreparedStatement preparedStatement;

    private PreparedStatement findAllStatement;

    private PreparedStatement findByUsername;

    public PersoanaDao(Connection connection) {
        this.connection = connection;

        try {
            preparedStatement = connection.prepareStatement("INSERT INTO persoana VALUES (null,?)");
            findAllStatement = connection.prepareStatement("SELECT * FROM persoana");
            findByUsername = connection.prepareStatement("SELECT * FROM persoana WHERE userName = ?");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean create(Persoana p) {
        try {
            preparedStatement.setString(1, p.getUsername());
            preparedStatement.setString(2, p.getEmail());
            preparedStatement.setString(3, p.getPassword());
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void updateName(String userName) {
        try {
            findByUsername.setString(1, userName);
            findByUsername.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateEmail(String email) {
        try {
            findByUsername.setString(2, email);
            findByUsername.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Persoana> findAll() {
        List<Persoana> persoanaList = new ArrayList<>();
        try {
            ResultSet rs = findAllStatement.executeQuery();
            while (rs.next()) {
                Persoana p = new Persoana(rs.getString("userName"), rs.getString("email"), rs.getString("parola"));
                persoanaList.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return persoanaList;
    }

    public Optional<Persoana> findByUsername(String username) {
        try {
            findByUsername.setString(1, username);
            ResultSet rs = findByUsername.executeQuery();
            if (rs.next()) {
                return Optional.of(new Persoana(rs.getString("userName"), rs.getString("email"), rs.getString("parola")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

}


