package com.example.fierydragon;

public class Player {
    /*  The Player class, it represents the player in the game

     */
    // The dragon of the player
    private Dragon dragon;

    public Player(Cave startingPosition, String color, int row, int col){
        /* Constructor for the Player class
        * @param startingPosition: The starting position of the player
        * @param color: The color of the dragon
        * @param row: The starting row of the dragon
        * @param col: The starting column of the dragon
         */
        this.createDragon(color, row, col,startingPosition);
    }



    private void createDragon(String color, int row, int col, Cave startingPosition){
        /* Method to create the dragon of the player
        * @param color: The color of the dragon
        * @param row: The starting row of the dragon
        * @param col: The starting column of the dragon
        * @param startingPosition: The starting position of the player
        *
         */
        this.dragon = new Dragon(color, row, col, startingPosition);
    }

    public Dragon getDragon() {
        /* Getter for the dragon attribute
        * @return: The dragon of the player
         */
        return dragon;
    }



}
