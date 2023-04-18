import java.awt.Color;

import java.awt.Window;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.program.GraphicsProgram;

public class Menu extends GraphicsProgram {
	private static final long serialVersionUID = 1L;
	private GLabel start_game = new GLabel("START GAME");
	private GLabel controls = new GLabel("CONTROLS");
	private GLabel about = new GLabel("ABOUT");
	private ControlsExplanation explanation = new ControlsExplanation();
	private About history = new About();

	@Override
	public void init() {
		setSize(GlobalVariables.APP_WIDTH, GlobalVariables.APP_HEIGHT);
		setBackground(Color.BLACK);
		addMouseListeners();

		GImage pacman = new GImage("PACMAN.png");
		add(pacman, getWidth() / 2 - pacman.getWidth() / 2, 0);

		start_game.setFont(GlobalVariables.standardFont);
		controls.setFont(GlobalVariables.standardFont);
		about.setFont(GlobalVariables.standardFont);

		start_game.setColor(Color.WHITE);
		controls.setColor(Color.WHITE);
		about.setColor(Color.WHITE);

		addElements();
	}

	private void addElements() {
		add(start_game, getWidth() / 2 - start_game.getWidth() / 2, getHeight() / 2 + start_game.getHeight() * 2);
		add(controls, getWidth() / 2 - controls.getWidth() / 2, getHeight() / 2 + start_game.getHeight() * 4);
		add(about, getWidth() / 2 - about.getWidth() / 2, getHeight() / 2 + start_game.getHeight() * 6);
	}

	private void removeElements() {
		remove(start_game);
		remove(controls);
		remove(about);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX(), y = e.getY();

		if (getElementAt(x, y) == explanation) {
			if (explanation.checkForGoBack(x, y)) {
				remove(explanation);
				addElements();
				return;
			}
		}

		if (getElementAt(x, y) == history) {
			if (history.checkForGoBack(x, y)) {
				remove(history);
				addElements();
				return;
			}
		}

		if (getElementAt(x, y) == controls) {
			removeElements();
			add(explanation);
			return;
		}

		if (getElementAt(x, y) == about) {
			removeElements();
			add(history);
			return;
		}

		if (getElementAt(x, y) == start_game) {
			Window window = SwingUtilities.getWindowAncestor(e.getComponent());
			window.dispose();
			GlobalVariables.gameIsOn = true;
//			new Game().start();
			new Thread() {
				@Override
				public void run() {
					new Game().start();
				}
			}.start();
		}
	}

	public static void main(String[] args) {
		new Menu().start();
	}
}