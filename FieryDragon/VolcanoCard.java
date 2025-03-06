package com.example.fierydragon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class VolcanoCard  {
    /* The VolcanoCard class, which represents a card in the game. Each card has 3 squares, each with a different type.
    */

    // The number of squares in a card
    private static final int NUM_SQUARES = 3;

    // The array of squares in the card
    private Square[] squares;


    public VolcanoCard(Type[] squareConfig) {
        /* Constructor for the VolcanoCard class
        * @param squareConfig: The configuration of the square types in the card
         */
        this.createSquare(squareConfig);
    }

    public Square[] getSquares() {
        /* Getter for the squares attribute
        * @return: The squares in the card
         */
        return squares;
    }

    private void createSquare(Type[] squareConfig) {
        /* Create the squares in the card
        * @param squareConfig: The configuration of the square types in the card
        * @return: None
         */
        this.squares = new Square[NUM_SQUARES];

        Type[] actualSquareConfig = squareConfig.clone();
        // Randomly reverse the square configuration, replicate flipping of volcano card
        if(Math.random() < 0.5){
            reverse(actualSquareConfig);
        }

        // Create the squares
        for (int i = 0; i < NUM_SQUARES; i++) {
            // Store the current square
            this.squares[i] = new Square(actualSquareConfig[i]);

        }
    }

    private static void reverse(Type[] array) {
        /* Reverse the elements in an array
        * @param array: The array to be reversed
        * @return: None
         */
        int start = 0, end = array.length - 1;
        while (start < end) {
            Type temp = array[start];
            array[start] = array[end];
            array[end] = temp;
            start++;
            end--;
        }
    }

    public Square getSquare(int index) {
        /* Get a square in the card based on index
        * @param index: The index of the square
        * @return: The square at the given index
         */
        return this.squares[index];
    }





}
