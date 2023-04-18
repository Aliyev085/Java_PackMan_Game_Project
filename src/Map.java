

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import acm.graphics.GCompound;
import acm.graphics.GRect;

public class Map extends GCompound {
	public Map() {

		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("C:\\Users\\asker\\Desktop\\Term Project\\mapOfPoint")));
		
		String str;
		
		while ((str = br.readLine()) != null)
		{
			String[] t = str.split(" ");
			
			GRect rect = new GRect(Double.parseDouble(t[0]), Double.parseDouble(t[1]));System.out.println(1);
			add(rect, Double.parseDouble(t[2]), Double.parseDouble(t[3]));
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.setVisible(false);
	}
}
