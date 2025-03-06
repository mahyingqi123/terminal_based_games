package com.example.fierydragon;

public class DragonCard implements Comparable{
    /* The DragonCard class, it represents the dragon card in the game
     */

    // The number of animals in the card
    private int animalCount;

    // Indicate if the card is face up
    private boolean isFaceUp = false;
    // The type of the card
    private Type type;
    DragonCard(int animalCount, Type type){
        /*
            * Constructor for the DragonCard class
            * @param animalCount: The number of animals in the card
            * @param type: The type of the card
            *
         */
        this.setAnimalCount(animalCount);
        this.setType(type);
    }

    public void setType(Type type) {
        /*
        * Setter for the type attribute
        * @param type: The type of the card
         */
        this.type = type;
    }

    @Override
    public Type getType() {
        /*
        * Getter for the type attribute
        * @return: The type of the card
         */
        return type;
    }

    public void flip(){
        /*
        * Method to flip the card
        *
         */
        this.isFaceUp = !this.isFaceUp;
    }
    public boolean isFaceUp(){
        /*
        * Method to check if the card is face up
         */
        return this.isFaceUp;
    }
    private void setAnimalCount(int animalCount) {
        /*
        * Setter for the animalCount attribute
        * @param animalCount: The number of animals in the card
        *
         */
        this.animalCount = animalCount;
    }

    public int getAnimalCount() {
        /*
        * Getter for the animalCount attribute
         */
        return this.animalCount;
    }



}
