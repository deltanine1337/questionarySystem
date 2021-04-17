package dto;

public class UsersQuestionary {
    private String login;
    private int id_questionary;
    private String answers;

    public UsersQuestionary(String login, int id_questionary, String answers)
    {
        this.login = login;
        this.id_questionary = id_questionary;
        this.answers = answers;
    }

    public int getId_questionary() {
        return id_questionary;
    }

    public String getLogin() {
        return login;
    }

    public String getAnswers() {
        return answers;
    }

    public void setId_questionary(int id_questionary) {
        this.id_questionary = id_questionary;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }
}
