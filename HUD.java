package games;

import java.awt.Color;
import java.awt.Graphics;

import games.Game.STATE;

public class HUD {

	private STATE gameState;

	public static float HEALTH = 200;
	
	private float greenValue = 255f;
	
	private int score = 0;
	private int level = 1;
	private int winning = 0;
	
	public void tick() {
		HEALTH = Game.clamp(HEALTH, 0, 100);
		
		greenValue = (int) Game.clamp(greenValue, 0, 255);
		
		greenValue = HEALTH * 2;
		
		score++;
		winning++;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(15, 15, 200, 32);
		g.setColor(new Color (72, (int)greenValue, 0));
		g.fillRect(15, 15, (int) (HEALTH * 2), 32);
		g.setColor(Color.white);
		g.drawRect(15, 15, 200, 32);
		
		g.drawString("Score: " + score, 15, 64);
		g.drawString("Level: " + level, 15, 80);
	}
	
	public float getHealth() {
		return HEALTH;
	}
	
	public void setHealth(float HEALTH) {
		this.HEALTH = HEALTH; 
	}
	
	public void setWin(int winning) {
		this.winning = winning;
	}
	
	public int getWin() {
		return winning;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public int getScore() {
		return score;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
}
