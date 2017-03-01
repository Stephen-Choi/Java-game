package games;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

public class Spawn {
	
	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	private int scorekeep = 0;
	private Game game;

	public Spawn(Handler handler, HUD hud, Game game) {
		this.handler = handler;
		this.hud = hud;
		this.game = game;
	}
	
	public void tick() {
		scorekeep ++;
		
		if (scorekeep >= 500) {
			scorekeep = 0;
			hud.setLevel(hud.getLevel() + 1);
			if(hud.getLevel() == 2) {
				handler.addObject(new BasicEnemy(r.nextInt(5), r.nextInt(5) , ID.BasicEnemy, handler));
			}  else if (hud.getLevel() == 3) {
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
			} else if (hud.getLevel() == 4) {
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.FastEnemy, handler));
			} else if (hud.getLevel() == 5) {
				handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.FastEnemy, handler));
			} else if (hud.getLevel() == 6) {
				handler.addObject(new BigEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.FastEnemy, handler));
			} else if (hud.getLevel() == 7) {
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.FastEnemy, handler));
			} else if (hud.getLevel() == 8) {
				handler.addObject(new BigEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.FastEnemy, handler));
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.FastEnemy, handler));
			} else if (hud.getLevel() == 10) {
				handler.clearEnemy();
				hud.setHealth(hud.getHealth() + 20);
				handler.addObject(new EnemyBoss(Game.WIDTH/2 - 32, (int)-250, ID.BasicEnemy, handler, game));
			} 
		}
	}
}
