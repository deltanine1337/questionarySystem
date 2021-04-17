package dto;

public class Answer {
    private int id_question;
    private int id_answer;
    private String formulation;

    public Answer(int id_question, int id_answer, String formulation)
    {
        this.id_question = id_question;
        this.id_answer = id_answer;
        this.formulation = formulation;
    }

    public int getId_question() {
        return id_question;
    }

    public int getId_answer() {
        return id_answer;
    }

    public String getFormulation() {
        return formulation;
    }

    public void setId_question(int id_question) {
        this.id_question = id_question;
    }

    public void setId_answer(int id_answer) {
        this.id_answer = id_answer;
    }

    public void setFormulation(String formulation) {
        this.formulation = formulation;
    }
}
