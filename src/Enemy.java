import java.awt.Color;

import acm.graphics.GCompound;
import acm.graphics.GImage;
import acm.graphics.GObject;

public class Enemy extends GCompound implements Runnable {

	private Direction direction = Direction.Stop, last = Direction.Stop;
	private GImage image, eyes, hiddenImage;
	private boolean stop = false;
	private boolean isGhost = false;
	private long time;
	private boolean isGoHome = false;

	public Enemy(final String picture) {
		image = new GImage(picture);
		hiddenImage = new GImage(image.getImage());
		image.setSize(GlobalVariables.pacmanWidth, GlobalVariables.pacmanHeight);
		hiddenImage.setSize(GlobalVariables.pacmanWidth, GlobalVariables.pacmanHeight);
		add(image);
		eyes = new GImage("Eyes right 1.png");
		add(eyes, 5, 5);
	}

	@Override
	public void run() {
		boolean[] bool;
		
		if (this.equals(GlobalVariables.pinky))
			Pause(1);
		else if (this.equals(GlobalVariables.inkey))
			Pause(2);
		else if (this.equals(GlobalVariables.clyde))
			Pause(3);
		
		while (GlobalVariables.gameIsOn && !stop) {
			bool = isPossible();
			if (bool[0] || bool[1] || bool[2] || bool[3])
				tryChangeDirection(bool);
			
			if (System.currentTimeMillis() - time > 10000 && isGhost)
				{
					isGhost = false;
					eyes.setVisible(true);
					image.setImage(hiddenImage.getImage());
					image.setSize(GlobalVariables.pacmanWidth, GlobalVariables.pacmanHeight);
					GlobalVariables.ghostEaten = 0;
				}
			
			if (!GlobalVariables.pause)
			if (isPossibleToGo()) {
				switch (direction) {
				case Down:
					this.move(0, 1);
					break;
				case Up:
					this.move(0, -1);
					break;
				case Right:
					this.move(1, 0);
					break;
				case Left:
					this.move(-1, 0);
					break;
				default:
					break;
				}

				if (this.getX() > GlobalVariables.APP_WIDTH)
					this.setLocation(0, this.getY());
				else if (this.getX() < -this.getWidth())
					this.setLocation(GlobalVariables.APP_WIDTH, this.getY());
			} else direction = Direction.Stop;

			pause((isGhost ? GlobalVariables.enemySpeed : GlobalVariables.ghostSpeed));
			
			if (GlobalVariables.pacman.getBounds().intersects(this.getBounds()))
			{
				if (isGhost)
				{
					image.setVisible(false);
					image.setImage(hiddenImage.getImage());
					image.setSize(GlobalVariables.pacmanWidth, GlobalVariables.pacmanHeight);
					eyes.setVisible(true);
					GlobalVariables.ghostEaten++;
					GlobalVariables.game.eatGhost(this.getX(), this.getY());
					goHome();
				}
				else
				{
					GlobalVariables.game.remove(GlobalVariables.pacman);
					GlobalVariables.game.redo();
				}
			}
		}
		
		stop = false;
	}

	private void goHome() {		
		isGoHome = true;
		this.setLocation(250, 220);
		image.setVisible(true);
		image.setImage(hiddenImage.getImage());
		image.setSize(GlobalVariables.pacmanWidth, GlobalVariables.pacmanHeight);
		
		for (int i = 0; i < 50; i++)
		{
			this.move(0, -1);
			pause(10);
		}
		
		isGhost = false;
		isGoHome = false;
		return;
		
	}

