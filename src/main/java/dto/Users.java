package dto;

public class Users {
    private String login;
    private String password;
    private byte role;

    public Users(String login, String password, byte role)
    {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public byte getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(byte role) {
        this.role = role;
    }
}
