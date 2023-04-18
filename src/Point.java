import java.awt.Color;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import acm.graphics.GRect;

public class Point extends GRect implements Runnable{

	public Point() {
		super(3, 3);
		super.setFilled(true);
		super.setFillColor(Color.white);
		super.setColor(Color.white);
	}

	@Override
	public void run() {
		while (true)
		{
			if (check())
			{
				if (!GlobalVariables.mute)
				try {
					Sound.playSound("./sound/pacman_chomp.wav");
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				GlobalVariables.mapOfPoint.remove(this);
				GlobalVariables.currentScore += 10;
				GlobalVariables.game.updateScore();
				break;
			}
			
			pause(100);
		}
	}

	private boolean check() {
		return (GlobalVariables.pacman.getX() <= this.getX() + GlobalVariables.locationOfMap &&
				GlobalVariables.pacman.getX() + GlobalVariables.pacman.getWidth() >= this.getX() + GlobalVariables.locationOfMap) &&
				(GlobalVariables.pacman.getY() <= this.getY() &&
				GlobalVariables.pacman.getY() + GlobalVariables.pacman.getHeight() >= this.getY());
	}
	
	

}
