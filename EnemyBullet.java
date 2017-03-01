package games;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class EnemyBullet extends GameObject{

	private Handler handler;
	Random r = new Random();
	
	public EnemyBullet(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		
		this.handler = handler;
		velY = 5;
		velX = (r.nextInt(5 - -5) + -5);
	}

	public void tick() {
		x += velX;
		y += velY;
		
		//if(y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;
		//if(x <= 0 || x >= Game.WIDTH - 16) velX *= -1;
		
		if(y >= Game.HEIGHT) handler.removeObject(this);
		
		handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.blue, 16, 16, (float) 0.05, handler));
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16, 16);
	}
	
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect((int)x, (int)y, 16, 16);
	}

}

