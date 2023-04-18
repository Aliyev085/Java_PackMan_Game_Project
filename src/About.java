import java.awt.Color;
import java.awt.Font;

import acm.graphics.GCompound;
import acm.graphics.GImage;
import acm.graphics.GLabel;

public class About extends GCompound{
	private GImage history = new GImage("About.png");
	private GLabel go_back = new GLabel("GO BACK");
	
	public About() {
		add(history, (GlobalVariables.APP_WIDTH - history.getWidth()) / 2, (GlobalVariables.APP_HEIGHT - history.getHeight()) / 2);
		
		go_back.setColor(Color.WHITE);
		go_back.setFont(GlobalVariables.standardFont);
		add(go_back, (GlobalVariables.APP_WIDTH - go_back.getWidth()) / 2, (GlobalVariables.APP_HEIGHT + history.getHeight()) / 2 + go_back.getHeight() * 2);
	}

	public boolean checkForGoBack(int x, int y) {
		if (getElementAt(x, y) == go_back)
			return true;
		return false;
	}
	
	public void changeTheFontOfTheGoBack(Font font) {
		go_back.setFont(font);	
	}
}
