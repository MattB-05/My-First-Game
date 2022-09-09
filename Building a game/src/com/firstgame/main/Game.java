package com.firstgame.main;

 

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable
{
	
	
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
	
	public static boolean paused = false;
	
	private Thread thread;
	private boolean running = false;
	
	private Random r;
	private HUD hud;
	private Handler handler;
	private Spawn spawner;
	private Menu menu;
	private MenuEffect menuEffect;
	
	public enum STATE
	{
		Menu,
		Help,
		Game,
		GameSelect,
		Win,
		GameOver;
	}
	
	public static STATE gameState = STATE.Menu;

	public Game()
	{
		handler = new Handler();
		hud = new HUD();
		menu = new Menu(this, handler, hud);
		
		r = new Random();
		
		menuEffect = new MenuEffect(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.MenuEffect, handler);
		
		this.addMouseListener(menu);
		this.addKeyListener(new KeyInput(handler));
		
		
		new Window(WIDTH, HEIGHT, "Surge", this);
		
		
		spawner = new Spawn(handler, hud);
		
		
		
		if (gameState == STATE.Game)
		{
				handler.addObject(new Player(WIDTH/2-32, HEIGHT/2-32, ID.Player, handler)); //Player1
				handler.addObject(new Player(WIDTH/2+20, HEIGHT/2-32, ID.Player2, handler)); // Possible Player 2
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
		}
		
		
	}

	public synchronized void start()
	{
		thread = new Thread(this);
		thread.start();
		setRunning(true);
	}
	
	public synchronized void stop()
	{
		try
		{
			thread.join();
			setRunning(false);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void run()
	{
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		
		while(isRunning())
		{
			long now = System.nanoTime();
			delta += (now - lastTime) /ns;
			lastTime = now;
			
			while(delta >= 1)
			{
				tick();
				delta--;
			}
			
			if (isRunning())
			{
				render();
				frames++;
			}
			
			if (System.currentTimeMillis() - timer >1000)
			{
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;				
			}
		}
		stop();
	}
	
	private void tick()
	{		
		if(!paused)
		{
			handler.tick();
			if (gameState == STATE.Game)
			{
				hud.tick();
				spawner.tick();
			}
			else if (gameState == STATE.Menu)
				{
					menu.tick();
				}
		}
	}
	
	private void render()
	{
		
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null)
		{
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		if(!paused)
		{
			
			g.setColor(Color.black);
			g.fillRect(0,0, WIDTH, HEIGHT);
			
			handler.render(g);
			
			if (gameState == STATE.Game)
			{
				hud.render(g);
			}
			else if (gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.GameOver || gameState == STATE.GameSelect || gameState == STATE.Win)
			{
				menu.render(g);
			}
			
			
			
			
			
		}
		
		if(paused)
		{
			Font font = new Font("arial", 1, 40);
			
			g.setFont(font);
			g.setColor(Color.gray);
			g.drawString("PAUSED", 230,150);
		}
		
		g.dispose();
		bs.show();
	}
	
	public static float clamp(float var, float min, float max)
	{
		if (var >= max)
		{
			return var = max;
		}
		else if(var <= min)
		{
			return var = min;
		}
		else
		{
			return var;
		}
	}
	
	public static void main(String[] args)
	{
		new Game();
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}
	
	

}
