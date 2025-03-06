package com.example.fierydragon;

public class Square extends Position implements Comparable{
    /* The Square class, it is a position in board and can be compared
    * hence it implements the Comparable interface
    * and extends the Position class
    */

    public Square(Type type){
        /* Constructor for the Square class
        * @param type: The type of the square
        *
         */
        this.setType(type);
    }





}
