package com.firstgame.main;

 

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.firstgame.main.Game.STATE;


public class Menu extends MouseAdapter
{
	
	private Game game;
	private Handler handler;
	private Random r = new Random();
	
	public Menu(Game game, Handler handler) 
	{
		this.game = game;
		this.handler = handler;
	}
	
	public void mousePressed(MouseEvent e)
	{
		int mx = e.getX();
		int my = e.getY();
		
		
		if (game.gameState == STATE.Menu)
		{
			//Play button
			if (mouseOver(mx, my, 210, 150, 200, 64))
			{
				game.gameState = STATE.Game;
				
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler)); //Player1
				//handler.addObject(new Player(Game.WIDTH/2+20, Game.HEIGHT/2-32, ID.Player2)); // Possible Player 2
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
				
			}
			
			//Help button
			if (mouseOver(mx, my, 210, 250, 200, 64))
			{
				game.gameState = STATE.Help;
			}
					
			
			//Quit button
			if (mouseOver(mx, my, 210, 350, 200, 64))
			{
				System.exit(1);
			}
		}
		
		if (game.gameState == STATE.Help)
		{
			//Back button in help
			if (mouseOver(mx, my, 210, 350, 200, 64))
			{
				game.gameState = STATE.Menu;
				return;
			}		
		}

	}
	
	public void mouseReleased(MouseEvent e)
	{
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height)
	{
		if (mx > x && mx < x + width)
		{
			if (my > y && my < y + height)
			{
				return true;
			}
			else return false;
		}
		else return false;
		
	}
	
	
	
	public void tick()
	{
		
	}
	
	public void render(Graphics g)
	{
		if (game.gameState == STATE.Menu)
		{
			Font font = new Font("arial", 1, 50);
			Font font2 = new Font("arial", 1, 30);
			
			g.setFont(font);
			g.setColor(Color.white);
			g.drawString("Surge", 245, 50);
			
			g.setFont(font2);
			g.drawRect(210, 150, 200, 64);
			g.drawString("Play", 278, 193);
			
			g.drawRect(210, 250, 200, 64);
			g.drawString("Help", 278, 293);
			
			g.drawRect(210, 350, 200, 64);
			g.drawString("Quit", 278, 393);
		}
		else if (game.gameState == STATE.Help)
		{
			Font font = new Font("arial", 1, 50);
			Font font2 = new Font("arial", 1, 30);
			
			g.setFont(font);
			g.setColor(Color.white);
			g.drawString("Help", 245, 50);
			
			g.setFont(font2);
			g.drawRect(210, 350, 200, 64);
			g.drawString("Back", 278, 393);
		}
		
		
	}
}
