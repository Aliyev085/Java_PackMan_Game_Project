import java.awt.Color;

import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.SwingUtilities;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.program.GraphicsProgram;

public class Game extends GraphicsProgram{
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("score.txt"));
			GlobalVariables.highScore = Integer.parseInt(br.readLine());
			br.close();
		} catch (Exception e) {}
		
		GlobalVariables.pacman = new Pacman();
		addKeyListeners();
		setSize(GlobalVariables.APP_WIDTH, GlobalVariables.APP_HEIGHT + GlobalVariables.barSize);
		setBackground(Color.BLACK);	
		
		GlobalVariables.game = this;
	
		add(new GImage("Map.png"), GlobalVariables.locationOfMap, 0);
		add(GlobalVariables.map, GlobalVariables.locationOfMap, 0);

		score.setFont(GlobalVariables.standardFont);
		score.setColor(Color.white);
		
		lives.setFont(GlobalVariables.standardFont);
		lives.setColor(Color.white);
		
		pauseL.setFont(GlobalVariables.standardFont);
		pauseL.setColor(Color.yellow);
		
		add(score);
		add(lives);
		
		GlobalVariables.pause = true;
		callNewLevel();
	}
	
	@Override
	public void run() {
		while (GlobalVariables.gameIsOn)
		{
			if(!GlobalVariables.pause)
			if (GlobalVariables.mapOfPoint.getElementCount() == 0)
			{
				remove(GlobalVariables.pacman);
				GlobalVariables.pacman = new Pacman();
				callNewLevel();
				add(GlobalVariables.pacman, GlobalVariables.APP_WIDTH / 2 - GlobalVariables.pacman.getWidth() / 2, 395);
				new Thread(GlobalVariables.pacman).start();
			}
			
			pause(1);
		}
		
		GLabel gameOver = new GLabel("GAME OVER!!");
		gameOver.setFont(GlobalVariables.standardFont);
		add(gameOver, GlobalVariables.APP_WIDTH / 2 - gameOver.getWidth() / 2, 315);
		gameOver.setColor(Color.RED);
		pause(1000);
		removeAll();
		remove(score);
		remove(lives);
		printScore();
		addMouseListeners();
	}
	
	private void printScore() {
		
		GLabel score = new GLabel("Score: " + GlobalVariables.currentScore);
		GLabel highScore = new GLabel("Previous High Score: " + GlobalVariables.highScore);
		GLabel comment = new GLabel("(click anywhere to go to menu)");
		GlobalVariables.highScore = Math.max(GlobalVariables.highScore, GlobalVariables.currentScore);
		
		score.setColor(Color.WHITE);
		highScore.setColor(Color.WHITE);
		comment.setColor(Color.WHITE);
		
		score.setFont(GlobalVariables.standardFont);
		highScore.setFont(GlobalVariables.standardFont);
		comment.setFont(GlobalVariables.standardFont);
		
		add(score, GlobalVariables.APP_WIDTH / 2 - score.getWidth() / 2, GlobalVariables.APP_HEIGHT / 2);
		add(highScore, GlobalVariables.APP_WIDTH / 2 - highScore.getWidth() / 2, GlobalVariables.APP_HEIGHT / 2 + score.getHeight() * 2);
		add(comment, GlobalVariables.APP_WIDTH / 2 - highScore.getWidth() / 2, GlobalVariables.APP_HEIGHT / 2 + score.getHeight() * 4);
		add(new GImage("Pacman.png"), GlobalVariables.APP_WIDTH / 2 - new GImage("Pacman.png").getWidth() / 2, 0);
		
		try {
			FileWriter fw = new FileWriter("score.txt");
			fw.write(Integer.toString(GlobalVariables.highScore));
			fw.close();
		} catch (Exception e) {}
		
	}

	private void callNewLevel() {
		
		GlobalVariables.level++;
		GlobalVariables.pause = true;
		remove(GlobalVariables.pacman);
		add(GlobalVariables.pacman, GlobalVariables.APP_WIDTH / 2 - GlobalVariables.pacman.getWidth() / 2, 393);
		
		updateScore();
		
		try {remove(GlobalVariables.mapOfPoint);} catch (Exception e) {}
		GlobalVariables.mapOfPoint = new MapOfPoint();
		add(GlobalVariables.mapOfPoint, GlobalVariables.locationOfMap, 0);
		
		remove(GlobalVariables.blinky);
		remove(GlobalVariables.clyde);
		remove(GlobalVariables.inkey);
		remove(GlobalVariables.pinky);

		GlobalVariables.blinky.notAGhost();
		GlobalVariables.pinky.notAGhost();
		GlobalVariables.inkey.notAGhost();
		GlobalVariables.clyde.notAGhost();
		
		GlobalVariables.pacman.sendToFront();
		add(GlobalVariables.blinky, GlobalVariables.APP_WIDTH / 2 - GlobalVariables.pacman.getWidth() / 2, 190);
		add(GlobalVariables.pinky, GlobalVariables.APP_WIDTH / 2 - GlobalVariables.pacman.getWidth() / 2, 240);
		add(GlobalVariables.inkey, GlobalVariables.APP_WIDTH / 2 - GlobalVariables.pacman.getWidth() / 2 - GlobalVariables.pacmanWidth, 240);
		add(GlobalVariables.clyde, GlobalVariables.APP_WIDTH / 2 - GlobalVariables.pacman.getWidth() / 2 + GlobalVariables.pacmanWidth, 240);
		if (!GlobalVariables.mute)
		try {
			Sound.playSound("./sound/pacman_beginning.wav");
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		GLabel level = new GLabel("LEVEL " + GlobalVariables.level);
		level.setColor(Color.white);
		level.setFont(GlobalVariables.standardFont);
		add(level, GlobalVariables.APP_WIDTH / 2 - level.getWidth() / 2, 310);
		//fruit.sendToFront();
		
		pause(2000);
		remove(level);
		
		GLabel get_ready = new GLabel("GET READY!!!");
		get_ready.setColor(Color.white);
		get_ready.setFont(GlobalVariables.standardFont);
		add(get_ready, GlobalVariables.APP_WIDTH / 2 - get_ready.getWidth() / 2, 310);
		
		pause(2000);
		remove(get_ready);
		GlobalVariables.pause = false;
		if (GlobalVariables.level == 1)
		{
			new Thread(GlobalVariables.pacman).start();
			new Thread(GlobalVariables.blinky).start();
			new Thread(GlobalVariables.clyde).start();
			new Thread(GlobalVariables.pinky).start();
			new Thread(GlobalVariables.inkey).start();
		}
		else
		{
			GlobalVariables.blinky.stop();
			GlobalVariables.pinky.stop();
			GlobalVariables.inkey.stop();
			GlobalVariables.clyde.stop();
			
			new Thread(GlobalVariables.blinky).start();
			new Thread(GlobalVariables.clyde).start();
			new Thread(GlobalVariables.pinky).start();
			new Thread(GlobalVariables.inkey).start();
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Window window = SwingUtilities.getWindowAncestor(e.getComponent());
		window.dispose();
		new Menu().start();
	}

	private GLabel pauseL = new GLabel("PAUSED");
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			GlobalVariables.pacman.changeDirectionInFuture(Direction.Up);
			break;
		
		case KeyEvent.VK_DOWN:
			GlobalVariables.pacman.changeDirectionInFuture(Direction.Down);
			break;
			
		case KeyEvent.VK_LEFT:
			GlobalVariables.pacman.changeDirectionInFuture(Direction.Left);
			break;
			
		case KeyEvent.VK_RIGHT:
			GlobalVariables.pacman.changeDirectionInFuture(Direction.Right);
			break;
			
		case KeyEvent.VK_P:
			{
				GlobalVariables.pause = !GlobalVariables.pause;
				
				if (GlobalVariables.pause)
				add(pauseL, GlobalVariables.APP_WIDTH / 2 - pauseL.getWidth() / 2, 310);
				else remove(pauseL);
			}
			break;
			
		case KeyEvent.VK_M:
		{
			GlobalVariables.mute = !GlobalVariables.mute;
		}
		break;
			
		case KeyEvent.VK_S:
		{
			try {
				FileWriter fw = new FileWriter("save.txt");
				fw.write(Integer.toString(GlobalVariables.currentScore) + " " + Integer.toString(GlobalVariables.pacman.getLives()));
				fw.close();
			} catch (Exception ee) {}
		}
		default:
			break;
		}
	}
	
	public static void main(String[] args) {
		new Game().start();
	}
	
	private GLabel score = new GLabel("");
	private GLabel lives = new GLabel("");
	public void updateScore(){
		remove(score);
		remove(lives);
		
		score.setLabel("Score: " + GlobalVariables.currentScore);
		lives.setLabel("Lives: " + GlobalVariables.pacman.getLives());
		
		add(score, 0, GlobalVariables.APP_HEIGHT - score.getHeight());
		add(lives, lives.getWidth() + 100, GlobalVariables.APP_HEIGHT - score.getHeight());
	}

	public void redo() {
		if (!GlobalVariables.mute)
		try {
			Sound.playSound("./sound/pacman_death.wav");
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GlobalVariables.pacman.decreaseLives();
		updateScore();
		
		if (GlobalVariables.gameIsOn)
		{
			remove(GlobalVariables.pacman);
			remove(GlobalVariables.blinky);
			remove(GlobalVariables.clyde);
			remove(GlobalVariables.inkey);
			remove(GlobalVariables.pinky);
			
			GlobalVariables.pause = true;
			
			GlobalVariables.blinky.stop();
			GlobalVariables.pinky.stop();
			GlobalVariables.inkey.stop();
			GlobalVariables.clyde.stop();
			
			GlobalVariables.blinky.notAGhost();
			GlobalVariables.pinky.notAGhost();
			GlobalVariables.inkey.notAGhost();
			GlobalVariables.clyde.notAGhost();
			
			add(GlobalVariables.pacman, GlobalVariables.APP_WIDTH / 2 - GlobalVariables.pacman.getWidth() / 2, 393);
			add(GlobalVariables.blinky, GlobalVariables.APP_WIDTH / 2 - GlobalVariables.pacman.getWidth() / 2, 190);
			add(GlobalVariables.pinky, GlobalVariables.APP_WIDTH / 2 - GlobalVariables.pacman.getWidth() / 2, 240);
			add(GlobalVariables.inkey, GlobalVariables.APP_WIDTH / 2 - GlobalVariables.pacman.getWidth() / 2 - GlobalVariables.pacmanWidth, 240);
			add(GlobalVariables.clyde, GlobalVariables.APP_WIDTH / 2 - GlobalVariables.pacman.getWidth() / 2 + GlobalVariables.pacmanWidth, 240);

			
			GLabel get_ready = new GLabel("GET READY!!!");
			get_ready.setColor(Color.white);
			get_ready.setFont(GlobalVariables.standardFont);
			add(get_ready, GlobalVariables.APP_WIDTH / 2 - get_ready.getWidth() / 2, 310);
			
			pause(2000);
			remove(get_ready);
			GlobalVariables.pause = false;
			new Thread(GlobalVariables.blinky).start();
			new Thread(GlobalVariables.clyde).start();
			new Thread(GlobalVariables.pinky).start();
			new Thread(GlobalVariables.inkey).start();
		}
	}

	public void setGhosts() {
		if (!GlobalVariables.mute)
		try {
			Sound.playSound("./sound/pacman_intermission.wav");
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GlobalVariables.blinky.setGhost();
		GlobalVariables.pinky.setGhost();
		GlobalVariables.clyde.setGhost();
		GlobalVariables.inkey.setGhost();
	}

	public void eatGhost(double x, double y) {
		if (!GlobalVariables.mute)
		try {
			Sound.playSound("./sound/pacman_eatghost.wav");
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GlobalVariables.pause = true;
		GLabel label = new GLabel(Integer.toString((1 << GlobalVariables.ghostEaten) * 100));
		label.setFont(GlobalVariables.standardFont);
		label.setColor(Color.WHITE);
		add(label, x, y);
		label.sendToFront();
		GlobalVariables.currentScore += (1 << GlobalVariables.ghostEaten) * 100;
		updateScore();
		pause(1000);
		remove(label);
		GlobalVariables.pause = false;
	}
}
