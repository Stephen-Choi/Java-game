package games;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.lang.Thread.State;
import java.util.Random;
import games.Game.STATE;

public class EnemyBoss extends GameObject{

	Random r = new Random();
	private Handler handler;
	private int timer = 140;
	private int timer2 = 80;
	private int timer3 = 0;
	private Game game;
	public STATE gameState = STATE.Game;
	
	public EnemyBoss(int x, int y, ID id, Handler handler, Game game) {
		super(x, y, id);
		
		this.game = game;
		this.handler = handler;
		velY = 0;
		velX = 0;
	}

	public void tick() {
		x += velX;
		y += velY;
		
		velY = 2;
		
		if(timer <= 0) velY = 0;
		else timer--;
		
		if (timer <= 0) timer2--;
		
		if(timer2 <= 0) {
			if(velX == 0) velX = 2;
			velX *= 1.001; 
			int spawn = r.nextInt(10);
			if (spawn == 0 && timer3 < 1500) handler.addObject(new EnemyBullet((int)x + 48, (int)y +48, ID.BasicEnemy, handler));
			else if(timer3 >= 1501) {
				if (spawn == 0 || spawn == 1) handler.addObject(new EnemyBullet((int)x + 48, (int)y +48, ID.BasicEnemy, handler));
			}
			timer3++;
		}
		
		if(timer3 >= 2200) {
			handler.clearEnemy();
		}
			
		if(x <= 0 || x >= Game.WIDTH - 32) velX *= -1;
		
		//handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.blue, 96, 96, (float) 0.05, handler));
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 96, 96);
	}
	
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect((int)x, (int)y, 96, 96);
	}
}
