package com.quizapp.util;

import com.quizapp.model.Question;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * QuestionBank class manages the collection of quiz questions.
 * It provides access to all questions in the quiz.
 */
public class QuestionBank {
    private static final List<Question> questions = new ArrayList<>();

    // Static initializer to populate questions
    static {
        questions.add(new Question(
                "What is the capital of France?",
                "London",
                "Berlin",
                "Paris",
                "Madrid",
                "C"
        ));

        questions.add(new Question(
                "Which planet is known as the Red Planet?",
                "Venus",
                "Mars",
                "Jupiter",
                "Saturn",
                "B"
        ));

        questions.add(new Question(
                "What is the largest ocean in the world?",
                "Atlantic Ocean",
                "Indian Ocean",
                "Arctic Ocean",
                "Pacific Ocean",
                "D"
        ));

        questions.add(new Question(
                "Who is the author of 'To Kill a Mockingbird'?",
                "Harper Lee",
                "Mark Twain",
                "F. Scott Fitzgerald",
                "Ernest Hemingway",
                "A"
        ));

        questions.add(new Question(
                "What is the smallest prime number?",
                "0",
                "1",
                "2",
                "3",
                "C"
        ));

        questions.add(new Question(
                "Which country has the most population?",
                "India",
                "United States",
                "Indonesia",
                "Pakistan",
                "A"
        ));

        questions.add(new Question(
                "What is the chemical symbol for Gold?",
                "Go",
                "Gd",
                "Au",
                "Ag",
                "C"
        ));

        questions.add(new Question(
                "In which year did World War II end?",
                "1943",
                "1944",
                "1945",
                "1946",
                "C"
        ));

        questions.add(new Question(
                "What is the speed of light?",
                "300,000 km/s",
                "150,000 km/s",
                "450,000 km/s",
                "600,000 km/s",
                "A"
        ));

        questions.add(new Question(
                "Which Shakespeare play has the most lines?",
                "Hamlet",
                "Macbeth",
                "Othello",
                "King Lear",
                "A"
        ));
    }

    /**
     * Get all questions from the question bank.
     *
     * @return List of all questions
     */
    public static List<Question> getQuestions() {
        return new ArrayList<>(questions);
    }

    /**
     * Get all questions from the question bank with both questions and options shuffled.
     * This method returns a new copy of all questions with the questions list shuffled
     * and each question's options shuffled as well.
     *
     * @return List of all questions shuffled
     */
    public static List<Question> getShuffledQuestions() {
        // Create a new list from the original questions
        List<Question> shuffledQuestions = new ArrayList<>(questions);
        
        // Shuffle the order of questions
        Collections.shuffle(shuffledQuestions);
        
        // Shuffle the options for each question
        for (Question question : shuffledQuestions) {
            question.shuffleOptions();
        }
        
        return shuffledQuestions;
    }

    /**
     * Get total number of questions in the bank.
     *
     * @return Total number of questions
     */
    public static int getTotalQuestions() {
        return questions.size();
    }

    /**
     * Get a specific question by index.
     *
     * @param index The index of the question
     * @return The question at the specified index, or null if index is out of bounds
     */
    public static Question getQuestion(int index) {
        if (index >= 0 && index < questions.size()) {
            return questions.get(index);
        }
        return null;
    }
}
