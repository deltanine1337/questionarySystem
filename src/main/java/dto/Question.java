package dto;

public class Question {
    private int id_question;
    private String formulation;
    private int num_of_answers;

    public Question(int id, String formulation, int num_of_answers)
    {
        this.id_question = id;
        this.formulation = formulation;
        this.num_of_answers = num_of_answers;
    }

    public int getId_question() {
        return id_question;
    }

    public void setId_question(int id) {
        this.id_question = id;
    }

    public String getFormulation() {
        return formulation;
    }

    public void setFormulation(String formulation) {
        this.formulation = formulation;
    }

    public int getNum_of_answers() {
        return num_of_answers;
    }

    public void setNum_of_answers(int num_of_answers) {
        this.num_of_answers = num_of_answers;
    }
}
