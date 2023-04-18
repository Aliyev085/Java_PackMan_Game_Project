import java.awt.Color;

import acm.graphics.GOval;

public class SpecialPoints extends GOval implements Runnable{

	public SpecialPoints() {
		super(1, 1);
		super.setFilled(true);
		super.setFillColor(Color.white);
		super.setColor(Color.white);
	}

	@Override
	public void run() {
		double dz = 1;
		double k = 0.01;
		while (true)
		{
			super.setSize(dz, dz);
			
			if (dz > 5)
				k = -0.01;
			
			if (dz < 1)
				k = 0.01;
			
			dz += k;
			
			if (check())
			{
				GlobalVariables.mapOfPoint.remove(this);
				GlobalVariables.currentScore += 40;
				GlobalVariables.game.updateScore();
				GlobalVariables.game.setGhosts();
				break;
			}
			
			pause(1);
		}
	}

	private boolean check() {
		return (GlobalVariables.pacman.getX() <= this.getX() + GlobalVariables.locationOfMap &&
				GlobalVariables.pacman.getX() + GlobalVariables.pacman.getWidth() >= this.getX() + GlobalVariables.locationOfMap) &&
				(GlobalVariables.pacman.getY() <= this.getY() &&
				GlobalVariables.pacman.getY() + GlobalVariables.pacman.getHeight() >= this.getY());
	}
	
	

}
