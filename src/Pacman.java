import acm.graphics.GImage;

public class Pacman extends GImage implements Runnable{
	
	private Direction direction;
	private Direction newDirection = Direction.Right;
	private int lives = 3;

	public Pacman() {
		super("Pacman Step 2.1.png");
		this.setSize(GlobalVariables.pacmanWidth, GlobalVariables.pacmanHeight);
		direction = Direction.Stop;
	}

	@Override
	public void run() {
		while (GlobalVariables.gameIsOn)
		{
			if (isPossible())
				tryChangeDirection();
			
			if (!GlobalVariables.pause)
			if (isPossibleToGo())
			{
				switch (direction)
				{
					case Down: 	this.move(0, 1); 	break;
					case Up:   	this.move(0, -1); 	break;
					case Right:	this.move(1, 0);	break;
					case Left:	this.move(-1, 0); 	break;
					default:						break;
				}
				
				if (this.getX() > GlobalVariables.APP_WIDTH)
					this.setLocation(0, this.getY());
				else if (this.getX() < -this.getWidth())
					this.setLocation(GlobalVariables.APP_WIDTH, this.getY());
			}
			else
			{
				if (direction != Direction.Stop)
				{
					changeDirection(Direction.Stop);
					newDirection = Direction.Stop;
				}
			}
			
			pause(GlobalVariables.pacmanSpeed);
			
			if (lives == 0)
				GlobalVariables.gameIsOn = false;
		}
	}
	
	public int getLives() {
		return lives;
	}
	
	public void decreaseLives() {
		lives--;
		
		if (lives == 0)
		{
			GlobalVariables.gameIsOn = false;
		}
	}

	private boolean isPossibleToGo() {		
		switch (direction) {
		case Up:
			return GlobalVariables.map.getElementAt((int) (this.getX() - GlobalVariables.map.getX() + 1), ((int) (this.getY() - 1))) == null &&
				   GlobalVariables.map.getElementAt((int) (this.getX() + this.getWidth() / 2 - 0 - GlobalVariables.map.getX()), (int) (this.getY() - 1)) == null &&
				   GlobalVariables.map.getElementAt((int) (this.getX() + this.getWidth() - 1 - 1 - GlobalVariables.map.getX()), (int) (this.getY() - 1)) == null;
		case Down:
			return GlobalVariables.map.getElementAt((int) (this.getX() - GlobalVariables.map.getX() + 1), (int) (this.getY() + this.getHeight())) == null &&
				   GlobalVariables.map.getElementAt((int) (this.getX() + this.getWidth() / 2 - 0 - GlobalVariables.map.getX()), (int) (this.getY() + this.getHeight())) == null &&
				   GlobalVariables.map.getElementAt((int) (this.getX() + this.getWidth() - 1 - 1 - GlobalVariables.map.getX()), (int) (this.getY() + this.getHeight())) == null;
		case Right:
			return GlobalVariables.map.getElementAt((int) (this.getX() + this.getWidth() - GlobalVariables.map.getX()), (int) (this.getY() + 1)) == null &&
			       GlobalVariables.map.getElementAt((int) (this.getX() + this.getWidth() - GlobalVariables.map.getX()), (int) (this.getY() + this.getHeight() / 2)) == null &&
				   GlobalVariables.map.getElementAt((int) (this.getX() + this.getWidth() - GlobalVariables.map.getX()), (int) (this.getY() + this.getHeight() - 1)) == null;
		case Left:
			return GlobalVariables.map.getElementAt((int) (this.getX() - 1 - GlobalVariables.map.getX()), (int) (this.getY() + 1)) == null &&
				   GlobalVariables.map.getElementAt((int) (this.getX() - 1 - GlobalVariables.map.getX()), (int) (this.getY() + this.getHeight() / 2)) == null &&
			       GlobalVariables.map.getElementAt((int) (this.getX() - 1 - GlobalVariables.map.getX()), (int) (this.getY() + this.getHeight() - 1)) == null;
		default:
			return true;
		}
	}
	
