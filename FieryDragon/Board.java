package com.example.fierydragon;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Board {
    /*
     * This class represents the game board
     */

    // The width and height of the board
    private final int width = 9;
    private final int height = 9;

    // The fixed configuration of volcano cards with and without caves
    private final Type[][]  VOLCANO_CONFIG_WITH_CAVE= {
            {Type.BABY_DRAGON, Type.BAT, Type.SPIDER},
            {Type.SALAMANDER, Type.SPIDER, Type.BAT},
            {Type.SPIDER, Type.SALAMANDER, Type.BABY_DRAGON},
            {Type.BAT, Type.SPIDER, Type.BABY_DRAGON}
    };

    private final Type[][] VOLCANO_CONFIG_WITHOUT_CAVE = {
            {Type.SPIDER, Type.BAT, Type.SALAMANDER},
            {Type.BABY_DRAGON, Type.SALAMANDER, Type.BAT},
            {Type.BAT, Type.BABY_DRAGON, Type.SALAMANDER},
            {Type.SALAMANDER, Type.BABY_DRAGON, Type.SPIDER}};

    // The configuration of type of caves
    private final Type[] caveConfig = {Type.BAT, Type.BABY_DRAGON, Type.SPIDER, Type.SALAMANDER};

    // The array of volcano cards and caves
    private VolcanoCard[] volcanoCards;
    private Cave[] caves;

    // The 2D array of caves and squares on the board
    private Position[][] coordinates;


    public Board(int volcanoCardCount){
        /*
         * Constructor for the Board class
         * @param volcanoCardCount: The number of volcano cards to be created
         * @return: None
         */
        this.coordinates = new Position[width][height];
        this.createVolcanoCards(volcanoCardCount);

    }

    private void createVolcanoCards(int volcanoCardCount) {
        /*
            * This method creates the volcano cards and caves on the board
            * @param volcanoCardCount: The number of volcano cards to be created
            * @return: None
         */
        ArrayList<Integer> withCave = new ArrayList<>(Arrays.asList(0, 1, 2, 3));
        ArrayList<Integer> withoutCave = new ArrayList<>(Arrays.asList(0, 1, 2, 3));
        ArrayList<Integer> caveIndex = new ArrayList<>(Arrays.asList(0, 1, 2, 3));
        Collections.shuffle(withCave);
        Collections.shuffle(withoutCave);
        Collections.shuffle(caveIndex);
        this.caves = new Cave[4];
        this.volcanoCards = new VolcanoCard[volcanoCardCount];
        Type[] squareConfig;
        Type caveType;
        int startingRow = 2;
        int startingColumn = 1;
        for (int i = 0;i<volcanoCardCount;i++){
            Square[] squares;
            if (i%2==0){
                squareConfig = VOLCANO_CONFIG_WITH_CAVE[withCave.remove(0)];
                this.volcanoCards[i] = new VolcanoCard(squareConfig);
                squares = this.volcanoCards[i].getSquares();
                caveType = caveConfig[caveIndex.remove(0)];
                int[] cavePosition = getCavePosition(nextCoordinate(startingRow, startingColumn));
                this.caves[i/2] = new Cave(caveType, squares[1]);
                this.coordinates[cavePosition[0]][cavePosition[1]] = caves[i/2];
            }else{
                squareConfig = VOLCANO_CONFIG_WITHOUT_CAVE[withoutCave.remove(0)];
                this.volcanoCards[i] = new VolcanoCard(squareConfig);
                squares = this.volcanoCards[i].getSquares();
            }

            for (Square square : squares) {
                this.coordinates[startingRow][startingColumn] = square;
                int[] nextCoord = nextCoordinate(startingRow, startingColumn);
                startingRow = nextCoord[0];
                startingColumn = nextCoord[1];
            }
        }
    }

    public VolcanoCard[] getVolcanoCards() {
        /*
         * This method returns the array of volcano cards
         * @return: The array of volcano cards
         */
        return volcanoCards;
    }

    public Cave[] getCaves() {
        /*
         * This method returns the array of caves
         * @return: The array of caves
         */
        return caves;
    }

    public int[] nextCoordinate(int row, int col){
        /*
         * This method returns the next coordinate to move to on the board according to a coordinate
         * @param row: The current row
         * @param col: The current column
         * @return: The next coordinate
         */
        if (row == 1 && col < 7) {
            col++;
        } else if (row < 7 && col == 7) {
            row++;
        } else if (row == 7 && col > 1) {
            col--;
        } else {
            row--;
        }
        return new int[]{row, col};
    }

    public int[] nextCoordinate(Dragon dragon, int steps){
        /*
         * This method returns the next coordinate to move to on the board according to the dragon and number of steps
         * @param dragon: The dragon to move
         * @param steps: The number of steps to move
         * @return: The next coordinate
         */
        int row = dragon.getRow();
        int col = dragon.getCol();
        int startingRow = dragon.getRow();
        int startingCol = dragon.getCol();
        Position endingPosition = dragon.getStartingCave().getSquareOutside();
        int[] endingCoordinates = getCoordinatesByPosition(endingPosition);

        if(this.dragonInCave(dragon)){
            row = endingCoordinates[0];
            col = endingCoordinates[1];
            steps--;
        }
        for(int i = 0; i<steps; i++) {
            if (row == endingCoordinates[0] && col == endingCoordinates[1] && dragon.getSquareMoved() > 1) {
                int[] caveCoordinates = getCoordinatesByPosition(dragon.getStartingCave());
                row = caveCoordinates[0];
                col = caveCoordinates[1];
                if (i<steps-1){
                    row = startingRow;
                    col = startingCol;
                }
                break;
            } else {
                if (row == 1 && col < 7) {
                    col++;
                } else if (row < 7 && col == 7) {
                    row++;
                } else if (row == 7 && col > 1) {
                    col--;
                } else {
                    row--;
                }
            }
        }
        return new int[]{row, col};
    }
    public int[] previousCoordinate(Dragon dragon, int steps){
        /*
         * This method returns the previous coordinate to move back to on the board according to the dragon and number of steps
         * @param dragon: The dragon to move
         * @param steps: The number of steps to move
         * @return: The previous coordinate
         */
        int row = dragon.getRow();
        int col = dragon.getCol();
        if (dragonInCave(dragon)){
            return new int[]{row, col};
        }
        for( int i = steps; i<0; i++){
            if(row==1 && col >1){
                col--;
            }else if (row >= 1 && col == 1) {
                row++;
            }else if (row == 7 && col < 7) {
                col++;
            } else {
                row--;
            }
        }
        return new int[]{row, col};
    }


    public int[] getCavePosition(int[] coordinates){
        /*
         * This method calculates the position of the cave based on the coordinates
         * @param coordinates: The coordinates of the cave
         * @return: The position of the cave
         */
        int[] cavePosition = new int[2];
        if(coordinates[0] == 1 && coordinates[1] <=1) {
            cavePosition[1] = coordinates[1] - 1;
        }else if(coordinates[0] == 1 && coordinates[1] == 7){
            cavePosition[1] = coordinates[1] + 1;}
        else if(coordinates[0] == 7 && coordinates[1] == 7){
            cavePosition[0] = coordinates[0] + 1;
            cavePosition[1] = coordinates[1] + 1;}
        else if(coordinates[0] == 7 && coordinates[1] == 1){
            cavePosition[0] = coordinates[0] + 1;}
        return cavePosition;
    }


    public int[] getCoordinatesByPosition(Position position){
        /*
         * This method returns the coordinates of a position on the board
         * @param position: The position to get the coordinates of
         * @return: The coordinates of the position
         */
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                if (this.coordinates[i][j] == position) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

    public Position getPositionByCoordinates(int row, int col){
        /*
         * This method returns the position on the board based on the coordinates
         * @param row: The row of the position
         * @param col: The column of the position
         * @return: The position on the board
         */
        return this.coordinates[row][col];
    }

    public Position[][] getCoordinates() {
        /*
         * This method returns the 2D array of positions on the board
         * @return: The 2D array of positions
         */
        return coordinates;
    }

    public int[] moveDragon(Dragon dragon, int steps){
        /*
         * This method calculates where the dragon should move to on the board
         * @param dragon: The dragon to move
         * @param steps: The number of steps to move
         * @return: The new coordinates of the dragon
         */
        int[] coordinates;
        if(steps < 0){
            coordinates = previousCoordinate(dragon, steps);
        }else{
            coordinates = nextCoordinate(dragon, steps);
        }
        return coordinates;
    }

    public boolean dragonInCave(Dragon dragon){
        /*
         * This method checks if the dragon is in a cave
         * @param dragon: The dragon to check
         * @return: True if the dragon is in a cave, False otherwise
         */
        int[] coordinates = getCoordinatesByPosition(dragon.getStartingCave());
        return dragon.getRow() == coordinates[0] && dragon.getCol() == coordinates[1];
    }
}
