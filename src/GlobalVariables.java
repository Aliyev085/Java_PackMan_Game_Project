import java.awt.Font;

import acm.graphics.GImage;
import acm.util.RandomGenerator;

public class GlobalVariables {
	public static final int APP_WIDTH = 600;
	public static final int APP_HEIGHT = 600;
	public static final Font smallFont = new Font("menuFont", Font.LAYOUT_NO_START_CONTEXT, 20);
	public static final Font standardFont = new Font("menuFont", Font.LAYOUT_NO_START_CONTEXT, 25);
	public static final double pacmanSpeed = 5;
	public static final double enemySpeed = 10;
	public static final double ghostSpeed = 10;
	public static final Map map = new Map();
	public static final double pacmanWidth = 22;
	public static final double pacmanHeight = 25;
	public static final int barSize = 40;
	public static final double locationOfMap = GlobalVariables.APP_WIDTH / 2 - new GImage("Map.png").getWidth() / 2;
	public static final RandomGenerator rgen = new RandomGenerator();
	
	public static Pacman pacman;
	public static Enemy inkey = new Enemy("Inkey Steps.gif");
	public static Enemy clyde = new Enemy("Clyde Steps.gif");
	public static Enemy pinky = new Enemy("Pinky Steps.gif");
	public static Enemy blinky = new Enemy("Blinky Steps.gif");
	public static Enemy[] enemies = new Enemy[4];
	public static MapOfPoint mapOfPoint;
	public static Menu menu;
	public static Game game;
	public static boolean gameIsOn = true;
	public static int currentScore = 0;
	public static int highScore = 0;
	public static int level = 0;
	public static boolean pause = false;
	public static int ghostEaten = 0;
	public static boolean mute = false;
}
