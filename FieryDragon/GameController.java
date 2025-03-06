package com.example.fierydragon;

import javafx.animation.PauseTransition;
import javafx.scene.layout.StackPane;

import java.util.Arrays;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class GameController {
    /*
     * The GameController class, it is a class that controls the game and the UI
     */

    // The game object
    private Game game;
    // The display object
    private Display display;

    public GameController(Game game, Display display){
        /*
         * The constructor of the GameController class
         * @param game: The game object
         * @param display: The display object
         */
        this.game = game;
        this.game.setPlayerCount(4);
        this.game.setUpGame();
        this.display = display;
        // Display the game components
        this.displayGameComponents();
        // Set the onClick event for the dragon cards
        this.setOnClick();
    }

    private void displayGameComponents(){
        /*
         * Method to display the game components
         */
        this.display.drawBoard(game.getBoard());
        this.display.drawDragon(game.getPlayers());
        this.display.drawDragonCards(game.getDragonCards());
    }

    private void setOnClick(){
        /*
         * Method to set the onClick event for the dragon cards
         */
        DragonCard[] dragonCards = game.getDragonCards();
        int[] coordinates = new int[]{2,2};
        for (DragonCard dragonCard : dragonCards) {
            StackPane dragonCardPane = this.display.getByCoordinates(coordinates[0], coordinates[1]);

            dragonCardPane.setOnMouseClicked(e -> {
                Player currentPlayer= this.game.getCurrentPlayer();
                System.out.println("Current player: " + currentPlayer.getDragon().getColor());
                int previousDragonRow = currentPlayer.getDragon().getRow();
                int previousDragonCol = currentPlayer.getDragon().getCol();
                FlipAction flipAction = new FlipAction(dragonCard);
                this.game.setAction(flipAction);
                int currentDragonRow = currentPlayer.getDragon().getRow();
                int currentDragonCol = currentPlayer.getDragon().getCol();
                this.display.displayCard(dragonCardPane, dragonCard);
                this.display.updateDragonPosition(previousDragonRow, previousDragonCol, currentDragonRow, currentDragonCol);

                if (flipAction.changePlayer()){
                    this.display.disableMouseClick(this.game.getDragonCards());
                    PauseTransition pause = new PauseTransition(javafx.util.Duration.seconds(3));
                    pause.setOnFinished(event -> {
                        this.display.drawDragonCards(game.getDragonCards());
                        this.display.enableMouseClick(game.getDragonCards());
                    });
                    pause.play();
                }
            });
            coordinates = this.display.nextDragonCardCoordinates(coordinates[0], coordinates[1]);
        }
    }


}
