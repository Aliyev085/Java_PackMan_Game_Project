import acm.graphics.GCompound;

public class MapOfPoint extends GCompound{
	public MapOfPoint() {
		
		SpecialPoints sp = new SpecialPoints();
		add(sp, 25, 40);
		new Thread(sp).start();
		
		sp = new SpecialPoints();
		add(sp, 25, 380);
		new Thread(sp).start();
		
		sp = new SpecialPoints();
		add(sp, 460, 40);
		new Thread(sp).start();
		
		sp = new SpecialPoints();
		add(sp, 460, 380);
		new Thread(sp).start();
		
		
		for (int i = 0; i < 25; i++)
		{
			Point point = new Point();
			add(point, 112, 25 + i * 18);
			new Thread(point).start();
		}
		
		for (int i = 0; i < 25; i++)
		{
			Point point = new Point();
			add(point, 372, 25 + i * 18);
			new Thread(point).start();
		}
		
		for (int i = 0; i < 25; i++)
		{
			Point point = new Point();
			add(point, 25 + i * 18, 505);
			new Thread(point).start();
		}
		
		for (int i = 0; i < 5; i++)
		{
			Point point = new Point();
			add(point, 25 + i * 18, 457);
			new Thread(point).start();
		}
		
		for (int i = 0; i < 4; i++)
		{
			Point point = new Point();
			add(point, 25 + i * 18 + 18 * 7 + 10, 457);
			new Thread(point).start();
		}
		
		for (int i = 0; i < 4; i++)
		{
			Point point = new Point();
			add(point, 25 + i * 18 + 18 * 13 + 10, 457);
			new Thread(point).start();
		}
		
		for (int i = 0; i < 5; i++)
		{
			Point point = new Point();
			add(point, 25 + i * 18 + 18 * 20 + 5, 457);
			new Thread(point).start();
		}
	
		for (int i = 0; i < 5; i++)
		{
			Point point = new Point();
			add(point, 25 + i * 18, 147);
			new Thread(point).start();
		}
		
		for (int i = 0; i < 4; i++)
		{
			Point point = new Point();
			add(point, 25 + i * 18 + 18 * 7 + 10, 147);
			new Thread(point).start();
		}
		
		for (int i = 0; i < 4; i++)
		{
			Point point = new Point();
			add(point, 25 + i * 18 + 18 * 13 + 10, 147);
			new Thread(point).start();
		}
		
		for (int i = 0; i < 5; i++)
		{
			Point point = new Point();
			add(point, 25 + i * 18 + 18 * 20 + 5, 147);
			new Thread(point).start();
		}
		
		for (int i = 0; i < 5; i++)
		{
			Point point = new Point();
			add(point, 25 + i * 18, 97);
			new Thread(point).start();
		}
		
		for (int i = 0; i < 13; i++)
		{
			Point point = new Point();
			add(point, 25 + i * 18 + 18 * 5 + 15, 97);
			new Thread(point).start();
		}
		
		for (int i = 0; i < 5; i++)
		{
			Point point = new Point();
			add(point, 25 + i * 18 + 18 * 20 + 5, 97);
			new Thread(point).start();
		}
		
		for (int i = 0; i < 5; i++)
		{
			Point point = new Point();
			add(point, 25 + i * 18, 25);
			new Thread(point).start();
		}
		
		for (int i = 0; i < 6; i++)
		{
			Point point = new Point();
			add(point, 25 + i * 18 + 18 * 5 + 15, 25);
			new Thread(point).start();
		}
		
		for (int i = 0; i < 6; i++)
		{
			Point point = new Point();
			add(point, 25 + i * 18 + 18 * 13 + 4, 25);
			new Thread(point).start();
		}
		
		for (int i = 0; i < 5; i++)
		{
			Point point = new Point();
			add(point, 25 + i * 18 + 18 * 20 + 5, 25);
			new Thread(point).start();
		}
		
		for (int i = 0; i < 5; i++)
		{
			Point point = new Point();
			add(point, 25 + i * 18, 350);
			new Thread(point).start();
		}
		
		for (int i = 0; i < 6; i++)
		{
			Point point = new Point();
			add(point, 25 + i * 18 + 18 * 5 + 15, 350);
			new Thread(point).start();
		}
		
		for (int i = 0; i < 6; i++)
		{
			Point point = new Point();
			add(point, 25 + i * 18 + 18 * 13 + 4, 350);
			new Thread(point).start();
		}
		
		for (int i = 0; i < 5; i++)
		{
			Point point = new Point();
			add(point, 25 + i * 18 + 18 * 20 + 5, 350);
			new Thread(point).start();
		}
	
		for (int i = 0; i < 3; i++)
		{
			Point point = new Point();
			add(point, 25 + i * 18, 403);
			new Thread(point).start();
		}
		
		for (int i = 0; i < 6; i++)
		{
			Point point = new Point();
			add(point, 25 + i * 18 + 18 * 5 + 15, 403);
			new Thread(point).start();
		}
		
		for (int i = 0; i < 6; i++)
		{
			Point point = new Point();
			add(point, 25 + i * 18 + 18 * 13 + 4, 403);
			new Thread(point).start();
		}
		
		for (int i = 0; i < 3; i++)
		{
			Point point = new Point();
			add(point, 25 + i * 18 + 18 * 22 + 5, 403);
			new Thread(point).start();
		}
		
		
		for (int i = 0; i < 2; i++)
		{
			Point point = new Point();
			add(point, 25, 25 + 18 * 2 + i * 18);
			new Thread(point).start();
		}
		
		for (int i = 0; i < 2; i++)
		{
			Point point = new Point();
			add(point, 25, 25 + 18 * 5 + i * 18);
			new Thread(point).start();
		}
		
		for (int i = 0; i < 2; i++)
		{
			Point point = new Point();
			add(point, 461, 25 + 18 * 2 + i * 18);
			new Thread(point).start();
		}
		
		for (int i = 0; i < 2; i++)
		{
			Point point = new Point();
			add(point, 461, 25 + 18 * 5 + i * 18);
			new Thread(point).start();
		}
		
		for (int i = 0; i < 3; i++)
		{
			Point point = new Point();
			add(point, 220, 25 + 18 + i * 18);
			new Thread(point).start();
		}
		
		for (int i = 0; i < 3; i++)
		{
			Point point = new Point();
			add(point, 263, 25 + 18 + i * 18);
			new Thread(point).start();
		}
		
		
		for (int i = 0; i < 2; i++)
		{
			Point point = new Point();
			add(point, 161, 25 + 18 * 5 + i * 18);
			new Thread(point).start();
		}
		
		for (int i = 0; i < 2; i++)
		{
			Point point = new Point();
			add(point, 323, 25 + 18 * 5 + i * 18);
			new Thread(point).start();
		}
				
		for (int i = 0; i < 2; i++)
		{
			Point point = new Point();
			add(point, 263, 25 + 18 * 19 + i * 19);
			new Thread(point).start();
		}
		
		
		for (int i = 0; i < 2; i++)
		{
			Point point = new Point();
			add(point, 220, 25 + 18 * 19 + i * 19);
			new Thread(point).start();
		}
		
		for (int i = 0; i < 2; i++)
		{
			Point point = new Point();
			add(point, 318, 25 + 18 * 22 + i * 19);
			new Thread(point).start();
		}
		
		for (int i = 0; i < 2; i++)
		{
			Point point = new Point();
			add(point, 161, 25 + 18 * 22 + i * 19);
			new Thread(point).start();
		}
		
		for (int i = 0; i < 2; i++)
		{
			Point point = new Point();
			add(point, 61, 25 + 18 * 22 + i * 19);
			new Thread(point).start();
		}
		
		for (int i = 0; i < 2; i++)
		{
			Point point = new Point();
			add(point, 426, 25 + 18 * 22 + i * 19);
			new Thread(point).start();
		}
		
		
		for (int i = 0; i < 2; i++)
		{
			Point point = new Point();
			add(point, 25, 22 + 18 * 25 + i * 18);
			new Thread(point).start();
		}
		
		for (int i = 0; i < 2; i++)
		{
			Point point = new Point();
			add(point, 462, 22 + 18 * 25 + i * 19);
			new Thread(point).start();
		}
		
		for (int i = 0; i < 2; i++)
		{
			Point point = new Point();
			add(point, 215, 22 + 18 * 25 + i * 18);
			new Thread(point).start();
		}
		
		for (int i = 0; i < 2; i++)
		{
			Point point = new Point();
			add(point, 269, 22 + 18 * 25 + i * 19);
			new Thread(point).start();
		}
	}
}
