package com.quizapp.controller;

import com.quizapp.model.Question;
import com.quizapp.util.QuestionBank;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * QuizController class handles the logic and interaction of the Quiz Application.
 * It manages question display, answer selection, scoring, and navigation.
 */
public class QuizController implements Initializable {

    @FXML
    private Label questionNumberLabel;

    @FXML
    private Label questionTextLabel;

    @FXML
    private RadioButton optionARadioButton;

    @FXML
    private RadioButton optionBRadioButton;

    @FXML
    private RadioButton optionCRadioButton;

    @FXML
    private RadioButton optionDRadioButton;

    @FXML
    private Button nextButton;

    @FXML
    private Label scoreLabel;

    @FXML
    private Label percentageLabel;

    @FXML
    private Label performanceLabel;

    @FXML
    private VBox scoreContainer;

    @FXML
    private Button restartButton;

    private ToggleGroup optionsGroup;

    // Quiz data
    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private int score = 0;

    /**
     * Initialize the controller and set up the quiz.
     * This method is called when the FXML file is loaded.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Load shuffled questions from QuestionBank
        questions = QuestionBank.getShuffledQuestions();

        // Create ToggleGroup for radio buttons
        optionsGroup = new ToggleGroup();
        optionARadioButton.setToggleGroup(optionsGroup);
        optionBRadioButton.setToggleGroup(optionsGroup);
        optionCRadioButton.setToggleGroup(optionsGroup);
        optionDRadioButton.setToggleGroup(optionsGroup);

        // Initialize score label (hide initially)
        scoreLabel.setVisible(false);

        // Load first question
        loadQuestion();

        // Set up next button action
        nextButton.setOnAction(event -> handleNextButton());

        // Set up restart button action
        restartButton.setOnAction(event -> restartQuiz());
    }

    /**
     * Load and display the current question.
     */
    private void loadQuestion() {
        if (currentQuestionIndex < questions.size()) {
            Question question = questions.get(currentQuestionIndex);

            // Update question number label
            questionNumberLabel.setText("Question " + (currentQuestionIndex + 1) + " of " + questions.size());

            // Update question text
            questionTextLabel.setText(question.getQuestionText());

            // Update option labels
            optionARadioButton.setText(question.getOptionA());
            optionBRadioButton.setText(question.getOptionB());
            optionCRadioButton.setText(question.getOptionC());
            optionDRadioButton.setText(question.getOptionD());

            // Clear previous selection
            optionsGroup.selectToggle(null);
        }
    }

    /**
     * Handle the "Next" button click event.
     * Validates selection, checks answer, updates score, and moves to next question.
     */
    @FXML
    private void handleNextButton() {
        // Check if an option is selected
        if (optionsGroup.getSelectedToggle() == null) {
            showAlert("Please select an option before proceeding.");
            return;
        }

        // Get the selected radio button
        RadioButton selectedRadioButton = (RadioButton) optionsGroup.getSelectedToggle();

        // Determine which option was selected (A, B, C, or D)
        String selectedAnswer = getSelectedAnswer(selectedRadioButton);

        // Get the correct answer
        String correctAnswer = questions.get(currentQuestionIndex).getCorrectAnswer();

        // Check if the answer is correct
        if (selectedAnswer.equals(correctAnswer)) {
            score++;
        }

        // Move to next question
        currentQuestionIndex++;

        // Check if there are more questions
        if (currentQuestionIndex < questions.size()) {
            loadQuestion();
        } else {
            // Quiz is complete
            showFinalScore();
        }
    }

    /**
     * Determine which option (A, B, C, or D) was selected.
     *
     * @param selectedRadioButton The selected radio button
     * @return The option letter (A, B, C, or D)
     */
    private String getSelectedAnswer(RadioButton selectedRadioButton) {
        if (selectedRadioButton == optionARadioButton) {
            return "A";
        } else if (selectedRadioButton == optionBRadioButton) {
            return "B";
        } else if (selectedRadioButton == optionCRadioButton) {
            return "C";
        } else if (selectedRadioButton == optionDRadioButton) {
            return "D";
        }
        return "";
    }

    /**
     * Display the final score and disable further interaction.
     */
    private void showFinalScore() {
        // Calculate percentage
        double percentage = (score * 100.0) / questions.size();
        int percentageInt = (int) percentage;

        // Set performance message based on score
        String performanceMessage = getPerformanceMessage(percentageInt);

        // Show final score overlay
        scoreContainer.setVisible(true);
        scoreLabel.setText("Your Score: " + score + " / " + questions.size());
        percentageLabel.setText("Percentage: " + percentageInt + "%");
        performanceLabel.setText(performanceMessage);
        restartButton.setVisible(true);

        // Disable next button
        nextButton.setDisable(true);
    }

    /**
     * Get a performance message based on the percentage score.
     *
     * @param percentage The percentage score
     * @return A performance message
     */
    private String getPerformanceMessage(int percentage) {
        if (percentage >= 90) {
            return "🌟 Excellent! Outstanding performance!";
        } else if (percentage >= 80) {
            return "👍 Very Good! Great job!";
        } else if (percentage >= 70) {
            return "😊 Good! You passed!";
        } else if (percentage >= 60) {
            return "📚 Fair! Keep practicing!";
        } else {
            return "💪 Try again! You can do better!";
        }
    }

    /**
     * Restart the quiz by resetting all values and reloading the first question.
     */
    private void restartQuiz() {
        // Reload shuffled questions
        questions = QuestionBank.getShuffledQuestions();
        currentQuestionIndex = 0;
        score = 0;
        scoreContainer.setVisible(false);
        nextButton.setDisable(false);
        loadQuestion();
    }

    /**
     * Show an alert dialog with a message.
     *
     * @param message The message to display
     */
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
