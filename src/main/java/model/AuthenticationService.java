package model;

import dao.PersoanaDao;

import java.util.HashMap;
import java.util.Map;

public class AuthenticationService {
    private static Map<String, String> usernamePasswordMap = new HashMap<>();
    private static Map<String, String> emailPasswordMap = new HashMap<>();
    private static PersoanaDao persoanaDao;

    public static boolean login(String username, String password) {
        if (usernamePasswordMap.get(username) != null && !usernamePasswordMap.get(username).equals(persoanaDao.findByUsername(username))) {
            return usernamePasswordMap.get(username).equals(password);
        } else if (emailPasswordMap.get(username) != null) {
            return emailPasswordMap.get(username).equals(password);
        }
        return false;
    }


    public static void registerUserName(String username, String password) {
        usernamePasswordMap.put(username, password);
    }

    public static void registerEmail(String email, String password) {
        emailPasswordMap.put(email, password);
    }

}
