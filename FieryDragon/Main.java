package com.example.fierydragon;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application{
    /* The Main class, the entry point of the game and extends the JavaFX Application class
    */

    public static void main(String[] args) {
        /* The main method, the entry point of the game

         */
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        /* The start method, the entry point of the JavaFX application
        * @param stage: The stage of the application
         */

        // Create a new display and game object
        Display display = new Display(stage);
        Game game = new Game();

        // Create a new GameController object with control of the UI and the game
        GameController gameController = new GameController(game,display);
    }
}