	private boolean isPossibleToGo() {
		switch (direction) {
		case Up:
			return GlobalVariables.map.getElementAt((int) (this.getX() - GlobalVariables.map.getX() + 1),
					((int) (this.getY() - 1))) == null
					&& GlobalVariables.map.getElementAt(
							(int) (this.getX() + this.getWidth() / 2 - 0 - GlobalVariables.map.getX()),
							(int) (this.getY() - 1)) == null
					&& GlobalVariables.map.getElementAt(
							(int) (this.getX() + this.getWidth() - 1 - 1 - GlobalVariables.map.getX()),
							(int) (this.getY() - 1)) == null;
		case Down:
			return GlobalVariables.map.getElementAt((int) (this.getX() - GlobalVariables.map.getX() + 1),
					(int) (this.getY() + this.getHeight())) == null
					&& GlobalVariables.map.getElementAt(
							(int) (this.getX() + this.getWidth() / 2 - 0 - GlobalVariables.map.getX()),
							(int) (this.getY() + this.getHeight())) == null
					&& GlobalVariables.map.getElementAt(
							(int) (this.getX() + this.getWidth() - 1 - 1 - GlobalVariables.map.getX()),
							(int) (this.getY() + this.getHeight())) == null;
		case Right:
			return GlobalVariables.map.getElementAt((int) (this.getX() + this.getWidth() - GlobalVariables.map.getX()),
					(int) (this.getY() + 1)) == null
					&& GlobalVariables.map.getElementAt(
							(int) (this.getX() + this.getWidth() - GlobalVariables.map.getX()),
							(int) (this.getY() + this.getHeight() / 2)) == null
					&& GlobalVariables.map.getElementAt(
							(int) (this.getX() + this.getWidth() - GlobalVariables.map.getX()),
							(int) (this.getY() + this.getHeight() - 1)) == null;
		case Left:
			return GlobalVariables.map.getElementAt((int) (this.getX() - 1 - GlobalVariables.map.getX()),
					(int) (this.getY() + 1)) == null
					&& GlobalVariables.map.getElementAt((int) (this.getX() - 1 - GlobalVariables.map.getX()),
							(int) (this.getY() + this.getHeight() / 2)) == null
					&& GlobalVariables.map.getElementAt((int) (this.getX() - 1 - GlobalVariables.map.getX()),
							(int) (this.getY() + this.getHeight() - 1)) == null;
		default:
			return true;
		}
	}

	private boolean[] isPossible() {
		
		if (direction != Direction.Stop)
			{
				boolean[] bool = new boolean[5];
				for (int i = 0; i < 5; i++)
					bool[i] = false;
				return bool;
			}

		boolean[] bool = new boolean[5];
		for (int i = 0; i < 5; i++)
			bool[i] = true;

		for (int i = 0; i <= 15; i++) {
			bool[0] = bool[0]
					&& GlobalVariables.map.getElementAt((int) (this.getX() - GlobalVariables.map.getX() + 1),
							((int) (this.getY() - i))) == null
					&& GlobalVariables.map.getElementAt(
							(int) (this.getX() + this.getWidth() / 2 - 0 - GlobalVariables.map.getX()),
							(int) (this.getY() - i)) == null
					&& GlobalVariables.map.getElementAt(
							(int) (this.getX() + this.getWidth() - 1 - 1 - GlobalVariables.map.getX()),
							(int) (this.getY() - i)) == null;

			bool[1] = bool[1]
					&& GlobalVariables.map.getElementAt((int) (this.getX() - GlobalVariables.map.getX() + 1),
							(int) (this.getY() + this.getHeight() + i)) == null
					&& GlobalVariables.map.getElementAt(
							(int) (this.getX() + this.getWidth() / 2 - 0 - GlobalVariables.map.getX()),
							(int) (this.getY() + this.getHeight() + i)) == null
					&& GlobalVariables.map.getElementAt(
							(int) (this.getX() + this.getWidth() - 1 - 1 - GlobalVariables.map.getX()),
							(int) (this.getY() + this.getHeight() + i)) == null;

			bool[2] = bool[2]
					&& GlobalVariables.map.getElementAt(
							(int) (this.getX() + this.getWidth() - GlobalVariables.map.getX() + i),
							(int) (this.getY() + 1)) == null
					&& GlobalVariables.map.getElementAt(
							(int) (this.getX() + this.getWidth() - GlobalVariables.map.getX() + i),
							(int) (this.getY() + this.getHeight() / 2)) == null
					&& GlobalVariables.map.getElementAt(
							(int) (this.getX() + this.getWidth() - GlobalVariables.map.getX() + i),
							(int) (this.getY() + this.getHeight() - 1)) == null;

			bool[3] = bool[3]
					&& GlobalVariables.map.getElementAt((int) (this.getX() - i - GlobalVariables.map.getX()),
							(int) (this.getY() + 1)) == null
					&& GlobalVariables.map.getElementAt((int) (this.getX() - i - GlobalVariables.map.getX()),
							(int) (this.getY() + this.getHeight() / 2)) == null
					&& GlobalVariables.map.getElementAt((int) (this.getX() - i - GlobalVariables.map.getX()),
							(int) (this.getY() + this.getHeight() - 1)) == null;

			bool[4] = bool[4] || true;
		}
		
		switch (direction) {
		case Down: bool[0] = false; bool[1] = false; break;
		case Up: bool[1] = false; bool[0] = false; break;
		case Left: bool[2] = false; bool[3] = false; break;
		case Right: bool[3] = false; bool[2] = false; break;
		default:
			break;
		}
		
		switch (last)
		{
			case Down: bool[1] = false; break;
			case Up: bool[0] = false; break;
			case Left: bool[3] = false; break;
			case Right: bool[2] = false; break;
			default:
				break;
		}

		return bool;
	}

