package games;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BigEnemy extends GameObject{

	private Handler handler;
	
	public BigEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		velY = 4;
		velX = 4;
	}

	public void tick() {
		x += velX;
		y += velY;
		
		if(y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;
		if(x <= 0 || x >= Game.WIDTH - 16) velX *= -1;
		
		handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.green, 25, 25, (float) 0.05, handler));
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 25, 25);
	}
	
	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRect((int)x, (int)y, 25, 25);
	}

}


