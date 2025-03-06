package com.example.fierydragon;

public class Testing {

    public boolean testGame(){
        /* Test the Game class */
        Game game = new Game();
        game.setUpGame();
        Board board = game.getBoard();
        DragonCard[] cards = game.getDragonCards();
        Player[] players = game.getPlayers();
        // Test if all players are created
        for (Player player : players) {
            if (player == null) {
                return false;
            }
        }
        // Test if all dragon cards are created
        for (DragonCard card : cards) {
            if (card == null) {
                return false;
            }
        }
        // Test if the board is created
        return board != null;
    }
    public boolean testBoard(){
        /* Test the Board class */
        Board board = new Board(8);
        Position[][] positions = board.getCoordinates();
        VolcanoCard[] volcanoCard = board.getVolcanoCards();

        // Test board coordinating system
        for(VolcanoCard card : volcanoCard){
            for (Square square : card.getSquares()) {
                if (square == null) {
                    return false;
                }
                if (board.getPositionByCoordinates(board.getCoordinatesByPosition(square)[0], board.getCoordinatesByPosition(square)[1]) != square) {
                    return false;
                }
            }
        }

        // Test if all volcano cards are created are filled
        for(VolcanoCard card : volcanoCard){
            if(card == null){
                return false;
            }
        }
        return true;
    }
    public boolean testPlayer(){
        /* Test the Player class */
        Square square = new Square(Type.BAT);
        Cave cave = new Cave(Type.BAT, square);
        Player player = new Player(cave, "Red",1,1);
        Dragon dragon = player.getDragon();
        return dragon != null;
    }
    public boolean testDragon(){
        /* Test the Dragon class */
        Square square = new Square(Type.BAT);
        Cave cave = new Cave(Type.BAT, square);
        Dragon dragon = new Dragon("Red", 1, 1, cave);
        if (dragon.getRow() != 1 || dragon.getCol() != 1 || dragon.getColor() != "Red" || dragon.getStartingCave() != cave) {
            return false;
        }
        return true;
    }
    public boolean testSquare(){
        /* Test the Square class */
        Square square = new Square(Type.BAT);
        if (square.getType() != Type.BAT) {
            return false;
        }
        return true;
    }
    public boolean testCave(){
        /* Test the Cave class */
        Square square = new Square(Type.BAT);
        Cave cave = new Cave(Type.BAT, square);
        if (cave.getSquareOutside() != square || cave.getType() != Type.BAT) {
            return false;
        }
        return true;
    }
    public boolean testDragonCard(){
        /* Test the DragonCard class */
        DragonCard card = new DragonCard(3, Type.BAT);
        if (card.getType() != Type.BAT || card.getAnimalCount() != 3) {
            return false;
        }
        return true;
    }
    public boolean stestFlipAction(){
        /* Test the FlipAction class */
        Square square = new Square(Type.BAT);
        Cave cave = new Cave(Type.BAT, square);
        Player player = new Player(cave, "Red",1,1);
        DragonCard card = new DragonCard(3, Type.BAT);
        FlipAction flipAction = new FlipAction(card);
        flipAction.execute(new Board(8), player);
        return card.isFaceUp();
    }
    public static void main(String[] args) {
        Testing testing = new Testing();
        if(testing.testGame()) {
            System.out.println("Game Test passed");
        } else {
            System.out.println("Game Test failed");
        }
        if (testing.testBoard()) {
            System.out.println("Board Test passed");
        } else {
            System.out.println("Board Test failed");
        }
        if (testing.testPlayer()) {
            System.out.println("Player Test passed");
        } else {
            System.out.println("Player Test failed");
        }
        if (testing.testDragon()) {
            System.out.println("Dragon Test passed");
        } else {
            System.out.println("Dragon Test failed");
        }
        if (testing.testSquare()) {
            System.out.println("Square Test passed");
        } else {
            System.out.println("Square Test failed");
        }
        if (testing.testCave()) {
            System.out.println("Cave Test passed");
        } else {
            System.out.println("Cave Test failed");
        }
        if (testing.testDragonCard()) {
            System.out.println("DragonCard Test passed");
        } else {
            System.out.println("DragonCard Test failed");
        }
        if (testing.stestFlipAction()) {
            System.out.println("FlipAction Test passed");
        } else {
            System.out.println("FlipAction Test failed");
        }

    }
}