	private boolean isPossible() {

		if (direction == newDirection)
			return false;
		
		boolean bool = true;
		for (int i = 0; i <= 15; i++)
		switch (newDirection) {
		case Up:
			bool = bool && GlobalVariables.map.getElementAt((int) (this.getX() - GlobalVariables.map.getX() + 1), ((int) (this.getY() - i))) == null &&
				   GlobalVariables.map.getElementAt((int) (this.getX() + this.getWidth() / 2 - 0 - GlobalVariables.map.getX()), (int) (this.getY() - i)) == null &&
				   GlobalVariables.map.getElementAt((int) (this.getX() + this.getWidth() - 1 - 1 - GlobalVariables.map.getX()), (int) (this.getY() - i)) == null;
			break;
		case Down:
			bool = bool && GlobalVariables.map.getElementAt((int) (this.getX() - GlobalVariables.map.getX() + 1), (int) (this.getY() + this.getHeight() + i)) == null &&
				   GlobalVariables.map.getElementAt((int) (this.getX() + this.getWidth() / 2 - 0 - GlobalVariables.map.getX()), (int) (this.getY() + this.getHeight() + i)) == null &&
				   GlobalVariables.map.getElementAt((int) (this.getX() + this.getWidth() - 1 - 1 - GlobalVariables.map.getX()), (int) (this.getY() + this.getHeight() + i)) == null;
			break;
		case Right:
			bool = bool && GlobalVariables.map.getElementAt((int) (this.getX() + this.getWidth() - GlobalVariables.map.getX() + i), (int) (this.getY() + 1)) == null &&
			       GlobalVariables.map.getElementAt((int) (this.getX() + this.getWidth() - GlobalVariables.map.getX() + i), (int) (this.getY() + this.getHeight() / 2)) == null &&
				   GlobalVariables.map.getElementAt((int) (this.getX() + this.getWidth() - GlobalVariables.map.getX() + i), (int) (this.getY() + this.getHeight() - 1)) == null;
			break;
		case Left:
			bool = bool && GlobalVariables.map.getElementAt((int) (this.getX() - i - GlobalVariables.map.getX()), (int) (this.getY() + 1)) == null &&
				   GlobalVariables.map.getElementAt((int) (this.getX() - i - GlobalVariables.map.getX()), (int) (this.getY() + this.getHeight() / 2)) == null &&
			       GlobalVariables.map.getElementAt((int) (this.getX() - i - GlobalVariables.map.getX()), (int) (this.getY() + this.getHeight() - 1)) == null;
			break;
		default:
			bool = bool || true;
			break;
		}
		
		return bool;
	}

	private void tryChangeDirection() {
		if (newDirection != Direction.Stop &&
			direction != newDirection)
		changeDirection(newDirection);		
	}

	private void changeDirection(Direction direct) {
		switch (direct)
		{
			case Down: 	this.setImage("Pacman Steps Down.gif");		break;
			case Up:   	this.setImage("Pacman Steps Up.gif");		break;
			case Right:	this.setImage("Pacman Steps Right.gif");	break;
			case Left:	this.setImage("Pacman Steps Left.gif"); 	break;
			default:	
				{
					switch (direction)
					{
						case Down: 	this.setImage("Pacman Step 2.2.png"); this.move(0, -1);	break;
						case Up:   	this.setImage("Pacman Step 2.4.png"); this.move(0, 1);	break;
						case Right:	this.setImage("Pacman Step 2.1.png"); this.move(-1, 0);	break;
						case Left:	this.setImage("Pacman Step 2.3.png"); this.move(1, 0);	break;
					default:
						break;
					}
				}
				break;
		}
		
		this.setSize(GlobalVariables.pacmanWidth, GlobalVariables.pacmanHeight);
		
		direction = direct;
		newDirection = Direction.Stop;
	}
	
	public void changeDirectionInFuture(Direction direct) {
		newDirection = direct;
	}
}
