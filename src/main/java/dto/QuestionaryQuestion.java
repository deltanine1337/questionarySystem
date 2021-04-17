package dto;

public class QuestionaryQuestion {
    private int id_questionary;
    private int id_question;

    public QuestionaryQuestion(int id_questionary, int id_question)
    {
        this.id_questionary = id_questionary;
        this.id_question = id_question;
    }

    public int getId_questionary() {
        return id_questionary;
    }

    public int getId_question() {
        return id_question;
    }

    public void setId_questionary(int id_questionary) {
        this.id_questionary = id_questionary;
    }

    public void setId_question(int id_question) {
        this.id_question = id_question;
    }
}
