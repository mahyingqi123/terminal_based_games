package com.example.fierydragon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Game {
    /*
     * The Game class, it represents the game
     */
    // The width of the board
    private int width = 9;
    // The height of the board
    private int height = 9;
    // The number of players
    private int playerCount = 4;
    // The board of the game
    private Board board;
    // The players of the game
    private Player[] players ;
    // The current player playing
    public Player currentPlayer;
    // The index of the current player
    private int currentPlayerIndex ;
    // Indicate if the game is over
    private boolean game_over = false;
    // The dragon cards of the game
    private DragonCard[] dragonCards;
    // The winner of the game
    private Player winner;

    public Game( ) {
        /*
         * Constructor for the Game class
         */
    }

    public void setUpGame(){
        /*
         * Set up all the components of the game
         */
        this.createBoard();
        this.createDragonCards();
        this.addPlayers();
        this.currentPlayer = this.players[0];
        this.currentPlayerIndex = 0;
    }
    public void setPlayerCount(int playerCount) {
        /*
            * Setter for the playerCount attribute
            * @param playerCount: The number of players
         */
        this.playerCount = playerCount;
    }

    public void gameEnd(Player winner){
        /*
         * Method to end the game
         * @param winner: The winner of the game
         */
        this.game_over = true;
        this.winner = winner;
        System.out.println("Game Over! The winner is " + winner.getDragon().getColor());
    }

    private int getPlayerCount() {
        /*
         * Getter for the playerCount attribute
         * @return: The number of players
         */
        return this.playerCount;
    }

    private void createBoard() {
        /*
         * Create the board of the game
         */
        this.board = new Board(8);
    }

    public Player getCurrentPlayer() {
        /*
         * Getter for the currentPlayer attribute
         * @return: The current player playing
         */
        return currentPlayer;
    }

    public Player[] getPlayers() {
        /*
         * Getter for the players attribute
         * @return: The players of the game
         */
        return players;
    }

    private void createDragonCards() {
        /*
         * Create the dragon cards of the game
         */
        ArrayList<DragonCard> allCards = new ArrayList<>();
        ArrayList<Type> animals = new ArrayList<>(Arrays.asList(Type.BAT, Type.BABY_DRAGON, Type.SPIDER, Type.SALAMANDER));
        for (Type animal : animals) {
            for (int j = 1; j <=3; j++) {
                allCards.add(new DragonCard(j, animal));
            }
        }
        for (int i = 0;i<2;i++){
            for(int j = 1;j<=2;j++){
                allCards.add(new DragonCard(j,Type.PIRATE_DRAGON));
            }
        }
        Collections.shuffle(allCards);
        this.dragonCards = new DragonCard[allCards.size()];
        allCards.toArray(this.dragonCards);
    }

    private void addPlayers() {
        /*
         * Add players to the game
         */
        this.players = new Player[this.getPlayerCount()];
        Cave[] caves = this.board.getCaves();
        String[] colors = new String[]{"red", "blue", "green", "yellow"};

        for(int i=0;i<this.getPlayerCount();i++){
            int[] coordinates = this.board.getCoordinatesByPosition(caves[i]);

            this.players[i] = new Player(caves[i], colors[i], coordinates[0], coordinates[1]);
        }
    }

    public Board getBoard() {
        /*
         * Getter for the board attribute
         * @return: The board of the game
         */
        return board;
    }

    public DragonCard[] getDragonCards() {
        /*
         * Getter for the dragonCards attribute
         * @return: The dragon cards of the game
         */
        return dragonCards;
    }

    public void setAction(FlipAction action){
        /*
         * Set the action of the and execute it
         * @param action: The action to set
         */
        action.execute(this.board, this.currentPlayer);
        if (this.currentPlayer.getDragon().getSquareMoved() >0 && this.board.dragonInCave(this.currentPlayer.getDragon())){
            this.gameEnd(this.currentPlayer);
        }
        if(action.changePlayer()){
            this.nextPlayerTurn();
            System.out.println("Next player: " + this.currentPlayer.getDragon().getColor());
        }
    }

    public void nextPlayerTurn(){
        /*
         * Method to change the turn of the player
         */
        this.currentPlayerIndex = (this.currentPlayerIndex + 1) % this.playerCount;
        this.currentPlayer = this.players[this.currentPlayerIndex];
    }


}
