package com.example.fierydragon;

public class FlipAction {
    /* The FlipAction class, it represents the action of flipping the dragon card
     */

    // The dragon card to flip
    private DragonCard dragonCard;
    // The player that flips the card
    private Player player;
    // Indicate if the player turn should be changed
    private boolean changePlayer = false;

    public FlipAction(DragonCard dragonCard){
        /*
         * Constructor for the FlipAction class
         * @param dragonCard: The dragon card to flip
         *
          */
        this.dragonCard = dragonCard;
    }

    public void execute(Board board, Player player){
        /*
         * Method to execute the flip action
         * @param board: The board of the game
         * @param player: The player that flips the card
         *
          */
        this.player = player;
        this.dragonCard.flip();
        int dragonRow = this.player.getDragon().getRow();
        int dragonCol = this.player.getDragon().getCol();
        Position position = board.getPositionByCoordinates(dragonRow, dragonCol);
        int[] newCoordinates = new int[] {dragonRow, dragonCol};
        int steps = this.dragonCard.getAnimalCount();
        if(this.dragonCard.getType() == position.getType()){
            System.out.println("Dragon card type: " + this.dragonCard.getType() + " Dragon card steps: " + this.dragonCard.getAnimalCount());
            steps = this.dragonCard.getAnimalCount();
            newCoordinates = board.moveDragon(this.player.getDragon(), steps);
        }else if(this.dragonCard.getType() == Type.PIRATE_DRAGON){
            System.out.println("Dragon card type: " + this.dragonCard.getType() + " Dragon card steps: " + this.dragonCard.getAnimalCount());
            steps = -this.dragonCard.getAnimalCount();
            newCoordinates = board.moveDragon(this.player.getDragon(), steps);
        }else{
            this.changePlayer = true;
        }

        if (newCoordinates[1] != dragonCol || newCoordinates[0] != dragonRow){
            this.player.getDragon().updateMovedDistance(steps);
        }else{
            this.changePlayer = true;
        }
        this.player.getDragon().updateCoordinate(newCoordinates);
    }

    public boolean changePlayer(){
        /*
         * Method to check if the player turn should be changed
         * @return: True if the player turn should be changed, False otherwise
         *
          */
        return this.changePlayer;
    }

}
