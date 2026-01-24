package com.quizapp.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Question class represents a multiple-choice question in the quiz.
 * Each question has a question text and four options (A, B, C, D),
 * along with the correct answer.
 */
public class Question {
    private String questionText;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String correctAnswer;

    /**
     * Constructor to initialize a Question object.
     *
     * @param questionText The text of the question
     * @param optionA      Option A
     * @param optionB      Option B
     * @param optionC      Option C
     * @param optionD      Option D
     * @param correctAnswer The correct answer (A, B, C, or D)
     */
    public Question(String questionText, String optionA, String optionB,
                    String optionC, String optionD, String correctAnswer) {
        this.questionText = questionText;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctAnswer = correctAnswer;
    }

    // Getters
    public String getQuestionText() {
        return questionText;
    }

    public String getOptionA() {
        return optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    // Setters
    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    /**
     * Shuffle the options for this question and update the correct answer accordingly.
     * This method randomly reorders the four options and updates the correct answer letter.
     */
    public void shuffleOptions() {
        // Create a list of all options with their original positions
        List<String> optionsList = new ArrayList<>();
        optionsList.add(optionA);
        optionsList.add(optionB);
        optionsList.add(optionC);
        optionsList.add(optionD);

        // Create a map to track the original correct answer
        Map<String, String> originalToNewPosition = new HashMap<>();
        String correctAnswerText = getCorrectAnswerText();

        // Shuffle the options
        Collections.shuffle(optionsList);

        // Update the options with shuffled values
        this.optionA = optionsList.get(0);
        this.optionB = optionsList.get(1);
        this.optionC = optionsList.get(2);
        this.optionD = optionsList.get(3);

        // Find the new position of the correct answer
        for (int i = 0; i < optionsList.size(); i++) {
            if (optionsList.get(i).equals(correctAnswerText)) {
                this.correctAnswer = String.valueOf((char) ('A' + i));
                break;
            }
        }
    }

    /**
     * Get the text of the correct answer option.
     *
     * @return The text of the correct answer
     */
    private String getCorrectAnswerText() {
        switch (correctAnswer) {
            case "A":
                return optionA;
            case "B":
                return optionB;
            case "C":
                return optionC;
            case "D":
                return optionD;
            default:
                return "";
        }
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionText='" + questionText + '\'' +
                ", optionA='" + optionA + '\'' +
                ", optionB='" + optionB + '\'' +
                ", optionC='" + optionC + '\'' +
                ", optionD='" + optionD + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                '}';
    }
}
