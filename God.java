package games;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class God extends GameObject {

	Handler handler;
	
	public God(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		x = Game.clamp(x, 0, Game.WIDTH-32);
		y = Game.clamp(y, 0, Game.HEIGHT - 54);
		
		collision();
		
		HUD.HEALTH += 0.5;
		
		if(HUD.HEALTH < 5) {
			HUD.HEALTH += 50;
		}
		
		if(HUD.HEALTH < 50) {
			HUD.HEALTH += 40;
		}
		
	}
	
	private void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if (tempObject.getid() == ID.BasicEnemy || tempObject.getid() == ID.FastEnemy) {
				if(this.getBounds().intersects(tempObject.getBounds())) {
					HUD.HEALTH -= 2;
				}
			}
		}
	}


	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect((int)x, (int)y, 32, 32);
	}
	
	
}

