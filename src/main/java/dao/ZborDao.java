package dao;

import model.Zbor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ZborDao {
    private Connection connection;

    private PreparedStatement preparedStatement;
    private PreparedStatement findByZbor;


    public ZborDao(Connection connection) {
        this.connection = connection;
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO ZBOR VALUES (null, ?)");
            findByZbor = connection.prepareStatement("SELECT * FROM ZBOR WHERE zbor = ?");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean insert(String nume) {
        try {
            preparedStatement.setString(1, nume);
            int numarLiniiModificate = preparedStatement.executeUpdate();
            return numarLiniiModificate != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Zbor> findAll() {
        try {
            ResultSet result = findByZbor.executeQuery();
            List<Zbor> zboruri = new ArrayList<>();

            while (result.next()) {
                String sursa = result.getString("sursa");
                String destinatie = result.getString("destinatie");
                String oraPlecare = result.getString("oraPlecare");
                String oraSosire = result.getString("oraSosire");
                String zile = result.getString("zile");
                String s = result.getString("s");

                Zbor zbor = new Zbor(sursa, destinatie, oraPlecare, oraSosire, zile, s);
                zboruri.add(zbor);
            }

            return zboruri;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
