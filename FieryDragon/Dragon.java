package com.example.fierydragon;

public class Dragon {
    /*
    * The Dragon class, it represents the dragon of a player in the game
    *
     */

    // The color of the dragon
    private String color;
    // The current column of the dragon
    private int col;
    // The current row of the dragon
    private int row;
    // The starting cave of the dragon
    private Cave startingCave;
    // The number of squares moved by the dragon
    private int squareMoved;

    public Dragon(String color, int row, int col, Cave startingPosition){
        /*
            * Constructor for the Dragon class
            * @param color: The color of the dragon
            * @param row: The starting row of the dragon
            * @param col: The starting column of the dragon
            * @param startingPosition: The starting position of the dragon
         */
        this.color = color;
        this.setCol(col);
        this.setRow(row);
        this.setStartingCave(startingPosition);
        this.squareMoved = 0;
    }

    public void setStartingCave(Cave startingCave) {
        /*
        * Setter for the startingCave attribute
        * @param startingCave: The starting cave of the dragon
         */
        this.startingCave = startingCave;
    }

    public Cave getStartingCave() {
        /*
        * Getter for the startingCave attribute
        *   @return: The starting cave of the dragon
         */
        return startingCave;
    }

    public void setCol(int col) {
        /*
        * Setter for the col attribute
        * @param col: The column of the dragon
         */
        this.col = col;
    }

    public void setRow(int row) {
        /*
        * Setter for the row attribute
        * @param row: The row of the dragon
         */
        this.row = row;
    }

    public int getCol() {
        /*
        * Getter for the col attribute
        * @return: The column of the dragon
         */
        return col;
    }

    public int getRow() {
        /*
        * Getter for the row attribute
        * @return: The row of the dragon
        *
         */
        return row;
    }

    public String getColor() {
        /*
        * Getter for the color attribute
        * @return: The color of the dragon
         */
        return color;
    }

    public int getSquareMoved() {
        /*
        * Getter for the squareMoved attribute
        * @return: The number of squares moved by the dragon
        *
         */
        return squareMoved;
    }
    public void updateCoordinate(int[] newCoordinate){
        /*
        * Method to update the coordinate of the dragon
        * @param newCoordinate: The new coordinate of the dragon
        *
         */
        this.row = newCoordinate[0];
        this.col = newCoordinate[1];
    }
    public void updateMovedDistance(int distance){
        /*
        * Method to update the number of squares moved by the dragon
        * @param distance: The distance moved by the dragon
         */
        this.squareMoved+= distance;
    }
}
