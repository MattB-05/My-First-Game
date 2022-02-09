package com.firstgame.main;

 

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Player extends GameObject
{

	Random r = new Random();
	Handler handler;
	
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
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.blue, 32, 32, 0.08f, handler));
		
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
					HUD.HEALTH -=2;
				}
			}
			
		}
	}

	public void render(Graphics g) 
	{
		
		if (id == ID.Player) g.setColor(Color.blue);
		//if (id == ID.Player2) g.setColor(Color.blue);
		g.fillRect((int)x,(int) y, 32, 32);
	}

	
	

}
