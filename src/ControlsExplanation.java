import java.awt.Color;
import java.awt.Font;

import acm.graphics.GCompound;
import acm.graphics.GLabel;

public class ControlsExplanation extends GCompound {
	private GLabel controls = new GLabel("CONTROLS");
	private GLabel pacman_control = new GLabel("ARROW KEYS FOR PACMAN");
	private GLabel pause = new GLabel("P: PAUSE/UNPAUSE GAME");
	private GLabel quit = new GLabel("Q: QUIT GAME");
	private GLabel mute = new GLabel("M: MUTE/UNMUTE SOUND");
	private GLabel save = new GLabel("S: SAVE STATE");
	private GLabel comment = new GLabel("(works only in the same time with pause)");
	private GLabel go_back = new GLabel("GO BACK");
	
	public ControlsExplanation() {
		controls.setFont(GlobalVariables.standardFont);
		pacman_control.setFont(GlobalVariables.smallFont);
		pause.setFont(GlobalVariables.smallFont);
		quit.setFont(GlobalVariables.smallFont);
		mute.setFont(GlobalVariables.smallFont);
		save.setFont(GlobalVariables.smallFont);
		comment.setFont(GlobalVariables.smallFont);
		go_back.setFont(GlobalVariables.standardFont);
		
		controls.setColor(Color.WHITE);
		pacman_control.setColor(Color.WHITE);
		pause.setColor(Color.WHITE);
		quit.setColor(Color.WHITE);
		mute.setColor(Color.WHITE);
		save.setColor(Color.WHITE);
		comment.setColor(Color.WHITE);
		go_back.setColor(Color.WHITE);
		
		add(controls, GlobalVariables.APP_WIDTH / 2 - controls.getWidth() / 2, 200);
		add(pacman_control, GlobalVariables.APP_WIDTH / 2 - pacman_control.getWidth() / 2, 200 + controls.getHeight() * 2);
		add(pause, GlobalVariables.APP_WIDTH / 2 - pacman_control.getWidth() / 2, 200 + controls.getHeight() * 3);
		add(quit, GlobalVariables.APP_WIDTH / 2 - pacman_control.getWidth() / 2, 200 + controls.getHeight() * 4);
		add(mute, GlobalVariables.APP_WIDTH / 2 - pacman_control.getWidth() / 2, 200 + controls.getHeight() * 5);
		add(save, GlobalVariables.APP_WIDTH / 2 - pacman_control.getWidth() / 2, 200 + controls.getHeight() * 6);
		add(comment, GlobalVariables.APP_WIDTH / 2 - comment.getWidth() / 2, 200 + controls.getHeight() * 7);
		add(go_back, GlobalVariables.APP_WIDTH / 2 - go_back.getWidth() / 2, 200 + controls.getHeight() * 9);
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