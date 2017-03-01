package games;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import games.Game.STATE;

public class Menu extends MouseAdapter{

	private Game game;
	private Handler handler;
	private HUD hud;
	Random r = new Random();
	public STATE gameState;
	
	public Menu(Game game, Handler handler, HUD hud) {
		this.game = game;
		this.handler = handler;
		this.hud = hud;
	}
	
	public void mousePressed(MouseEvent e) {
		
		if (game.gameState == STATE.Menu) {
		int mx = e.getX();
		int my = e.getY();
		
		//play button
		if(mouseOver(mx, my, 220, 150, 200, 64)) {
			game.gameState = STATE.Game;
			handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
			handler.addObject(new BasicEnemy(r.nextInt(5), r.nextInt(5) , ID.BasicEnemy, handler));
		}
		
		//help
		if(mouseOver(mx, my, 220, 250, 200, 64)) {
			game.gameState = STATE.Help;
		}
		
		//help menu
		
		//immortal
		if(mouseOver(mx,my, 220, 350, 200, 64)) {
			game.gameState = STATE.Immortal;
			handler.addObject(new God(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
			handler.addObject(new BasicEnemy(r.nextInt(5), r.nextInt(5) , ID.BasicEnemy, handler));
			}
		}
		
		if(game.gameState == STATE.Help) {
			int mx = e.getX();
			int my = e.getY();
			
			if(mouseOver(mx, my, 20, 350, 200, 64)) {
			game.gameState = STATE.Menu;
			}
		}
		
		//dead return
		if(game.gameState == STATE.Dead) {
			int mx = e.getX();
			int my = e.getY();
			
			if(mouseOver(mx, my, 20, 350, 200, 64)) {
			game.gameState = STATE.Menu;
			}
			
			if(mouseOver(mx, my, 300, 350, 200, 64)) {
				hud.setWin(0);
				hud.setLevel(0);
				hud.setScore(0);
				hud.HEALTH = 200;
				game.gameState = STATE.Game;
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
				handler.addObject(new BasicEnemy(r.nextInt(5), r.nextInt(5) , ID.BasicEnemy, handler));
			}
		}
		
		if(game.gameState == STATE.Win) {
			int mx = e.getX();
			int my = e.getY();
			
			if(mouseOver(mx, my, 20, 350, 200, 64)) {
			game.gameState = STATE.Menu;
			}
			
			if(mouseOver(mx, my, 300, 350, 200, 64)) {
				hud.setWin(0);
				hud.setLevel(0);
				hud.setScore(0);
				hud.HEALTH = 200;
				game.gameState = STATE.Game;
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
				handler.addObject(new BasicEnemy(r.nextInt(5), r.nextInt(5) , ID.BasicEnemy, handler));
			}
		}
	}


	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if(mx > x && mx <  x + width) {
			if(my > y && my < y + height) {
				return true;
			} else return false;
		} else return false;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		if(game.gameState == STATE.Menu) {
		Font fnt = new Font("arial", 1, 50);
		Font fnt2 = new Font("arial", 1, 30);
		Font fnt3 = new Font("arial", 1, 25);
		
		g.setFont(fnt);
		g.setColor(Color.white);
		g.drawString("Menu", 256, 80);
		
		g.setFont(fnt2);
		g.drawString("Play", 290, 195);
		g.drawRect(220, 150, 200, 64);
		
		g.drawString("Help", 290, 295);
		g.drawRect(220, 250, 200, 64);
		
		g.setFont(fnt3);
		
		g.drawString("Immortal Mode", 233, 392);
		g.drawRect(220, 350, 200, 64);
		
	} else if (game.gameState == STATE.Help) {
		Font fnt = new Font("arial", 1, 50);
		Font fnt2 = new Font("arial", 1, 30);
		
		g.setFont(fnt);
		g.setColor(Color.white);
		g.drawString("Help", 256, 80);
		
		g.setFont(fnt2);
		g.drawString("Back", 80, 392);
		g.drawRect(20, 350, 200, 64);
		
		g.drawString("Use the keys WASD to move", 120, 172);
		g.drawString("Goal: don't get hit", 120, 222);
		g.drawString("Press esc to leave game", 120, 272);
	}
	else if (game.gameState == STATE.Dead) {
		Font fnt = new Font("arial", 1, 50);
		Font fnt2 = new Font("arial", 1, 30);
		
		g.setFont(fnt);
		g.setColor(Color.white);
		g.drawString("You Lost", 200, 80);
		
		g.setFont(fnt2);
		g.drawString("You lost with a score of " + hud.getScore(), 120, 172);
	
		g.drawString("Back", 35, 392);
		g.drawRect(20, 350, 100, 64);
		
		g.drawString("Try Again", 257, 392);
		g.drawRect(250, 350, 150, 64);
		}	
	else if (game.gameState == STATE.Win) {
		Font fnt = new Font("arial", 1, 50);
		Font fnt2 = new Font("arial", 1, 30);
		
		g.setFont(fnt);
		g.setColor(Color.white);
		g.drawString("You Win", 220, 80);
		
		g.setFont(fnt2);
		g.drawString("You won, enjoy the music now!", 110, 172);
	
		g.drawString("Back", 35, 392);
		g.drawRect(20, 350, 100, 64);
		
		g.drawString("Try Again", 257, 392);
		g.drawRect(250, 350, 150, 64);
		}	
	}
}
