import java.io.*;
import java.net.MalformedURLException;
import javax.sound.sampled.*;
import javax.swing.*;

public class Sound extends JFrame{
	//this part was copied from stackOverFlow do not know how it works
	private static final long serialVersionUID = 1L;
	

	public static void playSound(String soundFile)
			throws MalformedURLException, UnsupportedAudioFileException, IOException, LineUnavailableException {
		AudioInputStream audioIn;
		File f = new File(soundFile);
		audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());
		Clip clip = AudioSystem.getClip();
		clip.open(audioIn);
		clip.start();
	}
		
}