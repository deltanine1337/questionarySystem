package dto;

public class Questionary {
    private int id_questionary;
    private String name;

    public Questionary(int id_questionary, String name)
    {
        this.id_questionary = id_questionary;
        this.name = name;
    }

    public int getId_questionary() {
        return id_questionary;
    }

    public String getName() {
        return name;
    }

    public void setId_questionary(int id_questionary) {
        this.id_questionary = id_questionary;
    }

    public void setName(String name) {
        this.name = name;
    }
}
