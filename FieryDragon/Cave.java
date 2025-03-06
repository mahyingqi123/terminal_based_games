package com.example.fierydragon;

public class Cave extends Position implements Comparable{
    /* The Cave class*/

    // The immediate square outside the cave
    private Position squareOutside;
    public Cave(Type caveType, Position squareOutside){
        /* Constructor for the Cave class
        * @param caveType: The type of the cave
        * @param squareOutside: The immediate square outside the cave
        *
        */
        this.setType(caveType);
        this.setSquareOutside(squareOutside);
    }

    public void setSquareOutside(Position squareOutside) {
        /* Setter for the squareOutside attribute
        * @param squareOutside: The immediate square outside the cave
         */
        this.squareOutside = squareOutside;
    }

    public Position getSquareOutside() {
        /* Getter for the squareOutside attribute
        * @return: The immediate square outside the cave
         */
        return this.squareOutside;
    }
}
