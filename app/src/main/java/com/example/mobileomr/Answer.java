package com.example.mobileomr;

public class Answer{
    int question_no;
    char answer;

    public Answer(int question_no, char answer) {
        this.question_no = question_no;
        this.answer = answer;
    }

    public int getQuestion_no() {
        return question_no;
    }

    public void setQuestion_no(int question_no) {
        this.question_no = question_no;
    }

    public char getAnswer() {
        return answer;
    }

    public void setAnswer(char answer) {
        this.answer = answer;
    }
}
