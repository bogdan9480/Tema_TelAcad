package model;

public class Zbor {

    private String sursa;
    private String destinatie;
    private String oraPlecare;
    private String oraSosire;
    private String zile;
    private String s;

    public Zbor(String sursa, String destinatie, String oraPlecare, String oraSosire, String zile, String s) {
        this.sursa = sursa;
        this.destinatie = destinatie;
        this.oraPlecare = oraPlecare;
        this.oraSosire = oraSosire;
        this.zile = zile;
        this.s = s;
    }

    public String getSursa() {
        return sursa;
    }

    public void setSursa(String sursa) {
        this.sursa = sursa;
    }

    public String getDestinatie() {
        return destinatie;
    }

    public void setDestinatie(String destinatie) {
        this.destinatie = destinatie;
    }

    public String getOraPlecare() {
        return oraPlecare;
    }

    public void setOraPlecare(String oraPlecare) {
        this.oraPlecare = oraPlecare;
    }

    public String getOraSosire() {
        return oraSosire;
    }

    public void setOraSosire(String oraSosire) {
        this.oraSosire = oraSosire;
    }

    public String getZile() {
        return zile;
    }

    public void setZile(String zile) {
        this.zile = zile;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return "Zbor{" +
                "sursa='" + sursa + '\'' +
                ", destinatie='" + destinatie + '\'' +
                ", oraPlecare='" + oraPlecare + '\'' +
                ", oraSosire='" + oraSosire + '\'' +
                ", zile='" + zile + '\'' +
                ", s='" + s + '\'' +
                '}';
    }
}
