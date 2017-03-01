package games;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 640, HEIGHT = WIDTH/12 *9;
	private Thread thread;
	private boolean running = false;
	private Handler handler;
	private HUD hud;
	private Menu menu;
	private Game game;
	
	public static boolean paused = false;
	
	public enum STATE {
		Menu,
		Game,
		Help,
		Immortal,
		Dead,
		Paused,
		Win,
	};
	
	public STATE gameState = STATE.Menu;
	
	private Spawn spawner;
	
	public Game() {
		handler = new Handler();
		hud = new HUD();
		menu = new Menu(this, handler, hud);
		this.addMouseListener(menu);
		spawner = new Spawn(handler, hud, game);
		this.addKeyListener(new KeyInput(handler, this));
		Random r = new Random();
		
		new Window(WIDTH, HEIGHT, "Let's build a game", this);
		
		if(gameState == STATE.Game) {
			
			if (!paused) {
				handler.addObject(new Player(WIDTH/2-32, HEIGHT/2-32, ID.Player, handler));
				handler.addObject(new BasicEnemy(r.nextInt(5), r.nextInt(5) , ID.BasicEnemy, handler));
			}
			
		}
		
		
		
		//for (int i = 0; i < 2; i++) {
			//handler.addObject(new BasicEnemy(r.nextInt(WIDTH),r.nextInt(HEIGHT), ID.BasicEnemy, handler));
		//}
	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			if (running)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				//System.out.println("FPS: + " + frames);
				frames = 0;
			}
		}
		stop();
	}

	private void tick() {
		
		if(gameState == STATE.Game || gameState == STATE.Immortal) {
			if (!paused) {
	
				//if the game is not paused, run otherwise dont
				
				hud.tick();
				spawner.tick();
				handler.tick();
			
			if(HUD.HEALTH <= 0) {
				handler.fullClear();
				hud.setWin(0);
				hud.setLevel(0);
				gameState = STATE.Dead;
			}  else if (hud.getWin() == 7005) {
				handler.fullClear();
				hud.setWin(0);
				hud.setLevel(0);
				gameState = STATE.Win;
			}
		}
			else if (gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.Dead || gameState == STATE.Win) {
			menu.tick();
			handler.tick();
		} 
	}
}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		
		if(paused) {
			g.setColor(Color.white);
			g.drawString("PAUSED", 100, 100);
		}
		
		if(gameState == STATE.Game || gameState == STATE.Immortal) {
			hud.render(g);
		} else if (gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.Dead || gameState == STATE.Win) {
			menu.render(g);
		}
		g.dispose();
		bs.show();
	}

	public static float clamp(float var, float min, float max) {
		if(var >= max) {
			return var = max;
		} else if (var <= min) {
			return var = min;
		} else return var;
	}
	
public static void main (String args[]) {
		new Game();
	}
}
