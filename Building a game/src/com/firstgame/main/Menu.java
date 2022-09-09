package com.firstgame.main;

 

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.text.AttributeSet.ColorAttribute;

import com.firstgame.main.Game.STATE;


public class Menu extends MouseAdapter
{
	
	private Game game;
	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	
	public Menu(Game game, Handler handler, HUD hud) 
	{
		this.game = game;
		this.handler = handler;
		this.hud = hud;
	}
	
	public void mousePressed(MouseEvent e)
	{
		int mx = e.getX();
		int my = e.getY();
		
		
		if (Game.gameState == STATE.Menu)
		{
			Spawn.scoreKeep = 0;
			
			//Play button
			if (mouseOver(mx, my, 210, 150, 200, 64))
			{
				Game.gameState = STATE.GameSelect;
				return;
				
			}
			
			//Help button
			if (mouseOver(mx, my, 210, 250, 200, 64))
			{
				Game.gameState = STATE.Help;
			}
					
			
			//Quit button
			if (mouseOver(mx, my, 210, 350, 200, 64))
			{
				System.exit(1);
			}
		}
		
		if (Game.gameState == STATE.Help)
		{
			//Back button in help
			if (mouseOver(mx, my, 210, 350, 200, 64))
			{
				Game.gameState = STATE.Menu;
				return;
			}		
		}
		
		//Game Over Screen
		if (Game.gameState == STATE.GameOver)
		{
			HUD.HEALTH = 100;
			hud.setScore(0);
			hud.setLevel(1);
			Spawn.scoreKeep = 0;
			
			//Play Again button
			if (mouseOver(mx, my, 210, 150, 200, 64))
			{
				Game.gameState = STATE.Game;
				
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler)); //Player1
				if (Player.damage == 1 || HUD.VS == true) handler.addObject(new Player(Game.WIDTH/2+20, Game.HEIGHT/2-32, ID.Player2, handler)); // Possible Player 2
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
				
			}
			
