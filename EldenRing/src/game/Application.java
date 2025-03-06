package game;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.actions.TravelAction;
import game.classes.*;
import game.environment.*;
import game.items.GoldenRunes;
import game.items.RemembranceOfTheGrafted;
import game.positions.SiteOfLostGrace;
import game.trader.FingerReaderEnia;
import game.positions.SummonSign;
import game.trader.MerchantKale;
import game.utils.ClassesMenu;
import game.utils.FancyMessage;
import game.utils.ResetManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The main class to start the game.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Ying Mah
 *
 */
public class Application {

	public static void main(String[] args) {
		Display display = new Display();
		World world = new World(display);

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new SiteOfLostGrace(),new GustOfWind(),new PuddleOfWater(),new Graveyard(),new Barrack(),new Cage(),new Cliff(), new SummonSign());

		List<String> limgrave = Arrays.asList(
				"......................#.............#..........................+++.........",
				"......................#.............#.......................+++++..........",
				"......................#..___....____#.........................+++++........",
				"......................#...........__#............................++........",
				"......................#_____........#.............................+++......",
				"......................#............_#..............................+++.....",
				"......................######...######......................................",
				"...........................................................................",
				"...........................=...............................................",
				"........++++......................###___###................................",
				"........+++++++...................________#................................",
				"..........+++.....................#___U____................................",
				"............+++...................#_______#................................",
				".............+....................###___###................................",
				"............++......................#___#..................................",
				"..............+...................=........................................",
				"..............++.................................................=.........",
				"..............................................++...........................",
				"..................++++......................+++...............######..##...",
				"#####___######....++...........................+++............#....____....",
				"_____________#.....++++..........................+..............__.....#...",
				"_____________#.....+....++........................++.........._.....__.#...",
				"_____________#.........+..+.....................+++...........###..__###...",
				"_____________#.............++..............................................");
		GameMap Limgrave = new GameMap(groundFactory, limgrave);
		world.addGameMap(Limgrave);

		List<String> stormveilCastle = Arrays.asList(
				"...........................................................................",
				"..................<...............<........................................",
				"...........................................................................",
				"##############################################...##########################",
				"............................#................#.......B..............B......",
				".....B...............B......#................#.............................",
				"...............................<.........<.................................",
				".....B...............B......#................#.......B..............B......",
				"............................#................#.............................",
				"#####################..#############...############.####..#########...#####",
				"...............#++++++++++++#................#++++++++++++#................",
				"...............#++++++++++++...<.........<...#++++++++++++#................",
				"...............#++++++++++++..................++++++++++++#................",
				"...............#++++++++++++#................#++++++++++++#................",
				"#####...##########.....#############...#############..#############...#####",
				".._______........................B......B........................B.....B...",
				"_____..._..____....&&........<&.............<..............................",
				".........____......&&......................................................",
				"...._______..................<&.............<....................<.....<...",
				"#####....##...###..#####...##########___###############......##.....####...",
				"+++++++++++++++++++++++++++#...................#+++++++++++++++++++++++++++",
				"+++++++++++++++++++++++++++....................#+++++++++++++++++++++++++++",
				"+++++++++++++++++++++++++++#....................+++++++++++++++++++++++++++",
				"+++++++++++++++++++++++++++#...................#+++++++++++++++++++++++++++");
		GameMap StormveilCastle = new GameMap(groundFactory, stormveilCastle);
		world.addGameMap(StormveilCastle);

		List<String> roundtableHold = Arrays.asList(
				"##################",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"########___#######");
		GameMap RoundtableHold = new GameMap(groundFactory, roundtableHold);
		world.addGameMap(RoundtableHold);

		List<String> bossRoom = Arrays.asList(
				"+++++++++++++++++++++++++",
				".........................",
				"..=......................",
				".........................",
				".........................",
				".........................",
				".........................",
				".........................",
				"+++++++++++++++++++++++++");
		GameMap BossRoom = new GameMap(groundFactory, bossRoom);
		world.addGameMap(BossRoom);


		// BEHOLD, ELDEN RING
		for (String line : FancyMessage.ELDEN_RING.split("\n")) {
			new Display().println(line);
			try {
				Thread.sleep(200);
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}

		//GoldenFogDoor
		//Limgrave to Stormveil Castle
		GoldenFogDoor limgraveDoor1 = new GoldenFogDoor(new TravelAction(StormveilCastle.at(38,19),"Stormveil Castle"));
		Limgrave.at(33,9).setGround(limgraveDoor1);

		//Stormveil Castle to Limgrave
		GoldenFogDoor stormveilDoor1 = new GoldenFogDoor(new TravelAction(Limgrave.at(33,9),"Limgrave"));
		StormveilCastle.at(38,19).setGround(stormveilDoor1);

		//Limgrave to Roundtable Hold
		GoldenFogDoor limgraveDoor2 = new GoldenFogDoor(new TravelAction(RoundtableHold.at(9,10),"Roundtable Hold"));
		Limgrave.at(37,8).setGround(limgraveDoor2);

		//Roundtable Hold to Limgrave
		GoldenFogDoor roundtableDoor1 = new GoldenFogDoor(new TravelAction(Limgrave.at(37,8),"Limgrave"));
		RoundtableHold.at(9,10).setGround(roundtableDoor1);

		//Stormveil Castle to BossRoom
		GoldenFogDoor stormveilDoor2 = new GoldenFogDoor(new TravelAction(BossRoom.at(12,6),"Boss Room"));
		StormveilCastle.at(38,23).setGround(stormveilDoor2);

		//add Golden Runes
		Limgrave.at(38,10).addItem(new GoldenRunes());             //testing
		StormveilCastle.at(4,10).addItem(new GoldenRunes());      //testing

		//add RemembranceOfTheGrafted in boss room
		Limgrave.at(38,9).addItem(new RemembranceOfTheGrafted());  //testing

		// HINT: what does it mean to prefer composition to inheritance?
		Player player = new Player(ClassesMenu.run());
		ResetManager.getInstance().setPlayer(player);
		ResetManager.getInstance().setReviveLocation(Limgrave.at(38,11));
		world.addPlayer(new MerchantKale(), Limgrave.at(40,12));
		world.addPlayer(new FingerReaderEnia(), RoundtableHold.at(9,4));    //testing
		world.addPlayer(player, Limgrave.at(36, 10));
		world.run();
	}
}
