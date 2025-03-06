package game.utils;

import edu.monash.fit2099.engine.displays.Display;
import game.classes.*;

import java.util.ArrayList;

/**
 * A classes menu that allows user to choose classes for the character
 *  Created by:
 *  @author Ying Mah
 *
 *
 */
public class ClassesMenu {
    /**
     * Display a list of classes and allowss user to choose from one of them
     * @return the classes that character chose
     */
    public static Classes run(){
        Display display = new Display();
        ArrayList<Classes> charactersList = new ArrayList<>();
        charactersList.add(new Samurai());
        charactersList.add(new Bandit());
        charactersList.add(new Wretch());
        charactersList.add(new Astrologer());
        display.println("Choose a character to start the game");
        int choice;
        while(true) {
            for (int i = 1; i < charactersList.size() + 1; i++) {
                display.println(i + ")\t" + charactersList.get(i - 1).getName());
            }
            choice = Character.getNumericValue(display.readChar());
            if (choice<=charactersList.size() + 1 && choice >=1){
                break;
            }
            display.println("Invalid choice, choose again");
        }
        return charactersList.get(choice-1);
    }

    /**
     * Randomly assign a class to the user
     * @return a randomly assigned class.
     */
    public static Classes randomClass(){
        ArrayList<Classes> charactersList = new ArrayList<>();
        charactersList.add(new Samurai());
        charactersList.add(new Bandit());
        charactersList.add(new Wretch());
        charactersList.add(new Astrologer());
        return charactersList.get(RandomNumberGenerator.getRandomInt(4));
    }

}
