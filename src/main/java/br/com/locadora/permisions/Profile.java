package br.com.locadora.permisions;

import br.com.locadora.enums.Idioma;

public class Profile {

    private static Profile instance;

    private String username;

    private String password;

    private Idioma language;

    private Profile() {
    }

    public static Profile getInstance() {
        if(instance == null) {
            instance = new Profile();
        }
        return instance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Idioma getLanguage() {
        return language;
    }

    public void setLanguage(Idioma language) {
        this.language = language;
    }

    public void clear() {
        instance = null;
    }
}
