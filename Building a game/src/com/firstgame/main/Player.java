package com.firstgame.main;

 

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.firstgame.main.Game.STATE;

public class Player extends GameObject
{

	Random r = new Random();
	Handler handler;
	
	public static int damage = 2;
	
	private static String winner = "";
	
	public Player(float x, float y, ID id, Handler handler) 
	{
		super(x, y, id);
		this.handler = handler;
		
	}
	
	public Rectangle getBounds() 
	{
		return new Rectangle((int)x, (int)y, 32, 32);
	}

	public void tick() 
	{
		x += velX;
		y += velY;
		
		x = Game.clamp(x, 0, Game.WIDTH - 47);
		y = Game.clamp(y, 0, Game.HEIGHT - 70);
		
		if (id == ID.Player) handler.addObject(new Trail(x, y, ID.Trail, Color.blue, 32, 32, 0.08f, handler));
		if (id == ID.Player2) handler.addObject(new Trail(x, y, ID.Trail, Color.magenta, 32, 32, 0.08f, handler));
		
		collision();
	}
	
	private void collision()
	{
		for (int i = 0; i < handler.object.size(); i++)
		{
			GameObject tempObject = handler.object.get(i);
			
			if (tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy || tempObject.getId() == ID.SmartEnemy || tempObject.getId() == ID.EnemyBoss)
			{
				
				if (getBounds().intersects(tempObject.getBounds()))
				{
					//collision code
					if(!HUD.VS) HUD.HEALTH -=damage;
					else if (HUD.VS)
					{
						if(id == ID.Player) HUD.HEALTH -=damage;
						else if (id == ID.Player2) HUD.P2HEALTH -=damage;
					}
					
					if (!HUD.VS && HUD.HEALTH <= 0)
					{
						Game.gameState = STATE.GameOver;
					}
					if(HUD.VS && (HUD.HEALTH <=0 || HUD.P2HEALTH <=0))
					{
						if (HUD.HEALTH > 0) setWinner("Player 1");
						else setWinner("Player 2");
						Game.gameState = STATE.Win;
					}
				}
			}
		}
			
	}

	public void render(Graphics g) 
	{
		
		if (id == ID.Player) g.setColor(Color.blue);
		if (id == ID.Player2) g.setColor(Color.magenta);
		g.fillRect((int)x,(int) y, 32, 32);
	}

	public void setWinner(String winner)
	{
		this.winner = winner;
	}
	public static String getWinner()
	{
		return winner;
	}
	

}
