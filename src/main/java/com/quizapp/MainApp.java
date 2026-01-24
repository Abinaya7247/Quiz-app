package com.quizapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * MainApp class is the entry point for the JavaFX Quiz Application.
 * It loads the FXML file and displays the main window.
 */
public class MainApp extends Application {

    /**
     * Start method is called when the application starts.
     * It loads the quiz.fxml file and displays it in the primary stage.
     *
     * @param primaryStage The primary stage of the application
     * @throws Exception If the FXML file cannot be loaded
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/quizapp/view/quiz.fxml"));
        Parent root = loader.load();

        // Create the scene
        Scene scene = new Scene(root, 800, 600);

        // Load CSS stylesheet if available
        String css = getClass().getResource("/com/quizapp/view/styles.css").toExternalForm();
        scene.getStylesheets().add(css);

        // Set up the primary stage
        primaryStage.setTitle("Quiz Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Main method to launch the application.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
