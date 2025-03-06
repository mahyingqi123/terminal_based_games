package com.example.fierydragon;

public abstract class Position {
    /*
    * The Position class, it is an abstract class that represents a position in the game where dragon can stand on
     */

    // The type of the position
    private Type type;

    public void setType(Type type) {
        /* Setter for the type attribute
        * @param type: The type of the position
         */
        this.type = type;
    }

    public Type getType() {
        /* Getter for the type attribute
        * @return: The type of the position
         */
        return type;
    }

}