			//Menu button
			if (mouseOver(mx, my, 210, 250, 200, 64))
			{
				Game.gameState = STATE.Menu;
			}
					
			
			//Quit button
			if (mouseOver(mx, my, 210, 350, 200, 64))
			{
				System.exit(1);
			}
		}
		//Win Screen
				if (Game.gameState == STATE.Win)
				{
					HUD.HEALTH = 100;
					HUD.P2HEALTH = 100;
					
					hud.setScore(0);
					hud.setLevel(1);
					Spawn.scoreKeep = 0;
					
					//Play Again button
					if (mouseOver(mx, my, 210, 150, 200, 64))
					{
						Game.gameState = STATE.Game;
						
						handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler)); //Player1
						if(HUD.VS) handler.addObject(new Player(Game.WIDTH/2+20, Game.HEIGHT/2-32, ID.Player2, handler)); // Possible Player 2
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
						
					}
					
					//Menu button
					if (mouseOver(mx, my, 210, 250, 200, 64))
					{
						Game.gameState = STATE.Menu;
						
						HUD.VS = false;
					}
							
					
					//Quit button
					if (mouseOver(mx, my, 210, 350, 200, 64))
					{
						System.exit(1);
					}
				}
		
		//Game Selection Screen
		if (Game.gameState == STATE.GameSelect)
		{
						
			//Solo button
			if (mouseOver(mx, my, 210, 150, 200, 64))
			{
				Spawn.scoreKeep = 0;
				
				Player.damage = 2;
				
				handler.clearEnemies();
				
			    Game.gameState = STATE.Game;
				
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler)); //Player1
				
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
				
			}
			
			//Team button
			if (mouseOver(mx, my, 210, 250, 200, 64))
			{
				Game.gameState = STATE.Game;
				
				
				Player.damage = 1;
				
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler)); //Player1
				handler.addObject(new Player(Game.WIDTH/2+20, Game.HEIGHT/2-32, ID.Player2, handler)); // Possible Player 2
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
			}
					
			
			//VS. button
			if (mouseOver(mx, my, 210, 350, 200, 64))
			{
				Game.gameState = STATE.Game;
				
				HUD.VS = true;
				Player.damage = 2;
				
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler)); //Player1
				handler.addObject(new Player(Game.WIDTH/2+20, Game.HEIGHT/2-32, ID.Player2, handler)); // Possible Player 2
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
			}
			
			//Back button
			if (mouseOver(mx, my, 10, 350, 70, 64))
			{
				Game.gameState = STATE.Menu;
				
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
		if (Game.gameState == STATE.Menu)
		{
			handler.addObject(new MenuEffect(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.MenuEffect, handler));

			
			Font font = new Font("arial", 1, 50);
			Font font2 = new Font("arial", 1, 30);
			
			g.setFont(font);
			g.setColor(Color.white);
			g.drawString("Surge", 245, 50);
			
			g.setFont(font2);
			g.drawRect(210, 150, 200, 64);
			g.setColor(Color.black);
			g.fillRect(211, 151, 199, 63);
			g.setColor(Color.white);
			g.drawString("Play", 278, 193);
			
			g.drawRect(210, 250, 200, 64);
			g.setColor(Color.black);
			g.fillRect(211, 251, 199, 63);
			g.setColor(Color.white);
			g.drawString("Help", 278, 293);
			
			g.drawRect(210, 350, 200, 64);
			g.setColor(Color.black);
			g.fillRect(211, 351, 199, 63);
			g.setColor(Color.white);
			g.drawString("Quit", 278, 393);
		}
		else if (Game.gameState == STATE.Help)
		{
			handler.addObject(new MenuEffect(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.MenuEffect, handler));

			
			Font font = new Font("arial", 1, 50);
			Font font2 = new Font("arial", 1, 30);
			Font font3 = new Font("arial", 1, 20);
			
			g.setFont(font);
			g.setColor(Color.white);
			g.drawString("Help", 245, 50);
			
			g.drawRect(40,60,560,280);
			g.setColor(Color.black);
			g.fillRect(41,61,559,279);
			g.setColor(Color.white);
			g.setFont(font3);
			g.drawString("Controls: For Player 1 Use the W, A, S, and D keys.", 45, 85);
			g.drawString("For Player 2 Use the ↑, ←, ↓, and → keys.", 139, 105);
			g.drawString("SpaceBar to Pause.", 139, 125);
			g.drawString("How To Play: Dodge a variety of enemies!", 45, 155);
			g.drawString("Stay alive as long as possible!", 175, 175);
			g.drawString("Outlast the BOSS to beat the game!", 175, 195);
			g.drawString("Have Fun!!", 175, 215);
			
			g.setFont(font2);
			g.drawRect(210, 350, 200, 64);
			g.setColor(Color.black);
			g.fillRect(211,351, 199, 63);
			g.setColor(Color.white);
			g.drawString("Back", 278, 393);
		}
		else if (Game.gameState == STATE.GameOver)
		{
			handler.addObject(new MenuEffect(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.MenuEffect, handler));

			
			Font font = new Font("arial", 1, 50);
			Font font2 = new Font("arial", 1, 30);
			
			handler.clearAll();
			
			g.setFont(font);
			g.setColor(Color.RED);
			g.drawString("Game Over", 180, 75);
			
			g.setFont(font2);
			g.drawRect(210, 150, 200, 64);
			g.setColor(Color.black);
			g.fillRect(211, 151, 199, 63);
			g.setColor(Color.red);
			g.drawString("Play Again", 238, 193);
			
			g.drawRect(210, 250, 200, 64);
			g.setColor(Color.black);
			g.fillRect(211, 251, 199, 63);
			g.setColor(Color.red);
			g.drawString("Menu", 278, 293);
			
			g.drawRect(210, 350, 200, 64);
			g.setColor(Color.black);
			g.fillRect(211, 351, 199, 63);
			g.setColor(Color.red);
			g.drawString("Quit", 278, 393);
		}
		
		else if (Game.gameState == STATE.GameSelect)
		{
			handler.addObject(new MenuEffect(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.MenuEffect, handler));

			
			Font font = new Font("arial", 1, 50);
			Font font2 = new Font("arial", 1, 30);
			
			g.setFont(font);
			g.setColor(Color.blue);
			g.drawString("Game Modes", 173, 50);
			
			g.setFont(font2);
			g.drawRect(210, 150, 200, 64);
			g.setColor(Color.black);
			g.fillRect(211, 151, 199, 63);
			g.setColor(Color.blue);
			g.drawString("Solo", 278, 193);
			
			g.drawRect(210, 250, 200, 64);
			g.setColor(Color.black);
			g.fillRect(211, 251, 199, 63);
			g.setColor(Color.blue);
			g.drawString("Team", 278, 293);
			
			g.drawRect(210, 350, 200, 64);
			g.setColor(Color.black);
			g.fillRect(211, 351, 199, 63);
			g.setColor(Color.blue);
			g.drawString(" VS.", 278, 393);
			
			g.setColor(Color.white);
			g.drawRect(10, 350, 70, 64);
			g.setColor(Color.black);
			g.fillRect(11, 351, 69, 63);
			g.setColor(Color.white);
			g.drawString("Back", 10, 393);
			
		}
		
		else if (Game.gameState == STATE.Win)
		{
			handler.addObject(new MenuEffect(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.MenuEffect, handler));

			
			handler.clearAll();
			
			Font font = new Font("arial", 1, 50);
			Font font3 = new Font("arial", 1, 30);
			Font font2 = new Font("arial", 1, 30);
			
			g.setFont(font);
			g.setColor(Color.green);
			if(HUD.VS && (Spawn.Tie == false)) g.drawString(Player.getWinner() + " wins!", 163, 50);
			if(HUD.VS && (Spawn.Tie == true)) g.drawString("It's A Tie", 220, 50);
			if(!HUD.VS) {g.setFont(font3); g.drawString("Congragulation! You Have Beat The Game!", 13, 50); }
			
			g.setFont(font2);
			g.drawRect(210, 150, 200, 64);
			g.setColor(Color.black);
			g.fillRect(211, 151, 199, 63);
			g.setColor(Color.green);
			g.drawString("Play Again", 238, 193);
			
			g.drawRect(210, 250, 200, 64);
			g.setColor(Color.black);
			g.fillRect(211, 251, 199, 63);
			g.setColor(Color.green);
			g.drawString("Menu", 278, 293);
			
			g.drawRect(210, 350, 200, 64);
			g.setColor(Color.black);
			g.fillRect(211, 351, 199, 63);
			g.setColor(Color.green);
			g.drawString("Quit", 278, 393);
		
			
		}
		
		
	}
}
