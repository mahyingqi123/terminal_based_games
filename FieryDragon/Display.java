package com.example.fierydragon;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Display {
    /* The Display class, controls the UI of the game
    */

    // The stage of the UI
    private Stage stage;
    // The gridPane of the UI
    private GridPane gridPane;

    public Display(Stage stage) {
        /*
        * The constructor of the Display class
        * @param stage: The stage of the UI
        * creates the base of the UI
         */
        this.stage = stage;
        this.stage.setTitle("Dragon Game");
        this.gridPane = new GridPane();
        this.gridPane.setGridLinesVisible(true);

        // Create a 9x9 grid for the game layout
        for(int i=0;i<9;i++){
            this.gridPane.getColumnConstraints().add(new ColumnConstraints(60));
            this.gridPane.getRowConstraints().add(new RowConstraints(60));
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                StackPane stackPane = new StackPane();
                stackPane.setStyle("-fx-border-color: black; -fx-border-width: 1;");
                this.gridPane.add(stackPane, j, i);
            }
        }
        gridPane.setStyle("-fx-border-color: black; -fx-border-width: 1;");

        Button button = new Button("Start Game!");//
        VBox vbox = new VBox(this.gridPane, button); // Adds the GridPane and Button to the VBox
        Scene scene = new Scene(vbox, 700,700);

        this.stage.setScene(scene);
        this.stage.show();
    }


    public GridPane getGridPane() {
        /* Getter for the gridPane attribute

         */
        return this.gridPane;
    }

    public void drawBoard(Board board){
        /* Method to draw the board on the UI
        * @param board: The board object to draw
        * Draws the board on the UI
         */
        Position[][] positions = board.getCoordinates();

        // Loop through the positions and draw them on the UI
        for (int i = 0; i < positions.length; i++) {
            for (int j = 0; j < positions[i].length; j++) {
                Position position = positions[i][j];
                if(position == null){
                    continue;
                }
                StackPane stackPane = this.getStackPane(i, j);
                this.setImage(stackPane, position.getType());
            }
        }


    }

    public void disableMouseClick(DragonCard[] dragonCards){
        /*
        * Method to disable mouuse click on the dragon cards
        * @param dragonCards: The dragon cards to disable mouse click
        *
         */
        int[] coordinates = new int[]{2,2};
        for (DragonCard dragonCard : dragonCards) {
            StackPane stackPane = this.getStackPane(coordinates[0], coordinates[1]);
            stackPane.setDisable(true);
            coordinates = nextDragonCardCoordinates(coordinates[0], coordinates[1]);
        }
    }
    public void enableMouseClick(DragonCard[] dragonCards){
        /*
        * Method to enable mouse click on the dragon cards
        * @param dragonCards: The dragon cards to enable mouse click
        *
         */
        int[] coordinates = new int[]{2,2};
        for (DragonCard dragonCard : dragonCards) {
            StackPane stackPane = this.getStackPane(coordinates[0], coordinates[1]);
            stackPane.setDisable(false);
            coordinates = nextDragonCardCoordinates(coordinates[0], coordinates[1]);
        }
    }
    public void drawDragonCards(DragonCard[] dragonCards){
        /*
        * Method to draw the dragon cards on the UI
        * @param dragonCards: The dragon cards to draw
        * Draws the dragon cards on the UI
         */
        int[] coordinates = new int[]{2,2};

        // Loop through the dragon cards and draw them on the UI, set the image to unflipped
        for (DragonCard dragonCard : dragonCards) {
            StackPane stackPane = this.getStackPane(coordinates[0], coordinates[1]);
            this.setImage(stackPane, "unflipped");
            coordinates = nextDragonCardCoordinates(coordinates[0], coordinates[1]);
        }

    }

    public int[] nextDragonCardCoordinates(int row, int col) {
        /*
        * Method to get the next dragon card coordinates
        * @param row: The current row of the dragon card
        * @param col: The current column of the dragon card
        * @return: The next coordinates of the dragon card
         */
        int[] coordinates = new int[]{row, col};

        if(row<6){
            coordinates[0] = row + 1;
        } else {
            coordinates[0] = 2;
            coordinates[1] = col + 1;
        }
        return coordinates;
    }

    public void drawDragon(Player[] players){
        /*
        * Method to draw the dragons on the UI
        * @param players: The players to draw
        * Draws the dragons on the UI
         */

        // Loop through the players and draw the dragons on the UI
        for (Player player: players) {
            Dragon d = player.getDragon();
            Circle circle = new Circle(10);
            circle.setFill(stringToColor(player.getDragon().getColor()));
            StackPane stackPane = this.getStackPane(d.getRow(), d.getCol());
            stackPane.getChildren().add(circle);
        }
    }

    private Color stringToColor(String color){
        /*
        * Method to convert a string to a color
        * @param color: The string to convert
        * @return: The color of the string
         */
        return switch (color) {
            case "red" -> Color.RED;
            case "blue" -> Color.BLUE;
            case "green" -> Color.GREEN;
            case "yellow" -> Color.YELLOW;
            default -> Color.BLACK;
        };
    }
    private int rowColToIndex(int row, int col) {
        /*
        * Method to convert row and column to index
        * @param row: The row to convert
        * @param col: The column to convert
        * @return: The index of the row and column
        * Converts the row and column to index
         */
        return row*9 + col+1;
    }


    private String typeToImageName(Type type){
        /*
        * Method to convert type to image name
        * @param type: The type to convert
        * @return: The image name of the type
        * Converts the type to image name
        *
         */
        return switch (type) {
            case BAT -> "bat";
            case SPIDER -> "spider";
            case BABY_DRAGON -> "babyDragon";
            case SALAMANDER -> "salamander";
            case PIRATE_DRAGON -> "pirateDragon";
        };
    }

    public StackPane getByCoordinates(int row, int col){
        /*
        * Method to get the stackPane by coordinates
        * @param row: The row of the stackPane
        * @param col: The column of the stackPane
        * @return: The stackPane at the coordinates
         */
        return this.getStackPane(row, col);
    }
    public void updateDragonPosition(int previousRow, int previousCol, int newRow, int newCol){
        /*
        * Method to update the dragon position on the UI
        * @param previousRow: The previous row of the dragon
        * @param previousCol: The previous column of the dragon
        * @param newRow: The new row of the dragon
        * @param newCol: The new column of the dragon
         */
        StackPane previousStackPane = this.getStackPane(previousRow, previousCol);
        Circle circle = (Circle) previousStackPane.getChildren().get(0);
        previousStackPane.getChildren().remove(circle);
        StackPane newStackPane = this.getStackPane(newRow, newCol);
        newStackPane.getChildren().add(circle);
    }

    public void displayCard(StackPane stackPane, DragonCard dragonCard){
        /*
        * Method to display the dragon card on the UI
        * @param stackPane: The stackPane to display the dragon card
        * @param dragonCard: The dragon card to display
        * Displays the dragon card on the UI
         */
        this.setImage(stackPane, dragonCard.getType(), dragonCard.getAnimalCount());
    }

    public StackPane getStackPane(int row, int col){
        /*
        * Method to get the stackPane by row and column
        * @param row: The row of the stackPane
        * @param col: The column of the stackPane
        * @return: The stackPane at the row and column
         */
        return (StackPane) this.gridPane.getChildren().get(rowColToIndex(row, col));
    }
    public void setImage(StackPane stackPane, Type type, int animalCount){
        /*
        * Method to set the image with type and count to the stackPane
        * @param stackPane: The stackPane to set the image
        * @param type: The type of the image
        * @param animalCount: The count of the image
        *
         */
        stackPane.setStyle("-fx-background-image: url('file:src/main/resources/"+ typeToImageName(type) +"_"+animalCount+".png');-fx-background-repeat: no-repeat; -fx-background-size: 60");
    }
    public void setImage(StackPane stackPane, Type type){
        /*
        * Method to set the image with type to the stackPane
        * @param stackPane: The stackPane to set the image
        * @param type: The type of the image
        *
         */
        stackPane.setStyle("-fx-background-image: url('file:src/main/resources/"+ typeToImageName(type) +".png');-fx-background-repeat: no-repeat; -fx-background-size: 60");
    }
    public void setImage(StackPane stackPane, String imageName){
        /*
        * Method to set the image to the stackPane using image name
        * @param stackPane: The stackPane to set the image
        * @param imageName: The name of the image
        *
         */
        stackPane.setStyle("-fx-background-image: url('file:src/main/resources/"+ imageName +".png');-fx-background-repeat: no-repeat; -fx-background-size: 60");
    }
}