	private void tryChangeDirection(boolean[] bool) {
		int rand = GlobalVariables.rgen.nextInt(0, 10);
		int x = -1;
		
		while (rand > 0)
		{
			x++;
			x %= 4;
			if (bool[x])
				rand--;
		}
		
		changeDirection(x == 0 ? Direction.Up : x == 1 ? Direction.Down : x == 2 ? Direction.Right : Direction.Left);
	}

	private void changeDirection(Direction direct) {

		
		if (!isGhost)
		{
			eyes.setVisible(true);
			if (direct != Direction.Stop)
				last = (direct == Direction.Up ? Direction.Down : direct == Direction.Down ? Direction.Up : 
					direct == Direction.Left ? Direction.Right : Direction.Left);
		}
		else
		{
			if (direct != Direction.Stop && isGoHome)
			last = direction;
			else
				if (direct != Direction.Stop)
				{
					last = (direct == Direction.Up ? Direction.Down : direct == Direction.Down ? Direction.Up : 
						direct == Direction.Left ? Direction.Right : Direction.Left);
				}
			direction = direct;
			if (System.currentTimeMillis() - time > 7000)
				image.setImage("Dissapearing Ghost Steps.gif");
			else image.setImage("Ghost Steps.gif");
			image.setSize(GlobalVariables.pacmanWidth, GlobalVariables.pacmanHeight);
			if (!isGoHome)
			eyes.setVisible(false);
			
			return;
		}
		
		direction = direct;
		if (direct == last)
			return;
		
		switch (direct) {
		case Down:
			eyes.setImage("Eyes down 1.png");
			break;
		case Up:
			eyes.setImage("Eyes up 1.png");
			break;
		case Right:
			eyes.setImage("Eyes right 1.png");
			break;
		case Left:
			eyes.setImage("Eyes left 1.png");
			break;
		default:
			break;
		}
	}
	
	public void Pause(int type)
	{
		if (type == 1)
		{
			pause(2000);
			while(GlobalVariables.pause == true)
				pause(2000);
			
			for (int i = 0 ; i < 50; i++)
				{
					move(0, -1);
					pause(10);
				}
		}
		else if (type == 2)
		{
			pause(5000);
			while(GlobalVariables.pause == true)
				pause(3000);
			
			for (int i = 0; i < 25; i++)
			{
				move(1, 0);
				pause(10);
			}
			
			for (int i = 0; i < 50; i++)
			{
				move(0, -1);
				pause(10);
			}
		}
		else if (type == 3)
		{
			pause(8000);
			while(GlobalVariables.pause == true)
				pause(4000);
			
			for (int i = 0; i < 25; i++)
			{
				move(-1, 0);
				pause(10);
			}
			
			for (int i = 0; i < 50; i++)
			{
				move(0, -1);
				pause(10);
			}
		}
	}
	
	public void setGhost()
	{
		time = System.currentTimeMillis();
		isGhost = true;
		changeDirection(direction);
	}
	
	public void stop()
	{
		this.stop = true;
	}

	public void notAGhost() {
		isGhost = false;
		isGoHome = false;
		image.setImage(hiddenImage.getImage());
		image.setSize(GlobalVariables.pacmanWidth, GlobalVariables.pacmanHeight);
		image.setVisible(true);
		eyes.setVisible(true);
		GlobalVariables.ghostEaten = 0;
	}
}
